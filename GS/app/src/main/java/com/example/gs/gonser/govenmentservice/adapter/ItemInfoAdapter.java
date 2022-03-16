package com.example.gs.gonser.govenmentservice.adapter;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.baseadpater.AutoRVAdapter;
import com.example.gs.gonser.govenmentservice.baseadpater.ViewHolder;
import com.example.gs.gonser.govenmentservice.bean.ApplyMaterBean;
import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.bean.ThingListBean;
import com.example.gs.gonser.govenmentservice.utils.FileDownloadManager;
import com.example.gs.gonser.govenmentservice.utils.LogUtils;

import java.io.File;
import java.util.List;

/**
 * Created by yangzhao on 2017/9/15.
 * 适配器，事项详情申请材料
 */

public class ItemInfoAdapter extends AutoRVAdapter {
    public List<?> list;
    private Context context;

    public ItemInfoAdapter(Context context, List<?> list) {
        super(context, list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.iteminfo_recycler_item;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ApplyMaterBean bean = (ApplyMaterBean) list.get(position);
        TextView matename = holder.getTextView(R.id.matename);
        TextView sampleform = holder.getTextView(R.id.sampleform);
        TextView emptyform = holder.getTextView(R.id.emptyform);

        /**
         * http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg
         * http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg
         * http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg
         * http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg
         */

        sampleform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sampleDownLoadFile.setDownLoadFile(position);
//                downFile(bean.getSampleUrl(),"示例表格");
//                downFile("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg","示例表格");
            }
        });

        emptyform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emptyDownLoadFile.setDownLoadFile(position);
//                downFile(bean.getEmptyUrl(),"空白表格");
            }
        });

        matename.setText((position+1)+ ".  材料名称：" + bean.getMaterName());
    }

    public interface SampleDownLoadFile{
        void setDownLoadFile(int pos);
    }

    private SampleDownLoadFile sampleDownLoadFile;

    public void sampleDownFile(SampleDownLoadFile sampleDownLoadFile){
        this.sampleDownLoadFile = sampleDownLoadFile;
    }

    public interface EmptyDownLoadFile{
        void setDownLoadFile(int pos);
    }
    private EmptyDownLoadFile emptyDownLoadFile;

    public void emptyDownFile(EmptyDownLoadFile emptyDownLoadFile){
        this.emptyDownLoadFile = emptyDownLoadFile;
    }

    public interface onLong{
        void getaLong();
    }

    private long aLong;

    public long getaLong(){
        return aLong;
    }

    public void setaLong(long aLong){
        this.aLong = aLong;
    }
}
