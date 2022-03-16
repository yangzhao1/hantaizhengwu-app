package com.example.gs.gonser.govenmentservice.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.MainActivity;
import com.example.gs.gonser.govenmentservice.R;


/**
 * Created by Administrator on 2017/9/12.
 * 引导页3
 */

public class Guidepage4 extends Fragment {

    private TextView start;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.guidepage4,null);

        init();
        return view;
    }

    private void init(){
        start = view.findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
            }
        });
    }
}
