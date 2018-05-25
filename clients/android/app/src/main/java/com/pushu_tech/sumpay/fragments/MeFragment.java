package com.pushu_tech.sumpay.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.activities.CouponActivity;
import com.pushu_tech.sumpay.activities.SettingsActivity;
import com.pushu_tech.sumpay.activities.SunCoinActivity;

/**
 * Created by virgil on 09/01/2018.
 */

public class MeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);

        View settings = view.findViewById(R.id.me_btn_settings);
        settings.setOnClickListener(v -> startActivity(new Intent(getContext(), SettingsActivity.class)));
        return view;
    }
}
