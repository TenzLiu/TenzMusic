package com.tenz.tenzmusic.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.base.BaseFragment;
import com.tenz.tenzmusic.base.WebActivity;
import com.tenz.tenzmusic.db.DBManager;
import com.tenz.tenzmusic.entity.PlaySongBean;
import com.tenz.tenzmusic.ui.home.LikeSongListActivity;
import com.tenz.tenzmusic.util.DateUtil;
import com.tenz.tenzmusic.util.ToastUtil;

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
        List<PlaySongBean> playSongByLocalList = DBManager.newInstance().playSongDao().getPlaySongByLocalDownload(true, false);
        List<PlaySongBean> playSongByDownloadList = DBManager.newInstance().playSongDao().getPlaySongByLocalDownload(true, true);
        List<PlaySongBean> playSongByRecentlyList = DBManager.newInstance().playSongDao().getPlaySongByTime(DateUtil.getStatus7Days(new Date()).getTime());

        tv_like_count.setText("" + playSongByLikeList.size());
        tv_local_count.setText("" + playSongByLocalList.size());
        tv_download_count.setText("" + playSongByDownloadList.size());
        tv_recently_count.setText("" + playSongByRecentlyList.size());
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @OnClick({R.id.iv_setting,R.id.ll_feedback,R.id.ll_code,R.id.ll_about,
        R.id.ll_like,R.id.ll_local,R.id.ll_download,R.id.ll_recently})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_setting:

                break;
            case R.id.ll_feedback:

                break;
            case R.id.ll_code:

                break;
            case R.id.ll_about:
                Bundle bundle = new Bundle();
                bundle.putString(WebActivity.EXTRA_TITLE,"关于");
                bundle.putString(WebActivity.EXTRA_URL,"https://github.com/TenzLiu");
                startActivity(WebActivity.class,bundle);
                break;
            case R.id.ll_like:
                startActivity(LikeSongListActivity.class);
                break;
            case R.id.ll_local:
                List<PlaySongBean> playSongByLocalList = DBManager.newInstance().playSongDao().getPlaySongByLocalDownload(true, false);
                ToastUtil.showToast("playSongByLocalList:"+playSongByLocalList.size());
                break;
            case R.id.ll_download:
                List<PlaySongBean> playSongByDownloadList = DBManager.newInstance().playSongDao().getPlaySongByLocalDownload(true, true);
                ToastUtil.showToast("playSongByDownloadList:"+playSongByDownloadList.size());
                break;
            case R.id.ll_recently:
                List<PlaySongBean> playSongByRecentlyList = DBManager.newInstance().playSongDao().getPlaySongByTime(DateUtil.getStatus7Days(new Date()).getTime());
                ToastUtil.showToast("playSongByRecentlyList:"+playSongByRecentlyList.size());
                break;
        }
    }

}
