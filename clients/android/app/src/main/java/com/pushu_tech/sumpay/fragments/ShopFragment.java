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
import com.pushu_tech.sumpay.activities.ScanActivity;
import com.pushu_tech.sumpay.activities.ShopActivity;
import com.pushu_tech.sumpay.views.IconView;

/**
 * Created by virgil on 01/03/2018.
 */

public class ShopFragment extends Fragment {

    private int[] navIcons = {
            R.drawable.cosmetic_normal,
            R.drawable.hotel_normal,
            R.drawable.travelling_normal,
            R.drawable.food_normal,
            R.drawable.drinks_normal
    };
    private int[] navLabels = {
            R.string.gift_page_cosmetic,
            R.string.gift_page_hotel,
            R.string.gift_page_travelling,
            R.string.gift_page_food,
            R.string.gift_page_drinks
    };
    // another resouces array for active state for the icon
    private int[] navIconsActive = {
            R.drawable.cosmetic_selected,
            R.drawable.hotel_selected,
            R.drawable.travelling_selected,
            R.drawable.food_selected,
            R.drawable.drinks_selected
    };

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
//        homeshow1 =(LinearLayout) view.findViewById(R.id.homeshow1);
//        homeshow2 =(LinearLayout) view.findViewById(R.id.homeshow2);
//        homeshow3 =(LinearLayout) view.findViewById(R.id.homeshow3);
//        homeshow4 =(LinearLayout) view.findViewById(R.id.homeshow4);
//        homeshow5 =(LinearLayout) view.findViewById(R.id.homeshow5);
//        homeshow6 =(LinearLayout) view.findViewById(R.id.homeshow6);
//        homeshow1.setOnClickListener(mClickListner);
//        homeshow2.setOnClickListener(mClickListner);
//        homeshow3.setOnClickListener(mClickListner);
//        homeshow4.setOnClickListener(mClickListner);
//        homeshow5.setOnClickListener(mClickListner);
//        homeshow6.setOnClickListener(mClickListner);


        this.setTabStyle(inflater, view);
        View scan = view.findViewById(R.id.scanIconView);
        scan.setOnClickListener(v -> startActivity(new Intent(getContext(), ScanActivity.class)));

        View shopOmega = view.findViewById(R.id.shop_omega);
        shopOmega.setOnClickListener(v -> startActivity(new Intent(getContext(), ShopActivity.class)));

        return view;
    }

    private void setTabStyle(LayoutInflater inflater, View view) {
        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.gift_tabLayout);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            // inflate the Parent LinearLayout Container for the tab
            // from the layout nav_tab.xml file that we created 'R.layout.nav_tab
            LinearLayout tab = (LinearLayout) inflater.inflate(R.layout.nav_tab, null);

            // get child TextView and ImageView from this layout for the icon and label
            TextView tab_label = (TextView) tab.findViewById(R.id.nav_label);
            ImageView tab_icon = (ImageView) tab.findViewById(R.id.nav_icon);

            // set the label text by getting the actual string value by its id
            // by getting the actual resource value `getResources().getString(string_id)`
            tab_label.setText(getResources().getString(navLabels[i]));

            // set the home to be active at first
            if(i == 0) {
                tab_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                tab_icon.setImageResource(navIconsActive[i]);
            } else {
                tab_icon.setImageResource(navIcons[i]);
            }

            // finally publish this custom view to navigation tab
            tabLayout.getTabAt(i).setCustomView(tab);
        }

        tabLayout.addOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {

                        // 1. get the custom View you've added
                        View tabView = tab.getCustomView();

                        // get inflated children Views the icon and the label by their id
                        TextView tab_label = (TextView) tabView.findViewById(R.id.nav_label);
                        ImageView tab_icon = (ImageView) tabView.findViewById(R.id.nav_icon);

                        // change the label color, by getting the color resource value
                        tab_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                        // change the image Resource
                        // i defined all icons in an array ordered in order of tabs appearances
                        // call tab.getPosition() to get active tab index.
                        tab_icon.setImageResource(navIconsActive[tab.getPosition()]);
                    }

                    // do as the above the opposite way to reset tab when state is changed
                    // as it not the active one any more
                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        View tabView = tab.getCustomView();
                        TextView tab_label = (TextView) tabView.findViewById(R.id.nav_label);
                        ImageView tab_icon = (ImageView) tabView.findViewById(R.id.nav_icon);

                        // back to the black color
                        tab_label.setTextColor(getResources().getColor(R.color.colorGray));
                        // and the icon resouce to the old black image
                        // also via array that holds the icon resources in order
                        // and get the one of this tab's position
                        tab_icon.setImageResource(navIcons[tab.getPosition()]);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                }
        );
    }

    private View.OnClickListener mClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getContext(), ShopActivity.class));
        }
    };
}
