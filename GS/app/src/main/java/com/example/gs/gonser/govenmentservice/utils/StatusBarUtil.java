package com.example.gs.gonser.govenmentservice.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by yangzhao on 2018/5/9.
 */

public class StatusBarUtil {

    private boolean isTransparent;
    private View view = null;
    private static SystemOS systemOS = SystemOS.API00;
    private Activity activity;

    static {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {                                                                   //0.0 <= system < 4.4
            systemOS = SystemOS.API00;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {   //4.4 <= system < 5.0
            systemOS = SystemOS.API19;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {        //5.0 <= system < 6.0
            systemOS = SystemOS.API21;
        } else {                                                                                                                    //6.0 <= system
            systemOS = SystemOS.API23;
        }
    }

    public StatusBarUtil getImageLayout(Activity activity, int resId) {
        return getNewLayout(activity, resId, 0, true);
    }

    public StatusBarUtil getBaseLayout(Activity activity, int resId, int statusBarColor) {
        return getNewLayout(activity, resId, statusBarColor, false);
    }

    /**
     * 生成新界面
     *
     * @param resId          activity资源布局
     * @param statusBarColor 颜色
     * @param isTransparent  设置顶部是否为透明
     * @return
     */
    private StatusBarUtil getNewLayout(Activity activity, int resId, int statusBarColor, boolean isTransparent) {
        this.isTransparent = isTransparent;
        this.activity = activity;
        LogUtils.d("Current System version:" + Build.VERSION.SDK_INT);
        if (systemOS == SystemOS.API00) { //Api18以下 Android 4.3及其以下
            view = LayoutInflater.from(activity).inflate(resId, null);
            this.isTransparent = true;
            return this;
        }


        Window window = activity.getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        if (isTransparent) {
            view = LayoutInflater.from(activity).inflate(resId, null);
        } else {
            FrameLayout.LayoutParams baseParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(baseParams);

            View statusView = new View(activity);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.height = getStatusBarHeight(activity);
            statusView.setLayoutParams(layoutParams);
            LogUtils.d("Color:" + statusBarColor);
            try {
                statusView.setBackgroundColor(statusBarColor);
            } catch (Exception e) {
                int defaultColor = Color.RED;
                statusView.setBackgroundColor(defaultColor);
            }

            linearLayout.addView(statusView);
            LayoutInflater.from(activity).inflate(resId, linearLayout, true);
//            linearLayout.addView(layout);
            view = linearLayout;
        }
        return this;
    }

    public View buildLayout() {
        return buildLayout(true);
    }

    /**
     * 创建layoutView
     *
     * @param isShowBarBackColor 清除statusBar背景色，设置是否显示半透明背景颜色
     */
    public View buildLayout(boolean isShowBarBackColor) {
        if (systemOS == SystemOS.API00 || systemOS == SystemOS.API19) {

        } else if (systemOS == SystemOS.API21 || systemOS == SystemOS.API23) { //5.0 6.0
            if (isShowBarBackColor) {
                if (systemOS == SystemOS.API23) {
                    view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                } else {
                    view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
                }
            } else {
                if (systemOS == SystemOS.API23) {
                    view.setSystemUiVisibility(View.INVISIBLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                } else {
                    view.setSystemUiVisibility(View.INVISIBLE);
                }

            }
        }
        return view;
    }


    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private enum SystemOS {
        API00("Android 1.1以上"),
        API19("Android 4.4以上"),
        API21("Android 5.0以上"),
        API23("Android 6.0以上");
        String desc;

        SystemOS(String desc) {
            this.desc = desc;
        }
    }
}
