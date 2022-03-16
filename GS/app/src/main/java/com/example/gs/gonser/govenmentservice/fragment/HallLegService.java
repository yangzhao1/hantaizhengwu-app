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
 * Created by yangzhao on 2017/9/13.
 * 大厅--法人办事
 */

public class HallLegService extends BaseFragment implements IRefreshUI {

    private View view;
    private GridView gridView;
    private List<ThemeBean> list;
    private static int [] images = {R.mipmap.affor,R.mipmap.alter,R.mipmap.aptitude,R.mipmap.arch,
            R.mipmap.career,R.mipmap.clima,R.mipmap.commerce,R.mipmap.customs,
            R.mipmap.educat,R.mipmap.fina,R.mipmap.hygiene,R.mipmap.innovate,
            R.mipmap.inspect,R.mipmap.invest,R.mipmap.judicial,R.mipmap.konw,
            R.mipmap.labour,R.mipmap.land,R.mipmap.legal,
            R.mipmap.permit,R.mipmap.pledge,R.mipmap.police,R.mipmap.power,
            R.mipmap.qualit,R.mipmap.revenue,R.mipmap.scene,
            R.mipmap.service,R.mipmap.tender,R.mipmap.test,R.mipmap.traffic,R.mipmap.tree
            ,R.mipmap.volk,R.mipmap.invest,R.mipmap.labour,R.mipmap.educat,R.mipmap.aptitude,
            R.mipmap.culture,R.mipmap.death,R.mipmap.door,
            R.mipmap.education,R.mipmap.exit,R.mipmap.found,R.mipmap.green,
            R.mipmap.health,R.mipmap.housing,R.mipmap.judicial,R.mipmap.marriage,
            R.mipmap.nation,R.mipmap.occupation};

    private BeInCommControllerFrag commControllerFrag;

    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.hallpersonalservice,null);

        return view;    }

    @Override
    protected void initView() {
        commControllerFrag = new BeInCommControllerFrag(this,this);
        gridView =  view.findViewById(R.id.gridview);
        list = new ArrayList<>();
//        commControllerFrag.postData(MyFacesUrl.legalTheme,null);
    }

    @Override
    protected void initData() {
        ThemeBean bean;
        bean = new ThemeBean();
        bean.setTypeName("设立变更");
        bean.setImageid(images[0]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("准营准办");
        bean.setImageid(images[1]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("资质认证");
        bean.setImageid(images[2]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("就业创业");
        bean.setImageid(images[3]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("年检年审");
        bean.setImageid(images[4]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("税收财务");
        bean.setImageid(images[5]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("人力资源");
        bean.setImageid(images[6]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("社会保障");
        bean.setImageid(images[7]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("投资审批");
        bean.setImageid(images[8]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("融资信贷");
        bean.setImageid(images[9]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("抵押质押");
        bean.setImageid(images[10]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("商务贸易");
        bean.setImageid(images[11]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("招标拍卖");
        bean.setImageid(images[12]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("海关口岸");
        bean.setImageid(images[13]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("涉外服务");
        bean.setImageid(images[14]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("农林牧渔");
        bean.setImageid(images[15]);
        list.add(bean);
        bean = new ThemeBean();
        bean.setTypeName("国土建设");
        bean.setImageid(images[16]);
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
