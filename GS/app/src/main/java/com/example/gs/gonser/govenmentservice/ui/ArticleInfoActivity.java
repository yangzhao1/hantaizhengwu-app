package com.example.gs.gonser.govenmentservice.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.bean.PolicyReadBean;
import com.example.gs.gonser.govenmentservice.controller.BeInCommController;
import com.example.gs.gonser.govenmentservice.interfaces.IRefreshUI;
import com.example.gs.gonser.govenmentservice.interfaces.MyCallBack;
import com.example.gs.gonser.govenmentservice.utils.MyOkHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangzhao on 2018/5/19.
 * 文章详情
 */

public class ArticleInfoActivity extends BaseActivity implements IRefreshUI {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.titletool)
    TextView titletool;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.content)
    TextView content;

    private String titleText = "文章详情";
    private PolicyReadBean bean;
    private String id = "";
    private BeInCommController controller;

    @Override
    protected int getLayout() {
        return R.layout.article_main;
    }
    //这里面的resource就是fromhtml函数的第一个参数里面的含有的url
    Html.ImageGetter imgGetter = new Html.ImageGetter() {

        public Drawable getDrawable(String source) {
            Log.i("RG", "source---?>>>" + source);
            Drawable drawable = null;
            URL url;
            try {
                url = new URL(source);
                Log.i("RG", "url---?>>>" + url);
                drawable = Drawable.createFromStream(url.openStream(), ""); // 获取网路图片
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            WindowManager manager = getWindowManager();
            DisplayMetrics outMetrics = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(outMetrics);
            int width = outMetrics.widthPixels;
            int height = width*2/3;
            Log.i("RG", "url---?>>" + url);
            int w = width/drawable.getIntrinsicWidth();
            if (drawable!=null){
                drawable.setBounds(0, 0,width, drawable.getIntrinsicHeight()*w);
                return drawable;
            }else {
                return null;
            }
        }
    };

    final Html.ImageGetter imageGetter = new Html.ImageGetter() {

        public Drawable getDrawable(String source) {
            Drawable drawable=null;
            URL url;
            try {
                url = new URL(source);
                drawable = Drawable.createFromStream(url.openStream(), "");
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            return drawable;}
    };

        @Override
        protected void initView() {
            controller = new BeInCommController(this, this);
            bean = getIntent().getParcelableExtra("obj");
            title.setText(bean.getTitle());
            id = bean.getId();

            if (!bean.getDate().equals("null")){
                date.setText(bean.getDate());
            }
            titletool.setText(titleText);
        }

        @Override
        protected void initData() {
            Map map = new HashMap();
            map.put("id", id);
//            controller.postData(MyFacesUrl.viewinfo, map);
            MyOkHttpClient.getNetClient().postCallNet(MyFacesUrl.viewinfo, map, new MyCallBack() {
                @Override
                public void onSuccess(String json) {
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        final JSONObject data = jsonObject.getJSONObject("data");
                        String code = jsonObject.getString("code");
                        if (code.equals("200")) {
                            final Spanned html;
                            html = Html.fromHtml(data.getString("infoContent").toString(),imgGetter, null);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    content.setText(html);
                                }
                            });
                        }else {
                            showToast("服务器异常");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int code) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            content.setText("暂无数据");
                        }
                    });
                }
            });

        }

        @Override
        protected void initEvent() {

        }

        @Override
        public void success(String message, String json) {
            try {
                JSONObject jsonObject = new JSONObject(json);
                final JSONObject data = jsonObject.getJSONObject("data");
                String code = jsonObject.getString("code");
                if (code.equals("200")) {
                    final Spanned html;
                    html = Html.fromHtml(data.getString("infoContent").toString(),imgGetter, null);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            content.setText(html);
                        }
                    });
                }else {
                    showToast("服务器异常");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void fails(String message, int code) {

        }

        @OnClick(R.id.back)
        public void onViewClicked() {
            finish();
        }
    }
