package com.example.nianxin.gongjuxiang.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.baidu.platform.comapi.map.F;
import com.example.nianxin.gongjuxiang.R;
import com.example.nianxin.gongjuxiang.base.BaseActivity;

/**
 * @author nianxin
 */
public class FirstActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    int a = 0;
    private Camera camera = Camera.open();
    private ImageView iv_off_on;
    private ImageView iv_light;
    //    private Parameters parameter = null;
    private static final String TAG = "ClearMemoryActivity";
    boolean isExit;
    private Camera m_Camera;
    private GridView gview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    private TextView textview1;
    private int[] icon = {R.drawable.ditu, R.drawable.jsq, R.drawable.bianqianioc,
            R.drawable.shoudian, R.drawable.sosou, R.drawable.danci, R.drawable.dianh, R.drawable.guanyu2};
    private String[] iconName = {"地图", "计算器", "便签", "手电筒", "网络", "速记", "通讯", "关于"};

    protected void onCreate(Bundle savedInstanceState) {
        // TODO 自动生成的方法存根
        super.onCreate(savedInstanceState);
        initWidget();
        setwidth();
        action();
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 17:04.
     * action:初始化
     */
    public void initWidget() {
        // 隐藏标题栏；必须放在setcontentview语句前，否则会报错
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 加载布局
        setContentView(R.layout.first_layout);

        gview = (GridView) findViewById(R.id.gview);
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 17:08.
     * action:初始化后获取数据、适配器的相关
     */
    public void action() {
        // 新建List
        data_list = new ArrayList<Map<String, Object>>();
        // 获取数据
        getData();
        // 新建适配器
        String[] from = {"image", "text"};
        int[] to = {R.id.image, R.id.text};
        sim_adapter = new SimpleAdapter(this, data_list, R.layout.item_zhuye, from, to);
        // 配置适配器
        gview.setOnItemClickListener(this);
        gview.setAdapter(sim_adapter);
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 17:06.
     * action:为Gridview得到数据
     */
    public List<Map<String, Object>> getData() {
        // cion和iconName的长度是相同的，这里任选其一都可以
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
        }
        return data_list;
    }


    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 17:06.
     * action:动态设置Gridview的子项宽度
     */
    public void setwidth() {
        //获取WindowManager
        WindowManager wm = this.getWindowManager();
        //得到屏幕的高度
        int width = wm.getDefaultDisplay().getWidth();
        double width2 = width / Math.sqrt(2);

//用屏幕的宽度比上单元格的个数，就是每一个单元格的宽度
        gview.setColumnWidth((int) width2);//gridview动态设置每一个单元格的宽度
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 17:02.
     * action:重写物理键back的方法
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自动生成的方法存根
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:58.
     * action:具体实现双击返回键退出程序
     */
    private void exit() {
        // TODO 自动生成的方法存根
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            System.exit(0);
        }
    }
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            isExit = false;
        }
    };

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:59.
     * action:Gridview子项的点击响应事件
     */
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // TODO 自动生成的方法存根
        switch (position) {
            case 0:
                dituActivity.actionStart(FirstActivity.this);
                break;
            case 1:
                JsqActivity.actionStart(FirstActivity.this);
                break;
            case 2:
                BianqianActivity.actionStart(FirstActivity.this);
                break;
            case 3:
                //调用方法打开本机闪光灯
                try {
                    if (a == 0) {
                        turnLightOn(camera);
                        a = 1;
                    } else {
                        turnLightOff(camera);
                        a = 0;

                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
                break;
            case 4:
                SousuoActivity.actionStart(FirstActivity.this);
                break;
            case 5:
                DanciActivity.actionStart(FirstActivity.this);
                break;
            case 6:
                PhoneListenerActivity.actionStart(FirstActivity.this);
                break;
            case 7:
                AboutActivity.actionStart(FirstActivity.this);
                break;
//            default:
//                AlertDialog.Builder dialog = new AlertDialog.Builder(FirstActivity.this);
//                dialog.setTitle("退出");
//                dialog.setMessage("是否确定退出应用？");
//                dialog.setCancelable(false);
//                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                });
//                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                    //instaling apk
//                });
//                dialog.show();
//                break;
        }
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 17:00.
     * action:闪光灯的开启
     */
    public static void turnLightOn(Camera mCamera) {

        if (mCamera == null) {
            return;
        }
        Parameters parameters = mCamera.getParameters();
        if (parameters == null) {
            return;
        }

        List<String> flashModes = parameters.getSupportedFlashModes();
        if (flashModes == null) {
            // Use the screen as a flashlight (next best thing)
            return;
        }
        String flashMode = parameters.getFlashMode();
        if (!Parameters.FLASH_MODE_TORCH.equals(flashMode)) {
            // Turn on the flash
            if (flashModes.contains(Parameters.FLASH_MODE_TORCH)) {
                parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
                mCamera.setParameters(parameters);
            } else {
            }
        }
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 17:01.
     * action:闪光灯的关闭
     */
    public static void turnLightOff(Camera mCamera) {
        if (mCamera == null) {
            return;
        }
        Parameters parameters = mCamera.getParameters();
        if (parameters == null) {
            return;
        }
        List<String> flashModes = parameters.getSupportedFlashModes();
        String flashMode = parameters.getFlashMode();
        if (flashMode == null) {
            return;
        }
        if (!parameters.FLASH_MODE_OFF.equals(flashMode)) {
            if (flashModes.contains(parameters.FLASH_MODE_OFF)) {
                parameters.setFlashMode(parameters.FLASH_MODE_OFF);
                mCamera.setParameters(parameters);
            } else {
                Log.e(TAG, "FLASH_MODE_OFF not supported");
            }
        }

    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 17:01.
     * action:销毁活动后回收对象，释放内存
     */
    protected void onDestroy() {
        super.onDestroy();
        if (camera != null) {
            camera.release();
        }
    }
}
