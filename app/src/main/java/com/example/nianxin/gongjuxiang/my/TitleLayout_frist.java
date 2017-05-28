package com.example.nianxin.gongjuxiang.my;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.nianxin.gongjuxiang.R;

/**
 * Created by nianxin on 2017/5/18.
 */

public class TitleLayout_frist extends LinearLayout{
    public TitleLayout_frist(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.biaotilan_frist,this);
    }
}
