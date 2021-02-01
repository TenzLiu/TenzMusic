package com.tenz.tenzmusic.receiver;

import android.content.Context;
import android.content.IntentFilter;

public class ReceiverManager {

    /**
     * 注册音乐广播
     * @param context
     * @param musicBroadcastReceiver
     */
    public static void registerMusicReceiver(Context context, MusicBroadcastReceiver musicBroadcastReceiver){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MusicBroadcastReceiver.ACTION_MUSIC_REGISTER_SUCCESS);
        intentFilter.addAction(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_NULL);
        intentFilter.addAction(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_SONG);
        intentFilter.addAction(MusicBroadcastReceiver.ACTION_MUSIC_PLAYING);
        intentFilter.addAction(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_START);
        intentFilter.addAction(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_PAUSE);
        intentFilter.addAction(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_STOP);
        intentFilter.addAction(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_PREVIOUS);
        intentFilter.addAction(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_NEXT);
        intentFilter.addAction(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_ERROR);
        context.registerReceiver(musicBroadcastReceiver,intentFilter);
    }

    /**
     * 取消注册音乐广播
     * @param context
     * @param musicBroadcastReceiver
     */
    public static void unRegisterMusicReceiver(Context context, MusicBroadcastReceiver musicBroadcastReceiver){
        context.unregisterReceiver(musicBroadcastReceiver);
    }

}
