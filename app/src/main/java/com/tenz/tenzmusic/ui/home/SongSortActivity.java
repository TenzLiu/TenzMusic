package com.tenz.tenzmusic.ui.home;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.adapter.SongSortAdapter;
import com.tenz.tenzmusic.api.RetrofitApi;
import com.tenz.tenzmusic.base.BaseActivity;
import com.tenz.tenzmusic.base.BaseQuickAdapter;
import com.tenz.tenzmusic.entity.SongSheetResponse;
import com.tenz.tenzmusic.http.BaseObserver;
import com.tenz.tenzmusic.http.RetrofitFactory;
import com.tenz.tenzmusic.http.RxScheduler;
import com.tenz.tenzmusic.widget.music.MusicPlayBar;
import com.tenz.tenzmusic.widget.titlebar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SongSortActivity extends BaseActivity {

    @BindView(R.id.ll_container)
    LinearLayout ll_container;
    @BindView(R.id.title_bar)
    TitleBar title_bar;
    @BindView(R.id.srl_song_sort)
    SmartRefreshLayout srl_song_sort;
    @BindView(R.id.rv_song_sort)
    RecyclerView rv_song_sort;

    @BindView(R.id.music_play_bar)
    MusicPlayBar music_play_bar;

    private SongSortAdapter songSortAdapter;
    private List<SongSheetResponse.InfoBean> songSortList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_song_sort;
    }

    @Override
    protected void initView() {
        super.initView();
        initTitleBar(title_bar,"歌单");
        initRefreshLayout();
        initRecycleView();
    }

    @Override
    protected void initData() {
        super.initData();
        initMusicPlayBar(music_play_bar,ll_container);

        srl_song_sort.autoRefresh();
    }

    @Override
    protected boolean isHaveMusicPlayFoot() {
        return true;
    }

    private void initRefreshLayout() {
        srl_song_sort.setEnableLoadMore(false);
        srl_song_sort.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getListData();
            }
        });
    }

    private void initRecycleView() {
        rv_song_sort.setLayoutManager(new LinearLayoutManager(mContext));
        songSortList = new ArrayList<>();
        songSortAdapter = new SongSortAdapter(mContext,R.layout.item_song_sort,songSortList);
        songSortAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("banner_url",songSortList.get(position).getBannerurl());
                bundle.putString("update_frequency",songSortList.get(position).getUpdate_frequency());
                bundle.putInt("rank_type",songSortList.get(position).getRanktype());
                bundle.putInt("rank_id",songSortList.get(position).getRankid());
                startActivity(SortSongListActivity.class, bundle);
            }
        });
        rv_song_sort.setAdapter(songSortAdapter);
    }

    /**
     * 获取歌单
     */
    private void getListData(){
        RetrofitFactory.getInstance().createApi(RetrofitApi.class).getSongSheet(9108,0,
                2,0,6,1,1,1)
                .compose(RxScheduler.rxSchedulerTransform())
                .subscribe(new BaseObserver<SongSheetResponse>() {
                    @Override
                    protected void onStart() {
                    }

                    @Override
                    protected void onSuccess(SongSheetResponse data) throws Exception {
                        songSortList.clear();
                        songSortList.addAll(data.getInfo());
                        songSortAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onFinish() {
                        srl_song_sort.finishRefresh();
                    }
                });
    }


}
