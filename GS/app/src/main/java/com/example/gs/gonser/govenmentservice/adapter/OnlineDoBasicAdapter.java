package com.example.gs.gonser.govenmentservice.adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.gs.gonser.govenmentservice.R;
import com.example.gs.gonser.govenmentservice.baseadpater.AutoRVAdapter;
import com.example.gs.gonser.govenmentservice.baseadpater.RVHolder;
import com.example.gs.gonser.govenmentservice.baseadpater.ViewHolder;
import com.example.gs.gonser.govenmentservice.bean.BaiscInfoBean;
import com.example.gs.gonser.govenmentservice.utils.LogUtils;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by yangzhao on 2017/9/15.
 * 适配器，在线办理里面的基本信息
 */

public class OnlineDoBasicAdapter extends RecyclerView.Adapter<RVHolder> {
    public List<?> list;
    private Context context;

    // item 的四种类型
    public static final int ITEM_TYPE_TEXT = 0X1111; // 正常的item类型
    public static final int ITEM_TYPE_RADIO = 0X1112; // radio 单选
    public static final int ITEM_TYPE_SPINNER = 0X1113; // 下拉列表
    public static final int ITEM_TYPE_DATEPINKER = 0X1114; // 日期选择器
    public static final int ITEM_TYPE_CHECKBOX = 0X1115; // checkbox 多选

    private Calendar cal;
    private int year,month,day;

    public OnlineDoBasicAdapter(Context context, List<?> list) {
        this.list = list;
        this.context = context;
    }

    public int onCreateViewLayoutID(int viewType) {

        return R.layout.onlinedoinfoitem_html;
    }

    @Override
    public RVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutID = 0;
        if (viewType==ITEM_TYPE_RADIO){
            layoutID = R.layout.onlinedoinfoitem_checkbox ;
        }else if (viewType==ITEM_TYPE_SPINNER){
            layoutID = R.layout.onlinedoinfoitem_spinner ;
        }else if (viewType==ITEM_TYPE_DATEPINKER){
            layoutID = R.layout.onlinedoinfoitem_datepicker ;
        }else if (viewType==ITEM_TYPE_CHECKBOX){
            layoutID = R.layout.onlinedoinfoitem_checkbox ;
        }else {
            layoutID = R.layout.onlinedoinfoitem_html ;
        }
        View view = LayoutInflater.from(context).inflate(layoutID, null);
        return new RVHolder(view);
    }

    @Override
    public void onBindViewHolder(RVHolder holder, int position) {
        final ViewHolder viewHolder = holder.getViewHolder();

        final BaiscInfoBean bean = (BaiscInfoBean) list.get(position);
        TextView name = viewHolder.getTextView(R.id.name);
        String type = bean.getLeipiplugins();
        String nameTr = bean.getName();
        final List<String> options;
        if (type.equals("radios")){
            options = bean.getOptionsList();
            if (options.size()!=0) {
                //根据ID找到RadioGroup实例
                RadioGroup group = viewHolder.getRadioGroup(R.id.radioGroup);
                for (int i = 0; i < options.size(); i++) {
                    RadioButton radioButton = new RadioButton(context);
                    radioButton.setText(options.get(i));
                    radioButton.setPadding(10,0,0,0);
                    group.addView(radioButton, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                }
                //绑定一个匿名监听器
                group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(RadioGroup arg0, int arg1) {
                        // TODO Auto-generated method stub
                        //根据ID获取RadioButton的实例
                        RadioButton rb = viewHolder.getRadioButton(arg1);
                        String rb_text = rb.getText().toString();
                        LogUtils.i("RadioGroup选中的数据----"+rb_text);
                        bean.setText(rb_text);
                    }
                });
            }
        }else if (type.equals("select")){
            options = bean.getOptionsList();
            Spinner spinner = viewHolder.getSpinner(R.id.spinner);
            for (int i = 0;i<options.size();i++){
                if (TextUtils.isEmpty(options.get(i))){
                    options.remove(i);
                }
            }
            spinner.setAdapter(new ArrayAdapter(context,R.layout.spinner_item,R.id.spinnerText,options));

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    bean.setText(options.get(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }else if (type.equals("datepicker")){
            final TextView date = viewHolder.getTextView(R.id.date);
            getDate();
            date.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker arg0, int year, int month, int day) {
                            String m = ""+(++month);
                            String d = ""+day;
                            if (++month<10){
                                m = "0"+m;
                            }
                            if (day<10){
                                d = "0"+day;
                            }
                            date.setText(year+"-"+m+"-"+d);//将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                            bean.setText(year+"-"+m+"-"+d);
                        }
                    };
                    DatePickerDialog dialog=new DatePickerDialog(context, 0,listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
                    dialog.show();
                }
            });
        }else{
            final EditText content = viewHolder.getEditText(R.id.content);
            content.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    LogUtils.i("Text改变后----"+s.toString());
                    bean.setText(s.toString());
                }
            });
        }
        name.setText(bean.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 0;
        BaiscInfoBean bean = (BaiscInfoBean) list.get(position);
        if(bean.getLeipiplugins().equals("radios")){
            viewType = ITEM_TYPE_RADIO;
        }else if (bean.getLeipiplugins().equals("select")){
            viewType = ITEM_TYPE_SPINNER;
        }else if (bean.getLeipiplugins().equals("datepicker")){
            viewType = ITEM_TYPE_DATEPINKER;
        }else {
            viewType = ITEM_TYPE_TEXT;
        }
        return viewType;
    }

    //获取当前日期
    private void getDate() {
        cal=Calendar.getInstance();
        year=cal.get(Calendar.YEAR);       //获取年月日时分秒
        Log.i("wxy","year"+year);
        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
    }
}
