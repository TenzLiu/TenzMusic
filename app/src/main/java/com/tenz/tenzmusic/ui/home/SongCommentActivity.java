package com.tenz.tenzmusic.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.adapter.SongCommentListAdapter;
import com.tenz.tenzmusic.api.RetrofitApi;
import com.tenz.tenzmusic.base.BaseActivity;
import com.tenz.tenzmusic.base.BaseQuickAdapter;
import com.tenz.tenzmusic.entity.SongCommentResponse;
import com.tenz.tenzmusic.http.RetrofitFactory;
import com.tenz.tenzmusic.http.RxScheduler;
import com.tenz.tenzmusic.util.LogUtil;
import com.tenz.tenzmusic.util.ToastUtil;
import com.tenz.tenzmusic.widget.titlebar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SongCommentActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBar title_bar;
    @BindView(R.id.srl_song_comment)
    SmartRefreshLayout srl_song_comment;
    @BindView(R.id.rv_song_comment)
    RecyclerView rv_song_comment;

    private int mPage = 1;
    public static final int PAGE_SIZE = 20;

    private String hash;

    private SongCommentListAdapter songCommentListAdapter;
    private List<SongCommentResponse.ListBean> songCommentBeanList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_song_comment;
    }

    @Override
    protected void initView() {
        super.initView();
        initTitleBar(title_bar,"评论");

        initRefreshLayout();
        initRecycleView();
    }

    @Override
    protected void initData() {
        super.initData();

        Bundle bundle = getIntent().getExtras();
        if(null != bundle){
            hash = bundle.getString("hash");
        }
        srl_song_comment.autoRefresh();
    }

    private void initRefreshLayout() {
        srl_song_comment.setEnableLoadMore(true);
        srl_song_comment.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPage ++;
                getSongComment(hash);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPage = 1;
                getSongComment(hash);
            }
        });
    }

    private void initRecycleView() {
        rv_song_comment.setLayoutManager(new LinearLayoutManager(mContext));
        songCommentBeanList = new ArrayList<>();
        songCommentListAdapter = new SongCommentListAdapter(mContext, R.layout.item_song_comment_list, songCommentBeanList);
        songCommentListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        rv_song_comment.setAdapter(songCommentListAdapter);
    }

    /**
     * 获取歌单歌曲列表
     */
    private void getSongComment(String hash){
        RetrofitFactory.getInstance().createApi(RetrofitFactory.BASE_URL_KUGOU_COMMENT, RetrofitApi.class).getSongComment(
                "commentsv2/getCommentWithLike","fc4be23b4e972707f36b8a828a93ba8a",
                8983,hash,mPage,PAGE_SIZE)
                .compose(RxScheduler.rxSchedulerTransform())
                .subscribe(new Observer<SongCommentResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SongCommentResponse songCommentResponse) {
                        if(mPage == 1){
                            songCommentBeanList.clear();
                        }
                        songCommentBeanList.addAll(songCommentResponse.getList());
                        songCommentListAdapter.notifyDataSetChanged();
                        if(songCommentBeanList.size() < songCommentResponse.getCount()){
                            srl_song_comment.setEnableLoadMore(true);
                        }else{
                            srl_song_comment.setEnableLoadMore(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("获取歌曲评论失败:"+e.toString());
                        ToastUtil.showToast("获取歌曲评论失败");
                        if(mPage == 1){
                            srl_song_comment.finishRefresh();
                        }else{
                            srl_song_comment.finishLoadMore();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if(mPage == 1){
                            srl_song_comment.finishRefresh();
                        }else{
                            srl_song_comment.finishLoadMore();
                        }
                    }
                });
    }

}
