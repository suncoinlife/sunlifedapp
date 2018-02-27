package com.pushu_tech.sumpay.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.activities.CollectActivity;
import com.pushu_tech.sumpay.activities.PayActivity;
import com.pushu_tech.sumpay.activities.ScanActivity;
import com.pushu_tech.sumpay.activities.ShopActivity;
import com.pushu_tech.sumpay.activities.TransferActivity;
import com.pushu_tech.sumpay.adapters.ShopItemAdapter;
import com.pushu_tech.sumpay.models.ShopItem;
import com.pushu_tech.sumpay.utils.IntentResultCodes;
import com.pushu_tech.sumpay.views.IconView;

import java.util.ArrayList;

/**
 * Created by virgil on 09/01/2018.
 */

public class HomeFragment extends Fragment {

    private final static int REQUST_CODE_QRSCAN = 1;
    private IconView mScanIconView;
    private LinearLayout mShopItemLinearLayout1;
    private LinearLayout mShopItemLinearLayout2;
    private LinearLayout mShopItemLinearLayout3;
    private LinearLayout mShopItemLinearLayout4;
    private LinearLayout mShopItemLinearLayout5;
    private LinearLayout mShopItemLinearLayout6;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, null);
        mScanIconView = (IconView)view.findViewById(R.id.scanIconView);
        mShopItemLinearLayout1 = (LinearLayout)view.findViewById(R.id.homeshow1);
        mShopItemLinearLayout2 = (LinearLayout)view.findViewById(R.id.homeshow2);
        mShopItemLinearLayout3 = (LinearLayout)view.findViewById(R.id.homeshow3);
        mShopItemLinearLayout4 = (LinearLayout)view.findViewById(R.id.homeshow4);
        mShopItemLinearLayout5 = (LinearLayout)view.findViewById(R.id.homeshow5);
        mShopItemLinearLayout6 = (LinearLayout)view.findViewById(R.id.homeshow6);
        init();
        return  view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getContext(), ShopActivity.class));
        }
    };

    private void init(){
        mScanIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getContext(), ScanActivity.class), REQUST_CODE_QRSCAN);
            }
        });
        mShopItemLinearLayout1.setOnClickListener(clickListener);
        mShopItemLinearLayout2.setOnClickListener(clickListener);
        mShopItemLinearLayout3.setOnClickListener(clickListener);
        mShopItemLinearLayout4.setOnClickListener(clickListener);
        mShopItemLinearLayout5.setOnClickListener(clickListener);
        mShopItemLinearLayout6.setOnClickListener(clickListener);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUST_CODE_QRSCAN && resultCode == IntentResultCodes.INTENT_RESULT_CODE_SUCCESS) {
            String qrCode = data.getStringExtra(ScanActivity.INTENT_EXTRA_QRCODE_RESULT);
            startActivity(new Intent(getContext(), TransferActivity.class));
        }
    }
}
