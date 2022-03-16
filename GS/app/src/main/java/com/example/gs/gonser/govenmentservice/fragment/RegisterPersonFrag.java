package com.example.gs.gonser.govenmentservice.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.base.BaseFragment;
import com.example.gs.gonser.govenmentservice.controller.RegisterController;
import com.example.gs.gonser.govenmentservice.interfaces.IRefreshUI;
import com.example.gs.gonser.govenmentservice.utils.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by Administrator on 2017/9/12.
 * 注册个人
 */

public class RegisterPersonFrag extends BaseFragment implements IRefreshUI{

    @BindView(R.id.loginname)
    EditText loginname;
    @BindView(R.id.usercode)
    EditText usercode;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password1)
    EditText password1;
    @BindView(R.id.password2)
    EditText password2;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.register)
    TextView register;

    private RegisterController registerController;

    @Override
    protected View getLayout(LayoutInflater inflater, ViewGroup container) {
        if (mview == null) {
            mview = inflater.inflate(R.layout.registerperson_main, container, false);
        }
        ViewGroup parent = (ViewGroup) mview.getParent();
        if (parent != null) {
            parent.removeView(mview);
        }
        return mview;
    }

    @Override
    protected void initView() {
        registerController = new RegisterController(this,this);
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
        String email_str = email.getText().toString();
        String usercode_str = usercode.getText().toString();
        String username_str = username.getText().toString();
        if (TextUtils.isEmpty(password1_str)||TextUtils.isEmpty(password2_str)||TextUtils.isEmpty(phone_str)
                ||TextUtils.isEmpty(usercode_str)||TextUtils.isEmpty(username_str)){
            showToast("请正确填写必填项");
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

        if (!Validator.isChinese(username_str)){
            showToast("用户名必须是1-9个汉字");
            return false;
        }

        if (password1_str.length()<6){
            showToast("密码长度不能少于6位字符");
            return false;
        }
        if (password1_str.length()>16){
            showToast("密码长度不能大于16位字符");
            return false;
        }
        if (!password1_str.equals(password2_str)){
            showToast("两次输入密码不一致");
            return false;
        }

        if (!Validator.isPassword(password1_str)){
            showToast("密码必须是数字和字母组合");
            return false;
        }

        if (!Validator.isPhone(phone_str)){
            showToast("手机号码格式不正确");
            return false;
        }

        if (!TextUtils.isEmpty(loginname_str)){
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
        }

        if (!TextUtils.isEmpty(email_str)){
            if (!Validator.isEmail(email_str)){
                showToast("邮箱格式不正确");
                return false;
            }
        }

        return true;
    }

    @OnClick(R.id.register)
    public void onViewClicked() {
        if (Judge()){
            Map<String,String> map = new HashMap<>();
            map.put("officeid",loginname.getText().toString());
            map.put("password",password1.getText().toString());
            map.put("username",username.getText().toString());
            map.put("unitname",username.getText().toString());
            map.put("identityid",usercode.getText().toString());
            map.put("mobilephone",phone.getText().toString());
            map.put("email",email.getText().toString());
            map.put("usertype","1");
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
