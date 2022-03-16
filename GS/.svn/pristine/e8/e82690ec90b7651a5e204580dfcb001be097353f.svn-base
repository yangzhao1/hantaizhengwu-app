package com.example.gs.gonser.govenmentservice.ui;

import android.os.Bundle;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.adapter.DepartPopAdapter;
import com.example.gs.gonser.govenmentservice.adapter.OnlinePopAdapter;
import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.bean.Constant;
import com.example.gs.gonser.govenmentservice.bean.DepartmentBean;
import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.bean.ThingListBean;
import com.example.gs.gonser.govenmentservice.bean.UserBean;
import com.example.gs.gonser.govenmentservice.controller.ConsultController;
import com.example.gs.gonser.govenmentservice.interfaces.IConsultRefreshUI;
import com.example.gs.gonser.govenmentservice.interfaces.IRefreshUI;
import com.example.gs.gonser.govenmentservice.popup.CommonPopupWindow;
import com.example.gs.gonser.govenmentservice.utils.SharedPreferencesUtils;
import com.example.gs.gonser.govenmentservice.utils.Validator;
import com.google.gson.Gson;

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
 * 我要咨询
 */

public class GoConsultActivity extends BaseActivity implements IConsultRefreshUI {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.titletool)
    TextView titletool;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.useremail)
    EditText useremail;
    @BindView(R.id.department)
    TextView department;
    @BindView(R.id.item)
    TextView item;
    @BindView(R.id.extitle)
    EditText extitle;
    @BindView(R.id.excontent)
    EditText excontent;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.lin)
    LinearLayout lin;
    private String titleText = "我要咨询";

    private CommonPopupWindow popupWindow;
    private List<DepartmentBean.DepartData> list_depart = new ArrayList();
    //这里复用了部门实体类
    private List<DepartmentBean.DepartData> list_item = new ArrayList();
    private ConsultController consultController;
    private String username_str,phone_str,usermail_str,department_str,item_str,extitle_str,excontent_str;
    private String depid = "";//部门id
    private String itemid = "";//事项id
    private String userid = "";//用户id
    private String comp = "0";//0是咨询。1是投诉
    private String complaints = "flow";//flow是投诉。item是咨询

    @Override
    protected int getLayout() {
        return R.layout.goconsult_main;
    }

    @Override
    protected void initView() {
        String myin_json = (String) SharedPreferencesUtils.getParam(this, Constant.MYINFO,"");
        Gson gson = new Gson();
        UserBean bean = gson.fromJson(myin_json, UserBean.class);
        String userType = bean.getData().getUsertype();
        if (userType.equals("1")){
            userid = bean.getData().getId();
            username.setText(bean.getData().getUsername());
            phone.setText(bean.getData().getMobilephone());
            useremail.setText(bean.getData().getEmail());
        }else {
            userid = bean.getData().getId();
            username.setText(bean.getData().getUsername());
            phone.setText(bean.getData().getMobilephone());
        }

        comp = getIntent().getStringExtra("comp");
        if (comp.equals("0")){
            titletool.setText(titleText);
            complaints = "item";
        }else {
            titletool.setText("我要投诉");
            complaints = "flow";
        }
    }

    @Override
    protected void initData() {
        consultController = new ConsultController(this,this);
//        for (int i = 0;i<15;i++){
//            list_depart.add(i+"   研发部门xxxx");
//            list_item.add(i+"   政务服务事项xxx");
//        }
    }

    @Override
    protected void initEvent() {

    }

    @OnClick({R.id.back, R.id.department, R.id.item, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.department:
                consultController.getDepartment(MyFacesUrl.departmentList,null);
                break;
            case R.id.item:
                if (department.getText().toString().equals("请选择部门")){
                    showToast("您必须先选择部门");
                }else {
                    Map map = new HashMap();
                    map.put("deptid",depid);
                    consultController.getItem(MyFacesUrl.deptItems,map);
                }
                break;
            case R.id.submit:
                if (judge()){
                    Map map = new HashMap();
                    map.put("complaints",complaints);
                    map.put("userid",userid);
                    map.put("username",username_str);
                    map.put("phone",phone_str);
                    map.put("email",usermail_str);
                    map.put("departid",depid);
                    map.put("departname",department_str);
                    map.put("itemid",itemid);
                    map.put("itemname",item_str);
                    map.put("title",extitle_str);
                    map.put("content",excontent_str);
                    showLog("咨询投诉提交内容-----"+map.toString());
                    consultController.postSubmit(MyFacesUrl.consultSubmit,map);
                }
                break;
        }
    }

    private boolean judge(){
        username_str = username.getText().toString();
        phone_str = phone.getText().toString();
        usermail_str = useremail.getText().toString();
        department_str = department.getText().toString();
        item_str = item.getText().toString();
        extitle_str = extitle.getText().toString();
        excontent_str = excontent.getText().toString();
        if (TextUtils.isEmpty(username_str)){
            showToast("请正确填写姓名");
            return false;
        }

        if (TextUtils.isEmpty(phone_str)){
            showToast("请正确填写手机号码");
            return false;
        }
        if (!Validator.isPhone(phone_str)){
            showToast("电话号码格式不正确");
            return false;
        }
        if (TextUtils.isEmpty(usermail_str)){
            showToast("请正确填写邮箱地址");
            return false;
        }
        if (!Validator.isEmail(usermail_str)){
            showToast("电子邮箱格式不正确");
            return false;
        }

        if (department_str.equals("请选择部门")){
            showToast("您必须先选择部门");
            return false;
        }

        if (item_str.equals("请选择事项")){
            showToast("您必须选择事项");
            return false;
        }
        if (TextUtils.isEmpty(extitle_str)){
            showToast("请正确填写内容标题");
            return false;
        }
        if (TextUtils.isEmpty(excontent_str)){
            showToast("请正确填写内容");
            return false;
        }
        return true;
    }

    private void showDepartPop(final List<DepartmentBean.DepartData> list_departs){
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
        DepartPopAdapter popAdapters = new DepartPopAdapter(this,list_departs);
        recyclerView.setAdapter(popAdapters);
        popAdapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                department.setText(list_departs.get(position).getDepartname());
                depid = list_departs.get(position).getId();
                itemid="";
                item.setText("请选择事项");
                popupWindow.dismiss();
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

        popupWindow.showAtLocation(lin, Gravity.CENTER,0,0);
    }

    private void showItemPop(final List<DepartmentBean.DepartData> list_items){
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
        DepartPopAdapter popAdapters = new DepartPopAdapter(this,list_items);
        recyclerView.setAdapter(popAdapters);
        popAdapters.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item.setText(list_items.get(position).getDepartname());
                itemid = list_items.get(position).getId();
                popupWindow.dismiss();
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

        popupWindow.showAtLocation(lin, Gravity.CENTER,0,0);
    }

    @Override
    public void departSuccess(String json) {
        list_depart.clear();
        Gson gson = new Gson();
        DepartmentBean bean = gson.fromJson(json, DepartmentBean.class);
        list_depart = bean.getData();
        showDepartPop(list_depart);
    }

    @Override
    public void departFail(int code) {
        showToast("服务器异常");
    }

    @Override
    public void itemSuccess(String json) {
        list_item.clear();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray data = jsonObject.getJSONArray("data");
            String code = jsonObject.getString("code");
            if (code.equals("200")) {
                DepartmentBean.DepartData bean;
                for (int i = 0; i < data.length(); i++) {
                    bean = new DepartmentBean.DepartData();
                    bean.setId(data.getJSONObject(i).getString("id"));
                    bean.setDepartname(data.getJSONObject(i).getString("itemName"));
                    list_item.add(bean);
                }
                if (list_item.size()==0){
                    showToast("暂无事项");
                }else {
                    showItemPop(list_item);
                }
            }else {
                showToast("服务器异常");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void itemFail(int code) {
        showToast("服务器异常");
    }

    @Override
    public void submitSuccess(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String code = jsonObject.getString("code");
            if (code.equals("200")) {
                if (complaints.equals("flow")){
                    showToast("投诉成功");
                }else {
                    showToast("咨询提交成功");
                }
                finish();
            }else {
                showToast("服务器异常");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void submitFail(int code) {
        showToast("服务器异常");
    }
}
