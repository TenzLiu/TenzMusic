package com.tenz.tenzmusic.ui.video;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.adapter.VideoAdapter;
import com.tenz.tenzmusic.api.RetrofitApi;
import com.tenz.tenzmusic.base.BaseFragment;
import com.tenz.tenzmusic.base.BaseQuickAdapter;
import com.tenz.tenzmusic.entity.VideoBean;
import com.tenz.tenzmusic.entity.VideoListResponse;
import com.tenz.tenzmusic.http.BaseObserver;
import com.tenz.tenzmusic.http.RetrofitFactory;
import com.tenz.tenzmusic.http.RxScheduler;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class VideoItemFragment extends BaseFragment {

    @BindView(R.id.srl_video)
    SmartRefreshLayout srl_video;
    @BindView(R.id.rv_video)
    RecyclerView rv_video;

    private static VideoItemFragment instance;

    private int mId;
    private int mPage = 1;
    public static final int PAGE_SIZE = 20;
    private VideoAdapter videoAdapter;
    private List<VideoBean> videoBeanList;

    public static VideoItemFragment newInstance(int id) {
        instance = new VideoItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video_item;
    }

    @Override
    protected void initView() {
        super.initView();
        initRefreshLayout();
        initRecycleView();
    }

    @Override
    protected void initData() {
        super.initData();
        mId = getArguments().getInt("id");
        srl_video.autoRefresh();
    }

    private void initRefreshLayout() {
        srl_video.setEnableLoadMore(true);
        srl_video.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPage ++;
                getVideoList();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPage = 1;
                getVideoList();
            }
        });
    }

    private void initRecycleView() {
        videoBeanList = new ArrayList<>();
        videoAdapter = new VideoAdapter(mContext,R.layout.item_video,videoBeanList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                // 如果是空布局，让它占满一行
                if (videoAdapter.isEmptyPosition(position)) {
                    return gridLayoutManager.getSpanCount();
                }
                return 1;
            }
        });
        rv_video.setLayoutManager(gridLayoutManager);
        videoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("mv_hash",videoBeanList.get(position).getMvhash());
                startActivity(VideoPlayActivity.class,bundle);
            }
        });
        rv_video.setAdapter(videoAdapter);
    }

    /**
     * 获取MV列表
     */
    private void getVideoList(){
        RetrofitFactory.getInstance().createApi(RetrofitApi.class).getVideoList(9108,0,
                4,mId,mPage,PAGE_SIZE)
                .compose(RxScheduler.rxSchedulerTransform())
                .subscribe(new BaseObserver<VideoListResponse>() {
                    @Override
                    protected void onStart() {
                    }

                    @Override
                    protected void onSuccess(VideoListResponse data) throws Exception {
                        if(mPage == 1){
                            videoBeanList.clear();
                        }
                        videoBeanList.addAll(data.getInfo());
                        videoAdapter.notifyDataSetChanged();
                        if(videoBeanList.size() < data.getTotal()){
                            srl_video.setEnableLoadMore(true);
                        }else{
                            srl_video.setEnableLoadMore(false);
                        }
                    }

                    @Override
                    protected void onFinish() {
                        if(mPage == 1){
                            srl_video.finishRefresh();
                        }else{
                            srl_video.finishLoadMore();
                        }
                    }
                });
    }



}
