package com.pushu_tech.sumpay.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.activities.BalanceActivity;
import com.pushu_tech.sumpay.activities.CardActivity;
import com.pushu_tech.sumpay.activities.CouponActivity;
import com.pushu_tech.sumpay.activities.SunCoinActivity;
import com.pushu_tech.sumpay.models.MeConfigItem;
import com.pushu_tech.sumpay.views.IconView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by virgil on 09/01/2018.
 */

public class MeFragment extends Fragment {

    private LinearLayout mSunCoinsLayout;
    private LinearLayout mCouponLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me, null);
        mSunCoinsLayout = (LinearLayout) view.findViewById(R.id.suncoinsLayout);
        mCouponLayout = (LinearLayout) view.findViewById(R.id.couponLayout);
        init();
        return view;
    }

    private void init(){
        mSunCoinsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SunCoinActivity.class));
            }
        });
        mCouponLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CouponActivity.class));
            }
        });
    }
}
