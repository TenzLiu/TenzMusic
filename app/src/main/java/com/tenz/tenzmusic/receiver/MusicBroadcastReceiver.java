package com.tenz.tenzmusic.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tenz.tenzmusic.util.LogUtil;

public class MusicBroadcastReceiver extends BroadcastReceiver {

    //成功注册广播
    public static final String ACTION_MUSIC_REGISTER_SUCCESS = "com.tenz.tenzmusic_music_register_success";
    //播放空音乐(播放完成，播放错误)，音乐onCompletion，onError
    public static final String ACTION_MUSIC_PLAY_NULL = "com.tenz.tenzmusic_music_play_null";
    //播放音乐(播放新音乐、上一首、下一首)，音乐onPrepared
    public static final String ACTION_MUSIC_PLAY_SONG = "com.tenz.tenzmusic_music_play_song";
    //音乐播放中,每隔一秒发送一次广播
    public static final String ACTION_MUSIC_PLAYING = "com.tenz.tenzmusic_music_playing";
    //音乐开始
    public static final String ACTION_MUSIC_PLAY_START = "com.tenz.tenzmusic_music_play_start";
    //音乐暂停
    public static final String ACTION_MUSIC_PLAY_PAUSE = "com.tenz.tenzmusic_music_play_pause";
    //音乐停止
    public static final String ACTION_MUSIC_PLAY_STOP = "com.tenz.tenzmusic_music_play_stop";
    //音乐上一首
    public static final String ACTION_MUSIC_PLAY_PREVIOUS = "com.tenz.tenzmusic_music_play_previous";
    //音乐下一首
    public static final String ACTION_MUSIC_PLAY_NEXT = "com.tenz.tenzmusic_music_play_next";
    //音乐播放错误
    public static final String ACTION_MUSIC_PLAY_ERROR = "com.tenz.tenzmusic_music_play_error";

    private MusicReceiverListener mMusicReceiverListener;

    public void setmMusicReceiverListener(MusicReceiverListener mMusicReceiverListener) {
        this.mMusicReceiverListener = mMusicReceiverListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
//        LogUtil.e("onReceive-----------------" + intent.getAction());
        if(mMusicReceiverListener != null){
            mMusicReceiverListener.onReceive(context,intent);
        }
    }

    public interface MusicReceiverListener{

        void onReceive(Context context, Intent intent);

    }

}
