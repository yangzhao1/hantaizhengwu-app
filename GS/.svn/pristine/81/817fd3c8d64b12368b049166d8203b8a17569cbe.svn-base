package com.example.gs.gonser.govenmentservice;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gs.gonser.govenmentservice.fragment.ThingListFragment;
import com.example.gs.gonser.govenmentservice.fragment.HomeFragment;
import com.example.gs.gonser.govenmentservice.fragment.MineFragment;
import com.example.gs.gonser.govenmentservice.fragment.OfficeHallFragment;
import com.example.gs.gonser.govenmentservice.utils.LogUtils;
import com.example.gs.gonser.govenmentservice.view.NoScrollViewPager;
import com.lcodecore.tkrefreshlayout.utils.LogUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(R.id.view_pager)
    NoScrollViewPager mViewPager;
    @BindView(android.R.id.tabs)
    TabWidget tabs;
    @BindView(android.R.id.tabhost)
    com.example.gs.gonser.govenmentservice.view.FragmentTabHost mTabHost;

    private List<Fragment> mFragmentList;
    public static MainActivity activity;
    private long exitTime = 0;
    private ArrayList<Fragment> mClass = new ArrayList<>();
    private ArrayList<String> txtClass = new ArrayList<>();
    private ArrayList<Integer> imgClass = new ArrayList<>();

    private static final String[] PERMISSIONS_CONTACT = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE} ;

    private static final int REQUEST_CONTACTS = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBuild();
        setContentView(R.layout.activity_main);
        setInnerLayoutFullScreen(this);
        ButterKnife.bind(this);

        init();
    }

    private void setBuild(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            try {
                Class decorViewClazz = Class.forName("com.android.internal.policy.DecorView");
                Field field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor");
                field.setAccessible(true);
                field.setInt(getWindow().getDecorView(), Color.TRANSPARENT);  //改为透明
            } catch (Exception e) {}
        }
    }

    //首页
    public static void setInnerLayoutFullScreen(Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    private void init() {
        HomeFragment homeFragment = new HomeFragment();
        mClass.add(homeFragment);
        OfficeHallFragment officeHallFragment = new OfficeHallFragment();
        mClass.add(officeHallFragment);
        ThingListFragment thingListFragment = new ThingListFragment();
        mClass.add(thingListFragment);
        MineFragment mineFragment = new MineFragment();
        mClass.add(mineFragment);

        txtClass.add("首页");
        txtClass.add("办事大厅");
        txtClass.add("事项清单");
        txtClass.add("我的");
        imgClass.add(R.drawable.tab_home_bg);
        imgClass.add(R.drawable.tab_hall_bg);
        imgClass.add(R.drawable.tab_list_bg);
        imgClass.add(R.drawable.tab_mine_bg);
        initPtView();
        initPtEvent();
    }

    private void initPtView() {
        mViewPager.setIsCanScroll(false);
        mViewPager.setOffscreenPageLimit(2);
        mFragmentList = new ArrayList<>();
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i < mClass.size(); i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(txtClass.get(i)).setIndicator(getPtTabView(i));
            mTabHost.addTab(tabSpec, mClass.get(i).getClass(), null);
            mFragmentList.add(mClass.get(i));
            mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
        }

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });
    }

    private void initPtEvent() {
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                mViewPager.setCurrentItem(mTabHost.getCurrentTab(), false);
            }
        });

        //sdk大于23的。动态权限自动获取
        if (Build.VERSION.SDK_INT >= 23) {
            showContacts();
        }
    }

    private void showContacts()
    {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestSetPermissions();
        }else
        {
//            mLocClient.start();
        }
    }
    private void requestSetPermissions() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE))
        {
//            Snackbar.make(view, "permission_contacts_rationale",
//                    Snackbar.LENGTH_INDEFINITE)
//                    .setAction("ok", new View.OnClickListener() {
//                        @Override
//                        publics void onClick(View view) {
            ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
//                        }
//                    })
//                    .show();
        }else
        {
            ActivityCompat.requestPermissions(this, PERMISSIONS_CONTACT, REQUEST_CONTACTS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if (requestCode==REQUEST_CONTACTS){
//            if (PermissionUtil.verifyPermissions(grantResults)) {
//                mLocClient.start();
//            } else {
//                Toast.makeText(getApplicationContext(),"授权不通过",Toast.LENGTH_SHORT).show();
//            }
        }else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private View getPtTabView(int index) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);

        ImageView image = (ImageView) view.findViewById(R.id.image);
        TextView title = (TextView) view.findViewById(R.id.title);

        image.setImageResource(imgClass.get(index));
        title.setText(txtClass.get(index));

        return view;
    }
}
