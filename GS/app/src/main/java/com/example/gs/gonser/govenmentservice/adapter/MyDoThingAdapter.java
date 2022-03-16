package com.example.gs.gonser.govenmentservice.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.baseadpater.AutoRVAdapter;
import com.example.gs.gonser.govenmentservice.baseadpater.ViewHolder;
import com.example.gs.gonser.govenmentservice.bean.Constant;
import com.example.gs.gonser.govenmentservice.bean.DoThingBean;
import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.bean.ThingListBean;
import com.example.gs.gonser.govenmentservice.interfaces.MyCallBack;
import com.example.gs.gonser.govenmentservice.popup.CommonPopupWindow;
import com.example.gs.gonser.govenmentservice.ui.GoEvaluateActivity;
import com.example.gs.gonser.govenmentservice.ui.ItemListActivity;
import com.example.gs.gonser.govenmentservice.ui.MyDoThingActivity;
import com.example.gs.gonser.govenmentservice.ui.OnlineDoWebActivity;
import com.example.gs.gonser.govenmentservice.ui.OnlineWebUpdateActivity;
import com.example.gs.gonser.govenmentservice.utils.LogUtils;
import com.example.gs.gonser.govenmentservice.utils.MyOkHttpClient;
import com.example.gs.gonser.govenmentservice.utils.SharedPreferencesUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangzhao on 2017/9/15.
 * 适配器，我的办件
 */

public class MyDoThingAdapter extends AutoRVAdapter {
    public List<?> list;
    private Context context;

    public MyDoThingAdapter(Context context, List<?> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.mydothing_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final DoThingBean bean = (DoThingBean) list.get(position);
        TextView itemname = holder.getTextView(R.id.itemname);
        TextView department = holder.getTextView(R.id.department);
        TextView date = holder.getTextView(R.id.date);
        TextView status = holder.getTextView(R.id.status);
        TextView eval = holder.getTextView(R.id.eval);

        String linkType = bean.getStatus();
        String pingjia = bean.getEndTime();
//        String pingjia = "1";

        if (linkType.equals("B2")) {
            linkType = "提交";
            eval.setVisibility(View.GONE);
        } else if (linkType.equals("A0")) {
            linkType = "派件";
            eval.setVisibility(View.GONE);
        } else if (linkType.equals("A1")) {
            linkType = "受理";
            eval.setVisibility(View.GONE);

        } else if (linkType.equals("A2")) {
            linkType = "审查";
            eval.setVisibility(View.GONE);

        } else if (linkType.equals("A3")) {
            linkType = "决定";
            eval.setVisibility(View.GONE);

        } else if (linkType.equals("B1")) {
            linkType = "暂存";
            eval.setVisibility(View.VISIBLE);
            eval.setText("修改");
        }else if (linkType.equals("C1")) {
            linkType = "予以许可";
            if (pingjia.equals("2")){
                eval.setVisibility(View.GONE);
            }else {
                eval.setVisibility(View.VISIBLE);
                eval.setText("我要评价");
            }
        }else if (linkType.equals("C2")) {
            linkType = "不予许可";
            eval.setVisibility(View.GONE);

        }else if (linkType.equals("B3")) {
            linkType = "补齐补正";
            eval.setVisibility(View.GONE);

        }else if (linkType.equals("C4")) {
            linkType = "打回";
            eval.setVisibility(View.GONE);

        }else if (linkType.equals("C3")) {
            linkType = "退回";
            eval.setVisibility(View.GONE);

        }else if (linkType.equals("A22")) {
            linkType = "法规复核";
            eval.setVisibility(View.GONE);

        }else if (linkType.equals("A31")) {
            linkType = "审定";
            eval.setVisibility(View.GONE);
        }else if (linkType.equals("A21")) {
            linkType = "业务复核";
            eval.setVisibility(View.GONE);
        }else {
            linkType = "待审";
            eval.setVisibility(View.GONE);
        }

        status.setText(linkType);
        department.setText(bean.getOrgname());
        itemname.setText(bean.getThing());
//        department.setText(bean.getOrgname());
        if (!bean.getStartTime().equals("null")){
            String []d = bean.getStartTime().split(" ");
            date.setText(d[0]);
        }else {
            date.setText("无");
        }
//        status.setText(bean.getStatus());
        eval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean.getStatus().equals("B1")){
                    //暂存状态下修改在线办理事项
                    Intent intent = new Intent(context, OnlineWebUpdateActivity.class);
                    intent.putExtra("itemid",bean.getCode());
                    intent.putExtra("itemName",bean.getThing());
                    intent.putExtra("flowsID",bean.getId());
                    context.startActivity(intent);
                }else {
                    Intent intent = new Intent(context, GoEvaluateActivity.class);
                    intent.putExtra("dothing", (Parcelable) bean);
                    context.startActivity(intent);
                }
            }
        });
    }

    private CommonPopupWindow popupWindow;

    private void showAlertDialogPop(View v, final DoThingBean bean){
        WindowManager manager = ((Activity)context).getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;

        View upView = LayoutInflater.from(context).inflate(R.layout.alertdialog_pop, null);
        TextView cancel = upView.findViewById(R.id.cancel);
        TextView submit = upView.findViewById(R.id.submit);
        LinearLayout lin = upView.findViewById(R.id.Lin);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(bean);
                popupWindow.dismiss();
            }
        });

        ViewGroup.LayoutParams params = lin.getLayoutParams();
        params.width = width-200;
        params.height = width-500;

        popupWindow = new CommonPopupWindow.Builder(context)
                .setView(upView)
                .setAnimationStyle(R.anim.push_right_in)
                .setBackGroundLevel(0.3f)
                .setWidthAndHeight(width-200, width-500)
                .setOutsideTouchable(true)
                .create();

        popupWindow.showAtLocation(v, Gravity.CENTER,0,0);
    }

    private Dialog dialog = null;
    private void updateData(final DoThingBean bean){
        String userid = (String) SharedPreferencesUtils.getParam(context, Constant.USERID,"");

        dialog = ((MyDoThingActivity)context).showLoading();
        Map map1 = new HashMap();
        map1.put("itemId",bean.getCode());
        map1.put("flowId",bean.getId());
        map1.put("userId",userid);
        LogUtils.i(map1.toString()+"------");
        MyOkHttpClient.getNetClient().postCallNet(MyFacesUrl.permitSave, map1, new MyCallBack() {
            @Override
            public void onSuccess(String json) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        Toast.makeText(context,"修改成功",Toast.LENGTH_SHORT).show();
                        bean.setStatus("B2");
                        notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onFailure(int code) {
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        Toast.makeText(context,"修改失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
