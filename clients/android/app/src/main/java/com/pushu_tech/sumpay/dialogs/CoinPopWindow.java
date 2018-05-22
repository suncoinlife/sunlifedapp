package com.pushu_tech.sumpay.dialogs;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pushu_tech.sumpay.R;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class CoinPopWindow extends PopupWindow {

    private View mPopView;
    private int mPoints;
    private TextView mEarnTextView;
    private TextView mNumberTextView;
    private TextView mTenTextView;
    private GifDrawable mGifDrawable;
    private RelativeLayout mCoinRelativeLayout;

    public CoinPopWindow(Context context, int points) {
        super(context);
        mPoints = points;
        init(context);
    }

    private void init(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPopView = layoutInflater.inflate(R.layout.popup_prize, null);
        this.setContentView(mPopView);

        AssetManager mgr = context.getAssets();
        Typeface tf = Typeface.createFromAsset(mgr, "fonts/LCDM2B.TTF");

        mCoinRelativeLayout = (RelativeLayout)mPopView.findViewById(R.id.coinLayout);
        mEarnTextView = (TextView) mPopView.findViewById(R.id.earnSage);
        mEarnTextView.setTypeface(tf);
        mEarnTextView.setText("+" + mPoints + "SAGE");

        mNumberTextView = (TextView) mPopView.findViewById(R.id.numPos);
        mNumberTextView.setTypeface(tf);
        mTenTextView = (TextView) mPopView.findViewById(R.id.tenPos);
        mTenTextView.setTypeface(tf);
        TextView hundredText = (TextView) mPopView.findViewById(R.id.hundredPos);
        hundredText.setTypeface(tf);
        TextView thousandText = (TextView) mPopView.findViewById(R.id.thousandPos);
        thousandText.setTypeface(tf);
        TextView tenThousandText = (TextView) mPopView.findViewById(R.id.tenThousandPos);
        tenThousandText.setTypeface(tf);

        GifImageView gifImageView = (GifImageView) mPopView.findViewById(R.id.coinGif);
        mGifDrawable = (GifDrawable) gifImageView.getDrawable();
        mGifDrawable.setLoopCount(3);
    }


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mPoints -= 1;
            if(mPoints <= 0){
                stopAnimation();
                //mGifDrawable.stop();
            }
            if(!mGifDrawable.isRunning()){
                mGifDrawable.start();
            }
            mEarnTextView.setText("+" + mPoints + " SAGE");
            int number = Integer.parseInt(mNumberTextView.getText().toString());
            int ten = Integer.parseInt(mTenTextView.getText().toString());
            if(number < 9) {
                number += 1;
            } else {
                number = 0;
                ten += 1;
            }
            mNumberTextView.setText(Integer.toString(number));
            mTenTextView.setText(Integer.toString(ten));
            super.handleMessage(msg);
        }
    };

    private Runnable mTask = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(1);
            mHandler.postDelayed(this, 50);
        }
    };

    public void startAnimation() {
        mHandler.postDelayed(mTask,50);
    }

    public void stopAnimation(){
        mHandler.removeCallbacks(mTask);
    }
}
