package com.example.gs.gonser.govenmentservice.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import com.example.gs.gonser.govenmentservice.ui.ItemInfoActivity;
import com.example.gs.gonser.govenmentservice.ui.ItemListActivity;
import com.example.gs.gonser.govenmentservice.ui.OnlineDoActivity;
import com.example.gs.gonser.govenmentservice.utils.MyOkHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangzhao on 2017/9/15.
 * 适配器，我的收藏
 */

public class MyCollectAdapter extends AutoRVAdapter {
    public List<?> list;
    private Context context;
    public MyCollectAdapter(Context context, List<?> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.mycollect_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ThingListBean bean = (ThingListBean) list.get(position);
        TextView title = holder.getTextView(R.id.title);
        TextView info = holder.getTextView(R.id.info);
        ImageView collect = holder.getImageView(R.id.collect);

//        collect.setText("我要收藏");
//        collect.setBackgroundResource(R.drawable.shape_bgs_theme);
//        collect.setTextColor(Color.WHITE);

//        collect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Dialog dialog = ((ItemListActivity)context).showLoading();
//                Map map = new HashMap();
//                map.put("itemId ",bean.getId());
//                map.put("userId ",bean.getUserid());
//                map.put("itemName ",bean.getTitle());
//                MyOkHttpClient.getNetClient().postCallNet(MyFacesUrl.collCetSave, map, new MyCallBack() {
//                    @Override
//                    public void onSuccess(String json) {
//                        try {
//                            JSONObject jsonObject = new JSONObject(json);
//                            final String message = jsonObject.getString("message");
//                            ((Activity)context).runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    dialog.dismiss();
//                                    Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(int code) {
//                        ((Activity)context).runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                dialog.dismiss();
//                                Toast.makeText(context,"服务器异常",Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                });
//                notifyDataSetChanged();
//            }
//        });

        title.setText(bean.getTitle());
        info.setText(bean.getDate());
    }
}
