package com.example.gs.gonser.govenmentservice.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.baseadpater.AutoRVAdapter;
import com.example.gs.gonser.govenmentservice.baseadpater.ViewHolder;
import com.example.gs.gonser.govenmentservice.bean.FlowsBean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/15.
 * 适配器，我的办件详情
 */

public class MyDoThingsInfoAdapter extends AutoRVAdapter {
    public List<?> list;

    private Context context;
    public MyDoThingsInfoAdapter(Context context, List<?> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.mydothinginfo_jd_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FlowsBean flows = (FlowsBean) list.get(position);

        String isAppro = flows.getIsApprove();
        String stepnamestr = flows.getStepname();

        TextView qiuqiushang = holder.getTextView(R.id.qiuqiushang);
        TextView qiuqiuxia = holder.getTextView(R.id.qiuqiuxia);
        TextView qiuqiu = holder.getTextView(R.id.qiuqiu);
        TextView stepname = holder.getTextView(R.id.stepname);
        TextView finishtime = holder.getTextView(R.id.finishedtime);
        TextView remark = holder.getTextView(R.id.remark);

        //去掉球球最上面线和最下面线
        if (position==0){
            qiuqiushang.setVisibility(View.INVISIBLE);
        }
        if (position==list.size()-1){
            qiuqiuxia.setVisibility(View.INVISIBLE);
        }

        stepname.setText(flows.getStepname());
        remark.setText(flows.getRemark());
        finishtime.setText(flows.getFinishtime());

//        //是否审批
//        if (isAppro.equals("true")){
//            finishtime.setText(flows.getFinishtime());
//            actorid_name.setText(flows.getActorid_name());
//            remark.setText(flows.getRemark());
//            qiuqiu.setBackgroundResource(R.drawable.theme_qiuqiu);
//        }else {
//            finishtime.setText("");
//            actorid_name.setText("");
//            remark.setText("");
//            qiuqiu.setBackgroundResource(R.drawable.gray_qiuqiu);
//        }
    }

}
