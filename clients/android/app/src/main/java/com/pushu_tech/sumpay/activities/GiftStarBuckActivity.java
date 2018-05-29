package com.pushu_tech.sumpay.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.pushu_tech.sumpay.R;

public class GiftStarBuckActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_starbucks);

        Button button = (Button) findViewById(R.id.gift_pay_button);
        //button.setOnClickListener(v -> new PayPasswordDiagFragment().show(getSupportFragmentManager(), "payPasswordDialog"));
        button.setOnClickListener(v->{
            startActivity(new Intent(this, GiftCouponOrderConfirmActivity.class));
        });

        setActionbar(R.string.title_goods_starbucs);
    }
}
