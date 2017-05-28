package com.example.nianxin.gongjuxiang.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.platform.comapi.location.CoordinateType;
import com.example.nianxin.gongjuxiang.R;
import com.example.nianxin.gongjuxiang.base.BaseActivity;
import com.example.nianxin.gongjuxiang.db.MyDatabaseHelper;
import com.example.nianxin.gongjuxiang.db.Bianqianshuju;
import com.example.nianxin.gongjuxiang.db.ShuJu;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nianxin on 2017/3/28.
 */

public class BianqianActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton button2, image1;
    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private List<Bianqianshuju> ShuJ, ShuJ_que;
    private ListView listView;
    private String shuj, edit_string;
    private EditText editText;
    private LinearLayout linear1,linear_bq;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWidget();
        ShuJ_que = getdb();
        listadapter(ShuJ_que);
        listview();
        //判断集合中是否有元素，没有则隐藏“搜索便签”布局,并且把页面背景图更换
        if (ShuJ_que.size()>0){
            linear1.setVisibility(View.VISIBLE);
            linear_bq.setBackgroundResource(R.drawable.bianqianbj);
        }else {
            linear1.setVisibility(View.GONE);
            linear_bq.setBackgroundResource(R.drawable.nobianqian);
        }
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 14:22.
     * action:加载页面，初始化控件
     */
    public void initWidget() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bianqian);
        listView = (ListView) findViewById(R.id.list_view);
        button2 = (ImageButton) findViewById(R.id.button2);
        image1 = (ImageButton) findViewById(R.id.image1);
        editText = (EditText) findViewById(R.id.edittext1);
        linear1=(LinearLayout)findViewById(R.id.linear1);
        //主布局，用于改变背景图片
        linear_bq=(LinearLayout)findViewById(R.id.line_bianqian);
        image1.setVisibility(View.GONE);
        image1.setOnClickListener(this);
        button2.setOnClickListener(this);
        //edittext添加上一个发生变化监听器
        editText.addTextChangedListener(textWatcher);
        //设置按钮2的图片
        button2.setImageResource(R.drawable.ic_add_white_24dp);
        //页面加载时让edittext失去焦点，即不用在加载页面时弹出输入法
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 14:23.
     * action:数据库操作
     */
    public List<Bianqianshuju> getdb() {
        edit_string = editText.getText().toString();
        ShuJ = new ArrayList<Bianqianshuju>();
        dbHelper = new MyDatabaseHelper(this, "BianqianShuju.db", null, 1);
        db = dbHelper.getReadableDatabase();
        if (edit_string.equals("")) {

            Cursor cursor = db.query("Bianqian", null, null, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    shuj = cursor.getString(cursor.getColumnIndex("shuju"));
                    if (!(shuj.equals(""))) {
                        Bianqianshuju sj = new Bianqianshuju(shuj);    //存一个条目的数据
                        ShuJ.add(sj);//把数据库的每一行加入数组中
                    }
                } while (cursor.moveToNext());
            }

            cursor.close();
        } else {
            Cursor cursor = db.query("Bianqian", null, "shuju like ?", new String[]{"%" + edit_string + "%"}, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    shuj = cursor.getString(cursor.getColumnIndex("shuju"));
                    if (!(shuj.equals(""))) {
                        Bianqianshuju sj = new Bianqianshuju(shuj);    //存一个条目的数据
                        Log.d("aaaaaaaaaaaaaaaaaaa", "getdb: " + sj.getShuju());
                        ShuJ.add(sj);//把数据库的每一行加入数组中
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return ShuJ;
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 14:26.
     * action:listview相关
     */
    public void listadapter(final List<Bianqianshuju> list) {
        //为ListView设置一个适配器,getCount()返回数据个数;getView()为每一行设置一个条目
        listView.setAdapter(new BaseAdapter() {
            public int getCount() {
                return list.size();
            }

            //ListView的每一个条目都是一个view对象

            public View getView(int position, View convertView, ViewGroup parent) {
                View view;
                //对ListView的优化，convertView为空时，创建一个新视图；convertView不为空时，代表它是滚出
                //屏幕，放入Recycler中的视图,若需要用到其他layout，则用inflate(),同一视图，用fiindViewBy()
                if (convertView == null) {
                    view = View.inflate(getBaseContext(), R.layout.item_bianqinan, null);
                } else {
                    view = convertView;
                }

                //从list中取出一行数据，position相当于数组下标,可以实现逐行取数据
                Bianqianshuju st = ShuJ_que.get(position);
                TextView number = (TextView) view.findViewById(R.id.shuju);
                number.setText(st.getShuju());
                return view;
            }

            public Object getItem(int position) {
                return null;
            }

            public long getItemId(int position) {
                return 0;
            }
        });
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 14:28.
     * action:点击事件
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
                dbHelper.getWritableDatabase();
                Bianqianjiemian.actionStart(BianqianActivity.this, null);
                finish();
                break;
            case R.id.image1:
                editText.setText("");
                //隐藏输入法
                ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                        hideSoftInputFromWindow(editText.getWindowToken(), 0);
            default:
                break;
        }
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 14:30.
     * action:liseview的子项点击以及长按事件
     */
    public void listview() {
        //子项的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                Bianqianshuju sj = ShuJ.get(position);
                                                String s = sj.getShuju();
                                                Bianqianjiemian.actionStart(BianqianActivity.this, s);
                                                finish();
                                            }
                                        }

        );
        //子项的长按事件
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(BianqianActivity.this);
                dialog.setTitle("删除便签");
                dialog.setMessage("是否确定删除该便签？");
                dialog.setCancelable(true);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Bianqianshuju sj = ShuJ.get(position);
                        String s = sj.getShuju();
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.delete("Bianqian", "shuju=?", new String[]{s});
                        Snackbar.make(view,"已删除",Snackbar.LENGTH_SHORT).show();
                        ShuJ_que = getdb();
                        listadapter(ShuJ_que);
                        //判断集合中是否有元素，没有则隐藏“搜索便签”布局,并且把页面背景图更换
                        if (ShuJ_que.size()>0){
                            linear1.setVisibility(View.VISIBLE);
                            linear_bq.setBackgroundResource(R.drawable.bianqianbj);
                        }else {
                            linear1.setVisibility(View.GONE);
                            linear_bq.setBackgroundResource(R.drawable.nobianqian);
                        }

                    }
                });
                dialog.show();
                return true;
            }
        });

    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 14:42.
     * action:edittext变化监听
     */
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            edit_string = editText.getText().toString();
            if (edit_string.equals("")) {
                image1.setVisibility(View.GONE);
            } else {
                image1.setVisibility(View.VISIBLE);
            }
            ShuJ_que = getdb();
            listadapter(ShuJ_que);
        }
    };

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, BianqianActivity.class);
        context.startActivity(intent);
    }
}
