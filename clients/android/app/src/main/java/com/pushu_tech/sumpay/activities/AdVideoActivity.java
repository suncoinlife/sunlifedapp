package com.pushu_tech.sumpay.activities;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.dialogs.CoinPopWindow;
import com.pushu_tech.sumpay.mock.DataProvider;

import java.util.Date;

public class AdVideoActivity extends BaseActivity {

    ProgressBar mProgressBar;
    VideoView vv;
    MyAsync asyncTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_video);
        double point = getIntent().getDoubleExtra("points", 0);

        vv = findViewById(R.id.videoView);
        MediaController mc = new MediaController(AdVideoActivity.this);
        mc.setVisibility(View.GONE);
        vv.setMediaController(mc);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.tesla;

        vv.setVideoURI(Uri.parse(path));
        vv.setOnCompletionListener(mp -> {
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

        View share = findViewById(R.id.share_button);
        share.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Tesla");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Share it !");
            startActivity(Intent.createChooser(shareIntent, "share using"));
        });

        mProgressBar = findViewById(R.id.Progressbar);
        mProgressBar.setProgress(0);
        mProgressBar.setMax(100);
        asyncTask = new MyAsync();
        asyncTask.execute();

        setActionbar(R.string.title_tesla);
    }

    private class MyAsync extends AsyncTask<Void, Integer, Void>
    {
        int duration = 0;
        int current = 0;
        @Override
        protected Void doInBackground(Void... params) {

            vv.start();
            vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                public void onPrepared(MediaPlayer mp) {
                    duration = vv.getDuration();
                }
            });

            do {
                current = vv.getCurrentPosition();
                System.out.println("duration - " + duration + " current- "
                        + current);
                try {
                    publishProgress((int) (current * 100 / duration));
                    if(mProgressBar.getProgress() >= 100){
                        break;
                    }
                } catch (Exception e) {
                }
            } while (mProgressBar.getProgress() <= 100);

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            System.out.println(values[0]);
            mProgressBar.setProgress(values[0]);
        }
    }

    @Override
    protected void onDestroy() {
        if (asyncTask != null) {
            asyncTask.cancel(true);
        }
        super.onDestroy();
    }
}
