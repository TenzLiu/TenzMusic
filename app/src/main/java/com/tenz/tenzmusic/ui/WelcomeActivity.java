package com.tenz.tenzmusic.ui;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.base.BaseActivity;
import com.tenz.tenzmusic.util.StatusBarUtil;
import com.tenz.tenzmusic.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.EasyPermissions;

public class WelcomeActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.rl_container)
    RelativeLayout rl_container;

    private int PERMISSION_STORAGE_CODE = 10001;

    String[] PERMS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        super.initView();
        StatusBarUtil.setBarColor(mActivity, Color.TRANSPARENT,false);
    }

    @Override
    protected void initData() {
        super.initData();
//        MediaPlayerUtil.with(mContext).openRawSound(R.raw.fmdt);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setData(Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 100);
        }
        requestPermission();
    }

    /**
     * 初始化动画
     */
    private void initAnim(){
        AlphaAnimation animation = new AlphaAnimation(0.2f, 1f);
        animation.setDuration(2000L);
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

    private void requestPermission(){
        if (EasyPermissions.hasPermissions(this, PERMS)) {
            // 已经申请过权限，做想做的事
            initAnim();
        } else {
            // 没有申请过权限，现在去申请
            /**
             *@param host Context对象
             *@param rationale  权限弹窗上的提示语。
             *@param requestCode 请求权限的唯一标识码
             *@param perms 一系列权限
             */
            EasyPermissions.requestPermissions(this, "权限申请失败", PERMISSION_STORAGE_CODE, PERMS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //将结果转发给EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        new Handler().postDelayed(new Runnable(){
            public void run() {
                startActivity(HomeActivity.class);
                finish();
            }
        }, 2000);
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        ToastUtil.showToast("权限申请失败");
        finish();
    }

}
