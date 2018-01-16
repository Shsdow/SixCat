package com.six.cat.sixcat.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaeger.library.StatusBarUtil;
import com.six.cat.sixcat.R;
import com.six.cat.sixcat.base.BaseActivity;
import com.six.cat.sixcat.fragment.HomeFragment;
import com.six.cat.sixcat.fragment.PictureFragment;
import com.six.cat.sixcat.fragment.ThemeFragment;
import com.six.cat.sixcat.fragment.VideoFragment;
import com.six.cat.sixcat.utils.SPUtil;
import com.six.cat.sixcat.utils.SettingUtil;
import com.six.cat.sixcat.utils.ShowToast;
import com.six.cat.sixcat.view.CircleImageView;
import com.six.cat.sixcat.widget.BottomNavigationViewHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nvv_view)
    NavigationView mNavigationView;
    @BindView(R.id.bttom_nav)
    BottomNavigationView mBottomNavigationView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBarLayout;

    private static final int FRAGMENT_NEWS = 0;
    private static final int FRAGMENT_PHOTO = 1;
    private static final int FRAGMENT_VIDEO = 2;
    private static final int FRAGMENT_MEDIA = 3;
    private Fragment[] fragments;
    private HomeFragment mHomeFragment;
    private PictureFragment mPictureFragment;
    private ThemeFragment mThemeFragment;
    private VideoFragment mVideoFragment;
    private static final String POSITION = "position";
    private static final String SELECT_ITEM = "bottomNavigationSelectItem";
    private int position;
    private int index;
    private List<String> titleList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (!SPUtil.getBoolean("isFirst")) {
            startActivity(new Intent(this, GuideActivity.class));
        }
//        StatusBarUtil.setTranslucent(this, 150);
//        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent, null));
//        StatusBarUtil.setTransparent(this);
        StatusBarUtil.setColorForDrawerLayout(MainActivity.this, mDrawerLayout, Color.parseColor("#20C1FD"), 255);
        initFragments(savedInstanceState);
        initNavigationView();
        titleList.add("讲演");
        titleList.add("枝丫");
        titleList.add("线程");
        titleList.add("记录");
    }


    private void initFragments(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mHomeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(HomeFragment.class.getName());
            mPictureFragment = (PictureFragment) getSupportFragmentManager().findFragmentByTag(PictureFragment.class.getName());
            mThemeFragment = (ThemeFragment) getSupportFragmentManager().findFragmentByTag(ThemeFragment.class.getName());
            mVideoFragment = (VideoFragment) getSupportFragmentManager().findFragmentByTag(VideoFragment.class.getName());
            showFragment(savedInstanceState.getInt(POSITION));
            mBottomNavigationView.setSelectedItemId(savedInstanceState.getInt(SELECT_ITEM));
        } else {
            showFragment(FRAGMENT_NEWS);
        }
    }

    @Override
    protected void initToolBar() {
        mToolbar.inflateMenu(R.menu.menu_activity_main);
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        setSupportActionBar(mToolbar);
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_news:
                    showFragment(FRAGMENT_NEWS);
                    doubleClick(FRAGMENT_NEWS);
                    break;
                case R.id.action_photo:
                    showFragment(FRAGMENT_PHOTO);
                    doubleClick(FRAGMENT_PHOTO);
                    break;
                case R.id.action_video:
                    showFragment(FRAGMENT_VIDEO);
                    doubleClick(FRAGMENT_VIDEO);
                    break;
                case R.id.action_media:
                    showFragment(FRAGMENT_MEDIA);
                    doubleClick(FRAGMENT_MEDIA);
                    break;
                default:
                    break;
            }
            return true;
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void doubleClick(int fragmentVideo) {

    }

    private void showFragment(int fragmentNews) {
        ShowToast.shortTime(String.format(Locale.CHINA, "这是第 %d 个 fragment ", fragmentNews));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        position = fragmentNews;
        switch (fragmentNews) {
            case FRAGMENT_NEWS:
                mToolbar.setTitle(R.string.app_name);
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance();
                    ft.add(R.id.container, mHomeFragment, HomeFragment.class.getName());
                } else {
                    ft.show(mHomeFragment);
                }
                break;

            case FRAGMENT_PHOTO:
                mToolbar.setTitle(R.string.title_photo);
                if (mPictureFragment == null) {
                    mPictureFragment = PictureFragment.newInstance();
                    ft.add(R.id.container, mPictureFragment, PictureFragment.class.getName());
                } else {
                    ft.show(mPictureFragment);
                }
                break;

            case FRAGMENT_VIDEO:
                mToolbar.setTitle(getString(R.string.title_video));
                if (mVideoFragment == null) {
                    mVideoFragment = VideoFragment.newInstance();
                    ft.add(R.id.container, mVideoFragment, VideoFragment.class.getName());
                } else {
                    ft.show(mVideoFragment);
                }
                break;

            case FRAGMENT_MEDIA:
                mToolbar.setTitle(getString(R.string.title_media));
                if (mThemeFragment == null) {
                    mThemeFragment = ThemeFragment.newInstance();
                    ft.add(R.id.container, mThemeFragment, ThemeFragment.class.getName());
                } else {
                    ft.show(mThemeFragment);
                }
                break;
            default:
                break;
        }

        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        if (mHomeFragment != null) {
            ft.hide(mHomeFragment);
        }
        if (mPictureFragment != null) {
            ft.hide(mPictureFragment);
        }
        if (mVideoFragment != null) {
            ft.hide(mVideoFragment);
        }
        if (mThemeFragment != null) {
            ft.hide(mThemeFragment);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // recreate 时记录当前位置 (在 Manifest 已禁止 Activity 旋转,所以旋转屏幕并不会执行以下代码)
        outState.putInt(POSITION, position);
        outState.putInt(SELECT_ITEM, mBottomNavigationView.getSelectedItemId());
    }

    private void initNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(this);
        View headerView = mNavigationView.getHeaderView(0);
        CircleImageView circleImageView = headerView.findViewById(R.id.civ_header);
        TextView mUserName = headerView.findViewById(R.id.tv_user_title_name);
        Glide.with(this).load("https://bingfilestore.blob.core.windows.net/homepage/app/Holiday2017/v1/icons/snow_40x40.png").into(circleImageView);
        mUserName.setText("我的大头照");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        ShowToast.shortTime("我点击了 naviagetion");
        mDrawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.item_home:
                // 主页
                return true;
            case R.id.item_download:
                return true;
            case R.id.item_vip:
                return true;
            case R.id.item_favourite:
                return true;
            case R.id.item_history:
                return true;
            case R.id.item_group:
                return true;
            case R.id.item_tracker:
                return true;
            case R.id.item_theme:
                // 主题选择
//                changeTheme();
                return true;
            case R.id.item_app:
                // 应用推荐
                startActivity(new Intent(this, SettingActivity.class));
                return true;
            case R.id.item_settings:
                // 设置中心
                return true;
            default:
                mDrawerLayout.closeDrawers();
                break;
        }
        return false;
    }

    private void changeTheme() {
        int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (mode == Configuration.UI_MODE_NIGHT_YES) {
            SettingUtil.getInstance().setIsNightMode(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            SettingUtil.getInstance().setIsNightMode(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
        recreate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            ShowToast.shortTime("我点击了 menu");
        }
        return super.onOptionsItemSelected(item);
    }
}
