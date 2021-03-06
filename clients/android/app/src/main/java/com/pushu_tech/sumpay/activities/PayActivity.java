package com.pushu_tech.sumpay.activities;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.utils.BarCodeEncoder;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;
/**
 * Created by virgil on 10/01/2018.
 */

public class PayActivity extends BaseActivity {

    private ImageView mBarCodeImageView;
    private  ImageView mQRCodeImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        setActionbar(R.string.title_pay);

        mBarCodeImageView = (ImageView)findViewById(R.id.payBarCode);
        mQRCodeImageView = (ImageView) findViewById(R.id.payQRCode);

        createQRCode();
        createBarCode();
    }

    private void createQRCode() {
        /*
        这里为了偷懒，就没有处理匿名 AsyncTask 内部类导致 Activity 泄漏的问题
        请开发在使用时自行处理匿名内部类导致Activity内存泄漏的问题，处理方式可参考 https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F%E6%80%BB%E7%BB%93.md
         */
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                return QRCodeEncoder.syncEncodeQRCode("testqrcode", BGAQRCodeUtil.dp2px(PayActivity.this, 150), Color.parseColor("#000000"));
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    mQRCodeImageView.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(PayActivity.this, "生成英文二维码失败", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    private void createBarCode(){
                /*
        这里为了偷懒，就没有处理匿名 AsyncTask 内部类导致 Activity 泄漏的问题
        请开发在使用时自行处理匿名内部类导致Activity内存泄漏的问题，处理方式可参考 https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F%E6%80%BB%E7%BB%93.md
         */
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                return BarCodeEncoder.creatBarcode(getApplicationContext(), "1234567890ba5", 800, 240, false);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    mBarCodeImageView.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(PayActivity.this, "生成英文二维码失败", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
}
