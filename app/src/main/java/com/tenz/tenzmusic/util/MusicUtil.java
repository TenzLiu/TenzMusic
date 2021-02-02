package com.tenz.tenzmusic.util;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.tenz.tenzmusic.entity.PlaySongBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 音乐工具类
 */
public class MusicUtil {

    public static final int FILTER_SIZE = 1 * 1024 * 1024;// 1MB
    public static final int FILTER_DURATION = 1 * 60 * 1000;// 1分钟

    public static final int START_FROM_LOCAL = 1;
    public static final int START_FROM_ARTIST = 2;
    public static final int START_FROM_ALBUM = 3;
    public static final int START_FROM_FOLDER = 4;

    //按歌曲
    private static String[] proj_music = new String[]{
            MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.ALBUM, MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ARTIST_ID, MediaStore.Audio.Media.DURATION, MediaStore.Audio.Media.SIZE};

    //按专辑
    private static String[] proj_album = new String[]{MediaStore.Audio.Albums._ID, MediaStore.Audio.Albums.ALBUM_ART,
            MediaStore.Audio.Albums.ALBUM, MediaStore.Audio.Albums.NUMBER_OF_SONGS, MediaStore.Audio.Albums.ARTIST};

    //按歌手
    private static String[] proj_artist = new String[]{
            MediaStore.Audio.Artists.ARTIST,
            MediaStore.Audio.Artists.NUMBER_OF_TRACKS,
            MediaStore.Audio.Artists._ID};

    //按文件夹
    private static String[] proj_folder = new String[]{MediaStore.Files.FileColumns.DATA};

    /**
     * @param context
     * @param from  不同的类型要做不同的查询
     * @return
     */
    public static List<PlaySongBean> queryMusic(Context context, int from) {
        return queryMusic(context, null, from);
    }

    /**
     * 查询音乐
     * @param context
     * @param id
     * @param from
     * @return
     */
    public static ArrayList<PlaySongBean> queryMusic(Context context, String id, int from) {
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        ContentResolver cr = context.getContentResolver();
        StringBuilder select = new StringBuilder(" 1=1 and title != ''");
        // 查询语句：检索出.mp3为后缀名，时长大于1分钟，文件大小大于1MB的媒体文件
        select.append(" and " + MediaStore.Audio.Media.SIZE + " > " + FILTER_SIZE);
        select.append(" and " + MediaStore.Audio.Media.DURATION + " > " + FILTER_DURATION);
        String selectionStatement = "is_music=1 AND title != ''";
        final String songSortOrder = MediaStore.Audio.Media.DEFAULT_SORT_ORDER;
        switch (from) {
            case START_FROM_LOCAL:
                Log.e("queue", "uri " + uri.toString());
                Log.e("queue", "proj_music " + proj_music.toString());
                Log.e("queue", "select " + select.toString());
                Log.e("queue", "songSortOrder " + songSortOrder.toString());
                ArrayList<PlaySongBean> listLocal = getMusicListCursor(cr.query(uri, proj_music,
                        select.toString(), null,
                        songSortOrder));
                return listLocal;
            case START_FROM_ARTIST:
                select.append(" and " + MediaStore.Audio.Media.ARTIST_ID + " = " + id);
                return getMusicListCursor(cr.query(uri, proj_music, select.toString(), null,
                        MediaStore.Audio.Media.DEFAULT_SORT_ORDER));
            case START_FROM_ALBUM:
                select.append(" and " + MediaStore.Audio.Media.ALBUM_ID + " = " + id);
                return getMusicListCursor(cr.query(uri, proj_music,
                        select.toString(), null,
                        MediaStore.Audio.Media.TRACK + ", "
                                + MediaStore.Audio.Media.DEFAULT_SORT_ORDER));
            case START_FROM_FOLDER:
                ArrayList<PlaySongBean> listFolder = new ArrayList<>();
                ArrayList<PlaySongBean> listSong = getMusicListCursor(cr.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, proj_music,
                        select.toString(), null,
                        null));
                for (PlaySongBean playSongBean : listSong) {
                    if (playSongBean.getPlay_url().substring(0, playSongBean.getPlay_url().lastIndexOf(File.separator)).equals(id)) {
                        listFolder.add(playSongBean);
                    }
                }
                return listFolder;
            default:
                return null;
        }

    }

    /**
     * 查询音乐
     * @param cursor
     * @return
     */
    public static ArrayList<PlaySongBean> getMusicListCursor(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        ArrayList<PlaySongBean> playSongBeanList = new ArrayList<>();
        while (cursor.moveToNext()) {
            PlaySongBean playSongBean = new PlaySongBean();
            int songId = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Audio.Media._ID));
            int duration = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DURATION));
            String musicName = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.TITLE));
            String artist = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ARTIST));
            String filePath = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DATA));
            playSongBean.setSong_name(musicName);
            playSongBean.setAuthor_name(artist);
            playSongBean.setPlay_url(filePath);
            playSongBean.setTimelength(duration);
            playSongBean.setImg(getAlbumArtUri(cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))) + "");
            playSongBean.setIs_local(true);
            LogUtil.e("songId:---"+songId);
            playSongBean.setHash(String.valueOf(songId));
            playSongBeanList.add(playSongBean);
        }
        cursor.close();
        return playSongBeanList;
    }

    public static Uri getAlbumArtUri(long albumId) {
        return ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"), albumId);
    }

}
