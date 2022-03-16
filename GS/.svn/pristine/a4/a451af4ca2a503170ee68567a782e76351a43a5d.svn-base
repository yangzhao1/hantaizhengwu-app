package com.example.gs.gonser.govenmentservice.ui;

import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.bean.Constant;
import com.example.gs.gonser.govenmentservice.bean.UserBean;
import com.example.gs.gonser.govenmentservice.controller.LoginController;
import com.example.gs.gonser.govenmentservice.fragment.MineFragment;
import com.example.gs.gonser.govenmentservice.interfaces.IRefreshUI;
import com.example.gs.gonser.govenmentservice.utils.SharedPreferencesUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by yangzhao on 2018/5/5.
 */

public class LoginActivity extends BaseActivity implements IRefreshUI {

    @BindView(R.id.loginname)
    EditText loginname;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.remember_pw)
    CheckBox rememberPw;
    @BindView(R.id.forget_pw)
    TextView forgetPw;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.comein)
    TextView comein;
    @BindView(R.id.lin)
    LinearLayout lin;
    @BindView(R.id.ps_image)
    ImageView ps_image;

    private LoginController controller;
    private boolean isps_show = true;//是否显示密码

    @Override
    protected int getLayout() {
        return R.layout.login;
    }

    @Override
    protected void initView() {
        controller = new LoginController(this,this);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void success(String message,String json) {
//        showToast(message);
        String code = "";
        JSONObject object = null;
        try {
            object = new JSONObject(json);
            code = object.getString("code");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (code.equals("200")){
            Gson gson = new Gson();
            UserBean bean = gson.fromJson(json,UserBean.class);
            SharedPreferencesUtils.setParam(this, Constant.MYINFO,gson.toJson(bean));
            SharedPreferencesUtils.setParam(this, Constant.PASSWORD,password.getText().toString());
            SharedPreferencesUtils.setParam(this, Constant.USERID,bean.getData().getId());
            SharedPreferencesUtils.setParam(this, Constant.USERTYPE,bean.getData().getUsertype());
            showToast("登录成功");
            String email = bean.getData().getEmail();
            showLog(email);
            setResult(RESULT_OK);
            finish();
        }else {
            try {
                showToast(object.getString("message"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void fails(String message,int code) {
        showToast(message);
        showLog(message);
    }

    @OnClick({R.id.forget_pw, R.id.login, R.id.register,R.id.ps_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.forget_pw:
                break;
            case R.id.login:
                if (Judge()){
                    controller.postLoginData(loginname.getText().toString(),password.getText().toString());
                }
                break;
            case R.id.register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.ps_image:
                if (isps_show){
                    ps_image.setImageResource(R.mipmap.eye_open);
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    password.setSelection(password.getText().toString().length());
                    isps_show=false;
                }else {
                    ps_image.setImageResource(R.mipmap.eye_close);
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    password.setSelection(password.getText().toString().length());
                    isps_show=true;
                }
                break;
        }
    }

    private boolean Judge(){
        String username = loginname.getText().toString();
        String passwords = password.getText().toString();
        if (TextUtils.isEmpty(username)||TextUtils.isEmpty(passwords)){
            showToast("请填写用户名和密码");
            return false;
        }
        return true;
    }
}
