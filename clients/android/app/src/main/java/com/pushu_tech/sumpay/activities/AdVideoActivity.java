package com.pushu_tech.sumpay.activities;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.VideoView;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.dialogs.CoinPopWindow;
import com.pushu_tech.sumpay.mock.DataProvider;

import java.util.Date;

public class AdVideoActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_video);
        double point = getIntent().getDoubleExtra("points", 0);

        VideoView vv = (VideoView) findViewById(R.id.videoView);
        MediaController mc = new MediaController(AdVideoActivity.this);
        mc.setVisibility(View.GONE);
        vv.setMediaController(mc);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.tesla;

        vv.setVideoURI(Uri.parse(path));
        vv.start();
        vv.setOnCompletionListener(mp -> {
            // prize
            Log.d("AdSurveyActivity", "Survey end, need to give prize to user");
            CoinPopWindow popupWindow = new CoinPopWindow(this);

            //LayoutInflater layoutInflater = (LayoutInflater) this
            //        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //View popupView = layoutInflater.inflate(R.layout.popup_prize, findViewById(R.id.popup_prize));
            //PopupWindow popupWindow = new PopupWindow(this);
            //popupWindow.setContentView(popupView);
            //TextView textView = (TextView) popupView.findViewById(R.id.popup_prize_count);
            //textView.setText("+" + point);
            DataProvider.getInstance().addBalanceChange("Telsela", new Date(), point);
            popupWindow.showAtLocation(findViewById(R.id.activity_ad_video), Gravity.CENTER, 0, 0);
            CountDownTimer timer = new CountDownTimer(1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    popupWindow.dismiss();
                    finish();
                }
            };
            timer.start();
        });
        setActionbar(R.string.title_tesla);
    }
}
