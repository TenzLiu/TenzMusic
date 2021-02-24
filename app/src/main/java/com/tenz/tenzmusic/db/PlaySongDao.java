package com.tenz.tenzmusic.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.tenz.tenzmusic.entity.PlaySongBean;

import java.util.List;


@Dao
public interface PlaySongDao {

    /**
     * 添加一个
     * @param playSongBean
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PlaySongBean playSongBean);

    /**
     * 添加集合
     * @param playSongBeanList
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<PlaySongBean> playSongBeanList);

    /**
     * 全部删除
     */
    @Query("DELETE FROM play_song")
    void deleteAll();

    /**
     * 删除一个
     * @param playSongBean
     */
    @Delete
    void delete(PlaySongBean playSongBean);

    /**
     * 根据hash删除一个
     * @param hash
     */
    @Query("DELETE FROM play_song WHERE hash = :hash")
    void deleteByHash(String hash);

    /**
     * 根据hash查找
     * @param hash
     * @return
     */
    @Query("SELECT * FROM play_song WHERE hash = :hash")
    PlaySongBean getPlaySongByHash(String hash);

    /**
     * 查找全部
     * @return
     */
    @Query("SELECT * FROM play_song")
    List<PlaySongBean> getPlaySongAll();

    /**
     * 查询喜欢歌曲
     * @param is_like
     * @return
     */
    @Query("SELECT * FROM play_song WHERE is_like = :is_like")
    List<PlaySongBean> getPlaySongByLike(boolean is_like);

    /**
     * 查询本地歌曲
     * @param is_local
     * @return
     */
    @Query("SELECT * FROM play_song WHERE is_local = :is_local")
    List<PlaySongBean> getPlaySongByLocal(boolean is_local);

    /**
     * 查询下载歌曲，isDownload=true
     * @param is_download
     * @return
     */
    @Query("SELECT * FROM play_song WHERE is_download = :is_download")
    List<PlaySongBean> getPlaySongByDownload(boolean is_download);

    /**
     * 查询最近歌曲（默认7天内）
     * @param time
     * @return
     */
    @Query("SELECT * FROM play_song WHERE update_time > :time")
    List<PlaySongBean> getPlaySongByTime(long time);

    /**
     * 查询最近歌曲（默认7天内）
     * @param time
     * @return
     */
    @Query("SELECT * FROM play_song WHERE update_time > :time LIMIT :limitCount")
    List<PlaySongBean> getPlaySongByTime(long time, int limitCount);

    /**
     * 修改一个
     * @param playSongBean
     */
    @Update
    void update(PlaySongBean playSongBean);

    /**
     * 修改喜欢状态
     * @param hash
     * @param is_like
     */
    @Query("UPDATE play_song SET is_like = :is_like WHERE hash = :hash")
    void updateLike(String hash, boolean is_like);

    /**
     * 修改本地状态
     * @param hash
     * @param is_local
     */
    @Query("UPDATE play_song SET is_local = :is_local WHERE hash = :hash")
    void updateIsLocal(String hash, boolean is_local);

    /**
     * 修改下载状态
     * @param hash
     * @param is_download
     */
    @Query("UPDATE play_song SET is_download = :is_download WHERE hash = :hash")
    void updateIsDownLoad(String hash, boolean is_download);

    /**
     * 查询是否是喜欢歌曲
     * @param hash
     * @return
     */
    @Query("SELECT * FROM play_song WHERE is_like = :is_like AND hash = :hash")
    PlaySongBean getLikePlaySongByHash(boolean is_like, String hash);

    /**
     * 查询是否是已经下载歌曲
     * @param hash
     * @return
     */
    @Query("SELECT * FROM play_song WHERE is_download = :is_download AND hash = :hash")
    PlaySongBean getDownloadPlaySongByHash(boolean is_download, String hash);


}
