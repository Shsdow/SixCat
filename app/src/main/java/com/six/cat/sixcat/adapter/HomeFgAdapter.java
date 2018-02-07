package com.six.cat.sixcat.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.module.video.HomeVideoFragment;
import com.six.cat.sixcat.module.movieshow.HomeLiveFragment;
import com.six.cat.sixcat.fragment.homefgs.HomeRecommendedFragment;
import com.six.cat.sixcat.fragment.homefgs.HomeRegionFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liguoying
 * @date 2017/12/27.
 */

public class HomeFgAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragmentLists;
    private List<String> mTitles;

    private String[] mTitle;
    private Fragment[] mFragments;


    public HomeFgAdapter(FragmentManager fm, List<Fragment> mFragmentLists, List<String> mTitles) {
        super(fm);
        this.mFragmentLists = mFragmentLists;
        this.mTitles = mTitles;
    }

    public HomeFgAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        mTitle = mContext.getResources().getStringArray(R.array.hometabs);
        mFragments = new Fragment[mTitle.length];
    }

    public HomeFgAdapter(FragmentManager fm, List<Fragment> mFragmentLists, String[] mTitles) {
        super(fm);
        this.mFragmentLists = mFragmentLists;
        this.mTitles = new ArrayList<>(Arrays.asList(mTitles));
    }


    @Override
    public Fragment getItem(int position) {
        if (mFragments[position] == null) {
            switch (position) {
                case 0:
                    mFragments[position] = HomeLiveFragment.newInstance();
                    break;
                case 1:
                    mFragments[position] = HomeRecommendedFragment.newInstance();
                    break;
                case 2:
                    mFragments[position] = HomeVideoFragment.Companion.newInstance();
                    break;
                case 3:
                    mFragments[position] = HomeRegionFragment.newInstance();
                    break;

            }
        }
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }

    public void recreateItems(List<Fragment> fragmentLists, List<String> titlesLists) {
        this.mFragmentLists = fragmentLists;
        this.mTitles = titlesLists;
        notifyDataSetChanged();
    }

}
