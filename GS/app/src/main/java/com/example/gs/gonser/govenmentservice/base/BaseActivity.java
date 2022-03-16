package com.example.gs.gonser.govenmentservice.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gs.gonser.govenmentservice.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Silas on 2016/10/7.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public Dialog dialog = null;
    //分页的总页数
    public int totalPage = 1;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        unbinder = ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }
    protected abstract int getLayout();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initEvent();

    public void showToast(String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    /**
     * 得到自定义的progressDialog
     * @return
     */
    public Dialog showLoading() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.progressbar, null);// 得到加载view
//		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局

        Dialog loadingDialog = new Dialog(this, R.style.CustomDialog);// 创建自定义样式dialog
        loadingDialog.setCancelable(true);// 可以用“返回键”取消
        loadingDialog.setCanceledOnTouchOutside(false);// 不可以点击外部取消

        loadingDialog.setContentView(v,new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));// 设置布局

        loadingDialog.show();
        dialog = loadingDialog;
        return loadingDialog;
    }

    public Dialog showLoading(String content) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.progressbar, null);// 得到加载view
//		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局

        TextView textView = v.findViewById(R.id.content);
        textView.setText(content);
        Dialog loadingDialog = new Dialog(this, R.style.CustomDialog);// 创建自定义样式dialog
        loadingDialog.setCancelable(true);// 可以用“返回键”取消
        loadingDialog.setCanceledOnTouchOutside(false);// 不可以点击外部取消

        loadingDialog.setContentView(v,new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));// 设置布局

        loadingDialog.show();
        dialog = loadingDialog;
        return loadingDialog;
    }

    public void showLog(String message){
        Log.i(""+getLocalClassName()+"=========    ",message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
