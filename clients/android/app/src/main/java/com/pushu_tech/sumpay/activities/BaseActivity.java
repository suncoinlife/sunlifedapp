package com.pushu_tech.sumpay.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.views.IconView;

/**
 * Created by virgil on 11/01/2018.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    protected void setActionbar(int titleRes) {
        final View actionBarLayout = (View) getLayoutInflater().inflate(
                R.layout.custom_action_bar,
                null);


        ViewGroup rootView = findViewById(android.R.id.content);

        // add the 'toolbar' and send it to back
        rootView.addView(actionBarLayout);

        final TextView titleView = findViewById(R.id.action_bar_title);
        titleView.setText(titleRes);

        final View back = findViewById(R.id.back);
        back.setOnClickListener(v -> finish());
    }

    // not working?
    protected void setActionBarTextColor(int colorRes) {
        TextView title = findViewById(R.id.action_bar_title);
        title.setTextColor(colorRes);

        TextView back = findViewById(R.id.tv_back);
        back.setTextColor(colorRes);

        IconView iv_back = findViewById(R.id.iv_back);
        iv_back.setTextColor(colorRes);
    }
}
