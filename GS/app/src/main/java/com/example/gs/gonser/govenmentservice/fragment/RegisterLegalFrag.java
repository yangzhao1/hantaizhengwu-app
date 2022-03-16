package com.example.gs.gonser.govenmentservice.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.base.BaseFragment;
import com.example.gs.gonser.govenmentservice.controller.RegisterController;
import com.example.gs.gonser.govenmentservice.interfaces.IRefreshUI;
import com.example.gs.gonser.govenmentservice.utils.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by Administrator on 2017/9/12.
 * 注册法人
 */

public class RegisterLegalFrag extends BaseFragment implements IRefreshUI{

    @BindView(R.id.loginname)
    EditText loginname;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.userzhengjiancode)
    EditText userzhengjiancode;
    @BindView(R.id.password1)
    EditText password1;
    @BindView(R.id.password2)
    EditText password2;
    @BindView(R.id.qiyename)
    EditText qiyename;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.usercode)
    EditText usercode;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.register)
    TextView register;
    private RegisterController registerController;
    private List<Map<String,String>> list = new ArrayList<>();
    private String spinnerStr = "0";
    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container) {
        if (mview == null) {
            mview = inflater.inflate(R.layout.registerlegal_main, container, false);
        }
        ViewGroup parent = (ViewGroup) mview.getParent();
        if (parent != null) {
            parent.removeView(mview);
        }
        return mview;
    }

    @Override
    protected void initView() {
        Map map = new HashMap();
        map.put("id","0");
        map.put("info","统一社会信用代码");
        list.add(map);

        Map map1 = new HashMap();
        map1.put("id","1");
        map1.put("info","企业营业执照");
        list.add(map1);

        Map map2 = new HashMap();
        map2.put("id","2");
        map2.put("info","组织机构代码");
        list.add(map2);
        registerController = new RegisterController(this,this);

        spinner.setAdapter(new SimpleAdapter(getContext(),list,R.layout.spinner_item,new String[]{"info","id"},new int[]{R.id.spinnerText,R.id.spinnerText1}));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerStr = list.get(position).get("id");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    private boolean Judge(){
        String loginname_str = loginname.getText().toString();
        String password1_str = password1.getText().toString();
        String password2_str = password2.getText().toString();
        String phone_str = phone.getText().toString();
        String usercode_str = usercode.getText().toString();
        String username_str = username.getText().toString();

        String qiyeming_str = qiyename.getText().toString();
        String userzhengjiancode_str = userzhengjiancode.getText().toString();
        if (TextUtils.isEmpty(loginname_str)||TextUtils.isEmpty(password1_str)||TextUtils.isEmpty(password2_str)||TextUtils.isEmpty(phone_str)
                ||TextUtils.isEmpty(usercode_str)||TextUtils.isEmpty(username_str)||TextUtils.isEmpty(qiyeming_str)
                ||TextUtils.isEmpty(userzhengjiancode_str)||TextUtils.isEmpty(spinnerStr)){
            showToast("请正确填写必填项");
            return false;
        }
        if (spinnerStr.equals("")){
            showToast("请选择证件类型");
            return false;
        }

        if (spinnerStr.equals("0")){
            if (!Validator.isLicense18(userzhengjiancode_str)){
                showToast("不是有效的统一社会信用代码");
                return false;
            }
        }else if (spinnerStr.equals("1")){
            if (userzhengjiancode_str.length()!=18&&userzhengjiancode_str.length()!=15){
                showToast("企业营业执照长度错误");
                return false;
            }
        }else if (spinnerStr.equals("2")){
            if (!Validator.isZuZhiCode(userzhengjiancode_str)){
                showToast("不是有效的组织机构代码");
                return false;
            }
        }

        if (password1_str.length()<6){
            showToast("密码长度不能少于6位字符");
            return false;
        }
        if (password1_str.length()>16){
            showToast("密码长度不能大于16位字符");
            return false;
        }

        String pat = "[0-9]+";
        if (password1_str.matches(pat)){
            showToast("密码不能是纯数字");
            return false;
        }

        if (!password1_str.equals(password2_str)){
            showToast("两次输入密码不一致");
            return false;
        }

        if (!Validator.isPassword(password1_str)){
            showToast("密码不得不包含特殊字符");
            return false;
        }

        if (TextUtils.isEmpty(qiyeming_str)){
            showToast("企业名称不能为空");
            return false;
        }

        if (!Validator.isFirstNum(loginname_str)){
            showToast("账号首字母必须是英文");
            return false;
        }
        if (loginname_str.length()<6){
            showToast("登录账号不能少于6位字符");
            return false;
        }
        if (loginname_str.length()>16){
            showToast("登录账号不能大于16位字符");
            return false;
        }
        if (!Validator.isLoginName(loginname_str)){
            showToast("账号必须是字母和数字组合");
            return false;
        }
        if (!Validator.isUserName(loginname_str)){
            showToast("登录账号不得包含中文和特殊字符");
            return false;
        }

        if (!Validator.isChinese(username_str)){
            showToast("法定代表人姓名必须是1-9个汉字");
            return false;
        }

//        try {
//            String IDCard = Validator.IDCardValidate(usercode_str);
//            if (!IDCard.equals("")){
//                showToast(IDCard);
//                return false;
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        if (!Validator.validator(usercode_str)){
            showToast("无效身份证号码");
            return false;
        }

        if (!Validator.isPhone(phone_str)){
            showToast("手机号码格式不正确");
            return false;
        }
        return true;
    }

    @OnClick(R.id.register)
    public void onViewClicked() {
        if (Judge()){
            Map<String,String> map = new HashMap<>();
            map.put("certificate_Type",spinnerStr);
            map.put("unicode",userzhengjiancode.getText().toString());
            map.put("officeid",loginname.getText().toString());
            map.put("password",password1.getText().toString());
            map.put("username",qiyename.getText().toString());

            map.put("unitname",username.getText().toString());
            map.put("fidentityId",usercode.getText().toString());
            map.put("mobilephone",phone.getText().toString());
            map.put("usertype","2");
            registerController.postRegisterData(map);
        }
    }

    @Override
    public void success(String message, String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String code = jsonObject.getString("code");
            if (code.equals("200")) {
                showToast("注册成功");
                getActivity().finish();
            }else if (code.equals("300")){
                showToast(jsonObject.getString("message"));
            }else {
                showToast("服务器异常，注册失败");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fails(String message, int code) {
        showToast("服务器异常，注册失败");
    }
}
