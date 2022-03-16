package com.example.gs.gonser.govenmentservice.ui;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.adapter.DepartPopAdapter;
import com.example.gs.gonser.govenmentservice.adapter.ItemInfoAdapter;
import com.example.gs.gonser.govenmentservice.adapter.ItemInfoCLPopAdapter;
import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.bean.ApplyMaterBean;
import com.example.gs.gonser.govenmentservice.bean.Constant;
import com.example.gs.gonser.govenmentservice.bean.HelpTopicImageBean;
import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.controller.BeInCommController;
import com.example.gs.gonser.govenmentservice.interfaces.IRefreshUI;
import com.example.gs.gonser.govenmentservice.popup.CommonPopupWindow;
import com.example.gs.gonser.govenmentservice.utils.FileDownloadManager;
import com.example.gs.gonser.govenmentservice.utils.LogUtils;
import com.example.gs.gonser.govenmentservice.utils.SharedPreferencesUtils;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangzhao on 2018/5/17.
 * 事项详情
 */

public class ItemInfoActivity extends BaseActivity implements IRefreshUI {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.titletool)
    TextView titletool;
    @BindView(R.id.headText)
    TextView headText;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.code)
    TextView code;
    @BindView(R.id.orgname)
    TextView orgname;
    @BindView(R.id.putobj)
    TextView putobj;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.condition)
    TextView condition;
    @BindView(R.id.accord)
    TextView accord;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.flow)
    ImageView flow;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.refresh)
    RelativeLayout refresh;
    @BindView(R.id.shishizhuti)
    TextView shishizhuti;
    @BindView(R.id.chengban)
    TextView chengban;
    @BindView(R.id.quanli)
    TextView quanli;
    @BindView(R.id.banjianleixing)
    TextView banjianleixing;
    @BindView(R.id.gongtongshishi)
    TextView gongtongshishi;
    @BindView(R.id.xingshi)
    TextView xingshi;
    @BindView(R.id.tongban)
    TextView tongban;
    @BindView(R.id.fading)
    TextView fading;
    @BindView(R.id.chengnuoqixian)
    TextView chengnuoqixian;
    @BindView(R.id.zixundianhua)
    TextView zixundianhua;
    @BindView(R.id.jiandudianhua)
    TextView jiandudianhua;
    @BindView(R.id.shifoushoufei)
    TextView shifoushoufei;
    @BindView(R.id.shifouzaixiansb)
    TextView shifouzaixiansb;
    @BindView(R.id.zhongjie)
    TextView zhongjie;
    @BindView(R.id.jieguomingc)
    TextView jieguomingc;
    @BindView(R.id.daoxianchangcs)
    TextView daoxianchangcs;
    @BindView(R.id.shoulidid)
    TextView shoulidid;
    @BindView(R.id.shoulishij)
    TextView shoulishij;
    @BindView(R.id.banliyij)
    TextView banliyij;
    @BindView(R.id.moneyyiju)
    TextView moneyyiju;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.nodata)
    LinearLayout nodata;
    @BindView(R.id.lin)
    LinearLayout lin;

    private ItemInfoAdapter adapter;
    private List<ApplyMaterBean> list = new ArrayList<>();
    private String titleText = "事项详情";
    private BeInCommController controller;
    private String id = "";
    private String itemName = "";
    private String userType = "";
    private String serverObject = "";
    private TextView[] textViews = {};
    private String lcUrl = "";
    private CommonPopupWindow popupWindow;

    @Override
    protected int getLayout() {
        return R.layout.iteminfo_main;
    }

    @Override
    protected void initView() {
        search.setText("在线办理");

        textViews = new TextView[]{headText, type, code, orgname, putobj, number, condition, accord, money, moneyyiju, shishizhuti, chengban, quanli, banjianleixing,
                gongtongshishi, xingshi, tongban, fading, chengnuoqixian, zixundianhua, jiandudianhua, shifoushoufei, shifouzaixiansb, zhongjie, jieguomingc,
                daoxianchangcs, shoulidid, shoulishij, banliyij};

        titletool.setText(titleText);
        id = getIntent().getStringExtra("id");
        controller = new BeInCommController(this, this);
        //获取事项详情数据
        Map map = new HashMap();
        map.put("id", id);//事项id
        controller.postData(MyFacesUrl.itemInfo, map);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initEvent() {
        back.setFocusableInTouchMode(true);
        back.setFocusable(true);
        back.requestFocus();

        listener();
    }

    @Override
    public void success(String message, String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);

            String code_s = jsonObject.getString("code");
            if (code_s.equals("200")) {
                JSONObject data = jsonObject.getJSONObject("data");
                serverObject = data.getString("serverObject");
                itemName = data.getString("itemName");
                headText.setText(data.getString("itemName"));
                type.setText(data.getString("itemType"));
                code.setText(data.getString("itemCode"));
                orgname.setText(data.getString("departname"));
                putobj.setText(data.getString("enforcementBody"));
                number.setText(data.getString("quantitativeRestriction"));
                condition.setText(data.getString("appRequirement"));
                accord.setText(data.getString("setBasis"));
                money.setText(data.getString("feeScale"));
                moneyyiju.setText(data.getString("chargingBasis"));

                shishizhuti.setText(data.getString("thematicNature"));
                chengban.setText(data.getString("enforcementBody"));
                quanli.setText(data.getString("sourcePower"));
                banjianleixing.setText(data.getString("handleType"));
                gongtongshishi.setText(data.getString("liaisonMechanism"));
                xingshi.setText(data.getString("exerciseLevel"));
                tongban.setText(data.getString("scopeOperation"));
                fading.setText(data.getString("legalTime") + "个工作日");
                chengnuoqixian.setText(data.getString("commitmentTime") + "个工作日");
                zixundianhua.setText(data.getString("hotLine"));
                jiandudianhua.setText(data.getString("complaintTelephone"));
                shifoushoufei.setText(data.getString("isCharge"));
                shifouzaixiansb.setText(data.getString("iswsfwcharge"));
                zhongjie.setText(data.getString("intermediaryServices"));
                jieguomingc.setText(data.getString("resultName"));
                daoxianchangcs.setText(data.getString("localeTime"));
                shoulidid.setText(data.getString("locationManagement"));
                shoulishij.setText(data.getString("processingTime"));
                banliyij.setText(data.getString("setBasis"));
                lcUrl = data.getString("lcUrl");

                Glide.with(this).load(lcUrl).placeholder(R.mipmap.conn_defualt).into(flow);

                JSONArray array = data.getJSONArray("sAppMaterialsDtoList");
                ApplyMaterBean bean;
                for (int i = 0; i < array.length(); i++) {
                    bean = new ApplyMaterBean();
                    String materName = array.getJSONObject(i).getString("clmc");
                    String sample_Url = array.getJSONObject(i).getString("clyb");
                    String empty_Url = array.getJSONObject(i).getString("dzbd");
                    bean.setEmptyUrl(empty_Url);
                    bean.setSampleUrl(sample_Url);
                    bean.setMaterName(materName);
                    list.add(bean);
                }

                initCLAdapter();

                //判断所有TextView是否为空
                for (int j = 0; j < textViews.length; j++) {
                    TextView textView = textViews[j];
                    if (textView != null) {
                        String textStr = textView.getText().toString();
                        if (TextUtils.isEmpty(textStr)) {
                            textViews[j].setText("无");
                        }
                    }
                }
            }else if(code_s.equals("300")){
                showToast(jsonObject.getString("message"));
            }else{
                showToast("服务器异常");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fails(String message, int code) {
        showToast("服务器异常");
    }

    private void initCLAdapter(){
        if (list.size() != 0) {
            LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            recycleview.setLayoutManager(manager);
            adapter = new ItemInfoAdapter(this, list);
            recycleview.setAdapter(adapter);

            adapter.sampleDownFile(new ItemInfoAdapter.SampleDownLoadFile() {
                @Override
                public void setDownLoadFile(int pos) {
                    ApplyMaterBean bean = list.get(pos);
                    String sampleUrl = bean.getSampleUrl();
                    String sample[] = sampleUrl.split(",");
                    List<String> sampleList = new ArrayList<>();
                    for (String url:sample) {
                        sampleList.add(url);
                    }
                    showCLDownLoadPop(lin,bean.getMaterName(),sampleList);
                }
            });

            adapter.emptyDownFile(new ItemInfoAdapter.EmptyDownLoadFile() {
                @Override
                public void setDownLoadFile(int pos) {
                    ApplyMaterBean bean = list.get(pos);
                    String emptyUrl = bean.getEmptyUrl();
                    String empty[] = emptyUrl.split(",");
                    List<String> emptyList = new ArrayList<>();
                    for (String url:empty) {
                        emptyList.add(url);
                    }
                    showCLDownLoadPop(lin,bean.getMaterName(),emptyList);
                }
            });
        }
    }

    private void showCLDownLoadPop(View v, final String materName, final List<String> lists){
        WindowManager manager = getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;

        View upView = LayoutInflater.from(this).inflate(R.layout.recyclerview_only, null);
        RecyclerView recyclerView = upView.findViewById(R.id.recycleview);
        RelativeLayout popRel = upView.findViewById(R.id.popRel);
        ViewGroup.LayoutParams params = popRel.getLayoutParams();
        params.width = width-200;
        params.height = width+200;

        LinearLayoutManager managers = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(managers);
        ItemInfoCLPopAdapter popAdapters = new ItemInfoCLPopAdapter(this,lists,materName);
        recyclerView.setAdapter(popAdapters);
        popAdapters.setOnDownLoad(new ItemInfoCLPopAdapter.DownFile() {
            @Override
            public void startDownLoad(int pos) {
                downFile(lists.get(pos),materName);
            }
        });

        popAdapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

        showLog("---------------"+width+"-----"+height);
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(upView)
                .setAnimationStyle(R.anim.push_right_in)
                .setBackGroundLevel(0.3f)
                .setWidthAndHeight(width-200, width+200)
                .setOutsideTouchable(true)
                .create();

        popupWindow.showAtLocation(v, Gravity.CENTER,0,0);
    }

    @Override
    protected void onDestroy() {
        //释放
        textViews = null;
        if (broadcastReceiver!=null){
            unregisterReceiver(broadcastReceiver);
        }
        super.onDestroy();
    }

    @OnClick({R.id.back, R.id.search,R.id.flow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.flow:
                if (!TextUtils.isEmpty(lcUrl)){
                    HelpTopicImageBean bean = new HelpTopicImageBean();
                    bean.setUrl(lcUrl);
                    List<HelpTopicImageBean> list = new ArrayList<>();
                    list.add(bean);
                    Intent intent = new Intent(this, PictureView.class);
                    intent.putParcelableArrayListExtra("helpTopicImage", (ArrayList<? extends Parcelable>) list);
                    intent.putExtra("gu", "1");
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_in,0);
                }else {
                    showToast("暂无图片");
                }
                break;
            case R.id.search:
                String userid = (String) SharedPreferencesUtils.getParam(this, Constant.USERID,"");
                userType = (String) SharedPreferencesUtils.getParam(this, Constant.USERTYPE,"1");

                if (!TextUtils.isEmpty(userid)){
                    if ((!id.equals(""))&&(!itemName.equals(""))){
                        if (serverObject.indexOf(userType)!=-1){
                            Intent intent = new Intent(this, OnlineDoWebActivity.class);
                            intent.putExtra("itemid",id);
                            intent.putExtra("itemName",itemName);
                            this.startActivity(intent);
                        }else {
                            if (userType.equals("1")){
                                showToast("只有法人可以办理");
                            }else if(userType.equals("2")){
                                showToast("只有个人可以办理");
                            }
                        }
                    }else {
                        showToast("服务器异常");
                    }
                }else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
        }
    }

    private BroadcastReceiver broadcastReceiver = null;

    public void listener() {
        // 注册广播监听系统的下载完成事件。
        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long Id = downid;
                long ID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (ID == Id) {
                    showToast("任务下载完成");
                }else {
                    showToast("无效下载链接");
                }
            }
        };

        registerReceiver(broadcastReceiver, intentFilter);
    }
    long downid = 1000000000;
    private void downFile(String version_url,String title) {
        File file = new File("/sdcard/汉台政务APP/"+title);
//        File file = new File("///storage/emulated/0/政务APP/"+title);
        if (!file.exists()){
            file.mkdirs();
        }
//        String new_url = "http://192.168.0.94:8888/sxslfj/" + version_url.substring(0,version_url.length()-1);
//        String new_url = MyFacesUrl.PIC_IP + version_url;
        String new_url = version_url;
        LogUtils.i("表格下载url---"+new_url);
//        String new_url = version_url;
        int endIndex = new_url.lastIndexOf("/");
        String filename = new_url.substring(endIndex+1);
        String path = "汉台政务APP/"+title;
        LogUtils.i("---------------------"+filename);
        File file1 = new File("/sdcard/汉台政务APP/"+title);
//        File file1 = new File("///storage/emulated/0/政务APP/"+title+"/"+filename);

        FileDownloadManager fileDownloadManager = FileDownloadManager.getInstance(this);
        downid = fileDownloadManager.startDownload(new_url,filename,path,"汉台政务",file1,"nodothing");
        String status = fileDownloadManager.getDownloadStatus(downid);
        Toast.makeText(this,status,Toast.LENGTH_SHORT).show();
//        LogUtils.i(downid+"----------");

//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
//        sp.edit().putLong(DownloadManager.EXTRA_DOWNLOAD_ID,downid).commit();
    }

}
