package com.example.nianxin.gongjuxiang.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.nianxin.gongjuxiang.activity.BianqianActivity;

/**
 * Created by nianxin on 2017/3/28.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {
    /**
    * wenming
    * created by:nianxin
    * created 2017/5/20 19:45.
    * action:新建数据库
    */
    public static final String CREATE_BIANQIAN = "create table Bianqian("
            + "id integer primary key autoincrement,"
            + "shuju text)";
    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }
    public MyDatabaseHelper(Context context) {
        //创建数据库
        super(context, "BianqianShuju.db", null, 1);
        // TODO Auto-generated constructor stub
        System.out.println("MyOpenHelper");
    }

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_BIANQIAN);
//        Toast.makeText(mContext, "数据库2创建成功！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
