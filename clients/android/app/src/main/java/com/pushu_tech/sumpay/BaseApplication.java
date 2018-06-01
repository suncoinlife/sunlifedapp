package com.pushu_tech.sumpay;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.fomopay.android.sdk.FOMOPay;
import com.pushu_tech.sumpay.utils.SageHelper;

/**
 * Created by virgil on 09/01/2018.
 */

public class BaseApplication extends Application {
    private  static BaseApplication instance;
    static public final String FOMO_API_USERNAME = "dev_sit";
    static public final String FOMO_API_SECRET = "KZzTEtBrctjdNnJgOi8gzhqiRagRpauAke7DJcuGcecU5CBY63ZC3MalAg9yUtWfXdnicli0ve21c45DmBdpata";

    public static  synchronized  BaseApplication getInstance(){
        return instance;
    }
    private Typeface mIconTypeFace;

    public Typeface getIconTypeFace() {
        return  mIconTypeFace;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mIconTypeFace = Typeface.createFromAsset(getAssets(), "iconfont/Framework7Icons-Regular.ttf");
        //设置异常捕获
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
        FOMOPay.SHOW_LOGS = true;
        FOMOPay.init(this, FOMO_API_USERNAME, FOMO_API_SECRET);

        // force set sage count
        SageHelper.SetTotalSagesForce(getApplicationContext(), 2100);
        SageHelper.SetRecordForce(getApplicationContext(), "StarBuckCoupon", false);
        SageHelper.SetRecordForce(getApplicationContext(), "sonyGift", false);
        SageHelper.SetRecordForce(getApplicationContext(), "HBCTask", false);
        SageHelper.SetRecordForce(getApplicationContext(), "TeslaTask", false);
        SageHelper.SetRecordForce(getApplicationContext(), "starbucksBill", false);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
