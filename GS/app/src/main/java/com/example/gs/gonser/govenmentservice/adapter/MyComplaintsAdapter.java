package com.example.gs.gonser.govenmentservice.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.baseadpater.AutoRVAdapter;
import com.example.gs.gonser.govenmentservice.baseadpater.ViewHolder;
import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.bean.ThingListBean;
import com.example.gs.gonser.govenmentservice.interfaces.MyCallBack;
import com.example.gs.gonser.govenmentservice.ui.ItemListActivity;
import com.example.gs.gonser.govenmentservice.utils.MyOkHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangzhao on 2017/9/15.
 * 适配器，我的咨询投诉
 */

public class MyComplaintsAdapter extends AutoRVAdapter {
    public List<?> list;
    private Context context;
    public MyComplaintsAdapter(Context context, List<?> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.mycomplaints_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ThingListBean bean = (ThingListBean) list.get(position);
        //借用事项列表的实体类
        TextView title = holder.getTextView(R.id.title);
        TextView content = holder.getTextView(R.id.content);
        TextView date = holder.getTextView(R.id.date);
        TextView dafu = holder.getTextView(R.id.dafu);
        TextView item = holder.getTextView(R.id.item);

        title.setText(bean.getTitle());
        content.setText(bean.getInfo());
        date.setText(bean.getDate());
        dafu.setText(bean.getDafu());
        item.setText(bean.getUserid());//事项名称
    }
}
