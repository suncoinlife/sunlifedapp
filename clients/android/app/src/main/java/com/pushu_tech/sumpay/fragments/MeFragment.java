package com.pushu_tech.sumpay.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.activities.CouponActivity;
import com.pushu_tech.sumpay.activities.CouponDetailActivity;
import com.pushu_tech.sumpay.activities.DeliverDetailActivity;
import com.pushu_tech.sumpay.activities.LoginActivity;
import com.pushu_tech.sumpay.activities.SettingsActivity;
import com.pushu_tech.sumpay.activities.SunCoinActivity;
import com.pushu_tech.sumpay.activities.TransferActivity;

/**
 * Created by virgil on 09/01/2018.
 */

public class MeFragment extends Fragment {

    static int reqCode_Settings = 100;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);

        View settings = view.findViewById(R.id.me_btn_settings);
        settings.setOnClickListener(v -> startActivityForResult(new Intent(getContext(), SettingsActivity.class), reqCode_Settings));

        CardView starbucksCardView = view.findViewById(R.id.starbucksCoupon);
        starbucksCardView.setOnClickListener(view1 -> startActivity(new Intent(getContext(), CouponDetailActivity.class)));

        CardView sonyCardView = view.findViewById(R.id.sonyDetail);
        sonyCardView.setOnClickListener(view1 -> startActivity(new Intent(getContext(), DeliverDetailActivity.class)));
        return view;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == reqCode_Settings) {
            if (resultCode == 1) {
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        }
    }
}
