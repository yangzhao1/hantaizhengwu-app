package com.example.gs.gonser.govenmentservice.adapter;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.baseadpater.AutoRVAdapter;
import com.example.gs.gonser.govenmentservice.baseadpater.ViewHolder;
import com.example.gs.gonser.govenmentservice.bean.Constant;
import com.example.gs.gonser.govenmentservice.utils.FileDownloadManager;

import java.util.List;
import java.util.Map;

/**
 * Created by yangzhao on 2017/9/15.
 * 适配器，在线办理提交材料
 */

public class OnlineDoAdapter extends AutoRVAdapter {
    public List<?> list;
    private Context context;

    public OnlineDoAdapter(Context context, List<?> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.onlinedo_recycler_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Map map = (Map) list.get(position);
        TextView matename = holder.getTextView(R.id.matename);
        TextView filename = holder.getTextView(R.id.filename);
        TextView addfile = holder.getTextView(R.id.addfile);

        addfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFile();
            }
        });

        addfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFileOnClick.setFileOnClick(position);
            }
        });

        matename.setText((position+1)+".材料名称："+map.get("matename").toString());
        filename.setText("附件名称："+map.get("filename").toString());
    }

    private void addFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //intent.setType(“image/*”);//选择图片
        //intent.setType(“audio/*”); //选择音频
        //intent.setType(“video/*”); //选择视频 （mp4 3gp 是android支持的视频格式）
        //intent.setType(“video/*;image/*”);//同时选择视频和图片
        intent.setType("*/*");//无类型限制
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        ((Activity)context).startActivityForResult(intent, Constant.REQUEST_CODE_SELECTFILE);
    }

    public interface AddFileOnClick{
        void setFileOnClick(int pos);
    }

    private AddFileOnClick addFileOnClick;

    public void setOnFileClick(AddFileOnClick addFileOnClick){
        this.addFileOnClick = addFileOnClick;
    }
}
