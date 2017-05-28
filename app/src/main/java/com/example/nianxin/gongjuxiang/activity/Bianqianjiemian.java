package com.example.nianxin.gongjuxiang.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.nianxin.gongjuxiang.R;
import com.example.nianxin.gongjuxiang.base.BaseActivity;
import com.example.nianxin.gongjuxiang.db.MyDatabaseHelper;

import static java.security.AccessController.getContext;

/**
 * Created by nianxin on 2017/3/29.
 */

public class Bianqianjiemian extends BaseActivity implements View.OnClickListener {
    public static MyDatabaseHelper dbHelper;
    private ImageButton button2, button1;
    private EditText editText;
    private String shuju = null;
    private Boolean shifou = false;
    private String aString, bstring;
    public SQLiteDatabase db;
    public ContentValues values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWidget();
        getdb();
    }

    protected void onPause() {
        super.onPause();
        BianqianActivity.actionStart(Bianqianjiemian.this);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.danci, menu);
        return true;
    }
    public void finish() {
        super.finish();
        BianqianActivity.actionStart(Bianqianjiemian.this);
    }
    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:07.
     * action:初始化
     */
    public void initWidget() {
        setContentView(R.layout.bianqianjiemian);
        button2 = (ImageButton) findViewById(R.id.button2);
        button1 = (ImageButton) findViewById(R.id.button1);
        editText = (EditText) findViewById(R.id.edittext1);
        Intent intent = getIntent();
        aString = intent.getStringExtra("zhnum");
        editText.setText(aString);
        button2.setImageResource(R.drawable.ic_done_white_24dp);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }
    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:11.
     * action:点击事件
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                BianqianActivity.actionStart(Bianqianjiemian.this);
                finish();
                break;
            case R.id.button2:
                if (shifou == true) {
                    shuju = editText.getText().toString();
                    if (!(shuju.equals(""))) {
                        values.put("shuju", shuju);
                        db.insert("Bianqian", null, values);
                        values.clear();
                        finish();
                        BianqianActivity.actionStart(Bianqianjiemian.this);
                    } else {
                        finish();
                        BianqianActivity.actionStart(Bianqianjiemian.this);
                    }
                } else {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values1 = new ContentValues();
                    bstring = editText.getText().toString();
                    values.put("shuju", bstring);
                    db.update("Bianqian", values, "shuju=?", new String[]{aString});
                    finish();
                    BianqianActivity.actionStart(Bianqianjiemian.this);
                }
                break;
            default:
                break;
        }
    }
    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:09.
     * action:数据库相关
     */
    public void getdb() {
        dbHelper = new MyDatabaseHelper(this, "BianqianShuju.db", null, 1);
        db = dbHelper.getWritableDatabase();
        values = new ContentValues();
        if (aString != null) {
            editText.setSelection(aString.length());
        }
        if (editText.getText().toString().equals("")) {
            shifou = true;
        } else {
            shifou = false;
        }

    }

    public static void actionStart(Context context, String s) {
        Intent intent = new Intent(context, Bianqianjiemian.class);
        intent.putExtra("zhnum", s);
        context.startActivity(intent);
        Log.d("这里", "s:" + s);
    }
}


