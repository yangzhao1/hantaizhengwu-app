package com.example.gs.gonser.govenmentservice.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;
import com.example.gs.gonser.govenmentservice.bean.Constant;
import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.bean.SpMaterialInformation;
import com.example.gs.gonser.govenmentservice.bean.SpOperationFlow;
import com.example.gs.gonser.govenmentservice.bean.SpUpload;
import com.example.gs.gonser.govenmentservice.interfaces.MyCallBack;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by yangzhao on 2018/5/7.
 * 通用方法封装
 */

public class UniversalMethod {

    /**
     * 配合glide 设置ImageVIew的背景图片
     * @param imageView
     * @return
     */
    public static ViewTarget getViewTarget(ImageView imageView){

        ViewTarget viewTarget = new ViewTarget<View, GlideDrawable>(imageView) {
            //括号里为需要加载的控件
            @Override
            public void onResourceReady(GlideDrawable resource,
                                        GlideAnimation<? super GlideDrawable> glideAnimation) {
                this.view.setBackground(resource.getCurrent());
            }
        };
        return viewTarget;
    }

    /**
     * 获取图片路径
     * @param contentUri
     * @param context
     * @return
     */
    public static String getRealPathFromURI(Uri contentUri,Context context) {
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if(null!=cursor&&cursor.moveToFirst()){;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
            cursor.close();
        }
        return res;
    }

