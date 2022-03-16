package com.example.gs.gonser.govenmentservice.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.adapter.ThingListAdapter;
import com.example.gs.gonser.govenmentservice.base.BaseFragment;
import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.bean.ThingListBean;
import com.example.gs.gonser.govenmentservice.controller.BeInCommControllerFrag;
import com.example.gs.gonser.govenmentservice.interfaces.IRefreshUI;
import com.example.gs.gonser.govenmentservice.ui.ItemInfoActivity;
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
import butterknife.Unbinder;

/**
 * Created by yangzhao on 2018/5/5..
 * 事项清单
 */

public class ThingListFragment extends BaseFragment implements IRefreshUI {

    @BindView(R.id.titletool)
    TextView titletool;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.refresh)
    TwinklingRefreshLayout refresh;
    @BindView(R.id.rel)
    RelativeLayout rel;
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

    private String titleText = "清单列表";
    private List<ThingListBean> list = new ArrayList<>();
    private ThingListAdapter adapter;
    private BeInCommControllerFrag controller;
    private int pageIndex = 1;//默认一页显示20条，由后台那边控制
    private int pageSize = 20;//默认一页显示20条，由后台那边控制
    private String search_con = "";

    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container) {
        if (mview == null) {
            mview = inflater.inflate(R.layout.thinglist, container, false);
        }
        ViewGroup parent = (ViewGroup) mview.getParent();
        if (parent != null) {
            parent.removeView(mview);
        }
        return mview;
    }

    @Override
    protected void initView() {
        refresh.setEnableLoadmore(false);
        refresh.setEnableRefresh(false);
        controller = new BeInCommControllerFrag(this, this);
        rel.setPadding(0, getStatusBarHeight(), 0, 0);
        titletool.setText(titleText);

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
    }

    @Override
    protected void initData() {
//        Map map = new HashMap();
//        map.put("pageNumber", "" + pageIndex);
//        map.put("itemName", "" + search_con);
//        controller.postData(MyFacesUrl.itemThingList, map);

        //假数据
        ThingListBean thingListBean;
        thingListBean = new ThingListBean();
        thingListBean.setTitle("母婴保健专项技术服务执业许可");
        thingListBean.setInfo("事项类型：" + "行政许可"+"    "+"办理机构：" + "省级");
        thingListBean.setStarSize("4");
        list.add(thingListBean);
        thingListBean = new ThingListBean();
        thingListBean.setTitle("医疗机构设置人类精子库许可");
        thingListBean.setInfo("事项类型：" + "行政许可"+"    "+"办理机构：" + "省级");
        thingListBean.setStarSize("5");
        list.add(thingListBean);
        thingListBean = new ThingListBean();
        thingListBean.setTitle("涉外、涉港澳台、涉华侨婚姻登记");
        thingListBean.setInfo("事项类型：" + "行政许可"+"    "+"办理机构：" + "省级");
        thingListBean.setStarSize("4");
        list.add(thingListBean);
        thingListBean = new ThingListBean();
        thingListBean.setTitle("涉外收养登记、解除收养关系登记\n");
        thingListBean.setInfo("事项类型：" + "行政许可"+"    "+"办理机构：" + "省级");
        thingListBean.setStarSize("4");
        list.add(thingListBean);
        thingListBean = new ThingListBean();
        thingListBean.setTitle("因参与传染病防治工作致病、致残、死亡人员的补助、抚恤");
        thingListBean.setInfo("事项类型：" + "行政许可"+"    "+"办理机构：" + "省级");
        thingListBean.setStarSize("3");
        list.add(thingListBean);
        thingListBean = new ThingListBean();
        thingListBean.setTitle("因参与传染病防治工作致病、致残、死亡人员的补助、抚恤");
        thingListBean.setInfo("事项类型：" + "行政许可"+"    "+"办理机构：" + "省级");
        thingListBean.setStarSize("4");
        list.add(thingListBean);
        thingListBean = new ThingListBean();
        thingListBean.setTitle("对非现役军人、公务员等人员残疾等级的认定和评定");
        thingListBean.setInfo("事项类型：" + "行政许可"+"    "+"办理机构：" + "省级");
        thingListBean.setStarSize("5");
        list.add(thingListBean);
        thingListBean = new ThingListBean();
        thingListBean.setTitle("评定牺牲的非现役军人为烈士");
        thingListBean.setInfo("事项类型：" + "行政许可"+"    "+"办理机构：" + "省级");
        thingListBean.setStarSize("4");
        list.add(thingListBean);
        thingListBean = new ThingListBean();
        thingListBean.setTitle("企业职工和离退休人员因病或非因工死亡及供养直系亲属待遇核定");
        thingListBean.setInfo("事项类型：" + "行政许可"+"    "+"办理机构：" + "省级");
        thingListBean.setStarSize("5");
        list.add(thingListBean);
        initThingData();

    }

    @Override
    protected void initEvent() {
        titletool.setFocusableInTouchMode(true);
        titletool.setFocusable(true);
        titletool.requestFocus();
    }

    private void initThingData() {
        if (pageIndex == 1) {
            if (list.size() == 0) {
                nodata.setVisibility(View.VISIBLE);
            } else {
                nodata.setVisibility(View.GONE);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recycleview.setLayoutManager(linearLayoutManager);
                adapter = new ThingListAdapter(getContext(), list);
                recycleview.setAdapter(adapter);

                adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Intent intent = new Intent(getContext(), ItemInfoActivity.class);
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
                initThingData();
            } else {
                Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fails(String message, int code) {
        refresh.finishLoadmore();
        refresh.finishRefreshing();
        Toast.makeText(getContext(), "服务器异常", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.search, R.id.searchs})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search:
                if (searchLin.getVisibility()==View.GONE){
                    searchLin.setVisibility(View.VISIBLE);
                    search.setText("取消");
                }else {
                    searchLin.setVisibility(View.GONE);
                    search.setText("搜索");
                }
                break;
            case R.id.searchs:
                search_con = searchConetnt.getText().toString();
                list.clear();
                pageIndex = 1;
                initData();
                break;
        }
    }
}