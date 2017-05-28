package com.example.nianxin.gongjuxiang.my;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.nianxin.gongjuxiang.R;

/**
 * Created by nianxin on 2017/3/28.
 */

public class TitleLayout_about_item extends LinearLayout {
    private ImageButton button1;
    private Button button2;
    public TitleLayout_about_item(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.biaotilan_about_item,this);
        button1=(ImageButton)findViewById(R.id.bt1);
        button2=(Button)findViewById(R.id.btn1);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();
            }
        });
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();
            }
        });

    }
}
