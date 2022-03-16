package com.example.gs.gonser.govenmentservice.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.baseadpater.AutoRVAdapter;
import com.example.gs.gonser.govenmentservice.baseadpater.ViewHolder;
import com.example.gs.gonser.govenmentservice.bean.SpMaterialInformation;
import com.example.gs.gonser.govenmentservice.bean.SpUpload;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yangzhao on 2017/9/15.
 * 适配器，在线办理修改材料
 */

public class OnlineUpdateWebAdapter extends AutoRVAdapter {
    public List<?> list;
    private Context context;

    public OnlineUpdateWebAdapter(Context context, List<?> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.onlinedoweb_recycler_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        SpMaterialInformation spMaterialInformation = (SpMaterialInformation) list.get(position);
        TextView matename = holder.getTextView(R.id.matename);
        TextView addFile = holder.getTextView(R.id.addFile);
        RecyclerView recyclerViewItem = holder.getRecyclerViewItem(R.id.recyclerViewItem);

        recyclerViewItem.setLayoutManager(new LinearLayoutManager(context));
        List<SpUpload> spUploads = spMaterialInformation.getSpUploads();

        if (spUploads!=null){
            ItemAdapter itemAdapter = new ItemAdapter(context,spUploads);
            recyclerViewItem.setAdapter(itemAdapter);
        }else {
            spUploads = new ArrayList<>();
            ItemAdapter itemAdapter = new ItemAdapter(context,spUploads);
            recyclerViewItem.setAdapter(itemAdapter);
        }

        addFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFileOnClick.setFileOnClick(position);
            }
        });

        matename.setText((position+1)+".材料名称："+spMaterialInformation.getMaterialName().toString());

    }

    public interface AddFileOnClick{
        void setFileOnClick(int pos);
    }

    private AddFileOnClick addFileOnClick;

    public void setOnFileClick(AddFileOnClick addFileOnClick){
        this.addFileOnClick = addFileOnClick;
    }

    public interface DeleteFileOnClick{
        void setDeleteOnClick(int pos);
    }

    private DeleteFileOnClick deleteFileOnClick;

    public void setOnDeleteFileOnClick(DeleteFileOnClick deleteFileOnClick){
        this.deleteFileOnClick = deleteFileOnClick;
    }

    public interface UploadFileOnClick{
        void setUploadOnClick(int pos);
    }
    private UploadFileOnClick uploadFileOnClick;

    public void setOnUploadFileOnClick(UploadFileOnClick uploadFileOnClick){
        this.uploadFileOnClick = uploadFileOnClick;
    }
}
