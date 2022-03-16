package com.example.gs.gonser.govenmentservice.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.baseadpater.AutoRVAdapter;
import com.example.gs.gonser.govenmentservice.baseadpater.ViewHolder;
import com.example.gs.gonser.govenmentservice.bean.Constant;

import java.util.List;
import java.util.Map;

/**
 * Created by yangzhao on 2017/9/15.
 * 适配器，在线办理阅读须知POP
 */

public class OnlinePopAdapter extends AutoRVAdapter {
    public List<?> list;
    private Context context;

    public OnlinePopAdapter(Context context, List<?> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.text_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        String texts = (String) list.get(position);
        TextView text = holder.getTextView(R.id.text);
        text.setText((position+1)+". "+texts);
    }
}
