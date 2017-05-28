package com.example.nianxin.gongjuxiang.activity;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;

import com.example.nianxin.gongjuxiang.R;
import com.example.nianxin.gongjuxiang.service.MyService_beifen;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 *
 */
public class Phone_visb_go_Activity extends Activity {
    private ListView mLvShow;
    private List<Map<String, String>> dataList;
    private SimpleAdapter adapter;
    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
    Map<String, String> map;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWidget();
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 17:13.
     * action:初始化,加载布局以及设置适配器
     */
    public void initWidget() {
        setContentView(R.layout.phone_visb_go);
        mLvShow = (ListView) findViewById(R.id.lv_show);
        dataList = getDataList();
        adapter = new SimpleAdapter(this, dataList, R.layout.item_phone_visb
                , new String[]{"name", "number", "date", "duration", "type"}
                , new int[]{R.id.tv_name, R.id.tv_number, R.id.tv_date, R.id.tv_duration, R.id.tv_type});
        mLvShow.setAdapter(adapter);
    }

    /**
     * 读取数据
     * 通过内容提供器
     *
     * @return 读取到的数据
     */
    private List<Map<String, String>> getDataList() {
        try {
            // 1.获得ContentResolver
            ContentResolver resolver = getContentResolver();
            // 2.利用ContentResolver的query方法查询通话记录数据库

            List<String> permiss = new ArrayList<>();
            if (ContextCompat.checkSelfPermission(Phone_visb_go_Activity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permiss.add(Manifest.permission.READ_EXTERNAL_STORAGE);
                Log.d("", "getDataList: 没有授权读取存储器权限");
            }
            if (ContextCompat.checkSelfPermission(Phone_visb_go_Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permiss.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                Log.d("", "getDataList: 没有授写入存储器权限");
            }
            if (ContextCompat.checkSelfPermission(Phone_visb_go_Activity.this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                permiss.add(Manifest.permission.READ_CALL_LOG);
            }
            if (!permiss.isEmpty()) {
                String[] permissions = permiss.toArray(new String[permiss.size()]);
                ActivityCompat.requestPermissions(Phone_visb_go_Activity.this, permissions, 1);
            }
            /**
             * @param uri 需要查询的URI，（这个URI是ContentProvider提供的）
             * @param projection 需要查询的字段
             * @param selection sql语句where之后的语句
             * @param selectionArgs ?占位符代表的数据
             * @param sortOrder 排序方式
             */
            // 3.通过Cursor获得数据
            Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, // 查询通话记录的URI
                    new String[]{CallLog.Calls.CACHED_NAME// 通话记录的联系人
                            , CallLog.Calls.NUMBER// 通话记录的电话号码
                            , CallLog.Calls.DATE// 通话记录的日期
                            , CallLog.Calls.DURATION// 通话时长
                            , CallLog.Calls.TYPE}// 通话类型
                    , null, null, CallLog.Calls.DEFAULT_SORT_ORDER// 按照时间逆序排列，最近打的最先显示
            );
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
                String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                long dateLong = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE));
                String date = new SimpleDateFormat("yyyy-MM-dd H:mm:ss").format(new Date(dateLong));
                int duration = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.DURATION));
                int type = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
                String typeString = "";
                switch (type) {
                    case CallLog.Calls.INCOMING_TYPE:
                        typeString = "打入";
                        break;
                    case CallLog.Calls.OUTGOING_TYPE:
                        typeString = "打出";
                        break;
                    case CallLog.Calls.MISSED_TYPE:
                        typeString = "未接";
                        break;
                    default:
                        break;
                }
                Map<String, String> map = new HashMap<String, String>();
                map.put("name", (name == null) ? "未知联系人" : name);
                map.put("number", number);
                map.put("date", date);
                map.put("duration", (duration / 60) + "分钟");
                map.put("type", typeString);
                list.add(map);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void actionStrat(Context context) {
        Intent intent = new Intent(context, Phone_visb_go_Activity.class);
        context.startActivity(intent);
    }
}