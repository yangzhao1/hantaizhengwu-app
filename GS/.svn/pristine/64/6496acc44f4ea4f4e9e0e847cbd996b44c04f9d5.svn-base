package com.example.gs.gonser.govenmentservice.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ZHAOBAOSHAN on 2018/3/27.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    int mSpace;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mSpace;
        outRect.right = mSpace;
//        outRect.bottom = mSpace;
//        if (parent.getChildAdapterPosition(view) == 0) {
//            outRect.top = mSpace;
//        }
    }

    public SpaceItemDecoration(int space) {
        this.mSpace = space;
    }
}
