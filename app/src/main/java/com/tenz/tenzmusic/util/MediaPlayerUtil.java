package com.tenz.tenzmusic.util;

import android.content.Context;
import android.media.MediaPlayer;

public class MediaPlayerUtil {

    private static volatile MediaPlayerUtil singleton = null;
    private Context mContext;
    private MediaPlayer mediaPlayer = null;

    private MediaPlayerUtil(Context context) {
        this.mContext = context.getApplicationContext();
    }

    /**
     * 单例
     *
     * @param context
     * @return
     */
    public static MediaPlayerUtil with(Context context) {
        if (singleton == null) {
            synchronized (MediaPlayerUtil.class) {
                if (singleton == null) {
                    singleton = new MediaPlayerUtil(context);
                }
            }
        }
        return singleton;
    }

    /**
     * 打开raw目录下的音乐mp3文件
     */
    public void openRawSound(int resourceId) {
        try {
            mediaPlayer = MediaPlayer.create(mContext, resourceId);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
            });
            //用prepare方法，会报错误java.lang.IllegalStateExceptio
            //mediaPlayer.prepare();
            mediaPlayer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
