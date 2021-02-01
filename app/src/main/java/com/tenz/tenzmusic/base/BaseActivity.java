package com.tenz.tenzmusic.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.Service.MusicService;
import com.tenz.tenzmusic.app.App;
import com.tenz.tenzmusic.app.AppManager;
import com.tenz.tenzmusic.entity.PlaySongBean;
import com.tenz.tenzmusic.receiver.MusicBroadcastReceiver;
import com.tenz.tenzmusic.receiver.ReceiverManager;
import com.tenz.tenzmusic.ui.home.MusicPlayActivity;
import com.tenz.tenzmusic.util.AppUtil;
import com.tenz.tenzmusic.util.LogUtil;
import com.tenz.tenzmusic.util.StatusBarUtil;
import com.tenz.tenzmusic.widget.dialog.LoadingDialog;
import com.tenz.tenzmusic.widget.music.MusicPlayBar;
import com.tenz.tenzmusic.widget.pop.SongListPopWin;
import com.tenz.tenzmusic.widget.titlebar.TitleBar;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements TitleBar.OnTitleBarClickListener {

    //底部播放栏
    MusicPlayBar mMusicPlayBar;

    protected Activity mActivity;
    protected Context mContext;
    protected Unbinder mUnbinder;

    private LoadingDialog loadingDialog;

    private boolean isHadPlaySong;
    private MusicBroadcastReceiver mMusicBroadcastReceiver;
    private MusicBroadcastReceiver.MusicReceiverListener mMusicReceiverListener
            = new MusicBroadcastReceiver.MusicReceiverListener() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(!isHaveMusicPlayFoot()){
                //没有音乐播放栏，下面MusicPlayBar控件为空会报错
                return;
            }
            String action = intent.getAction();
            if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_REGISTER_SUCCESS)){

            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_NULL)){
                //播放空音乐(播放完成，播放错误)，音乐onCompletion，onError
                mMusicPlayBar.setSongName("音乐");
                mMusicPlayBar.setSinger("艺术家");
                mMusicPlayBar.setSongImage(R.drawable.logo);

                mMusicPlayBar.setMusicPlayControlImage(R.drawable.music_play_gray_dark);
            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_SONG)){
                isHadPlaySong = true;
                PlaySongBean currentSong = App.getApplication().getmMusicBinder().getCurrentSong();
                if(currentSong != null){
                    mMusicPlayBar.setSongName(currentSong.getSong_name());
                    mMusicPlayBar.setSinger(currentSong.getAuthor_name());
                    mMusicPlayBar.setSongImage(currentSong.getImg().replace("{size}","400"));

                    if(!App.getApplication().getmMusicBinder().getPlayState()){
                        mMusicPlayBar.setMusicPlayControlImage(R.drawable.music_play_gray_dark);
                    }else{
                        mMusicPlayBar.setMusicPlayControlImage(R.drawable.music_stop_gray_dark);
                    }
                }
            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAYING)){
                if(!isHadPlaySong){
                    isHadPlaySong = true;
                    //设置歌曲信息
                    PlaySongBean currentSong = App.getApplication().getmMusicBinder().getCurrentSong();
                    if(currentSong != null){
                        mMusicPlayBar.setSongName(currentSong.getSong_name());
                        mMusicPlayBar.setSinger(currentSong.getAuthor_name());
                        mMusicPlayBar.setSongImage(currentSong.getImg().replace("{size}","400"));

                        if(!App.getApplication().getmMusicBinder().getPlayState()){
                            mMusicPlayBar.setMusicPlayControlImage(R.drawable.music_play_gray_dark);
                        }else{
                            mMusicPlayBar.setMusicPlayControlImage(R.drawable.music_stop_gray_dark);
                        }
                    }
                }else{
                    //更新进度

                }
            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_START)){
                mMusicPlayBar.setMusicPlayControlImage(R.drawable.music_stop_gray_dark);
            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_PAUSE)){
                mMusicPlayBar.setMusicPlayControlImage(R.drawable.music_play_gray_dark);
            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_STOP)){

            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_PREVIOUS)){

            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_NEXT)){

            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_ERROR)){

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        StatusBarUtil.setBarColor(this,getResources().getColor(R.color.app_color),false);
        mUnbinder = ButterKnife.bind(this);
        mActivity = this;
        mContext = this;
        if(isRegisterEventBus()){
            EventBus.getDefault().register(this);
        }
        if(isHaveMusicPlayFoot()){
            mMusicBroadcastReceiver = new MusicBroadcastReceiver();
            mMusicBroadcastReceiver.setmMusicReceiverListener(mMusicReceiverListener);
            ReceiverManager.registerMusicReceiver(mContext,mMusicBroadcastReceiver);
        }
        LogUtil.d("activity:------------" + getComponentName().getClassName());
        AppManager.getInstance().addActivity(this);
        initView();
        initData();

    }



    /**
     * 是否有底部播放栏
     * @return
     */
    protected boolean isHaveMusicPlayFoot() {
        return false;
    }

    /**
     * 是否使用EventBus
     * @return
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 显示loading框
     */
    public void showLoadingDialog() {
        if(loadingDialog != null){
            if (loadingDialog.isShowing()){
                loadingDialog.dismiss();
            }
            loadingDialog = null;
        }
        if(!mActivity.isFinishing()){
            loadingDialog = new LoadingDialog(mContext);
            loadingDialog.show();
        }
    }

    /**
     * 隐藏loading框
     */
    public void dismissLoadingDialog() {
        if(loadingDialog != null){
            if (loadingDialog.isShowing()){
                loadingDialog.dismiss();
            }
            loadingDialog = null;
        }
    }

    protected abstract int getLayoutId();

    protected void initView(){

    }

    protected void initData(){

    }

    /**
     * 初始化音乐播放栏
     * @param musicPlayBar
     */
    public void initMusicPlayBar(MusicPlayBar musicPlayBar, LinearLayout ll_container){
        this.mMusicPlayBar = musicPlayBar;
        MusicService.MusicBinder musicBinder = App.getApplication().getmMusicBinder();
        if(musicBinder == null){
            return;
        }
        PlaySongBean currentSong = musicBinder.getCurrentSong();
        if(null != currentSong){
            mMusicPlayBar.setSongImage(currentSong.getImg().replace("{size}","400"));
            mMusicPlayBar.setSongName(currentSong.getSong_name());
            mMusicPlayBar.setSinger(currentSong.getAuthor_name());
            if(musicBinder.getPlayState()){
                mMusicPlayBar.setMusicPlayControlImage(R.drawable.music_stop_gray_dark);
            }
        }
        musicPlayBar.setmOnMusicPlayBarClickListener(new MusicPlayBar.OnMusicPlayBarClickListener() {
            @Override
            public void toMusicPlay() {
                startActivity(MusicPlayActivity.class);
            }

            @Override
            public void musicPlayControl() {
                if(!musicBinder.isPrepare()){
                    return;
                }
                if(musicBinder.getPlayState()){
                    musicBinder.pause();
                    mMusicPlayBar.setMusicPlayControlImage(R.drawable.music_play_gray_dark);
                }else{
                    musicBinder.start();
                    mMusicPlayBar.setMusicPlayControlImage(R.drawable.music_stop_gray_dark);
                }
            }

            @Override
            public void musicPlayList() {
                List<PlaySongBean> playSongBeanList = musicBinder.getPlaySongBeanList();
                SongListPopWin songListPopWin = new SongListPopWin(mActivity,mContext,playSongBeanList,getSupportFragmentManager());
                songListPopWin.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        AppUtil.setBackgroundAlpha(mActivity,1);
                    }
                });
                AppUtil.setBackgroundAlpha(mActivity,0.6f);
                songListPopWin.showAtLocation(ll_container, Gravity.BOTTOM,0,0);
            }
        });
    }

    /**
     * 初始化titlebar
     * @param titleBar
     * @param title
     */
    public void initTitleBar(TitleBar titleBar, String title){
        initTitleBar(titleBar, title, false);
    }

    /**
     * 初始化titlebar
     * @param titleBar
     * @param title
     */
    public void initTitleBar(TitleBar titleBar, String title, boolean isShowGone){
        titleBar.setmOnTitleBarClickListener(this);
        if(isShowGone){
            titleBar.setBackGone();
        }
        titleBar.setTitle(title);
    }

    /**
     * 跳转页面
     *
     * @param cls
     */
    public void startActivity(Class cls) {
        startActivity(cls, null);
    }

    /**
     * 跳转页面,带参数
     *
     * @param cls
     */
    public void startActivity(Class cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void startActivityForResult(Class cls, int requestCode , Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent,requestCode);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                View v = getCurrentFocus();
                if (isShouldHideKeyboard(v, ev)) {
                    hideKeyBord();
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 隐藏键盘
     */
    public void hideKeyBord() {
        //点击空白位置 隐藏软键盘
        InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService
                (INPUT_METHOD_SERVICE);
        boolean hideSoftInputFromWindow = mInputMethodManager.hideSoftInputFromWindow(this
                .getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void back() {
//        AppManager.getInstance().finishActivity();
        finish();
    }

    @Override
    public void more() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        if(isHaveMusicPlayFoot()){
            ReceiverManager.unRegisterMusicReceiver(mContext,mMusicBroadcastReceiver);
        }
    }

}
