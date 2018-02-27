package com.pushu_tech.sumpay.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.dialogs.PayPasswordDiagFragment;

/**
 * Created by virgil on 18/01/2018.
 */

public class ShopActivity extends BaseActivity {

    private Button mBillButton;
    private Button mGetButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        mBillButton = (Button) findViewById(R.id.billBtn);
        mGetButton = (Button) findViewById(R.id.getBtn);
        mBillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), TransferActivity.class));
            }
        });
        mGetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayPasswordDiagFragment diagFragment = new PayPasswordDiagFragment();
                diagFragment.show(getSupportFragmentManager(), "payPasswordDialog");
            }
        });

        setActionbar(R.string.empty_str);
    }
}