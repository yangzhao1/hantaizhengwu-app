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
import com.example.gs.gonser.govenmentservice.adapter.ItemListAdapter;
import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.bean.ThingListBean;
import com.example.gs.gonser.govenmentservice.controller.BeInCommController;
import com.example.gs.gonser.govenmentservice.interfaces.IRefreshUI;
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
 * Created by yangzhao on 2018/5/9.
 * 事项列表
 */

public class ItemListActivity extends BaseActivity implements IRefreshUI {

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

    private String titleText = "事项列表";
    private List<ThingListBean> list = new ArrayList<>();
    private ItemListAdapter adapter;
    private BeInCommController controller;
    private int pageIndex = 1;//默认一页显示20条，由后台那边控制
    private int pageSize = 20;//默认一页显示20条，由后台那边控制
    private String typeCode = "1";
    private String search_con = "";

    @Override
    protected int getLayout() {
        return R.layout.itemlist_activity;
    }

    @Override
    protected void initView() {
        controller = new BeInCommController(this, this);

        titleText = getIntent().getStringExtra("titleText");
        typeCode = getIntent().getStringExtra("serviceTopic");
        titletool.setText(titleText);
    }

    @Override
    protected void initData() {
//        Map map = new HashMap();
//        map.put("pageNumber", "" + pageIndex);
//        map.put("serviceTopic", "" + typeCode);
//        map.put("itemName", "" + search_con);
//        controller.postData(MyFacesUrl.itemList, map);
        ThingListBean thingListBean;
        thingListBean = new ThingListBean();
        thingListBean.setTitle("宗教团体、寺观教堂编印宗教内部资料性出版物或者其他宗教用品的审批");
        thingListBean.setInfo("事项类型：" + "行政许可"+"    "+"办理机构：" + "省级");
        list.add(thingListBean);
        thingListBean = new ThingListBean();
        thingListBean.setTitle("香港澳门服务者在内地设立独资人力资源服务（人才中介服务、职业中介）机构审批");
        thingListBean.setInfo("事项类型：" + "行政许可"+"    "+"办理机构：" + "省级");
        list.add(thingListBean);
        thingListBean = new ThingListBean();
        thingListBean.setTitle("违法行为造成水生动植物自然保护区损失责令限期改正、赔偿损失");
        thingListBean.setInfo("事项类型：" + "行政许可"+"    "+"办理机构：" + "省级");
        list.add(thingListBean);
        thingListBean = new ThingListBean();
        thingListBean.setTitle("涉外收养登记、解除收养关系登记");
        thingListBean.setInfo("事项类型：" + "行政许可"+"    "+"办理机构：" + "省级");
        list.add(thingListBean);
        thingListBean = new ThingListBean();
        thingListBean.setTitle("因参与传染病防治工作致病、致残、死亡人员的补助、抚恤");
        thingListBean.setInfo("事项类型：" + "行政许可"+"    "+"办理机构：" + "省级");
        list.add(thingListBean);
        thingListBean = new ThingListBean();
        thingListBean.setTitle("因参与传染病防治工作致病、致残、死亡人员的补助、抚恤");
        thingListBean.setInfo("事项类型：" + "行政许可"+"    "+"办理机构：" + "省级");
        list.add(thingListBean);
        thingListBean = new ThingListBean();
        thingListBean.setTitle("对非现役军人、公务员等人员残疾等级的认定和评定");
        thingListBean.setInfo("事项类型：" + "行政许可"+"    "+"办理机构：" + "省级");
        list.add(thingListBean);
        thingListBean = new ThingListBean();
        thingListBean.setTitle("评定牺牲的非现役军人为烈士");
        thingListBean.setInfo("事项类型：" + "行政许可"+"    "+"办理机构：" + "省级");
        list.add(thingListBean);
        thingListBean = new ThingListBean();
        thingListBean.setTitle("企业职工和离退休人员因病或非因工死亡及供养直系亲属待遇核定");
        thingListBean.setInfo("事项类型：" + "行政许可"+"    "+"办理机构：" + "省级");
        list.add(thingListBean);
        initItemData();
    }

    @Override
    protected void initEvent() {
        //假数据
        refresh.setEnableRefresh(false);
        refresh.setEnableLoadmore(false);
        refresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                list.clear();
                pageIndex = 1;
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

    private void initItemData() {
        if (pageIndex == 1) {
            if (list.size() == 0) {
                nodata.setVisibility(View.VISIBLE);
            } else {
                nodata.setVisibility(View.GONE);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                recycleview.setLayoutManager(linearLayoutManager);
                adapter = new ItemListAdapter(this, list);
                recycleview.setAdapter(adapter);

                adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Intent intent = new Intent(ItemListActivity.this, ItemInfoActivity.class);
//                        intent.putExtra("id", list.get(position).getId());
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
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray data = jsonObject.getJSONArray("data");
            String code = jsonObject.getString("code");
            if (code.equals("200")) {
                ThingListBean thingListBean;
                for (int i = 0; i < data.length(); i++) {
                    thingListBean = new ThingListBean();
                    JSONObject matterDto = data.getJSONObject(i);
                    thingListBean.setId(matterDto.getString("id"));
                    thingListBean.setTitle(matterDto.getString("itemName"));
                    thingListBean.setServerObject(matterDto.getString("serverObject"));
                    thingListBean.setInfo("事项类型：" + matterDto.getString("itemType")+"     "+"办理机构：" + matterDto.getString("departname") );
                    thingListBean.setStarSize(matterDto.getString("itemstar"));
                    thingListBean.setIsCollect(false);
                    list.add(thingListBean);
                }
                if (data.length() == 0 || data.length() < pageSize) {
                    //禁止加载更多
                    refresh.setEnableLoadmore(false);
                }
                initItemData();
            } else {
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
