package com.example.gs.gonser.govenmentservice.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.baseadpater.AutoRVAdapter;
import com.example.gs.gonser.govenmentservice.baseadpater.ViewHolder;
import com.example.gs.gonser.govenmentservice.bean.Constant;

import java.util.List;
import java.util.Map;

/**
 * Created by yangzhao on 2017/9/15.
 * 适配器，所有的便民服务
 */

public class ConvenRecyclerAdapter extends AutoRVAdapter {
    public List<?> list;
    private Context context;

    public ConvenRecyclerAdapter(Context context, List<?> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.convengridviewitem;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Map map = (Map) list.get(position);
        ImageView imageView = holder.getImageView(R.id.image);
        TextView textView = holder.getTextView(R.id.text);

        Glide.with(context).load(map.get("image")).into(imageView);
        textView.setText(map.get("text").toString());
    }
}
