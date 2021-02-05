package com.tenz.tenzmusic.ui.mine;

import android.app.DownloadManager;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.common.AbsEntity;
import com.pgyer.pgyersdk.PgyerSDKManager;
import com.pgyer.pgyersdk.callback.CheckoutVersionCallBack;
import com.pgyer.pgyersdk.model.CheckSoftModel;
import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.base.BaseFragment;
import com.tenz.tenzmusic.base.WebActivity;
import com.tenz.tenzmusic.db.DBManager;
import com.tenz.tenzmusic.entity.PlaySongBean;
import com.tenz.tenzmusic.helper.DownloadManagerUtil;
import com.tenz.tenzmusic.receiver.DownloadReceiver;
import com.tenz.tenzmusic.util.AppUtil;
import com.tenz.tenzmusic.util.DateUtil;
import com.tenz.tenzmusic.util.DisplayUtil;
import com.tenz.tenzmusic.util.GsonUtil;
import com.tenz.tenzmusic.util.LogUtil;
import com.tenz.tenzmusic.util.ToastUtil;
import com.tenz.tenzmusic.widget.dialog.ConfirmDialog;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MineFragment extends BaseFragment {

    @BindView(R.id.tv_like_count)
    TextView tv_like_count;
    @BindView(R.id.tv_local_count)
    TextView tv_local_count;
    @BindView(R.id.tv_download_count)
    TextView tv_download_count;
    @BindView(R.id.tv_recently_count)
    TextView tv_recently_count;

    private DownloadReceiver mDownloadReceiver;

    private static MineFragment instance;

    public static MineFragment newInstance() {
        if(null == instance){
            instance = new MineFragment();
        }
        return instance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onResume() {
        super.onResume();
        List<PlaySongBean> playSongByLikeList = DBManager.newInstance().playSongDao().getPlaySongByLike(true);
        List<PlaySongBean> playSongByLocalList = DBManager.newInstance().playSongDao().getPlaySongByLocal(true);
        List<AbsEntity> totalTaskList = Aria.download(this).getTotalTaskList();
        List<PlaySongBean> playSongByRecentlyList = DBManager.newInstance().playSongDao().getPlaySongByTime(DateUtil.getStatus7Days(new Date()).getTime());

        tv_like_count.setText("" + playSongByLikeList.size());
        tv_local_count.setText("" + playSongByLocalList.size());
        tv_download_count.setText("" + totalTaskList.size());
        tv_recently_count.setText("" + playSongByRecentlyList.size());
    }

    @Override
    protected void initData() {
        super.initData();
        mDownloadReceiver = new DownloadReceiver();
        IntentFilter downloadIntentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        mContext.registerReceiver(mDownloadReceiver, downloadIntentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.unregisterReceiver(mDownloadReceiver);
    }

    @OnClick({R.id.iv_setting,R.id.ll_support,R.id.ll_feedback,R.id.ll_code,R.id.ll_about,R.id.ll_check_upgrade,
        R.id.ll_like,R.id.ll_local,R.id.ll_download,R.id.ll_recently})
    public void onClick(View view){
        Bundle bundle;
        switch (view.getId()){
            case R.id.iv_setting:

                break;
            case R.id.ll_support:
                startActivity(SupportActivity.class);
                break;
            case R.id.ll_feedback:
                bundle = new Bundle();
                bundle.putString(WebActivity.EXTRA_TITLE,"反馈");
                bundle.putString(WebActivity.EXTRA_URL,"https://github.com/TenzLiu/TenzMusic/issues");
                startActivity(WebActivity.class,bundle);
                break;
            case R.id.ll_code:
                bundle = new Bundle();
                bundle.putString(WebActivity.EXTRA_TITLE,"代码");
                bundle.putString(WebActivity.EXTRA_URL,"https://github.com/TenzLiu/TenzMusic");
                startActivity(WebActivity.class,bundle);
                break;
            case R.id.ll_about:
                bundle = new Bundle();
                bundle.putString(WebActivity.EXTRA_TITLE,"关于");
                bundle.putString(WebActivity.EXTRA_URL,"https://github.com/TenzLiu");
                startActivity(WebActivity.class,bundle);
                break;
            case R.id.ll_check_upgrade:
                checkUpgrade();
                break;
            case R.id.ll_like:
                startActivity(LikeSongListActivity.class);
                break;
            case R.id.ll_local:
                startActivity(LocalSongListActivity.class);
                break;
            case R.id.ll_download:
                startActivity(DownloadSongListActivity.class);
                break;
            case R.id.ll_recently:
                startActivity(RecentlySongListActivity.class);
                break;
        }
    }

    /**
     * 检查蒲公英版本更新
     */
    private void checkUpgrade() {
        PgyerSDKManager.checkSoftwareUpdate(mActivity, new CheckoutVersionCallBack() {
            @Override
            public void onSuccess(CheckSoftModel checkSoftModel) {
                LogUtil.e("checkSoftModel:" + GsonUtil.beanToJson(checkSoftModel));
                int versionCode = AppUtil.getVersionCode(mContext);
                if(versionCode < checkSoftModel.getBuildBuildVersion()){
                    //有新版本
                    ConfirmDialog.newInstance("提示","发现新版本，是否下载更新？").setCancelConfirmOption(new ConfirmDialog.CancelConfirmOption() {
                        @Override
                        public void cancel() {

                        }

                        @Override
                        public void confirm() {
                            DownloadManagerUtil downloadManagerUtil = new DownloadManagerUtil(mContext);
                            downloadManagerUtil.download(checkSoftModel.getDownloadURL(), "分秒动听", "新版本下载中...");
                        }
                    }).setWidth(DisplayUtil.px2dp((int) (DisplayUtil.getWindowWidth() * 0.65)))
                            .show(getFragmentManager());
                }else{
                    ToastUtil.showToast("已是最新版本");
                }
            }

            @Override
            public void onFail(String s) {
                LogUtil.e("检查失败:" + s);
                ToastUtil.showToast("已是最新版本");
            }
        });
    }

}
