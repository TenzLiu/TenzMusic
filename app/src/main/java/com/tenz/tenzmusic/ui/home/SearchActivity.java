package com.tenz.tenzmusic.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.library.flowlayout.FlowLayoutManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.adapter.SearchHistoryHotAdapter;
import com.tenz.tenzmusic.adapter.SearchSongListAdapter;
import com.tenz.tenzmusic.api.RetrofitApi;
import com.tenz.tenzmusic.base.BaseActivity;
import com.tenz.tenzmusic.base.BaseQuickAdapter;
import com.tenz.tenzmusic.entity.SearchSongResponse;
import com.tenz.tenzmusic.http.BaseObserver;
import com.tenz.tenzmusic.http.RetrofitFactory;
import com.tenz.tenzmusic.http.RxScheduler;
import com.tenz.tenzmusic.ui.video.VideoPlayActivity;
import com.tenz.tenzmusic.util.DisplayUtil;
import com.tenz.tenzmusic.util.StatusBarUtil;
import com.tenz.tenzmusic.util.StringUtil;
import com.tenz.tenzmusic.util.ToastUtil;
import com.tenz.tenzmusic.widget.music.MusicPlayBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.ll_container)
    LinearLayout ll_container;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.iv_close)
    ImageView iv_close;
    @BindView(R.id.ll_search_before)
    LinearLayout ll_search_before;
    @BindView(R.id.ll_search_result)
    LinearLayout ll_search_result;
    @BindView(R.id.rv_search_history)
    RecyclerView rv_search_history;
    @BindView(R.id.rv_search_hot)
    RecyclerView rv_search_hot;
    @BindView(R.id.srl_search_result)
    SmartRefreshLayout srl_search_result;
    @BindView(R.id.rv_search_result)
    RecyclerView rv_search_result;

    @BindView(R.id.music_play_bar)
    MusicPlayBar music_play_bar;

    private SearchHistoryHotAdapter searchHistoryAdapter;
    private List<String> searchHistoryList;

    private SearchHistoryHotAdapter searchHotAdapter;
    private List<String> searchHotList;

    private int mPage = 1;
    public static final int PAGE_SIZE = 20;
    private String keyWord;

    private SearchSongListAdapter searchSongListAdapter;
    private List<SearchSongResponse.InfoBean> searchSongBeanList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        super.initView();
        StatusBarUtil.setBarColor(mActivity, Color.TRANSPARENT,true);
        ll_container.setPadding(DisplayUtil.dp2px(10),StatusBarUtil.getStatusBarHeight(mContext)+DisplayUtil.dp2px(10),
                DisplayUtil.dp2px(10),DisplayUtil.dp2px(10));

        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String content = et_search.getText().toString().trim();
                    if(StringUtil.isEmpty(content)){
                        ToastUtil.showToast("输入不能为空");
                    }else{
                        hideKeyBord();
                        ll_search_before.setVisibility(View.GONE);
                        ll_search_result.setVisibility(View.VISIBLE);
                        iv_close.setVisibility(View.VISIBLE);
                        keyWord = content;
                        srl_search_result.autoRefresh();
                    }
                    return true;
                }
                return false;
            }
        });

        initRefreshLayout();
        initRecycleView();
    }

    private void initRefreshLayout() {
        srl_search_result.setEnableLoadMore(true);
        srl_search_result.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPage ++;
                searchSong();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPage = 1;
                searchSong();
            }
        });
    }

    private void initRecycleView() {
        rv_search_result.setLayoutManager(new LinearLayoutManager(mContext));
        searchSongBeanList = new ArrayList<>();
        searchSongListAdapter = new SearchSongListAdapter(mContext, R.layout.item_song_list, searchSongBeanList, new SearchSongListAdapter.Option() {
            @Override
            public void playVideo(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("mv_hash",searchSongBeanList.get(position).getMvhash());
                startActivity(VideoPlayActivity.class,bundle);
            }

            @Override
            public void dotClick(int position) {

            }
        });
        searchSongListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("hash",searchSongBeanList.get(position).getHash());
                bundle.putString("album_id",searchSongBeanList.get(position).getAlbum_id());
                startActivity(MusicPlayActivity.class, bundle);
                mActivity.overridePendingTransition(R.anim.anim_up,R.anim.anim_no_anim);
            }
        });
        rv_search_result.setAdapter(searchSongListAdapter);
    }

    @Override
    protected void initData() {
        super.initData();

        initMusicPlayBar(music_play_bar,ll_container);

        searchHistoryList = new ArrayList<>();
        searchHistoryList.add("张国荣");
        searchHistoryList.add("陈奕迅");
        searchHistoryList.add("张敬轩");
        searchHistoryList.add("王菀之");
        rv_search_history.setLayoutManager(new FlowLayoutManager());
        searchHistoryAdapter = new SearchHistoryHotAdapter(this,R.layout.item_search_history_hot,searchHistoryList);
        searchHistoryAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                et_search.setText(searchHistoryList.get(position));
                keyWord = searchHistoryList.get(position);
                ll_search_before.setVisibility(View.GONE);
                ll_search_result.setVisibility(View.VISIBLE);
                iv_close.setVisibility(View.VISIBLE);
                srl_search_result.autoRefresh();
            }
        });
        rv_search_history.setAdapter(searchHistoryAdapter);

        searchHotList = new ArrayList<>();
        searchHotList.add("张国荣");
        searchHotList.add("陈奕迅");
        searchHotList.add("张敬轩");
        searchHotList.add("王菀之");
        rv_search_hot.setLayoutManager(new FlowLayoutManager());
        searchHotAdapter = new SearchHistoryHotAdapter(this,R.layout.item_search_history_hot,searchHotList);
        searchHotAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                et_search.setText(searchHotList.get(position));
                keyWord = searchHotList.get(position);
                ll_search_before.setVisibility(View.GONE);
                ll_search_result.setVisibility(View.VISIBLE);
                iv_close.setVisibility(View.VISIBLE);
                srl_search_result.autoRefresh();
            }
        });
        rv_search_hot.setAdapter(searchHotAdapter);
    }

    @Override
    protected boolean isHaveMusicPlayFoot() {
        return true;
    }

    @OnClick({R.id.iv_back,R.id.iv_close,R.id.iv_delete_history})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_close:
                ll_search_before.setVisibility(View.VISIBLE);
                ll_search_result.setVisibility(View.GONE);
                iv_close.setVisibility(View.GONE);
                et_search.setText("");
                keyWord = "";
                searchSongBeanList.clear();
                searchSongListAdapter.notifyDataSetChanged();
                break;
            case R.id.iv_delete_history:

                break;
        }
    }

    /**
     * 搜索歌曲
     */
    private void searchSong(){
        RetrofitFactory.getInstance().createApi(RetrofitFactory.BASE_URL_KUGOU_SEARCH,RetrofitApi.class)
                .searchSong(14,"em",1,"全部",0,5,1,1,
                        9108,1,1,1,keyWord,mPage,PAGE_SIZE)
                .compose(RxScheduler.rxSchedulerTransform())
                .subscribe(new BaseObserver<SearchSongResponse>() {
                    @Override
                    protected void onStart() {

                    }

                    @Override
                    protected void onSuccess(SearchSongResponse data) throws Exception {
                        if(mPage == 1){
                            searchSongBeanList.clear();
                        }
                        searchSongBeanList.addAll(data.getInfo());
                        searchSongListAdapter.notifyDataSetChanged();
                        if(searchSongBeanList.size() < data.getTotal()){
                            srl_search_result.setEnableLoadMore(true);
                        }else{
                            srl_search_result.setEnableLoadMore(false);
                        }
                    }

                    @Override
                    protected void onFinish() {
                        if(mPage == 1){
                            srl_search_result.finishRefresh();
                        }else{
                            srl_search_result.finishLoadMore();
                        }
                    }
                });
    }

}
