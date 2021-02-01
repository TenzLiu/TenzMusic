package com.tenz.tenzmusic.api;


import com.tenz.tenzmusic.entity.RecommendResponse;
import com.tenz.tenzmusic.entity.SearchSongResponse;
import com.tenz.tenzmusic.entity.SongCommentResponse;
import com.tenz.tenzmusic.entity.SongDetailBean;
import com.tenz.tenzmusic.entity.SongListResponse;
import com.tenz.tenzmusic.entity.SongSheetResponse;
import com.tenz.tenzmusic.entity.VideoDetailBean;
import com.tenz.tenzmusic.entity.VideoListResponse;
import com.tenz.tenzmusic.entity.VideoSortResponse;
import com.tenz.tenzmusic.http.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Author: Tenz
 * Date: 2019-04-09
 * Description: retrofit请求每个接口的入口
 */

public interface RetrofitApi {

    /**
     * 获取热门推荐分类
     * @return
     */
    @GET("v3/tag/recommend")
    Observable<BaseResponse<RecommendResponse>> getBanner(@Query("showtype") int showtype,
                                                          @Query("apiver") int apiver,
                                                          @Query("plat") int plat);

    /**
     * 获取歌单
     * @param showtype
     * @param apiver
     * @param plat
     * @return
     */
    @GET("v3/rank/list")
    Observable<BaseResponse<SongSheetResponse>> getSongSheet(@Query("version") int version,
                                                @Query("plat") int plat,
                                                @Query("showtype") int showtype,
                                                @Query("parentid") int parentid,
                                                @Query("apiver") int apiver,
                                                @Query("area_code") int area_code,
                                                @Query("withsong") int withsong,
                                                @Query("with_res_tag") int with_res_tag);

    /**
     * 获取歌单对应列表歌曲
     * @param plat
     * @return
     */
    @GET("v3/rank/song")
    Observable<BaseResponse<SongListResponse>> getSongListBySheet(@Query("version") int version,
                                                @Query("plat") int plat,
                                                @Query("area_code") int area_code,
                                                @Query("volid") int volid,
                                                @Query("ranktype") int ranktype,
                                                @Query("rankid") int rankid,
                                                @Query("pagesize") int pagesize,
                                                @Query("page") int page,
                                                @Query("with_res_tag") int with_res_tag);

    /**
     * 获取新歌
     * @param plat
     * @return
     */
    @GET("v3/rank/newsong")
    Observable<BaseResponse<SongListResponse>> newSong(@Query("version") int version,
                                                       @Query("plat") int plat,
                                                       @Query("with_cover") int with_cover,
                                                       @Query("area_code") int area_code,
                                                       @Query("with_res_tag") int with_res_tag,
                                                       @Query("type") int type,
                                                       @Query("page") int page,
                                                       @Query("pagesize") int pagesize);

    /**
     * 获取歌曲详情
     * @return
     */
    @GET("yy/index.php")
    Observable<BaseResponse<SongDetailBean>> getSongDetail(@Query("r") String r,
                                                           @Query("callback") String callback,
                                                           @Query("dfid") String dfid,
                                                           @Query("mid") String mid,
                                                           @Query("platid") String platid,
                                                           @Query("_") String _down,
                                                           @Query("hash") String hash,
                                                           @Query("album_id") String album_id);

    /**
     * 搜索歌曲列表
     * @return
     */
    @GET("api/v3/search/song")
    Observable<BaseResponse<SearchSongResponse>> searchSong(@Query("showtype") int showtype,
                                                               @Query("highlight") String highlight,
                                                               @Query("tag_aggr") int tag_aggr,
                                                               @Query("agtype") String agtype,
                                                               @Query("plat") int plat,
                                                               @Query("sver") int sver,
                                                               @Query("correct") int correct,
                                                               @Query("api_ver") int api_ver,
                                                               @Query("version") int version,
                                                               @Query("area_code") int area_code,
                                                               @Query("tag") int tag,
                                                               @Query("with_res_tag") int with_res_tag,
                                                               @Query("keyword") String keyword,
                                                               @Query("page") int page,
                                                               @Query("pagesize") int pagesize);

    /**
     * 获取MV分类
     * @param plat
     * @return
     */
    @GET("v5/video/recommend_channel")
    Observable<BaseResponse<VideoSortResponse>> getVideoSort(@Query("version") int version,
                                                             @Query("plat") int plat,
                                                             @Query("type") int type);

    /**
     * 获取MV列表
     * @param plat
     * @return
     */
    @GET("v5/video/list")
    Observable<BaseResponse<VideoListResponse>> getVideoList(@Query("version") int version,
                                                             @Query("plat") int plat,
                                                             @Query("sort") int sort,
                                                             @Query("id") int id,
                                                             @Query("page") int page,
                                                             @Query("pagesize") int pagesize);

    /**
     * 获取MV详情
     * @return
     */
    @GET("app/i/mv.php")
    Observable<VideoDetailBean> getVideoDetail(@Query("cmd") int cmd,
                                               @Query("ismp3") int ismp3,
                                               @Query("ext") String ext,
                                               @Query("hash") String hash);

    /**
     * 获取歌曲评论列表
     * @return
     */
    @GET("index.php")
    Observable<SongCommentResponse> getSongComment(@Query("r") String r,
                                                   @Query("code") String code,
                                                   @Query("clientver") int clientver,
                                                   @Query("extdata") String extdata,
                                                   @Query("p") int p,
                                                   @Query("pagesize") int pagesize);


}