    /**
     * 通过上传的文件的完整路径生成RequestBody
     * @param params 完整的文件路径
     * @return
     */
    public static RequestBody getRequestBody(Map<String,String> params,List<SpMaterialInformation> spMaterialInformationList) {
        //创建MultipartBody.Builder，用于添加请求的数据
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

//        LogUtils.i("上传文件--------"+fileNames);
        LogUtils.i("上传参数--------"+params.toString());

        //参数上传
        //是否邮寄
        if (params.get("mailtype").equals("1")){
            builder.addFormDataPart("mailaddress",params.get("mailaddress"));//邮寄地址
            builder.addFormDataPart("recipientname",params.get("recipientname"));//收件人名称-
            builder.addFormDataPart("recipientphone",params.get("recipientphone"));//收件人电话
        }

        if (params.get("userType").equals("2")){
            builder.addFormDataPart("userName",params.get("userName"));
            builder.addFormDataPart("unitName",params.get("unitName"));
            builder.addFormDataPart("unifiedCode",params.get("unifiedCode"));
            builder.addFormDataPart("agents",params.get("agents"));
            builder.addFormDataPart("agentPhone",params.get("agentPhone"));
            builder.addFormDataPart("dateOfRegistration",params.get("dateOfRegistration"));
            builder.addFormDataPart("enterpriseClassCode",params.get("enterpriseClassCode"));
            builder.addFormDataPart("nameOfEnterpriseCategory",params.get("nameOfEnterpriseCategory"));
            builder.addFormDataPart("nameOfLegalPerson",params.get("nameOfLegalPerson"));
            builder.addFormDataPart("scopeOfBusiness",params.get("scopeOfBusiness"));
            builder.addFormDataPart("registeredAddress",params.get("registeredAddress"));
            builder.addFormDataPart("theContact",params.get("theContact"));
            builder.addFormDataPart("contactPhoneNumber",params.get("contactPhoneNumber"));
        }else {
            builder.addFormDataPart("userName",params.get("userName"));
            builder.addFormDataPart("mobilePhone",params.get("mobilePhone"));
            builder.addFormDataPart("email",params.get("email"));
            builder.addFormDataPart("papersSerial",params.get("papersSerial"));//证件号码
            builder.addFormDataPart("address",params.get("address"));
            builder.addFormDataPart("papersType",params.get("papersType"));//证件类型
        }

        builder.addFormDataPart("mailtype",params.get("mailtype"));//是否邮寄
        builder.addFormDataPart("userId",params.get("userId"));
        builder.addFormDataPart("itemId",params.get("itemId"));
        builder.addFormDataPart("itemName",params.get("itemName"));
        builder.addFormDataPart("userType",params.get("userType"));
//        builder.addFormDataPart("materialName","");//材料名称

        builder.addFormDataPart("value",params.get("value"));//动态表单拼成的json字符串
        builder.addFormDataPart("form_id",params.get("form_id"));//动态表单需要的formid
        builder.addFormDataPart("submitType",params.get("submitType"));//是否窗口提交

        builder.addFormDataPart("flowId",params.get("flowId"));//是否窗口提交

        String submitType = params.get("submitType");
//        SpMaterialInformation spMaterialInformation = null;
//        List<SpUpload> list = null;
//        List<SpMaterialInformation> listInfo = new ArrayList<>();
//        SpUpload spUpload = null;
//        int pos = 100000000;

//        if (submitType.equals("2")){
        //实体类排序
//            Collections.sort(uploadList);

//            for (int i=0;i<uploadList.size();i++){
//                SpUpload upload = uploadList.get(i);
//                int pos1 = upload.getPos();
//
//                if (pos!=pos1&&pos!=100000000){
//                    spMaterialInformation.setSpUploads(list);
//                    listInfo.add(spMaterialInformation);
//                }
//
//                if (pos!=pos1){
//                    spMaterialInformation = new SpMaterialInformation();
//                    list = new ArrayList<>();
//                    spMaterialInformation.setMaterialName(listcl.get(pos1).get("matename"));
//                    list.add(upload);
//                }else {
//                    list.add(upload);
//                }
//                pos = pos1;
//
//                if (i+1==uploadList.size()){
//                    spMaterialInformation.setSpUploads(list);
//                    listInfo.add(spMaterialInformation);
//                }
//            }
        //1表示窗口提交，2表示在线提交
        for (SpMaterialInformation sm:spMaterialInformationList) {
            if (submitType.equals("1")){
                sm.setSpUploads(new ArrayList<SpUpload>());
            }
            sm.setSubmitType(submitType);
        }
        SpOperationFlow spOperationFlow = new SpOperationFlow();
        spOperationFlow.setSpMaterialInformation(spMaterialInformationList);
        Gson gson = new Gson();
        String sp = gson.toJson(spOperationFlow,SpOperationFlow.class);
        builder.addFormDataPart("json",sp);
//        }else {
//            for (int i=0;i<listcl.size();i++){
//                SpUpload upload = new SpUpload();
//                spMaterialInformation = new SpMaterialInformation();
//                list = new ArrayList<>();
//                spMaterialInformation.setMaterialName(listcl.get(i).get("matename"));
//                spMaterialInformation.setSubmitType("1");
//                list.add(upload);
//                spMaterialInformation.setSpUploads(list);
//                listInfo.add(spMaterialInformation);
//            }
//            spOperationFlow.setSpMaterialInformation(listInfo);
//            Gson gson = new Gson();
//            String sp = gson.toJson(spOperationFlow,SpOperationFlow.class);
//            builder.addFormDataPart("json",sp);
//        }


//        if (fileNames.size()==0){
//            builder.addFormDataPart("file","");
//        }else {
//            //多文件上传
//            listInfo = new ArrayList<>();
//
//            for (int i = 0; i < fileNames.size(); i++) { //对文件进行遍历
//                spMaterialInformation = new SpMaterialInformation();
//                spMaterialInformation.setMaterialName(listcl.get(i).get("matename").toString());
//                String filePaths = fileNames.get(i).get("filepath").toString();
//                int last = filePaths.lastIndexOf(",");
//                list = new ArrayList<>();
//                if (last==-1){
//                    File file = new File(filePaths); //生成文件
//
//                    spUpload = new SpUpload();
//                    spUpload.setNewName(file.getName());
//                    spUpload.setOldName(file.getName());
//                    list.add(spUpload);
//
//                    builder.addFormDataPart( //给Builder添加上传的文件
//                            "file",  //请求的名字
//                            file.getName(), //文件的文字，服务器端用来解析的
//                            RequestBody.create(MediaType.parse("*/*"), file) //创建RequestBody，把上传的文件放入
//                    );
//                }else {
//                    String filePathByte [] = filePaths.split(",");
//                    for (int j = 0; j < filePathByte.length; j++){
//                        String filePath = filePathByte[j].trim();
//                        filePath = filePath.replace("\n","");
//                        File file = new File(filePath);
//
//                        spUpload = new SpUpload();
//                        spUpload.setNewName(file.getName());
//                        spUpload.setOldName(file.getName());
//
//                        builder.addFormDataPart( //给Builder添加上传的文件
//                                "file",  //请求的名字
//                                file.getName(), //文件的文字，服务器端用来解析的
//                                RequestBody.create(MediaType.parse("*/*"), file) //创建RequestBody，把上传的文件放入
//                        );
//                        list.add(spUpload);
//                    }
//                }
//
//                spMaterialInformation.setSpUploads(list);
//                listInfo.add(spMaterialInformation);
//                //根据文件的后缀名，获得文件类型
////            String fileType = getMimeType(file.getName());
//            }

//        }

        return builder.build(); //根据Builder创建请求
    }

