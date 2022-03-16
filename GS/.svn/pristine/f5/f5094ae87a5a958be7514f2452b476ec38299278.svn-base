package com.example.gs.gonser.govenmentservice.adapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.baseadpater.AutoRVAdapter;
import com.example.gs.gonser.govenmentservice.baseadpater.ViewHolder;
import com.example.gs.gonser.govenmentservice.bean.NoticeNewsBean;
import com.example.gs.gonser.govenmentservice.bean.PolicyReadBean;
import com.example.gs.gonser.govenmentservice.utils.UniversalMethod;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/9/15.
 * 适配器，通知公告
 */

public class HomeNotificateAdapter extends AutoRVAdapter {
    public List<?> list;

    private Context context;
    public HomeNotificateAdapter(Context context, List<?> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.notice_items;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        PolicyReadBean bean = (PolicyReadBean) list.get(position);
        TextView no_title = holder.getTextView(R.id.no_title);
        LinearLayout news = holder.getLinearLayout(R.id.news);
        TextView no_content = holder.getTextView(R.id.no_content);
        ImageView no_image = holder.getImageView(R.id.no_image);

//        int heights = news.getLayoutParams().height;
        news.measure(0,0);
        height = news.getMeasuredHeight();

        scrollHeight = height;

        Log.i("测量linear的高度======",height+"");

        no_title.setText(bean.getTitle());
        no_content.setText(bean.getDate());
        String imagepath = bean.getUrl();

//        Glide.with(context)
//                .load(imagepath)
//                .placeholder(R.mipmap.no_pic)
//                .into(UniversalMethod.getViewTarget(no_image));
    }

    private Runnable runnable = null;
    private int startY1, endY1;
    private boolean isShow = true;
    private boolean flag = false;
    private int offsetY = 0;
    private int scrollHeight = 0;
    private int allScrollHeight = 10000;
    private Handler handler;
    private int height = 0;

    public void startScroll(final RecyclerView recyclerView){
        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                scrollHeight = height;
//                Log.i("线程开始泡了linear的高度======",height+"");

                startY1 = isShow ? endY1:offsetY;
                endY1 = isShow ? -offsetY:0;
                ObjectAnimator.ofFloat(recyclerView, "translationY", startY1, endY1).setDuration(2000).start();
//                Log.e("runnable","线程开始泡了    "+offsetY +" startY1="+startY1+"    endY1="+endY1);
                offsetY +=scrollHeight;

                if (offsetY>=allScrollHeight){
                    endY1=0;
                    offsetY=0;
                    startY1 = 0;
//                    Log.e("runnable","线程过钱了 1000+++++");
                }

                if (!flag){
                    if (list.size()>1&&height>0){
                        flag = true;
                        if (list.size()==2){
                            allScrollHeight=scrollHeight;
                        }else if (list.size()==3){
                            allScrollHeight=scrollHeight*2;
                        }else if (list.size()==4){
                            allScrollHeight=scrollHeight*3;
                        }else if (list.size()==5){
                            allScrollHeight=scrollHeight*4;
                        }else {
                            allScrollHeight=scrollHeight*5;
                        }
                    }
                }
                handler.postDelayed(runnable,6000);
            }
        };
        handler.postDelayed(runnable,3000);
    }

    public void stopScroll(){
        handler.removeCallbacks(runnable);
    }

    /**
     * 加载头像
     * @Title: setPic
     * @Description: TODO
     * @return void
     * @throws
     */
//    private void setPic(String picurl, final ImageView noimage) {
//        if (!"".equals(picurl)) {
//            BitmapUtils bitmapUtils = new BitmapUtils(context);
//            bitmapUtils.display(noimage,picurl);
//        }
//    }

}
