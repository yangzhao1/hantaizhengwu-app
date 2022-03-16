package com.example.gs.gonser.govenmentservice.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;


import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.adapter.FragmentAdapter;
import com.example.gs.gonser.govenmentservice.fragment.Guidepage1;
import com.example.gs.gonser.govenmentservice.fragment.Guidepage2;
import com.example.gs.gonser.govenmentservice.fragment.Guidepage3;
import com.example.gs.gonser.govenmentservice.fragment.Guidepage4;
import com.example.gs.gonser.govenmentservice.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzhao on 2018/4/26.
 */

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<Fragment> listfragment = new ArrayList<Fragment>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInnerLayoutFullScreen();
        int count = (int) SharedPreferencesUtils.getParamCount(this,"count",0);
        int lay = 0;
        if (count!=0){
            startActivity(new Intent(this,WelcomeSActivity.class));
            finish();
        }else {
            SharedPreferencesUtils.setParamCount(this,"count",1);
            lay = R.layout.welcome;
            setContentView(lay);
            init();
        }
    }

    private void init(){
        viewPager = findViewById(R.id.viewpager);

        listfragment.add(new Guidepage1());
        listfragment.add(new Guidepage2());
//        listfragment.add(new Guidepage3());
        listfragment.add(new Guidepage4());

        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),listfragment));
    }
    //首页
    public void setInnerLayoutFullScreen() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}
