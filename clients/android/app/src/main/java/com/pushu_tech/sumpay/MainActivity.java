package com.pushu_tech.sumpay;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.support.design.widget.BottomNavigationView;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.pushu_tech.sumpay.activities.LoginActivity;
import com.pushu_tech.sumpay.fragments.GiftFragment;
import com.pushu_tech.sumpay.fragments.HomeFragment;
import com.pushu_tech.sumpay.fragments.MeFragment;
import com.pushu_tech.sumpay.fragments.ShopFragment;
import com.pushu_tech.sumpay.fragments.TaskFragment;
import com.pushu_tech.sumpay.utils.BottomNavigationViewHelper;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private MenuItem mMenuItem;
    private View badge;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_task:
                    //viewPager.setCurrentItem(0);
                    setItemUnselected(1);
                    setItemUnselected(2);
                    setItemUnselected(3);
                    setItemSelected(0);
                    return true;
                case R.id.navigation_gift:
                    //viewPager.setCurrentItem(1);
                    setItemUnselected(0);
                    setItemUnselected(2);
                    setItemUnselected(3);
                    setItemSelected(1);
                    return true;
                case R.id.navigation_shop:
                    //viewPager.setCurrentItem(2);
                    setItemUnselected(0);
                    setItemUnselected(1);
                    setItemUnselected(3);
                    setItemSelected(2);
                    return true;
                case R.id.navigation_me:
                    //viewPager.setCurrentItem(3);
                    setItemUnselected(0);
                    setItemUnselected(1);
                    setItemUnselected(2);
                    setItemSelected(3);
                    return true;
            }
            return false;
        }
    };

    private ViewPager.OnPageChangeListener mOnpageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (mMenuItem != null) {
                mMenuItem.setChecked(false);
            } else {
              bottomNavigationView.getMenu().getItem(0).setChecked(false);
            }
            mMenuItem = bottomNavigationView.getMenu().getItem(position);
            mMenuItem.setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(mOnpageChangeListener);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        badge = getLayoutInflater().inflate(R.layout.frame_bottom_navigation_bar_selected, null);
        setItemSelected(0);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        setupViewPager();

        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestCodeQRCodePermissions();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
    }

    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQRCodePermissions() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        }
    }

    private void setupViewPager() {
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());

        adapter.addFragment(new TaskFragment());
        adapter.addFragment(new GiftFragment());
        adapter.addFragment(new ShopFragment());
        adapter.addFragment(new MeFragment());

        viewPager.setAdapter(adapter);
    }

    private void setItemSelected(int index) {
        viewPager.setCurrentItem(index);
        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(index); 
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;
        if (badge.getParent() == null) {
            itemView.addView(badge);
            badge.setTag(index);
        }
    }

    private void setItemUnselected(int index) {
        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        View v = bottomNavigationMenuView.getChildAt(index); 
        BottomNavigationItemView itemView = (BottomNavigationItemView) v;
        int childCount = itemView.getChildCount();
        View lastView = itemView.getChildAt(childCount - 1);
        Object tag = lastView.getTag();
        if (tag != null) {
            int nTag = (Integer) lastView.getTag();
    
            if (nTag == index) {
                itemView.removeViewAt(itemView.getChildCount() - 1);
            }
        }
    }
}
