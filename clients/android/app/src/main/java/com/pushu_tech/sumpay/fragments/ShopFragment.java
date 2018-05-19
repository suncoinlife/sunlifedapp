package com.pushu_tech.sumpay.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

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

        TabLayout tableLayout = (TabLayout) view.findViewById(R.id.shop_tableLayout);
        for (int i = 0; i < tableLayout.getTabCount(); i++) {

            TabLayout.Tab tab = tableLayout.getTabAt(i);
            if (tab != null) {

                TextView tabTextView = new TextView(getContext());
                tab.setCustomView(tabTextView);

                tabTextView.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
                tabTextView.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;

                int pixels = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
                tabTextView.setPadding(pixels, 0, pixels, 0);

                tabTextView.setText(tab.getText());

                // First tab is the selected tab, so if i==0 then set BOLD typeface
                if (i == 0) {
                    tabTextView.setTypeface(null, Typeface.BOLD);
                    tabTextView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    tabTextView.setTextColor(getResources().getColor(R.color.colorWhite));
                }

            }

        }

        tableLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //viewPager.setCurrentItem(tab.getPosition());

                TextView text = (TextView) tab.getCustomView();
                text.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                text.setTextColor(getResources().getColor(R.color.colorWhite));

                text.setTypeface(null, Typeface.BOLD);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView text = (TextView) tab.getCustomView();
                text.setBackgroundColor(Color.TRANSPARENT);
                text.setTextColor(getResources().getColor(R.color.colorDark));

                text.setTypeface(null, Typeface.NORMAL);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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
