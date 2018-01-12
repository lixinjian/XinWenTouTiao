package com.xinjian.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.xinjian.R;
import com.xinjian.fragment.MediaChannelView;
import com.xinjian.fragment.NewsTabLayout;
import com.xinjian.fragment.PhotoTabLayout;
import com.xinjian.fragment.VideoTabLayout;
import com.xinjian.utils.ToastUtils;
import com.xinjian.widget.helper.BottomNavigationViewHelper;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String POSITION = "position";
    private static final String SELECT_ITEM = "bottomNavigationSelectItem";
    private static final int FRAGMENT_NEWS = 0;
    private static final int FRAGMENT_PHOTO = 1;
    private static final int FRAGMENT_VIDEO = 2;
    private static final int FRAGMENT_MEDIA = 3;
    private int position;

    private long mExitTime = 0;
    private long firstClickTime = 0;

    private Toolbar mToolbar;
    private BottomNavigationView mBottomNavigationView;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;

    private NewsTabLayout mNewsTabLayout;
    private PhotoTabLayout mPhotoTabLayout;
    private VideoTabLayout mVideoTabLayout;
    private MediaChannelView mMediaChannelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        if (savedInstanceState != null) {
            mNewsTabLayout = (NewsTabLayout) getSupportFragmentManager().findFragmentByTag(NewsTabLayout.class.getName());
            mPhotoTabLayout = (PhotoTabLayout) getSupportFragmentManager().findFragmentByTag(PhotoTabLayout.class.getName());
            mVideoTabLayout = (VideoTabLayout) getSupportFragmentManager().findFragmentByTag(VideoTabLayout.class.getName());
            mMediaChannelView = (MediaChannelView) getSupportFragmentManager().findFragmentByTag(MediaChannelView.class.getName());
            // 恢复 recreate 前的位置
            showFragment(savedInstanceState.getInt(POSITION));
            mBottomNavigationView.setSelectedItemId(savedInstanceState.getInt(SELECT_ITEM));
        } else {
            showFragment(FRAGMENT_NEWS);
        }

    }

    /**
     * 展示对应的频道fragment
     */
    private void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        position = index;
        int resouseId = R.id.container;
        switch (index) {
            case FRAGMENT_NEWS:
                mToolbar.setTitle(R.string.title_news);
                /**
                 * 如果Fragment为空，就新建一个实例
                 * 如果不为空，就将它从栈中显示出来
                 */
                if (mNewsTabLayout == null) {
                    mNewsTabLayout = NewsTabLayout.getInstance();
                    ft.add(resouseId, mNewsTabLayout, NewsTabLayout.class.getName());
                } else {
                    ft.show(mNewsTabLayout);
                }
                break;

            case FRAGMENT_PHOTO:
                mToolbar.setTitle(R.string.title_photo);
                if (mPhotoTabLayout == null) {
                    mPhotoTabLayout = PhotoTabLayout.getInstance();
                    ft.add(resouseId, mPhotoTabLayout, PhotoTabLayout.class.getName());
                } else {
                    ft.show(mPhotoTabLayout);
                }
                break;

            case FRAGMENT_VIDEO:
                mToolbar.setTitle(R.string.title_video);
                if (mVideoTabLayout == null) {
                    mVideoTabLayout = VideoTabLayout.getInstance();
                    ft.add(resouseId, mVideoTabLayout, VideoTabLayout.class.getName());
                } else {
                    ft.show(mVideoTabLayout);
                }
                break;

            case FRAGMENT_MEDIA:
                mToolbar.setTitle(R.string.title_media);
                if (mMediaChannelView == null) {
                    mMediaChannelView = MediaChannelView.getInstance();
                    ft.add(resouseId, mMediaChannelView, MediaChannelView.class.getName());
                } else {
                    ft.show(mMediaChannelView);
                }
        }
        ft.commit();
    }

    /**
     * 隐藏
     */
    private void hideFragment(FragmentTransaction ft) {
        // 如果不为空，就先隐藏起来
        if (mNewsTabLayout != null) {
            ft.hide(mNewsTabLayout);
        }
        if (mPhotoTabLayout != null) {
            ft.hide(mPhotoTabLayout);
        }
        if (mVideoTabLayout != null) {
            ft.hide(mVideoTabLayout);
        }
        if (mMediaChannelView != null) {
            ft.hide(mMediaChannelView);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // recreate 时记录当前位置 (在 Manifest 已禁止 Activity 旋转,所以旋转屏幕并不会执行以下代码)
        outState.putInt(POSITION, position);
        outState.putInt(SELECT_ITEM, mBottomNavigationView.getSelectedItemId());
        super.onSaveInstanceState(outState);
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.menu_activity_main);
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        setSupportActionBar(mToolbar);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
//                        doubleClick(FRAGMENT_VIDEO);
                        break;
                    case R.id.action_media:
                        showFragment(FRAGMENT_MEDIA);
                        break;
                }
                return true;
            }
        });

        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
    }


    /**
     * 监听物理返回键,再按一次退出程序
     */
    /*public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/
    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - mExitTime) < 2000) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = currentTime;
        }
    }

    public void doubleClick(int index) {
        long secondClickTime = System.currentTimeMillis();
        if ((secondClickTime - firstClickTime < 500)) {
            switch (index) {
                case FRAGMENT_NEWS:
                    ToastUtils.showToastShort("双击666");
//                    mNewsTabLayout.onDoubleClick();
                    break;
                case FRAGMENT_PHOTO:
//                    mPhotoTabLayout.onDoubleClick();
                    break;
                case FRAGMENT_VIDEO:
//                    mVideoTabLayout.onDoubleClick();
                    break;
            }
        } else {
            firstClickTime = secondClickTime;
        }
    }

    /**
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_switch_night_mode:
                ToastUtils.showToastShort("点击切换主题");
                return false;

            case R.id.nav_setting:
                ToastUtils.showToastShort("点击设置");
                return false;

            case R.id.nav_share:
                ToastUtils.showToastShort("点击分享");
                return false;
        }
        return false;
    }
}
