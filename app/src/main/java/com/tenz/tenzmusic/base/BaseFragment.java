package com.tenz.tenzmusic.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.tenz.tenzmusic.widget.dialog.LoadingDialog;
import com.tenz.tenzmusic.widget.titlebar.TitleBar;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements TitleBar.OnTitleBarClickListener {

    protected Unbinder mUnbinder;
    protected Activity mActivity;
    protected Context mContext;

    private LoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(),container,false);
    }

    @SuppressLint("NewApi")
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbinder = ButterKnife.bind(this,view);
        mActivity = getActivity();
        mContext = getActivity();
        if(isRegisterEventBus()){
            EventBus.getDefault().register(this);
        }
        initView();
        initData();
    }

    protected abstract int getLayoutId();

    protected void initView(){

    }

    protected void initData(){

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
        Intent intent = new Intent(mActivity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void back() {

    }

    @Override
    public void more() {

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
        loadingDialog = new LoadingDialog(mContext);
        loadingDialog.show();
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

    /**
     * 是否使用EventBus
     * @return
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

}
