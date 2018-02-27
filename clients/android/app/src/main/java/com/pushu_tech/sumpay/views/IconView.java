package com.pushu_tech.sumpay.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.pushu_tech.sumpay.BaseApplication;

/**
 * Created by virgil on 10/01/2018.
 */

public class IconView extends AppCompatTextView {
    public IconView(Context context){
        super(context);
        init();
    }

    public IconView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IconView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        this.setTypeface(BaseApplication.getInstance().getIconTypeFace());
    }
}
