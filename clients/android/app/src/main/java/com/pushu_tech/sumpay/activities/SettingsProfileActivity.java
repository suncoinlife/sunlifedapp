package com.pushu_tech.sumpay.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.pushu_tech.sumpay.MainActivity;
import com.pushu_tech.sumpay.R;

public class SettingsProfileActivity extends BaseActivity {
    boolean isFromSettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_topic);

        isFromSettings = getIntent().getBooleanExtra("isFromSettings", true);
        View next = findViewById(R.id.next);
        View btnMore = findViewById(R.id.btn_more_settings);
        if (isFromSettings) {
            // no action bar for first login...
            this.setActionbar(R.string.empty_str);
            next.setVisibility(View.INVISIBLE);
        } else {
            next.setVisibility(View.VISIBLE);
            Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
            homeIntent.putExtra("isLogin", true);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            next.setOnClickListener(v -> startActivity(homeIntent));
        }

        Intent moreIntent = new Intent(this, SettingsProfileMoreActivity.class);
        moreIntent.putExtra("isFromSettings", isFromSettings);
        btnMore.setOnClickListener(v -> startActivity(moreIntent));
    }

    @Override
    public void onBackPressed() {
        if (isFromSettings) {
            super.onBackPressed();
        } else {
            // ???
        }
    }
}
