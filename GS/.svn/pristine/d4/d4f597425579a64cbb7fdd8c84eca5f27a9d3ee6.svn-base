package com.example.gs.gonser.govenmentservice.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.gs.gonser.govenmentservice.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yangzhao on 2018/5/5.
 */

public abstract class BaseFragment extends Fragment {

    protected View mview;
    Unbinder unbinder;
    public Dialog dialog = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getLayout(inflater,container);
        unbinder = ButterKnife.bind(this,view);
        initView();
        initData();
        initEvent();
        return view;
    }

    protected abstract View getLayout(LayoutInflater inflater,ViewGroup container);

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initEvent();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void showToast(String text){
        Toast.makeText(getContext(),text,Toast.LENGTH_SHORT).show();
    }

    /**
     * 得到自定义的progressDialog
     * @return
     */
    public Dialog showLoading() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.progressbar, null);// 得到加载view
//		LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局

        Dialog loadingDialog = new Dialog(getContext(), R.style.CustomDialog);// 创建自定义样式dialog
        loadingDialog.setCancelable(true);// 不可以用“返回键”取消
        loadingDialog.setContentView(v,new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT));// 设置布局

        loadingDialog.show();
        dialog = loadingDialog;
        return loadingDialog;
    }


}
