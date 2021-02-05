package com.tenz.tenzmusic.ui.video;

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

public class VideoFragment extends BaseFragment {

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

    /**
     * 获取MV分类
     */
    private void getVideoSort(){
        RetrofitFactory.getInstance().createApi(RetrofitApi.class).getVideoSort(9108,0, 2)
                .compose(RxScheduler.rxSchedulerTransform())
                .subscribe(new BaseObserver<VideoSortResponse>() {
                    @Override
                    protected void onStart() {
                        showLoadingDialog();
                    }

                    @Override
                    protected void onSuccess(VideoSortResponse data) throws Exception {
                        fragmentList.clear();
                        titleList.clear();
                        for(int i = 0; i < data.getList().size(); i++){
                            fragmentList.add(VideoItemFragment.newInstance(data.getList().get(i).getChannel_id()));
                            titleList.add(data.getList().get(i).getName());
                        }
                        myViewPagerAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onFinish() {
                        dismissLoadingDialog();
                    }
                });
    }

}
