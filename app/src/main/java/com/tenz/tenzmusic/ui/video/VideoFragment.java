package com.tenz.tenzmusic.ui.video;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tenz.tenzmusic.R;
import com.tenz.tenzmusic.adapter.MyViewPagerAdapter;
import com.tenz.tenzmusic.api.RetrofitApi;
import com.tenz.tenzmusic.base.BaseFragment;
import com.tenz.tenzmusic.entity.VideoSortResponse;
import com.tenz.tenzmusic.http.BaseObserver;
import com.tenz.tenzmusic.http.RetrofitFactory;
import com.tenz.tenzmusic.http.RxScheduler;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class VideoFragment extends BaseFragment {

    @BindView(R.id.ll_load)
    LinearLayout ll_load;
    @BindView(R.id.pb_video)
    ProgressBar pb_video;
    @BindView(R.id.tv_reload)
    TextView tv_reload;
    @BindView(R.id.tl_video_sort)
    TabLayout tl_video_sort;
    @BindView(R.id.vp_video)
    ViewPager vp_video;

    private static VideoFragment instance;

    private MyViewPagerAdapter myViewPagerAdapter;
    private List<Fragment> fragmentList;
    private List<String> titleList;

    public static VideoFragment newInstance() {
        if(null == instance){
            instance = new VideoFragment();
        }
        return instance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
        fragmentList = new ArrayList<>();
        titleList = new ArrayList<>();

        myViewPagerAdapter = new MyViewPagerAdapter(getFragmentManager(),fragmentList,titleList);
        vp_video.setAdapter(myViewPagerAdapter);
        tl_video_sort.setupWithViewPager(vp_video);
        vp_video.setOffscreenPageLimit(5);

        getVideoSort();
    }

    @OnClick({R.id.tv_reload})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_reload:
                getVideoSort();
                break;
        }
    }

    /**
     * 获取MV分类
     */
    private void getVideoSort(){
        RetrofitFactory.getInstance().createApi(RetrofitApi.class).getVideoSort(9108,0, 2)
                .compose(RxScheduler.rxSchedulerTransform())
                .subscribe(new BaseObserver<VideoSortResponse>() {
                    @Override
                    protected void onStart() {
                        ll_load.setVisibility(View.VISIBLE);
                        pb_video.setVisibility(View.VISIBLE);
                        tv_reload.setVisibility(View.GONE);
                    }

                    @Override
                    protected void onSuccess(VideoSortResponse data) throws Exception {
                        ll_load.setVisibility(View.GONE);

                        fragmentList.clear();
                        titleList.clear();
                        for(int i = 0; i < data.getList().size(); i++){
                            fragmentList.add(VideoItemFragment.newInstance(data.getList().get(i).getChannel_id()));
                            titleList.add(data.getList().get(i).getName());
                        }
                        myViewPagerAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onError(String message) throws Exception {
                        super.onError(message);
                        pb_video.setVisibility(View.GONE);
                        tv_reload.setVisibility(View.VISIBLE);
                    }

                    @Override
                    protected void onFinish() {
                    }
                });
    }

}
