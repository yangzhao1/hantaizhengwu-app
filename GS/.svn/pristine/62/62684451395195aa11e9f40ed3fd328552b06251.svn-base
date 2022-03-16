package com.example.gs.gonser.govenmentservice.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.adapter.FragmentAdapter;
import com.example.gs.gonser.govenmentservice.fragment.RegisterLegalFrag;
import com.example.gs.gonser.govenmentservice.fragment.RegisterPersonFrag;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangzhao on 2018/5/10.
 * 注册
 */

public class RegisterActivity extends FragmentActivity {

    @BindView(R.id.titletool)
    TextView titletool;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.person)
    TextView person;
    @BindView(R.id.legal)
    TextView legal;

    private ArrayList<Fragment> mClass = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        RegisterPersonFrag registerPersonFrag = new RegisterPersonFrag();
        mClass.add(registerPersonFrag);
        RegisterLegalFrag registerLegalFrag = new RegisterLegalFrag();
        mClass.add(registerLegalFrag);
        viewpager.setCurrentItem(0);
        viewpager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), mClass));
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position==0){
                    person.setBackgroundResource(R.color.theme_color);
                    legal.setBackgroundResource(R.color.white);
                    person.setTextColor(getResources().getColor(R.color.white));
                    legal.setTextColor(getResources().getColor(R.color.black));
                }else if (position==1){
                    legal.setBackgroundResource(R.color.theme_color);
                    person.setBackgroundResource(R.color.white);
                    legal.setTextColor(getResources().getColor(R.color.white));
                    person.setTextColor(getResources().getColor(R.color.black));
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.person, R.id.legal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.person:
//                person.setBackgroundResource(R.color.theme_color);
//                legal.setBackgroundResource(R.color.white);
//                person.setTextColor(getResources().getColor(R.color.white));
//                legal.setTextColor(getResources().getColor(R.color.black));
                viewpager.setCurrentItem(0);
                break;
            case R.id.legal:
//                legal.setBackgroundResource(R.color.theme_color);
//                person.setBackgroundResource(R.color.white);
//                legal.setTextColor(getResources().getColor(R.color.white));
//                person.setTextColor(getResources().getColor(R.color.black));
                viewpager.setCurrentItem(1);

                break;
        }
    }
}
