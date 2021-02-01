package com.tenz.tenzmusic.ui;

import android.graphics.Color;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.base.BaseActivity;
import com.tenz.tenzmusic.util.MediaPlayerUtil;
import com.tenz.tenzmusic.util.StatusBarUtil;

import butterknife.BindView;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.rl_container)
    RelativeLayout rl_container;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        super.initView();
        StatusBarUtil.setBarColor(mActivity, Color.TRANSPARENT,true);
        AlphaAnimation animation = new AlphaAnimation(0.2f, 1f);
        animation.setDuration(3000L);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(HomeActivity.class);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        rl_container.setAnimation(animation);
    }

    @Override
    protected void initData() {
        super.initData();
//        MediaPlayerUtil.with(mContext).openRawSound(R.raw.fmdt);
    }

}
