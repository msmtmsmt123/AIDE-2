package com.example.nianxin.gongjuxiang.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.example.nianxin.gongjuxiang.activity.PhoneListenerActivity;

import java.io.File;
import java.util.Random;

public class PhoneService extends Service {
    // 电话管理器
    private TelephonyManager tm;
    // 监听器对象
    private MyListener listener;
    //声明录音机
    private MediaRecorder mRecorder;

    public PhoneService() {
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //服务开启调用该方法
    public void onCreate() {
        Log.d("===============", "onCreate: 服务1启动了");
        super.onCreate();
        tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        listener = new MyListener();
        tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    //服务销毁调用该方法
    public void onDestroy() {
        super.onDestroy();
        // 取消电话的监听
        tm.listen(listener, PhoneStateListener.LISTEN_NONE);
        listener = null;
    }

    private class MyListener extends PhoneStateListener {
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            try {
                // 为当前的app在内存中新建文件夹
                File dir = new File("storage/emulated/0/AIDE/Music");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE://.空闲状态。
                        if (mRecorder != null) {
                            //8.停止捕获
                            mRecorder.stop();
                            //9.释放资源
                            mRecorder.release();
                            Log.d("bbbbbbbbbbbbbb", "onCallStateChanged: 通话结束，录音完成……");
                            mRecorder = null;
                        }
                        break;
                    case TelephonyManager.CALL_STATE_RINGING://零响状态。
                        Log.d("aaaaaaaaaaaaaa", "onCallStateChanged: 电话铃响，准备接听中");
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK://通话状态
                        Log.d("bbbbbbbbbbbbbb", "onCallStateChanged: 通话中，开始录音……");
                        //1.实例化一个录音机
                        mRecorder = new MediaRecorder();
                        //2.指定录音机的声音源
                        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        //3.设置录制的文件输出的格式
                        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
                        //4.指定录音文件的名称
                       File file = new File(dir + "/" + new Random().nextInt(999999999) + ".mp3");
                        mRecorder.setOutputFile(file.getAbsolutePath());
                        //5.设置音频的编码
                        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                        //6.准备开始录音
                        mRecorder.prepare();
                        //7.开始录音
                        mRecorder.start();
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}