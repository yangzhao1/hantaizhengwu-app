package com.example.gs.gonser.govenmentservice.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.adapter.MyFragmentAdapter;
import com.example.gs.gonser.govenmentservice.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by yangzhao on 2018/5/5.
 * 办事大厅
 */

public class OfficeHallFragment extends BaseFragment {
    @BindView(R.id.titletool)
    TextView titletool;
    @BindView(R.id.tablayout)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.rel)
    RelativeLayout rel;
    private MyFragmentAdapter adapterfrag;
    //    private List<String> tabs = new ArrayList();
    private String tabs[] = {"个人办事", "法人办事"};
    private List<Fragment> listfrag = new ArrayList<>();
    private String titleText = "办事大厅";

    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container) {
        if (mview == null) {
            mview = inflater.inflate(R.layout.fg_hall, container, false);
        }
        ViewGroup parent = (ViewGroup) mview.getParent();
        if (parent != null) {
            parent.removeView(mview);
        }
        return mview;
    }

    @Override
    protected void initView() {
        rel.setPadding(0,getStatusBarHeight(),0,0);

        titletool.setText(titleText);
    }

    @Override
    protected void initData() {
        listfrag.clear();
        listfrag.add(new HallPersonalService());
        listfrag.add(new HallLegService());
        adapterfrag = new MyFragmentAdapter(getChildFragmentManager(), listfrag, tabs);
        viewPager.setAdapter(adapterfrag);

        initTabLayout();
    }

    private void initTabLayout() {

        //MODE_FIXED标签栏不可滑动，各个标签会平分屏幕的宽度
        tab.setTabMode(TabLayout.MODE_FIXED);
        //指示条的颜色
        tab.setSelectedTabIndicatorColor(getResources().getColor(R.color.theme_color));
        tab.setSelectedTabIndicatorHeight((int) getResources().getDimension(R.dimen.indicatorHeight));
//        setIndicator(tab,20,20);
        //关联tabLayout和ViewPager,两者的选择和滑动状态会相互影响
        tab.setupWithViewPager(viewPager);
    }
    @Override
    protected void initEvent() {
    }

}
