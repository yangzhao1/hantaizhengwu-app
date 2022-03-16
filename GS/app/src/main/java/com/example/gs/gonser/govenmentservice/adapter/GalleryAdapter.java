package com.example.gs.gonser.govenmentservice.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.bean.HelpTopicImageBean;
import com.example.gs.gonser.govenmentservice.view.MyImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangzhao on 2018/6/19.
 */

public class GalleryAdapter extends BaseAdapter {

    private Context context;

    private ArrayList<MyImageView> imageViews = new ArrayList<MyImageView>();
//    private ArrayList<MyZoomImageView> imageViews = new ArrayList<MyZoomImageView>();

    private GalleryPositionListener positionListener;

    private ArrayList<HelpTopicImageBean>   helpTopicImage = new ArrayList<HelpTopicImageBean>();
    private int position = 0;
//    private ArrayList<Bitmap> bitmaps = new ArrayList<Bitmap>();
    private ArrayList<String> bitmaps = new ArrayList<String>();

    private SimpleTarget target = new SimpleTarget<Bitmap>() {
        @Override
        public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
            //这里我们拿到回掉回来的bitmap，可以加载到我们想使用到的地方

        }
    };

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Bitmap bitmap = (Bitmap) msg.obj;
            Bundle bundle = msg.getData();
            String url = bundle.getString("url");
            for (int i = 0; i < imageViews.size(); i++) {
                if (imageViews.get(i).getTag().equals(url)) {
                    imageViews.get(i).setImageBitmap(bitmap);
                }
            }
        }
    };

    public void setData(List<Integer> data) {
        notifyDataSetChanged();
    }

    public GalleryAdapter(Context context, ArrayList<HelpTopicImageBean>  helpTopicImage, int position) {
        this.context = context;
        this.helpTopicImage = helpTopicImage;
        this.position = position;
        for(int i=0; i<helpTopicImage.size(); i++){

//            Bitmap bitmap = ImageLoader.getInstance().loadImageSync(helpTopicImage.get(i).getUrl(),options);
            bitmaps.add(helpTopicImage.get(i).getUrl());
        }
    }

    @Override
    public int getCount() {
        return helpTopicImage.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyImageView view = new MyImageView(context);
//        MyZoomImageView view = new MyZoomImageView(context);

        view.setLayoutParams(new Gallery.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT));

        if (bitmaps.get(position) != null) {
            Glide.with(context).load(bitmaps.get(position)).placeholder(R.mipmap.conn_defualt).into(view);
        }
        if (!this.imageViews.contains(view)) {
            imageViews.add(view);
        }
        positionListener.movePosition(position);
        return view;
    }

    public void getPositionListener(GalleryPositionListener positionListener) {
        this.positionListener = positionListener;
    }

    public interface GalleryPositionListener{
        public void movePosition(int index);
    }
}
