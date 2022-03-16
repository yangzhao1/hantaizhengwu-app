package com.example.gs.gonser.govenmentservice.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.baseadpater.AutoRVAdapter;
import com.example.gs.gonser.govenmentservice.baseadpater.ViewHolder;
import com.example.gs.gonser.govenmentservice.bean.DepartmentBean;

import java.util.List;

/**
 * Created by yangzhao on 2017/9/15.
 * 适配器，事项详情材料二级列表POP
 */

public class ItemInfoCLPopAdapter extends AutoRVAdapter {
    public List<?> list;
    private Context context;
    private String materName;

    public ItemInfoCLPopAdapter(Context context, List<?> list,String materName) {
        super(context, list);
        this.list = list;
        this.context = context;
        this.materName = materName;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.downloadfile_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        TextView text = holder.getTextView(R.id.text);
        final TextView download = holder.getTextView(R.id.download);
        text.setText(materName);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downFile.startDownLoad(position);
            }
        });
    }

    public interface DownFile{
        void startDownLoad(int pos);
    }

    private DownFile downFile;
    public void setOnDownLoad(DownFile downLoad){
        this.downFile = downLoad;
    }
}
