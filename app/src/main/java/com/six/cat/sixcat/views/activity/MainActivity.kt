package com.six.cat.sixcat.views.activity

import android.Manifest
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatDelegate
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.bumptech.glide.Glide
import com.six.cat.sixcat.R
import com.six.cat.sixcat.base.BaseActivity
import com.six.cat.sixcat.base.IBasePresenter
import com.six.cat.sixcat.utils.SPUtil
import com.six.cat.sixcat.utils.SettingUtil
import com.six.cat.sixcat.utils.ShowToast
import com.six.cat.sixcat.utils.StatusBarUtil
import com.six.cat.sixcat.views.fragment.HomeFragment
import com.six.cat.sixcat.views.fragment.PictureFragment
import com.six.cat.sixcat.views.fragment.ThemeFragment
import com.six.cat.sixcat.views.fragment.VideoShowFragment
import com.six.cat.sixcat.widgets.BottomNavigationViewHelper
import com.six.cat.sixcat.widgets.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnNeverAskAgain
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions
import java.util.*

@RuntimePermissions
class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {


    private var mHomeFragment: HomeFragment? = null
    private var mPictureFragment: PictureFragment? = null
    private var mThemeFragment: ThemeFragment? = null
    private var mVideoFragment: VideoShowFragment? = null
    private var position: Int = 0
    private val titleList = ArrayList<String>()
    private var exitTime: Long = 0L

    companion object {
        private val FRAGMENT_NEWS = 0
        private val FRAGMENT_PHOTO = 1
        private val FRAGMENT_VIDEO = 2
        private val FRAGMENT_MEDIA = 3
        private val POSITION = "position"
        private val SELECT_ITEM = "bottomNavigationSelectItem"
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        if (!SPUtil.getBoolean("isFirst")) {
            startActivity(Intent(this, GuideActivity::class.java))
        }
        StatusBarUtil.setColorForDrawerLayout(this@MainActivity, mDrawerLayoutMain, Color.parseColor("#20C1FD"), 255)
        initToolBar()
        initFragments(null)
        initNavigationView()
        titleList.add("讲演")
        titleList.add("枝丫")
        titleList.add("线程")
        titleList.add("记录")
    }


