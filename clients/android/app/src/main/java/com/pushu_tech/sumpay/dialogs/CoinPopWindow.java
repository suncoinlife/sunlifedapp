package com.pushu_tech.sumpay.dialogs;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.pushu_tech.sumpay.R;

public class CoinPopWindow extends PopupWindow {

    private View mPopView;

    public CoinPopWindow(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context){
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPopView = layoutInflater.inflate(R.layout.popup_prize, null);
        this.setContentView(mPopView);

        AssetManager mgr = context.getAssets();
        Typeface tf = Typeface.createFromAsset(mgr, "fonts/LCDM2B.TTF");

        TextView numText = (TextView)mPopView.findViewById(R.id.numPos);
        numText.setTypeface(tf);
        TextView tenText = (TextView)mPopView.findViewById(R.id.tenPos);
        tenText.setTypeface(tf);
        TextView hundredText = (TextView)mPopView.findViewById(R.id.hundredPos);
        hundredText.setTypeface(tf);
        TextView thousandText = (TextView)mPopView.findViewById(R.id.thousandPos);
        thousandText.setTypeface(tf);
        TextView tenThousandText = (TextView)mPopView.findViewById(R.id.tenThousandPos);
        tenThousandText.setTypeface(tf);
    }
}
