package com.pushu_tech.sumpay.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.activities.GiftActivity;
import com.pushu_tech.sumpay.activities.ScanActivity;
import com.pushu_tech.sumpay.views.IconView;

/**
 * Created by virgil on 01/03/2018.
 */

public class GiftFragment extends Fragment {


    IconView mScanIconView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gift, null);

//        Intent intent = new Intent(getContext(), GiftActivity.class);
//        View headset = view.findViewById(R.id.gift_headset);
//        headset.setOnClickListener(v -> startActivity(intent));


        mScanIconView = (IconView)view.findViewById(R.id.scanIconView);
        mScanIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ScanActivity.class));
            }
        });

        return view;
    }
}
