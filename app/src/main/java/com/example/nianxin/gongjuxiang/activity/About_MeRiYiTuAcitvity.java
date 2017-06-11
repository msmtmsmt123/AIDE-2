package com.example.nianxin.gongjuxiang.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.nianxin.gongjuxiang.R;
import com.example.nianxin.gongjuxiang.base.BaseActivity;
import com.example.nianxin.gongjuxiang.base.HttpUtil;
import com.example.nianxin.gongjuxiang.implement.initWidgetInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by nianxin on 2017/5/30.
 */

public class About_MeRiYiTuAcitvity extends BaseActivity implements View.OnLongClickListener,initWidgetInterface {
    private ImageView bingpiimg;
    private SharedPreferences.Editor editor;
    private String bingpic;
    private File file;
    private double yixiazai = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWidget();
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/30 21:58.
     * action:初始化
     */
    public void initWidget() {
        //设置状态栏背景色透明，使得与图片融合(要求安卓5.0以上才能实现该效果，所以这里进行了一个判断)
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.about_meiriyitu);
        bingpiimg = (ImageView) findViewById(R.id.bing_pic_img);
        bingpiimg.setOnLongClickListener(this);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(About_MeRiYiTuAcitvity.this);
        String bingpic = prefs.getString("bing_pic", null);
//        if (bingpic != null) {
//            Glide.with(this).load(bingpic).into(bingpiimg);
//        } else {
        loadBingPic();
//        }

    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/30 21:03.
     * action:从必应获得每日一图
     */
    public void loadBingPic() {
        String pricture = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(pricture, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                bingpic = response.body().string();
                Log.d("=============", "onResponse: " + bingpic);
                editor = PreferenceManager.getDefaultSharedPreferences(About_MeRiYiTuAcitvity.this).edit();
                editor.putString("bing_pic", bingpic);
                editor.apply();
                editor.clear();
                //切换到主线程
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //加载图片
                        Glide.with(About_MeRiYiTuAcitvity.this).load(bingpic).into(bingpiimg);

                    }
                });
            }
        });
    }


    /**
     * wenming
     * created by:nianxin
     * created 2017/5/30 21:59.
     * action:重写finsh方法
     */
    @Override
    public void finish() {
        super.finish();
        AboutActivity.actionStart(About_MeRiYiTuAcitvity.this);
    }





    /**
     * wenming
     * created by:nianxin
     * created 2017/5/30 22:23.
     * action:长按事件
     */
    @Override
    public boolean onLongClick(final View view) {
        switch (view.getId()) {
            case R.id.bing_pic_img:
                final String[] items = new String[]{"保存到本地"};
                final AlertDialog.Builder dialog = new AlertDialog.Builder(About_MeRiYiTuAcitvity.this);
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //将okhttp返回的图片链接传递给子线程，在子线程中下载图片
                        new MyThread(bingpic).start();
                    }
                });
                dialog.setCancelable(true);

                dialog.show();
                break;
        }
        return true;
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 18:55.
     * action:用于处理异步回调消息
     */
    private Handler handler = new Handler() {
        // 重写处理消息的方法
        public void handleMessage(Message message) {
            // obj为1，代表图片下载完成
           if (message.what == 1) {
                Snackbar.make(bingpiimg, "已保存", Snackbar.LENGTH_SHORT).show();
            }
        }
    };


    /**
     * wenming
     * created by:nianxin
     * created 2017/6/2 19:46.
     * action:开启子线程执行下载图片任务
     */
    public class MyThread extends Thread {
        private String bingpic;
        public MyThread(String bingpic) {
            this.bingpic = bingpic;
        }

        public void run() {
            try

            {
                // 为当前的app在内存中新建文件夹
                File dir = new File("storage/emulated/0/AIDE/Prcture");
                if (!dir.exists()) {
                    dir.mkdirs();
                    dir = new File("storage/emulated/0/AIDE/Prcture");
                } else {
                    dir = new File("storage/emulated/0/AIDE/Prcture");
                }
                URL url = new URL(bingpic);
                // 打开连接
                URLConnection urlConnection = null;
                urlConnection = url.openConnection();
                // 设置超时
                urlConnection.setConnectTimeout(10000);
                // 输入流
                InputStream inputStream = null;
                inputStream = urlConnection.getInputStream();
                // 1K的数据缓冲
                byte[] bs = new byte[1024];
                // 读取到的数据长度
                int len = 0;
                int total = urlConnection.getContentLength(); // 待下载文件的总大小
                file = new File(dir + "/" + new Random().nextInt(999999999) + ".jpg");
                // 输出的文件流
                OutputStream outputStream = null;
                outputStream = new FileOutputStream(file);
                // 开始读取
                while ((len = inputStream.read(bs)) != -1) {
                    // 用于向Handler发送消息
                    Message message = new Message();
                    // what为0代表未下载完
                    message.what = 0;
                    yixiazai += len;
                    handler.sendMessage(message);
                    outputStream.write(bs, 0, len);
                }
                Message message = new Message();
                // what为1代表下载完成
                message.what = 1;
                handler.sendMessage(message);
                // 释放资源
                inputStream.close();
                outputStream.close();
            } catch(
                    Exception e)

            {
                e.printStackTrace();
            }
        }
    }

    public static void actionStrat(Context context) {
        Intent intent = new Intent(context, About_MeRiYiTuAcitvity.class);
        context.startActivity(intent);
    }
}
