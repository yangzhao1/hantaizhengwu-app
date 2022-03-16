package com.example.gs.gonser.govenmentservice.ui;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.adapter.OnlineDoAdapter;
import com.example.gs.gonser.govenmentservice.adapter.OnlineDoBasicAdapter;
import com.example.gs.gonser.govenmentservice.adapter.OnlinePopAdapter;
import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.bean.BaiscInfoBean;
import com.example.gs.gonser.govenmentservice.bean.Constant;
import com.example.gs.gonser.govenmentservice.bean.DoThingBean;
import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.bean.UserBean;
import com.example.gs.gonser.govenmentservice.controller.OnlineDoController;
import com.example.gs.gonser.govenmentservice.interfaces.IOnlineDoRefreshUI;
import com.example.gs.gonser.govenmentservice.interfaces.MyCallBack;
import com.example.gs.gonser.govenmentservice.popup.CommonPopupWindow;
import com.example.gs.gonser.govenmentservice.utils.LogUtils;
import com.example.gs.gonser.govenmentservice.utils.MyOkHttpClient;
import com.example.gs.gonser.govenmentservice.utils.SharedPreferencesUtils;
import com.example.gs.gonser.govenmentservice.utils.SpaceItemDecoration;
import com.example.gs.gonser.govenmentservice.utils.UniversalMethod;
import com.example.gs.gonser.govenmentservice.utils.Validator;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by yangzhao on 2018/5/18.
 * 在线办理
 */

