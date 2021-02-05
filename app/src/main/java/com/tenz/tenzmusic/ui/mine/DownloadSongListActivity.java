package com.tenz.tenzmusic.ui.mine;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.common.AbsEntity;
import com.arialyy.aria.core.download.DownloadEntity;
import com.arialyy.aria.core.inf.IEntity;
import com.arialyy.aria.core.task.DownloadTask;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.adapter.DownloadSongListAdapter;
import com.tenz.tenzmusic.base.BaseActivity;
import com.tenz.tenzmusic.base.BaseQuickAdapter;
import com.tenz.tenzmusic.db.DBManager;
import com.tenz.tenzmusic.entity.PlaySongBean;
import com.tenz.tenzmusic.ui.home.MusicPlayActivity;
import com.tenz.tenzmusic.util.DisplayUtil;
import com.tenz.tenzmusic.util.GsonUtil;
import com.tenz.tenzmusic.util.LogUtil;
import com.tenz.tenzmusic.widget.dialog.ConfirmDialog;
import com.tenz.tenzmusic.widget.music.MusicPlayBar;
import com.tenz.tenzmusic.widget.titlebar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DownloadSongListActivity extends BaseActivity {

    @BindView(R.id.ll_container)
    LinearLayout ll_container;
    @BindView(R.id.title_bar)
    TitleBar title_bar;
    @BindView(R.id.srl_download_song_list)
    SmartRefreshLayout srl_download_song_list;
    @BindView(R.id.rv_download_song_list)
    RecyclerView rv_download_song_list;

    @BindView(R.id.music_play_bar)
    MusicPlayBar music_play_bar;

    private DownloadSongListAdapter downloadSongListAdapter;
    private List<AbsEntity> downloadBeanList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_download_song_list;
    }

    @Override
    protected void initView() {
        super.initView();
        initTitleBar(title_bar,"我的下载");

        initRefreshLayout();
        initRecycleView();

        Aria.download(this).register();
    }

    @Override
    protected void initData() {
        super.initData();
        initMusicPlayBar(music_play_bar,ll_container);

        srl_download_song_list.autoRefresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Aria.download(this).unRegister();
    }

    private void initRefreshLayout() {
        srl_download_song_list.setEnableLoadMore(false);
        srl_download_song_list.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getListData();
            }
        });
    }

    @Override
    protected boolean isHaveMusicPlayFoot() {
        return true;
    }

    private void initRecycleView() {
        rv_download_song_list.setLayoutManager(new LinearLayoutManager(mContext));
        downloadBeanList = new ArrayList<>();
        downloadSongListAdapter = new DownloadSongListAdapter(mContext, R.layout.item_down_song_list, downloadBeanList);
        downloadSongListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                DownloadEntity downloadEntity = (DownloadEntity) (downloadSongListAdapter.mData.get(position));
                if(downloadEntity.getState() == IEntity.STATE_COMPLETE){
                    Bundle bundle = new Bundle();
                    bundle.putString("hash",String.valueOf(downloadEntity.getCompleteTime()));
                    bundle.putString("play_url",downloadEntity.getFilePath());
                    startActivity(MusicPlayActivity.class, bundle);
                    mActivity.overridePendingTransition(R.anim.anim_up,R.anim.anim_no_anim);
                }
            }
        });
        downloadSongListAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position) {
                ConfirmDialog.newInstance("提示","确定删除该歌曲？").setCancelConfirmOption(new ConfirmDialog.CancelConfirmOption() {
                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm() {
                        downloadSongListAdapter.cancel(downloadSongListAdapter.mData.get(position));
                    }
                }).setWidth(DisplayUtil.px2dp((int) (DisplayUtil.getWindowWidth() * 0.65)))
                        .show(getSupportFragmentManager());
            }
        });
        rv_download_song_list.setAdapter(downloadSongListAdapter);
    }

    /**
     * 获取歌单歌曲列表
     */
    private void getListData(){
        LogUtil.e("downloadBeanList---:" + downloadBeanList.size());
        List<AbsEntity> totalTaskList = Aria.download(this).getTotalTaskList();
        if(totalTaskList.size() > 0){
            downloadBeanList.clear();
            downloadBeanList.addAll(totalTaskList);
        }
        LogUtil.e("downloadBeanList---:" + downloadBeanList.size());
        srl_download_song_list.finishRefresh();
        downloadSongListAdapter.updatePositions(downloadBeanList);
        downloadSongListAdapter.notifyDataSetChanged();
    }

    @Download.onPre void onPre(DownloadTask task) {
        LogUtil.e("onPre");
        downloadSongListAdapter.updateState(task.getEntity());
        LogUtil.e(task.getTaskName() + ", " + task.getState());
    }

    @Download.onWait void onWait(DownloadTask task) {
        LogUtil.e("onWait");
        downloadSongListAdapter.updateState(task.getEntity());
    }

    @Download.onTaskStart void taskStart(DownloadTask task) {
        LogUtil.e("taskStart---" + task.getTaskName() + ", " + task.getState());
        downloadSongListAdapter.updateState(task.getEntity());
    }

    @Download.onTaskResume void taskResume(DownloadTask task) {
        LogUtil.e("taskResume---" + task.getTaskName() + ", " + task.getState());
        downloadSongListAdapter.updateState(task.getEntity());
    }

    @Download.onTaskStop void taskStop(DownloadTask task) {
        LogUtil.e("taskStop");
        downloadSongListAdapter.updateState(task.getEntity());
    }

    @Download.onTaskCancel void taskCancel(DownloadTask task) {
        LogUtil.e("taskCancel");
        downloadSongListAdapter.updateState(task.getEntity());
        PlaySongBean playSongByHash = DBManager.newInstance().playSongDao().getPlaySongByHash(task.getEntity().getStr());
        if(playSongByHash != null){
            DBManager.newInstance().playSongDao().deleteByHash(task.getEntity().getStr());
        }
        List<DownloadEntity> tasks = Aria.download(this).getAllNotCompleteTask();
        if (tasks != null){
            LogUtil.e("未完成的任务数：" + tasks.size());
        }
    }

    @Download.onTaskFail void taskFail(DownloadTask task) {
        LogUtil.e("taskFail");
        if (task == null || task.getEntity() == null){
            return;
        }
        downloadSongListAdapter.updateState(task.getEntity());
    }

    @Download.onTaskComplete void taskComplete(DownloadTask task) {
        LogUtil.e("taskComplete");
        downloadSongListAdapter.updateState(task.getEntity());
    }

    @Download.onTaskRunning void taskRunning(DownloadTask task) {
        LogUtil.e("taskRunning");
        downloadSongListAdapter.setProgress(task.getEntity());
    }

}
