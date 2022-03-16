package com.example.gs.gonser.govenmentservice.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.baseadpater.AutoRVAdapter;
import com.example.gs.gonser.govenmentservice.baseadpater.ViewHolder;
import com.example.gs.gonser.govenmentservice.bean.Constant;
import com.example.gs.gonser.govenmentservice.bean.ItemListBean;
import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.bean.ThingListBean;
import com.example.gs.gonser.govenmentservice.bean.UserBean;
import com.example.gs.gonser.govenmentservice.interfaces.MyCallBack;
import com.example.gs.gonser.govenmentservice.ui.GoConsultActivity;
import com.example.gs.gonser.govenmentservice.ui.ItemInfoActivity;
import com.example.gs.gonser.govenmentservice.ui.ItemListActivity;
import com.example.gs.gonser.govenmentservice.ui.LoginActivity;
import com.example.gs.gonser.govenmentservice.ui.OnlineDoActivity;
import com.example.gs.gonser.govenmentservice.ui.OnlineDoWebActivity;
import com.example.gs.gonser.govenmentservice.utils.LogUtils;
import com.example.gs.gonser.govenmentservice.utils.MyOkHttpClient;
import com.example.gs.gonser.govenmentservice.utils.SharedPreferencesUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.gs.gonser.govenmentservice.R.color.textcolor;

/**
 * Created by yangzhao on 2017/9/15.
 * 适配器，事项列表
 */

public class ItemListAdapter extends AutoRVAdapter {
    public List<?> list;
    private Context context;
    private String userid="";
    private String userType="";

    public ItemListAdapter(Context context, List<?> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.hallthinglist_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ThingListBean bean = (ThingListBean) list.get(position);
        TextView title = holder.getTextView(R.id.title);
        TextView info = holder.getTextView(R.id.info);
        TextView onlinedo = holder.getTextView(R.id.onlinedo);
        TextView guildthing = holder.getTextView(R.id.guildthing);
        TextView collect = holder.getTextView(R.id.collect);

        onlinedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                userid = (String) SharedPreferencesUtils.getParam(context,Constant.USERID,"");
//                userType = (String) SharedPreferencesUtils.getParam(context, Constant.USERTYPE,"1");
//
//                if (!TextUtils.isEmpty(userid)){
//                    if (bean.getServerObject().indexOf(userType)!=-1){
//                        Intent intent = new Intent(context, OnlineDoWebActivity.class);
//                        intent.putExtra("itemid",bean.getId());
//                        intent.putExtra("itemName",bean.getTitle());
//                        context.startActivity(intent);
//                    }else {
//                        if (userType.equals("1")){
//                            Toast.makeText(context,"只有法人可以办理",Toast.LENGTH_SHORT).show();
//                        }else if (userType.equals("2")){
//                            Toast.makeText(context,"只有个人可以办理",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }else {
//                    context.startActivity(new Intent(context, LoginActivity.class));
//                }
            }
        });
        guildthing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, ItemInfoActivity.class);
//                intent.putExtra("id",bean.getId());
//                context.startActivity(intent);
            }
        });

//        collect.setText("我要收藏");
//        collect.setBackgroundResource(R.drawable.shape_bgs_theme);
//        collect.setTextColor(Color.WHITE);

        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                userid = (String) SharedPreferencesUtils.getParam(context,Constant.USERID,"");
//
//                if (!TextUtils.isEmpty(userid)){
//                    final Dialog dialog = ((ItemListActivity)context).showLoading();
//                    Map map = new HashMap();
//                    map.put("itemId",bean.getId());
//                    map.put("userId",userid);
//
////                try {
//                    map.put("itemName",bean.getTitle());
////                } catch (UnsupportedEncodingException e) {
////                    e.printStackTrace();
////                }
//                    LogUtils.i("收藏事项----"+map.toString());
//                    MyOkHttpClient.getNetClient().postCallNet(MyFacesUrl.collCetSave, map, new MyCallBack() {
//                        @Override
//                        public void onSuccess(String json) {
//                            try {
//                                JSONObject jsonObject = new JSONObject(json);
//                                final String message = jsonObject.getString("message");
//                                ((Activity)context).runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        dialog.dismiss();
//                                        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(int code) {
//                            ((Activity)context).runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    dialog.dismiss();
//                                    Toast.makeText(context,"服务器异常",Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }
//                    });
//                }else {
//                    context.startActivity(new Intent(context, LoginActivity.class));
//                }

            }
        });

        title.setText(bean.getTitle());
        info.setText(bean.getInfo());
    }
}
