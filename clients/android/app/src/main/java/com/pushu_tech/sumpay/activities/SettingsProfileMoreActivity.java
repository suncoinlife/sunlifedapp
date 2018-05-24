package com.pushu_tech.sumpay.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.pushu_tech.sumpay.MainActivity;
import com.pushu_tech.sumpay.R;

public class SettingsProfileMoreActivity extends BaseActivity {
    boolean isFromSettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_topic_more);

        isFromSettings = getIntent().getBooleanExtra("isFromSettings", true);
        View next = findViewById(R.id.btn_next);
        View prev = findViewById(R.id.btn_prev);
        if (isFromSettings) {
            // no action bar for first login...
            this.setActionbar(R.string.empty_str);
            next.setVisibility(View.INVISIBLE);
            prev.setVisibility(View.INVISIBLE);

        } else {
            next.setVisibility(View.VISIBLE);
            Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
            homeIntent.putExtra("isLogin", true);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            next.setOnClickListener(v -> startActivity(homeIntent));
            prev.setOnClickListener(v -> finish());
        }

    }
}
