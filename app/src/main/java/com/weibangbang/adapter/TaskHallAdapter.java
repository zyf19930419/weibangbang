package com.weibangbang.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weibangbang.R;

/**
 * 创建者：zhangyunfei
 * 创建时间：2018/10/9 13:53
 * 功能描述：任务大厅、我的任务和公告栏列表页适配器
 */
public class TaskHallAdapter extends RecyclerView.Adapter<TaskHallAdapter.MyViewHolder> {
    private String mTitle;

    public TaskHallAdapter(String title) {
        mTitle = title;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if ("任务大厅".equals(mTitle)){
            holder.button_tv.setVisibility(View.VISIBLE);
            holder.date_tv.setVisibility(View.GONE);
            holder.button_tv.setText("查 看");
            holder.left_img.setImageResource(R.mipmap.icon_task);
        }

        if ("我的任务".equals(mTitle)){
            holder.button_tv.setVisibility(View.VISIBLE);
            holder.date_tv.setVisibility(View.GONE);
            holder.button_tv.setText("上传截图");
            holder.left_img.setImageResource(R.mipmap.icon_task);
        }

        if ("公告栏".equals(mTitle)){
            holder.button_tv.setVisibility(View.GONE);
            holder.date_tv.setVisibility(View.VISIBLE);
            holder.left_img.setImageResource(R.mipmap.icon_notice);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView left_img;
        private TextView title_tv,status_tv,button_tv,date_tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            left_img=itemView.findViewById(R.id.left_img);
            title_tv=itemView.findViewById(R.id.title_tv);
            status_tv=itemView.findViewById(R.id.status_tv);
            button_tv=itemView.findViewById(R.id.button_tv);
            date_tv=itemView.findViewById(R.id.date_tv);
        }
    }
}