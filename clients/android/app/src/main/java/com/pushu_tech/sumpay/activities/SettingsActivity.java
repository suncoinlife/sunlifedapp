package com.pushu_tech.sumpay.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import com.pushu_tech.sumpay.R;

public class SettingsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setActionbar(R.string.title_settings);

        LinearLayout profileSettings = (LinearLayout) findViewById(R.id.settings_profile);
        profileSettings.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SettingsProfileActivity.class)));
    }
}
