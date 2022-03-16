package com.example.gs.gonser.govenmentservice.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.adapter.DoThingBasicAdapter;
import com.example.gs.gonser.govenmentservice.adapter.MyDoThingInfoAdapter;
import com.example.gs.gonser.govenmentservice.adapter.MyDoThingsInfoAdapter;
import com.example.gs.gonser.govenmentservice.adapter.OnlineDoWebAdapter;
import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.bean.FlowsBean;
import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.bean.SpMaterialInformation;
import com.example.gs.gonser.govenmentservice.bean.SpUpload;
import com.example.gs.gonser.govenmentservice.controller.BeInCommController;
import com.example.gs.gonser.govenmentservice.interfaces.IRefreshUI;
import com.example.gs.gonser.govenmentservice.utils.LogUtils;
import com.example.gs.gonser.govenmentservice.utils.SpaceItemDecoration;
import com.lcodecore.tkrefreshlayout.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangzhao on 2018/5/19.
 * 办件详情
 */

public class DoThingInfoActivity extends BaseActivity implements IRefreshUI {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.titletool)
    TextView titletool;
    @BindView(R.id.chuangkou)
    TextView chuangkou;
    @BindView(R.id.savePath)
    TextView savePath;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.recycleview_htmlcontent)
    RecyclerView recycleviewHtmlcontent;
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.htmlContent)
    WebView webView;
    @BindView(R.id.recycleview)
    RecyclerView recyclerView;

    private String titleText = "办件详情";
    private BeInCommController controller;
    private String flowId = "";
    private String userID = "";
    private String itemid = "";
    private List<FlowsBean> list = new ArrayList<>();
    private List<String> listStr = new ArrayList<>();
    private List<Map<String, String>> listStrM = new ArrayList<>();
    private List<SpMaterialInformation> spMaterialInformationList = new ArrayList<>();

    @Override
    protected int getLayout() {
        return R.layout.dothinginfo_main;
    }

    @Override
    protected void initView() {
        controller = new BeInCommController(this, this);
        titletool.setText(titleText);
        flowId = getIntent().getStringExtra("id");
        userID = getIntent().getStringExtra("userID");
        itemid = getIntent().getStringExtra("itemid");
    }

    @Override
    protected void initData() {
        Map map = new HashMap();
        map.put("flowId", flowId);
        controller.postData(MyFacesUrl.business_look, map);

        String webViewUrl = MyFacesUrl.baseInfoForm + "itemId="+itemid+"&userId=" + userID+"&flowId="+flowId;
        LogUtil.i(webViewUrl);
        setDataWebView(webViewUrl);
    }

    /**
     * 加载webview 加载数据
     * @param url
     */
    private void setDataWebView(String url) {

        //加载服务器上的页面
        webView.loadUrl(url);
        //加载本地中的html
        //myWebView.loadUrl("file:///android_asset/www/test2.html");
        //加上下面这段代码可以使网页中的链接不以浏览器的方式打开
        webView.setWebViewClient(new WebViewClient());
        //得到webview设置
        WebSettings webSettings = webView.getSettings();
        //允许使用javascript
        webSettings.setJavaScriptEnabled(true);
        //将WebAppInterface于javascript绑定
//        webView.addJavascriptInterface(new WebAppInterface(this), "Android");

        webView.setWebViewClient(new WebViewClient() {
            //覆写shouldOverrideUrlLoading实现内部显示网页
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO 自动生成的方法存根
                view.loadUrl(url);
                return true;
            }
        });

