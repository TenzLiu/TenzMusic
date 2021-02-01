package com.tenz.tenzmusic.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.adapter.SongListAdapter;
import com.tenz.tenzmusic.api.RetrofitApi;
import com.tenz.tenzmusic.base.BaseActivity;
import com.tenz.tenzmusic.base.BaseQuickAdapter;
import com.tenz.tenzmusic.entity.SongBean;
import com.tenz.tenzmusic.entity.SongListResponse;
import com.tenz.tenzmusic.http.BaseObserver;
import com.tenz.tenzmusic.http.RetrofitFactory;
import com.tenz.tenzmusic.http.RxScheduler;
import com.tenz.tenzmusic.ui.video.VideoPlayActivity;
import com.tenz.tenzmusic.util.GlideUtil;
import com.tenz.tenzmusic.util.StatusBarUtil;
import com.tenz.tenzmusic.widget.music.MusicPlayBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SortSongListActivity extends BaseActivity {

    @BindView(R.id.ll_container)
    LinearLayout ll_container;
    @BindView(R.id.srl_sort_song_list)
    SmartRefreshLayout srl_sort_song_list;
    @BindView(R.id.rv_sort_song_list)
    RecyclerView rv_sort_song_list;
    @BindView(R.id.iv_banner)
    ImageView iv_banner;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.tv_update_frequency)
    TextView tv_update_frequency;

    @BindView(R.id.music_play_bar)
    MusicPlayBar music_play_bar;

    private int mPage = 1;
    public static final int PAGE_SIZE = 20;
    private SongListAdapter songListAdapter;
    private List<SongBean> songBeanList;
    private String bannerUrl;
    private String updateFrequency;
    private int rankType;
    private int rankId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sort_song_list;
    }

    @Override
    protected void initView() {
        super.initView();
        StatusBarUtil.setTransparent(mActivity,false);
        rl_back.setPadding(0,StatusBarUtil.getStatusBarHeight(mContext),0,0);

        initRefreshLayout();
        initRecycleView();
    }

    @Override
    protected void initData() {
        super.initData();
        initMusicPlayBar(music_play_bar,ll_container);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            bannerUrl = bundle.getString("banner_url");
            updateFrequency = bundle.getString("update_frequency");
            rankType = bundle.getInt("rank_type");
            rankId = bundle.getInt("rank_id");
        }
        GlideUtil.loadImage(mContext,bannerUrl.replace("{size}","400"),iv_banner);
        tv_update_frequency.setText(updateFrequency);
        srl_sort_song_list.autoRefresh();
    }

    private void initRefreshLayout() {
        srl_sort_song_list.setEnableLoadMore(true);
        srl_sort_song_list.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPage ++;
                getListData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPage = 1;
                getListData();
            }
        });
    }

    @Override
    protected boolean isHaveMusicPlayFoot() {
        return true;
    }

    private void initRecycleView() {
        rv_sort_song_list.setLayoutManager(new LinearLayoutManager(mContext));
        songBeanList = new ArrayList<>();
        songListAdapter = new SongListAdapter(mContext, R.layout.item_song_list, songBeanList, new SongListAdapter.Option() {
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
                bundle.putString("album_id",songBeanList.get(position).getAlbum_id());
                startActivity(MusicPlayActivity.class, bundle);
                mActivity.overridePendingTransition(R.anim.anim_up,R.anim.anim_no_anim);
            }
        });
        rv_sort_song_list.setAdapter(songListAdapter);
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }

    /**
     * 获取歌单歌曲列表
     */
    private void getListData(){
        RetrofitFactory.getInstance().createApi(RetrofitApi.class).getSongListBySheet(9108,0,
                1,35050,rankType,rankId,PAGE_SIZE,mPage,1)
                .compose(RxScheduler.rxSchedulerTransform())
                .subscribe(new BaseObserver<SongListResponse>() {
                    @Override
                    protected void onStart() {
                    }

                    @Override
                    protected void onSuccess(SongListResponse data) throws Exception {
                        if(mPage == 1){
                            songBeanList.clear();
                        }
                        songBeanList.addAll(data.getInfo());
                        songListAdapter.notifyDataSetChanged();
                        if(songBeanList.size() < data.getTotal()){
                            srl_sort_song_list.setEnableLoadMore(true);
                        }else{
                            srl_sort_song_list.setEnableLoadMore(false);
                        }
                    }

                    @Override
                    protected void onFinish() {
                        if(mPage == 1){
                            srl_sort_song_list.finishRefresh();
                        }else{
                            srl_sort_song_list.finishLoadMore();
                        }
                    }
                });
    }


}
