package com.tenz.tenzmusic.ui.mine;

import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.base.BaseActivity;
import com.tenz.tenzmusic.widget.snow.SnowView;
import com.tenz.tenzmusic.widget.titlebar.TitleBar;

import butterknife.BindView;

public class SupportActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBar title_bar;
    @BindView(R.id.sv_snow)
    SnowView sv_snow;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_support;
    }

    @Override
    protected void initView() {
        super.initView();
        initTitleBar(title_bar,"第三方框架");

        if(sv_snow != null){
            sv_snow.resume();
        }
    }

    @Override
    protected void onPause() {
        if(sv_snow != null){
            sv_snow.pause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if(sv_snow != null){
            sv_snow.destroy();
        }
        super.onDestroy();
    }

}
