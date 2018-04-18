package org.egret.launcher.wdtest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Log.d(TAG, "initTestActivity");

        //
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Intent in = new Intent();
                in.setClassName( getApplicationContext(), "org.egret.launcher.wdtest.MainActivity" );
                startActivity( in );
            }
        }, 2000);

        vv_video = (VideoView) findViewById(R.id.vv_videoview);
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.yes;
        vv_video.setVideoURI(Uri.parse(uri));
        vv_video.start();



    }
}
