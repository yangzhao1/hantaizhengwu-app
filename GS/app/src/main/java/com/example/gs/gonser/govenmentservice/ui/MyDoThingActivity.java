package com.example.gs.gonser.govenmentservice.ui;

import android.content.Intent;
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
import com.example.gs.gonser.govenmentservice.adapter.MyDoThingAdapter;
import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.bean.Constant;
import com.example.gs.gonser.govenmentservice.bean.DoThingBean;
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
import butterknife.OnClick;

/**
 * Created by yangzhao on 2018/5/11.
 * 我的办件
 */

public class MyDoThingActivity extends BaseActivity implements IRefreshUI {

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

    private String titleText = "我的办件";
    private BeInCommController controller;
    private String userid = "";
    private List<DoThingBean> list = new ArrayList<>();
    private MyDoThingAdapter adapter;

    private int pageIndex = 1;
    private int pageSize = 20;//默认一页显示20条，由后台那边控制
    private String search_con = "";

    @Override
    protected int getLayout() {
        return R.layout.itemlist_activity;
    }

    @Override
    protected void initView() {
        controller = new BeInCommController(this,this);
        titletool.setText(titleText);
        userid = (String) SharedPreferencesUtils.getParam(this, Constant.USERID,"");    }

    @Override
    protected void initData() {
        Map map = new HashMap();
        map.put("userId",userid);
        map.put("pageNumber",pageIndex+"");
        map.put("itemName", "" + search_con);

        controller.postData(MyFacesUrl.business_list,map);
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
        titletool.setFocusableInTouchMode(true);
        titletool.setFocusable(true);
        titletool.requestFocus();
    }

    private void initItemData(){
        if (pageIndex == 1) {
            if (list.size() == 0) {
                nodata.setVisibility(View.VISIBLE);
            }else {
                nodata.setVisibility(View.GONE);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                recycleview.setLayoutManager(linearLayoutManager);
                adapter = new MyDoThingAdapter(this, list);
                recycleview.setAdapter(adapter);

                adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MyDoThingActivity.this,DoThingInfoActivity.class);
                        intent.putExtra("id",list.get(position).getId());
                        intent.putExtra("userID",userid);
                        intent.putExtra("itemid",list.get(position).getCode());
                        startActivity(intent);
                    }
                });
            }
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        if (adapter!=null){
            list.clear();
            pageIndex=1;
            initData();
            //恢复加载更多
            refresh.setEnableLoadmore(true);
        }
        super.onResume();
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
                DoThingBean thingListBean;
                for (int i = 0; i < data.length(); i++) {
                    thingListBean = new DoThingBean();
                    thingListBean.setId(data.getJSONObject(i).getString("id"));
                    thingListBean.setThing(data.getJSONObject(i).getString("itemName"));
                    thingListBean.setOrgname(data.getJSONObject(i).getString("businessTheme"));
                    thingListBean.setStartTime(data.getJSONObject(i).getString("updateDate"));
                    thingListBean.setCode(data.getJSONObject(i).getString("itemId"));
                    thingListBean.setStatus(data.getJSONObject(i).getString("linkType"));
                    //是否评价参数
                    thingListBean.setEndTime(data.getJSONObject(i).getString("pingjia"));
                    list.add(thingListBean);
                }

                if (data.length() == 0 || data.length() < pageSize) {
                    //禁止加载更多
                    refresh.setEnableLoadmore(false);
                }
                initItemData();
            }else{
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
