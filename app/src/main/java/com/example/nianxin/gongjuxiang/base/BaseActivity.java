package com.example.nianxin.gongjuxiang.base;




        import android.app.Activity;
        import android.os.Bundle;

public class BaseActivity extends Activity {



    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

}
