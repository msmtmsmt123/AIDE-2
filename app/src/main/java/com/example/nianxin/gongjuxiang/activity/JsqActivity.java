package com.example.nianxin.gongjuxiang.activity;

import android.content.Intent;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nianxin.gongjuxiang.R;
import com.example.nianxin.gongjuxiang.base.BaseActivity;

import java.math.BigDecimal;

public class JsqActivity extends BaseActivity implements OnClickListener {
    double num = 0, num1 = 0, num2 = 0, num3 = 0;
    Button qingchu, shanchu, zhengfu, chu, qi, ba, jiu, cheng, si, wu, liu, jian, yi, er, san, jia, ling, dian, deng;
    TextView textViewjsq;
    String string, fh;

    private static String getStringOutE(String string) {
        BigDecimal bd = new BigDecimal(string);
        return bd.toPlainString();

    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        initWidget();
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 17:10.
     * action:初始化界面
     */
    public void initWidget() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.jsq);
        textViewjsq = (TextView) findViewById(R.id.textViewjsq);
        qingchu = (Button) findViewById(R.id.qingchu);
        qingchu.setOnClickListener(this);
        shanchu = (Button) findViewById(R.id.shanchu);
        shanchu.setOnClickListener(this);
        zhengfu = (Button) findViewById(R.id.zhengfu);
        zhengfu.setOnClickListener(this);
        chu = (Button) findViewById(R.id.chu);
        chu.setOnClickListener(this);
        qi = (Button) findViewById(R.id.qi);
        qi.setOnClickListener(this);
        ba = (Button) findViewById(R.id.ba);
        ba.setOnClickListener(this);
        jiu = (Button) findViewById(R.id.jiu);
        jiu.setOnClickListener(this);
        cheng = (Button) findViewById(R.id.cheng);
        cheng.setOnClickListener(this);
        si = (Button) findViewById(R.id.si);
        si.setOnClickListener(this);
        wu = (Button) findViewById(R.id.wu);
        wu.setOnClickListener(this);
        liu = (Button) findViewById(R.id.liu);
        liu.setOnClickListener(this);
        jian = (Button) findViewById(R.id.jian);
        jian.setOnClickListener(this);
        yi = (Button) findViewById(R.id.yi);
        yi.setOnClickListener(this);
        er = (Button) findViewById(R.id.er);
        er.setOnClickListener(this);
        san = (Button) findViewById(R.id.san);
        san.setOnClickListener(this);
        jia = (Button) findViewById(R.id.jia);
        jia.setOnClickListener(this);
        ling = (Button) findViewById(R.id.ling);
        ling.setOnClickListener(this);
        dian = (Button) findViewById(R.id.dian);
        dian.setOnClickListener(this);
        deng = (Button) findViewById(R.id.deng);
        deng.setOnClickListener(this);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, JsqActivity.class);
        context.startActivity(intent);
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 17:11.
     * action:全部按钮的点击事件（逻辑）,即通过按钮的点击事件来完成运算
     */
    public void onClick(View v) {

        string = textViewjsq.getText().toString();

        switch (v.getId()) {
            case R.id.yi:
                int a = jia.getCurrentTextColor();
                if (jia.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    string = "0";
                    // textViewjsq.setText(string);
                    jia.setTextColor(Color.parseColor("#000000"));
                } else if (jian.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    string = "0";
                    // textViewjsq.setText(string);
                    jian.setTextColor(Color.parseColor("#000000"));
                } else if (cheng.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    string = "0";
                    // textViewjsq.setText(string);
                    cheng.setTextColor(Color.parseColor("#000000"));
                } else if (chu.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    string = "0";
                    // textViewjsq.setText(string);
                    chu.setTextColor(Color.parseColor("#000000"));
                }

                if (string.length() >= 9) {
                    Snackbar.make(v,"长度超过上限！",Snackbar.LENGTH_SHORT).show();
                } else {
                    if (string.equals("0")) {
                        textViewjsq.setText("1");
                    } else {
                        string = string + "1";
                        textViewjsq.setText(string);
                    }
                }
                break;
            case R.id.er:
                if (jia.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    string = "0";
                    // textViewjsq.setText(string);
                    jia.setTextColor(Color.parseColor("#000000"));
                } else if (jian.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    string = "0";
                    // textViewjsq.setText(string);
                    jian.setTextColor(Color.parseColor("#000000"));
                } else if (cheng.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    string = "0";
                    // textViewjsq.setText(string);
                    cheng.setTextColor(Color.parseColor("#000000"));
                } else if (chu.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    string = "0";
                    // textViewjsq.setText(string);
                    chu.setTextColor(Color.parseColor("#000000"));
                }

                if (string.length() >= 9) {
                    Snackbar.make(v,"长度超过上限！",Snackbar.LENGTH_SHORT).show();
                } else {
                    if (string.equals("0")) {
                        textViewjsq.setText("2");
                    } else {
                        string = string + "2";
                        textViewjsq.setText(string);
                    }
                }
                break;
            case R.id.san:
                if (jia.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    string = "0";
                    // textViewjsq.setText(string);
                    jia.setTextColor(Color.parseColor("#000000"));
                } else if (jian.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    jian.setTextColor(Color.parseColor("#000000"));
                } else if (cheng.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    cheng.setTextColor(Color.parseColor("#000000"));
                } else if (chu.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    chu.setTextColor(Color.parseColor("#000000"));
                }

                if (string.length() >= 9) {
                    Snackbar.make(v,"长度超过上限！",Snackbar.LENGTH_SHORT).show();
                } else {
                    if (string.equals("0")) {
                        textViewjsq.setText("3");
                    } else {
                        string = string + "3";
                        textViewjsq.setText(string);
                    }
                }
                break;
            case R.id.si:
                if (jia.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    string = "0";
                    // textViewjsq.setText(string);
                    jia.setTextColor(Color.parseColor("#000000"));
                } else if (jian.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    jian.setTextColor(Color.parseColor("#000000"));
                } else if (cheng.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    cheng.setTextColor(Color.parseColor("#000000"));
                } else if (chu.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    chu.setTextColor(Color.parseColor("#000000"));
                }

                if (string.length() >= 9) {
                    Snackbar.make(v,"长度超过上限！",Snackbar.LENGTH_SHORT).show();
                } else {
                    if (string.equals("0")) {
                        textViewjsq.setText("4");
                    } else {
                        string = string + "4";
                        textViewjsq.setText(string);
                    }
                }
                break;
            case R.id.wu:
                if (jia.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    jia.setTextColor(Color.parseColor("#000000"));
                } else if (jian.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    jian.setTextColor(Color.parseColor("#000000"));
                } else if (cheng.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    cheng.setTextColor(Color.parseColor("#000000"));
                } else if (chu.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    chu.setTextColor(Color.parseColor("#000000"));
                }

                if (string.length() >= 9) {
                    Snackbar.make(v,"长度超过上限！",Snackbar.LENGTH_SHORT).show();
                } else {
                    if (string.equals("0")) {
                        textViewjsq.setText("5");
                    } else {
                        string = string + "5";
                        textViewjsq.setText(string);
                    }
                }
                break;
            case R.id.liu:

                if (jia.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    jia.setTextColor(Color.parseColor("#000000"));
                } else if (jian.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    jian.setTextColor(Color.parseColor("#000000"));
                } else if (cheng.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    cheng.setTextColor(Color.parseColor("#000000"));
                } else if (chu.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    chu.setTextColor(Color.parseColor("#000000"));
                }

                if (string.length() >= 9) {
                    Snackbar.make(v,"长度超过上限！",Snackbar.LENGTH_SHORT).show();
                } else {
                    if (string.equals("0")) {
                        textViewjsq.setText("6");
                    } else {
                        string = string + "6";
                        textViewjsq.setText(string);
                    }
                }
                break;
            case R.id.qi:
                if (jia.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    jia.setTextColor(Color.parseColor("#000000"));
                } else if (jian.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    jian.setTextColor(Color.parseColor("#000000"));
                } else if (cheng.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    cheng.setTextColor(Color.parseColor("#000000"));
                } else if (chu.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    chu.setTextColor(Color.parseColor("#000000"));
                }

                if (string.length() >= 9) {
                    Snackbar.make(v,"长度超过上限！",Snackbar.LENGTH_SHORT).show();
                } else {
                    if (string.equals("0")) {
                        textViewjsq.setText("7");
                    } else {
                        string = string + "7";
                        textViewjsq.setText(string);
                    }
                }
                break;
            case R.id.ba:
                if (jia.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    jia.setTextColor(Color.parseColor("#000000"));
                } else if (jian.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    jian.setTextColor(Color.parseColor("#000000"));
                } else if (cheng.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    cheng.setTextColor(Color.parseColor("#000000"));
                } else if (chu.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    chu.setTextColor(Color.parseColor("#000000"));
                }

                if (string.length() >= 9) {
                    Snackbar.make(v,"长度超过上限！",Snackbar.LENGTH_SHORT).show();
                } else {
                    if (string.equals("0")) {
                        textViewjsq.setText("8");
                    } else {
                        string = string + "8";
                        textViewjsq.setText(string);
                    }
                }
                break;
            case R.id.jiu:
                if (jia.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    jia.setTextColor(Color.parseColor("#000000"));
                } else if (jian.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    jian.setTextColor(Color.parseColor("#000000"));
                } else if (cheng.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    cheng.setTextColor(Color.parseColor("#000000"));
                } else if (chu.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    // textViewjsq.setText(string);
                    chu.setTextColor(Color.parseColor("#000000"));
                }

                if (string.length() >= 9) {
                    Snackbar.make(v,"长度超过上限！",Snackbar.LENGTH_SHORT).show();
                } else {
                    if (string.equals("0")) {
                        textViewjsq.setText("9");
                    } else {
                        string = string + "9";
                        textViewjsq.setText(string);
                    }
                }
                break;
            case R.id.ling:
                if (jia.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    textViewjsq.setText(string);
                    jia.setTextColor(Color.parseColor("#000000"));
                } else if (jian.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    textViewjsq.setText(string);
                    jian.setTextColor(Color.parseColor("#000000"));
                } else if (cheng.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    textViewjsq.setText(string);
                    cheng.setTextColor(Color.parseColor("#000000"));
                } else if (chu.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    //
                    string = "0";
                    textViewjsq.setText(string);
                    chu.setTextColor(Color.parseColor("#000000"));
                }

                if (string.length() >= 9) {
                    Snackbar.make(v,"长度超过上限！",Snackbar.LENGTH_SHORT).show();
                } else {
                    if (string.equals("0")) {
                    } else {
                        string = string + "0";
                        textViewjsq.setText(string);
                    }
                }
                break;
            case R.id.dian:
                if (jia.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    string = "0";
                    textViewjsq.setText(string);
                    jia.setTextColor(Color.parseColor("#000000"));
                } else if (jian.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    string = "0";
                    textViewjsq.setText(string);
                    jian.setTextColor(Color.parseColor("#000000"));
                } else if (cheng.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    string = "0";
                    textViewjsq.setText(string);
                    cheng.setTextColor(Color.parseColor("#000000"));
                } else if (chu.getCurrentTextColor() == -1) {
                    // string = textViewjsq.getText().toString();
                    // num1 = Double.parseDouble(string);
                    string = "0";
                    textViewjsq.setText(string);
                    chu.setTextColor(Color.parseColor("#000000"));
                }

                if (string.length() >= 9) {
                    Snackbar.make(v,"长度超过上限！",Snackbar.LENGTH_SHORT).show();
                } else {
                    if (string.equals("0")) {
                        string = string + ".";
                        textViewjsq.setText(string);
                    } else if (string.indexOf(".") != -1) {
                    } else {
                        string = string + ".";
                        textViewjsq.setText(string);
                    }
                }
                break;
            case R.id.deng:
                try {
                    jia.setTextColor(Color.parseColor("#000000"));
                    jian.setTextColor(Color.parseColor("#000000"));
                    cheng.setTextColor(Color.parseColor("#000000"));
                    chu.setTextColor(Color.parseColor("#000000"));
                    string = textViewjsq.getText().toString();
                    num2 = Double.parseDouble(string);
                    if (fh.equals("+")) {
                        num3 = num1 + num2;
                        string = Double.toString(num3);
                        if (string.substring(string.length() - 1).equals("0")) {
                            string = string.substring(0, string.length() - 2);
                        } else if (string.length() >= 9) {
                            string = String.valueOf(num3);
                            string = getStringOutE(string);
                            int x = string.lastIndexOf("0");
                            string = String
                                    .valueOf(new BigDecimal(num3).setScale(x + 2, BigDecimal.ROUND_HALF_UP).doubleValue());
                            string = getStringOutE(string);
                        }

                        textViewjsq.setText(string);
                        string = "0";
                    } else if (fh.equals("-")) {
                        num3 = num1 - num2;
                        string = Double.toString(num3);
                        if (string.substring(string.length() - 1).equals("0")) {
                            string = string.substring(0, string.length() - 2);
                        } else if (string.length() >= 9) {
                            string = String.valueOf(num3);
                            string = getStringOutE(string);
                            int x = string.lastIndexOf("0");
                            string = String
                                    .valueOf(new BigDecimal(num3).setScale(x + 2, BigDecimal.ROUND_HALF_UP).doubleValue());
                            string = getStringOutE(string);
                        }

                        textViewjsq.setText(string);
                        string = "0";
                    } else if (fh.equals("*")) {
                        num3 = num1 * num2;
                        string = Double.toString(num3);
                        if (string.substring(string.length() - 1).equals("0")) {
                            string = string.substring(0, string.length() - 2);
                        } else if (string.length() >= 9) {
                            string = String.valueOf(num3);
                            string = getStringOutE(string);
                            int x = string.lastIndexOf("0");
                            string = String
                                    .valueOf(new BigDecimal(num3).setScale(x + 2, BigDecimal.ROUND_HALF_UP).doubleValue());
                            string = getStringOutE(string);
                        }

                        textViewjsq.setText(string);
                        string = "0";
                    } else if (fh.equals("/")) {
                        num3 = num1 / num2;
                        string = Double.toString(num3);
                        string = getStringOutE(string);
                        if (string.substring(string.length() - 1).equals("0")) {
                            string = string.substring(0, string.length() - 2);
                            Log.d("num3", "num3=" + num3);
                        } else if (string.length() >= 9) {
                            string = String.valueOf(num3);
                            string = getStringOutE(string);
                            int x = string.lastIndexOf("0");
                            string = String
                                    .valueOf(new BigDecimal(num3).setScale(x + 2, BigDecimal.ROUND_HALF_UP).doubleValue());
                            string = getStringOutE(string);
                        }

                        textViewjsq.setText(string);
                        string = "0";
                    }
                } catch (Exception e) {

                }
                break;
            case R.id.qingchu:
                jia.setTextColor(Color.parseColor("#000000"));
                jian.setTextColor(Color.parseColor("#000000"));
                cheng.setTextColor(Color.parseColor("#000000"));
                chu.setTextColor(Color.parseColor("#000000"));
                if (string.equals("0")) {
                } else {
                    string = "0";
                    num1 = 0;
                    num2 = 0;
                    num3 = 0;
                    num = 0;
                    textViewjsq.setText(string);
                }
                break;
            case R.id.shanchu:
                jia.setTextColor(Color.parseColor("#000000"));
                jian.setTextColor(Color.parseColor("#000000"));
                cheng.setTextColor(Color.parseColor("#000000"));
                chu.setTextColor(Color.parseColor("#000000"));
                if (string.equals("0")) {
                } else if (string.length() == 1) {
                    string = "0";
                    textViewjsq.setText(string);
                } else {
                    string = string.substring(0, string.length() - 1);
                    textViewjsq.setText(string);
                }
                break;
            case R.id.jia:
                jian.setTextColor(Color.parseColor("#000000"));
                cheng.setTextColor(Color.parseColor("#000000"));
                chu.setTextColor(Color.parseColor("#000000"));
                jia.setTextColor(Color.parseColor("#ffffff"));
                if (num1 == 0) {
                    string = textViewjsq.getText().toString();
                    num1 = Double.parseDouble(string);

                    string = "0";
                } else {
                    string = textViewjsq.getText().toString();
                    num = Double.parseDouble(string);

                    if (fh.equals("+")) {
                        num1 = num1 + num;
                    } else if (fh.equals("-")) {
                        num1 = num1 - num;
                    } else if (fh.equals("*")) {
                        num1 = num1 * num;
                    } else {
                        num1 = num1 / num;
                    }
                    string = String.valueOf(num1);
                    if (string.substring(string.length() - 1).equals("0")) {
                        string = string.substring(0, string.length() - 2);
                    } else if (string.length() >= 9) {
                        string = String.valueOf(num1);
                        string = getStringOutE(string);
                        int x = string.lastIndexOf("0");
                        string = String
                                .valueOf(new BigDecimal(num1).setScale(x + 3, BigDecimal.ROUND_HALF_UP).doubleValue());
                        string = getStringOutE(string);
                    }

                    textViewjsq.setText(string);
                }
                fh = "+";
                break;
            case R.id.jian:
                jia.setTextColor(Color.parseColor("#000000"));
                cheng.setTextColor(Color.parseColor("#000000"));
                chu.setTextColor(Color.parseColor("#000000"));
                jian.setTextColor(Color.parseColor("#ffffff"));
                if (num1 == 0) {
                    string = textViewjsq.getText().toString();
                    num1 = Double.parseDouble(string);
                    string = "0";
                } else {
                    string = textViewjsq.getText().toString();
                    num = Double.parseDouble(string);
                    if (fh.equals("+")) {
                        num1 = num1 + num;
                    } else if (fh.equals("-")) {
                        num1 = num1 - num;
                    } else if (fh.equals("*")) {
                        num1 = num1 * num;
                    } else {
                        num1 = num1 / num;
                    }
                    string = String.valueOf(num1);
                    if (string.substring(string.length() - 1).equals("0")) {
                        string = string.substring(0, string.length() - 2);
                    } else if (string.length() >= 9) {
                        string = String.valueOf(num1);
                        string = getStringOutE(string);
                        int x = string.lastIndexOf("0");
                        string = String
                                .valueOf(new BigDecimal(num1).setScale(x + 3, BigDecimal.ROUND_HALF_UP).doubleValue());
                        string = getStringOutE(string);
                    }

                    textViewjsq.setText(string);
                }

                fh = "-";
                break;
            case R.id.cheng:
                jia.setTextColor(Color.parseColor("#000000"));
                jian.setTextColor(Color.parseColor("#000000"));
                chu.setTextColor(Color.parseColor("#000000"));
                cheng.setTextColor(Color.parseColor("#ffffff"));
                if (num1 == 0) {
                    string = textViewjsq.getText().toString();
                    num1 = Double.parseDouble(string);
                    string = "0";
                } else {
                    string = textViewjsq.getText().toString();
                    num = Double.parseDouble(string);
                    if (fh.equals("+")) {
                        num1 = num1 + num;
                    } else if (fh.equals("-")) {
                        num1 = num1 - num;
                    } else if (fh.equals("*")) {
                        num1 = num1 * num;
                    } else {
                        num1 = num1 / num;
                    }
                    string = String.valueOf(num1);
                    if (string.substring(string.length() - 1).equals("0")) {
                        string = string.substring(0, string.length() - 2);
                    } else if (string.length() >= 9) {
                        string = String.valueOf(num1);
                        string = getStringOutE(string);
                        int x = string.lastIndexOf("0");
                        string = String
                                .valueOf(new BigDecimal(num1).setScale(x + 3, BigDecimal.ROUND_HALF_UP).doubleValue());
                        string = getStringOutE(string);
                    }

                    textViewjsq.setText(string);
                }
                fh = "*";
                break;
            case R.id.chu:
                jia.setTextColor(Color.parseColor("#000000"));
                jian.setTextColor(Color.parseColor("#000000"));
                cheng.setTextColor(Color.parseColor("#000000"));
                chu.setTextColor(Color.parseColor("#ffffff"));
                try {
                    if (num1 == 0) {
                        string = textViewjsq.getText().toString();

                        num1 = Double.parseDouble(string);
                        string = "0";
                    } else {
                        string = textViewjsq.getText().toString();
                        num = Double.parseDouble(string);

                        if (fh.equals("+")) {
                            num1 = num1 + num;
                        } else if (fh.equals("-")) {
                            num1 = num1 - num;
                        } else if (fh.equals("*")) {
                            num1 = num1 * num;
                        } else {
                            num1 = num1 / num;
                        }

                        string = String.valueOf(num1);
                        if (string.substring(string.length() - 1).equals("0")) {
                            string = string.substring(0, string.length() - 2);
                        } else if (string.length() >= 9) {
                            string = String.valueOf(num1);
                            string = getStringOutE(string);
                            int x = string.lastIndexOf("0");
                            string = String
                                    .valueOf(new BigDecimal(num1).setScale(x + 2, BigDecimal.ROUND_HALF_UP).doubleValue());
                            string = getStringOutE(string);
                        }

                        textViewjsq.setText(string);
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }

                fh = "/";
                break;
            case R.id.zhengfu:
                if (jia.getCurrentTextColor() == -1) {
                    string = textViewjsq.getText().toString();
                    num1 = Double.parseDouble(string);
                    string = "0";
                    textViewjsq.setText(string);
                    jia.setTextColor(Color.parseColor("#000000"));
                } else if (jian.getCurrentTextColor() == -1) {
                    string = textViewjsq.getText().toString();
                    num1 = Double.parseDouble(string);
                    string = "0";
                    textViewjsq.setText(string);
                    jian.setTextColor(Color.parseColor("#000000"));
                } else if (cheng.getCurrentTextColor() == -1) {
                    string = textViewjsq.getText().toString();
                    num1 = Double.parseDouble(string);
                    string = "0";
                    textViewjsq.setText(string);
                    cheng.setTextColor(Color.parseColor("#000000"));
                } else if (chu.getCurrentTextColor() == -1) {
                    string = textViewjsq.getText().toString();
                    num1 = Double.parseDouble(string);
                    string = "0";
                    textViewjsq.setText(string);
                    chu.setTextColor(Color.parseColor("#000000"));
                }
                if (string.equals("0")) {
                } else if (string.indexOf("-") != -1) {
                    string = string.substring(1, string.length());
                } else if (string.length() > 9) {
                    Snackbar.make(v,"长度超过上限！",Snackbar.LENGTH_SHORT).show();
                } else {
                    string = "-" + string;
                    textViewjsq.setText(string);
                }
                textViewjsq.setText(string);
                break;
            default:
                break;
        }
    }

}
