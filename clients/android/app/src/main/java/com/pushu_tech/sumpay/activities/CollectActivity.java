package com.pushu_tech.sumpay.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.pushu_tech.sumpay.R;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

/**
 * Created by virgil on 09/01/2018.
 */

public class CollectActivity extends BaseActivity {

    private ImageView mQRCodeImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);

        setActionbar(R.string.title_collect);

        mQRCodeImageView = (ImageView) findViewById(R.id.sumpayQRCode);
        createQRCode();
    }

    private void createQRCode() {
        /*
        这里为了偷懒，就没有处理匿名 AsyncTask 内部类导致 Activity 泄漏的问题
        请开发在使用时自行处理匿名内部类导致Activity内存泄漏的问题，处理方式可参考 https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F%E6%80%BB%E7%BB%93.md
         */
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                return QRCodeEncoder.syncEncodeQRCode("testqrcode", BGAQRCodeUtil.dp2px(CollectActivity.this, 150), Color.parseColor("#000000"));
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    mQRCodeImageView.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(CollectActivity.this, "生成英文二维码失败", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

}
