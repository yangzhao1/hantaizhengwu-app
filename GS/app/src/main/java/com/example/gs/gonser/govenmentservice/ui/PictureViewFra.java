package com.example.gs.gonser.govenmentservice.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.adapter.GalleryAdapter;
import com.example.gs.gonser.govenmentservice.bean.HelpTopicImageBean;
import com.example.gs.gonser.govenmentservice.view.MyImageView;
import com.example.gs.gonser.govenmentservice.view.PicGallery;

import java.util.ArrayList;

/**
 * Created by yangzhao on 2018/6/19.
 */

public class PictureViewFra extends Activity {

    private PicGallery gallery;
    // private ViewGroup tweetLayout; // 弹层
    private boolean mTweetShow = false; // 弹层是否显示

    // 屏幕宽度
    public static int screenWidth;
    // 屏幕高度
    public static int screenHeight;

    private GalleryAdapter mAdapter;

    private ArrayList<HelpTopicImageBean> helpTopicImage = new ArrayList<HelpTopicImageBean>();
    private int position = 0;
    // private ProgressDialog mProgress;

    private TextView tv_img_count,tv_topic_title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_view);

        getIntentData();

        tv_img_count = (TextView)findViewById(R.id.tv_img_count);
        tv_topic_title = (TextView)findViewById(R.id.tv_topic_title);

        gallery = (PicGallery) findViewById(R.id.pic_gallery);
        gallery.setVerticalFadingEdgeEnabled(false);// 取消竖直渐变边框
        gallery.setHorizontalFadingEdgeEnabled(false);// 取消水平渐变边框
        gallery.setDetector(new GestureDetector(this,
                new MySimpleGesture()));
        mAdapter = new GalleryAdapter(this,helpTopicImage,position);
        gallery.setAdapter(mAdapter);

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                finish();
                overridePendingTransition(0,R.anim.anim_out);
            }
        });

        mAdapter.getPositionListener(new GalleryAdapter.GalleryPositionListener() {
            @Override
            public void movePosition(int index) {
                Log.d("helpTopicImage--> ", " " + index);
                tv_img_count.setText((index+1)+"/"+helpTopicImage.size());
                tv_topic_title.setText(helpTopicImage.get(index).getUrl());
            }
        });

//        mAdapter.setData(dataResult);
        initViews();
        // mProgress = ProgressDialog.show(getActivity(),
        // null,getActivity().getString(R.string.loading));
    }

    private void getIntentData() {
        Intent intent = getIntent();
        helpTopicImage = intent.getParcelableArrayListExtra("helpTopicImage");
        position = intent.getIntExtra("position", 0);
    }

    private void initViews() {

        screenWidth = getWindow().getWindowManager().getDefaultDisplay()
                .getWidth();
        screenHeight = getWindow().getWindowManager().getDefaultDisplay()
                .getHeight();

    }

    private class MySimpleGesture extends GestureDetector.SimpleOnGestureListener {
        // 按两下的第二下Touch down时触发
        public boolean onDoubleTap(MotionEvent e) {

            View view = gallery.getSelectedView();
            if (view instanceof MyImageView) {
                MyImageView imageView = (MyImageView) view;
                if (imageView.getScale() > imageView.getMiniZoom()) {
                    imageView.zoomTo(imageView.getMiniZoom());
                } else {
                    imageView.zoomTo(imageView.getMaxZoom());
                }

            } else {

            }
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            // Logger.LOG("onSingleTapConfirmed",
            // "onSingleTapConfirmed excute");
            // mTweetShow = !mTweetShow;
            // tweetLayout.setVisibility(mTweetShow ? View.VISIBLE
            // : View.INVISIBLE);
            return true;
        }
    }

}
