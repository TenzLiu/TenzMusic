package com.tenz.tenzmusic.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "play_song")
public class PlaySongBean implements Serializable {

    private boolean is_local;//是否本地
    private boolean is_like;//是否喜欢
    private boolean is_download;//是否下载
    private long add_time;//添加时间
    private long update_time;//添加时间

    //网络音乐字段,播放需要请求详情接口
    @PrimaryKey()
    @NonNull
    private String hash = "";//唯一主键
    private String album_id = "";

    //本地音乐字段，直接播放
    private String song_name = "";
    private String author_name = "";
    private String lyrics = "";
    private String play_url = "";
    private String img = "";
    private long timelength = 0;
    private String mvhash = "";

    public PlaySongBean() {
    }

    public boolean isIs_local() {
        return is_local;
    }

    public void setIs_local(boolean is_local) {
        this.is_local = is_local;
    }

    public boolean isIs_like() {
        return is_like;
    }

    public void setIs_like(boolean is_like) {
        this.is_like = is_like;
    }

    public boolean isIs_download() {
        return is_download;
    }

    public void setIs_download(boolean is_download) {
        this.is_download = is_download;
    }

    public long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getPlay_url() {
        return play_url;
    }

    public void setPlay_url(String play_url) {
        this.play_url = play_url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public long getTimelength() {
        return timelength;
    }

    public void setTimelength(long timelength) {
        this.timelength = timelength;
    }

    public String getMvhash() {
        return mvhash;
    }

    public void setMvhash(String mvhash) {
        this.mvhash = mvhash;
    }
}