//        webView.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onProgressChanged(WebView view, int newProgress) {
//                // TODO 自动生成的方法存根
//                if (pg1!=null){
//                    if (newProgress == 100) {
//                        pg1.setVisibility(View.GONE);//加载完网页进度条消失
//                    } else {
//                        pg1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
//                        pg1.setProgress(newProgress);//设置进度值
//                    }
//                }
//            }
//        });
    }


    @Override
    protected void initEvent() {

    }

    @Override
    public void success(String message, String json) {

        try {
            JSONObject object = new JSONObject(json);
            String code = object.getString("code");
            if (code.equals("200")) {
                JSONObject data = object.getJSONObject("data");

//                JSONArray flowSteps = data.getJSONArray("flowSteps");
                JSONArray spMaterialInfo = data.getJSONArray("spMaterialInfo");
                String materialName = "";
                String materialNames = "";

                for (int i = 0; i < spMaterialInfo.length(); i++) {
                    JSONObject mater = spMaterialInfo.getJSONObject(i);
                    SpMaterialInformation spMaterialInformation1 = new SpMaterialInformation();
                    spMaterialInformation1.setMaterialName(mater.getString("materialName"));
                    spMaterialInformation1.setId(mater.getString("id"));
                    String subType = mater.getString("submitType");
                    if (subType!=null&&!subType.equals("null")){
                        if (subType.equals("2")){
                            chuangkou.setVisibility(View.GONE);
                            savePath.setVisibility(View.VISIBLE);
                        }else {
                            chuangkou.setVisibility(View.VISIBLE);
                            savePath.setVisibility(View.GONE);
                        }
                    }
                    JSONArray spUploads = mater.getJSONArray("spUploads");
                    List<SpUpload> spUploadsList = new ArrayList();
                    if (spUploads.length()!=0){
                        for (int j = 0; j < spUploads.length(); j++){
                            JSONObject jsonObject1 = spUploads.getJSONObject(j);
                            SpUpload spUpload1 = new SpUpload();
                            spUpload1.setUploadId(jsonObject1.getString("uploadId"));
                            spUpload1.setNewName(jsonObject1.getString("newName"));
                            spUpload1.setOldName(jsonObject1.getString("oldName"));
                            spUpload1.setUrl(jsonObject1.getString("url"));
                            spUploadsList.add(spUpload1);
                        }
                    }
                    spMaterialInformation1.setSpUploads(spUploadsList);
                    spMaterialInformationList.add(spMaterialInformation1);
                }

                LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };

                recyclerView.addItemDecoration(new SpaceItemDecoration(1));
                recyclerView.setLayoutManager(manager);
                MyDoThingInfoAdapter adapter = new MyDoThingInfoAdapter(this, spMaterialInformationList);
                recyclerView.setAdapter(adapter);

//                FlowsBean bean;
//                for (int i = 0; i < flowSteps.length(); i++) {
//                    bean = new FlowsBean();
//                    JSONObject object1 = flowSteps.getJSONObject(i);
//
//                    String linkType = object1.getString("linkType");
//                    if (linkType.equals("B2")) {
//                        linkType = "提交";
//                    } else if (linkType.equals("A0")) {
//                        linkType = "派件";
//                    } else if (linkType.equals("A1")) {
//                        linkType = "受理";
//                    } else if (linkType.equals("A2")) {
//                        linkType = "审查";
//                    } else if (linkType.equals("A3")) {
//                        linkType = "决定";
//                    } else if (linkType.equals("B1")) {
//                        linkType = "暂存";
//                    }else if (linkType.equals("C1")) {
//                        linkType = "予以许可";
//                    }else if (linkType.equals("C2")) {
//                        linkType = "不予许可";
//                    }else if (linkType.equals("B3")) {
//                        linkType = "补齐补正";
//                    }else if (linkType.equals("C4")) {
//                        linkType = "打回";
//                    }else if (linkType.equals("C3")) {
//                        linkType = "退回";
//                    }else if (linkType.equals("A22")) {
//                        linkType = "法规复核";
//                    }else if (linkType.equals("A31")) {
//                        linkType = "审定";
//                    }else if (linkType.equals("A21")) {
//                        linkType = "业务复核";
//                    }else {
//                        linkType = "待审";
//                    }
//                    bean.setStepname(linkType);
//                    bean.setFinishtime(object1.getString("createDate"));
//                    if (object1.getString("result").equals("null")) {
//                        bean.setRemark("");
//                    } else {
//                        bean.setRemark(object1.getString("result"));
//                    }
//                    list.add(bean);
//                }
//                LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
//                    @Override
//                    public boolean canScrollVertically() {
//                        return false;
//                    }
//                };
//
//                recycleviewJd.setLayoutManager(manager);
//                MyDoThingsInfoAdapter adapter = new MyDoThingsInfoAdapter(this, list);
//                recycleviewJd.setAdapter(adapter);

//                String html = data.getString("html");
//                htmlContent.setText(Html.fromHtml(html));

//                Document doc = Jsoup.parse(data.getString("html"));
//                try {
//                    table1(doc);
//                } catch (IndexOutOfBoundsException e) {
//                    e.printStackTrace();
//                }

//                JSONObject itemd = data.getJSONObject("itemd");
//                shenqingcl.setText(itemd.getString("appRequirement"));
            } else {
                showToast(object.getString("message"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析html表格，原生展示
     * @param doc
     * @return
     */
    private int table1(Document doc) {
        Elements trs = doc.select("table").select("tr");
        int i;
        for (i = 0; i < trs.size(); i++) {
            Elements tds = trs.get(i).select("td");
            for (int j = 0; j < tds.size(); j++) {
                String txt = tds.get(j).text();
//                LogUtils.i(txt);
                listStr.add(txt);
            }
            System.out.println("");
        }

        upList(listStr);
        return i;
    }

    /**
     * 从新梳理list，改为map
     *
     * @param list1
     */
    private void upList(List<String> list1) {
        Map map;
        for (int i = 0; i < list1.size(); i = i + 2) {
            map = new HashMap();
            map.put("name", list1.get(i));
            map.put("content", list1.get(i + 1));
            listStrM.add(map);
        }

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        recycleviewHtmlcontent.setLayoutManager(manager);
        DoThingBasicAdapter adapter = new DoThingBasicAdapter(this, listStrM);
        recycleviewHtmlcontent.setAdapter(adapter);
    }

    @Override
    public void fails(String message, int code) {
        showToast("服务器异常");
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
