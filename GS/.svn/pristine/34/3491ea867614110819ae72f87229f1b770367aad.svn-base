package com.example.gs.gonser.govenmentservice.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.adapter.ConvenRecyclerAdapter;
import com.example.gs.gonser.govenmentservice.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/14.
 * 便民所有item
 */

public class ConvenAllActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.titletool)
    TextView titletool;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.kuaidi)
    ImageView kuaidi;
    @BindView(R.id.weiz)
    ImageView weiz;
    @BindView(R.id.jipiao)
    ImageView jipiao;

    private List<Map<String, Object>> list;
    private int[] images = {R.mipmap.weather, R.mipmap.idcard, R.mipmap.calendar, R.mipmap.plane, R.mipmap.recruit, R.mipmap.navigat,
            R.mipmap.goods, R.mipmap.cellphone, R.mipmap.express};

    private Intent intent = null;

    @Override
    protected int getLayout() {
        return R.layout.convenallmain;
    }

    @Override
    protected void initView() {
        init();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    private void init() {

        titletool.setText("便民服务");

        list = new ArrayList<>();
        HashMap<String, Object> map;
        String[] convens = getResources().getStringArray(R.array.convenience);
        for (int i = 0; i < convens.length; i++) {
            map = new HashMap<>();
            map.put("image", images[i]);
            map.put("text", convens[i]);
            list.add(map);
        }

        GridLayoutManager manager = new GridLayoutManager(this, 3);
        recycleview.setLayoutManager(manager);
        ConvenRecyclerAdapter adapter = new ConvenRecyclerAdapter(this, list);
        recycleview.setAdapter(adapter);

        adapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        intent = new Intent(ConvenAllActivity.this, WebActivity.class);
                        intent.putExtra("title", "天气查询");
                        intent.putExtra("code", 1);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(ConvenAllActivity.this, WebActivity.class);
                        intent.putExtra("title", "身份证查询");
                        intent.putExtra("code", 2);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(ConvenAllActivity.this, WebActivity.class);
                        intent.putExtra("title", "万年历/黄历");
                        intent.putExtra("code", 3);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(ConvenAllActivity.this, WebActivity.class);
                        intent.putExtra("title", "机票预订");
                        intent.putExtra("code", 4);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(ConvenAllActivity.this, WebActivity.class);
                        intent.putExtra("title", "找工作");
                        intent.putExtra("code", 5);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(ConvenAllActivity.this, WebActivity.class);
                        intent.putExtra("title", "地图导航");
                        intent.putExtra("code", 6);
                        startActivity(intent);
                        break;
                    case 6:
                        intent = new Intent(ConvenAllActivity.this, WebActivity.class);
                        intent.putExtra("title", "商品价格");
                        intent.putExtra("code", 7);
                        startActivity(intent);
                        break;
                    case 7:
                        intent = new Intent(ConvenAllActivity.this, WebActivity.class);
                        intent.putExtra("title", "手机归属地");
                        intent.putExtra("code", 8);
                        startActivity(intent);
                        break;
                    case 8:
                        intent = new Intent(ConvenAllActivity.this, WebActivity.class);
                        intent.putExtra("title", "快递查询");
                        intent.putExtra("code", 9);
                        startActivity(intent);
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:
                        break;
                    default:

                        break;
                }
            }
        });

    }

    @OnClick({R.id.kuaidi, R.id.weiz, R.id.jipiao,R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.kuaidi:
                intent = new Intent(ConvenAllActivity.this, WebActivity.class);
                intent.putExtra("title", "快递查询");
                intent.putExtra("code", 9);
                startActivity(intent);
                break;
            case R.id.weiz:
                intent = new Intent(ConvenAllActivity.this, WebActivity.class);
                intent.putExtra("title", "违章查询");
                intent.putExtra("code", 11);
                startActivity(intent);
                break;
            case R.id.jipiao:
                intent = new Intent(ConvenAllActivity.this, WebActivity.class);
                intent.putExtra("title", "机票预订");
                intent.putExtra("code", 4);
                startActivity(intent);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
