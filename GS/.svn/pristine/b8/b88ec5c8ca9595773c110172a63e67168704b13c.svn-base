package com.example.gs.gonser.govenmentservice.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.baseadpater.AutoRVAdapter;
import com.example.gs.gonser.govenmentservice.baseadpater.ViewHolder;
import com.example.gs.gonser.govenmentservice.bean.ThingListBean;

import java.util.List;
import java.util.Map;

/**
 * Created by yangzhao on 2017/9/15.
 * 适配器，办件详情里面的基本信息
 */

public class DoThingBasicAdapter extends AutoRVAdapter {
    public List<?> list;
    private Context context;
    public DoThingBasicAdapter(Context context, List<?> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.dothinginfoitem_html;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //这里借用事项列表的实体类

        Map map = (Map) list.get(position);
        TextView name = holder.getTextView(R.id.name);
        TextView content = holder.getTextView(R.id.content);

        String nameStr = map.get("name").toString();
        if (TextUtils.isEmpty(nameStr)){
            name.setVisibility(View.GONE);
        }
        name.setText(map.get("name").toString()+"");
        content.setText(map.get("content").toString());
    }
}
