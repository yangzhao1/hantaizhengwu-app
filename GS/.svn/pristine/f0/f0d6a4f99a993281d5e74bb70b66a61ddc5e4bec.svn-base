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
 * 适配器，我的评价
 */

public class MyEvalAdapter extends AutoRVAdapter {
    public List<?> list;
    private Context context;
    public MyEvalAdapter(Context context, List<?> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.myeval_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //这里借用事项列表的实体类
        ThingListBean bean = (ThingListBean) list.get(position);
        TextView evalcontent = holder.getTextView(R.id.evalcontent);
        TextView itemname = holder.getTextView(R.id.itemname);
        TextView date = holder.getTextView(R.id.date);

        evalcontent.setText(bean.getInfo());
        itemname.setText(bean.getTitle());
        String []d = bean.getDate().split(" ");
        date.setText(d[0]);
    }
}
