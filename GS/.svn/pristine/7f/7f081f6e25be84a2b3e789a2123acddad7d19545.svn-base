package com.example.gs.gonser.govenmentservice.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.baseadpater.AutoRVAdapter;
import com.example.gs.gonser.govenmentservice.baseadpater.ViewHolder;
import com.example.gs.gonser.govenmentservice.bean.DepartmentBean;

import java.util.List;

/**
 * Created by yangzhao on 2017/9/15.
 * 适配器，部门列表和事项列表POP
 */

public class DepartPopAdapter extends AutoRVAdapter {
    public List<?> list;
    private Context context;

    public DepartPopAdapter(Context context, List<?> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.departtext_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        DepartmentBean.DepartData bean = (DepartmentBean.DepartData) list.get(position);
        TextView text = holder.getTextView(R.id.text);
        text.setText(bean.getDepartname());
    }
}
