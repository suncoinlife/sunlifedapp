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
import android.widget.TextView;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.activities.CouponActivity;
import com.pushu_tech.sumpay.activities.CouponDetailActivity;
import com.pushu_tech.sumpay.activities.DeliverDetailActivity;
import com.pushu_tech.sumpay.activities.LoginActivity;
import com.pushu_tech.sumpay.activities.SettingsActivity;
import com.pushu_tech.sumpay.activities.SunCoinActivity;
import com.pushu_tech.sumpay.activities.TransferActivity;
import com.pushu_tech.sumpay.utils.SageHelper;

/**
 * Created by virgil on 09/01/2018.
 */

public class MeFragment extends Fragment {

    static int reqCode_Settings = 100;
    TextView mTotalSagesTextView;
    CardView mStarbucksBillCardView;
    CardView mHBCComapanyCardView;
    CardView mStarbucksGouponCardView;
    CardView mTeslaAdverCardView;
    CardView mSageOfficalAdverCardView;
    TextView mBillPayAmountTextView;
    TextView mBillRewardAmountTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);

        mBillPayAmountTextView = view.findViewById(R.id.billPayAmount);
        mBillRewardAmountTextView = view.findViewById(R.id.billRewardAmount);

        View settings = view.findViewById(R.id.me_btn_settings);
        settings.setOnClickListener(v -> startActivityForResult(new Intent(getContext(), SettingsActivity.class), reqCode_Settings));

        CardView starbucksCardView = view.findViewById(R.id.starbucksCoupon);
        starbucksCardView.setOnClickListener(view1 -> startActivity(new Intent(getContext(), CouponDetailActivity.class)));

        CardView sonyCardView = view.findViewById(R.id.sonyDetail);
        sonyCardView.setOnClickListener(view1 -> startActivity(new Intent(getContext(), DeliverDetailActivity.class)));

        mTotalSagesTextView = view.findViewById(R.id.totalSages);
        mStarbucksBillCardView = view.findViewById(R.id.starbucksBill);
        mStarbucksGouponCardView = view.findViewById(R.id.starbucksCoupon);
        mHBCComapanyCardView = view.findViewById(R.id.hbcCompanyCoupon);
        mTeslaAdverCardView = view.findViewById(R.id.telsaAdver);
        mSageOfficalAdverCardView = view.findViewById(R.id.sonyDetail);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mTotalSagesTextView.setText(Float.toString(SageHelper.GetTotalSages(this.getContext())));
        if(SageHelper.GetRecord(this.getContext(), "StarBuckCoupon")){
            mStarbucksGouponCardView.setVisibility(View.VISIBLE);
        }
        if(SageHelper.GetRecord(this.getContext(), "sonyGift")){
            mSageOfficalAdverCardView.setVisibility(View.VISIBLE);
        }
        if(SageHelper.GetRecord(this.getContext(),"HBCTask")){
            mHBCComapanyCardView.setVisibility(View.VISIBLE);
        }
        if(SageHelper.GetRecord(this.getContext(), "TeslaTask")){
            mTeslaAdverCardView.setVisibility(View.VISIBLE);
        }
        if(SageHelper.GetRecord(this.getContext(), "starbucksBill")){
            mStarbucksBillCardView.setVisibility(View.VISIBLE);
            float amount = SageHelper.GetFloatRecord(this.getContext(), "billAmount");
            mBillPayAmountTextView.setText("Amount: $S " + Float.toString(amount));
            mBillRewardAmountTextView.setText("Reward SAGE: " + Float.toString(amount));
        }
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
