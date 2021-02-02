package com.tenz.tenzmusic.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tenz.tenzmusic.app.App;
import com.tenz.tenzmusic.ui.home.LockActivity;

public class LockReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_SCREEN_OFF.equals(action)) {
            //锁屏

        } else if (Intent.ACTION_SCREEN_ON.equals(action)) {
            //开屏
            if(App.getApplication().getmMusicBinder().getPlayState()){
                //正在播放中才显示锁屏
                Intent lockIntent = new Intent(context, LockActivity.class);
                lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(lockIntent);
            }
        }
    }

}
