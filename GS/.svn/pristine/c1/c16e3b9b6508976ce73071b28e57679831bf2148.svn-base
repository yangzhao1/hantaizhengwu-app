package com.example.gs.gonser.govenmentservice.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.bean.HelpTopicImageBean;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangzhao on 2018/6/19.
 */

public class PictureView extends Activity {

    @BindView(R.id.pic)
    PhotoView pic;
    @BindView(R.id.lin)
    LinearLayout lin;
    private String gulide = "办事指南";
    private ArrayList<HelpTopicImageBean> helpTopicImage = new ArrayList<HelpTopicImageBean>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pictureview_main);
        ButterKnife.bind(this);
        getIntentData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        helpTopicImage = intent.getParcelableArrayListExtra("helpTopicImage");
        gulide = intent.getStringExtra("gu");
        if (gulide.equals("办事指南")){
            Glide.with(this).load(R.mipmap.flow).placeholder(R.mipmap.conn_defualt).into(pic);
        }else {
            Glide.with(this).load(helpTopicImage.get(0).getUrl()).placeholder(R.mipmap.conn_defualt).into(pic);
        }
    }

    @OnClick(R.id.pic)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.pic:
                finish();
                overridePendingTransition(0, R.anim.anim_out);
                break;
        }

    }


}
