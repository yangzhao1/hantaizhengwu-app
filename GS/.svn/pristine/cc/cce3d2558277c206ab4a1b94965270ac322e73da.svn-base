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

/**
 * Created by yangzhao on 2017/9/15.
 * 适配器，我的办件材料列表
 */

public class MyDoThingInfoAdapter extends AutoRVAdapter {
    public List<?> list;
    private Context context;

    public MyDoThingInfoAdapter(Context context, List<?> list) {
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
        TextView filename = holder.getTextView(R.id.filename);
        TextView addFile = holder.getTextView(R.id.addFile);
        RecyclerView recyclerViewItem = holder.getRecyclerViewItem(R.id.recyclerViewItem);
        addFile.setVisibility(View.GONE);

        recyclerViewItem.setLayoutManager(new LinearLayoutManager(context));
        List<SpUpload> spUploads = spMaterialInformation.getSpUploads();

        if (spUploads!=null){
            MyDoThingItemAdapter itemAdapter = new MyDoThingItemAdapter(context,spUploads,spMaterialInformation.getMaterialName());
            recyclerViewItem.setAdapter(itemAdapter);
        }else {
            spUploads = new ArrayList<>();
            MyDoThingItemAdapter itemAdapter = new MyDoThingItemAdapter(context,spUploads,spMaterialInformation.getMaterialName());
            recyclerViewItem.setAdapter(itemAdapter);
        }

        addFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFileOnClick.setFileOnClick(position);
            }
        });

        matename.setText((position+1)+".材料名称："+spMaterialInformation.getMaterialName().toString());
//        String fileName = "";
//        if (spUploads.size()!=0){
//            if (spUploads.size()==1){
//                SpUpload spUpload = spUploads.get(0);
//                fileName = spUpload.getNewName();
//            }else {
//                for (int i=0;i<spUploads.size();i++){
//                    SpUpload spUpload = spUploads.get(i);
//                    if (fileName.equals("")){
//                        fileName = spUpload.getNewName();
//                    }else {
//                        fileName = fileName +",\n\n" +spUpload.getNewName();
//                    }
//                }
//            }
//        }
//        filename.setText(fileName);
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
