package com.pushu_tech.sumpay.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import java.util.Date;

public class AdSurveyActivity extends BaseActivity {

    private double point;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_survey);
        Button button = findViewById(R.id.survey_submit);
        point = getIntent().getDoubleExtra("points", 0);
        button.setOnClickListener(v -> prize());
        setActionbar(R.string.title_survey);
    }

    private void prize() {
        // prize
        Log.d("AdSurveyActivity", "Survey end, need to give prize to user");
        CoinPopWindow popupWindow = new CoinPopWindow(this, (int)point);

        //LayoutInflater layoutInflater = (LayoutInflater) this
        //        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View popupView = layoutInflater.inflate(R.layout.popup_prize, findViewById(R.id.popup_prize));
        //PopupWindow popupWindow = new PopupWindow(this);
        //popupWindow.setContentView(popupView);
        //TextView textView = (TextView) popupView.findViewById(R.id.popup_prize_count);
        //textView.setText("+" + point);
        DataProvider.getInstance().addBalanceChange("Telsela", new Date(), point);
        popupWindow.showAtLocation(findViewById(R.id.activity_ad_survey), Gravity.CENTER, 0, 0);
        popupWindow.startAnimation();
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
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_submit, menu);
//        final Menu mMenu = menu;
//        final MenuItem item = menu.findItem(R.id.action_submit_button);
//        item.getActionView().setOnClickListener(v -> prize());
//        return true;
//    }
}
