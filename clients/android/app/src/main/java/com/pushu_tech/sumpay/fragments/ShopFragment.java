package com.pushu_tech.sumpay.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.activities.ScanActivity;
import com.pushu_tech.sumpay.activities.ShopActivity;
import com.pushu_tech.sumpay.views.IconView;

/**
 * Created by virgil on 01/03/2018.
 */

public class ShopFragment extends Fragment {

    LinearLayout homeshow1;
    LinearLayout homeshow2;
    LinearLayout homeshow3;
    LinearLayout homeshow4;
    LinearLayout homeshow5;
    LinearLayout homeshow6;
    IconView mScanIconView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, null);
        homeshow1 =(LinearLayout) view.findViewById(R.id.homeshow1);
        homeshow2 =(LinearLayout) view.findViewById(R.id.homeshow2);
        homeshow3 =(LinearLayout) view.findViewById(R.id.homeshow3);
        homeshow4 =(LinearLayout) view.findViewById(R.id.homeshow4);
        homeshow5 =(LinearLayout) view.findViewById(R.id.homeshow5);
        homeshow6 =(LinearLayout) view.findViewById(R.id.homeshow6);
        homeshow1.setOnClickListener(mClickListner);
        homeshow2.setOnClickListener(mClickListner);
        homeshow3.setOnClickListener(mClickListner);
        homeshow4.setOnClickListener(mClickListner);
        homeshow5.setOnClickListener(mClickListner);
        homeshow6.setOnClickListener(mClickListner);

        mScanIconView = (IconView)view.findViewById(R.id.scanIconView);
        mScanIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ScanActivity.class));
            }
        });
        return view;
    }

    private View.OnClickListener mClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getContext(), ShopActivity.class));
        }
    };
}
