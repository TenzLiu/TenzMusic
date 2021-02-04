package com.tenz.tenzmusic.app;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;

import com.arialyy.aria.core.Aria;
import com.pgyer.pgyersdk.PgyerSDKManager;
import com.pgyer.pgyersdk.pgyerenum.FeatureEnum;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.service.MusicService;
import com.tenz.tenzmusic.util.LogUtil;
import com.tenz.tenzmusic.util.ResourceUtil;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import io.reactivex.annotations.NonNull;

public class App extends Application {

    private static App mApplication;
    protected static Context mContext;
    protected static Handler sHandler;
    protected static int sMainThreadId;

    protected MusicServiceConnection mMusicServiceConnection;
    protected MusicService.MusicBinder mMusicBinder;

    public MusicService.MusicBinder getmMusicBinder() {
        return mMusicBinder;
    }

    public class MusicServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mMusicBinder = (MusicService.MusicBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        mContext = this;
        sHandler = new Handler();
        sMainThreadId = android.os.Process.myTid();
        LogUtil.init(true);
        BGASwipeBackHelper.init(this, null);
        initRefreshLayout();
        initDownload();

        startMusicService();
        initPGY();
    }

    /**
     * 初始化蒲公英sdk（异常日志、版本更新）
     */
    private void initPGY() {
        new PgyerSDKManager.InitSdk()
                .setContext(this) //设置上下问对象
                .enable(FeatureEnum.CHECK_UPDATE)  //添加检查新版本
                .build();
    }

    /**
     * 初始化下载
     */
    private void initDownload() {
        Aria.init(this);
        Aria.get(this).getDownloadConfig().setMaxTaskNum(3);
        Aria.get(this).getDownloadConfig().setMaxSpeed(300);
    }

    /**
     * 开启音乐服务
     */
    private void startMusicService() {
        Intent serviceIntent = new Intent(mContext,MusicService.class);
        mMusicServiceConnection = new MusicServiceConnection();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent);
        } else {
            startService(serviceIntent);
        }
        bindService(serviceIntent,mMusicServiceConnection,BIND_AUTO_CREATE);
    }

    public static App getApplication(){
        return mApplication;
    }

    public static Context getContext(){
        return mContext;
    }

    /**
     * 初始化RefreshLayout
     */
    private void initRefreshLayout() {
        //初始化SmartRefreshLayout样式
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setPrimaryColors(ResourceUtil.getColor(R.color.color_white), ResourceUtil.getColor(R.color.color_gray));
                return new ClassicsHeader(context);
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new ClassicsFooter(context);
            }
        });
    }

    /**
     * 是否运行在UI主线程
     *
     * @return
     */
    public static boolean isRunOnUIThread() {
        int myTid = android.os.Process.myTid();
        if (myTid == sMainThreadId) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 运行在UI主线程
     *
     * @param runnable
     */
    public static void runOnUIThread(Runnable runnable) {
        if (isRunOnUIThread()) {
            // 已经是主线程, 直接运行
            runnable.run();
        } else {
            // 如果是子线程, 借助handler让其运行在主线程
            sHandler.post(runnable);
        }
    }

}
