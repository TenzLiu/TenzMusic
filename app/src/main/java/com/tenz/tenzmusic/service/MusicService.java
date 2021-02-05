package com.tenz.tenzmusic.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.task.DownloadTask;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.api.RetrofitApi;
import com.tenz.tenzmusic.app.App;
import com.tenz.tenzmusic.app.AppManager;
import com.tenz.tenzmusic.db.DBManager;
import com.tenz.tenzmusic.entity.PlaySongBean;
import com.tenz.tenzmusic.entity.SongDetailBean;
import com.tenz.tenzmusic.http.BaseObserver;
import com.tenz.tenzmusic.http.RetrofitFactory;
import com.tenz.tenzmusic.http.RxScheduler;
import com.tenz.tenzmusic.receiver.LockReceiver;
import com.tenz.tenzmusic.receiver.MusicBroadcastReceiver;
import com.tenz.tenzmusic.receiver.ReceiverManager;
import com.tenz.tenzmusic.ui.HomeActivity;
import com.tenz.tenzmusic.ui.home.MusicPlayActivity;
import com.tenz.tenzmusic.util.LogUtil;
import com.tenz.tenzmusic.util.StringUtil;
import com.tenz.tenzmusic.util.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 音乐服务
 */
public class MusicService extends Service {

    private MediaPlayer mPlayer;
    private List<PlaySongBean> mPlaySongBeanList = new ArrayList<>();
    private int mPlayMusicPosition = -1;
    private int playModel = PLAY_MODEL_REPEAT;//默认循环
    public static final int PLAY_MODEL_REPEAT = 1;//循环播放
    public static final int PLAY_MODEL_SINGLE = 2;//单曲播放
    public static final int PLAY_MODEL_RANDOM = 3;//随机播放
    private boolean mIsPrepared;//音乐是否准备完毕

    private Notification mMusicNotification;
    private RemoteViews mMusicContentView;

    private LockReceiver mLockReceiver;