    /**
     * 通过上传的文件的完整路径生成RequestBody
     * @param fileNames 完整的文件路径
     * @return
     */
    public static RequestBody getRequestBodyCL(List<Map<String,String>> fileNames,List<Map<String,String>> listcl) {
        //创建MultipartBody.Builder，用于添加请求的数据
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        LogUtils.i("上传文件--------"+fileNames);

        SpMaterialInformation spMaterialInformation;
        List<SpUpload> list;
        List<SpMaterialInformation> listInfo = null;
        SpUpload spUpload = null;
        if (fileNames.size()==0){
            builder.addFormDataPart("file","");
        }else {
            //多文件上传
            listInfo = new ArrayList<>();

            for (int i = 0; i < fileNames.size(); i++) { //对文件进行遍历
                spMaterialInformation = new SpMaterialInformation();
                spMaterialInformation.setMaterialName(listcl.get(i).get("matename").toString());
                String filePaths = fileNames.get(i).get("filepath").toString();
                int last = filePaths.lastIndexOf(",");
                list = new ArrayList<>();
                if (last==-1){
                    File file = new File(filePaths); //生成文件

                    spUpload = new SpUpload();
                    spUpload.setNewName(file.getName());
                    spUpload.setOldName(file.getName());
                    list.add(spUpload);

                    builder.addFormDataPart( //给Builder添加上传的文件
                            "file",  //请求的名字
                            file.getName(), //文件的文字，服务器端用来解析的
                            RequestBody.create(MediaType.parse("*/*"), file) //创建RequestBody，把上传的文件放入
                    );
                }else {
                    String filePathByte [] = filePaths.split(",");
                    for (int j = 0; j < filePathByte.length; j++){
                        String filePath = filePathByte[j].trim();
                        filePath = filePath.replace("\n","");
                        File file = new File(filePath);

                        spUpload = new SpUpload();
                        spUpload.setNewName(file.getName());
                        spUpload.setOldName(file.getName());

                        builder.addFormDataPart( //给Builder添加上传的文件
                                "file",  //请求的名字
                                file.getName(), //文件的文字，服务器端用来解析的
                                RequestBody.create(MediaType.parse("*/*"), file) //创建RequestBody，把上传的文件放入
                        );
                        list.add(spUpload);
                    }
                }

                spMaterialInformation.setSpUploads(list);
                listInfo.add(spMaterialInformation);
                //根据文件的后缀名，获得文件类型
//            String fileType = getMimeType(file.getName());
            }
            SpOperationFlow spOperationFlow = new SpOperationFlow();
            spOperationFlow.setSpMaterialInformation(listInfo);
            Gson gson = new Gson();
            String sp = gson.toJson(spOperationFlow,SpOperationFlow.class);
            builder.addFormDataPart("json",sp);
        }

        return builder.build(); //根据Builder创建请求
    }

