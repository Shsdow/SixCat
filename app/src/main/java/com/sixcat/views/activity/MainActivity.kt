package com.sixcat.views.activity

import android.Manifest
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.sixcat.R
import com.sixcat.base.BaseActivity
import com.sixcat.utils.SPUtil
import com.sixcat.utils.SettingUtil
import com.sixcat.utils.ShowToast
import com.sixcat.utils.StatusBarUtil
import com.sixcat.views.fragment.HomeFragment
import com.sixcat.views.fragment.PictureFragment
import com.sixcat.views.fragment.ThemeFragment
import com.sixcat.views.fragment.VideoShowFragment
import com.sixcat.widgets.BottomNavigationViewHelper
import com.sixcat.widgets.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnNeverAskAgain
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions
import java.util.*

@RuntimePermissions
class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val mHomeFragment by lazy { HomeFragment.newInstance() }
    private val mPictureFragment by lazy { PictureFragment.newInstance() }
    private val mThemeFragment by lazy { ThemeFragment.newInstance() }
    private val mVideoFragment by lazy { VideoShowFragment.newInstance() }
    private val manager by lazy { supportFragmentManager.beginTransaction() }
    private var position: Int = 0
    private val titleList = ArrayList<String>()
    private var exitTime: Long = 0L

    companion object {
        private const val FRAGMENT_NEWS = 0
        private const val FRAGMENT_PHOTO = 1
        private const val FRAGMENT_VIDEO = 2
        private const val FRAGMENT_MEDIA = 3
        private const val POSITION = "position"
        private const val SELECT_ITEM = "bottomNavigationSelectItem"
    }

    override fun getLayoutId() = R.layout.activity_main


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
            showFragment(savedInstanceState.getInt(POSITION))
            bttom_nav!!.selectedItemId = savedInstanceState.getInt(SELECT_ITEM)
        } else {
            addFragmentsToActivity()
            showFragment(FRAGMENT_NEWS)
        }
    }

    private fun addFragmentsToActivity() {
        manager.add(R.id.container, mHomeFragment, PictureFragment::class.java.name)
        manager.add(R.id.container, mPictureFragment, PictureFragment::class.java.name)
        manager.add(R.id.container, mThemeFragment, PictureFragment::class.java.name)
        manager.add(R.id.container, mVideoFragment, PictureFragment::class.java.name)
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

//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        // NOTE: delegate the permission handling to generated function
////        onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }

    private fun initToolBar() {
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
        hideFragment(manager)
        position = fragmentNews
        when (fragmentNews) {
            FRAGMENT_NEWS -> {
                toolbar!!.setTitle(R.string.app_name)
                manager.show(mHomeFragment)
            }

            FRAGMENT_PHOTO -> {
                toolbar!!.setTitle(R.string.title_photo)
                manager.show(mPictureFragment)
            }

            FRAGMENT_VIDEO -> {
                toolbar!!.title = getString(R.string.title_video)
                manager.show(mVideoFragment)
            }

            FRAGMENT_MEDIA -> {
                toolbar!!.title = getString(R.string.title_media)
                manager.show(mThemeFragment)
            }
        }
        manager.commit()
    }

    private fun hideFragment(transaction: FragmentTransaction) {
        transaction.hide(mHomeFragment)
        transaction.hide(mPictureFragment)
        transaction.hide(mVideoFragment)
        transaction.hide(mThemeFragment)
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
