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
import com.sixcat.R
import com.sixcat.base.BaseActivity
import com.sixcat.utils.LogUtil
import com.sixcat.utils.SettingUtil
import com.sixcat.utils.ShowToast
import com.sixcat.utils.StatusBarUtil
import com.sixcat.views.fragment.FileListFragment
import com.sixcat.views.fragment.PictureFragment
import com.sixcat.views.fragment.TodoListFragment
import com.sixcat.views.fragment.VideoShowFragment
import com.sixcat.widgets.BottomNavigationViewHelper
import com.sixcat.widgets.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.OnNeverAskAgain
import permissions.dispatcher.OnPermissionDenied
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class MainActivity : BaseActivity()/*, NavigationView.OnNavigationItemSelectedListener*/ {


    private var mHomeFragment: FileListFragment? = null
    private var mPictureFragment: PictureFragment? = null
    private var mTodoListFragment: TodoListFragment? = null
    private var mVideoFragment: VideoShowFragment? = null
    private var position: Int = 0
    private var exitTime: Long = 0L
    private val titleList by lazy {
        listOf(resources.getString(R.string.app_name), resources.getString(R.string.title_photo), resources.getString(R.string.title_video), getString(R.string.title_todo))
    }

    private val fragmentTags by lazy {
        listOf(FileListFragment::class.java.name, PictureFragment::class.java.name, TodoListFragment::class.java.name, VideoShowFragment::class.java.name)
    }

    override fun getLayoutId(): Int {
//        setTheme(R.style.AppTheme_NoActionBar)
        return R.layout.activity_main
    }

    override fun initView() {
//        if (!SPUtil.getBoolean("isFirst")) {
//        startActivity(Intent(this, GuideActivity::class.java))
//        }

        LogUtil.e("aaaaa")
        Thread.sleep(3000L)
        reportFullyDrawn()
        LogUtil.e("aaaaa")
        StatusBarUtil.setColorForDrawerLayout(this@MainActivity, mDrawerLayoutMain, Color.parseColor("#20C1FD"), 255)
        initToolBar()
        initFragments(null)
        initNavigationView()
    }


    private fun initToolBar() {
        toolbar!!.inflateMenu(R.menu.menu_activity_main)
        BottomNavigationViewHelper.disableShiftMode(bottomNav)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(this, mDrawerLayoutMain, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawerLayoutMain!!.addDrawerListener(toggle)
        toggle.syncState()
        initListener()
    }


    private fun initFragments(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mHomeFragment = supportFragmentManager.findFragmentByTag(fragmentTags[0]) as FileListFragment
            mPictureFragment = supportFragmentManager.findFragmentByTag(fragmentTags[1]) as PictureFragment
            mTodoListFragment = supportFragmentManager.findFragmentByTag(fragmentTags[2]) as TodoListFragment
            mVideoFragment = supportFragmentManager.findFragmentByTag(fragmentTags[3]) as VideoShowFragment
            showFragment(savedInstanceState.getInt(POSITION))
            bottomNav!!.selectedItemId = savedInstanceState.getInt(SELECT_ITEM)
        } else {
            showFragment(FRAGMENT_FILM_LIST)
        }
    }

    private fun showFragment(fragmentNews: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragment(transaction)
        position = fragmentNews
        when (fragmentNews) {
            FRAGMENT_FILM_LIST -> {
                if (mHomeFragment == null) {
                    mHomeFragment = FileListFragment.newInstance()
                    transaction.add(R.id.container, mHomeFragment!!, FileListFragment::class.java.name)
                } else {
                    transaction.show(mHomeFragment!!)
                }
            }

            FRAGMENT_PHOTO -> {
                if (mPictureFragment == null) {
                    mPictureFragment = PictureFragment.newInstance()
                    transaction.add(R.id.container, mPictureFragment!!, PictureFragment::class.java.name)
                } else {
                    transaction.show(mPictureFragment!!)
                }
            }

            FRAGMENT_VIDEO -> {
                if (mVideoFragment == null) {
                    mVideoFragment = VideoShowFragment.newInstance()
                    transaction.add(R.id.container, mVideoFragment!!, VideoShowFragment::class.java.name)
                } else {
                    transaction.show(mVideoFragment!!)
                }
            }

            FRAGMENT_TODO_LIST -> {
                if (mTodoListFragment == null) {
                    mTodoListFragment = TodoListFragment.newInstance()
                    transaction.add(R.id.container, mTodoListFragment!!, TodoListFragment::class.java.name)
                } else {
                    transaction.show(mTodoListFragment!!)
                }
            }
        }
        transaction.commit()
        toolbar!!.title = titleList[fragmentNews]
    }

    private fun hideFragment(ft: FragmentTransaction) {
        if (mHomeFragment != null) {
            ft.hide(mHomeFragment!!)
        }
        if (mPictureFragment != null) {
            ft.hide(mPictureFragment!!)
        }
        if (mVideoFragment != null) {
            ft.hide(mVideoFragment!!)
        }
        if (mTodoListFragment != null) {
            ft.hide(mTodoListFragment!!)
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

//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        // NOTE: delegate the permission handling to generated function
////        onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }

    private fun initListener() {
        bottomNav!!.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_film_list -> {
                    showFragment(FRAGMENT_FILM_LIST)
                }
                R.id.action_photo -> {
                    showFragment(FRAGMENT_PHOTO)
                }
                R.id.action_video -> {
                    showFragment(FRAGMENT_VIDEO)
                }
                R.id.action_todo_list -> {
                    showFragment(FRAGMENT_TODO_LIST)
                }
            }
            true
        }

        navigationView.setNavigationItemSelectedListener { item ->
            mDrawerLayoutMain!!.closeDrawer(GravityCompat.START)
            ShowToast.shortTime("f3434")
            when (item.itemId) {
                R.id.item_home -> {
                    ShowToast.shortTime("f")
                }
                R.id.item_download -> true
                R.id.item_vip -> true
                R.id.item_favourite -> true
                R.id.item_history -> true
                R.id.item_group -> true
                R.id.item_tracker -> true
                R.id.item_theme ->
                    // 主题选择
                    //                changeTheme();
                    true
                R.id.item_app -> {
                    // 应用推荐
                    startActivity(Intent(this, SettingActivity::class.java))
                    true
                }
                R.id.item_settings ->
                    // 设置中心
                    true
                else -> mDrawerLayoutMain!!.closeDrawers()
            }
            false
        }
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        // recreate 时记录当前位置 (在 Manifest 已禁止 Activity 旋转,所以旋转屏幕并不会执行以下代码)
        outState!!.putInt(POSITION, position)
        outState.putInt(SELECT_ITEM, bottomNav!!.selectedItemId)
    }

    private fun initNavigationView() {
//        navigationView!!.setNavigationItemSelectedListener(this)
        val headerView = navigationView!!.getHeaderView(0)
        val circleImageView = headerView.findViewById<CircleImageView>(R.id.civ_header)
        val mUserName = headerView.findViewById<TextView>(R.id.tv_user_title_name)
        Glide.with(this).load("https://bingfilestore.blob.core.windows.net/homepage/app/Holiday2017/v1/icons/snow_40x40.png").into(circleImageView)
        mUserName.text = "我的大头照"
    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        ShowToast.shortTime("我点击了 naviagetion")
//
//    }

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

    companion object {
        private const val FRAGMENT_FILM_LIST = 0
        private const val FRAGMENT_PHOTO = 1
        private const val FRAGMENT_VIDEO = 2
        private const val FRAGMENT_TODO_LIST = 3
        private const val POSITION = "position"
        private const val SELECT_ITEM = "bottomNavigationSelectItem"
    }

}

