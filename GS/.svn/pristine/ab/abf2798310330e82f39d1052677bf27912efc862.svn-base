package com.example.gs.gonser.govenmentservice.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.adapter.DoThingShowAdapter;
import com.example.gs.gonser.govenmentservice.adapter.ItemListAdapter;
import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.bean.DoThingBean;
import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.bean.ThingListBean;
import com.example.gs.gonser.govenmentservice.controller.BeInCommController;
import com.example.gs.gonser.govenmentservice.interfaces.IRefreshUI;
import com.example.gs.gonser.govenmentservice.utils.SpaceItemDecoration;
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
 * Created by yangzhao on 2018/5/18.
 * 办件公示
 */

public class DoThingShowActivity extends BaseActivity implements IRefreshUI {

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
    @BindView(R.id.searchs)
    TextView searchs;
    @BindView(R.id.searchLin)
    LinearLayout searchLin;
    @BindView(R.id.searchConetnt)
    EditText searchConetnt;
    @BindView(R.id.search)
    TextView search;

    private String titleText = "办件公示";
    private DoThingShowAdapter adapter;
    private List<DoThingBean> list = new ArrayList<>();
    private BeInCommController controller;
    private int pageIndex = 1;//默认一页显示20条，由后台那边控制
    private int pageSize = 20;//默认一页显示20条，由后台那边控制
    private String search_con = "";

    @Override
    protected int getLayout() {
        return R.layout.toolbar_and_recycler;
    }

    @Override
    protected void initView() {
        controller = new BeInCommController(this,this);
        titletool.setText(titleText);
    }

    @Override
    protected void initData() {
        Map map = new HashMap();
        map.put("pageNumber", "" + pageIndex);
        map.put("itemName", "" + search_con);
        controller.postData(MyFacesUrl.doThingShowList,map);
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

    //同步数据
    private void synchData(){

        if (pageIndex == 1) {
            if (list.size() == 0) {
                nodata.setVisibility(View.VISIBLE);
            }else {
                nodata.setVisibility(View.GONE);
                LinearLayoutManager manager = new LinearLayoutManager(this);
                recycleview.setLayoutManager(manager);
                recycleview.addItemDecoration(new SpaceItemDecoration(1));
                adapter = new DoThingShowAdapter(this,list);
                recycleview.setAdapter(adapter);

//                adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        Intent intent = new Intent(DoThingShowActivity.this,ItemInfoActivity.class);
//                        intent.putExtra("id",list.get(position).getId());
////                        intent.putExtra("itemList", (Parcelable) list.get(position));
//                        startActivity(intent);
//                    }
//                });
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
            if (code.equals("200")){
                DoThingBean bean;
                for (int i = 0; i < data.length(); i++) {
                    bean = new DoThingBean();
                    bean.setId(data.getJSONObject(i).getString("item_id"));
                    String linkType = data.getJSONObject(i).getString("link_type");

//                    if (linkType.equals("B2")) {
//                        linkType = "提交";
//                    } else if (linkType.equals("A0")) {
//                        linkType = "派件";
//                    } else if (linkType.equals("A1")) {
//                        linkType = "受理";
//                    } else if (linkType.equals("A2")) {
//                        linkType = "审查";
//
//                    } else if (linkType.equals("A3")) {
//                        linkType = "决定";
//
//                    } else if (linkType.equals("B1")) {
//                        linkType = "暂存";
//
//                    }else if (linkType.equals("C1")) {
//                        linkType = "予以许可";
//                    }else if (linkType.equals("C2")) {
//                        linkType = "不予许可";
//
//                    }else if (linkType.equals("B3")) {
//                        linkType = "补齐补正";
//
//                    }else if (linkType.equals("C4")) {
//                        linkType = "打回";
//
//                    }else if (linkType.equals("C3")) {
//                        linkType = "退回";
//
//                    }else if (linkType.equals("A22")) {
//                        linkType = "法规复核";
//
//                    }else if (linkType.equals("A31")) {
//                        linkType = "审定";
//
//                    }else if (linkType.equals("A21")) {
//                        linkType = "业务复核";
//                    }else {
//                        linkType = "待审";
//                    }

                    bean.setStatus(linkType);
//                    bean.setEndTime(data.getJSONObject(i).getString("update_date"));
                    bean.setStartTime(data.getJSONObject(i).getString("update_date"));
//                    bean.setCode(data.getJSONObject(i).getString("bid_number"));
                    bean.setThing(data.getJSONObject(i).getString("business_theme"));
                    bean.setCode(data.getJSONObject(i).getString("item_name"));
                    bean.setEndTime(data.getJSONObject(i).getString("create_name"));//申请人或单位
//                bean.setOrgname(data.getJSONObject(i).getString("id"));
                    list.add(bean);
                }

                if (data.length()==0||data.length()<pageSize){
                    //禁止加载更多
                    refresh.setEnableLoadmore(false);
                }
                synchData();
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

    @OnClick({R.id.back,R.id.search,R.id.searchs})
    public void onViewClicked(View view) {
        switch (view.getId()){
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
