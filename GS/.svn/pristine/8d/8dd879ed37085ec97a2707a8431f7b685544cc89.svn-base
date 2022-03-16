package com.example.gs.gonser.govenmentservice.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.bean.Constant;
import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.bean.UserBean;
import com.example.gs.gonser.govenmentservice.controller.BeInCommController;
import com.example.gs.gonser.govenmentservice.interfaces.IRefreshUI;
import com.example.gs.gonser.govenmentservice.utils.SharedPreferencesUtils;
import com.example.gs.gonser.govenmentservice.utils.Validator;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yangzhao on 2018/5/14.
 */

public class ChangePasswordActivity extends BaseActivity implements IRefreshUI {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.titletool)
    TextView titletool;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.ps_image)
    ImageView psImage;
    @BindView(R.id.newpassword1)
    EditText newpassword1;
    @BindView(R.id.newpassword2)
    EditText newpassword2;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.lin)
    LinearLayout lin;
    private String titleText = "修改密码";
    private String sps_s,ps1_s,ps2_s;
    private String mypassword = "";
    private BeInCommController controller = null;
    private String info = "";
    private boolean isps_show = false;//是否显示密码

    @Override
    protected int getLayout() {
        return R.layout.changeps_main;
    }

    @Override
    protected void initView() {
        titletool.setText(titleText);
    }

    @Override
    protected void initData() {
        mypassword = (String) SharedPreferencesUtils.getParam(this,Constant.PASSWORD,"");
        controller = new BeInCommController(this,this);
        info = (String) SharedPreferencesUtils.getParam(this,Constant.MYINFO,"");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void success(String message, String json) {
        try {
            JSONObject object = new JSONObject(json);
            String code = object.getString("code");
            if (code.equals("200")){
                showToast("修改成功");
                SharedPreferencesUtils.setParam(this,Constant.PASSWORD,newpassword1.getText().toString());
                finish();
            }else if (code.equals("300")){
                showToast(object.getString("message"));
            }else {
                showToast("服务器异常，修改失败");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fails(String message, int code) {
        showToast("服务器异常，修改失败");
    }

    @OnClick({R.id.back, R.id.ps_image, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.ps_image://密码的显示和隐藏
                if (isps_show){
                    psImage.setImageResource(R.mipmap.seeps_gray);
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    newpassword1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    newpassword2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    password.setSelection(password.getText().toString().length());
                    newpassword1.setSelection(newpassword1.getText().toString().length());
                    newpassword2.setSelection(newpassword2.getText().toString().length());
                    isps_show=false;
                }else {
                    psImage.setImageResource(R.mipmap.nosee_ps_gray);
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    newpassword1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    newpassword2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    password.setSelection(password.getText().toString().length());
                    newpassword1.setSelection(newpassword1.getText().toString().length());
                    newpassword2.setSelection(newpassword2.getText().toString().length());
                    isps_show=true;
                }
                break;
            case R.id.submit:
                sps_s = password.getText().toString();
                ps1_s = newpassword1.getText().toString();
                ps2_s = newpassword2.getText().toString();

                if (TextUtils.isEmpty(sps_s)){
                    showToast("请输入旧密码");
                }else if (TextUtils.isEmpty(ps1_s)){
                    showToast("请输入新密码");
                }else if (TextUtils.isEmpty(ps2_s)){
                    showToast("请再次输入新密码");
                }else if (!ps1_s.equals(ps2_s)){
                    showToast("新的密码两次输入不一致");
                }else if (!Validator.isPassword(sps_s)||!Validator.isPassword(ps1_s)){
                    showToast("密码必须由数字和字母组成");
                }else {
                    if (!TextUtils.isEmpty(info)){
                        UserBean bean = new Gson().fromJson(info,UserBean.class);
                        Map map = new HashMap();
                        map.put("id",bean.getData().getId());
                        map.put("username",bean.getData().getOfficeId());
                        map.put("password",ps1_s);
                        map.put("oldpassword",sps_s);
                        controller.postData(MyFacesUrl.updatePassword,map);
                    }else {
                        showToast("服务器异常");
                    }
                }
                break;
        }
    }
}
