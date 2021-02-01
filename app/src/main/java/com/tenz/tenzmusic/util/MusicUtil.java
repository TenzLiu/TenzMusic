package com.tenz.tenzmusic.util;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.tenz.tenzmusic.entity.PlaySongBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 音乐工具类
 */
public class MusicUtil {

    /**
     * 扫描系统里面的音频文件，返回一个list集合
     */
    public static List<PlaySongBean> getLocalMusicList(Context context) {
        List<PlaySongBean> playSongBeanList = new ArrayList<>();
        // 媒体库查询语句
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null,
                null, MediaStore.Audio.AudioColumns.IS_MUSIC);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                PlaySongBean playSongBean = new PlaySongBean();
                playSongBean.setSong_name(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));
                playSongBean.setAuthor_name(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
                playSongBean.setPlay_url(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
                playSongBean.setTimelength(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
                playSongBeanList.add(playSongBean);
            }
            // 释放资源
            cursor.close();
        }

        return playSongBeanList;
    }

}
