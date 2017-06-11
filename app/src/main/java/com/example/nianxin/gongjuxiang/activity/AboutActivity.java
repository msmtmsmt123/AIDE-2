package com.example.nianxin.gongjuxiang.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nianxin.gongjuxiang.R;
import com.example.nianxin.gongjuxiang.base.BaseActivity;
import com.example.nianxin.gongjuxiang.implement.initWidgetInterface;

/**
 * Created by nianxin on 2017/3/23.
 */

public class AboutActivity extends BaseActivity implements View.OnClickListener,initWidgetInterface {
    private Button bt_shiyong,bt_gongneng,bt_fankui,bt_meiriyitu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       initWidget();
    }
    /**
    * wenming
    * created by:nianxin
    * created 2017/5/18 16:36.
    * action:初始化界面
    */
    public void initWidget(){
        setContentView(R.layout.about);
        bt_fankui=(Button)findViewById(R.id.bt_yijian);
        bt_gongneng=(Button)findViewById(R.id.bt_gongneng);
        bt_shiyong=(Button)findViewById(R.id.bt_shiyong);
        bt_meiriyitu=(Button)findViewById(R.id.bt_meiriyitu);
        bt_gongneng.setOnClickListener(this);
        bt_shiyong.setOnClickListener(this);
        bt_fankui.setOnClickListener(this);
        bt_meiriyitu.setOnClickListener(this);
    }

    public static void  actionStart(Context context){
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);

    }

   /**
   * wenming
   * created by:nianxin
   * created 2017/5/20 16:06.
   * action:点击事件
   */
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_gongneng:
                finish();
                About_GongNengActivity.actionStrat(AboutActivity.this);
                break;
            case R.id.bt_shiyong:
                finish();
                About_ShiYongActivity.actionStrat(AboutActivity.this);
                break;
            case R.id.bt_yijian:
                finish();
                About_YiJianActivity.actionStrat(AboutActivity.this);
                break;
            case R.id.bt_meiriyitu:
                finish();
                About_MeRiYiTuAcitvity.actionStrat(AboutActivity.this);
                break;
            default:
                break;
        }
    }
}
