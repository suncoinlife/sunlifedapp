package com.pushu_tech.sumpay.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.LinearLayout;

public class NewCheckableLinearLayout extends LinearLayout implements Checkable {
    private boolean mChecked;

    public NewCheckableLinearLayout(Context context) {
        super(context);
    }

    public NewCheckableLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void setChecked(boolean b) {
        if(mChecked != b) {
            mChecked = b;
            refreshDrawableState();
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }
}
