package com.pushu_tech.sumpay.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.pushu_tech.sumpay.R;

/**
 * Created by virgil on 19/01/2018.
 */

public class CouponActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        setActionbar(R.string.title_coupon);
    }
}
