package com.six.cat.sixcat.views.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.design.widget.TabLayout;

import com.six.cat.sixcat.R;
import com.six.cat.sixcat.adapter.HomeFgAdapter;
import com.six.cat.sixcat.views.base.BaseRxLazyFragment;
import com.six.cat.sixcat.utils.SettingUtil;
import com.six.cat.sixcat.utils.ShowToast;
import com.trello.rxlifecycle2.LifecycleTransformer;

import butterknife.BindView;

/**
 * @author liguoying
 * @date 2017/12/11.Folder E:\Git\PGit\SixCat\app\build\generated\source\kaptKotlin\downDebug
 */

public class HomeFragment extends BaseRxLazyFragment {
    private static HomeFragment instance;
    @BindView(R.id.vp_home_fragment)
    ViewPager mHomeViewPage;
    @BindView(R.id.ll_home_fragment_header_layout)
    LinearLayout mHomell;
    @BindView(R.id.tb_home_fragment)
    TabLayout mHomeTb;
    @BindView(R.id.iv_home_fragment_add_channel)
    ImageView mHomeIv;


    public static HomeFragment newInstance() {
        if (instance == null) {
            instance = new HomeFragment();
        }
        return instance;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void finishCreateView(Bundle state) {
        initViewPage();
    }

    private void initViewPage() {
        HomeFgAdapter homeFgAdapter = new HomeFgAdapter(getChildFragmentManager(), getApplicationContext());
        mHomeViewPage.setOffscreenPageLimit(3);
        mHomeViewPage.setAdapter(homeFgAdapter);
        mHomeTb.setupWithViewPager(mHomeViewPage);
        mHomeTb.setTabMode(TabLayout.MODE_SCROLLABLE);
        mHomeIv.setOnClickListener(view -> ShowToast.shortTime("i click it"));
        mHomell.setBackgroundColor(SettingUtil.getInstance().getColor());
    }


    @Override
    public void onShowNetError() {

    }

    @Override
    public void mSetPresenter(Object presenter) {

    }

    @Override
    public void onShowNoMore() {

    }

    @Override
    public LifecycleTransformer bindToLife() {
        return null;
    }


}
