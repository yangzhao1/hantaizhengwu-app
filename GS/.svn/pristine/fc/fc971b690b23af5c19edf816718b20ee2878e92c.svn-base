package com.example.gs.gonser.govenmentservice.baseadpater;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.utils.MyRatingBar;

/**
 * Created by Administrator on 2017/9/15.
 */

public class ViewHolder {
    private SparseArray<View> viewHolder;
    private View view;
    public static  ViewHolder getViewHolder(View view){
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        return viewHolder;
    }
    private ViewHolder(View view) {
        this.view = view;
        viewHolder = new SparseArray<View>();
        view.setTag(viewHolder);
    }
    public <T extends View> T get(int id) {
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }

    public View getConvertView() {
        return view;
    }

    public TextView getTextView(int id) {
        return get(id);
    }

    public RecyclerView getRecyclerViewItem(int id) {
        return get(id);
    }

    public RadioButton getRadioButton(int id) {
        return get(id);
    }

    public RadioGroup getRadioGroup(int id) {
        return get(id);
    }

    public Spinner getSpinner(int id) {

        return get(id);
    }

    public LinearLayout getLinearLayout(int id) {

        return get(id);
    }

    public MyRatingBar getRatingBar(int id) {

        return get(id);
    }

    public EditText getEditText(int id) {

        return get(id);
    }
    public Button getButton(int id) {

        return get(id);
    }

    public ImageView getImageView(int id) {
        return get(id);
    }

    public void setTextView(int  id,CharSequence charSequence){
        getTextView(id).setText(charSequence);
    }
}
