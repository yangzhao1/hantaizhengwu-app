package com.example.gs.gonser.govenmentservice.ui;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.interfaces.IRefreshUI;
import com.example.gs.gonser.govenmentservice.view.ImageControl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangzhao on 2018/5/19.
 * 办事指南
 */

public class GuideActivity extends BaseActivity implements IRefreshUI {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.titletool)
    TextView titletool;
    @BindView(R.id.content)
    ImageControl imgControl;

    private String titleText = "办事指南";

    @Override
    protected int getLayout() {
        return R.layout.guide_main;
    }

    @Override
    protected void initView() {
        titletool.setText(titleText);
    }

    @Override
    protected void initData() {

    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        init();
    }

    private void init() {
        // 这里可以为imgcontrol的图片路径动态赋值
        // ............

        Bitmap bmp;
        if (imgControl.getDrawingCache() != null) {
            bmp = Bitmap.createBitmap(imgControl.getDrawingCache());
        } else {
            bmp = ((BitmapDrawable) imgControl.getDrawable()).getBitmap();
        }
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int screenW = this.getWindowManager().getDefaultDisplay().getWidth();
        int screenH = this.getWindowManager().getDefaultDisplay().getHeight()
                - statusBarHeight;
        if (bmp != null) {
            imgControl.imageInit(bmp, screenW, screenH, statusBarHeight,
                    new ImageControl.ICustomMethod() {

                        @Override
                        public void customMethod(Boolean currentStatus) {
                            // 当图片处于放大或缩小状态时，控制标题是否显示
                            if (currentStatus) {
                                titletool.setVisibility(View.GONE);
                            } else {
                                titletool.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        } else {

        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                imgControl.mouseDown(event);
                break;

            /**
             * 非第一个点按下
             */
            case MotionEvent.ACTION_POINTER_DOWN:
                imgControl.mousePointDown(event);

                break;
            case MotionEvent.ACTION_MOVE:
                imgControl.mouseMove(event);

                break;

            case MotionEvent.ACTION_UP:
                imgControl.mouseUp();
                break;

        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void success(String message, String json) {

    }

    @Override
    public void fails(String message, int code) {

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

}
