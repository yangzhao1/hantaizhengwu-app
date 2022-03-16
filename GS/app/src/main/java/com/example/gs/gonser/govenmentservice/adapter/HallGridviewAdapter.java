package com.example.gs.gonser.govenmentservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.bean.ThemeBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/13.
 */

public class HallGridviewAdapter extends BaseAdapter {
    private List<?> list;
    private Context context;

    public HallGridviewAdapter(Context context, List<?> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHandler viewHandler;
        if (view==null){
            viewHandler = new ViewHandler();
            view = LayoutInflater.from(context).inflate(R.layout.personal_gridviewitem,null);
            viewHandler.imageView = (ImageView) view.findViewById(R.id.image);
            viewHandler.textView = (TextView) view.findViewById(R.id.text);
            view.setTag(viewHandler);
        }else {
            viewHandler = (ViewHandler) view.getTag();
        }

        ThemeBean bean = (ThemeBean) list.get(i);
        int imageid = bean.getImageid();
        viewHandler.imageView.setBackgroundResource(imageid);
        viewHandler.textView.setText(bean.getTypeName());
//        if (i==list.size()-1){
//            viewHandler.textView.setText("其他");
//            viewHandler.imageView.setBackgroundResource(R.mipmap.other);
//        }
        return view;
    }

    class ViewHandler{
        ImageView imageView;
        TextView textView;
    }
}
