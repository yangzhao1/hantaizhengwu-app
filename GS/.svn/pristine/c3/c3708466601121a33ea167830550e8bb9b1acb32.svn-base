package com.example.gs.gonser.govenmentservice.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.adapter.HallGridviewAdapter;
import com.example.gs.gonser.govenmentservice.base.BaseFragment;
import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.bean.NoticeNewsBean;
import com.example.gs.gonser.govenmentservice.bean.ThemeBean;
import com.example.gs.gonser.govenmentservice.controller.BeInCommControllerFrag;
import com.example.gs.gonser.govenmentservice.interfaces.IRefreshUI;
import com.example.gs.gonser.govenmentservice.ui.ItemListActivity;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/13.
 * 大厅--个人服务
 */

public class HallPersonalService extends BaseFragment implements IRefreshUI{

    private View view;
    private GridView gridView;
    private List<ThemeBean> list;
    private static int [] images = {R.mipmap.administ,R.mipmap.allow,R.mipmap.army,R.mipmap.birth,
                                    R.mipmap.build,R.mipmap.cause,R.mipmap.change,R.mipmap.collateral,
                                    R.mipmap.consume,R.mipmap.culture,R.mipmap.death,R.mipmap.door,
                                    R.mipmap.education,R.mipmap.exit,R.mipmap.found,R.mipmap.green,
                                    R.mipmap.health,R.mipmap.housing,R.mipmap.judicial,R.mipmap.marriage,
                                    R.mipmap.nation,R.mipmap.occupation,R.mipmap.other,R.mipmap.paper,
                                    R.mipmap.pensions,R.mipmap.publics,R.mipmap.quit,R.mipmap.right,
                                    R.mipmap.tour,R.mipmap.traffic,R.mipmap.world};

    private BeInCommControllerFrag commControllerFrag;
    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.hallpersonalservice,null);

        return view;    }

    @Override
    protected void initView() {
        commControllerFrag = new BeInCommControllerFrag(this,this);
        gridView = view.findViewById(R.id.gridview);
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        Map map = new HashMap();
//        commControllerFrag.postData(MyFacesUrl.personTheme,null);
        ThemeBean bean;
        bean = new ThemeBean();
        bean.setTypeName("生育收养");
        bean.setImageid(images[0]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("民族宗教");
        bean.setImageid(images[1]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("教育科研");
        bean.setImageid(images[2]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("就业创业");
        bean.setImageid(images[3]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("设立变更");
        bean.setImageid(images[4]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("主营准办");
        bean.setImageid(images[5]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("抵押质押");
        bean.setImageid(images[6]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("职业资格");
        bean.setImageid(images[7]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("行政缴费");
        bean.setImageid(images[8]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("婚姻登记");
        bean.setImageid(images[9]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("优待抚恤");
        bean.setImageid(images[10]);
        list.add(bean);
        getGridviewData();

    }

    @Override
    protected void initEvent() {

    }

    private void getGridviewData(){
        gridView.setAdapter(new HallGridviewAdapter(getContext(),list));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getContext(), ItemListActivity.class);
//                intent.putExtra("titleText",list.get(i).getTypeName().toString());
//                intent.putExtra("serviceTopic",list.get(i).getTypeCode());
                startActivity(intent);

            }
        });
    }

    @Override
    public void success(String message, String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray data = jsonObject.getJSONArray("data");
            String code = jsonObject.getString("code");
            if (code.equals("200")){
                ThemeBean bean;
                for (int i = 0; i < data.length(); i++) {
                    bean = new ThemeBean();
                    bean.setId(data.getJSONObject(i).getString("id"));
                    bean.setTypeCode(data.getJSONObject(i).getString("typeCode"));
                    bean.setTypeName(data.getJSONObject(i).getString("typeName"));
                    bean.setImageid(images[i]);
                    list.add(bean);
                }
                getGridviewData();
            }else {
                Toast.makeText(getContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fails(String message, int code) {
        Toast.makeText(getContext(),"服务器异常",Toast.LENGTH_SHORT).show();
    }
}
