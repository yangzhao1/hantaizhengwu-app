package com.example.gs.gonser.govenmentservice.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.adapter.HomeNotificateAdapter;
import com.example.gs.gonser.govenmentservice.base.BaseFragment;
import com.example.gs.gonser.govenmentservice.bean.Constant;
import com.example.gs.gonser.govenmentservice.bean.HelpTopicImageBean;
import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.bean.NoticeNewsBean;
import com.example.gs.gonser.govenmentservice.bean.PolicyReadBean;
import com.example.gs.gonser.govenmentservice.controller.HomeFragController;
import com.example.gs.gonser.govenmentservice.interfaces.IHomeFragRefreshUI;
import com.example.gs.gonser.govenmentservice.ui.ArticleInfoActivity;
import com.example.gs.gonser.govenmentservice.ui.ConvenAllActivity;
import com.example.gs.gonser.govenmentservice.ui.DoThingShowActivity;
import com.example.gs.gonser.govenmentservice.ui.GoConsultActivity;
import com.example.gs.gonser.govenmentservice.ui.GuideActivity;
import com.example.gs.gonser.govenmentservice.ui.LoginActivity;
import com.example.gs.gonser.govenmentservice.ui.PictureView;
import com.example.gs.gonser.govenmentservice.ui.PolicyReadingActivity;
import com.example.gs.gonser.govenmentservice.ui.WebActivity;
import com.example.gs.gonser.govenmentservice.utils.LogUtils;
import com.example.gs.gonser.govenmentservice.utils.SharedPreferencesUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 杨钊、 on 2018/5/5.
 * 首页
 */

public class HomeFragment extends BaseFragment implements OnBannerListener,IHomeFragRefreshUI {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.moreconven)
    TextView moreconven;
    @BindView(R.id.express)
    LinearLayout express;
    @BindView(R.id.livingpay)
    LinearLayout livingpay;
    @BindView(R.id.violation)
    LinearLayout violation;
    @BindView(R.id.trick)
    LinearLayout trick;
    @BindView(R.id.nav)
    LinearLayout nav;
    @BindView(R.id.dothingshow)
    LinearLayout dothingshow;
    @BindView(R.id.zhengce)
    LinearLayout zhengce;
    @BindView(R.id.guildthing)
    LinearLayout guildthing;
    @BindView(R.id.centershow)
    LinearLayout centershow;
    @BindView(R.id.zixun)
    LinearLayout zixun;
    @BindView(R.id.tousu)
    LinearLayout tousu;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.morenews)
    TextView morenews;


    private View mview;
    private ArrayList<Integer> list_path;
    //    private ArrayList<Integer> list_path;
    private LinearLayout cervixdisease, drugusage, diseasemanage, hosinn, medtech;
    private HomeNotificateAdapter adapter;
    private HomeFragController controller;
    private List<PolicyReadBean> listNotice = new ArrayList<>();

    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container) {
        if (mview == null) {
            mview = inflater.inflate(R.layout.fg_home, container, false);
        }
        ViewGroup parent = (ViewGroup) mview.getParent();
        if (parent != null) {
            parent.removeView(mview);
        }
        return mview;
    }

    @Override
    protected void initView() {
        controller = new HomeFragController(this,this);

        WindowManager manager = getActivity().getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;

        ViewGroup.LayoutParams layout = banner.getLayoutParams();
        layout.width = width;
        layout.height = (width * 2 / 5) + getStatusBarHeight();
        banner.setLayoutParams(layout);
    }

    @Override
    protected void initData() {
        getHomeImagePath();
        initBannerData();
//        initNoticeData();
        controller.getNoticeLimit(MyFacesUrl.noticeLimitList);
    }

    @Override
    protected void initEvent() {
        banner.setFocusable(true);
        banner.setFocusableInTouchMode(true);
        banner.requestFocus();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (adapter!=null){
            adapter.stopScroll();
        }
    }

    @Override
    public void onResume() {
        if (adapter!=null){
            adapter.stopScroll();
        }
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if (adapter!=null){
            adapter.stopScroll();
        }
        super.onDestroy();
    }

    private void initNoticeData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recycleview.setLayoutManager(linearLayoutManager);

        adapter = new HomeNotificateAdapter(getContext(), listNotice);
        recycleview.setAdapter(adapter);

        adapter.startScroll(recycleview);
        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),ArticleInfoActivity.class);
                intent.putExtra("obj",listNotice.get(position));
                startActivity(intent);
            }
        });
    }

    /**
     * 获取轮播图片
     */
    private void getHomeImagePath() {
        list_path = new ArrayList<>();
        list_path.add(R.mipmap.banner_1);
//        list_path.add(R.mipmap.banner_2);
        list_path.add(R.mipmap.banner_3);
        list_path.add(R.mipmap.banner_4);
    }

    /**
     * 加载轮播图片
     */
    private void initBannerData() {
        //放图片地址的集合

//        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
//        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
//        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
//        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");

        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Stack);