    private boolean isHadPlaySong;
    private MusicBroadcastReceiver mMusicBroadcastReceiver;
    private MusicBroadcastReceiver.MusicReceiverListener mMusicReceiverListener
            = new MusicBroadcastReceiver.MusicReceiverListener() {
        @Override
        public void onReceive(Context context, Intent intent) {
            LogUtil.e("MusicReceiverListener:----" + intent.getAction());
            String action = intent.getAction();
            if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_REGISTER_SUCCESS)){

            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_NULL)){
                //播放空音乐(播放完成，播放错误)，音乐onCompletion，onError

            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_SONG)){
                isHadPlaySong = true;

            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAYING)){
                if(!isHadPlaySong) {
                    isHadPlaySong = true;

                }
            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_START)){
                mMusicContentView.setImageViewResource(R.id.iv_music_play_stop,R.drawable.music_stop_gray_dark);
                startForeground(10,mMusicNotification);
            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_PAUSE)){
                mMusicContentView.setImageViewResource(R.id.iv_music_play_stop,R.drawable.music_play_gray_dark);
                startForeground(10,mMusicNotification);
            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_STOP)){

            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_PREVIOUS)){

            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_NEXT)){

            }else if(action.equals(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_ERROR)){

            }
        }
    };

    /**
     * 通知栏广播接收类
     */
    public static final String ACTION_NOTIFICATION = "intent_action_notification";
    public static final int VIEW_ID_NOTIFICATION_PREVIOUS = 1;
    public static final int VIEW_ID_NOTIFICATION_PLAY_STOP = 2;
    public static final int VIEW_ID_NOTIFICATION_NEXT = 3;
    public static final int VIEW_ID_NOTIFICATION_OPEN = 4;
    private NotificationBroadcastReceiver mNotificationBroadcastReceiver;
    public class NotificationBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            if(action.equals(ACTION_NOTIFICATION)){
                int buttonId=intent.getIntExtra("view_id",0);
                switch (buttonId){
                    case VIEW_ID_NOTIFICATION_PREVIOUS:
                        //上一首
                        App.getApplication().getmMusicBinder().playPrevious();
                        break;
                    case VIEW_ID_NOTIFICATION_PLAY_STOP:
                        //播放暂停
                        if(!App.getApplication().getmMusicBinder().isPrepare()){
                            return;
                        }
                        if(App.getApplication().getmMusicBinder().getPlayState()){
                            App.getApplication().getmMusicBinder().pause();
                            mMusicContentView.setImageViewResource(R.id.iv_music_play_stop,R.drawable.music_play_gray_dark);
                        }else{
                            App.getApplication().getmMusicBinder().start();
                            mMusicContentView.setImageViewResource(R.id.iv_music_play_stop,R.drawable.music_stop_gray_dark);
                        }
                        break;
                    case VIEW_ID_NOTIFICATION_NEXT:
                        //下一首
                        App.getApplication().getmMusicBinder().playNext();
                    case VIEW_ID_NOTIFICATION_OPEN:
                        //打开APP
                        Intent homeIntent = new Intent(context, HomeActivity.class);
                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Intent musicPlayIntent = new Intent(context, MusicPlayActivity.class);
                        musicPlayIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if(AppManager.getInstance().isOpenActivity(HomeActivity.class)){
                            context.startActivity(musicPlayIntent);
                        }else{
                            Intent[] intents = new Intent[2];
                            intents[0] = homeIntent;
                            intents[1] = musicPlayIntent;
                            context.startActivities(intents);
                        }
                    default:
                        break;
                }
            }
        }
    }

    private Runnable musicPlayRunnable = new Runnable() {
        @Override
        public void run() {
            while (true){
                try {
                    if(mPlayer != null && mPlayer.isPlaying()){
                        if(App.getApplication().getmMusicBinder().getCurrentSong() != null){
                            //每隔一秒发送播放中广播通知
                            Intent intent = new Intent(MusicBroadcastReceiver.ACTION_MUSIC_PLAYING);
                            sendBroadcast(intent);
                        }
                    }
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        if (mPlayer != null) {
            mPlayer.stop();//停止播放
            mPlayer.release();//释放资源
            mPlayer = null;//把player对象设置为null
        }
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mMusicBroadcastReceiver = new MusicBroadcastReceiver();
        mMusicBroadcastReceiver.setmMusicReceiverListener(mMusicReceiverListener);
        ReceiverManager.registerMusicReceiver(this,mMusicBroadcastReceiver);

        mNotificationBroadcastReceiver = new NotificationBroadcastReceiver();
        IntentFilter notificationIntentFilter = new IntentFilter();
        notificationIntentFilter.addAction(ACTION_NOTIFICATION);
        registerReceiver(mNotificationBroadcastReceiver,notificationIntentFilter);

        mLockReceiver = new LockReceiver();
        IntentFilter lockIntentFilter = new IntentFilter();
        lockIntentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        lockIntentFilter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(mLockReceiver,lockIntentFilter);

        //通知栏显示
        initNotificationView();
        //启动线程每隔一秒发送播放中音乐广播通知
        new Thread(musicPlayRunnable).start();

        //监听下载歌曲信息
        Aria.download(this).register();
    }

    /**
     * 初始化通知栏
     */
    private void initNotificationView() {
        String tickerText = "分秒动听";
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mMusicContentView = new RemoteViews(getPackageName(), R.layout.layout_notification_music_play);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 通知渠道的id
            String CHANNEL_ID = "tenz_music_channel";
            String CHANNEL_NAME = "tenz_music";
            if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
                int importance = NotificationManager.IMPORTANCE_LOW;
                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
                mChannel.enableLights(true);
                notificationManager.createNotificationChannel(mChannel);
            }
            mMusicNotification = new Notification.Builder(getApplicationContext())
                    .setContentTitle(tickerText)
                    .setContentText("一起来听歌~")
                    .setSmallIcon(R.drawable.music_icon_white)
                    .setContent(mMusicContentView)
                    .setChannelId(CHANNEL_ID)
                    .build();
        }else{
            mMusicNotification = new Notification.Builder(getApplicationContext())
                    .setContentTitle(tickerText)
                    .setContentText("一起来听歌~")
                    .setSmallIcon(R.drawable.music_icon_white)
                    .setContent(mMusicContentView)
                    .build();
        }

        Intent buttonIntent =new Intent(ACTION_NOTIFICATION);
        buttonIntent.putExtra("view_id", VIEW_ID_NOTIFICATION_PREVIOUS);
        PendingIntent viewPendingIntent;
        //假如在同一个requestCode下，使用FLAG_UPDATE_CURRENT，最新接收的广播消息中的Intent的extra会替换掉旧的广播消息Intent的extra
        viewPendingIntent = PendingIntent.getBroadcast(this,1,buttonIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mMusicContentView.setOnClickPendingIntent(R.id.iv_music_previous,viewPendingIntent);

        buttonIntent.putExtra("view_id",VIEW_ID_NOTIFICATION_PLAY_STOP);
        viewPendingIntent = PendingIntent.getBroadcast(this,2,buttonIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        mMusicContentView.setOnClickPendingIntent(R.id.iv_music_play_stop,viewPendingIntent);

        buttonIntent.putExtra("view_id",VIEW_ID_NOTIFICATION_NEXT);
        viewPendingIntent = PendingIntent.getBroadcast(this,3,buttonIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        mMusicContentView.setOnClickPendingIntent(R.id.iv_music_next,viewPendingIntent);

        buttonIntent.putExtra("view_id",VIEW_ID_NOTIFICATION_NEXT);
        viewPendingIntent = PendingIntent.getBroadcast(this,4,buttonIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        mMusicContentView.setOnClickPendingIntent(R.id.iv_image,viewPendingIntent);

        startForeground(10,mMusicNotification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            mPlayer.stop();//停止播放
            mPlayer.release();//释放资源
            mPlayer = null;//把player对象设置为null
        }
        ReceiverManager.unRegisterMusicReceiver(this,mMusicBroadcastReceiver);
        unregisterReceiver(mNotificationBroadcastReceiver);
        unregisterReceiver(mLockReceiver);
        Aria.download(this).unRegister();

    }

    @Download.onNoSupportBreakPoint public void onNoSupportBreakPoint(DownloadTask task) {
        LogUtil.e("MusicService 该下载链接不支持断点");
    }

    @Download.onTaskStart public void onTaskStart(DownloadTask task) {
        LogUtil.e("MusicService " + task.getDownloadEntity().getFileName() + "，开始下载");
    }

    @Download.onTaskStop public void onTaskStop(DownloadTask task) {
        LogUtil.e("MusicService " + task.getDownloadEntity().getFileName() + "，停止下载");
    }

    @Download.onTaskCancel public void onTaskCancel(DownloadTask task) {
        LogUtil.e("MusicService " + task.getDownloadEntity().getFileName() + "，取消下载");
    }

    @Download.onTaskFail public void onTaskFail(DownloadTask task) {
        LogUtil.e("MusicService " + "，下载失败");
    }

    @Download.onTaskComplete public void onTaskComplete(DownloadTask task) {
        LogUtil.e("MusicService " + task.getDownloadEntity().getFileName() + "，下载完成");
        PlaySongBean playSongByHash = DBManager.newInstance().playSongDao().getPlaySongByHash(task.getEntity().getStr());
        if(playSongByHash != null){
            PlaySongBean playSongBean = playSongByHash;
            playSongBean.setIs_local(true);
            playSongBean.setIs_download(true);
            playSongBean.setPlay_url(task.getEntity().getFilePath());
            DBManager.newInstance().playSongDao().update(playSongBean);
        }
    }

    @Download.onTaskRunning public void onTaskRunning(DownloadTask task) {
        LogUtil.e("MusicService onTaskRunning");
        long len = task.getFileSize();
        int p = (int) (task.getCurrentProgress() * 100 / len);
    }

    /**
     * 音乐控制
     */
    public class MusicBinder extends Binder implements MusicControllerImpl,
            MediaPlayer.OnPreparedListener,
            MediaPlayer.OnCompletionListener,
            MediaPlayer.OnErrorListener {

        @Override
        public void setPlaySongBeanList(List<PlaySongBean> playSongBeanList) {
            mPlaySongBeanList = playSongBeanList;
            LogUtil.e("mPlayMusicPosition mPlaySongBeanList.size:" + mPlaySongBeanList.size());
        }

        @Override
        public List<PlaySongBean> getPlaySongBeanList() {
            return mPlaySongBeanList;
        }

        @Override
        public void playMusic(String hash, String album_id) {
            //网络音乐，一种是已经在播放列表存在，所以已经有了play_url，一种是不存在播放列表，需要接口获取play_url。
            List<PlaySongBean> playSongBeanList = getPlaySongBeanList();
            PlaySongBean playSongBean = new PlaySongBean();
            playSongBean.setIs_local(false);
            playSongBean.setHash(hash);
            playSongBean.setAlbum_id(album_id);
            boolean isExist = false;//是否存在列表
            for (int i = 0; i < playSongBeanList.size(); i++){
                if(hash.equals(playSongBeanList.get(i).getHash())){
                    mPlayMusicPosition = i;
                    LogUtil.e("mPlaySongBeanList.size():"+mPlaySongBeanList.size() + "-mPlayMusicPosition:"+mPlayMusicPosition);
                    isExist = true;
                    if(StringUtil.isEmpty(playSongBeanList.get(i).getPlay_url())){
                        //网络音乐有mp3路径
                        mIsPrepared = false;
                        if(mPlayer != null){
                            mPlayer.reset();
                            mPlayer.release();
                            mPlayer = null;
                        }
                        mPlayer = new MediaPlayer();
                        mPlayer.setOnPreparedListener(this);
                        mPlayer.setOnCompletionListener(this);
                        mPlayer.setOnErrorListener(this);
                        try {
                            mPlayer.setDataSource(playSongBeanList.get(i).getPlay_url());
                            mPlayer.prepare();
                            mPlayer.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                            ToastUtil.showToast("播放错误");
                            Intent intent = new Intent(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_ERROR);
                            sendBroadcast(intent);

                        }
                    }else{
                        //网络音乐没有mp3路径，需要接口获取再播放
                        getSongDetail(this,playSongBeanList.get(i).getHash(),playSongBeanList.get(i).getAlbum_id());
                    }
                    break;
                }
            }
            if(!isExist){
                playSongBeanList.add(playSongBean);
                setPlaySongBeanList(playSongBeanList);
                for (int i = 0; i < playSongBeanList.size(); i++) {
                    if (hash.equals(playSongBeanList.get(i).getHash())) {
                        mPlayMusicPosition = i;
                    }
                }
                LogUtil.e("mPlaySongBeanList.size():"+mPlaySongBeanList.size() + "-mPlayMusicPosition:"+mPlayMusicPosition);
                //网络音乐没有mp3路径，需要接口获取再播放
                getSongDetail(this,hash,album_id);
            }
        }

        @Override
        public void playLocalMusic(String hash, String playUrl) {
            List<PlaySongBean> playSongBeanList = getPlaySongBeanList();
            PlaySongBean playSongBean = new PlaySongBean();
            playSongBean.setIs_local(true);
            playSongBean.setHash(hash);
            playSongBean.setPlay_url(playUrl);
            PlaySongBean playSongByHash = DBManager.newInstance().playSongDao().getPlaySongByHash(hash);
            if(playSongByHash != null){
                playSongBean.setSong_name(playSongByHash.getSong_name());
                playSongBean.setAuthor_name(playSongByHash.getAuthor_name());
                playSongBean.setImg(playSongByHash.getImg());
            }
            boolean isExist = false;//是否存在列表
            for (int i = 0; i < playSongBeanList.size(); i++){
                if(hash.equals(playSongBeanList.get(i).getHash())){
                    mPlayMusicPosition = i;
                    LogUtil.e("mPlaySongBeanList.size():"+mPlaySongBeanList.size() + "-mPlayMusicPosition:"+mPlayMusicPosition);
                    isExist = true;
                    //本地音乐有mp3路径
                    mIsPrepared = false;
                    if(mPlayer != null){
                        mPlayer.reset();
                        mPlayer.release();
                        mPlayer = null;
                    }
                    mPlayer = new MediaPlayer();
                    mPlayer.setOnPreparedListener(this);
                    mPlayer.setOnCompletionListener(this);
                    mPlayer.setOnErrorListener(this);
                    try {
                        if (playSongBeanList.get(i).getPlay_url().startsWith("content://")) {
                            mPlayer.setDataSource(App.getApplication(), Uri.parse(playSongBeanList.get(i).getPlay_url()));
                        } else {
                            mPlayer.setDataSource(playSongBeanList.get(i).getPlay_url());
                        }
                        mPlayer.prepare();
                        mPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                        ToastUtil.showToast("播放错误");
                        Intent intent = new Intent(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_ERROR);
                        sendBroadcast(intent);
                    }
                    break;
                }
            }
            if(!isExist){
                playSongBeanList.add(playSongBean);
                setPlaySongBeanList(playSongBeanList);
                for (int i = 0; i < playSongBeanList.size(); i++) {
                    if (hash.equals(playSongBeanList.get(i).getHash())) {
                        mPlayMusicPosition = i;
                    }
                }
                LogUtil.e("mPlaySongBeanList.size():"+mPlaySongBeanList.size() + "-mPlayMusicPosition:"+mPlayMusicPosition);
                //本地音乐有mp3路径
                mIsPrepared = false;
                if(mPlayer != null){
                    mPlayer.reset();
                    mPlayer.release();
                    mPlayer = null;
                }
                mPlayer = new MediaPlayer();
                mPlayer.setOnPreparedListener(this);
                mPlayer.setOnCompletionListener(this);
                mPlayer.setOnErrorListener(this);
                try {
                    if (playSongBeanList.get(mPlayMusicPosition).getPlay_url().startsWith("content://")) {
                        mPlayer.setDataSource(App.getApplication(), Uri.parse(playSongBeanList.get(mPlayMusicPosition).getPlay_url()));
                    } else {
                        mPlayer.setDataSource(playSongBeanList.get(mPlayMusicPosition).getPlay_url());
                    }
                    mPlayer.prepare();
                    mPlayer.start();
                } catch (IOException e) {
                    LogUtil.e("e:"+e.toString());
                    e.printStackTrace();
                    ToastUtil.showToast("播放错误");
                    Intent intent = new Intent(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_ERROR);
                    sendBroadcast(intent);
                }
            }
        }

        @Override
        public void playMusic(int position) {
            if(mPlaySongBeanList.size() == 0){
                ToastUtil.showToast("播放列表为空");
                return;
            }
            if(position < 0 || position > (mPlaySongBeanList.size() - 1)){
                stop();
                return;
            }
            mPlayMusicPosition = position;
            LogUtil.e("mPlaySongBeanList.size():"+mPlaySongBeanList.size() + "-mPlayMusicPosition:"+mPlayMusicPosition);
            PlaySongBean playSongBean = mPlaySongBeanList.get(position);
            if(!playSongBean.isIs_local() && StringUtil.isEmpty(playSongBean.getPlay_url())){
                //网络音乐且还没有mp3路径，需要接口获取再播放
                getSongDetail(this,playSongBean.getHash(),playSongBean.getAlbum_id());
            }else{
                //本地音乐有mp3路径，直接播放
                mIsPrepared = false;
                if(mPlayer != null){
                    mPlayer.reset();
                    mPlayer.release();
                    mPlayer = null;
                }
                mPlayer = new MediaPlayer();
                mPlayer.setOnPreparedListener(this);
                mPlayer.setOnCompletionListener(this);
                mPlayer.setOnErrorListener(this);
                try {
                    mPlayer.setDataSource(playSongBean.getPlay_url());
                    mPlayer.prepare();
                    mPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                    ToastUtil.showToast("播放错误");
                    Intent intent = new Intent(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_ERROR);
                    sendBroadcast(intent);
                }
            }
        }

        @Override
        public int getCurrentMusicPosition() {
            return mPlayMusicPosition;
        }

        @Override
        public void deleteMusic(int position) {
            if(mPlaySongBeanList.size() == 0){
                return;
            }
            if(position < 0 || position > (mPlaySongBeanList.size() - 1)){
                return;
            }
            if(position == mPlayMusicPosition){
                Intent intent = new Intent(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_NULL);
                sendBroadcast(intent);
                mPlaySongBeanList.remove(position);
                mPlayMusicPosition --;
                if(mPlayer != null){
                    mPlayer.reset();
                    mPlayer.release();
                    mPlayer = null;
                    mIsPrepared = false;
                }
                stop();
                playNext();
            }

        }

        @Override
        public void deleteMusicAll() {
            Intent intent = new Intent(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_NULL);
            sendBroadcast(intent);
            mPlaySongBeanList.clear();
            if(mPlayer != null){
                mPlayer.reset();
                mPlayer.release();
                mPlayer = null;
                mIsPrepared = false;
            }
            stop();
        }

        @Override
        public void start() {
            if(mPlayer != null && !mPlayer.isPlaying()){
                Intent intent = new Intent(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_START);
                sendBroadcast(intent);
                mPlayer.start();
            }
        }

        @Override
        public void pause(){
            if(mPlayer != null && mPlayer.isPlaying()){
                Intent intent = new Intent(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_PAUSE);
                sendBroadcast(intent);
                mPlayer.pause();
            }
        }

        @Override
        public void stop(){
            if(mPlayer != null){
                try {
                    mPlayer.seekTo(0);
                    mPlayer.stop();
                    mPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void playPrevious() {
            if(mPlaySongBeanList.size() == 0){
                return;
            }
            if((mPlayMusicPosition - 1) >= 0){
                playMusic(mPlayMusicPosition - 1);
            }else{
                ToastUtil.showToast("没有上一首了");
                stop();
            }
        }

        @Override
        public void playNext() {
            if(mPlaySongBeanList.size() == 0){
                return;
            }
            if((mPlayMusicPosition + 1) <= (mPlaySongBeanList.size() - 1)){
                playMusic(mPlayMusicPosition + 1);
            }else{
                ToastUtil.showToast("没有下一首了");
                stop();
            }
        }

        @Override
        public boolean getPlayState() {
            if(mPlayer != null){
                return mPlayer.isPlaying();
            }
            return false;
        }

        @Override
        public int getDuration() {
            if(mPlayer != null && mIsPrepared){
                return mPlayer.getDuration();
            }else{
                return 0;
            }
        }

        @Override
        public int getProgress() {
            if(mPlayer != null && mIsPrepared){
                return mPlayer.getCurrentPosition();
            }else{
                return 0;
            }
        }

        @Override
        public void setProgress(int progress) {
            if(mPlayer != null){
                mPlayer.seekTo(progress);
            }
        }

        @Override
        public void changePlayModel() {
            if(getPlayModel() == PLAY_MODEL_REPEAT){
                playModel = PLAY_MODEL_SINGLE;
            }else if(getPlayModel() == PLAY_MODEL_SINGLE){
                playModel = PLAY_MODEL_RANDOM;
            }else{
                playModel = PLAY_MODEL_REPEAT;
            }
        }

        @Override
        public int getPlayModel() {
            return playModel;
        }

        @Override
        public boolean isPrepare() {
            return mIsPrepared;
        }

        @Override
        public PlaySongBean getCurrentSong() {
            if(mPlaySongBeanList != null && mPlayMusicPosition >= 0 && mPlayMusicPosition < mPlaySongBeanList.size()){
                return mPlaySongBeanList.get(mPlayMusicPosition);
            }
            return null;
        }

        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            mIsPrepared = true;
            updateMusicNotification();//每次歌曲准备完毕更新音乐通知栏的信息
            Intent intent = new Intent(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_SONG);
            sendBroadcast(intent);
            //添加到播放历史
            PlaySongBean currentSong = getCurrentSong();
            if(null != currentSong){
                PlaySongBean playSongByHash = DBManager.newInstance().playSongDao().getPlaySongByHash(currentSong.getHash());
                if(playSongByHash != null){
                    playSongByHash.setUpdate_time(new Date().getTime());
                    DBManager.newInstance().playSongDao().update(playSongByHash);
                }else{
                    currentSong.setUpdate_time(new Date().getTime());
                    DBManager.newInstance().playSongDao().insert(currentSong);
                }
            }
        }



        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            Intent intent = new Intent(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_NULL);
            sendBroadcast(intent);
            if(mPlaySongBeanList.size() == 0){
                return;
            }
            switch (getPlayModel()){
                case PLAY_MODEL_REPEAT:
                    if((mPlayMusicPosition + 1) <= (mPlaySongBeanList.size() - 1)){
                        mPlayMusicPosition ++;
                    }else{
                        mPlayMusicPosition = 0;
                        LogUtil.e("PLAY_MODE:"+getPlayModel() + "-mPlaySongBeanList.size():"+mPlaySongBeanList.size() + "-mPlayMusicPosition"+mPlayMusicPosition);
                    }
                    break;
                case PLAY_MODEL_SINGLE:
                    LogUtil.e("PLAY_MODE:"+getPlayModel() + "-PlaySongBeanList.size():"+mPlaySongBeanList.size() + "-mPlayMusicPosition"+mPlayMusicPosition);
                    break;
                case PLAY_MODEL_RANDOM:
                    mPlayMusicPosition = new Random().nextInt(mPlaySongBeanList.size());
                    LogUtil.e("PLAY_MODE:"+getPlayModel() + "-PlaySongBeanList.size():"+mPlaySongBeanList.size() + "-mPlayMusicPosition"+mPlayMusicPosition);
                    break;
            }
            playMusic(mPlayMusicPosition);
        }

        @Override
        public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
            ToastUtil.showToast("播放错误");
            Intent intent = new Intent(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_NULL);
            sendBroadcast(intent);
            playNext();//自动播放下一首
            return true;
        }

        @Override
        public void updateMusicNotification(){
            PlaySongBean currentSong = getCurrentSong();
            if(currentSong == null){
                return;
            }
            LogUtil.e("updateMusicNotification-------------------");
            Glide.with(MusicService.this)
                    .asBitmap()
                    .load(currentSong.getImg().replace("{size}","400"))
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            LogUtil.e("updateMusicNotification-------------------onResourceReady");
                            mMusicContentView.setTextViewText(R.id.tv_song_name,currentSong.getSong_name());
                            mMusicContentView.setTextViewText(R.id.tv_singer,currentSong.getAuthor_name());
                            mMusicContentView.setImageViewBitmap(R.id.iv_image,resource);
                            mMusicContentView.setImageViewResource(R.id.iv_music_play_stop,R.drawable.music_stop_gray_dark);
                            startForeground(10,mMusicNotification);
                        }

                        @Override
                        public void onLoadFailed(@Nullable Drawable errorDrawable) {
                            super.onLoadFailed(errorDrawable);
                            LogUtil.e("updateMusicNotification-------------------onResourceReady");
                            mMusicContentView.setTextViewText(R.id.tv_song_name,currentSong.getSong_name());
                            mMusicContentView.setTextViewText(R.id.tv_singer,currentSong.getAuthor_name());
                            mMusicContentView.setImageViewResource(R.id.iv_image,R.drawable.logo);
                            mMusicContentView.setImageViewResource(R.id.iv_music_play_stop,R.drawable.music_stop_gray_dark);
                            startForeground(10,mMusicNotification);
                        }
                    });
        }

    }

    /**
     * 获取歌曲详情
     */
    private void getSongDetail(MusicBinder musicBinder, String hash, String album_id){
        RetrofitFactory.getInstance().createApi(RetrofitFactory.BASE_URL_KUGOU, RetrofitApi.class).getSongDetail(
                "play/getdata",
                "jQuery1910818988551264608_1610613407119",
                "4D",
                "41acec3806dc94eb4e2235be80c06ae4",
                "4",
                "1610613407120",
                hash,
                album_id)
                .compose(RxScheduler.rxSchedulerTransform())
                .subscribe(new BaseObserver<SongDetailBean>() {
                    @Override
                    protected void onStart() {
                    }

                    @Override
                    protected void onSuccess(SongDetailBean data) throws Exception {
                        LogUtil.e("getSongDetail------------" + data.getPlay_url());

                        //更新播放列表的音乐mp3地址
                        List<PlaySongBean> playSongBeanList = musicBinder.getPlaySongBeanList();
                        for (int i = 0; i < playSongBeanList.size(); i++){
                            if(data.getHash().equals(playSongBeanList.get(i).getHash())){
                                playSongBeanList.get(i).setSong_name(data.getSong_name());
                                playSongBeanList.get(i).setAuthor_name(data.getAuthor_name());
                                playSongBeanList.get(i).setLyrics(data.getLyrics());
                                playSongBeanList.get(i).setTimelength(data.getTimelength());
                                playSongBeanList.get(i).setImg(data.getImg().replace("{size}","400"));
                                playSongBeanList.get(i).setPlay_url(data.getPlay_url());
                            }
                        }
                        musicBinder.setPlaySongBeanList(playSongBeanList);

                        mIsPrepared = false;
                        if(mPlayer != null){
                            mPlayer.reset();
                            mPlayer.release();
                            mPlayer = null;
                        }
                        mPlayer = new MediaPlayer();
                        mPlayer.setOnPreparedListener(musicBinder);
                        mPlayer.setOnCompletionListener(musicBinder);
                        mPlayer.setOnErrorListener(musicBinder);
                        try {
                            mPlayer.setDataSource(data.getPlay_url());
                            mPlayer.prepare();
                            mPlayer.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                            ToastUtil.showToast("播放错误");
                            Intent intent = new Intent(MusicBroadcastReceiver.ACTION_MUSIC_PLAY_ERROR);
                            sendBroadcast(intent);
                        }
                    }

                    @Override
                    protected void onFinish() {
                    }
                });
    }

}
