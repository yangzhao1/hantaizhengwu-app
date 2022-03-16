package com.example.gs.gonser.govenmentservice.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.adapter.MyCollectAdapter;
import com.example.gs.gonser.govenmentservice.adapter.MyComplaintsAdapter;
import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.bean.Constant;
import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.bean.ThingListBean;
import com.example.gs.gonser.govenmentservice.controller.BeInCommController;
import com.example.gs.gonser.govenmentservice.interfaces.IRefreshUI;
import com.example.gs.gonser.govenmentservice.utils.SharedPreferencesUtils;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangzhao on 2018/5/11.
 * 我的咨询/投诉
 */

public class MyComplaintsActivity extends BaseActivity implements IRefreshUI {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.titletool)
    TextView titletool;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.refresh)
    TwinklingRefreshLayout refresh;
    @BindView(R.id.nodata)
    LinearLayout nodata;
    @BindView(R.id.searchConetnt)
    EditText searchConetnt;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.searchs)
    TextView searchs;
    @BindView(R.id.searchLin)
    LinearLayout searchLin;

    private String titleText = "我的咨询";
    private BeInCommController controller;
    private String userid = "";
    private List<ThingListBean> list = new ArrayList<>();
    private MyComplaintsAdapter adapter;

    private int pageIndex = 1;//默认一页显示20条，由后台那边控制
    private int pageSize = 20;//默认一页显示20条，由后台那边控制
    private String complaints = "flow"; //flow表示投诉，item表示咨询
    private String search_con = "";

    @Override
    protected int getLayout() {
        return R.layout.toolbar_and_recycler;
    }

    @Override
    protected void initView() {
        controller = new BeInCommController(this,this);
        userid = (String) SharedPreferencesUtils.getParam(this, Constant.USERID,"");
        String flag= getIntent().getStringExtra("comp");

        if (flag.equals("0")){
            complaints = "item";
            titletool.setText(titleText);
        }else {
            complaints = "flow";
            titletool.setText("我的投诉");
        }
    }

    @Override
    protected void initData() {
        Map map = new HashMap();
        map.put("userId",userid);
        map.put("complaint",complaints);
        map.put("pageNumber",pageIndex+"");
        map.put("itemName", "" + search_con);

        controller.postData(MyFacesUrl.business_ask,map);
    }

    @Override
    protected void initEvent() {
        refresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                list.clear();
                pageIndex=1;
                initData();
                //恢复加载更多
                refresh.setEnableLoadmore(true);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                pageIndex++;
                initData();
            }
        });
    }

    private void initItemData(){
        if (pageIndex == 1) {
            if (list.size() == 0) {
                nodata.setVisibility(View.VISIBLE);
            }else {
                nodata.setVisibility(View.GONE);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                recycleview.setLayoutManager(linearLayoutManager);
                adapter = new MyComplaintsAdapter(this, list);
                recycleview.setAdapter(adapter);

                adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Intent intent = new Intent(MyCollectionActivity.this,ItemInfoActivity.class);
//                        intent.putExtra("id",list.get(position).getId());
//                        startActivity(intent);
                    }
                });
            }
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void success(String message, String json) {
        refresh.finishLoadmore();
        refresh.finishRefreshing();
//        showToast("请求失败");

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray data = jsonObject.getJSONArray("data");
            String code = jsonObject.getString("code");
            if (code.equals("200")) {
                ThingListBean thingListBean;
                for (int i = 0; i < data.length(); i++) {
                    thingListBean = new ThingListBean();
                    thingListBean.setId(data.getJSONObject(i).getString("id"));
                    thingListBean.setTitle(data.getJSONObject(i).getString("title"));
                    thingListBean.setDate(data.getJSONObject(i).getString("createdate"));
                    thingListBean.setInfo(data.getJSONObject(i).getString("content"));
                    thingListBean.setUserid(data.getJSONObject(i).getString("itemname"));
                    thingListBean.setDafu(data.getJSONObject(i).getString("handlingtstatus"));
                    list.add(thingListBean);
                }

                if (data.length() == 0 || data.length() < pageSize) {
                    //禁止加载更多
                    refresh.setEnableLoadmore(false);
                }
                initItemData();
            }else {
                showToast("服务器异常");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fails(String message, int code) {
        refresh.finishLoadmore();
        refresh.finishRefreshing();
        showToast("服务器异常");
    }

    @OnClick({R.id.back, R.id.search,R.id.searchs})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.searchs:
                search_con = searchConetnt.getText().toString();
                list.clear();
                pageIndex = 1;
                initData();
                break;
            case R.id.search:
                if (searchLin.getVisibility()==View.GONE){
                    searchLin.setVisibility(View.VISIBLE);
                    search.setText("取消");
                }else {
                    searchLin.setVisibility(View.GONE);
                    search.setText("搜索");
                }
                break;
        }
    }
}
