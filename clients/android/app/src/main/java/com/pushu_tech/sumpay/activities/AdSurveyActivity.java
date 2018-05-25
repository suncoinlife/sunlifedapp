package com.pushu_tech.sumpay.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.dialogs.CoinPopWindow;
import com.pushu_tech.sumpay.mock.DataProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private List<Integer> coins;
    private ScheduledFuture future;
    private PopupWindow popupWindow;
    TextView sageText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_survey);
        point = getIntent().getDoubleExtra("points", 0);
        coins = this.splitCoin((int)point, 10);
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
        sageText = findViewById(R.id.text_sage);

        LayoutInflater layoutInflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.moving_coin_view, findViewById(R.id.popup_prize));
        popupWindow = new PopupWindow(this);
        popupWindow.setContentView(popupView);
        int[] location = new int[2];
        sageText.getLocationOnScreen(location);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.clear));
        popupWindow.showAtLocation(findViewById(R.id.activity_ad_survey), Gravity.NO_GRAVITY, location[0] / 2, location[1] - sageText.getMeasuredHeight() / 2);

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

    private List<Integer> splitCoin(int coin, int part) {
        if (coin < part) {
            // shit
        } else {
            final _CountWrapper coinLeft = new _CountWrapper(coin);
            final _CountWrapper sum = new _CountWrapper(0);
            return new ArrayList<Integer>() {{
                for (int i = 0; i < part; i++) {
                    int randomCoin = random.nextInt(coinLeft.getCount() / (part - i));
                    sum.setCount(sum.getCount() + (i < part - 1 ? randomCoin : coinLeft.getCount()));
                    add(sum.getCount());
                    coinLeft.setCount(coinLeft.getCount() - randomCoin);
                }
            }};
        }

        return new ArrayList<Integer>() {{ add(coin); }};
    }

    protected void startTimer() {
        final _CountWrapper countWrapper = new _CountWrapper(25);
        future = scheduler.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                countWrapper.setCount(countWrapper.getCount() - 1); //increase every sec
                if (countWrapper.getCount() <= 1 && future != null) {
                    future.cancel(true);
                }
                if (countWrapper.getCount() <= 10) {
                    mHandler.obtainMessage(10 - countWrapper.getCount()).sendToTarget();
                }
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
    }

    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            //coinUp();
            sageText.setText("+" + coins.get(msg.what));
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
