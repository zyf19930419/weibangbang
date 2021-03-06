package com.weibangbang.aty.personal;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.weibangbang.R;
import com.weibangbang.aliPay.AliPay;
import com.weibangbang.aliPay.AuthResult;
import com.weibangbang.api.Config;
import com.weibangbang.base.BaseActivity;
import com.weibangbang.bean.personal.AliInfoBean;
import com.weibangbang.presenter.PersonalPresenter;
import com.weibangbang.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 创建者：voodoo_jie
 * <br>创建时间：2018/10/10 010下午 01:40
 * <br>功能描述：提现界面
 */
public class WithdrawMoneyAty extends BaseActivity implements View.OnClickListener {

    private TextView withdrawMoney_money_tv;
    private EditText withdrawMoney_moneyInput_et;
    private TextView commit_tv;
    private ImageView alipay_img;
    private ImageView wechat_img;
    private TextView alipay_tip_tv;
    private TextView wechat_tip_tv;
    private TextView tip_tv;
    private PersonalPresenter mPersonalPresenter;
    private float total_price;
    private String pay_way="2";

    @Override
    public int getLayoutId() {
        return R.layout.activity_withdraw_money;
    }

    @Override
    public void initView() {
        setTitleBar(getResources().getString(R.string.put_forward), true);

        commit_tv = findViewById(R.id.commit_tv);
        alipay_img = findViewById(R.id.alipay_img);
        wechat_img = findViewById(R.id.wechat_img);
        alipay_tip_tv = findViewById(R.id.alipay_tip_tv);
        wechat_tip_tv = findViewById(R.id.wechat_tip_tv);
        tip_tv=findViewById(R.id.tip_tv);
        withdrawMoney_moneyInput_et = findViewById(R.id.withdrawMoney_moneyInput_et);
        withdrawMoney_money_tv=findViewById(R.id.withdrawMoney_money_tv);

        commit_tv.setText(R.string.lijitixian); // 立即提现按钮
        commit_tv.setOnClickListener(this);
        withdrawMoney_moneyInput_et.addTextChangedListener(new MyTextWatcher());

        alipay_img.setSelected(true);
        alipay_tip_tv.setVisibility(View.VISIBLE);
        alipay_tip_tv.setText("提现￥0.00");
        wechat_tip_tv.setText("提现￥0.00");

        alipay_tip_tv.setSingleLine(true);
        wechat_tip_tv.setSingleLine(true);

    }

    @Override
    public void initData() {
        mPersonalPresenter=new PersonalPresenter(this);
        mPersonalPresenter.postUserBalance(Config.getToken());
    }

    public void onAlipay(View view) {
        alipay_img.setSelected(true);
        alipay_tip_tv.setVisibility(View.VISIBLE);
        wechat_img.setSelected(false);
        wechat_tip_tv.setVisibility(View.GONE);
        pay_way="2";
    }

    public void onWechat(View view) {
        alipay_img.setSelected(false);
        alipay_tip_tv.setVisibility(View.GONE);
        wechat_img.setSelected(true);
        wechat_tip_tv.setVisibility(View.VISIBLE);
        pay_way="1";
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.commit_tv:
                String s = withdrawMoney_moneyInput_et.getText().toString();
                if (s.isEmpty()) {
                    showShortToast("请输入金额", Toast.LENGTH_SHORT);
                    return;
                }
                if (Float.parseFloat(s)>total_price){
                    showShortToast("输入金额不能大于可提现金额", Toast.LENGTH_SHORT);
                    return;
                }
                if ("1".equals(pay_way)){

                }else if ("2".equals(pay_way)){
                    mPersonalPresenter.postAliShare(Config.getToken());
                }
                break;
        }
    }

    private String removeBrackets(String str, boolean remove) {
        if (remove) {
            if (!TextUtils.isEmpty(str)) {
                if (str.startsWith("\"")) {
                    str = str.replaceFirst("\"", "");
                }
                if (str.endsWith("\"")) {
                    str = str.substring(0, str.length() - 1);
                }
            }
        }
        return str;
    }

    private String getValue(String header, String data) {
        return data.substring(header.length(), data.length());
    }

    @Override
    public void onComplete(String requestUrl, String jsonStr) {
        super.onComplete(requestUrl, jsonStr);
        if (requestUrl.endsWith("User/withdrawal.html")){
            try {
                JSONObject jsonObject=new JSONObject(jsonStr);
                ToastUtils.showToast(jsonObject.optString("msg"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return;
        }

        if (requestUrl.endsWith("User/ali_share.html")){
            AliInfoBean aliInfoBean= com.alibaba.fastjson.JSONObject.parseObject(jsonStr,AliInfoBean.class);
            AliInfoBean.DataBean.ParamDataBean paramData = aliInfoBean.getData().getParamData();
            Log.e("TAG", paramData.toString());
            AliPay aliPay=new AliPay(paramData.toString(), new AliPay.OnAuthInterface() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Log.e("TAG", "onSuccess: "+authResult.getResult());
                    String[] resultValue = authResult.getResult().split("&");
                    String user_id = "";
                    for (String value : resultValue) {
                        if (value.startsWith("user_id")) {
                            user_id = removeBrackets(getValue("user_id=", value), true);
                            break;
                        }
                    }
                    Log.e("TAG", "user_id: "+user_id);
                    mPersonalPresenter.postWithDrawal(Config.getToken(),withdrawMoney_moneyInput_et.getText().toString(),pay_way,user_id);
                }

                @Override
                public void onFailure() {

                }
            });
            aliPay.setMessageWhat(AliPay.SDK_AUTH_FLAG);
            aliPay.pay();
            return;
        }
        /**
         * {"code":1,"msg":"","data":{"user_balance":"99.00"}}
         */
        if (requestUrl.endsWith("User/user_balance.html")){
            try {
                JSONObject jsonObject=new JSONObject(jsonStr);
                JSONObject jsonObject2=new JSONObject(jsonObject.optString("data"));
                String user_balance = jsonObject2.optString("user_balance");
                String charge = jsonObject2.optString("charge");
                total_price=Float.parseFloat(user_balance);
                withdrawMoney_money_tv.setText(user_balance);
                tip_tv.setText("提示：提现手续费用按照提现总额的"+charge+"%收取");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return;
        }
    }

    class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            alipay_tip_tv.setText("提现￥" + (editable.toString().isEmpty() ? "0.00" : editable.toString()));
            wechat_tip_tv.setText("提现￥" + (editable.toString().isEmpty() ? "0.00" : editable.toString()));
        }
    }


}
