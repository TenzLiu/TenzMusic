package com.tenz.tenzmusic.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager mFragmentManager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    public MyViewPagerAdapter(@NonNull FragmentManager fragmentManager,
                              List<Fragment> fragmentList, List<String> titleList) {
        super(fragmentManager);
        this.mFragmentManager = fragmentManager;
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

}
