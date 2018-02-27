package com.pushu_tech.sumpay.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.utils.IntentResultCodes;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * Created by virgil on 09/01/2018.
 */

public class ScanActivity extends BaseActivity implements QRCodeView.Delegate {
    public final static String INTENT_EXTRA_QRCODE_RESULT = "qr_code_result";
    public final static int INTENT_RESULT_CODE_SUCCESS = 2;
    private static final String TAG = ScanActivity.class.getSimpleName();
    private boolean mFlashLightOpen = false;

    private QRCodeView mQRCodeView;
    private TextView mFlashlightTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        setActionbar(R.string.title_scan);

        mQRCodeView = (ZXingView) findViewById(R.id.zxingview);
        mQRCodeView.setDelegate(this);

        mFlashlightTextView = (TextView) findViewById(R.id.flashlight);
        mFlashlightTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFlashLightOpen) {
                    mQRCodeView.closeFlashlight();
                } else {
                    mQRCodeView.openFlashlight();
                }
                mFlashLightOpen = !mFlashLightOpen;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
//        mQRCodeView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);

        //mQRCodeView.showScanRect();
        mQRCodeView.startSpot();
    }

    @Override
    protected void onStop() {
        mQRCodeView.closeFlashlight();
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result:" + result);
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        //vibrate();
        //mQRCodeView.startSpot();
        Intent intent = new Intent();
        intent.putExtra(INTENT_EXTRA_QRCODE_RESULT, result);
        setResult(IntentResultCodes.INTENT_RESULT_CODE_SUCCESS, intent);
        finish();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }
}
