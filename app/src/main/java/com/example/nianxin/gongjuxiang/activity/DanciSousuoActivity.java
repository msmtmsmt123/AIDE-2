package com.example.nianxin.gongjuxiang.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.nianxin.gongjuxiang.R;
import com.example.nianxin.gongjuxiang.adapter.DanCi;
import com.example.nianxin.gongjuxiang.adapter.DanCiAdapter;
import com.example.nianxin.gongjuxiang.base.BaseActivity;
import com.example.nianxin.gongjuxiang.db.Danci;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nianxin on 2017/4/25.
 */

public class DanciSousuoActivity extends BaseActivity implements View.OnClickListener {
    int i = 0;
    private ImageButton button1, imageButton;
    private Button btn1;
    private EditText editText1;
    String astring = null;
    private List<DanCi> dancis = new ArrayList<>();
    private List<Danci> danciss = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWidget();
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:38.
     * action:初始化
     */
    public void initWidget() {
        setContentView(R.layout.dancisousuo);
        editText1 = (EditText) findViewById(R.id.edittext1);
        btn1 = (Button) findViewById(R.id.btn1);
        imageButton = (ImageButton) findViewById(R.id.image1);
        imageButton.setOnClickListener(this);
        btn1.setOnClickListener(this);
        //edittext添加上一个发生变化监听器
        editText1.addTextChangedListener(textWatcher);
        imageButton.setVisibility(View.GONE);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, DanciSousuoActivity.class);
        context.startActivity(intent);
    }


 /**
 * wenming
 * created by:nianxin
 * created 2017/5/20 16:39.
 * action:点击事件
 */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                finish();
                break;
            case R.id.image1:
                astring = "";
                editText1.setText("");
        }
    }

    /**
    * wenming
    * created by:nianxin
    * created 2017/5/20 16:40.
    * action:监听文本框变化
    */
    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            Log.d("TAG", "afterTextChanged--------------->");
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
            Log.d("TAG", "beforeTextChanged--------------->");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            i = 0;
            astring = editText1.getText().toString();
            //edittext不为空时，执行查询命令，并写进所查询数据集合外的另一个list，并传给适配器，写入布局页面
            if (!(astring.equals(""))) {
//                    imageButton.setImageResource(R.drawable.ic_cancel_white_24dp);
                imageButton.setVisibility(View.VISIBLE);
                if (danciss.size() != 0 && dancis.size() != 0) {
                    for (int j = dancis.size() - 1; j >= 0; j--) {
                        dancis.remove(j);
                    }
                    for (int k = danciss.size() - 1; k >= 0; k--) {
                        danciss.remove(k);
                    }
                }
                Log.d("集合1", "onClick: " + dancis.size());
                Log.d("集合2", "onClick: " + danciss.size());
                danciss = DataSupport.select("name", "text").where("name like ?", astring + "%").find(Danci.class);
                if (danciss.size() == 0) {
                    danciss = DataSupport.select("name", "text").where("text like ?", "%" + astring + "%").find(Danci.class);
                }
                Log.d("此处", "onClick: " + danciss.size());
                for (Danci dan : danciss) {
                    DanCi dancishuju = new DanCi(dan.getName(), dan.getText());
                    dancis.add(i, dancishuju);
                    i++;
                }
                Log.d("", "onClick: " + dancis.size());
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                DanCiAdapter adapter = new DanCiAdapter(dancis);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } else {
                imageButton.setVisibility(View.GONE);
                //清空edittext时把list清空，已达到清空页面数据的效果
                Log.d("", "onTextChanged: " + "运行到这里了");
                if (danciss.size() != 0 && dancis.size() != 0) {
                    for (int j = dancis.size() - 1; j >= 0; j--) {
                        dancis.remove(j);
                    }
                    for (int k = danciss.size() - 1; k >= 0; k--) {
                        danciss.remove(k);
                    }
                }
                for (Danci dan : danciss) {
                    DanCi dancishuju = new DanCi(dan.getName(), dan.getText());
                    dancis.add(i, dancishuju);
                    i++;
                }
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                DanCiAdapter adapter = new DanCiAdapter(dancis);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    public void finish() {
        super.finish();
        DanciActivity.actionStart(DanciSousuoActivity.this);
    }
}

