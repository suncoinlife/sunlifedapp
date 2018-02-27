package com.pushu_tech.sumpay.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.pushu_tech.sumpay.R;

/**
 * Created by virgil on 11/01/2018.
 */

public class BalanceActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        setActionbar(R.string.title_balance);
    }
}
