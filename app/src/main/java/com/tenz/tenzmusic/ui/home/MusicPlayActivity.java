package com.tenz.tenzmusic.ui.home;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.arialyy.aria.core.Aria;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;
import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.app.App;
import com.tenz.tenzmusic.app.Constant;
import com.tenz.tenzmusic.base.BaseActivity;
import com.tenz.tenzmusic.db.DBManager;
import com.tenz.tenzmusic.entity.PlaySongBean;
import com.tenz.tenzmusic.helper.BaseUIListener;
import com.tenz.tenzmusic.helper.Util;
import com.tenz.tenzmusic.receiver.MusicBroadcastReceiver;
import com.tenz.tenzmusic.receiver.ReceiverManager;
import com.tenz.tenzmusic.service.MusicService;
import com.tenz.tenzmusic.util.AppUtil;
import com.tenz.tenzmusic.util.AudioUtil;
import com.tenz.tenzmusic.util.DisplayUtil;
import com.tenz.tenzmusic.util.GlideUtil;
import com.tenz.tenzmusic.util.LogUtil;
import com.tenz.tenzmusic.util.ResourceUtil;
import com.tenz.tenzmusic.util.StatusBarUtil;
import com.tenz.tenzmusic.util.StringUtil;
import com.tenz.tenzmusic.util.ToastUtil;
import com.tenz.tenzmusic.widget.dialog.BaseDialog;
import com.tenz.tenzmusic.widget.dialog.CommonDialog;
import com.tenz.tenzmusic.widget.dialog.ViewConvertListener;
import com.tenz.tenzmusic.widget.dialog.ViewHolder;
import com.tenz.tenzmusic.widget.image.ShapeImageView;
import com.tenz.tenzmusic.widget.lyric.LrcView;
import com.tenz.tenzmusic.widget.pop.SongListPopWin;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MusicPlayActivity extends BaseActivity {

    @BindView(R.id.ll_container)
    LinearLayout ll_container;
    @BindView(R.id.tv_song_info)
    TextView tv_song_info;
    @BindView(R.id.tv_song_lyric)
    TextView tv_song_lyric;
    @BindView(R.id.rl_song_info)
    RelativeLayout rl_song_info;
    @BindView(R.id.rl_song_lyric)
    RelativeLayout rl_song_lyric;
    @BindView(R.id.lrcview_song)
    LrcView lrcview_song;
    @BindView(R.id.tv_song_name)
    TextView tv_song_name;
    @BindView(R.id.tv_singer)
    TextView tv_singer;
    @BindView(R.id.siv_song_image)
    ShapeImageView siv_song_image;
    @BindView(R.id.sb_sound)
    SeekBar sb_sound;
    @BindView(R.id.sb_music)
    SeekBar sb_music;
    @BindView(R.id.tv_total_time)
    TextView tv_total_time;
    @BindView(R.id.tv_current_time)
    TextView tv_current_time;
    @BindView(R.id.iv_music_play_stop)
    ImageView iv_music_play_stop;
    @BindView(R.id.iv_repeat_single_random)
    ImageView iv_repeat_single_random;
    @BindView(R.id.iv_like)
    ImageView iv_like;

    private String hash;
    private String album_id;
    private String play_url;//本地歌曲的播放路径

    private ObjectAnimator mObjectAnimator;
    private final static int SONG_INFO = 1;
    private final static int SONG_LYRIC = 2;

    private boolean isHadPlaySong;

    private MusicBroadcastReceiver mMusicBroadcastReceiver;
    private MusicBroadcastReceiver.MusicReceiverListener mMusicReceiverListener
            = new MusicBroadcastReceiver.MusicReceiverListener() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_REGISTER_SUCCESS)){

            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_NULL)){
                //播放空音乐(播放完成，播放错误)，音乐onCompletion，onError
                tv_song_name.setText("音乐");
                tv_singer.setText("艺术家");
                tv_total_time.setText("00:00");
                tv_current_time.setText("00:00");
                siv_song_image.setImageResource(R.drawable.logo);
                lrcview_song.loadLrc("");

                iv_music_play_stop.setImageResource(R.drawable.music_play_gray_dark);
                sb_music.setMax(0);
                sb_music.setProgress(0);

                iv_like.setImageResource(R.drawable.like_gray);
            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_SONG)){
                isHadPlaySong = true;
                mObjectAnimator.start();
                PlaySongBean currentSong = App.getApplication().getmMusicBinder().getCurrentSong();
                if(currentSong != null){
                    int songTotalDuration = App.getApplication().getmMusicBinder().getDuration();
                    int songCurrentDuration = App.getApplication().getmMusicBinder().getProgress();

                    tv_song_name.setText(currentSong.getSong_name());
                    tv_singer.setText(currentSong.getAuthor_name());
                    tv_total_time.setText(StringUtil.stringForTime(songTotalDuration));
                    tv_current_time.setText(StringUtil.stringForTime(songCurrentDuration));
                    GlideUtil.loadImage(mContext,currentSong.getImg().replace("{size}","400"),GlideUtil.mLogoRequestOptions,siv_song_image);
                    lrcview_song.loadLrc(currentSong.getLyrics());

                    if(!App.getApplication().getmMusicBinder().getPlayState()){
                        iv_music_play_stop.setImageResource(R.drawable.music_play_gray_dark);
                    }else{
                        iv_music_play_stop.setImageResource(R.drawable.music_stop_gray_dark);
                    }
                    sb_music.setMax(songTotalDuration);
                    sb_music.setProgress(songCurrentDuration);

                    PlaySongBean likePlaySongByHash = DBManager.newInstance().playSongDao().getLikePlaySongByHash(true,currentSong.getHash());
                    if(likePlaySongByHash != null){
                        iv_like.setImageResource(R.drawable.like);
                    }else{
                        iv_like.setImageResource(R.drawable.like_gray);
                    }
                }
            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAYING)){
                if(!isHadPlaySong){
                    isHadPlaySong = true;
                    mObjectAnimator.start();
                    //设置歌曲信息
                    PlaySongBean currentSong = App.getApplication().getmMusicBinder().getCurrentSong();
                    if(currentSong != null){
                        int songTotalDuration = App.getApplication().getmMusicBinder().getDuration();
                        int songCurrentDuration = App.getApplication().getmMusicBinder().getProgress();

                        tv_song_name.setText(currentSong.getSong_name());
                        tv_singer.setText(currentSong.getAuthor_name());
                        tv_total_time.setText(StringUtil.stringForTime(songTotalDuration));
                        tv_current_time.setText(StringUtil.stringForTime(songCurrentDuration));
                        GlideUtil.loadImage(mContext,currentSong.getImg().replace("{size}","400"),GlideUtil.mLogoRequestOptions,siv_song_image);
                        lrcview_song.loadLrc(currentSong.getLyrics());

                        if(!App.getApplication().getmMusicBinder().getPlayState()){
                            iv_music_play_stop.setImageResource(R.drawable.music_play_gray_dark);
                        }else{
                            iv_music_play_stop.setImageResource(R.drawable.music_stop_gray_dark);
                        }
                        sb_music.setMax(songTotalDuration);
                        sb_music.setProgress(songCurrentDuration);

                        PlaySongBean likePlaySongByHash = DBManager.newInstance().playSongDao().getLikePlaySongByHash(true, currentSong.getHash());
                        if(likePlaySongByHash != null){
                            iv_like.setImageResource(R.drawable.like);
                        }else{
                            iv_like.setImageResource(R.drawable.like_gray);
                        }
                    }
                }else{
                    //更新进度
                    int songTotalDuration = App.getApplication().getmMusicBinder().getDuration();
                    int songCurrentDuration = App.getApplication().getmMusicBinder().getProgress();
                    sb_music.setMax(songTotalDuration);
                    sb_music.setProgress(songCurrentDuration);
                    tv_current_time.setText(StringUtil.stringForTime(songCurrentDuration));
                    lrcview_song.updateTime(songCurrentDuration);
                }
            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_START)){
                mObjectAnimator.start();
                iv_music_play_stop.setImageResource(R.drawable.music_stop_gray_dark);
            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_PAUSE)){
                mObjectAnimator.pause();
                iv_music_play_stop.setImageResource(R.drawable.music_play_gray_dark);
            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_STOP)){
                mObjectAnimator.pause();
            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_PREVIOUS)){

            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_NEXT)){

            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_ERROR)){

            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_music_play;
    }

    @Override
    protected void initView() {
        super.initView();

        StatusBarUtil.setBarColor(mActivity, Color.TRANSPARENT,true);
        ll_container.setPadding(0,StatusBarUtil.getStatusBarHeight(mContext),0,0);

        initCDAnimation();

        showSongInfoOrLyric(SONG_INFO);

        initSound();

        lrcview_song.setDraggable(true, new LrcView.OnPlayClickListener() {
            @Override
            public boolean onPlayClick(long time) {
                App.getApplication().getmMusicBinder().setProgress(new Long(time).intValue());
                return false;
            }
        });
        sb_music.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                App.getApplication().getmMusicBinder().setProgress(progress);
            }
        });
        lrcview_song.setOnSingerClickListener(new LrcView.OnSingleClickListener() {
            @Override
            public void onClick() {
                showSongInfoOrLyric(SONG_INFO);
            }
        });
        rl_song_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSongInfoOrLyric(SONG_LYRIC);
            }
        });
    }

    private void initSound() {
        int mediaMaxVolume = AudioUtil.newInstance(mContext).getMediaMaxVolume();
        int mediaVolume = AudioUtil.newInstance(mContext).getMediaVolume();
        sb_sound.setMax(mediaMaxVolume);
        sb_sound.setProgress(mediaVolume);
        sb_sound.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                AudioUtil.newInstance(mContext).setMediaVolume(progress);
            }
        });
    }

    private void showSongInfoOrLyric(int type){
        if(type == SONG_INFO){
            tv_song_info.setTextColor(ResourceUtil.getColor(R.color.app_color));
            tv_song_lyric.setTextColor(ResourceUtil.getColor(R.color.color_gray));
            rl_song_info.setVisibility(View.VISIBLE);
            rl_song_lyric.setVisibility(View.GONE);
        }else{
            tv_song_info.setTextColor(ResourceUtil.getColor(R.color.color_gray));
            tv_song_lyric.setTextColor(ResourceUtil.getColor(R.color.app_color));
            rl_song_info.setVisibility(View.GONE);
            rl_song_lyric.setVisibility(View.VISIBLE);
        }
    }

    /**
     * CD旋转动画
     */
    private void initCDAnimation() {
        mObjectAnimator = ObjectAnimator.ofFloat(siv_song_image, "rotation", 0, 360);
        mObjectAnimator.setDuration(25000);
        //匀速平滑旋转
        mObjectAnimator.setInterpolator(new LinearInterpolator());
        //无限循环旋转
        mObjectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mObjectAnimator.setRepeatMode(ValueAnimator.RESTART);
//        mObjectAnimator.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mObjectAnimator.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mObjectAnimator.pause();
    }

    @Override
    public void finish() {
        super.finish();
        mActivity.overridePendingTransition(R.anim.anim_no_anim,R.anim.anim_down);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ReceiverManager.unRegisterMusicReceiver(mContext,mMusicBroadcastReceiver);
    }

    @Override
    protected void initData() {
        super.initData();
        mMusicBroadcastReceiver = new MusicBroadcastReceiver();
        mMusicBroadcastReceiver.setmMusicReceiverListener(mMusicReceiverListener);
        ReceiverManager.registerMusicReceiver(mContext,mMusicBroadcastReceiver);

        PlaySongBean currentSong = App.getApplication().getmMusicBinder().getCurrentSong();
        if(currentSong != null){
            int songTotalDuration = App.getApplication().getmMusicBinder().getDuration();
            int songCurrentDuration = App.getApplication().getmMusicBinder().getProgress();

            tv_song_name.setText(currentSong.getSong_name());
            tv_singer.setText(currentSong.getAuthor_name());
            tv_total_time.setText(StringUtil.stringForTime(songTotalDuration));
            tv_current_time.setText(StringUtil.stringForTime(songCurrentDuration));
            GlideUtil.loadImage(mContext,currentSong.getImg().replace("{size}","400"),GlideUtil.mLogoRequestOptions,siv_song_image);
            lrcview_song.loadLrc(currentSong.getLyrics());

            if(!App.getApplication().getmMusicBinder().getPlayState()){
                iv_music_play_stop.setImageResource(R.drawable.music_play_gray_dark);
            }else{
                iv_music_play_stop.setImageResource(R.drawable.music_stop_gray_dark);
            }
            sb_music.setMax(songTotalDuration);
            sb_music.setProgress(songCurrentDuration);

            PlaySongBean likePlaySongByHash = DBManager.newInstance().playSongDao().getLikePlaySongByHash(true,currentSong.getHash());
            if(likePlaySongByHash != null){
                iv_like.setImageResource(R.drawable.like);
            }else{
                iv_like.setImageResource(R.drawable.like_gray);
            }
        }

        Bundle bundle = getIntent().getExtras();
        if(null != bundle){
            hash = bundle.getString("hash");
            album_id = bundle.getString("album_id");
            play_url = bundle.getString("play_url");
            if((!StringUtil.isEmpty(hash) && !StringUtil.isEmpty(album_id)) || (!StringUtil.isEmpty(hash) && !StringUtil.isEmpty(play_url))){
                PlaySongBean currentSongBean = App.getApplication().getmMusicBinder().getCurrentSong();
                if(null != currentSongBean){
                    boolean playState = App.getApplication().getmMusicBinder().getPlayState();
                    boolean isSameSong = false;
                    if(hash.equals(currentSongBean.getHash())){
                        isSameSong = true;
                    }
                    if(playState && isSameSong){
                        PlaySongBean likePlaySongByHash = DBManager.newInstance().playSongDao().getLikePlaySongByHash(true,currentSong.getHash());
                        if(likePlaySongByHash != null){
                            iv_like.setImageResource(R.drawable.like);
                        }else{
                            iv_like.setImageResource(R.drawable.like_gray);
                        }
                    }else{
                        if(!StringUtil.isEmpty(play_url)){
                            App.getApplication().getmMusicBinder().playLocalMusic(hash,play_url);
                        }else{
                            App.getApplication().getmMusicBinder().playMusic(hash,album_id);
                        }
                    }
                }else{
                    if(!StringUtil.isEmpty(play_url)){
                        App.getApplication().getmMusicBinder().playLocalMusic(hash,play_url);
                    }else{
                        App.getApplication().getmMusicBinder().playMusic(hash,album_id);
                    }
                }
            }
        }
    }

    @OnClick({R.id.iv_down,R.id.tv_song_info,R.id.tv_song_lyric,R.id.iv_share,R.id.iv_music,
        R.id.iv_download,R.id.iv_like,R.id.iv_comment,R.id.iv_dot,
        R.id.iv_music_play_stop,R.id.iv_repeat_single_random,R.id.iv_music_previous,R.id.iv_music_next,
        R.id.iv_music_list})
    public void onClick(View view){
        PlaySongBean currentSong;
        Bundle bundle;
        switch (view.getId()){
            case R.id.iv_down:
                finish();
                break;
            case R.id.tv_song_info:
                //歌曲信息
                showSongInfoOrLyric(SONG_INFO);
                break;
            case R.id.tv_song_lyric:
                //歌词信息
                showSongInfoOrLyric(SONG_LYRIC);
                break;
            case R.id.iv_share:
                currentSong = App.getApplication().getmMusicBinder().getCurrentSong();
                if(currentSong != null){
                    showSwitchModelDialog(currentSong);
                }
                break;
            case R.id.iv_music:

                break;
            case R.id.iv_download:
                currentSong = App.getApplication().getmMusicBinder().getCurrentSong();
                if(currentSong != null){
                    PlaySongBean downloadPlaySongByHash = DBManager.newInstance().playSongDao().getDownloadPlaySongByHash(true, currentSong.getHash());
                    if(null != downloadPlaySongByHash){
                        ToastUtil.showToast("歌曲已下载");
                    }else{
                        LogUtil.e("添加下载---:"+currentSong.getPlay_url());
                        ToastUtil.showToast("成功添加下载任务，请在我的下载中查看");

                        //下载之前先入数据库，下载完成之后会更新数据库（改为本地标识，更改本地播放路径）
                        DBManager.newInstance().playSongDao().insert(currentSong);
                        Aria.download(mContext)
                                .load(currentSong.getPlay_url())
                                .setFilePath(Environment.getExternalStorageDirectory() + "/Download/" + currentSong.getSong_name())
                                .setExtendField(currentSong.getHash())//设置扩展字段作为标识
                                .create();
                    }
                }
                break;
            case R.id.iv_like:
                currentSong = App.getApplication().getmMusicBinder().getCurrentSong();
                if(currentSong != null){
                    currentSong.setIs_like(!currentSong.isIs_like());
                    DBManager.newInstance().playSongDao().updateLike(currentSong.getHash(),currentSong.isIs_like());

                    if(currentSong.isIs_like()){
                        iv_like.setImageResource(R.drawable.like);
                    }else{
                        iv_like.setImageResource(R.drawable.like_gray);
                    }
                }
                break;
            case R.id.iv_comment:
                currentSong = App.getApplication().getmMusicBinder().getCurrentSong();
                if(currentSong != null){
                    bundle = new Bundle();
                    bundle.putString("hash",currentSong.getHash());
                    startActivity(SongCommentActivity.class,bundle);
                }
                break;
            case R.id.iv_dot:

                break;
            case R.id.iv_music_play_stop:
                if(!App.getApplication().getmMusicBinder().isPrepare()){
                    return;
                }
                if(App.getApplication().getmMusicBinder().getPlayState()){
                    App.getApplication().getmMusicBinder().pause();
                    iv_music_play_stop.setImageResource(R.drawable.music_play_gray_dark);
                }else{
                    App.getApplication().getmMusicBinder().start();
                    iv_music_play_stop.setImageResource(R.drawable.music_stop_gray_dark);
                }
                break;
            case R.id.iv_repeat_single_random:
                App.getApplication().getmMusicBinder().changePlayModel();
                switchMusicModel(App.getApplication().getmMusicBinder().getPlayModel());
                break;
            case R.id.iv_music_previous:
                App.getApplication().getmMusicBinder().playPrevious();
                break;
            case R.id.iv_music_next:
                App.getApplication().getmMusicBinder().playNext();
                break;
            case R.id.iv_music_list:
                List<PlaySongBean> playSongBeanList = App.getApplication().getmMusicBinder().getPlaySongBeanList();
                SongListPopWin songListPopWin = new SongListPopWin(mActivity,mContext,playSongBeanList,getSupportFragmentManager());
                songListPopWin.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        AppUtil.setBackgroundAlpha(mActivity,1);
                    }
                });
                AppUtil.setBackgroundAlpha(mActivity,0.6f);
                songListPopWin.showAtLocation(ll_container, Gravity.BOTTOM,0,0);
                break;
        }
    }

    /**
     * 切换模式
     * @param model
     */
    private void switchMusicModel(int model){
        if(model == MusicService.PLAY_MODEL_REPEAT){
            iv_repeat_single_random.setImageResource(R.drawable.music_repeat_gray);
        }else if(model == MusicService.PLAY_MODEL_SINGLE){
            iv_repeat_single_random.setImageResource(R.drawable.music_single_gray);
        }else{
            iv_repeat_single_random.setImageResource(R.drawable.music_random_gray);
        }
    }

    /**
     * 分享弹框
     * @param currentSong
     */
    private void showSwitchModelDialog(PlaySongBean currentSong) {
        CommonDialog.newInstance()
                .setLayoutId(R.layout.dialog_share)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseDialog dialog) {
                        holder.setOnClickListener(R.id.ll_qq, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                //QQ分享
                                Tencent mTencent = Tencent.createInstance("1109892018", App.getContext(), "com.tenz.tenzmusic.fileprovider");
                                Bundle params = new Bundle();
                                params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);//图文分享类型
                                params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, currentSong.getPlay_url());//跳转URL
                                params.putString(QQShare.SHARE_TO_QQ_TITLE, currentSong.getSong_name());
                                params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "快和我来听听吧~");
                                params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "分秒动听");
                                mTencent.shareToQQ(mActivity, params, new BaseUIListener(mContext));
                            }
                        });
                        holder.setOnClickListener(R.id.ll_wechat, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                //微信分享
                                IWXAPI api = WXAPIFactory.createWXAPI(mContext, Constant.WX_APP_ID,false);
                                WXWebpageObject webpage = new WXWebpageObject();
                                webpage.webpageUrl = currentSong.getPlay_url();
                                WXMediaMessage msg = new WXMediaMessage(webpage);
                                msg.title = currentSong.getSong_name();
                                msg.description = "快和我来听听吧";
                                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
                                Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 150, 150, true);
                                bmp.recycle();
                                msg.thumbData = Util.bmpToByteArray(thumbBmp, true);
                                SendMessageToWX.Req req = new SendMessageToWX.Req();
                                req.transaction = "webpage" + System.currentTimeMillis();
                                req.message = msg;
                                req.scene = SendMessageToWX.Req.WXSceneSession;
                                api.sendReq(req);
                            }
                        });
                        holder.setOnClickListener(R.id.tv_close, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                    }
                })
                .setWidth(DisplayUtil.px2dp(DisplayUtil.getWindowWidth()))
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

}
