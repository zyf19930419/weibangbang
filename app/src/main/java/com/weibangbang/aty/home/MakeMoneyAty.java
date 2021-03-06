package com.weibangbang.aty.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.weibangbang.R;
import com.weibangbang.aty.member.TaskHallAty;
import com.weibangbang.base.BaseActivity;
import com.weibangbang.bean.home.MakeMoneyBean;
import com.weibangbang.presenter.HomePresenter;

import java.util.List;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/9/25 15:50
 * 功能描述：我要赚佣
 */
public class MakeMoneyAty extends BaseActivity {

    private RecyclerView mRecyclerView;
    private MakeMoneyAdapter mMoneyAdapter;
    private TextView commit_tv;
    private HomePresenter mHomePresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_make_money;
    }

    @Override
    public void initView() {
        setTitleBar(getResources().getString(R.string.woyaozhuangyong), true);

        mRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        commit_tv = findViewById(R.id.commit_tv);
        commit_tv.setText(R.string.lijizhuanyong);
    }

    @Override
    public void initData() {
        mHomePresenter = new HomePresenter(this);
        mHomePresenter.postMakeMoney();
    }

    public void onCommit(View view) {
        startActivity(TaskHallAty.class);
    }

    @Override
    public void onComplete(String requestUrl, String jsonStr) {
        super.onComplete(requestUrl, jsonStr);
        if (requestUrl.endsWith("Index/commission.html")){
            MakeMoneyBean makeMoneyBean = JSON.parseObject(jsonStr, MakeMoneyBean.class);
            List<MakeMoneyBean.DataBean> data = makeMoneyBean.getData();
            if (null != data){
                mMoneyAdapter = new MakeMoneyAdapter(data);
                mRecyclerView.setAdapter(mMoneyAdapter);
            }
        }
    }

    @Override
    public void onFailure(String msg) {
        super.onFailure(msg);
    }

    private class MakeMoneyAdapter extends RecyclerView.Adapter<MakeMoneyAdapter.ViewHolder> {
//        private String[] titles={"扫码下载APP","支付99元成为VIP","【任务中心】领取任务发朋友圈保留两小时","【提交任务】按照任务模板截图上传","赚取8元一天的佣金"};
        private List<MakeMoneyBean.DataBean> data;

        public MakeMoneyAdapter(List<MakeMoneyBean.DataBean> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.make_money_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.content_tv.setText("第" + data.get(position).getCommission_id() + "步\u3000~\u3000" + data.get(position).getCommission_content());
        }

        @Override
        public int getItemCount() {
            return data.size()>0?data.size():0;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView content_tv;

            public ViewHolder(View itemView) {
                super(itemView);
                content_tv = itemView.findViewById(R.id.content_tv);
            }
        }
    }
}
