package com.example.gs.gonser.govenmentservice.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.base.BaseActivity;
import com.example.gs.gonser.govenmentservice.bean.Constant;
import com.example.gs.gonser.govenmentservice.bean.MyFacesUrl;
import com.example.gs.gonser.govenmentservice.bean.UserBean;
import com.example.gs.gonser.govenmentservice.popup.CommonPopupWindow;
import com.example.gs.gonser.govenmentservice.utils.SharedPreferencesUtils;
import com.example.gs.gonser.govenmentservice.utils.Validator;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by yangzhao on 2018/5/11.
 */

public class MyInfoActivity extends BaseActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.titletool)
    TextView titletool;
    @BindView(R.id.headimage)
    ImageView headimage;
    @BindView(R.id.headimageRel)
    RelativeLayout headimageRel;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.usercode)
    TextView usercode;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.loginname)
    TextView loginname;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.zhengjianType_legal)
    TextView zhengjianTypeLegal;
    @BindView(R.id.loginname_legal)
    TextView loginnameLegal;
    @BindView(R.id.userzhengjiancode)
    TextView userzhengjiancode;
    @BindView(R.id.qiyeming)
    TextView qiyeming;
    @BindView(R.id.personLin)
    LinearLayout personLin;
    @BindView(R.id.fadingusername_legal)
    EditText fadingusernameLegal;
    @BindView(R.id.fadingusercode_legal)
    EditText fadingusercodeLegal;
    @BindView(R.id.fadingphone_legal)
    EditText fadingphoneLegal;
    @BindView(R.id.legalLin)
    LinearLayout legalLin;

    private String titleText = "????????????";
    private CommonPopupWindow commonPopupWindow;
    private Uri userImageUri;
    private final int REQUEST_CODE_GET_PHOTO = 4;
    private final int REQUEST_CODE_CUT_PHOTO = 5;
    private String imagepath = "";
    private CropCircleTransformation cropCircleTransformation;
    UserBean bean = null;
    private String userType = "1";

    @Override
    protected int getLayout() {
        return R.layout.myinfo_main;
    }

    @Override
    protected void initView() {
        cropCircleTransformation = new CropCircleTransformation(new LruBitmapPool((int) (Runtime.getRuntime().maxMemory() / 4)));

        titletool.setText(titleText);

        String infoJson = (String) SharedPreferencesUtils.getParam(this, Constant.MYINFO, "");
        userType = (String) SharedPreferencesUtils.getParam(this, Constant.USERTYPE, "1");

        if (userType.equals("1")) {
            personLin.setVisibility(View.VISIBLE);
            legalLin.setVisibility(View.GONE);
            if (!TextUtils.isEmpty(infoJson)) {
                Gson gson = new Gson();
                bean = gson.fromJson(infoJson, UserBean.class);
                username.setText(bean.getData().getUsername());
                loginname.setText(bean.getData().getOfficeId());
                usercode.setText(Validator.idFormat(bean.getData().getIdentityid()));
                phone.setText(bean.getData().getMobilephone());
                email.setText(bean.getData().getEmail());
                Glide.with(this)
                        .load(bean.getData().getUrl())
                        .bitmapTransform(cropCircleTransformation)
                        .placeholder(R.mipmap.user_defalut)
                        .into(headimage);
            }
        } else if (userType.equals("2")) {
            personLin.setVisibility(View.GONE);
            legalLin.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(infoJson)) {
                Gson gson = new Gson();
                bean = gson.fromJson(infoJson, UserBean.class);
                String typeCode = bean.getData().getCertificate_Type();

                if (typeCode.equals("0")) {
                    zhengjianTypeLegal.setText("????????????????????????");
                } else if (typeCode.equals("1")) {
                    zhengjianTypeLegal.setText("??????????????????");
                } else if (typeCode.equals("2")) {
                    zhengjianTypeLegal.setText("??????????????????");
                }

                loginnameLegal.setText(bean.getData().getOfficeId());
                userzhengjiancode.setText(Validator.idFormat(bean.getData().getUnicode()));
                qiyeming.setText(bean.getData().getUsername());
                fadingusernameLegal.setText(bean.getData().getUnitname());
                fadingusercodeLegal.setText(Validator.idFormat(bean.getData().getFidentityId()));
                fadingphoneLegal.setText(bean.getData().getMobilephone());

                Glide.with(this)
                        .load(bean.getData().getUrl())
                        .bitmapTransform(cropCircleTransformation)
                        .placeholder(R.mipmap.user_defalut)
                        .into(headimage);
            }
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @OnClick({R.id.back, R.id.headimageRel, R.id.submit, R.id.phone, R.id.email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.headimageRel:
                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE_GET_PHOTO);
                break;
            case R.id.phone:
                updateDataPop(phone, "phone");
                break;
            case R.id.email:
                updateDataPop(email, "email");
                break;
            case R.id.submit:
                if (bean != null) {
                    showLoading();
                    submitData();
                } else {
                    showToast("????????????");
                }
                break;
        }
    }

    private void submitData() {
        OkHttpClient client = new OkHttpClient();

        RequestBody fileBody = null;
        String fileName = "";
        RequestBody multipartBody = null;

        if (!imagepath.equals("")) {
            File file = null;
            try {
                file = new File(new URI(imagepath));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            showLog(imagepath);
            fileBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
            int endFileIndex = imagepath.lastIndexOf("/");
            fileName = imagepath.substring(endFileIndex + 1);

            if (userType.equals("1")) {
                multipartBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("coverpicfile", fileName, fileBody)
                        .addFormDataPart("id", bean.getData().getId())
                        .addFormDataPart("mobilephone", phone.getText().toString())
                        .addFormDataPart("email", email.getText().toString())
                        .build();
            } else {

                multipartBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("coverpicfile", fileName, fileBody)
                        .addFormDataPart("id", bean.getData().getId())
                        .addFormDataPart("mobilephone", fadingphoneLegal.getText().toString())
                        .addFormDataPart("unitname", fadingusernameLegal.getText().toString())
                        .addFormDataPart("fidentityId", fadingusercodeLegal.getText().toString())
                        .build();
            }
        } else {
            if (userType.equals("1")) {
                multipartBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("coverpicfile", "")
                        .addFormDataPart("id", bean.getData().getId())
                        .addFormDataPart("mobilephone", phone.getText().toString())
                        .addFormDataPart("email", email.getText().toString())
                        .build();
            } else {
                multipartBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("coverpicfile", "")
                        .addFormDataPart("id", bean.getData().getId())
                        .addFormDataPart("mobilephone", fadingphoneLegal.getText().toString())
                        .addFormDataPart("unitname", fadingusernameLegal.getText().toString())
                        .addFormDataPart("fidentityId", fadingusercodeLegal.getText().toString())
                        .build();
            }

        }
        Request request = new Request.Builder().url(MyFacesUrl.update)
                .post(multipartBody)//?????????????????????????????????????????????????????????
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {

                Log.i("xxx", "1???????????????============" + e);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                        showToast("???????????????" + e.toString());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

//                Log.i("xxx","1??????????????????"+response.body().string());
                final String result = response.body().string();
                if (response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (dialog != null) {
                                    dialog.dismiss();
                                }
                                JSONObject jsonObject = new JSONObject(result);
                                String code = jsonObject.getString("code");
                                if (code.equals("200")) {
                                    SharedPreferencesUtils.setParam(MyInfoActivity.this, Constant.MYINFO, result);
                                    showToast("????????????");
                                    setResult(RESULT_OK);
                                    finish();
                                } else if (code.equals("300")) {
                                    showToast("????????????");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        showLog(resultCode + "     ");

        switch (requestCode) {
            case REQUEST_CODE_GET_PHOTO:
                if (data != null) {
//                    startPhotoZoom(data.getData(), 100);
                    reSizeImage(data.getData());
                }
//                showLog(data.toString());
                break;
            case REQUEST_CODE_CUT_PHOTO:
                if (data != null) {
//                    setPicToViews(data);
                    imagepath = userImageUri.toString();
                    File file = null;
                    try {
                        file = new File(new URI(imagepath));
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    Glide.with(this)
                            .load(file)
                            .skipMemoryCache(true)   //??????????????????
                            .diskCacheStrategy(DiskCacheStrategy.NONE)   //????????? disk???????????????
                            .bitmapTransform(cropCircleTransformation)
                            .placeholder(R.mipmap.ic_launcher)
                            .into(headimage);
                    showLog("----------??????----------" + imagepath);
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    /**
     * ??????????????????
     *
     * @param textView
     */
    private void updateDataPop(final TextView textView, final String myphone) {
        submit.setClickable(true);

        View upView = LayoutInflater.from(this).inflate(R.layout.updatetext_pop, null);
        final EditText text = upView.findViewById(R.id.text);
        TextView complaint = upView.findViewById(R.id.comlaint);
        text.setText(textView.getText().toString());
        text.setSelection(textView.getText().toString().length());

        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text_s = text.getText().toString();
                if (!TextUtils.isEmpty(text_s)) {
                    if (myphone.equals("phone")) {
                        if (text_s.length() != 11) {
                            showToast("??????????????????????????????");
                        } else {
                            if (!Validator.isPhone(text_s)) {
                                showToast("??????????????????????????????");
                            } else {
                                textView.setText(text_s);
                                commonPopupWindow.dismiss();
                            }
                        }
                    } else if (myphone.equals("email")) {
                        if (!Validator.isEmail(text_s)) {
                            showToast("??????????????????????????????");
                        } else {
                            textView.setText(text_s);
                            commonPopupWindow.dismiss();
                        }
                    } else {
                        textView.setText(text_s);
                        commonPopupWindow.dismiss();
                    }
                } else {
                    showToast("???????????????????????????");
                }
            }
        });

        commonPopupWindow = new CommonPopupWindow.Builder(this)
                .setView(upView)
                .setBackGroundLevel(0.5f)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .create();

        commonPopupWindow.showAtLocation(personLin, Gravity.CENTER, 0, -300);
    }

    private void reSizeImage(Uri uri) {
        //???????????????????????????
        //??????????????????????????????????????????????????????????????????
        File outputImage = new File(Environment.getExternalStorageDirectory(), "crop.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        userImageUri = Uri.fromFile(outputImage);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image");
        intent.setDataAndType(uri, "image/*");
        // ????????????crop=true?????????????????????Intent??????????????????VIEW?????????
        intent.putExtra("crop", true);
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);// ?????????
        // aspectX aspectY ??????????????????
        intent.putExtra("aspectX", 1);//?????????X???????????????
        intent.putExtra("aspectY", 1);
        // outputX outputY ?????????????????????????????????????????????????????????????????????
        intent.putExtra("outputX", 500);//??????X???????????????
        intent.putExtra("outputY", 500);
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", false);//????????????????????????
        /**
         * ????????????????????????????????????????????????????????????160px????????????
         * ?????????????????????Uri??????????????????Uri?????????Bitmap????????????????????????miui????????????return data?????????
         */
//        intent.putExtra("return-data", true);
//        intent.putExtra("output", Uri.fromFile(new File("/mnt/sdcard/temp")));//????????????
//        userImageUri = Uri.parse("file:///"+ Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, userImageUri);
//        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        showLog("reSizeImage() called with: " + "uri = [" + userImageUri + "]");
        startActivityForResult(intent, REQUEST_CODE_CUT_PHOTO);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
