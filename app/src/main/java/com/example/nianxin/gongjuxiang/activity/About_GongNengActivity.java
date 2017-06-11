package com.example.nianxin.gongjuxiang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.nianxin.gongjuxiang.R;
import com.example.nianxin.gongjuxiang.base.BaseActivity;
import com.example.nianxin.gongjuxiang.implement.initWidgetInterface;

/**
 * Created by nianxin on 2017/5/18.
 */

public class About_GongNengActivity extends BaseActivity implements initWidgetInterface{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWidget();
    }

    /**
    * wenming
    * created by:nianxin
    * created 2017/5/20 16:02.
    * action:初始化
    */
    public void initWidget(){
        setContentView(R.layout.about_gongneng);
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/18 19:00.
     * action:重写finish方法，实现销毁动画效果
     */
    @Override
    public void finish() {
        super.finish();
        AboutActivity.actionStart(About_GongNengActivity.this);
    }

    public static void actionStrat(Context context){
        Intent intent=new Intent(context,About_GongNengActivity.class);
        context.startActivity(intent);
    }


}
