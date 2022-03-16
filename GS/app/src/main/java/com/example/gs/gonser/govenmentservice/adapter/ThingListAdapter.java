package com.example.gs.gonser.govenmentservice.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.baseadpater.AutoRVAdapter;
import com.example.gs.gonser.govenmentservice.baseadpater.ViewHolder;
import com.example.gs.gonser.govenmentservice.bean.ThingListBean;
import com.example.gs.gonser.govenmentservice.utils.MyRatingBar;

import java.util.List;

/**
 * Created by yangzhao on 2017/9/15.
 * 适配器，事项清单
 */

public class ThingListAdapter extends AutoRVAdapter {
    public List<?> list;
    private Context context;

    public ThingListAdapter(Context context, List<?> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.thinglist_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ThingListBean bean = (ThingListBean) list.get(position);
        TextView title = holder.getTextView(R.id.title);
        TextView info = holder.getTextView(R.id.info);
        MyRatingBar star = holder.getRatingBar(R.id.star);
        star.setStar(Integer.parseInt(bean.getStarSize()));
        title.setText(bean.getTitle());
        info.setText(bean.getInfo());
    }
}
