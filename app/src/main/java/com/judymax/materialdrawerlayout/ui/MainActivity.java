package com.judymax.materialdrawerlayout.ui;

import android.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.judymax.materialdrawerlayout.R;
import com.judymax.materialdrawerlayout.ui.fragment.Fragment1;
import com.judymax.materialdrawerlayout.ui.fragment.Fragment2;
import com.judymax.materialdrawerlayout.ui.fragment.Fragment3;
import com.judymax.materialdrawerlayout.ui.fragment.NavigationDrawerFragment;


public class MainActivity extends ActionBarActivity implements
        NavigationDrawerFragment.NavigationDrawerCallbacks{

    protected Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationDrawerFragment mNavigationDrawerFragment;

    public ActionBarHelper mActionBar;

    private int mCurrent;

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        // 出现 Title 左边的小箭头
        mActionBar = new ActionBarHelper();
        mActionBar.init();

        setUpDrawer();

        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
                .findFragmentById(R.id.navigation_drawer);

        mCurrent = 0;
    }

    private void setUpDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(new MyDrawerListener());
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);

        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(Gravity.LEFT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!isDrawerOpen()) {
            return true;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 没有这三行点击 Toggle 不弹出 Drawer
        if(mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onNavigationDrawerItemSelected(int position) {
        mCurrent = position;

        try {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } catch (NullPointerException e) {}

        FragmentManager fragmentManager = getFragmentManager();

        switch (position) {
            case 0:
                if (fragment1 == null) {
                    fragment1 = new Fragment1();
                }
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.container, fragment1)
                        .commit();
                setTitle("Fragment1");
                return true;
            case 1:
                if (fragment2 == null) {
                    fragment2 = new Fragment2();
                }
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.container, fragment2)
                        .commit();
                setTitle("Fragment2");
                return true;
            case 2:
                if (fragment3 == null) {
                    fragment3 = new Fragment3();
                }
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.container, fragment3)
                        .commit();
                setTitle("Fragment3");
                return true;
            case 3:
                // settings
                return false;
        }
        return true;
    }

    private class MyDrawerListener implements DrawerLayout.DrawerListener {

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            mDrawerToggle.onDrawerOpened(drawerView);
            mActionBar.onDrawerOpened();
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            mDrawerToggle.onDrawerClosed(drawerView);
            mActionBar.onDrawerClosed();
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            mDrawerToggle.onDrawerStateChanged(newState);
        }
    }

    private class ActionBarHelper {
        private final ActionBar mActionBar;
        private CharSequence mDrawerTitle;
        private CharSequence mTitle;

        ActionBarHelper() {
            mActionBar = getSupportActionBar();
        }

        public void init() {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setDisplayShowHomeEnabled(false);
            mTitle = mDrawerTitle = getTitle();
        }

        public void onDrawerClosed() {
            mActionBar.setTitle(mTitle);
        }

        public void onDrawerOpened() {
            mActionBar.setTitle(mDrawerTitle);
        }
    }
}
