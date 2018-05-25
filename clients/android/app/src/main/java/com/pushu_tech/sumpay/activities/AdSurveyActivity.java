package com.pushu_tech.sumpay.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.dialogs.CoinPopWindow;
import com.pushu_tech.sumpay.mock.DataProvider;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class AdSurveyActivity extends BaseActivity {

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);
    private Random random = new Random();
    private double point;
    private Map<Integer, Pair<Integer, Integer>> coins;
    private ScheduledFuture future;
    private PopupWindow popupWindow;
    private FrameLayout amountContainer;
    TextView sageText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_survey);
        point = getIntent().getDoubleExtra("points", 0);
        coins = this.splitCoin((int)DataProvider.getInstance ().getUserBalance(), (int)point, 10);
        setActionbar(R.string.title_survey, R.string.action_submit, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prize();
            }
        });
    }

    private void prize() {
        // prize
        Log.d("AdSurveyActivity", "Survey end, need to give prize to user");

        LayoutInflater layoutInflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.moving_coin_view, findViewById(R.id.popup_prize));
        popupWindow = new PopupWindow(popupView, -1, -1, true);
        sageText = popupView.findViewById(R.id.total_amount);
        sageText.setText("" + (int)DataProvider.getInstance().getUserBalance());
        amountContainer = popupView.findViewById(R.id.amount_container);
        //int[] location = new int[2];
        //sageText.getLocationOnScreen(location);
        //popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorMask)));
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.clear));
        popupWindow.setAnimationStyle(R.style.popup_animation);
        //popupWindow.showAtLocation(findViewById(R.id.activity_ad_survey), Gravity.NO_GRAVITY, location[0] / 2, location[1] - sageText.getMeasuredHeight() / 2);
        popupWindow.showAtLocation(findViewById(R.id.activity_ad_survey), Gravity.FILL, 0, 0);

        startTimer();


//        CoinPopWindow popupWindow = new CoinPopWindow(this, (int)point);

        //LayoutInflater layoutInflater = (LayoutInflater) this
        //        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View popupView = layoutInflater.inflate(R.layout.popup_prize, findViewById(R.id.popup_prize));
        //PopupWindow popupWindow = new PopupWindow(this);
        //popupWindow.setContentView(popupView);
        //TextView textView = (TextView) popupView.findViewById(R.id.popup_prize_count);
        //textView.setText("+" + point);


//        DataProvider.getInstance().addBalanceChange("Telsela", new Date(), point);
//        popupWindow.showAtLocation(findViewById(R.id.activity_ad_survey), Gravity.CENTER, 0, 0);
//        popupWindow.startAnimation();


//        CountDownTimer timer = new CountDownTimer(1000, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//
//            }
//
//            @Override
//            public void onFinish() {
//                popupWindow.dismiss();
//                finish();
//            }
//        };
//        timer.start();
    }

    class _CountWrapper {
        int count;

        public _CountWrapper(int count) {
            this.count = count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCount() {
            return this.count;
        }
    }

    private Map<Integer, Pair<Integer, Integer>> splitCoin(int startCoin, int coin, int part) {
        if (coin < part) {
            // shit
        } else {
            final _CountWrapper coinLeft = new _CountWrapper(coin);
            final _CountWrapper sum = new _CountWrapper(startCoin);
            return new HashMap<Integer, Pair<Integer, Integer>>() {{
                for (int i = 0; i < part; i++) {
                    int randomCoin = random.nextInt(coinLeft.getCount() / (part - i));
                    int additionalCoin = i < part - 1 ? randomCoin : coinLeft.getCount();
                    sum.setCount(sum.getCount() + additionalCoin);
                    put(i, new Pair<>(sum.getCount(), additionalCoin));
                    coinLeft.setCount(coinLeft.getCount() - randomCoin);
                }
            }};
        }

        return new HashMap<Integer, Pair<Integer, Integer>>() {{ put(0, new Pair<>(coin, 0)); }};
    }

    protected void startTimer() {
        final _CountWrapper countWrapper = new _CountWrapper(35);
        future = scheduler.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                countWrapper.setCount(countWrapper.getCount() - 1); //increase every sec
                Log.d("AdSurveyActivity", "we're now " + countWrapper.getCount());
                if (countWrapper.getCount() <= 1 && future != null) {
                    future.cancel(true);
                    popupWindow.dismiss();
                }
                if (countWrapper.getCount() <= 30 && countWrapper.getCount() > 20) {
                    mHandler.obtainMessage(30 - countWrapper.getCount()).sendToTarget();
                }
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
    }

    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            //coinUp();
            Log.d("AdSurveyActivity", "biu");
            coinUp2(coins.get(msg.what).second);
            sageText.setText("" + coins.get(msg.what).first);
        }
    };

    private void coinUp() {
        float originSize = sageText.getTextSize();
        Log.d("AdSurveyActivity", "changing size from " + originSize + " to " + (originSize + dpToFloat(2)));
        ValueAnimator animator = ValueAnimator.ofFloat(originSize, originSize + dpToFloat(2));
        animator.setDuration(20);

        animator.addUpdateListener(valueAnimator -> {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                sageText.setTextSize(animatedValue);
            });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                // set back

                Log.d("AdSurveyActivity", "changing size from " + sageText.getTextSize() + " to " + originSize);
                ValueAnimator animator2 = ValueAnimator.ofFloat(sageText.getTextSize(), originSize);
                animator2.setDuration(20);

                animator2.addUpdateListener(valueAnimator -> {
                    float animatedValue = (float) valueAnimator.getAnimatedValue();
                    sageText.setTextSize(animatedValue);
                });
                animator2.start();
            }
        });
        animator.start();
    }

    private void coinUp2(int additionalCoin) {
        TextView tv = new TextView(this);
        tv.setText("+" + additionalCoin);
        tv.setTextColor(getResources().getColor(R.color.colorZText));
        tv.setTextSize(15);
//        AnimationSet as = new AnimationSet(true);
//        as.setFillEnabled(true);
//        as.setInterpolator(new BounceInterpolator());

        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(tv, "alpha", 1.0f, 0.0f);
        alphaAnim.setDuration(300);
        amountContainer.addView(tv);
        FrameLayout.LayoutParams tvLayoutParams = (FrameLayout.LayoutParams) tv.getLayoutParams();
        tvLayoutParams.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
        tv.setLayoutParams(tvLayoutParams);
//        TranslateAnimation ta = new TranslateAnimation(0, 0, 0, -10);
        ObjectAnimator yAnima = ObjectAnimator.ofFloat(tv, "translationY", 0.0f, -10f);
//        ta.setDuration(300);
//        ta.setFillAfter(true);
        AnimatorSet as = new AnimatorSet();
        as.playTogether(alphaAnim, yAnima);
        as.start();




//
//        ValueAnimator varl = ValueAnimator.ofInt(0, (int)dpToFloat(-10));
//        varl.setDuration(200);
//        varl.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) tv.getLayoutParams();
//                lp.setMargins(0, (Integer) animation.getAnimatedValue(), 0, 0);
//                tv.setLayoutParams(lp);
//            }
//        });
//        varl.start();
    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_submit, menu);
//        final Menu mMenu = menu;
//        final MenuItem item = menu.findItem(R.id.action_submit_button);
//        item.getActionView().setOnClickListener(v -> prize());
//        return true;
//    }


    @Override
    public void onBackPressed() {
        popupWindow.dismiss();
        super.onBackPressed();

    }
}
