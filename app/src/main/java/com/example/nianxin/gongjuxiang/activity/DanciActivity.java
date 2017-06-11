package com.example.nianxin.gongjuxiang.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nianxin.gongjuxiang.R;
//import com.example.nianxin.gongjuxiang.db.DanCishuju;
import com.example.nianxin.gongjuxiang.db.Bianqianshuju;
import com.example.nianxin.gongjuxiang.db.Danci;
import com.example.nianxin.gongjuxiang.db.SQLiteDbManager;
import com.example.nianxin.gongjuxiang.implement.initWidgetInterface;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by nianxin on 2017/4/18.
 */

public class DanciActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener ,initWidgetInterface {
    private Button Button1, Button2;
    private Context context;
    private List<Danci> dancis;
    private List<Danci> yixue;
    private TextView txv3;
    private ProgressBar prg;
    private TextView tv1, tv2;
    private ImageButton button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWidget();
        action();
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:22.
     * action:初始化
     */
    public void initWidget() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dancizhuye);
        Button1 = (Button) findViewById(R.id.Button1);
        Button2 = (Button) findViewById(R.id.Button2);
        prg = (ProgressBar) findViewById(R.id.progress_bar);
        txv3 = (TextView) findViewById(R.id.textview3);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        button1 = (ImageButton) findViewById(R.id.button1);
        Button1.setOnClickListener(this);
        Button2.setOnClickListener(this);
        Button1.setOnLongClickListener(this);
        button1.setOnClickListener(this);
    }

    /**
    * wenming
    * created by:nianxin
    * created 2017/5/20 16:23.
    * action:初始化后执行的界面端操作
    */
    public void action(){
        //        实例化SQLiteDbManager类，如果系统中没有存在该数据库则复制一个过去，否则直接打开数据库；
        SQLiteDbManager sqLiteDbManager = new SQLiteDbManager();
        sqLiteDbManager.openDatabase(this);
        LitePal.getDatabase();
        yixue = DataSupport.select("yixue").where("yixue = ?", "1").find(Danci.class);
        dancis = DataSupport.select("yiji").where("yiji = ?", "1").find(Danci.class);
        double progress = (int) ((dancis.size()) * 100 / 1695) / 100.0;
        int progress1 = (int) (progress * 100);
        Log.d("这里", "onCreate: " + progress);
        prg.setProgress(progress1);
        txv3.setText(dancis.size() + "/1695");
        int text = 1695 - (yixue.size());
        Log.d("这里", "onCreate: " + text);
        tv1.setText(text + "");
        tv2.setText(yixue.size() + "");
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, DanciActivity.class);
        context.startActivity(intent);
    }
    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:20.
     * action:点击事件
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Button1:
                DanciLineActivity.actionStart(DanciActivity.this,"0");
                finish();
                break;
            case R.id.Button2:
                DanciYemianActivity.actionStart(DanciActivity.this);
                finish();
                break;
            case R.id.button1:
                DanciSousuoActivity.actionStart(DanciActivity.this);
                break;
        }
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:21.
     * action:销毁活动时清空集合
     */
    protected void onDestroy() {
        super.onDestroy();
        dancis.clear();
        yixue.clear();
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:21.
     * action:按钮“单词列表”的长按事件
     */
    public boolean onLongClick(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(DanciActivity.this);
        dialog.setTitle("清空数据");
        dialog.setMessage("是否确定清空所有数据记忆？");
        dialog.setCancelable(true);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Danci danci1 = new Danci();
                danci1.setToDefault("yixue");
                danci1.setToDefault("yiji");
                danci1.updateAll();
                finish();
                Intent intent = new Intent(DanciActivity.this, DanciActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
        return true;
    }
}
