package com.example.gs.gonser.govenmentservice.utils;

import android.content.Context;
import android.text.TextUtils;

import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.bean.SpMaterialInformation;
import com.example.gs.gonser.govenmentservice.bean.SpOperationFlow;
import com.example.gs.gonser.govenmentservice.bean.SpUpload;
import com.example.gs.gonser.govenmentservice.interfaces.MyCallBack;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by yangzhao on 2018/8/23.
 */

public class UploadFile{

    String mapToken=null;
    private Context context;
    public UploadFile(Context context) {
        this.context =context;
    }

    public static void getToken(){
        MyOkHttpClient.getNetClient().postCallNet(MyFacesUrl.getToken, null, new MyCallBack() {
            @Override
            public void onSuccess(String json) {

            }

            @Override
            public void onFailure(int code) {

            }
        });
    }

    private void sendFile(final byte b [], final String filename){
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i=0;mapToken==null&&i<100;i++){
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    JSONObject jsonObject = new JSONObject(mapToken);
                    JSONObject data = jsonObject.getJSONObject("data");
                    String token = data.getString("token");
                    boolean success = data.getBoolean("success");
                    String server = data.getString("server");

                    StringBuffer buffer = new StringBuffer();
                    BufferedReader reader = null;
                    try {
                        String param = "";

                        if(success){
                            URL urlObj = new URL(server+"/uploadFile/uploadStream?token="+token+param);
                            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
                            con.setRequestMethod("POST"); // ???????????????,???Post???????????????????????????get??????
                            con.setDoInput(true);
                            con.setDoOutput(true);
                            con.setUseCaches(false); // post????????????????????????
                            // ?????????????????????
                            con.setRequestProperty("Connection", "Keep-Alive");
                            con.setRequestProperty("Charset", "UTF-8");
                            // ????????????
                            String BOUNDARY = "----------" + System.currentTimeMillis();
                            con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);
                            // ??????????????????
                            // ???????????????
                            StringBuilder sb = new StringBuilder();
                            sb.append("--"); // ??????????????????
                            sb.append(BOUNDARY);
                            sb.append("\r\n");
                            sb.append("Content-Disposition: form-data;name=\"file\";filename=\""+ filename + "\"\r\n");
                            sb.append("Content-Type:application/octet-stream\r\n\r\n");
                            byte[] head = sb.toString().getBytes("utf-8");
                            // ???????????????
                            OutputStream out = new DataOutputStream(con.getOutputStream());
                            // ????????????
                            out.write(head);

                            //????????????
                            out.write(b, 0, b.length - 1);

                            // ????????????
                            byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// ???????????????????????????
                            out.write(foot);
                            out.flush();
                            out.close();


                            /**
                             * ???????????? ????????????
                             */

                            // ??????BufferedReader??????????????????URL?????????
                            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                            String line = null;
                            while ((line = reader.readLine()) != null) {
                                buffer.append(line);
                            }
                            String result = buffer.toString();
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
            }
        });
    }


    /**
     * ??????Request??????
     * @param url
     * @param filePath ?????????????????????
     * @return
     */

    public static Request getRequestCL(String url, String filePath,String token) {
        Request.Builder builder = new Request.Builder();
        builder.url(url).post(getRequestBodyCL(filePath,token));
        return builder.build();
    }

    /**
     * ??????????????????????????????????????????RequestBody
     * @param filePath ?????????????????????
     * @return
     */
    public static RequestBody getRequestBodyCL(String filePath,String token) {
        //??????MultipartBody.Builder??????????????????????????????
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        LogUtils.i("????????????--------"+filePath);
        builder.addFormDataPart("token",token);

        if (TextUtils.isEmpty(filePath)){
            builder.addFormDataPart("file","");
        }else {
            File file = new File(filePath);
            builder.addFormDataPart( //???Builder?????????????????????
                    "file",  //???????????????
                    file.getName(), //?????????????????????????????????????????????
                    RequestBody.create(MediaType.parse("*/*"), file) //??????RequestBody???????????????????????????
            );
                //?????????????????????????????????????????????
//            String fileType = getMimeType(file.getName());
        }
        return builder.build(); //??????Builder????????????
    }



}
