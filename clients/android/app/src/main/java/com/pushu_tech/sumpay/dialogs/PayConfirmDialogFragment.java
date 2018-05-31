package com.pushu_tech.sumpay.dialogs;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Layout;
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
import android.widget.Toast;

import com.fomopay.android.sdk.FOMOConsumer;
import com.fomopay.android.sdk.FOMOPay;
import com.fomopay.android.sdk.FOMOPayException;
import com.fomopay.android.sdk.FOMOPayOrder;
import com.fomopay.android.sdk.FOMOPayRequest;
import com.fomopay.android.sdk.FOMOPayResponse;
import com.fomopay.android.sdk.RandomString;
import com.fomopay.android.sdk.SignUtils;
import com.pushu_tech.sumpay.BaseApplication;
import com.pushu_tech.sumpay.MainActivity;
import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.views.IconView;

import java.util.UUID;

/**
 * Created by virgil on 12/01/2018.
 */

public class PayConfirmDialogFragment extends AppCompatDialogFragment {

    private static final String TAG = PayConfirmDialogFragment.class.getSimpleName();
    private  LinearLayout mPayConfirmLayout;
    private  LinearLayout mPayResultLayout;
    private IconView mCloseIconView;
    private Button mConfrimTransferButton;
    private Button mDoneButton;
    private TextView mPayAmountTextView;
    private TextView mPayAmountConfirmTextView;
    private TextView mRewardSunCoinTextView;
    private double mPayAmount;
    private AppCompatActivity mTransferActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        final View view = inflater.inflate(R.layout.pay_dialog, container);
        mPayConfirmLayout = view.findViewById(R.id.payConfirmDialog);
        mPayResultLayout = view.findViewById(R.id.payResultDialog);

        initPayDetail(view);
        mCloseIconView = (IconView) view.findViewById(R.id.closeIconView);
        mCloseIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownAnimation(view);
            }
        });
        mPayAmountTextView = (TextView) view.findViewById(R.id.payAmountTextView);
        mPayAmountTextView.setText("S$" + String.valueOf(mPayAmount));
        startUpAnimation(view);
        return view;
    }

    public void setPayAmount(double payAmount){
        mPayAmount = payAmount;
    }
    public void setTransferActivity(AppCompatActivity activity){
        mTransferActivity = activity;
    }

    private void initPayDetail(final View view) {
        mConfrimTransferButton = (Button) view.findViewById(R.id.payNowButton);
        mDoneButton = (Button) view.findViewById(R.id.doneButton);
        mPayAmountConfirmTextView = (TextView) view.findViewById(R.id.payAmountConfirmTextView);
        mRewardSunCoinTextView = (TextView) view.findViewById(R.id.rewardValueTextView);
        mConfrimTransferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FOMOPayOrder order = createPayOrder();
                FOMOPay.createOrder(mTransferActivity, order, new FOMOConsumer<String>() {
                    @Override
                    public void accept(String s) {
                        pay(s);
                    }
                }, new FOMOConsumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Log.e("---", "", throwable);

                        StringBuilder sb = new StringBuilder();
                        if (throwable instanceof FOMOPayException) {
                            sb.append("FOMOPay ErrorCode:")
                                    .append(((FOMOPayException) throwable).getErrorCode())
                                    .append(" ");
                        }
                        sb.append(throwable.getMessage());
                        Toast.makeText(getContext(), sb.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownAnimation(view);
                mTransferActivity.finish();
            }
        });
    }

    private void  paySuccess(){
        mPayConfirmLayout.setVisibility(View.GONE);
        mPayAmountConfirmTextView.setText(String.valueOf(mPayAmount));
        mRewardSunCoinTextView.setText("1.0");
        mPayResultLayout.setVisibility(View.VISIBLE);
    }

    private void pay(String paymentId) {
        FOMOPayRequest payRequest = new FOMOPayRequest();
        payRequest.payment_id = paymentId;
        payRequest.merchant = BaseApplication.FOMO_API_USERNAME;
        payRequest.nonce = new RandomString().nextString();
        payRequest.timestamp = String.valueOf(System.currentTimeMillis());
        payRequest.signature = SignUtils.sign(payRequest, BaseApplication.FOMO_API_SECRET);
        FOMOPay.pay(mTransferActivity, payRequest,
                new FOMOConsumer<FOMOPayResponse>() {
                    @Override
                    public void accept(FOMOPayResponse payResponse) {
                        Toast.makeText(getContext(), "Payment successful.", Toast.LENGTH_LONG).show();
                        paySuccess();
                    }
                },
                new FOMOConsumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Log.e("---", "", throwable);
                        StringBuilder sb = new StringBuilder();
                        if (throwable instanceof FOMOPayException) {
                            sb.append("FOMOPay ErrorCode:")
                                    .append(((FOMOPayException) throwable).getErrorCode())
                                    .append(" ");
                        }
                        sb.append(throwable.getMessage());
                        Toast.makeText(getContext(), sb.toString(),
                                Toast.LENGTH_LONG).show();
                        paySuccess();
                    }
                });
    }

    private FOMOPayOrder createPayOrder() {
        FOMOPayOrder order = new FOMOPayOrder();
        order.merchant = BaseApplication.FOMO_API_USERNAME;
        order.price = String.valueOf(mPayAmount);
        order.description = "description";
        order.transaction = "txnid-" + UUID.randomUUID().toString();
        order.callback_url = "http://callback.url";
        order.currency_code = "sgd";
        order.type = "sale";
        order.nonce = new RandomString().nextString();
        order.tag = "Lady M";
        order.upstreams = "netspay";
        order.signature = SignUtils.sign(order, BaseApplication.FOMO_API_SECRET);
        return order;
    }


    @Override
    public void onStart() {
        super.onStart();

        Log.i(TAG, "onStart: ");

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
