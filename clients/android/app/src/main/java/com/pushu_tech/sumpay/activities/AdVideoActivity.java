package com.pushu_tech.sumpay.activities;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
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

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class AdVideoActivity extends BaseActivity {

    TextView mTextSage;
    VideoView vv;
    int mPoint;
    int mCurrent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_video);
        mPoint = getIntent().getIntExtra("points", 0);
        mTextSage = (TextView)findViewById(R.id.text_sage);

        vv = findViewById(R.id.videoView);
        MediaController mc = new MediaController(AdVideoActivity.this);
        mc.setVisibility(View.GONE);
        vv.setMediaController(mc);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.tesla;

        vv.setVideoURI(Uri.parse(path));
        vv.setOnCompletionListener(mp -> {
            // prize
            Log.d("AdSurveyActivity", "Survey end, need to give prize to user");
            LayoutInflater layoutInflater = (LayoutInflater) this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View popupView = layoutInflater.inflate(R.layout.moving_coin_view, null);
            GifImageView gifImageView = (GifImageView) popupView.findViewById(R.id.coinGif);
            GifDrawable gifDrawable = (GifDrawable) gifImageView.getDrawable();
            gifDrawable.setLoopCount(1);
            PopupWindow popupWindow = new PopupWindow(this);
            popupWindow.setContentView(popupView);
            int[] location = new int[2];
            mTextSage.getLocationOnScreen(location);
            int popX = location[0] - popupView.getWidth() - 60;
            int popY = location[1] - 60;
            popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.clear));
            popupWindow.showAtLocation(findViewById(R.id.activity_ad_video), Gravity.NO_GRAVITY, popX, popY);
            int countDown = 1500/(mPoint + 100);
            new CountDownTimer(1500, countDown){
                @Override
                public void onTick(long l) {
                    mHandler.handleMessage(new Message());
                }

                @Override
                public void onFinish() {

                }
            }.start();
            try{
                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.coins);
                mediaPlayer.start();
            } catch (Exception e){
                Log.e("Play sound error",e.getMessage());
            }
        });


        View share = findViewById(R.id.share_button);
        share.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Tesla");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Share it !");
            startActivity(Intent.createChooser(shareIntent, "share using"));
        });

        vv.start();

        setActionbar(R.string.title_tesla);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(mCurrent < mPoint){
                mCurrent += 1;
                mTextSage.setText("+" + Integer.toString(mCurrent));
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if(vv != null){
            vv.stopPlayback();
        }
        super.onDestroy();
    }
}
