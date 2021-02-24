package com.tenz.tenzmusic.ui.mine;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.adapter.LocalSongListAdapter;
import com.tenz.tenzmusic.base.BaseActivity;
import com.tenz.tenzmusic.base.BaseQuickAdapter;
import com.tenz.tenzmusic.db.DBManager;
import com.tenz.tenzmusic.entity.PlaySongBean;
import com.tenz.tenzmusic.ui.home.MusicPlayActivity;
import com.tenz.tenzmusic.ui.video.VideoPlayActivity;
import com.tenz.tenzmusic.util.DateUtil;
import com.tenz.tenzmusic.util.DisplayUtil;
import com.tenz.tenzmusic.util.GsonUtil;
import com.tenz.tenzmusic.util.LogUtil;
import com.tenz.tenzmusic.util.ToastUtil;
import com.tenz.tenzmusic.widget.dialog.ConfirmDialog;
import com.tenz.tenzmusic.widget.music.MusicPlayBar;
import com.tenz.tenzmusic.widget.titlebar.TitleBar;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class RecentlySongListActivity extends BaseActivity {

    @BindView(R.id.ll_container)
    LinearLayout ll_container;
    @BindView(R.id.title_bar)
    TitleBar title_bar;
    @BindView(R.id.srl_recently_song_list)
    SmartRefreshLayout srl_recently_song_list;
    @BindView(R.id.rv_recently_song_list)
    RecyclerView rv_recently_song_list;

    @BindView(R.id.music_play_bar)
    MusicPlayBar music_play_bar;

    private LocalSongListAdapter songListAdapter;
    private List<PlaySongBean> songBeanList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recently_song_list;
    }

    @Override
    protected void initView() {
        super.initView();
        initTitleBar(title_bar,"我的最近");

        initRefreshLayout();
        initRecycleView();
    }

    @Override
    protected void initData() {
        super.initData();
        initMusicPlayBar(music_play_bar,ll_container);

        srl_recently_song_list.autoRefresh();
    }

    private void initRefreshLayout() {
        srl_recently_song_list.setEnableLoadMore(false);
        srl_recently_song_list.setOnRefreshListener(new OnRefreshListener() {
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
        rv_recently_song_list.setLayoutManager(new LinearLayoutManager(mContext));
        songBeanList = new ArrayList<>();
        songListAdapter = new LocalSongListAdapter(mContext, R.layout.item_song_list, songBeanList, new LocalSongListAdapter.Option() {
            @Override
            public void playVideo(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("mv_hash",songBeanList.get(position).getMvhash());
                startActivity(VideoPlayActivity.class,bundle);
            }

            @Override
            public void dotClick(int position) {

            }
        });
        songListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("hash",songBeanList.get(position).getHash());
                bundle.putString("play_url",songBeanList.get(position).getPlay_url());
                startActivity(MusicPlayActivity.class, bundle);
                mActivity.overridePendingTransition(R.anim.anim_up,R.anim.anim_no_anim);
            }
        });
        songListAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position) {
                ConfirmDialog.newInstance("提示","确定删除该歌曲？").setCancelConfirmOption(new ConfirmDialog.CancelConfirmOption() {
                    @Override
                    public void cancel() {

                    }

                    @Override
                    public void confirm() {
                        DBManager.newInstance().playSongDao().deleteByHash(songBeanList.get(position).getHash());
                        songBeanList.remove(position);
                        songListAdapter.notifyDataSetChanged();
                    }
                }).setWidth(DisplayUtil.px2dp((int) (DisplayUtil.getWindowWidth() * 0.65)))
                        .show(getSupportFragmentManager());
            }
        });
        rv_recently_song_list.setAdapter(songListAdapter);
    }

    /**
     * 获取歌单歌曲列表
     */
    private void getListData(){
        List<PlaySongBean> playSongByRecentlyList = DBManager.newInstance().playSongDao()
                .getPlaySongByTime(DateUtil.getStatus7Days(new Date()).getTime());
        if(playSongByRecentlyList.size() > 0){
            songBeanList.clear();
            songBeanList.addAll(playSongByRecentlyList);
        }
        srl_recently_song_list.finishRefresh();
        songListAdapter.notifyDataSetChanged();
    }

}
