package com.weibangbang.aty.home;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.weibangbang.R;
import com.weibangbang.api.Config;
import com.weibangbang.aty.LoginAty;
import com.weibangbang.base.BaseActivity;
import com.weibangbang.bean.home.VipListBean;
import com.weibangbang.presenter.HomePresenter;
import com.weibangbang.utils.ToastUtils;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/9/25 15:54
 * 功能描述：开通会员
 */
public class OpenMemberAty extends BaseActivity {
    private TextView commit_tv;
    private TextView[] priceTextViews;
    private ImageView[] imagebuttons2;
    private TextView[] textviews;
    private int redColor, txtColor;
    private String price;
    private String regularPrice;
    private String seniorPrice;
    private HomePresenter mHomePresenter;
    private String regularUpgrade;
    private String seniorUpgrade;
    private String upgrade;

    @Override
    public int getLayoutId() {
        return R.layout.activity_open_member;
    }

    @Override
    public void initView() {
        setTitleBar(getResources().getString(R.string.kaitonghuiyuan), true);
        commit_tv = findViewById(R.id.commit_tv);
        commit_tv.setText(R.string.become_regular_member);
        redColor = ContextCompat.getColor(this, R.color.red_bg);
        txtColor = ContextCompat.getColor(this, R.color.txt_color);
        priceTextViews = new TextView[2];
        priceTextViews[0] = findViewById(R.id.left_price_tv);
        priceTextViews[1] = findViewById(R.id.right_price_tv);
        priceTextViews[0].setBackgroundResource(R.mipmap.icon_red_circle);
        imagebuttons2 = new ImageView[2];
        imagebuttons2[0] = findViewById(R.id.regular_member_img2);
        imagebuttons2[1] = findViewById(R.id.senior_member_img2);
        imagebuttons2[0].setSelected(true);
        textviews = new TextView[2];
        textviews[0] = findViewById(R.id.regular_member_tv);
        textviews[1] = findViewById(R.id.senior_member_tv);
        textviews[0].setTextColor(redColor);
        price = "99.00";
    }

    @Override
    public void initData() {
        mHomePresenter = new HomePresenter(this);
        mHomePresenter.postVipList(Config.getToken());
    }

    /**
     * 普通会员
     *
     * @param view
     */
    public void onRegularClicked(View view) {
        priceTextViews[0].setBackgroundResource(R.mipmap.icon_red_circle);
        priceTextViews[1].setBackgroundResource(R.mipmap.icon_grey_circle);
        imagebuttons2[0].setSelected(true);
        imagebuttons2[1].setSelected(false);
        textviews[0].setTextColor(redColor);
        textviews[1].setTextColor(txtColor);
        commit_tv.setText(R.string.become_regular_member);
        price = regularPrice;
        upgrade=regularUpgrade;
    }

    /**
     * 高级会员
     *
     * @param view
     */
    public void onSeniorClicked(View view) {
        priceTextViews[0].setBackgroundResource(R.mipmap.icon_grey_circle);
        priceTextViews[1].setBackgroundResource(R.mipmap.icon_red_circle);
        imagebuttons2[1].setSelected(true);
        imagebuttons2[0].setSelected(false);
        textviews[1].setTextColor(redColor);
        textviews[0].setTextColor(txtColor);
        commit_tv.setText(R.string.become_senior_member);
        price = seniorPrice;
        upgrade=seniorUpgrade;
    }


    @Override
    public void onComplete(final String requestUrl, String jsonStr) {
        super.onComplete(requestUrl, jsonStr);
        if (requestUrl.endsWith("Vip/vip_list.html")) {
            VipListBean vipListBean = JSON.parseObject(jsonStr, VipListBean.class);
            List<VipListBean.DataBean> data = vipListBean.getData();
            if (null!=data && data.size()==2){
                regularPrice=data.get(0).getVip_price();
                priceTextViews[0].setText(regularPrice);
                String vip_name = data.get(0).getVip_name();
                textviews[0].setText(vip_name);
                regularUpgrade= String.valueOf(data.get(0).getVip_id());
                upgrade=regularUpgrade;
                price=regularPrice;
                seniorPrice=data.get(1).getVip_price();
                priceTextViews[1].setText(seniorPrice);
                String vip_name1 = data.get(1).getVip_name();
                seniorUpgrade= String.valueOf(data.get(1).getVip_id());
                textviews[1].setText(vip_name1);

            }
        }
    }

    @Override
    public void onFailure(String msg) {
        super.onFailure(msg);
    }

    /**
     * 开通会员
     *
     * @param view
     */
    public void onCommit(View view) {
        if (!Config.isLogin()){
            startActivity(LoginAty.class);
            return;
        }
        if (!TextUtils.isEmpty(price) && !TextUtils.isEmpty(upgrade)){
            Bundle bundle = new Bundle();
            bundle.putString("price", price);
            bundle.putString("upgrade",upgrade);
            startActivity(PayMemberAty.class, bundle);
        }else {
            ToastUtils.showToast("数据获取错误，请稍后重试");
        }

    }
}