    private fun initFragments(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mHomeFragment = supportFragmentManager.findFragmentByTag(HomeFragment.newInstance().tag) as HomeFragment
            mPictureFragment = supportFragmentManager.findFragmentByTag(PictureFragment.newInstance().tag) as PictureFragment
            mThemeFragment = supportFragmentManager.findFragmentByTag(ThemeFragment.newInstance().tag) as ThemeFragment
            mVideoFragment = supportFragmentManager.findFragmentByTag(VideoShowFragment.newInstance().tag) as VideoShowFragment
            showFragment(savedInstanceState.getInt(POSITION))
            bttom_nav!!.selectedItemId = savedInstanceState.getInt(SELECT_ITEM)
        } else {
            showFragment(FRAGMENT_NEWS)
        }
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun shoWrite() {
    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun onWriteDenied() {
        ShowToast.shortTime(R.string.permission_write_denied);
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun onWriteNeverAskAgain() {
        ShowToast.shortTime(R.string.permission_write_never_askagain);
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // NOTE: delegate the permission handling to generated function
//        onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun initToolBar() {
        toolbar!!.inflateMenu(R.menu.menu_activity_main)
        BottomNavigationViewHelper.disableShiftMode(bttom_nav)
        setSupportActionBar(toolbar)
        bttom_nav!!.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_news -> {
                    showFragment(FRAGMENT_NEWS)
                    doubleClick(FRAGMENT_NEWS)
                }
                R.id.action_photo -> {
                    showFragment(FRAGMENT_PHOTO)
                    doubleClick(FRAGMENT_PHOTO)
                }
                R.id.action_video -> {
                    showFragment(FRAGMENT_VIDEO)
                    doubleClick(FRAGMENT_VIDEO)
                }
                R.id.action_media -> {
                    showFragment(FRAGMENT_MEDIA)
                    doubleClick(FRAGMENT_MEDIA)
                }
                else -> {
                }
            }
            true
        }
        val toggle = ActionBarDrawerToggle(this, mDrawerLayoutMain, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawerLayoutMain!!.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun doubleClick(fragmentVideo: Int) {

    }

    private fun showFragment(fragmentNews: Int) {
//        ShowToast.shortTime(String.format(Locale.CHINA, "这是第 %d 个 fragment ", fragmentNews))
        val ft = supportFragmentManager.beginTransaction()
        hideFragment(ft)
        position = fragmentNews
        when (fragmentNews) {
            FRAGMENT_NEWS -> {
                toolbar!!.setTitle(R.string.app_name)
                if (mHomeFragment == null) {
                    mHomeFragment = HomeFragment.newInstance()
                    ft.add(R.id.container, mHomeFragment, HomeFragment::class.java.name)
                } else {
                    ft.show(mHomeFragment)
                }
            }

            FRAGMENT_PHOTO -> {
                toolbar!!.setTitle(R.string.title_photo)
                if (mPictureFragment == null) {
                    mPictureFragment = PictureFragment.newInstance()
                    ft.add(R.id.container, mPictureFragment, PictureFragment::class.java.name)
                } else {
                    ft.show(mPictureFragment)
                }
            }

            FRAGMENT_VIDEO -> {
                toolbar!!.title = getString(R.string.title_video)
                if (mVideoFragment == null) {
                    mVideoFragment = VideoShowFragment.newInstance()
                    ft.add(R.id.container, mVideoFragment, VideoShowFragment::class.java.name)
                } else {
                    ft.show(mVideoFragment)
                }
            }

            FRAGMENT_MEDIA -> {
                toolbar!!.title = getString(R.string.title_media)
                if (mThemeFragment == null) {
                    mThemeFragment = ThemeFragment.newInstance()
                    ft.add(R.id.container, mThemeFragment, ThemeFragment::class.java.name)
                } else {
                    ft.show(mThemeFragment)
                }
            }
        }

        ft.commit()
    }

    private fun hideFragment(ft: FragmentTransaction) {
        if (mHomeFragment != null) {
            ft.hide(mHomeFragment)
        }
        if (mPictureFragment != null) {
            ft.hide(mPictureFragment)
        }
        if (mVideoFragment != null) {
            ft.hide(mVideoFragment)
        }
        if (mThemeFragment != null) {
            ft.hide(mThemeFragment)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        // recreate 时记录当前位置 (在 Manifest 已禁止 Activity 旋转,所以旋转屏幕并不会执行以下代码)
        outState!!.putInt(POSITION, position)
        outState.putInt(SELECT_ITEM, bttom_nav!!.selectedItemId)
    }

    private fun initNavigationView() {
        nvv_view!!.setNavigationItemSelectedListener(this)
        val headerView = nvv_view!!.getHeaderView(0)
        val circleImageView = headerView.findViewById<CircleImageView>(R.id.civ_header)
        val mUserName = headerView.findViewById<TextView>(R.id.tv_user_title_name)
        Glide.with(this).load("https://bingfilestore.blob.core.windows.net/homepage/app/Holiday2017/v1/icons/snow_40x40.png").into(circleImageView)
        mUserName.text = "我的大头照"
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        ShowToast.shortTime("我点击了 naviagetion")
        mDrawerLayoutMain!!.closeDrawer(GravityCompat.START)
        when (item.itemId) {
            R.id.item_home ->
                // 主页
                return true
            R.id.item_download -> return true
            R.id.item_vip -> return true
            R.id.item_favourite -> return true
            R.id.item_history -> return true
            R.id.item_group -> return true
            R.id.item_tracker -> return true
            R.id.item_theme ->
                // 主题选择
                //                changeTheme();
                return true
            R.id.item_app -> {
                // 应用推荐
                startActivity(Intent(this, SettingActivity::class.java))
                return true
            }
            R.id.item_settings ->
                // 设置中心
                return true
            else -> mDrawerLayoutMain!!.closeDrawers()
        }
        return false
    }

    private fun changeTheme() {
        val mode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (mode == Configuration.UI_MODE_NIGHT_YES) {
            SettingUtil.instance.isNightMode = false
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            SettingUtil.instance.isNightMode = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        window.setWindowAnimations(R.style.WindowAnimationFadeInOut)
        recreate()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_search) {
            ShowToast.shortTime("我点击了 menu")
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mDrawerLayoutMain.isDrawerOpen(mDrawerLayoutMain.getChildAt(1))) {
                mDrawerLayoutMain.closeDrawers()
            } else {
                exitApp()
            }
        }
        return true
    }

    private fun exitApp() {
        when (System.currentTimeMillis() - exitTime > 2000) {
            true -> {
                ShowToast.shortTime("再按一次退出我")
                exitTime = System.currentTimeMillis()
            }
            else -> finish()
        }
    }

}
