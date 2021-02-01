package com.tenz.tenzmusic.ui.home;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.adapter.HomeSongListAdapter;
import com.tenz.tenzmusic.adapter.HomeSongSortAdapter;
import com.tenz.tenzmusic.api.RetrofitApi;
import com.tenz.tenzmusic.base.BaseFragment;
import com.tenz.tenzmusic.base.BaseQuickAdapter;
import com.tenz.tenzmusic.entity.RecommendResponse;
import com.tenz.tenzmusic.entity.SongBean;
import com.tenz.tenzmusic.entity.SongListResponse;
import com.tenz.tenzmusic.entity.SongSheetResponse;
import com.tenz.tenzmusic.helper.BannerImageLoader;
import com.tenz.tenzmusic.http.BaseObserver;
import com.tenz.tenzmusic.http.RetrofitFactory;
import com.tenz.tenzmusic.http.RxScheduler;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.srl_home)
    SmartRefreshLayout srl_home;
    @BindView(R.id.banner_home)
    Banner banner_home;
    @BindView(R.id.rv_song_sort)
    RecyclerView rv_song_sort;
    @BindView(R.id.rv_song_chinese)
    RecyclerView rv_song_chinese;
    @BindView(R.id.rv_song_western)
    RecyclerView rv_song_western;
    @BindView(R.id.rv_song_jap_korean)
    RecyclerView rv_song_jap_korean;

    private static HomeFragment instance;

    //banner
    private List<String> bannerImageList;
    private List<String> bannerTitleList;
    private List<String> bannerLinkList;

    private HomeSongSortAdapter homeSongSortAdapter;
    private List<SongSheetResponse.InfoBean> homeSongSortList;

    private HomeSongListAdapter homeSongListChineseAdapter;
    private List<SongBean> songBeanChineseList;

    private HomeSongListAdapter homeSongListWesternAdapter;
    private List<SongBean> songBeanWesternList;

    private HomeSongListAdapter homeSongListJapKoreanAdapter;
    private List<SongBean> songBeanJapKoreanList;

    public static HomeFragment newInstance() {
        if(null == instance){
            instance = new HomeFragment();
        }
        return instance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        super.initView();
        initRefreshLayout();

        initRecycleView();
    }

    private void initRecycleView() {
        rv_song_sort.setLayoutManager(new GridLayoutManager(mContext,3));
        homeSongSortList = new ArrayList<>();
        homeSongSortAdapter = new HomeSongSortAdapter(mContext,R.layout.item_home_song_sort,homeSongSortList);
        homeSongSortAdapter.showEmptyView(false);
        homeSongSortAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("banner_url",homeSongSortList.get(position).getBannerurl());
                bundle.putString("update_frequency",homeSongSortList.get(position).getUpdate_frequency());
                bundle.putInt("rank_type",homeSongSortList.get(position).getRanktype());
                bundle.putInt("rank_id",homeSongSortList.get(position).getRankid());
                startActivity(SortSongListActivity.class, bundle);
            }
        });
        rv_song_sort.setAdapter(homeSongSortAdapter);

        rv_song_chinese.setLayoutManager(new LinearLayoutManager(mContext));
        songBeanChineseList = new ArrayList<>();
        homeSongListChineseAdapter = new HomeSongListAdapter(mContext,R.layout.item_home_song_list,songBeanChineseList);
        homeSongListChineseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("hash",songBeanChineseList.get(position).getHash());
                bundle.putString("album_id",songBeanChineseList.get(position).getAlbum_id());
                startActivity(MusicPlayActivity.class, bundle);
                mActivity.overridePendingTransition(R.anim.anim_up,R.anim.anim_no_anim);
            }
        });
        rv_song_chinese.setAdapter(homeSongListChineseAdapter);

        rv_song_western.setLayoutManager(new LinearLayoutManager(mContext));
        songBeanWesternList = new ArrayList<>();
        homeSongListWesternAdapter = new HomeSongListAdapter(mContext,R.layout.item_home_song_list,songBeanWesternList);
        homeSongListWesternAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("hash",songBeanWesternList.get(position).getHash());
                bundle.putString("album_id",songBeanWesternList.get(position).getAlbum_id());
                startActivity(MusicPlayActivity.class, bundle);
                mActivity.overridePendingTransition(R.anim.anim_up,R.anim.anim_no_anim);
            }
        });
        rv_song_western.setAdapter(homeSongListWesternAdapter);

        rv_song_jap_korean.setLayoutManager(new LinearLayoutManager(mContext));
        songBeanJapKoreanList = new ArrayList<>();
        homeSongListJapKoreanAdapter = new HomeSongListAdapter(mContext,R.layout.item_home_song_list,songBeanJapKoreanList);
        homeSongListJapKoreanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("hash",songBeanJapKoreanList.get(position).getHash());
                bundle.putString("album_id",songBeanJapKoreanList.get(position).getAlbum_id());
                startActivity(MusicPlayActivity.class, bundle);
                mActivity.overridePendingTransition(R.anim.anim_up,R.anim.anim_no_anim);
            }
        });
        rv_song_jap_korean.setAdapter(homeSongListJapKoreanAdapter);
    }

    private void initRefreshLayout() {
        srl_home.setEnableLoadMore(false);
        srl_home.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getBannerData();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        srl_home.autoRefresh();
    }

    @OnClick({R.id.ll_search,R.id.ll_song_sort})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_search:
                startActivity(SearchActivity.class);
                break;
            case R.id.ll_song_sort:
                startActivity(SongSortActivity.class);
                break;
        }
    }

    /**
     * 初始化banner数据
     */
    private void initBannerData(List<RecommendResponse.InfoBean> banners) {
        bannerImageList = new ArrayList<>();
        bannerTitleList = new ArrayList<>();
        bannerLinkList = new ArrayList<>();
        for (int i=0; i<banners.size(); i++){
            RecommendResponse.InfoBean banner = banners.get(i);
            bannerImageList.add(banner.getBannerurl());
            bannerTitleList.add(banner.getName());
            bannerLinkList.add(banner.getJump_url());
        }

        banner_home.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner_home.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        banner_home.setImageLoader(new BannerImageLoader());
        //设置图片集合
        banner_home.setImages(bannerImageList);
        banner_home.setBannerTitles(bannerTitleList);
        banner_home.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner_home.start();
    }

    /**
     * 获取banner数据
     */
    private void getBannerData(){
        RetrofitFactory.getInstance().createApi(RetrofitApi.class).getBanner(3,2,0)
                .compose(RxScheduler.rxSchedulerTransform())
                .subscribe(new BaseObserver<RecommendResponse>() {
                    @Override
                    protected void onStart() {
                    }

                    @Override
                    protected void onSuccess(RecommendResponse data) throws Exception {
                        initBannerData(data.getInfo());
                    }

                    @Override
                    protected void onFinish() {
                        getSongSheetData();
                    }
                });
    }

    /**
     * 获取歌单
     */
    private void getSongSheetData(){
        RetrofitFactory.getInstance().createApi(RetrofitApi.class).getSongSheet(9108,0,
                2,0,6,1,1,1)
                .compose(RxScheduler.rxSchedulerTransform())
                .subscribe(new BaseObserver<SongSheetResponse>() {
                    @Override
                    protected void onStart() {
                    }

                    @Override
                    protected void onSuccess(SongSheetResponse data) throws Exception {
                        homeSongSortList.clear();
                        for (int i = 0; i < data.getInfo().size(); i++){
                            if(i >= 6){
                                break;
                            }
                            homeSongSortList.add(data.getInfo().get(i));
                        }
                        homeSongSortAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onFinish() {
                        getNewSongChinese();
                    }
                });
    }

    /**
     * 获取华语新歌
     */
    private void getNewSongChinese(){
        RetrofitFactory.getInstance().createApi(RetrofitApi.class).newSong(9108,0,
                1,1,1,1,1,5)
                .compose(RxScheduler.rxSchedulerTransform())
                .subscribe(new BaseObserver<SongListResponse>() {
                    @Override
                    protected void onStart() {
                    }

                    @Override
                    protected void onSuccess(SongListResponse data) throws Exception {
                        songBeanChineseList.clear();
                        songBeanChineseList.addAll(data.getInfo());
                        homeSongListChineseAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onFinish() {
                        getNewSongWestern();
                    }
                });
    }

    /**
     * 获取欧美新歌
     */
    private void getNewSongWestern(){
        RetrofitFactory.getInstance().createApi(RetrofitApi.class).newSong(9108,0,
                1,1,1,2,1,5)
                .compose(RxScheduler.rxSchedulerTransform())
                .subscribe(new BaseObserver<SongListResponse>() {
                    @Override
                    protected void onStart() {
                    }

                    @Override
                    protected void onSuccess(SongListResponse data) throws Exception {
                        songBeanWesternList.clear();
                        songBeanWesternList.addAll(data.getInfo());
                        homeSongListWesternAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onFinish() {
                        getNewSongJapKorean();
                    }
                });
    }

    /**
     * 获取日韩新歌
     */
    private void getNewSongJapKorean(){
        RetrofitFactory.getInstance().createApi(RetrofitApi.class).newSong(9108,0,
                1,1,1,3,1,5)
                .compose(RxScheduler.rxSchedulerTransform())
                .subscribe(new BaseObserver<SongListResponse>() {
                    @Override
                    protected void onStart() {
                    }

                    @Override
                    protected void onSuccess(SongListResponse data) throws Exception {
                        songBeanJapKoreanList.clear();
                        songBeanJapKoreanList.addAll(data.getInfo());
                        homeSongListJapKoreanAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onFinish() {
                        srl_home.finishRefresh();
                    }
                });
    }

}
