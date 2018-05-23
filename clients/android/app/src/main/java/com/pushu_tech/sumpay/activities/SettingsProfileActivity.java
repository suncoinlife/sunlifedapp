package com.pushu_tech.sumpay.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pushu_tech.sumpay.R;

public class SettingsProfileActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_topic);

        boolean isFromSettings = getIntent().getBooleanExtra("isFromSettings", true);
        if (isFromSettings) {
            // no action bar for first login...
            this.setActionbar(R.string.empty_str);
        }
    }
}
