package com.example.nianxin.gongjuxiang.my;


import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.baidu.platform.comapi.map.B;
import com.example.nianxin.gongjuxiang.R;
import com.example.nianxin.gongjuxiang.activity.FirstActivity;

/**
 * Created by nianxin on 2017/5/18.
 */

public class TitleLayout_about extends LinearLayout{
    private ImageButton imageButton;
    public TitleLayout_about(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.biaotilan_about,this);
        imageButton=(ImageButton)findViewById(R.id.bt1);
        imageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();
            }
        });

    }
}