    /**
     * 获得Request实例
     * @param url
     * @param params 完整的文件路径
     * @return
     */
    public static Request getRequest(String url, Map<String,String> params,List<SpMaterialInformation> spMaterialInformationList) {
        Request.Builder builder = new Request.Builder();
        builder.url(url).post(getRequestBody(params,spMaterialInformationList));
        return builder.build();
    }

    /**
     * 获得Request实例
     * @param url
     * @param fileNames 完整的文件路径
     * @return
     */
    public static Request getRequestCL(String url, List<Map<String,String>> fileNames ,List<Map<String,String>> listcl) {
        Request.Builder builder = new Request.Builder();
        builder.url(url).post(getRequestBodyCL(fileNames,listcl));
        return builder.build();
    }

    private String result = "";

    private void getToken(){

        MyOkHttpClient.getNetClient().postCallNet(MyFacesUrl.getToken, null, new MyCallBack() {
            @Override
            public void onSuccess(String json) {

            }

            @Override
            public void onFailure(int code) {

            }
        });
    }

    /**
     * 连接文件服务器  上传文件
     * @param b
     * @param filename
     * @return
     */
    private String sendFile(final byte b [], final String filename,String json){

        /**
         * 获取令牌和文件服务器地址
         */
//        Map<String,Object> map = UploadUtil.checkToken("SERVER_PATH");
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject data = jsonObject.getJSONObject("data");
            String token = data.getString("token");
            boolean success = data.getBoolean("success");
            String server = data.getString("server");

            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = null;
            try {
                String param = "";
//            /**
//             * 判断是否需要旋转图片
//             */
//            if("1".equals(request.getParameter("rotate"))){
//                param = "&rotate=1&angle=90";
//            }
                if(success){
                    URL urlObj = new URL(server+"/uploadFile/uploadStream?token="+token+param);
                    HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
                    con.setRequestMethod("POST"); // 设置关键值,以Post方式提交表单，默认get方式
                    con.setDoInput(true);
                    con.setDoOutput(true);
                    con.setUseCaches(false); // post方式不能使用缓存
                    // 设置请求头信息
                    con.setRequestProperty("Connection", "Keep-Alive");
                    con.setRequestProperty("Charset", "UTF-8");
                    // 设置边界
                    String BOUNDARY = "----------" + System.currentTimeMillis();
                    con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);
                    // 请求正文信息
                    // 第一部分：
                    StringBuilder sb = new StringBuilder();
                    sb.append("--"); // 必须多两道线
                    sb.append(BOUNDARY);
                    sb.append("\r\n");
                    sb.append("Content-Disposition: form-data;name=\"file\";filename=\""+ filename + "\"\r\n");
                    sb.append("Content-Type:application/octet-stream\r\n\r\n");
                    byte[] head = sb.toString().getBytes("utf-8");
                    // 获得输出流
                    OutputStream out = new DataOutputStream(con.getOutputStream());
                    // 输出表头
                    out.write(head);

                    //发送文件
                    out.write(b, 0, b.length - 1);

                    // 结尾部分
                    byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
                    out.write(foot);
                    out.flush();
                    out.close();


                    /**
                     * 上传完毕 返回信息
                     */

                    // 定义BufferedReader输入流来读取URL的响应
                    reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }
                    result = buffer.toString();
                    JSONObject jb = new JSONObject(result);
                    boolean bool = jsonObject.getBoolean("success");
                    if(bool){
                        result = jsonObject.getString("data");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                }catch (Exception e){
                    e.getStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;

    }


    /**
     * 专为Android4.4设计的从Uri获取文件绝对路径，以前的方法已不好使
     */
    @SuppressLint("NewApi")
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

}
