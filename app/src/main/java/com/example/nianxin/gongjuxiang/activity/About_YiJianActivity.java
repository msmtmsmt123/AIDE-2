package com.example.nianxin.gongjuxiang.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nianxin.gongjuxiang.R;
import com.example.nianxin.gongjuxiang.base.BaseActivity;

/**
 * Created by nianxin on 2017/5/18.
 */

public class About_YiJianActivity extends BaseActivity implements View.OnClickListener{
    private TextView textView;
    private EditText et_subject,et_content;
    private Button button;
    private String et_sub_string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWidget();
    }
    /**
    * wenming
    * created by:nianxin
    * created 2017/5/20 16:05.
    * action:初始化
    */
    public void initWidget(){
        setContentView(R.layout.about_yijian);
        textView=(TextView)findViewById(R.id.textview);
        et_subject=(EditText)findViewById(R.id.et_subject);
        et_content=(EditText)findViewById(R.id.et_content);
        button=(Button)findViewById(R.id.bt_tijiao);
        button.setVisibility(View.VISIBLE);
        button.setOnClickListener(this);
        textView.setText("意见反馈");

    }
    public static void actionStrat(Context context){
        Intent intent=new Intent(context,About_YiJianActivity.class);
        context.startActivity(intent);
    }
    /**
    * wenming
    * created by:nianxin
    * created 2017/5/20 16:05.
    * action:重写finish方法，优化销毁activity效果
    */
    public void finish() {
        super.finish();
        AboutActivity.actionStart(About_YiJianActivity.this);
    }

    /**
    * wenming
    * created by:nianxin
    * created 2017/5/20 16:03.
    * action:点击事件
    */
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_tijiao:
                String[] email = {"2509329719@qq.com"};
                Intent intent = new Intent(android.content.Intent.ACTION_SEND,Uri.EMPTY);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, email); // 接收人
        /*邮件标题*/
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, et_subject.getText().toString());
        /*邮件正文*/
                intent.putExtra(android.content.Intent.EXTRA_TEXT, et_content.getText().toString());
                //调用系统的邮件系统
                startActivity(Intent.createChooser(intent, "请选择邮件应用发送"));
                break;
            default:
                break;
        }
    }
}
