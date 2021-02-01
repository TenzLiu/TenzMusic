package com.tenz.tenzmusic.ui;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentTransaction;

import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.app.AppManager;
import com.tenz.tenzmusic.base.BaseActivity;
import com.tenz.tenzmusic.ui.home.HomeFragment;
import com.tenz.tenzmusic.ui.mine.MineFragment;
import com.tenz.tenzmusic.ui.video.VideoFragment;
import com.tenz.tenzmusic.util.ResourceUtil;
import com.tenz.tenzmusic.util.ToastUtil;
import com.tenz.tenzmusic.widget.music.MusicPlayBar;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.ll_container)
    LinearLayout ll_container;
    @BindView(R.id.fl_content)
    FrameLayout fl_content;
    @BindView(R.id.iv_home)
    ImageView iv_home;
    @BindView(R.id.tv_home)
    TextView tv_home;
    @BindView(R.id.iv_video)
    ImageView iv_video;
    @BindView(R.id.tv_video)
    TextView tv_video;
    @BindView(R.id.iv_mine)
    ImageView iv_mine;
    @BindView(R.id.tv_mine)
    TextView tv_mine;

    @BindView(R.id.music_play_bar)
    MusicPlayBar music_play_bar;

    private HomeFragment homeFragment;
    private VideoFragment videoFragment;
    private MineFragment mineFragment;
    public static final int INDEX_HOME = 0;
    public static final int INDEX_VIDEO = 1;
    public static final int INDEX_MINE = 2;
    private long mExitTime;//点击退出的时间

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        super.initView();
        switchToFragment(INDEX_HOME);
    }

    @Override
    protected void initData() {
        super.initData();

        initMusicPlayBar(music_play_bar,ll_container);
    }

    @OnClick({R.id.ll_home,R.id.ll_video,R.id.ll_mine})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_home:
                switchToFragment(INDEX_HOME);
                break;
            case R.id.ll_video:
                switchToFragment(INDEX_VIDEO);
                break;
            case R.id.ll_mine:
                switchToFragment(INDEX_MINE);
                break;
        }
    }

    @Override
    protected boolean isHaveMusicPlayFoot() {
        return true;
    }

    /**
     * 切换tab
     * @param index
     */
    private void switchToFragment(int index) {
        switchTab(index);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (index){
            case INDEX_HOME:
                if(null == homeFragment){
                    homeFragment = HomeFragment.newInstance();
                    fragmentTransaction.show(homeFragment);
                    fragmentTransaction.add(R.id.fl_content, homeFragment);
                }
                if(null != videoFragment){
                    fragmentTransaction.hide(videoFragment);
                }
                if(null != mineFragment){
                    fragmentTransaction.hide(mineFragment);
                }
                fragmentTransaction.show(homeFragment);
                fragmentTransaction.commitAllowingStateLoss();
                break;
            case INDEX_VIDEO:
                if(null == videoFragment){
                    videoFragment = VideoFragment.newInstance();
                    fragmentTransaction.add(R.id.fl_content, videoFragment);
                }
                if(null != homeFragment){
                    fragmentTransaction.hide(homeFragment);
                }
                if(null != mineFragment){
                    fragmentTransaction.hide(mineFragment);
                }
                fragmentTransaction.show(videoFragment);
                fragmentTransaction.commitAllowingStateLoss();
                break;
            case INDEX_MINE:
                if(null == mineFragment){
                    mineFragment = MineFragment.newInstance();
                    fragmentTransaction.add(R.id.fl_content, mineFragment);
                }
                if(null != homeFragment){
                    fragmentTransaction.hide(homeFragment);
                }
                if(null != videoFragment){
                    fragmentTransaction.hide(videoFragment);
                }
                fragmentTransaction.show(mineFragment);
                fragmentTransaction.commitAllowingStateLoss();
                break;
        }
    }

    /**
     * 切换tab样式
     * @param index
     */
    private void switchTab(int index) {
        iv_home.setImageResource(R.drawable.music_n);
        tv_home.setTextColor(ResourceUtil.getColor(R.color.color_black));
        iv_video.setImageResource(R.drawable.video_n);
        tv_video.setTextColor(ResourceUtil.getColor(R.color.color_black));
        iv_mine.setImageResource(R.drawable.mine_n);
        tv_mine.setTextColor(ResourceUtil.getColor(R.color.color_black));
        switch (index){
            case INDEX_HOME:
                iv_home.setImageResource(R.drawable.music_s);
                tv_home.setTextColor(ResourceUtil.getColor(R.color.app_color));
                break;
            case INDEX_VIDEO:
                iv_video.setImageResource(R.drawable.video_s);
                tv_video.setTextColor(ResourceUtil.getColor(R.color.app_color));
                break;
            case INDEX_MINE:
                iv_mine.setImageResource(R.drawable.mine_s);
                tv_mine.setTextColor(ResourceUtil.getColor(R.color.app_color));
                break;
        }
    }

    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis() - mExitTime > 2000){
            ToastUtil.showToast(getString(R.string.push_again_exit));
            mExitTime = System.currentTimeMillis();
        }else{
            AppManager.getInstance().finishAllActivity();
        }
    }

}