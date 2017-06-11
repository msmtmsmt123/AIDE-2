package com.example.nianxin.gongjuxiang.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nianxin.gongjuxiang.R;
import com.example.nianxin.gongjuxiang.base.BaseActivity;
import com.example.nianxin.gongjuxiang.implement.initWidgetInterface;

/**
 * Created by nianxin on 2017/5/18.
 */

public class About_ShiYongActivity extends BaseActivity implements View.OnClickListener,initWidgetInterface {
    public TextView textView, bb_num;
    private LinearLayout line_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWidget();
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:01.
     * action:初始化
     */
    public void initWidget() {
        setContentView(R.layout.about_shiyong);
        textView = (TextView) findViewById(R.id.textview);
        bb_num = (TextView) findViewById(R.id.bb_num);
        line_url=(LinearLayout)findViewById(R.id.line_url);
        line_url.setOnClickListener(this);
        textView.setText("使用须知");
        String string=getVersion();
        bb_num.setText(string);
    }

    public static void actionStrat(Context context) {
        Intent intent = new Intent(context, About_ShiYongActivity.class);
        context.startActivity(intent);
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
        AboutActivity.actionStart(About_ShiYongActivity.this);
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/26 12:39.
     * action:获取版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return this.getString(R.string.version_name) + version;
        } catch (Exception e) {
            e.printStackTrace();
            return this.getString(R.string.can_not_find_version_name);
        }
    }

   /**
   * wenming
   * created by:nianxin
   * created 2017/5/26 21:25.
   * action:点击响应事件
   */
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.line_url:
                //判断如果点击了“开源地址”布局，则使用隐式intent访问项目的开源地址
                Intent intenet=new Intent(Intent.ACTION_VIEW);
                intenet.setData(Uri.parse("http://www.github.com/provdboy/aide"));
                startActivity(intenet);
                break;
            default:
                break;
        }
    }
}
