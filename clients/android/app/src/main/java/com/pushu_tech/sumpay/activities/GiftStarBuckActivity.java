package com.pushu_tech.sumpay.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.pushu_tech.sumpay.R;

public class GiftStarBuckActivity extends BaseActivity {

    static int reqCode_buy = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_starbucks);

        Button button = (Button) findViewById(R.id.gift_pay_button);
        //button.setOnClickListener(v -> new PayPasswordDiagFragment().show(getSupportFragmentManager(), "payPasswordDialog"));
        button.setOnClickListener(v->{
            startActivityForResult(new Intent(this, GiftCouponOrderConfirmActivity.class), reqCode_buy);
        });

        setActionbar(R.string.title_goods_starbucs);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == reqCode_buy) {
            if (resultCode == 1) {
                startActivity(new Intent(this, CouponDetailActivity.class));
            }
        }
    }
}
