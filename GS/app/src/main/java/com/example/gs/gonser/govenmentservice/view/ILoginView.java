package com.example.gs.gonser.govenmentservice.view;

import android.view.View;

/**
 * Created by yangzhao on 2018/5/5.
 */

public interface ILoginView {
    String getUsername();
    String getPassword();

    void initView();
    void setViewClickListener(View.OnClickListener onclikLister);

    void onUsernameEmpty();   // 用户名为空时的显示操作
    void onPasswordEmpty();   // 密码为空时的显示操作
    void onLoginSuccess();   // 登陆成功时的显示操作
    void onLoginFail();   // 密码失败时的显示操作
}
