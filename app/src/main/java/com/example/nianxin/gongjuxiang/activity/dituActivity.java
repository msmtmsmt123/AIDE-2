package com.example.nianxin.gongjuxiang.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.baidu.mapapi.map.TextureMapView;
import com.example.nianxin.gongjuxiang.R;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.nianxin.gongjuxiang.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nianxin on 2017/4/13.
 */


public class dituActivity extends AppCompatActivity {
    public LocationClient mlocationclient;
    private TextView positionText;
    private Button button1;
    private TextureMapView mapView = null;
    private BaiduMap baiduMap;
    private boolean isFirstLocate = true;
    StringBuffer currentposition;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWidget();
        action();
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:49.
     * action:初始化
     */
    public void initWidget() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //得到全局上下文
        mlocationclient = new LocationClient(getApplicationContext());
        //注册监听
        mlocationclient.registerLocationListener(new MyLocationListener());
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.ditu);
        mapView = (TextureMapView) findViewById(R.id.bmapView);
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        positionText = (TextView) findViewById(R.id.position_text_view);




        // 隐藏logo
        View child = mapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }

    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:52.
     * action:判断是否取得权限
     */
    public void action() {
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(dituActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(dituActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(dituActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(dituActivity.this, permissions, 1);
        } else {
            requestLocation();
        }
    }

    /**
    * wenming
    * created by:nianxin
    * created 2017/5/20 16:57.
    * action:具体实现地图展示细节
    */
    private void navigaTo(BDLocation location) {
        if (isFirstLocate) {
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(15f);
            baiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);
    }

    private void requestLocation() {
        initLocation();
        mlocationclient.start();
    }

    public void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setScanSpan(1000);
        option.setIsNeedAddress(true);
        mlocationclient.setLocOption(option);
    }

/**
* wenming
* created by:nianxin
* created 2017/5/20 16:56.
* action:拒绝授权处理
*/
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意权限才能使用本应用！！！", Toast.LENGTH_LONG).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
            default:
        }
    }


    /**
    * wenming
    * created by:nianxin
    * created 2017/5/20 16:56.
    * action:显示位置到屏幕上
    */
    public class MyLocationListener implements BDLocationListener {
        public void onReceiveLocation(BDLocation location) {
            try {
                if (location.getLocType() == BDLocation.TypeGpsLocation || location.getLocType() == BDLocation.TypeNetWorkLocation) {
                    navigaTo(location);
                }
                currentposition = new StringBuffer();
                currentposition.append("纬度：").append(location.getLatitude()).append("\t");
                currentposition.append("经度：").append(location.getLongitude()).append("\n");
                currentposition.append("国家：").append(location.getCountry()).append("\t");
                currentposition.append("省份：").append(location.getProvince()).append("\t");
                currentposition.append("市：").append(location.getCity()).append("\n");
                currentposition.append("区：").append(location.getDistrict()).append("\t");
                currentposition.append("街道：").append(location.getStreet()).append("\t");
                positionText.setText(currentposition);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }


        public void onConnectHotSpotMessage(String s, int i) {

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * wenming
     * created by:nianxin
     * created 2017/5/20 16:55.
     * action:销毁活动时回收数据，释放内存
     */
    protected void onDestroy() {
        super.onDestroy();
        mlocationclient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, dituActivity.class);
        context.startActivity(intent);
    }


}



