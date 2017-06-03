package com.example.nianxin.gongjuxiang.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nianxin.gongjuxiang.R;
import com.example.nianxin.gongjuxiang.adapter.DanCi;
import com.example.nianxin.gongjuxiang.adapter.DanCiAdapter;
import com.example.nianxin.gongjuxiang.base.BaseActivity;
import com.example.nianxin.gongjuxiang.db.Danci;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.Random;

public class SousuoActivity extends BaseActivity implements View.OnClickListener {
    private SwipeRefreshLayout swipeRefresh;
    private String astring = null, bstring = null, cstring = null, dstring = null,estring;
    private DecimalFormat df;
    private double yixiazai = 0;
    private double progress;
    private File file;
    private WebView webView = null;
    private ProgressDialog progressDialog;
    private LinearLayout linearLayoutinput, linearLayoutnoinput;
    private EditText editText;
    private Button bt_quxiao;
    private TextView edit_oninput;
    private ImageButton button1, button2, bt_back, bt_next, bt_home, bt_shuaxin,image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWidget();
        edit_action();
//        //设置刷新事件
//        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                if(!refreshLayout.canChildScrollUp()) {
//                    bstring = webView.getUrl();
//                    webView.loadUrl(bstring);
//                    refreshLayout.setRefreshing(false);
//                }
//            }
//        });
//        refreshLayout.setCanChildScrollUpCallback(this);
//        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            public void onRefresh() {
//                bstring = webView.getUrl();
//                webView.loadUrl(bstring);
//                swipeRefresh.setRefreshing(false);
//            }
//        });


    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 17:17.
     * action:初始化页面
     */
    public void initWidget() {

        this.setContentView(R.layout.sousuo_pk);
        webView = (WebView) this.findViewById(R.id.webview1);
        final WebSettings webSettings = webView.getSettings();
        editText = (EditText) findViewById(R.id.edittext1);
        button1 = (ImageButton) findViewById(R.id.button1);
        button2 = (ImageButton) findViewById(R.id.button2);
        bt_back = (ImageButton) findViewById(R.id.bt_back);
        bt_next = (ImageButton) findViewById(R.id.bt_next);
        bt_home = (ImageButton) findViewById(R.id.bt_home);
       image=(ImageButton)findViewById(R.id.image1);
        bt_shuaxin = (ImageButton) findViewById(R.id.bt_shuaxin);
        bt_quxiao = (Button) findViewById(R.id.bt_quxiao);
        edit_oninput = (TextView) findViewById(R.id.edittext1_noinput);
        linearLayoutinput = (LinearLayout) findViewById(R.id.line_input);
        linearLayoutnoinput = (LinearLayout) findViewById(R.id.line_noinput);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        edit_oninput.setOnClickListener(this);
        bt_shuaxin.setOnClickListener(this);
        bt_next.setOnClickListener(this);
        bt_back.setOnClickListener(this);
        bt_home.setOnClickListener(this);
        bt_quxiao.setOnClickListener(this);
        image.setOnClickListener(this);
        //edittext添加上一个发生变化监听器
        editText.addTextChangedListener(textWatcher);
        webSettings.setAllowFileAccess(true); // 设置可以访问文件
        webSettings.setSupportZoom(false); // 设置不支持缩放
        webSettings.setJavaScriptEnabled(true); // 设置支持javaSrcipt
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); // 设置支持js的弹窗
        webView.loadUrl("http://www.baidu.com"); // 加载首页
        webView.setDownloadListener(new MyDownloadStart());
        //让
        webView.setWebViewClient(new WebViewClient());
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("提示");
        progressDialog.setCancelable(false);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
//                bstring = webView.getUrl();
//                view.loadUrl(bstring);
                return true;
            }
        });
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 18:58.
     * action:点击事件
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                //判断此时edittext是否获得焦点（即输入法是否不为隐藏状态）
                if (editText.requestFocus()) {
                    //隐藏输入法
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                            hideSoftInputFromWindow(editText.getWindowToken(), 0);
                   dialog();
                } else {
                   dialog();
                }
                break;
            case R.id.button2:
                bstring = webView.getUrl();
                webView.loadUrl(bstring);
                break;
            case R.id.bt_shuaxin:
                bstring = webView.getUrl();
                webView.loadUrl(bstring);
                break;
            case R.id.edittext1_noinput:
                Log.d("aaa", "onClick: 点击了noinput布局");
                linearLayoutnoinput.setVisibility(View.GONE);
                linearLayoutinput.setVisibility(View.VISIBLE);
                editText.requestFocus();
                break;
            case R.id.bt_home:
                webView.loadUrl("http://www.baidu.com"); // 加载首页
                break;
            case R.id.bt_back:
                if (webView.canGoBack()) {
                    // 返回上一页面
                    webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                    webView.goBack();
                    Log.d("", "onClick: 可以返回");
                }
                break;
            case R.id.bt_next:
                if (webView.canGoForward()) {
                    // 前进下一页面
                    webView.goForward();
                }
                break;
            case R.id.bt_quxiao:
                linearLayoutnoinput.setVisibility(View.VISIBLE);
                linearLayoutinput.setVisibility(View.GONE);
                break;
            case R.id.image1:
                editText.setText("");
            default:
                break;
        }
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 19:02.
     * action:edittext相关事件，例如：按下输入法右下角键，失去焦点事件
     */
    public void edit_action() {
        //监听输入法右下角按键的按下
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                /*判断是否是“GO”键*/
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    String string = editText.getText().toString();

                    if (string.length() > 3) {
                        cstring = string.substring(0, 3);
                        cstring = cstring.toLowerCase();
                        if (!cstring.equals("www")) {
                            dstring = editText.getText().toString();
                            webView.loadUrl("https://m.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=39042058_13_oem_dg&wd=" + dstring + "&oq=%25E4%25BD%25A0%25E5%25A5%25BD&rsv_pq=b4b1af0a0004ea2a&rsv_t=ea05hPKgTaiyPv81HhmMMKShaq60Xehw%2BoOa3lqT%2Fe832Nclh7RUTILkoKiV8xsEZF8s%2FP6TNJWy&rqlang=cn&rsv_enter=1&inputT=2584&rsv_sug3=11&rsv_sug1=9&rsv_sug7=100&rsv_sug2=0&rsv_sug4=3456");
                        } else {
                            string = string.toLowerCase();
                            string = "http://" + (editText.getText().toString());
                            webView.loadUrl(string);
                        }
                    } else {
                        dstring = editText.getText().toString();
                        webView.loadUrl("https://m.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=39042058_13_oem_dg&wd=" + dstring + "&oq=%25E4%25BD%25A0%25E5%25A5%25BD&rsv_pq=b4b1af0a0004ea2a&rsv_t=ea05hPKgTaiyPv81HhmMMKShaq60Xehw%2BoOa3lqT%2Fe832Nclh7RUTILkoKiV8xsEZF8s%2FP6TNJWy&rqlang=cn&rsv_enter=1&inputT=2584&rsv_sug3=11&rsv_sug1=9&rsv_sug7=100&rsv_sug2=0&rsv_sug4=3456");
                    }
                    webView.requestFocus();
                    //隐藏输入法
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                            hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    linearLayoutinput.setVisibility(View.GONE);
                    linearLayoutnoinput.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
            }
        });
        //edittext的焦点事件
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    editText.setText("");
                    webView.setClickable(false);
                    toggleSoftInput(editText);
                } else {
                    bstring = webView.getUrl();
                    editText.setText(bstring);
                    webView.setClickable(true);
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                            hideSoftInputFromWindow(editText.getWindowToken(), 0);
                }
            }
        });
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 18:57.
     * action:禁止调用系统浏览器
     */
    class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
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

            estring = editText.getText().toString();
            if (estring.equals("")){
                image.setVisibility(View.GONE);
            }else {
                image.setVisibility(View.VISIBLE);
            }
            }

    };
    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 18:53.
     * action:重写物理按键back的方法，实现按返回回退上一网页
     * 当没有上一网页时
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            linearLayoutnoinput.setVisibility(View.VISIBLE);
            linearLayoutinput.setVisibility(View.GONE);
            if (webView.canGoBack()) {
                // 返回上一页面
                webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                webView.goBack();
                return true;
            } else {
                dialog();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 18:55.
     * action:监听浏览器下载
     */
    class MyDownloadStart implements DownloadListener {
        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            progressDialog.show();
            new MyThread(url).start();
        }
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
            // obj为0，代表未下载完成
            if (message.what == 0) {
                progressDialog.setMessage("正在下载" + df.format(progress) + "%");
            }
            // obj为1，代表下载完成
            else if (message.what == 1) {
                progressDialog.dismiss();
                Snackbar.make(webView,"下载完成，文件位置：" + astring,Snackbar.LENGTH_LONG).show();
            }
        }
    };

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 18:54.
     * action:开启一个线程进行下载
     */
    public class MyThread extends Thread {
        private String src;

        public MyThread(String src) {
            this.src = src;
        }

        @Override
        public void run() {
            try {
                // 为当前的app在内存中新建文件夹
                File dir = new File("storage/emulated/0/AIDE/app");
                if (!dir.exists()) {
                    dir.mkdirs();
                    dir = new File("storage/emulated/0/AIDE/app");
                    astring = "storage/emulated/0/AIDE/app";
                } else {
                    astring = "storage/emulated/0/AIDE/app";
                }
                URL url = new URL(src);
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

                file = new File(dir + "/" + new Random().nextInt(999999999) + ".apk");
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
                    Log.d("此处", "已下载：" + yixiazai);
                    // 计算百分比
                    Log.d("this", "总大小：" + total);
                    progress = (double) ((yixiazai / total) * 100);
                    df = new DecimalFormat("#");
                    Log.d("这里", "下载了：" + df.format(progress));
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 设置输入法,如果当前页面输入法打开则关闭
     *
     * @param activity
     */
    public void hideInputMethod(Activity activity) {
        View a = activity.getCurrentFocus();
        if (a != null) {
            InputMethodManager imm = (InputMethodManager) activity.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            try {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 强制显示输入法
     */
    public void toggleSoftInput(View view) {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        } catch (Exception e) {

        }
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/21 14:58.
     * action:弹出退出浏览器对话框
     */
    public void dialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(SousuoActivity.this);
        dialog.setTitle("退出");

        dialog.setMessage("是否确定退出浏览器？");

        dialog.setCancelable(false);

        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialogInterface, int i) {

                finish();

            }

        });

        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }

        });
        dialog.show();

    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SousuoActivity.class);
        context.startActivity(intent);
    }
}


