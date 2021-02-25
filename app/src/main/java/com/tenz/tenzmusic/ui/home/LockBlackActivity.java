package com.tenz.tenzmusic.ui.home;

import android.graphics.Color;
import android.view.WindowManager;

import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.base.BaseActivity;
import com.tenz.tenzmusic.util.StatusBarUtil;

public class LockBlackActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_lock_black;
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


}
