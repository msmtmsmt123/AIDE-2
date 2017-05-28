package com.example.nianxin.gongjuxiang.BroadcastReceiver;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
import com.example.nianxin.gongjuxiang.activity.PhoneListenerActivity;
import com.example.nianxin.gongjuxiang.service.MyService_beifen;
import com.example.nianxin.gongjuxiang.service.PhoneService;

public class BootCompleteReceiver1 extends BroadcastReceiver {
   /**
   * wenming
   * created by:nianxin
   * created 2017/5/20 19:43.
   * action:方法实现接收开机广播后判断是否标记了开机自启的功能，
    * 如果有则去开启指定的服务
   */
    public void onReceive(Context context, Intent intent) {
            SharedPreferences p=context.getSharedPreferences("com.example.nianxin.gongjuxiang_preferences",context.MODE_PRIVATE);
            boolean abs=p.getBoolean("phone_strat",false);
        if (abs==true) {
            Toast.makeText(context, "开机服务自动启动成功！！！", Toast.LENGTH_SHORT).show();
            Log.i("这里=====================", "手机启动完毕了，监视到了手机启动的广播事件，开启后台监听的服务");
            Intent i = new Intent(context, PhoneService.class);
            context.startService(i);
        }
        SharedPreferences a=context.getSharedPreferences("com.example.nianxin.gongjuxiang_preferences",context.MODE_PRIVATE);
        boolean abc=a.getBoolean("phone_beifen",false);
        if (abc==true){
            Toast.makeText(context, "开机备份已完成", Toast.LENGTH_SHORT).show();
            Intent i1 = new Intent(context, MyService_beifen.class);
            context.startService(i1);
        }
    }
}
