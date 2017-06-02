package com.example.nianxin.gongjuxiang.activity;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nianxin.gongjuxiang.R;
import com.example.nianxin.gongjuxiang.service.MyService_beifen;
import com.example.nianxin.gongjuxiang.service.PhoneService;

import java.util.List;

public class PhoneListenerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button service_strat, service_stop, visibility_go, visibility_nextgo;
    private CheckBox checkBox1, checkBox2;
    public SharedPreferences pre1, pre2;
    private SharedPreferences.Editor editor1, editor2;
    private boolean is1, is2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWidget();//初始化控件

    }

/**
* wenming
* created by:nianxin
* created 2017/5/20 17:14.
* action:初始化部件
*/
    private void initWidget() {
        setContentView(R.layout.phonejiemian);
        service_strat = (Button) findViewById(R.id.service_strat);
        service_stop = (Button) findViewById(R.id.service_stop);
        visibility_go = (Button) findViewById(R.id.visibility_go);
        visibility_nextgo = (Button) findViewById(R.id.visibility_nextgo);
        checkBox1 = (CheckBox) findViewById(R.id.checkbox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkbox2);
        service_strat.setOnClickListener(this);
        service_stop.setOnClickListener(this);
        visibility_nextgo.setOnClickListener(this);
        visibility_go.setOnClickListener(this);
        pre1 = PreferenceManager.getDefaultSharedPreferences(this);
        pre2 = PreferenceManager.getDefaultSharedPreferences(this);
        is1 = pre1.getBoolean("phone_strat", false);
        is2 = pre2.getBoolean("phone_beifen", false);
        if (is1) {
            checkBox1.setChecked(true);
        }
        if (is2) {
            checkBox2.setChecked(true);
        }
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor1 = pre1.edit();
                if (checkBox1.isChecked()) {
                    editor1.putBoolean("phone_strat", true);
                } else {
                    editor1.putBoolean("phone_strat", false);
                }
                editor1.apply();
            }
        });
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor2 = pre2.edit();
                if (checkBox2.isChecked()) {
                    editor2.putBoolean("phone_beifen", true);
                } else {
                    editor2.putBoolean("phone_beifen", false);
                }
                editor2.apply();
            }
        });
    }

    /**
    * wenming
    * created by:nianxin
    * created 2017/5/20 17:15.
    * action:按钮的点击事件
    */
    public void onClick(View v) {
        boolean a = isServiceWork(PhoneListenerActivity.this, "com.example.nianxin.gongjuxiang.service.PhoneService");
        switch (v.getId()) {
            case R.id.service_strat:
                if (a == true) {
                    Snackbar.make(v,"服务已处于开启状态",Snackbar.LENGTH_SHORT).show();
                } else {
                    Intent intent1 = new Intent(this, PhoneService.class);
                    startService(intent1);
                    AlertDialog.Builder dialog = new AlertDialog.Builder(PhoneListenerActivity.this);
                    dialog.setMessage("服务成功开启");
                    dialog.setCancelable(true);
                    dialog.show();
                    Log.d("", "onClick: 服务进行了");
                }
                //开启服务

                break;
            case R.id.service_stop:
                if (a == false) {
                    Snackbar.make(v,"服务已处于关闭状态",Snackbar.LENGTH_SHORT).show();
                } else {
                    //停止服务。
                    Intent intent2 = new Intent(this, PhoneService.class);
                    stopService(intent2);
                    AlertDialog.Builder dialog = new AlertDialog.Builder(PhoneListenerActivity.this);
                    dialog.setMessage("服务成功关闭");
                    dialog.setCancelable(true);
                    dialog.show();
                }


                break;
            case R.id.visibility_go:
                Phone_visb_go_Activity.actionStrat(PhoneListenerActivity.this);
                break;
            case R.id.visibility_nextgo:
                boolean b = isServiceWork(PhoneListenerActivity.this, "com.example.nianxin.gongjuxiang.service.MyService_beifen");
                if (b==true){
                    Snackbar.make(v,"数据刚刚备份过！",Snackbar.LENGTH_SHORT).show();
                }else {
                    Intent intent1 = new Intent(this, MyService_beifen.class);
                    startService(intent1);
                    Snackbar.make(v, "数据成功备份", Snackbar.LENGTH_SHORT).show();
                    Log.d("", "onClick: 备份成功了");
                }
                break;
            default:
                break;

        }
    }

/**
* wenming
* created by:nianxin
* created 2017/6/2 21:29.
* action:重写finsh方法，判断服务二如果在运行，则关闭它
*/
    @Override
    public void finish() {
        boolean b = isServiceWork(PhoneListenerActivity.this, "com.example.nianxin.gongjuxiang.service.MyService_beifen");
        if (b==true){
            Intent intent2 = new Intent(this, MyService_beifen.class);
            stopService(intent2);
        }
        super.finish();
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/16 20:51.
     * action:方法判断某服务是否在运行中
     */
    public boolean isServiceWork(Context mContext, String serviceName) {
        boolean isWork = false;
        ActivityManager myAM = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> myList = myAM.getRunningServices(40);
        if (myList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < myList.size(); i++) {
            String mName = myList.get(i).service.getClassName().toString();
            if (mName.equals(serviceName)) {
                isWork = true;
                break;
            }
        }
        return isWork;
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, PhoneListenerActivity.class);
        context.startActivity(intent);
    }
}