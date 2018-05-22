package com.pushu_tech.sumpay.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.pushu_tech.sumpay.R;

public class RegisterActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnReg = (Button) findViewById(R.id.btn_register);
        btnReg.setOnClickListener(t -> finish());

        setActionbar(R.string.register_title);
        //setActionBarTextColor(R.color.colorWhite);
    }
}
