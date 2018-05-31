package com.pushu_tech.sumpay.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SageHelper {

    public static float GetTotalSages(Context context){
        SharedPreferences sp = context.getSharedPreferences("Sages", Context.MODE_PRIVATE);
        return sp.getFloat("total", (float) 2100.0);
    }

    public static void SetTotalSages(Context context, float value){
        SharedPreferences sp = context.getSharedPreferences("Sages", Context.MODE_PRIVATE);
        float total = sp.getFloat("total", (float) 2100.0);
        total += value;
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat("total", total);
        editor.commit();
    }

    public static boolean GetRecord(Context context, String key){
        SharedPreferences sp = context.getSharedPreferences("Sages", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    public static void SetRecord(Context context, String key){
        SharedPreferences sp = context.getSharedPreferences("Sages", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, true);
        editor.commit();
    }

    public static float GetFloatRecord(Context context, String key){
        SharedPreferences sp = context.getSharedPreferences("Sages", Context.MODE_PRIVATE);
        return sp.getFloat(key, (float) 0.0);
    }

    public static void SetFloatRecord(Context context, String key, float value){
        SharedPreferences sp = context.getSharedPreferences("Sages", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, value);
        editor.commit();
    }
}
