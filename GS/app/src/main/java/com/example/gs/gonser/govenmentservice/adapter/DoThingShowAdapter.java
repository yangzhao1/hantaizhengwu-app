package com.example.gs.gonser.govenmentservice.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.baseadpater.AutoRVAdapter;
import com.example.gs.gonser.govenmentservice.baseadpater.ViewHolder;
import com.example.gs.gonser.govenmentservice.bean.DoThingBean;
import com.example.gs.gonser.govenmentservice.bean.ThingListBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by yangzhao on 2017/9/15.
 * 适配器，办件公示
 */

public class DoThingShowAdapter extends AutoRVAdapter {
    public List<?> list;
    private Context context;

    public DoThingShowAdapter(Context context, List<?> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.dothingshow_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        DoThingBean bean = (DoThingBean) list.get(position);
        TextView thing = holder.getTextView(R.id.thing);
        TextView startTime = holder.getTextView(R.id.startTime);
        TextView creatname = holder.getTextView(R.id.creatname);
        TextView status = holder.getTextView(R.id.status);

//        orgname.setText(bean.getOrgname());
        thing.setText(bean.getThing());
//        code.setText(bean.getCode());
        String []d = bean.getStartTime().split(" ");
        startTime.setText(d[0]);
        creatname.setText(bean.getEndTime());

        String linkType = bean.getStatus();
        status.setText(linkType);
    }
}
