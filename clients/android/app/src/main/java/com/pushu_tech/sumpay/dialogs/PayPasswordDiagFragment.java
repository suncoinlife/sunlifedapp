package com.pushu_tech.sumpay.dialogs;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.views.IconView;

/**
 * Created by virgil on 19/01/2018.
 */

public class PayPasswordDiagFragment extends AppCompatDialogFragment {
    private Button mDoneButton;
    private IconView mCloseIconView;
    private TextView mPwd1TextView;
    private TextView mPwd2TextView;
    private TextView mPwd3TextView;
    private TextView mPwd4TextView;
    private TextView mPwd5TextView;
    private TextView mPwd6TextView;
    private TextView mNumber1TextView;
    private TextView mNumber2TextView;
    private TextView mNumber3TextView;
    private TextView mNumber4TextView;
    private TextView mNumber5TextView;
    private TextView mNumber6TextView;
    private TextView mNumber7TextView;
    private TextView mNumber8TextView;
    private TextView mNumber9TextView;
    private TextView mNumber0TextView;
    private IconView mDeleteIconView;
    private int mPwdLength;
    private LinearLayout mPayPwdDialog;
    private LinearLayout mPayResultDialog;
    private TextView mPayItemTextView;
    private TextView mPayUniteTextView;
    private TextView mPayAmountConfirmTextView;
    private TextView mRewardNameTextView;
    private TextView mRewardKeyTextView;
    private TextView mRewardValueTextView;
    private TextView mPayTotalPriceTextView;
    private int mPayTotalPrice;
    private int mPayRewardSage;
    private String mPayType;
    private String mPayItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        final View view = inflater.inflate(R.layout.pay_sc_dialog, container);
        mDoneButton = (Button) view.findViewById(R.id.doneButton);
        mPayItemTextView = (TextView) view.findViewById(R.id.itemTextView);
        mPayUniteTextView = (TextView) view.findViewById(R.id.payUnitTextView);
        mPayAmountConfirmTextView = (TextView) view.findViewById(R.id.payAmountConfirmTextView);
        mRewardNameTextView = (TextView) view.findViewById(R.id.rewardNameTextView);
        mRewardKeyTextView = (TextView) view.findViewById(R.id.rewardKeyTextView);
        mRewardValueTextView = (TextView) view.findViewById(R.id.rewardValueTextView);
        mPayPwdDialog = (LinearLayout) view.findViewById(R.id.payPwdDialog);
        mPayResultDialog = (LinearLayout) view.findViewById(R.id.payResultDialog);
        mCloseIconView = (IconView) view.findViewById(R.id.closeIconView);
        mPwd1TextView = (TextView) view.findViewById(R.id.pwd1TextView);
        mPwd2TextView = (TextView) view.findViewById(R.id.pwd2TextView);
        mPwd3TextView = (TextView) view.findViewById(R.id.pwd3TextView);
        mPwd4TextView = (TextView) view.findViewById(R.id.pwd4TextView);
        mPwd5TextView = (TextView) view.findViewById(R.id.pwd5TextView);
        mPwd6TextView = (TextView) view.findViewById(R.id.pwd6TextView);
        mNumber1TextView = (TextView) view.findViewById(R.id.oneTextView);
        mNumber2TextView = (TextView) view.findViewById(R.id.twoTextView);
        mNumber3TextView = (TextView) view.findViewById(R.id.threeTextView);
        mNumber4TextView = (TextView) view.findViewById(R.id.fourTextView);
        mNumber5TextView = (TextView) view.findViewById(R.id.fiveTextView);
        mNumber6TextView = (TextView) view.findViewById(R.id.sixTextView);
        mNumber7TextView = (TextView) view.findViewById(R.id.sevenTextView);
        mNumber8TextView = (TextView) view.findViewById(R.id.eightTextView);
        mNumber9TextView = (TextView) view.findViewById(R.id.nineTextView);
        mNumber0TextView = (TextView) view.findViewById(R.id.zeroTextView);
        mDeleteIconView = (IconView)  view.findViewById(R.id.backTextView);
        mPayTotalPriceTextView = (TextView) view.findViewById(R.id.payTotoalPrice);
        mPwdLength = 0;
        init(view);
        return view;
    }

    private void init(final View view) {
        mCloseIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownAnimation(view);
            }
        });
        mNumber1TextView.setOnClickListener(numberClickListener);
        mNumber2TextView.setOnClickListener(numberClickListener);
        mNumber3TextView.setOnClickListener(numberClickListener);
        mNumber4TextView.setOnClickListener(numberClickListener);
        mNumber5TextView.setOnClickListener(numberClickListener);
        mNumber6TextView.setOnClickListener(numberClickListener);
        mNumber7TextView.setOnClickListener(numberClickListener);
        mNumber8TextView.setOnClickListener(numberClickListener);
        mNumber9TextView.setOnClickListener(numberClickListener);
        mNumber0TextView.setOnClickListener(numberClickListener);

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownAnimation(view);
            }
        });

        mDeleteIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPwdLength == 0){
                    return;
                }
                mPwdLength -= 1;
                switch (mPwdLength){
                    case  0:
                        mPwd1TextView.setText("");
                        break;
                    case 1:
                        mPwd2TextView.setText("");
                        break;
                    case 2:
                        mPwd3TextView.setText("");
                        break;
                    case 3:
                        mPwd4TextView.setText("");
                        break;
                    case 4:
                        mPwd5TextView.setText("");
                        break;
                    case 5:
                        mPwd6TextView.setText("");
                        break;
                    default:
                        break;
                }
            }
        });

        Bundle bundle = this.getArguments();
        mPayTotalPrice = bundle.getInt("payTotalPrice");
        mPayRewardSage = bundle.getInt("payRewardSage");
        mPayType = bundle.getString("payType");
        mPayItem = bundle.getString("payItem");
        if(mPayType == "SAGE"){
            mPayTotalPriceTextView.setText("$SG" + Integer.toString(mPayTotalPrice));
            mPayUniteTextView.setText("$SG");
        } else {
            mPayTotalPriceTextView.setText("S$" + Integer.toString(mPayTotalPrice));
            mPayUniteTextView.setText("S$");
        }
        mPayAmountConfirmTextView.setText(Integer.toString(mPayTotalPrice));
        mRewardValueTextView.setText(Integer.toString(mPayRewardSage));
        mPayItemTextView.setText(mPayItem);

    }

    private View.OnClickListener numberClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mPwdLength == 6){
                return;
            } else {
                mPwdLength += 1;
                switch (mPwdLength){
                    case 1:
                        mPwd1TextView.setText("*");
                        break;
                    case 2:
                        mPwd2TextView.setText("*");
                        break;
                    case 3:
                        mPwd3TextView.setText("*");
                        break;
                    case 4:
                        mPwd4TextView.setText("*");
                        break;
                    case 5:
                        mPwd5TextView.setText("*");
                        break;
                    case 6:
                        mPwd6TextView.setText("*");
                        verifyPwd();
                    default:
                            break;
                }
            }
        }
    };

    private void verifyPwd(){
        mPayPwdDialog.setVisibility(View.GONE);
        mPayResultDialog.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM; // 显示在底部
        params.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度填充满屏
        window.setAttributes(params);

        // 这里用透明颜色替换掉系统自带背景
        int color = ContextCompat.getColor(getActivity(), android.R.color.transparent);
        window.setBackgroundDrawable(new ColorDrawable(color));
    }

    private void startUpAnimation(View view) {
        Animation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);

        slide.setDuration(400);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        view.startAnimation(slide);
    }

    private void startDownAnimation(View view) {
        Animation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 1.0f);

        slide.setDuration(400);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        slide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dismiss();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(slide);
    }
}
