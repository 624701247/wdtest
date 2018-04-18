package org.egret.launcher.wdtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/3/19.
 */

public class TestActivity extends Activity {
    private VideoView vv_video;
    private final String TAG = "TestActivityLog";


    //
    private Handler handler;
    private OrientationSensorListener listener;
    private SensorManager sm;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);  //默认设置横屏


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Log.d(TAG, "initTestActivity");

        // kone point ：延时切换 activity
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Intent in = new Intent();
                in.setClassName( getApplicationContext(), "org.egret.launcher.wdtest.MainActivity" );
//                startActivity( in );
            }
        }, 2000);

        // kone point ： 播放视频
        vv_video = (VideoView) findViewById(R.id.vv_videoview);
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.yes;
        vv_video.setVideoURI(Uri.parse(uri));
        vv_video.start();


        //
        handler = new ChangeOrientationHandler(this);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        listener = new OrientationSensorListener(handler);
        sm.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI);
    }


    @Override
    protected void onPause() {
        sm.unregisterListener(listener);
        super.onPause();
    }

    @Override
    protected void onResume() {
        sm.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI);
        super.onResume();
    }


    /*
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        int dir = getRequestedOrientation();  //该activity 设置的方向 （xml文件中的 android:screenOrientation）
        System.out.println("curDir" + dir);

        System.out.println("newConfigOrientation" + newConfig.orientation); //只输出 1 或 2，表示横屏、竖屏

        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d("newc", "ppppppppp");
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        else if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("newc", "lllllllll");
        }
    }
    */
}
