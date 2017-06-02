package com.example.nianxin.gongjuxiang.service;

import android.Manifest;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Environment;
import android.os.IBinder;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.nianxin.gongjuxiang.activity.Phone_visb_go_Activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MyService_beifen extends Service {
    public static List<Map<String, String>> dataList;
    public static List<Map<String, String>> list = new ArrayList<Map<String, String>>();
    Map<String, String> map;
    int i=0;
    public MyService_beifen() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("===============", "onCreate: 服务2启动了");
        dataList = getDataList();
            IO(dataList);
    }
    /**
     * wenming
     * created by:nianxin
     * created 2017/5/9 21:21.
     * action:IO流写入文件方法
     */
    public void IO(List<Map<String, String>> dataList) {
        try {
            // 为当前的app在内存中新建文件夹
            File dir1 = new File("storage/emulated/0/AIDE/Text");
            if (!dir1.exists()) {
                dir1.mkdirs();
            }
            String fileName = new Random().nextInt(999999999) +".txt";
            //app文件夹目录
            String SDPATH = "storage/emulated/0/AIDE/Text";
            File file = new File(SDPATH + "/" + fileName);
            //如果文件不存在，则新建一个文件
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(SDPATH + "/" + fileName);
            File f = new File(SDPATH + "/" + fileName);
            for (int k =0;k<dataList.size();k++) {
                map = dataList.get(k);
                Log.d("这里", "onCreate: " + map.get("date"));
                fw.write(map.get("name") + "\t");
                fw.write(map.get("number") + "\t");
                fw.write(map.get("type") + "\n");
                fw.write(map.get("date") + "\t");
                fw.write(map.get("duration")+"\n\n");
            }
            FileOutputStream os = new FileOutputStream(f, false);
            ObjectOutputStream out = new ObjectOutputStream(os);
            out.writeShort(2);
            out.writeUTF("");
            fw.flush();
            fw.close();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("=======================", "onCreate: 异常出现了！！！");
            e.printStackTrace();
        }
    }
    /**
     * 读取数据
     *
     * @return 读取到的数据
     */
    public List<Map<String, String>> getDataList() {
        try {
            // 1.获得ContentResolver
            ContentResolver resolver = getContentResolver();
            // 2.利用ContentResolver的query方法查询通话记录数据库
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
                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dateLong));
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

}
