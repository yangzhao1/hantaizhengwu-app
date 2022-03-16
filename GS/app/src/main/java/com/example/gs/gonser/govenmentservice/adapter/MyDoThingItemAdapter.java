package com.example.gs.gonser.govenmentservice.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.baseadpater.AutoRVAdapter;
import com.example.gs.gonser.govenmentservice.baseadpater.ViewHolder;
import com.example.gs.gonser.govenmentservice.bean.SpUpload;
import com.example.gs.gonser.govenmentservice.utils.FileDownloadManager;
import com.example.gs.gonser.govenmentservice.utils.LogUtils;

import java.io.File;
import java.util.List;

/**
 * Created by yangzhao on 2018/8/22.
 * 我的办件材料详细列表
 */

public class MyDoThingItemAdapter extends AutoRVAdapter {
    private List<?> list;
    private Context context;
    private String materName;

    public MyDoThingItemAdapter(Context context, List<?> list,String materName) {
        super(context, list);
        this.context = context;
        this.list = list;
        this.materName = materName;
    }

    @Override
    public int onCreateViewLayoutID(int viewType) {
        return R.layout.onlinedoitem_recycler;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        TextView fileName = holder.getTextView(R.id.fileName);
        final TextView downFile = holder.getTextView(R.id.deleteFile);
        downFile.setText("下载附件");

        final SpUpload bean = (SpUpload) list.get(position);
        fileName.setText(bean.getNewName());
        downFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            downFile(bean.getUrl(),materName,bean.getNewName());
            }
        });
    }

    private void downFile(String version_url,String title,String fileName) {
        File file = new File("/sdcard/汉台政务APP/"+title);
//        File file = new File("///storage/emulated/0/政务APP/"+title);
        if (!file.exists()){
            file.mkdirs();
        }
//        String new_url = "http://192.168.0.94:8888/sxslfj/" + version_url.substring(0,version_url.length()-1);
//        String new_url = MyFacesUrl.PIC_IP + version_url;
        String new_url = version_url;
        if(new_url.equals("null")||new_url==null){
            Toast.makeText(context,"无效下载地址",Toast.LENGTH_SHORT).show();
        }else {
            LogUtils.i("表格下载url---"+new_url);
//        String new_url = version_url;
//            int endIndex = new_url.lastIndexOf("/");
//            String filename = new_url.substring(endIndex+1);
        String path = "汉台政务APP/"+title;
//            String path = "政务APP/"+title;
//            LogUtils.i("---------------------"+filename);
        File file1 = new File("/sdcard/汉台政务APP/"+title);
//            File file1 = new File("///storage/emulated/0/政务APP/"+title);

            FileDownloadManager fileDownloadManager = FileDownloadManager.getInstance(context);
            long downid = fileDownloadManager.startDownload(new_url,fileName,path,"汉台政务",file1,"dothing");
            String status = fileDownloadManager.getDownloadStatus(downid);
            Toast.makeText(context,status,Toast.LENGTH_SHORT).show();
//        LogUtils.i(downid+"----------");

//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
//        sp.edit().putLong(DownloadManager.EXTRA_DOWNLOAD_ID,downid).commit();
        }

    }

}
