package com.example.nianxin.gongjuxiang.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nianxin.gongjuxiang.R;
import com.example.nianxin.gongjuxiang.base.BaseActivity;
import com.example.nianxin.gongjuxiang.db.Danci;
import com.example.nianxin.gongjuxiang.db.SQLiteDbManager;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by nianxin on 2017/4/19.
 */

public class DanciYemianActivity extends AppCompatActivity implements View.OnClickListener {
    String astring, bstring, name, text;
    TextView textView1, textView2, textview1;
    Button Button1, Button2;
    ImageButton button2;
    List<Danci> dancis;
    List<Danci> yixuexi;
    List<Danci> weixuexi;
    List<Danci> weijiyi;
    Danci danci = new Danci();
    Danci danci1 = new Danci();
    private int i = 0, a = 0, b = 0;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initWidget();
        action();
//        if (savedInstanceState !=null){
//            i=savedInstanceState.getInt("shuju");
//        }
    }

    /**
    * wenming
    * created by:nianxin
    * created 2017/5/23 23:17.
    * action:加载定义的菜单选项
    */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.danci, menu);
        return true;
    }

    /**
    * wenming
    * created by:nianxin
    * created 2017/5/23 23:17.
    * action:菜单子项的点击事件
    */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.danci:
                a = 1;
                b = 0;
                i = 0;
                Button1.setClickable(true);
                Button2.setClickable(true);
                weixuexi = DataSupport.select("name", "text", "yixue").where("yixue = ?", "0").find(Danci.class);
                astring = weixuexi.get(i).getName();
                bstring = weixuexi.get(i).getText();
                textView1.setText(astring);
                textView2.setText(bstring);
                break;
            case R.id.danci2:
                weijiyi = DataSupport.select("name", "text").where("yixue = ? and yiji= ?", "1", "0").find(Danci.class);
                if (weijiyi.size() != 0) {
                    b = 1;
                    a = 0;
                    i = 0;
                    Button1.setClickable(true);
                    Button2.setClickable(true);
                    astring = weijiyi.get(i).getName();
                    bstring = weijiyi.get(i).getText();
                    textView1.setText(astring);
                    textView2.setText(bstring);
                } else {
                    textView1.setText("真棒！！！");
                    textView2.setText("所有学习得单词均已记忆，不要忘记复习哦！");
                }
                break;
            case R.id.danci3:
                Intent intent = new Intent(DanciYemianActivity.this, DanciLineActivity.class);
                intent.putExtra("if", "1");
                startActivity(intent);
            default:
                break;
        }
        return true;
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:41.
     * action:初始化
     */
    public void initWidget() {
        setContentView(R.layout.danciyemian);
        //引用Toolbar导航栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textview1 = (TextView) findViewById(R.id.textview1);
        Button1 = (Button) findViewById(R.id.Button1);
        Button2 = (Button) findViewById(R.id.Button2);
        Button1.setOnClickListener(this);
        Button2.setOnClickListener(this);
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:45.
     * action:初始化布局后溢出异常的处理以及没有出现异常的工作
     */
    public void action() {
        try {
            yixuexi = DataSupport.select("name", "text", "yixue").where("yixue = ?", "1").find(Danci.class);
            textview1.setText("已学：" + yixuexi.size() + "/1695");
            dancis = DataSupport.select("name", "text", "yixue").where("yixue = ?", "0").find(Danci.class);
            Log.d("这里", "onCreate: " + dancis.size());
            astring = dancis.get(i).getName();
            bstring = dancis.get(i).getText();
            textView1.setText(astring);
            textView2.setText(bstring);
        } catch (Exception e) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(DanciYemianActivity.this);
            dialog.setTitle("提示：");
            dialog.setMessage("太棒了！本地所有单词您均已记忆，您可以长按单词列表来进行清空记忆数据");
            dialog.setCancelable(false);
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            dialog.show();
        }
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, DanciYemianActivity.class);
        context.startActivity(intent);

    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:46.
     * action:点击事件
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Button1:
                try {
                    danci1.setYiji(1);
                    danci1.setYixue(1);
                    danci1.updateAll("name=?", astring);
                    i++;
                    //通过点击菜单传入的a、b值判断点击了哪个菜单，1代表点击了该菜单
                    if (a == 1) {
                        astring = weixuexi.get(i).getName();
                        bstring = weixuexi.get(i).getText();
                        textView1.setText(astring);
                        textView2.setText(bstring);
                    } else if (b == 1) {
                        weijiyi = DataSupport.select("name", "text").where("yixue = ? and yiji= ?", "1", "0").find(Danci.class);
                        Log.d("===========", "onClick: " + weijiyi.size());
                        if (weijiyi.size() == 0) {
                            textView1.setText("真棒！！！");
                            textView2.setText("所有学习得单词均已记忆，不要忘记复习哦！");
                            //所有已经学习而且没有记忆的单词全部被记忆时，设置两个按钮不能点击，只能通过菜单回到没有学习的单词中去
                            Button1.setClickable(false);
                            Button2.setClickable(false);
                        } else {
                            astring = weijiyi.get(0).getName();
                            bstring = weijiyi.get(0).getText();
                            textView1.setText(astring);
                            textView2.setText(bstring);
                        }
                    } else {
                        astring = dancis.get(i).getName();
                        bstring = dancis.get(i).getText();
                        textView1.setText(astring);
                        textView2.setText(bstring);
                    }
                    break;
                } catch (Exception e) {
                }
            case R.id.Button2:
                try {
                    danci1.setYiji(0);
                    danci1.setYixue(1);
                    danci1.updateAll("name=?", astring);
                    i++;
                    //通过点击菜单传入的a、b值判断点击了哪个菜单，1代表点击了该菜单
                    if (a == 1) {
                        astring = weixuexi.get(i).getName();
                        bstring = weixuexi.get(i).getText();
                    } else if (b == 1) {
                        astring = weijiyi.get(i).getName();
                        bstring = weijiyi.get(i).getText();
                    } else {
                        astring = dancis.get(i).getName();
                        bstring = dancis.get(i).getText();
                    }
                    textView1.setText(astring);
                    textView2.setText(bstring);
                    break;
                } catch (Exception e) {
                }

            default:
                break;

        }

        Log.d("这里", "astring:" + astring + "bstring:" + bstring);
        yixuexi = DataSupport.select("yixue").where("yixue = ?", "1").find(Danci.class);
        textview1.setText("已学：" + yixuexi.size() + "/1695");

    }


    @Override
    public void finish() {
        super.finish();
        DanciActivity.actionStart(DanciYemianActivity.this);
    }
}
