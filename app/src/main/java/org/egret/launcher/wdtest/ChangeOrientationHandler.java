package org.egret.launcher.wdtest;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;



public class ChangeOrientationHandler extends Handler {
    private Activity activity;

    public ChangeOrientationHandler(Activity ac) {
        super();
        activity = ac;
    }

    @Override
    public void handleMessage(Message msg) {
        if (msg.what == 888) {
            int orientation = msg.arg1;
            if (orientation > 45 && orientation < 135) {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
//                Log.d("jinx", "横屏翻转: ");
            }
            else if (orientation > 225 && orientation < 315) {
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                Log.d("jinx", "横屏: ");
            }

            else if (orientation > 135 && orientation < 225) {
//                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                Log.d("jinx", "竖屏翻转: ");
            }
            else if ((orientation > 315 && orientation < 360) || (orientation > 0 && orientation < 45)) {
//                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                Log.d("jinx", "竖屏: ");
            }
        }
        super.handleMessage(msg);
    }
}
