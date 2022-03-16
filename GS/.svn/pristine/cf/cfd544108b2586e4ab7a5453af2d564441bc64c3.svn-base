package com.example.gs.gonser.govenmentservice.adapter;

import android.content.Context;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.baseadpater.AutoRVAdapter;
import com.example.gs.gonser.govenmentservice.baseadpater.ViewHolder;
import com.example.gs.gonser.govenmentservice.bean.DoThingBean;
import com.example.gs.gonser.govenmentservice.bean.PolicyReadBean;

import java.util.List;

/**
 * Created by yangzhao on 2017/9/15.
 * 适配器，政策解读/通知公告
 */

public class PolicyReadingAdapter extends AutoRVAdapter {
    public List<?> list;
    private Context context;

    public PolicyReadingAdapter(Context context, List<?> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.policyreading_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        PolicyReadBean bean = (PolicyReadBean) list.get(position);
        ImageView image = holder.getImageView(R.id.image);
        TextView title = holder.getTextView(R.id.title);
        TextView content = holder.getTextView(R.id.content);
        TextView date = holder.getTextView(R.id.date);

        title.setText(bean.getTitle());
        date.setText(bean.getDate());


//        Glide.with(context)
//                .load(bean.getUrl())
//                .placeholder(R.mipmap.nopic)
//                .into(image);
    }
}
