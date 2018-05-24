package com.pushu_tech.sumpay.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.pushu_tech.sumpay.MainActivity;
import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.dialogs.PayPasswordDiagFragment;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        Button btnLogin = (Button) findViewById(R.id.btn_login);
        Intent profileIntent = new Intent(getApplicationContext(), SettingsProfileActivity.class);
        profileIntent.putExtra("isFromSettings", false);
        btnLogin.setOnClickListener(t -> startActivity(profileIntent));

        Button btnRegister = (Button) findViewById(R.id.btn_register);
        Intent r = new Intent(this, RegisterActivity.class);
        btnRegister.setOnClickListener(t -> startActivity(r));
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
    }
}
