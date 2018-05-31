package com.pushu_tech.sumpay.activities;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.dialogs.CoinPopWindow;
import com.pushu_tech.sumpay.mock.DataProvider;

import java.util.Date;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class AdSurveyActivity extends BaseActivity {

    private int mPoint;
    private int mCurrent;
    private TextView mSageText;
    private boolean mIsDone;
    PopupWindow popupWindow;
    ScrollView scrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_survey);
        scrollView = findViewById(R.id.survey_scrollView);
        mPoint = getIntent().getIntExtra("points", 0);
        mIsDone = getIntent().getBooleanExtra("isDone", false);
        setActionbar(R.string.title_survey, R.string.action_submit, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prize();
            }
        });
        if (mIsDone) {
            disableActionButton();
            setResult(0);
        } else {
            setResult(1);
        }
    }

    private void prize() {
        // prize
        scrollView.smoothScrollTo(0, 0);
        disableActionButton();
        setResult(0);
        mCurrent = 0;
        Log.d("AdSurveyActivity", "Survey end, need to give prize to user");
         mSageText = findViewById(R.id.text_sage);

        LayoutInflater layoutInflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.moving_coin_view, null);
        GifImageView gifImageView = (GifImageView) popupView.findViewById(R.id.coinGif);
        GifDrawable gifDrawable = (GifDrawable) gifImageView.getDrawable();
        gifDrawable.setLoopCount(1);

        Activity _this = this;

        new CountDownTimer(300, 300) {
            @Override
            public void onTick(long l) {
                mHandler.handleMessage(new Message());
            }

            @Override
            public void onFinish() {

                popupWindow = new PopupWindow(_this);
                popupWindow.setContentView(popupView);
                int[] location = new int[2];
                mSageText.getLocationOnScreen(location);
                int popX = location[0] - popupView.getWidth() - 80;
                int popY = location[1] - 140;
                popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.clear));
                popupWindow.showAtLocation(findViewById(R.id.activity_ad_survey), Gravity.NO_GRAVITY, popX, popY);
                int countDown = 4500/(mPoint + 100);
                new CountDownTimer(4500, countDown){
                    @Override
                    public void onTick(long l) {
                        mHandler.handleMessage(new Message());
                    }

                    @Override
                    public void onFinish() {
                        if (popupWindow != null) {
                            popupWindow.dismiss();
                        }
                    }
                }.start();
                try{
                    MediaPlayer mediaPlayer = MediaPlayer.create(_this, R.raw.coins);
                    mediaPlayer.start();
                } catch (Exception e){
                    Log.e("Play sound error",e.getMessage());
                }
            }
        }.start();


    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (mCurrent - 30 < mPoint){
                mCurrent += 1;
                if (mCurrent >= 30) {
                    mSageText.setText("+" + Integer.toString(mCurrent - 30));
                }
            }
            super.handleMessage(msg);
        }
    };
}
