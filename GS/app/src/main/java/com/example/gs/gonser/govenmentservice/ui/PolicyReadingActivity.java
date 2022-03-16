package com.example.gs.gonser.govenmentservice.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.adapter.DoThingShowAdapter;
import com.example.gs.gonser.govenmentservice.adapter.ItemListAdapter;
import com.example.gs.gonser.govenmentservice.adapter.PolicyReadingAdapter;
import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.bean.DoThingBean;
import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.bean.PolicyReadBean;
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
 * Created by yangzhao on 2018/5/19.
 * 政策解读
 */

public class PolicyReadingActivity extends BaseActivity implements IRefreshUI {

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
    @BindView(R.id.search)
    TextView search;

    private String titleText = "政策解读";
    private PolicyReadingAdapter adapter;
    private List<PolicyReadBean> list = new ArrayList<>();
    private BeInCommController controller;
    private int pageIndex = 1;//默认一页显示20条，由后台那边控制
    private int pageSize = 20;//默认一页显示20条，由后台那边控制
    private String typeCode = "1";
    private String titleID = "0";
    private String Url = MyFacesUrl.policyList;
    @Override
    protected int getLayout() {
        return R.layout.toolbar_and_recycler;
    }

    @Override
    protected void initView() {
        controller = new BeInCommController(this, this);
        titleID = getIntent().getStringExtra("titleID");
        //titleID ==0 是政策解读  titleID =1 是通知公告

        search.setVisibility(View.GONE);
        if (titleID.equals("0")){
            titletool.setText(titleText);
            Url = MyFacesUrl.policyList;
        }else {
            titletool.setText("政策解读");
            Url = MyFacesUrl.noticeList;
        }
    }

    @Override
    protected void initData() {
        Map map = new HashMap();
        map.put("pageNumber", "" + pageIndex);
        controller.postData(Url, map);
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
                adapter = new PolicyReadingAdapter(this,list);
                recycleview.setAdapter(adapter);

                adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(PolicyReadingActivity.this,ArticleInfoActivity.class);
                        intent.putExtra("obj",list.get(position));
                        startActivity(intent);
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
                PolicyReadBean bean;
                for (int i = 0; i < data.length(); i++) {
                    bean = new PolicyReadBean();
                    bean.setId(data.getJSONObject(i).getString("id"));
                    bean.setTitle(data.getJSONObject(i).getString("infoTitle"));
                    String date = data.getJSONObject(i).getString("infoCreateDate");
                    if (!date.equals("null")){
                        String dates [] = date.split(" ");
                        bean.setDate(dates[0]);
                    }else {
                        bean.setDate("2018-04-12");
                    }
                    list.add(bean);
                }

                if (data.length() == 0 || data.length() < pageSize) {
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

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
