package com.tenz.tenzmusic.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.app.App;
import com.tenz.tenzmusic.app.AppManager;
import com.tenz.tenzmusic.base.BaseActivity;
import com.tenz.tenzmusic.entity.PlaySongBean;
import com.tenz.tenzmusic.receiver.MusicBroadcastReceiver;
import com.tenz.tenzmusic.receiver.ReceiverManager;
import com.tenz.tenzmusic.util.GlideUtil;
import com.tenz.tenzmusic.util.StatusBarUtil;
import com.tenz.tenzmusic.widget.lyric.LrcView;

import butterknife.BindView;

public class LockActivity extends BaseActivity {

    @BindView(R.id.iv_image)
    ImageView iv_image;
    @BindView(R.id.lrcview_song)
    LrcView lrcview_song;
    @BindView(R.id.tv_song_name)
    TextView tv_song_name;
    @BindView(R.id.tv_singer)
    TextView tv_singer;

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
                lrcview_song.loadLrc("");
            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_SONG)){
                isHadPlaySong = true;
                PlaySongBean currentSong = App.getApplication().getmMusicBinder().getCurrentSong();
                if(currentSong != null){
                    tv_song_name.setText(currentSong.getSong_name());
                    tv_singer.setText(currentSong.getAuthor_name());
                    GlideUtil.loadImage(mContext,currentSong.getImg().replace("{size}","400"),iv_image);
                    lrcview_song.loadLrc(currentSong.getLyrics());
                }
            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAYING)){
                if(!isHadPlaySong){
                    isHadPlaySong = true;
                    //设置歌曲信息
                    PlaySongBean currentSong = App.getApplication().getmMusicBinder().getCurrentSong();
                    if(currentSong != null){
                        tv_song_name.setText(currentSong.getSong_name());
                        tv_singer.setText(currentSong.getAuthor_name());
                        GlideUtil.loadImage(mContext,currentSong.getImg().replace("{size}","400"),iv_image);
                        lrcview_song.loadLrc(currentSong.getLyrics());
                    }
                }else{
                    //更新进度
                    int songCurrentDuration = App.getApplication().getmMusicBinder().getProgress();
                    lrcview_song.updateTime(songCurrentDuration);
                }
            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_START)){

            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_PAUSE)){

            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_STOP)){

            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_PREVIOUS)){

            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_NEXT)){

            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_ERROR)){

            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lock;
    }

    @Override
    protected void beforeContentView() {
        super.beforeContentView();
        this.getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                        | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
    }

    @Override
    protected void initView() {
        super.initView();
        StatusBarUtil.setBarColor(mActivity, Color.TRANSPARENT,false);
    }

    @Override
    protected void initData() {
        super.initData();
        mMusicBroadcastReceiver = new MusicBroadcastReceiver();
        mMusicBroadcastReceiver.setmMusicReceiverListener(mMusicReceiverListener);
        ReceiverManager.registerMusicReceiver(mContext,mMusicBroadcastReceiver);
    }

    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(AppManager.getInstance().isOpenActivity(LockBlackActivity.class)){
            AppManager.getInstance().finishActivity(LockBlackActivity.class);
        }
        ReceiverManager.unRegisterMusicReceiver(mContext,mMusicBroadcastReceiver);
    }

}
