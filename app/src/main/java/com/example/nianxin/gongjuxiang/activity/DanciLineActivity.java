package com.example.nianxin.gongjuxiang.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.AppLaunchChecker;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nianxin.gongjuxiang.R;
import com.example.nianxin.gongjuxiang.adapter.DanCi;
import com.example.nianxin.gongjuxiang.adapter.DanCilineAdapter;
import com.example.nianxin.gongjuxiang.base.BaseActivity;
import com.example.nianxin.gongjuxiang.db.Danci;
import com.example.nianxin.gongjuxiang.implement.initWidgetInterface;
import com.example.nianxin.gongjuxiang.my.MyFragment;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nianxin on 2017/4/19.
 */

public class DanciLineActivity extends AppCompatActivity implements View.OnClickListener ,initWidgetInterface {
    //fragment
    private MyFragment f1, f2, f3, f4, f5;
    //按钮
    private Button b1, b2, b3, b4, b5;
    public  TextView TV2;
    private TextView tv1;
    private ImageButton btn1;
    private TextView textView1;
    private List<DanCi> dcs = new ArrayList<>();
    private List<Danci> danciss = new ArrayList<>();
    private int i = 0;
    private String a;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWidget();
        //第一次初始化首页默认显示第一个fragment
        initFragment1();
        textView1.setText("单词数：" + dcs.size());

    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:28.
     * action:初始化
     */
    public void initWidget() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.danciliebiao);
        b1 = (Button) findViewById(R.id.btn1);
        b2 = (Button) findViewById(R.id.btn2);
        b3 = (Button) findViewById(R.id.btn3);
        b4 = (Button) findViewById(R.id.btn4);
        b5 = (Button) findViewById(R.id.btn5);
        TV2 = (TextView) findViewById(R.id.TV2);
        recyclerView = (RecyclerView) findViewById(R.id.Recycler_view1);
        textView1 = (TextView) findViewById(R.id.textv1);
        btn1 = (ImageButton) findViewById(R.id.button2);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        btn1.setVisibility(View.GONE);
        tv1 = (TextView) findViewById(R.id.textview1);
        tv1.setText("单词列表");


    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:32.
     * action:设置五个按钮的背景颜色
     */
    public void color() {
        b1.setBackgroundColor(Color.parseColor("#FFE1D9BE"));
        b2.setBackgroundColor(Color.parseColor("#FFE1D9BE"));
        b3.setBackgroundColor(Color.parseColor("#FFE1D9BE"));
        b4.setBackgroundColor(Color.parseColor("#FFE1D9BE"));
        b5.setBackgroundColor(Color.parseColor("#FFE1D9BE"));
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:36.
     * action:显示Fragment
     */
    //显示第一个fragment
    private void initFragment1() {
        b1.setBackgroundColor(Color.parseColor("#FFE7DFCC"));
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if (f1 == null) {
            if (danciss.size() != 0 && dcs.size() != 0) {
                for (int j = dcs.size() - 1; j >= 0; j--) {
                    dcs.remove(j);
                }
                for (int k = danciss.size() - 1; k >= 0; k--) {
                    danciss.remove(k);
                }
            }
            danciss = DataSupport.select("name", "text").where("yiji = ?", "1").find(Danci.class);
            for (Danci dan : danciss) {
                DanCi dancishuju = new DanCi(dan.getName(), dan.getText());
                dcs.add(i, dancishuju);
                i++;
            }
            f1 = new MyFragment(dcs);
            Log.d("这里这里这里", "initFragment1: " + dcs.size());
            transaction.add(R.id.main_frame_layout, f1);

        } else {
            if (danciss.size() != 0 && dcs.size() != 0) {
                for (int j = dcs.size() - 1; j >= 0; j--) {
                    dcs.remove(j);
                }
                for (int k = danciss.size() - 1; k >= 0; k--) {
                    danciss.remove(k);
                }
            }
            danciss = DataSupport.select("name", "text").where("yiji = ?", "1").find(Danci.class);
            for (Danci dan : danciss) {
                DanCi dancishuju = new DanCi(dan.getName(), dan.getText());
                dcs.add(i, dancishuju);
                i++;
            }
            f1 = new MyFragment(dcs);
            Log.d("这里这里这里", "initFragment1: " + dcs.size());
            transaction.replace(R.id.main_frame_layout, f1);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(f1);
        //提交事务
        transaction.commit();
    }

    //显示第二个fragment
    private void initFragment2() {
        b2.setBackgroundColor(Color.parseColor("#FFE7DFCC"));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (f2 == null) {
            if (danciss.size() != 0 && dcs.size() != 0) {
                for (int j = dcs.size() - 1; j >= 0; j--) {
                    dcs.remove(j);
                }
                Log.d("---------", "initFragment2: " + dcs.size());
                for (int k = danciss.size() - 1; k >= 0; k--) {
                    danciss.remove(k);
                }
                Log.d("==========", "initFragment2: " + danciss.size());
            }
            danciss = DataSupport.select("name", "text").where("yixue = ?", "1").find(Danci.class);
            for (Danci dan : danciss) {
                DanCi dancishuju = new DanCi(dan.getName(), dan.getText());
                dcs.add(i, dancishuju);
                i++;
            }
            Log.d("这里这里这里", "initFragment1: " + dcs.size());
            f2 = new MyFragment(dcs);
            transaction.add(R.id.main_frame_layout, f2);
        } else {
            if (danciss.size() != 0 && dcs.size() != 0) {
                for (int j = dcs.size() - 1; j >= 0; j--) {
                    dcs.remove(j);
                }
                Log.d("---------", "initFragment2: " + dcs.size());
                for (int k = danciss.size() - 1; k >= 0; k--) {
                    danciss.remove(k);
                }
                Log.d("==========", "initFragment2: " + danciss.size());
            }
            danciss = DataSupport.select("name", "text").where("yixue = ?", "1").find(Danci.class);
            for (Danci dan : danciss) {
                DanCi dancishuju = new DanCi(dan.getName(), dan.getText());
                dcs.add(i, dancishuju);
                i++;
            }
            Log.d("这里这里这里", "initFragment1: " + dcs.size());
            f2 = new MyFragment(dcs);
            transaction.replace(R.id.main_frame_layout, f2);
        }
        hideFragment(transaction);
        transaction.show(f2);
        transaction.commit();

    }

    //显示第三个fragment
    private void initFragment3() {
        b3.setBackgroundColor(Color.parseColor("#FFE7DFCC"));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (f3 == null) {
            if (danciss.size() != 0 && dcs.size() != 0) {
                for (int j = dcs.size() - 1; j >= 0; j--) {
                    dcs.remove(j);
                }
                for (int k = danciss.size() - 1; k >= 0; k--) {
                    danciss.remove(k);
                }
            }
            danciss = DataSupport.select("name", "text").where("yixue = ?", "0").find(Danci.class);
            for (Danci dan : danciss) {
                DanCi dancishuju = new DanCi(dan.getName(), dan.getText());
                dcs.add(i, dancishuju);
                i++;
            }
            Log.d("这里这里这里", "initFragment1: " + dcs.size());
            f3 = new MyFragment(dcs);
            transaction.add(R.id.main_frame_layout, f3);
        } else {
            if (danciss.size() != 0 && dcs.size() != 0) {
                for (int j = dcs.size() - 1; j >= 0; j--) {
                    dcs.remove(j);
                }
                for (int k = danciss.size() - 1; k >= 0; k--) {
                    danciss.remove(k);
                }
            }
            danciss = DataSupport.select("name", "text").where("yixue = ?", "0").find(Danci.class);
            for (Danci dan : danciss) {
                DanCi dancishuju = new DanCi(dan.getName(), dan.getText());
                dcs.add(i, dancishuju);
                i++;
            }
            Log.d("这里这里这里", "initFragment1: " + dcs.size());
            f3 = new MyFragment(dcs);
            transaction.replace(R.id.main_frame_layout, f3);
        }
        hideFragment(transaction);
        transaction.show(f3);
        transaction.commit();

    }

    //显示第四个fragment
    private void initFragment4() {
        b4.setBackgroundColor(Color.parseColor("#FFE7DFCC"));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (f4 == null) {
            if (danciss.size() != 0 && dcs.size() != 0) {
                for (int j = dcs.size() - 1; j >= 0; j--) {
                    dcs.remove(j);
                }
                for (int k = danciss.size() - 1; k >= 0; k--) {
                    danciss.remove(k);
                }
            }
            danciss = DataSupport.select("name", "text").where("yiji != ?", "3").find(Danci.class);
            for (Danci dan : danciss) {
                DanCi dancishuju = new DanCi(dan.getName(), dan.getText());
                dcs.add(i, dancishuju);
                i++;
            }
            Log.d("这里这里这里", "initFragment1: " + dcs.size());
            f4 = new MyFragment(dcs);
            transaction.add(R.id.main_frame_layout, f4);
        } else {
            if (danciss.size() != 0 && dcs.size() != 0) {
                for (int j = dcs.size() - 1; j >= 0; j--) {
                    dcs.remove(j);
                }
                for (int k = danciss.size() - 1; k >= 0; k--) {
                    danciss.remove(k);
                }
            }
            danciss = DataSupport.select("name", "text").where("yiji != ?", "3").find(Danci.class);
            for (Danci dan : danciss) {
                DanCi dancishuju = new DanCi(dan.getName(), dan.getText());
                dcs.add(i, dancishuju);
                i++;
            }
            Log.d("这里这里这里", "initFragment1: " + dcs.size());
            f4 = new MyFragment(dcs);
            transaction.replace(R.id.main_frame_layout, f4);
        }
        hideFragment(transaction);
        transaction.show(f4);
        transaction.commit();

    }

    //显示第五个fragment
    private void initFragment5() {
        b5.setBackgroundColor(Color.parseColor("#FFE7DFCC"));
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (f5 == null) {
            if (danciss.size() != 0 && dcs.size() != 0) {
                for (int j = dcs.size() - 1; j >= 0; j--) {
                    dcs.remove(j);
                }
                for (int k = danciss.size() - 1; k >= 0; k--) {
                    danciss.remove(k);
                }
            }
            danciss = DataSupport.select("name", "text").where("yixue = ? and yiji= ?", "1", "0").find(Danci.class);
            for (Danci dan : danciss) {
                DanCi dancishuju = new DanCi(dan.getName(), dan.getText());
                dcs.add(i, dancishuju);
                i++;
            }
            f5 = new MyFragment(dcs);
            transaction.add(R.id.main_frame_layout, f5);
        } else {
            if (danciss.size() != 0 && dcs.size() != 0) {
                for (int j = dcs.size() - 1; j >= 0; j--) {
                    dcs.remove(j);
                }
                for (int k = danciss.size() - 1; k >= 0; k--) {
                    danciss.remove(k);
                }
            }
            danciss = DataSupport.select("name", "text").where("yixue = ? and yiji= ?", "1", "0").find(Danci.class);
            for (Danci dan : danciss) {
                DanCi dancishuju = new DanCi(dan.getName(), dan.getText());
                dcs.add(i, dancishuju);
                i++;
            }
            Log.d("这里这里这里", "initFragment1: " + dcs.size());
            f5 = new MyFragment(dcs);
            transaction.replace(R.id.main_frame_layout, f5);
        }
        hideFragment(transaction);
        transaction.show(f5);
        transaction.commit();

    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:35.
     * action:隐藏所有Fragment
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (f1 != null) {
            transaction.hide(f1);
        }
        if (f2 != null) {
            transaction.hide(f2);
        }
        if (f3 != null) {
            transaction.hide(f3);
        }
        if (f4 != null) {
            transaction.hide(f4);
        }
        if (f5 != null) {
            transaction.hide(f5);
        }
    }


    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:38.
     * action:点击事件
     */
    public void onClick(View v) {
        color();
        i = 0;
        if (v == b1) {
            initFragment1();
        } else if (v == b2) {
            initFragment2();
        } else if (v == b3) {
            initFragment3();
        } else if (v == b4) {
            initFragment4();
        } else if (v == b5) {
            initFragment5();
        }
        textView1.setText("单词数：" + dcs.size());
    }



    @Override
    public void finish() {
        super.finish();
        Intent intent = getIntent();
        String a = intent.getStringExtra("if");
        Log.d("", "finish: " + a);
        if (a.equals("1")) {
            DanciYemianActivity.actionStart(DanciLineActivity.this);
        } else {
            DanciActivity.actionStart(DanciLineActivity.this);
        }
    }

    public static void actionStart(Context context, String a) {
        Intent intent = new Intent(context, DanciLineActivity.class);
        intent.putExtra("if", a);
        context.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.danci_gone,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.danci:
                Toast.makeText(DanciLineActivity.this,"点击了菜单1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.danci2:
                Toast.makeText(DanciLineActivity.this,"点击了菜单2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.danci3:
                Toast.makeText(DanciLineActivity.this,"点击了菜单3",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}
