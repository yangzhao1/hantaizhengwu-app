package com.example.gs.gonser.govenmentservice.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.baseadpater.AutoRVAdapter;
import com.example.gs.gonser.govenmentservice.baseadpater.ViewHolder;
import com.example.gs.gonser.govenmentservice.bean.SpUpload;

import java.util.List;

/**
 * Created by yangzhao on 2018/8/22.
 */

public class ItemAdapter extends AutoRVAdapter {
    private List<?> list;
    private Context context;
    public ItemAdapter(Context context, List<?> list) {
        super(context, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.onlinedoitem_recycler;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        TextView fileName = holder.getTextView(R.id.fileName);
        final TextView deleteFile = holder.getTextView(R.id.deleteFile);

        SpUpload bean = (SpUpload) list.get(position);
        fileName.setText(bean.getNewName());
        deleteFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    public interface DeleteFileOnClick{
        void setDeleteOnClick(int pos);
    }

    private DeleteFileOnClick deleteFileOnClick;

    public void setOnDeleteFileOnClick(DeleteFileOnClick deleteFileOnClick){
        this.deleteFileOnClick = deleteFileOnClick;
    }
}
