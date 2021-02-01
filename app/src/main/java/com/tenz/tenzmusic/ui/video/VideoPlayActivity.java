package com.tenz.tenzmusic.ui.video;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.tencent.liteav.superplayer.SuperPlayerDef;
import com.tencent.liteav.superplayer.SuperPlayerModel;
import com.tencent.liteav.superplayer.SuperPlayerView;
import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.adapter.SongCommentListAdapter;
import com.tenz.tenzmusic.api.RetrofitApi;
import com.tenz.tenzmusic.app.App;
import com.tenz.tenzmusic.base.BaseActivity;
import com.tenz.tenzmusic.base.BaseQuickAdapter;
import com.tenz.tenzmusic.entity.SongCommentResponse;
import com.tenz.tenzmusic.entity.VideoDetailBean;
import com.tenz.tenzmusic.http.RetrofitFactory;
import com.tenz.tenzmusic.http.RxScheduler;
import com.tenz.tenzmusic.util.GlideUtil;
import com.tenz.tenzmusic.util.LogUtil;
import com.tenz.tenzmusic.util.ResourceUtil;
import com.tenz.tenzmusic.util.StatusBarUtil;
import com.tenz.tenzmusic.util.StringUtil;
import com.tenz.tenzmusic.util.ToastUtil;
import com.tenz.tenzmusic.widget.image.ShapeImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class VideoPlayActivity extends BaseActivity {

    @BindView(R.id.spv_video)
    SuperPlayerView spv_video;
    @BindView(R.id.tv_detail)
    TextView tv_detail;
    @BindView(R.id.tv_comment)
    TextView tv_comment;
    @BindView(R.id.ll_detail)
    LinearLayout ll_detail;
    @BindView(R.id.ll_comment)
    LinearLayout ll_comment;
    @BindView(R.id.srl_song_comment)
    SmartRefreshLayout srl_song_comment;
    @BindView(R.id.rv_song_comment)
    RecyclerView rv_song_comment;

    @BindView(R.id.iv_image)
    ShapeImageView iv_image;
    @BindView(R.id.tv_song_name)
    TextView tv_song_name;
    @BindView(R.id.tv_singer)
    TextView tv_singer;
    @BindView(R.id.tv_play_count)
    TextView tv_play_count;
    @BindView(R.id.tv_time)
    TextView tv_time;

    private String hash;
    private String mv_hash;

    private int mPage = 1;
    public static final int PAGE_SIZE = 20;
    private SongCommentListAdapter songCommentListAdapter;
    private List<SongCommentResponse.ListBean> songCommentBeanList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_play;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(spv_video != null){
            if (spv_video.getPlayerState() == SuperPlayerDef.PlayerState.PAUSE) {
                spv_video.onResume();
                if (spv_video.getPlayerMode() == SuperPlayerDef.PlayerMode.FLOAT) {
                    spv_video.switchPlayMode(SuperPlayerDef.PlayerMode.WINDOW);
                }
            }
            if (spv_video.getPlayerMode() == SuperPlayerDef.PlayerMode.FULLSCREEN) {
                //隐藏虚拟按键，并且全屏
                View decorView = getWindow().getDecorView();
                if (decorView == null) return;
                if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
                    decorView.setSystemUiVisibility(View.GONE);
                } else if (Build.VERSION.SDK_INT >= 19) {
                    int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
                    decorView.setSystemUiVisibility(uiOptions);
                }
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(spv_video != null){
            if (spv_video.getPlayerMode() != SuperPlayerDef.PlayerMode.FLOAT) {
                spv_video.onPause();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(spv_video != null){
            spv_video.release();
            if (spv_video.getPlayerMode() != SuperPlayerDef.PlayerMode.FLOAT) {
                spv_video.resetPlayer();
            }
        }
    }

    @Override
    protected void initView() {
        super.initView();
        StatusBarUtil.setBarColor(mActivity, Color.TRANSPARENT,false);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏

        spv_video.setPlayerViewCallback(new SuperPlayerView.OnSuperPlayerViewCallback() {
            @Override
            public void onStartFullScreenPlay() {}

            @Override
            public void onStopFullScreenPlay() {}

            @Override
            public void onClickFloatCloseBtn() {}

            @Override
            public void onClickSmallReturnBtn() {
                finish();
            }

            @Override
            public void onStartFloatWindowPlay() {}
        });

        initRefreshLayout();
        initRecycleView();
    }

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = getIntent().getExtras();
        if(null != bundle){
            mv_hash = bundle.getString("mv_hash");
        }


        getVideoDetail(mv_hash);
    }

    private void initRefreshLayout() {
        srl_song_comment.setEnableLoadMore(true);
        srl_song_comment.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPage ++;
                getSongComment(hash);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPage = 1;
                getSongComment(hash);
            }
        });
    }

    private void initRecycleView() {
        rv_song_comment.setLayoutManager(new LinearLayoutManager(mContext));
        songCommentBeanList = new ArrayList<>();
        songCommentListAdapter = new SongCommentListAdapter(mContext, R.layout.item_song_comment_list, songCommentBeanList);
        songCommentListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        rv_song_comment.setAdapter(songCommentListAdapter);
    }

    @OnClick({R.id.tv_detail,R.id.tv_comment})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_detail:
                tv_detail.setTextColor(ResourceUtil.getColor(R.color.app_color));
                tv_comment.setTextColor(ResourceUtil.getColor(R.color.color_gray));
                ll_detail.setVisibility(View.VISIBLE);
                ll_comment.setVisibility(View.GONE);
                break;
            case R.id.tv_comment:
                tv_detail.setTextColor(ResourceUtil.getColor(R.color.color_gray));
                tv_comment.setTextColor(ResourceUtil.getColor(R.color.app_color));
                ll_detail.setVisibility(View.GONE);
                ll_comment.setVisibility(View.VISIBLE);
                if(mPage == 1){
                    srl_song_comment.autoRefresh();
                }
                break;
        }
    }

    /**
     * 加载播放器
     * @param videoDetailBean
     */
    private void initPlayerView(VideoDetailBean videoDetailBean) {
        //暂停音乐播放
        App.getApplication().getmMusicBinder().pause();
        // 通过URL方式的视频信息配置
        SuperPlayerModel model = new SuperPlayerModel();
        model.title  = videoDetailBean.getSongname();
        model.url = videoDetailBean.getMvdata().getLe().getDownurl();
        // 开始播放
        spv_video.playWithModel(model);
    }

    /**
     * 获取MV详情
     */
    private void getVideoDetail(String mv_hash){
        RetrofitFactory.getInstance().createApi(RetrofitFactory.BASE_URL_KUGOU_MV, RetrofitApi.class).
                getVideoDetail(100,1,"mp4",mv_hash)
                .compose(RxScheduler.rxSchedulerTransform())
                .subscribe(new Observer<VideoDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        showLoadingDialog();
                    }

                    @Override
                    public void onNext(VideoDetailBean videoDetailBean) {
                        GlideUtil.loadImage(mContext,videoDetailBean.getMvicon().replace("{size}","400"),iv_image);
                        tv_song_name.setText(videoDetailBean.getSongname());
                        tv_singer.setText(videoDetailBean.getSinger());
                        tv_play_count.setText(videoDetailBean.getPlay_count() + "次播放");
                        tv_time.setText(videoDetailBean.getPublish_date());
                        hash = videoDetailBean.getMp3data().getHash();
                        initPlayerView(videoDetailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.showToast("获取MV详情失败");
                    }

                    @Override
                    public void onComplete() {
                        dismissLoadingDialog();
                    }
                });
    }

    /**
     * 获取歌单歌曲评论
     */
    private void getSongComment(String hash){
        if(StringUtil.isEmpty(hash)){
            ToastUtil.showToast("获取歌曲评论失败");
            return;
        }
        RetrofitFactory.getInstance().createApi(RetrofitFactory.BASE_URL_KUGOU_COMMENT, RetrofitApi.class).getSongComment(
                "commentsv2/getCommentWithLike","fc4be23b4e972707f36b8a828a93ba8a",
                8983,hash,mPage,PAGE_SIZE)
                .compose(RxScheduler.rxSchedulerTransform())
                .subscribe(new Observer<SongCommentResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SongCommentResponse songCommentResponse) {
                        if(mPage == 1){
                            songCommentBeanList.clear();
                        }
                        songCommentBeanList.addAll(songCommentResponse.getList());
                        songCommentListAdapter.notifyDataSetChanged();
                        if(songCommentBeanList.size() < songCommentResponse.getCount()){
                            srl_song_comment.setEnableLoadMore(true);
                        }else{
                            srl_song_comment.setEnableLoadMore(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("获取歌曲评论失败" + e.toString());
                        ToastUtil.showToast("获取歌曲评论失败");
                        if(mPage == 1){
                            srl_song_comment.finishRefresh();
                        }else{
                            srl_song_comment.finishLoadMore();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if(mPage == 1){
                            srl_song_comment.finishRefresh();
                        }else{
                            srl_song_comment.finishLoadMore();
                        }
                    }
                });
    }


    @Override
    public void onBackPressed() {
        //全屏，切换会竖屏播放
        if(spv_video.getPlayerMode() == SuperPlayerDef.PlayerMode.FULLSCREEN){
            spv_video.switchPlayMode(SuperPlayerDef.PlayerMode.WINDOW);
        }else{
            finish();
        }
    }

}
