package com.tenz.tenzmusic.service;

import com.tenz.tenzmusic.entity.PlaySongBean;

import java.util.List;

interface MusicControllerImpl {

    /**
     * 设置播放列表
     * @param playSongBeanList
     */
    void setPlaySongBeanList(List<PlaySongBean> playSongBeanList);

    /**
     * 获取播放列表
     * @return
     */
    List<PlaySongBean> getPlaySongBeanList();

    /**
     * 播放音乐
     * @param position
     */
    void playMusic(int position);

    /**
     * 播放网络音乐
     */
    void playMusic(String hash, String album_id);

    /**
     * 播放本地音乐
     */
    void playLocalMusic(String hash, String playUrl);

    /**
     * 获取当前音乐位置
     * @return
     */
    int getCurrentMusicPosition();

    /**
     * 删除播放列表的其中一个
     * @param position
     */
    void deleteMusic(int position);

    /**
     * 删除播放列表的全部
     */
    void deleteMusicAll();

    /**
     * 播放
     */
    void start();

    /**
     * 暂停
     */
    void pause();

    /**
     * 停止
     */
    void stop();

    /**
     * 上一首
     */
    void playPrevious();

    /**
     * 下一首
     */
    void playNext();

    /**
     * 获取播放状态 true:正在播放 false:暂停
     * @return
     */
    boolean getPlayState();

    /***
     * 获取音乐总时长
     * @return
     */
    int getDuration();

    /**
     * 获取播放进度
     * @return
     */
    int getProgress();

    /**
     * 设置播放进度
     */
    void setProgress(int progress);

    /**
     * 设置播放模式
     */
    void changePlayModel();

    /**
     * 获取播放模式
     * @return
     */
    int getPlayModel();

    /**
     * 音乐是否准备完毕
     * @return
     */
    boolean isPrepare();

    /**
     * 获取当前播放音乐
     * @return
     */
    PlaySongBean getCurrentSong();

    /**
     * 更新音乐播放通知栏
     */
    void updateMusicNotification();

}