//        //设置轮播图的标题集合
//        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(5000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);

        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();
    }

    //轮播图的监听方法
    @Override
    public void OnBannerClick(int position) {
        Log.i("tag", "你点了第" + position + "张轮播图");
    }

    @OnClick({R.id.dothingshow, R.id.zhengce, R.id.guildthing, R.id.centershow, R.id.zixun, R.id.tousu, R.id.morenews, R.id.moreconven, R.id.express, R.id.livingpay,R.id.nav, R.id.violation, R.id.trick})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.dothingshow://办件公示
                startActivity(new Intent(getContext(), DoThingShowActivity.class));
                break;
            case R.id.zhengce://政策解读
                intent = new Intent(getContext(), PolicyReadingActivity.class);
                intent.putExtra("titleID","1");
                startActivity(intent);
                break;
            case R.id.guildthing://办事指南
                HelpTopicImageBean bean = new HelpTopicImageBean();
                bean.setUrl("");
                List<HelpTopicImageBean> list = new ArrayList<>();
                list.add(bean);
                intent = new Intent(getContext(), PictureView.class);
                intent.putParcelableArrayListExtra("helpTopicImage", (ArrayList<? extends Parcelable>) list);
                intent.putExtra("gu", "办事指南");
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_in,0);
//                startActivity(new Intent(getContext(), GuideActivity.class));
                break;
            case R.id.centershow://中心简介
                startActivity(new Intent(getContext(), GuideActivity.class));
                break;
            case R.id.zixun://我要咨询
                String userid = (String) SharedPreferencesUtils.getParam(getContext(), Constant.USERID,"");
                if (!TextUtils.isEmpty(userid)){
                    intent = new Intent(getContext(), GoConsultActivity.class);
                    intent.putExtra("comp","0");
                    startActivity(intent);
                }else {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            case R.id.tousu://我要投诉
                String userid1 = (String) SharedPreferencesUtils.getParam(getContext(), Constant.USERID,"");
                if (!TextUtils.isEmpty(userid1)){
                    intent = new Intent(getContext(), GoConsultActivity.class);
                    intent.putExtra("comp","1");
                    startActivity(intent);
                }else {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }
                break;
            case R.id.morenews://通知公告更多
                intent = new Intent(getContext(), PolicyReadingActivity.class);
                intent.putExtra("titleID","1");
                startActivity(intent);
                break;
            case R.id.moreconven://便民更多
                startActivity(new Intent(getContext(), ConvenAllActivity.class));
                break;
            case R.id.express://快递查询
                intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("title", "快递查询");
                intent.putExtra("code", 9);
                startActivity(intent);
                break;
            case R.id.livingpay://生活缴费
                intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("title", "生活缴费");
                intent.putExtra("code", 10);
                startActivity(intent);
                break;
            case R.id.violation://违章查询
                intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("title", "违章查询");
                intent.putExtra("code", 11);
                startActivity(intent);
                break;
            case R.id.trick://机票预订
                intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("title", "机票预订");
                intent.putExtra("code", 4);
                startActivity(intent);
                break;
            case R.id.nav://地图导航
                intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("title", "地图导航");
                intent.putExtra("code", 6);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void noticeSuccess(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray data = jsonObject.getJSONArray("data");
            String code = jsonObject.getString("code");
            if (code.equals("200")){
                PolicyReadBean bean;
                for (int i = 0; i < data.length(); i++) {
                    bean = new PolicyReadBean();
                    bean.setId(data.getJSONObject(i).getString("id"));
                    bean.setTitle(data.getJSONObject(i).getString("infoTitle"));
                    bean.setContent(data.getJSONObject(i).getString("infoContent"));
                    bean.setUrl(data.getJSONObject(i).getString("infoFaceImg"));

                    String date = data.getJSONObject(i).getString("infoCreateDate");
                    if (!date.equals("null")){
                        String dates [] = date.split(" ");
                        bean.setDate(dates[0]);
                    }else {
                        bean.setDate("2018-04-12");
                    }

                    listNotice.add(bean);
                }
                initNoticeData();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void noticeFail(int code) {
        LogUtils.i("通知公告服务器错误");
    }

    @Override
    public void getImageSuccess(String json) {

    }

    @Override
    public void getImageFail(int code) {

    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).placeholder(R.mipmap.banner_default).into(imageView);
        }
    }

}