public class OnlineDoActivity extends BaseActivity implements IOnlineDoRefreshUI {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.titletool)
    TextView titletool;
    @BindView(R.id.itemName)
    TextView itemNameText;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.usercode)
    EditText usercode;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.remark)
    EditText remark;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.recycleview_basic)
    RecyclerView recycleview_basic;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.lin)
    LinearLayout lin;
    @BindView(R.id.spinnerType_person)
    Spinner spinnerType;
    @BindView(R.id.youjifou)
    RadioGroup youjifou;
    @BindView(R.id.submitType)
    RadioGroup submitType;
    @BindView(R.id.shoujianrn_phone)
    EditText shoujianrnPhone;
    @BindView(R.id.shoujianrn_name)
    EditText shoujianrnName;
    @BindView(R.id.shoujianrn_address)
    EditText shoujianrnAddress;
    @BindView(R.id.personInfo)
    LinearLayout personInfo;
    @BindView(R.id.tongyi)
    TextView tongyi;
    @BindView(R.id.spinnerType_legal)
    Spinner spinnerTypeLegal;
    @BindView(R.id.qiyeming)
    EditText qiyeming;
    @BindView(R.id.jingbanren)
    EditText jingbanren;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.jingbanphone)
    EditText jingbanphone;
    @BindView(R.id.qiyeTypeCode)
    EditText qiyeTypeCode;
    @BindView(R.id.qiyeTypeName)
    EditText qiyeTypeName;
    @BindView(R.id.fadingdaibiaoren)
    EditText fadingdaibiaoren;
    @BindView(R.id.farenzhengjianname)
    EditText farenzhengjianname;
    @BindView(R.id.farenzhengjianCode)
    EditText farenzhengjianCode;
    @BindView(R.id.lianxidianhua)
    EditText lianxidianhua;
    @BindView(R.id.lianxiren)
    EditText lianxiren;
    @BindView(R.id.danweizhucedizhi)
    EditText danweizhucedizhi;
    @BindView(R.id.jingyingshengchan)
    EditText jingyingshengchan;
    @BindView(R.id.legalInfo)
    LinearLayout legalInfo;
    @BindView(R.id.youjiLin)
    LinearLayout youjiLin;
    @BindView(R.id.farenTypeText)
    TextView farenTypeText;

    private String titleText = "在线办理";
    private OnlineDoAdapter adapter;
    //设置个最大值判断position 是否赋值成功
    private int position = 10000;
    private List<Map<String, String>> listPath = new ArrayList<>();
    private String myinfo = "";
    private CommonPopupWindow popupWindow;

    //申请材料listcl是提交列表。listc是阅读须知列表哦
    private List<Map<String, String>> listcl = new ArrayList<>();
    private List<Map<String, String>> list_zjlx = new ArrayList<>();
    private List<String> listc = new ArrayList<>();
    private LinearLayoutManager manager;
    private OnlineDoController controller;
    private String itemid = "";
    private String itemName = "";
    private List<String> listStr = new ArrayList<>();
    private List<Map<String, String>> listStrM = new ArrayList<>();
    private List<Map<String, String>> listSpinnerLegal = new ArrayList<>();
    private List<BaiscInfoBean> baiscInfoBeans = new ArrayList<>();//动态表单list
    private String[] spinnType = {};

    //个人提交数据String
    private String usernameStr = "";
    private String usercodeStr = "";
    private String phoneStr = "";
    private String emailStr = "";
    private String addressStr = "";
    private String spinnerStr = "";
    private String youjifouStr = "1";
    private String shoujianren_nameStr = "";
    private String shoujianren_dianhuaStr = "";
    private String youji_dizhiStr = "";
    private String userID = "";
    private String formid = "";//动态表单提交id
    private String valuesStr;//提交的动态表单数据

    //法人提交动态表单
    private String zhengjianTypeCode_Str = "";
    private String fadingdaibiaorenType_Str = "";
    private String qiyemingcheng_Str = "";
    private String jingbanren_Str = "";
    private String dengjizhucedate_Str = "";
    private String jingbanrendianhua_Str = "";
    private String qiyeTypeCode_Str = "";
    private String qiyeTypeName_Str = "";
    private String fadingdaibiaoren_Str = "";
    private String farenzhengjianName_Str = "";
    private String farenzhengjianCode_Str = "";
    private String lianxidianhua_Str = "";
    private String lianxiren_Str = "";
    private String danweizhucedizhi_Str = "";
    private String jingyingshengchanfanwei_Str = "";
    private String submitType_Str = "2"; //1表示窗口提交，2表示在线提交

    private String userType = "";
    private Calendar cal;
    private int year,month,day;
    @Override
    protected int getLayout() {
        return R.layout.onlinedo_main;
    }

    @Override
    protected void initView() {
        titletool.setText(titleText);
        itemid = getIntent().getStringExtra("itemid");
        itemName = getIntent().getStringExtra("itemName");
        itemNameText.setText(itemName);

        controller = new OnlineDoController(this, this);

        Map map ;
        map = new HashMap();
        map.put("id","01");
        map.put("info","法人");
        listSpinnerLegal.add(map);
        map = new HashMap();
        map.put("id","02");
        map.put("info","负责人");
        listSpinnerLegal.add(map);
        map = new HashMap();
        map.put("id","03");
        map.put("info","业主");
        listSpinnerLegal.add(map);

        spinnerTypeLegal.setAdapter(new SimpleAdapter(this, listSpinnerLegal, R.layout.spinner_item, new String[]{"info", "id"},
                new int[]{R.id.spinnerText, R.id.spinnerText1}));
        spinnerTypeLegal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fadingdaibiaorenType_Str = listSpinnerLegal.get(position).get("id");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        youjifou.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                String youjif = radioButton.getText().toString();
                if (youjif.equals("是")) {
                    youjifouStr = 1 + "";//是否邮寄
                    youjiLin.setVisibility(View.VISIBLE);
                } else {
                    youjifouStr = 0 + "";
                    youjiLin.setVisibility(View.GONE);
                }
            }
        });
        submitType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                String youjif = radioButton.getText().toString();
                if (youjif.equals("是")) {
                    submitType_Str = 1 + "";//是否窗口提交
                    recycleview.setVisibility(View.GONE);
                } else {
                    submitType_Str = 2 + "";
                    recycleview.setVisibility(View.VISIBLE);
                }
            }
        });

        myinfo = (String) SharedPreferencesUtils.getParam(this, Constant.MYINFO, "");
        userType = (String) SharedPreferencesUtils.getParam(this,Constant.USERTYPE,"");

        if (userType.equals("1")){
            if (!TextUtils.isEmpty(myinfo)) {
                Gson gson = new Gson();
                UserBean bean = gson.fromJson(myinfo, UserBean.class);
                username.setText(bean.getData().getUnitname());
                phone.setText(bean.getData().getMobilephone());
                usercode.setText(bean.getData().getIdentityid());
                userID = bean.getData().getId();
            }
        }else if (userType.equals("2")){
            if (!TextUtils.isEmpty(myinfo)) {
                Gson gson = new Gson();
                UserBean bean = gson.fromJson(myinfo, UserBean.class);
                String typeCode = bean.getData().getCertificate_Type();
                if (typeCode.equals("0")){
                    farenTypeText.setText("统一社会信用代码");
                }else if (typeCode.equals("1")){
                    farenTypeText.setText("企业营业执照");
                }else if (typeCode.equals("2")){
                    farenTypeText.setText("组织机构代码");
                }
                userID = bean.getData().getId();
                tongyi.setText(bean.getData().getUnicode());
                qiyeming.setText(bean.getData().getUsername());
                fadingdaibiaoren.setText(bean.getData().getUnitname());
            }
        }

        if (userType.equals("2")){
            personInfo.setVisibility(View.GONE);
            legalInfo.setVisibility(View.VISIBLE);
        }else {
            personInfo.setVisibility(View.VISIBLE);
            legalInfo.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {
        Map map;
//        for (int i = 0;i<5;i++){
//            map = new HashMap();
//            map.put("matename","事项"+i);
//            map.put("filename","");
//            list.add(map);
//        }

//        for (int i = 0;i<5;i++){
//            lists.add(i+"   请注意what条件等等..");
//            listc.add(i+"   请准备what资料等等..");
//        }

        //获取阅读须知数据
        Map map1 = new HashMap();
        map1.put("itemId", itemid);
        controller.getReadIKnow(MyFacesUrl.findConditionsReading, map1);

        if (!TextUtils.isEmpty(userID)) {
            Map map2 = new HashMap();
            map2.put("id", itemid);
            map2.put("userId", userID);
            map2.put("userType", userType);
            LogUtils.i(itemid + "-------------");
            controller.getItemHtml(MyFacesUrl.baseinfo, map2);
        }
    }

    private void addFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //intent.setType(“image/*”);//选择图片
        //intent.setType(“audio/*”); //选择音频
        //intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
        //intent.setType(“video/*;image/*”);//同时选择视频和图片
        intent.setType("*/*");//无类型限制
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, Constant.REQUEST_CODE_SELECTFILE);
    }

    @Override
    protected void initEvent() {
        back.requestFocus();
    }

    @OnClick({R.id.back, R.id.submit,R.id.date})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.submit:
                if (userType.equals("2")){
                    if (judgeLegal()) {
                        submitData();
                    }
                }else {
                    if (judgePerson()) {
                        submitData();
                    }
                }

                break;
            case R.id.date:
                getDate();
                date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker arg0, int year, int month, int day) {
                                String m = ""+(++month);
                                String d = ""+day;
                                if (++month<10){
                                    m = "0"+m;
                                }
                                if (day<10){
                                    d = "0"+day;
                                }
                                date.setText(year+"-"+m+"-"+d);//将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                            }
                        };
                        DatePickerDialog dialog=new DatePickerDialog(OnlineDoActivity.this, 0,listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                        dialog.show();
                    }
                });
                break;
        }
    }

    //获取当前日期
    private void getDate() {
        cal= Calendar.getInstance();
        year=cal.get(Calendar.YEAR);       //获取年月日时分秒
        Log.i("wxy","year"+year);
        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 验证参数是否符合规范
     *
     * @return
     */
    private boolean judgePerson() {
        valuesStr = "";
        StringBuffer values = new StringBuffer();
        Map map = new HashMap();
        String js = "";
        String formids = "{\"formid\":";
        String formidText = formids + "\"" + formid + "\",";

        for (int i = 0; i < baiscInfoBeans.size(); i++) {
            String text = baiscInfoBeans.get(i).getText();

            if (TextUtils.isEmpty(text)) {
                showToast("提交表单不能为空");
                return false;
            }

            String name = baiscInfoBeans.get(i).getName();
            map.put(name, text);

            text = "\"" + text + "\"";
            name = "\"" + name + "\"";
            js = name + ":" + text;

            values.append(js + ",");
        }

        LogUtils.i(map.toString());
        values.insert(0, formidText);
        valuesStr = values.toString();
        valuesStr = values.substring(0, values.length() - 1) + "}";

        LogUtils.i(valuesStr);

        usernameStr = username.getText().toString();
        usercodeStr = usercode.getText().toString();
        emailStr = email.getText().toString();
        phoneStr = phone.getText().toString();
        addressStr = address.getText().toString();
        shoujianren_nameStr = shoujianrnName.getText().toString();
        shoujianren_dianhuaStr = shoujianrnPhone.getText().toString();
        youji_dizhiStr = shoujianrnAddress.getText().toString();

        //判断是不是窗口提交
        if (submitType_Str.equals("2")){
            if (listPath.size() != listcl.size()) {
                showToast("请添加申请材料");
                return false;
            }
        }

        if (TextUtils.isEmpty(usernameStr)) {
            showToast("请输入姓名");
            return false;
        }
        if (TextUtils.isEmpty(usercodeStr)) {
            showToast("请输入证件号码");
            return false;
        }
        if (!Validator.isPhone(phoneStr)) {
            showToast("手机号码格式不正确");
            return false;
        }
        if (!Validator.isEmail(emailStr)) {
            showToast("电子邮箱格式不正确");
            return false;
        }
        if (TextUtils.isEmpty(addressStr)) {
            showToast("请输入联系地址");
            return false;
        }
        if (youjifouStr.equals("")) {
            showToast("请选择是否邮寄");
            return false;
        } else if (youjifouStr.equals("1")) {
            if (TextUtils.isEmpty(shoujianren_nameStr)) {
                showToast("请输入收件人名称");
                return false;
            }
            if (TextUtils.isEmpty(shoujianren_dianhuaStr)) {
                showToast("请输入收件人联系电话");
                return false;
            }
            if (TextUtils.isEmpty(youji_dizhiStr)) {
                showToast("请输入邮寄地址");
                return false;
            }
        }
        return true;
    }

    /**
     * 验证参数是否符合规范
     *
     * @return
     */
    private boolean judgeLegal() {
        valuesStr = "";
        StringBuffer values = new StringBuffer();
        Map map = new HashMap();
        String js = "";
        String formids = "{\"formid\":";
        String formidText = formids + "\"" + formid + "\",";

        for (int i = 0; i < baiscInfoBeans.size(); i++) {
            String text = baiscInfoBeans.get(i).getText();

            if (TextUtils.isEmpty(text)) {
                showToast("提交表单不能为空");
                return false;
            }

            String name = baiscInfoBeans.get(i).getName();
            map.put(name, text);

            text = "\"" + text + "\"";
            name = "\"" + name + "\"";
            js = name + ":" + text;

            values.append(js + ",");
        }

        LogUtils.i(map.toString());
        values.insert(0, formidText);
        valuesStr = values.toString();
        valuesStr = values.substring(0, values.length() - 1) + "}";

        LogUtils.i(valuesStr);

        shoujianren_nameStr = shoujianrnName.getText().toString();
        shoujianren_dianhuaStr = shoujianrnPhone.getText().toString();
        youji_dizhiStr = shoujianrnAddress.getText().toString();

        qiyemingcheng_Str = qiyeming.getText().toString();
        fadingdaibiaoren_Str = fadingdaibiaoren.getText().toString();
        farenzhengjianCode_Str = farenzhengjianCode.getText().toString();
        jingbanren_Str = jingbanren.getText().toString();
        jingbanrendianhua_Str = jingbanphone.getText().toString();
        dengjizhucedate_Str = date.getText().toString();
        qiyeTypeCode_Str = qiyeTypeCode.getText().toString();
        qiyeTypeName_Str = qiyeTypeName.getText().toString();
        farenzhengjianName_Str = farenzhengjianname.getText().toString();
        jingyingshengchanfanwei_Str = jingyingshengchan.getText().toString();
        danweizhucedizhi_Str = danweizhucedizhi.getText().toString();
        lianxiren_Str = lianxiren.getText().toString();
        lianxidianhua_Str = lianxidianhua.getText().toString();

        if (dengjizhucedate_Str.equals("选择日期")){
            showToast("请选择登记日期");
            return false;
        }

        if (TextUtils.isEmpty(jingbanrendianhua_Str)||TextUtils.isEmpty(qiyeTypeCode_Str)||TextUtils.isEmpty(farenzhengjianCode_Str)
                ||TextUtils.isEmpty(farenzhengjianName_Str)||TextUtils.isEmpty(qiyeTypeCode_Str)||TextUtils.isEmpty(danweizhucedizhi_Str)){
            showToast("必填选项不能为空");
            return false;
        }

        //判断是不是窗口提交
        if (submitType_Str.equals("2")){
            if (listPath.size() != listcl.size()) {
                showToast("请添加申请材料");
                return false;
            }
        }
        if (!Validator.isPhone(jingbanrendianhua_Str)) {
            showToast("手机号码格式不正确");
            return false;
        }

        if (qiyeTypeCode_Str.length()>18){
            showToast("企业类别代码长度不能超过18个字符");
            return false;
        }

        if (farenzhengjianCode.length()>18){
            showToast("法人证件号码长度不能超过18个字符");
            return false;
        }

        if (farenzhengjianCode.length()<6){
            showToast("法人证件号码长度不能小于6个字符");
            return false;
        }

        if (youjifouStr.equals("")) {
            showToast("请选择是否邮寄");
            return false;
        } else if (youjifouStr.equals("1")) {
            if (TextUtils.isEmpty(shoujianren_nameStr)) {
                showToast("请输入收件人名称");
                return false;
            }
            if (TextUtils.isEmpty(shoujianren_dianhuaStr)) {
                showToast("请输入收件人联系电话");
                return false;
            }
            if (TextUtils.isEmpty(youji_dizhiStr)) {
                showToast("请输入邮寄地址");
                return false;
            }
        }
        return true;
    }
    private String flowId = "";
    /**
     * 提交
     */
    private void submitData() {
        OkHttpClient client = new OkHttpClient();
        dialog = showLoading();

        Map map = new HashMap();
        if (userType.equals("2")){
            map.put("userName", qiyemingcheng_Str);
            map.put("unitName", fadingdaibiaoren_Str);
            map.put("unifiedCode", farenzhengjianCode_Str);
            map.put("agents", jingbanren_Str);
            map.put("agentPhone", jingbanrendianhua_Str);
            map.put("dateOfRegistration", dengjizhucedate_Str);
            map.put("enterpriseClassCode", qiyeTypeCode_Str);
            map.put("nameOfEnterpriseCategory", qiyeTypeName_Str);
            map.put("nameOfLegalPerson", farenzhengjianName_Str);
            map.put("scopeOfBusiness", jingyingshengchanfanwei_Str);
            map.put("registeredAddress", danweizhucedizhi_Str);
            map.put("theContact", lianxiren_Str);
            map.put("contactPhoneNumber", lianxidianhua_Str);
        }else {
            map.put("userName", usernameStr);
            map.put("mobilePhone", phoneStr);
            map.put("email", emailStr);
            map.put("papersSerial", usercodeStr);
            map.put("address", addressStr);
            map.put("papersType", spinnerStr);
        }

        List<Map<String, String>> newListPath = new ArrayList<>();

        if (submitType_Str.equals("2")){
            newListPath.addAll(listPath);
        }

        map.put("submitType", submitType_Str);
        map.put("userType", userType);
        map.put("userId", userID);
        map.put("itemId", itemid);
        map.put("itemName", itemName);
        //邮寄参数
        map.put("mailaddress", youji_dizhiStr);
        map.put("recipientname", shoujianren_nameStr);
        map.put("recipientphone", shoujianren_dianhuaStr);
        map.put("mailtype", youjifouStr);

        //动态表单数据
        map.put("value", valuesStr);
        map.put("form_id", formid);

//        client.newCall(UniversalMethod.getRequest(MyFacesUrl.onlineDo,null)).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, final IOException e) {
//                Log.i("xxx", "1、连接失败============" + e);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (dialog != null) {
//                            dialog.dismiss();
//                        }
//                        showToast("失败，" + e.toString());
//                    }
//                });
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String result = response.body().string();
////                LogUtils.i("-------"+result);
//                try {
//                    JSONObject jsonObject = new JSONObject(result);
//                    flowId = jsonObject.getString("data");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                if (response.isSuccessful()) {
//                    runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    if (dialog != null) {
//                                        dialog.dismiss();
//                                    }
//                                    Map map1 = new HashMap();
//                                    map1.put("itemId",itemid);
//                                    map1.put("flowId",flowId);
//                                    map1.put("userId",userID);
//                                    LogUtils.i(map1.toString()+"------");
//                                    showAlertDialogPop(lin,map1);
//                                }
//                            });
//                }
//            }
//        });
    }

    private void showAlertDialogPop(View v, final Map map){
        WindowManager manager = getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;

        View upView = LayoutInflater.from(this).inflate(R.layout.alertdialog_pop, null);
        TextView subcontent = upView.findViewById(R.id.subcontent);
        TextView cancel = upView.findViewById(R.id.cancel);
        TextView submit = upView.findViewById(R.id.submit);
        LinearLayout lin = upView.findViewById(R.id.Lin);
        subcontent.setText("资料已经暂存，您确定进一步提交吗？");
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData(map);
                popupWindow.dismiss();
            }
        });

        ViewGroup.LayoutParams params = lin.getLayoutParams();
        params.width = width-200;
        params.height = width-500;

        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(upView)
                .setAnimationStyle(R.anim.push_right_in)
                .setBackGroundLevel(0.3f)
                .setWidthAndHeight(width-200, width-500)
                .setOutsideTouchable(true)
                .create();

        popupWindow.showAtLocation(v, Gravity.CENTER,0,0);
    }

    private void updateData(final Map map){
        showLoading();
        LogUtils.i(map.toString()+"------");
        MyOkHttpClient.getNetClient().postCallNet(MyFacesUrl.permitSave, map, new MyCallBack() {
            @Override
            public void onSuccess(String json) {
               runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (dialog!=null){
                            dialog.dismiss();
                        }
                        Toast.makeText(OnlineDoActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }

            @Override
            public void onFailure(int code) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (dialog!=null){
                            dialog.dismiss();
                        }
                        Toast.makeText(OnlineDoActivity.this,"服务器异常,请重试",Toast.LENGTH_SHORT).show();
                        Map map1 = new HashMap();
                        map1.put("itemId",itemid);
                        map1.put("flowId",flowId);
                        map1.put("userId",userID);
                        LogUtils.i(map1.toString()+"------");
                        showAlertDialogPop(lin,map1);
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String path = "";

        if (requestCode == Constant.REQUEST_CODE_SELECTFILE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            if ("file".equalsIgnoreCase(uri.getScheme())) {//使用第三方应用打开
                path = uri.getPath();
                showLog("111111111111----" + path);
            } else {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {//4.4以后
                    path = UniversalMethod.getPath(this, uri);
                    showLog("22222222222----" + path);
                } else {//4.4以下下系统调用方法
                    path = UniversalMethod.getRealPathFromURI(uri, this);
                    showLog("33333333333----" + path);
                }
            }
            Map mapPath = null;
            if (listPath.size() != 0) {
                int pos = 20000;
                for (int i = 0; i < listPath.size(); i++) {
                    mapPath = listPath.get(i);
                    pos = (int) mapPath.get("pos");
                    showLog(".0000000000" + mapPath.toString());
                    if (pos == position) {
                        break;
                    }
                }
                if (pos == position) {
                    mapPath.put("filepath", path);
                    mapPath.put("pos", position);
                } else {
                    mapPath = new HashMap();
                    mapPath.put("filepath", path);
                    mapPath.put("pos", position);
                    listPath.add(mapPath);
                }
            } else {
                mapPath = new HashMap();
                mapPath.put("filepath", path);
                mapPath.put("pos", position);
                listPath.add(mapPath);
            }

            int endFileIndex = path.lastIndexOf("/");
            String fileName = path.substring(endFileIndex + 1);

            if (position != 10000) {
                Map map = listcl.get(position);
                map.put("filename", fileName);
                adapter.notifyDataSetChanged();
            }
        }
    }


    private void showIReadPop(String appRequirement) {
        WindowManager manager = getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;

        View upView = LayoutInflater.from(this).inflate(R.layout.onlinedo_pop, null);
        TextView iread = upView.findViewById(R.id.iread);
        TextView text_tiaojian = upView.findViewById(R.id.text_tiaojian);
        RecyclerView recyclerView_c = upView.findViewById(R.id.recycleview_c);
        LinearLayout popLin = upView.findViewById(R.id.popLin);
        ViewGroup.LayoutParams params = popLin.getLayoutParams();
        text_tiaojian.setText(appRequirement);
        params.width = width - 200;
        if (params.height > height - 200) {
            params.height = height - 200;
        }

        LinearLayoutManager managerc = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView_c.setLayoutManager(managerc);

        OnlinePopAdapter popAdapterc = new OnlinePopAdapter(this, listc);
        recyclerView_c.setAdapter(popAdapterc);

        showLog("---------------" + width + "-----" + height);

        iread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

        if (lin != null) {
            popupWindow = new CommonPopupWindow.Builder(this)
                    .setView(upView)
                    .setAnimationStyle(R.anim.push_right_in)
                    .setBackGroundLevel(0.3f)
//                .setWidthAndHeight(width-100, ViewGroup.LayoutParams.WRAP_CONTENT)
                    .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                    .setOutsideTouchable(false)
                    .create();
            popupWindow.showAtLocation(lin, Gravity.CENTER, 0, 0);
        }
    }

    @Override
    public void readIKnowSuccess(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject data = jsonObject.getJSONObject("data");
            String code = jsonObject.getString("code");
            if (code.equals("200")) {
                JSONArray clmcList = data.getJSONArray("clmcList");
                for (int i = 0; i < clmcList.length(); i++) {
                    listc.add(clmcList.getJSONObject(i).getString("materialName"));
                }
                JSONObject itemd = data.getJSONObject("itemd");
                final String appRequirement = itemd.getString("appRequirement");

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showIReadPop(appRequirement);
                    }
                }, 4000);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readIKnowFail(int code) {
    }

    @Override
    public void getItemInfoSuccess(String json) {

    }

    @Override
    public void getItemInfoFail(int code) {

    }

    @Override
    public void getItemHtmlDataSuccess(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String code = jsonObject.getString("code");
            if (code.equals("200")) {
                JSONObject data = jsonObject.getJSONObject("data");
                JSONArray clmcList = data.getJSONArray("clmcList");
                JSONArray dataList = data.getJSONArray("data");
                JSONArray papersTypes = data.getJSONArray("papersTypes");
                formid = data.getString("form_id");
                Map maps;
                for (int i = 0; i < papersTypes.length(); i++) {
                    maps = new HashMap();
                    maps.put("itemType", papersTypes.getJSONObject(i).getString("itemType"));
                    maps.put("typeName", papersTypes.getJSONObject(i).getString("typename"));
                    list_zjlx.add(maps);
                }
//转换html数据
//                Document doc = Jsoup.parse(data.getString("html"));
//                try {
//                    table1(doc);
//                } catch (IndexOutOfBoundsException e) {
//                    e.printStackTrace();
//                }

                BaiscInfoBean bean;
                for (int i = 0; i < dataList.length(); i++) {
                    bean = new BaiscInfoBean();
                    String leipiplugins = dataList.getJSONObject(i).getString("leipiplugins");
                    if (leipiplugins.equals("radios")) {
                        JSONArray options = dataList.getJSONObject(i).getJSONArray("options");
                        List<String> ops = new ArrayList<>();
                        for (int j = 0; j < options.length(); j++) {
                            ops.add(options.getJSONObject(j).getString("value"));
                        }
                        bean.setOptionsList(ops);
                    } else if (leipiplugins.equals("select")) {
                        JSONArray options = dataList.getJSONObject(i).getJSONArray("options");
                        List<String> ops = new ArrayList<>();
                        for (int j = 0; j < options.length(); j++) {
                            ops.add(options.getJSONObject(j).getString("value"));
                        }
                        bean.setOptionsList(ops);
                    }
                    bean.setLeipiplugins(leipiplugins);
                    bean.setName(dataList.getJSONObject(i).getString("name"));
                    bean.setTitle(dataList.getJSONObject(i).getString("title"));
                    baiscInfoBeans.add(bean);
                }

                basicRecyclerView();

                Map map;
                for (int i = 0; i < clmcList.length(); i++) {
                    map = new HashMap();
                    map.put("matename", clmcList.getJSONObject(i).getString("materialName"));
                    map.put("filename", "");
                    listcl.add(map);
                }
                manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                };

                recycleview.addItemDecoration(new SpaceItemDecoration(1));
                recycleview.setLayoutManager(manager);
                adapter = new OnlineDoAdapter(this, listcl);
                recycleview.setAdapter(adapter);

                adapter.setOnFileClick(new OnlineDoAdapter.AddFileOnClick() {
                    @Override
                    public void setFileOnClick(int pos) {
                        position = pos;
                        addFile();
                    }
                });

                spinnerType.setAdapter(new SimpleAdapter(this, list_zjlx, R.layout.spinner_item, new String[]{"typeName", "itemType"}, new int[]{R.id.spinnerText, R.id.spinnerText1}));

                spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        spinnerStr = list_zjlx.get(position).get("itemType");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            } else if (code.equals("300")) {
                lin = null;
                showToast("此事项暂不支持在线办理");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void basicRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        recycleview_basic.setLayoutManager(manager);
        OnlineDoBasicAdapter adapter = new OnlineDoBasicAdapter(this, baiscInfoBeans);
        recycleview_basic.setAdapter(adapter);
    }

    @Override
    public void getItemHtmlDataFail(int code) {
        showToast("服务器异常");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        lin = null;
        super.onDestroy();
    }
}
