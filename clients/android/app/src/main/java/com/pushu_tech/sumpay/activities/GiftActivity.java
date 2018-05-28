package com.pushu_tech.sumpay.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.dialogs.PayPasswordDiagFragment;

import java.util.HashMap;

public class GiftActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);

        SliderLayout mDemoSlider = (SliderLayout)findViewById(R.id.slider);

        TextSliderView textSliderView1 = new TextSliderView(this);
        textSliderView1.image(R.drawable.sony01);
        textSliderView1.setScaleType(BaseSliderView.ScaleType.FitCenterCrop);

        TextSliderView textSliderView2 = new TextSliderView(this);
        textSliderView2.image(R.drawable.sony02);
        textSliderView2.setScaleType(BaseSliderView.ScaleType.FitCenterCrop);

        TextSliderView textSliderView3 = new TextSliderView(this);
        textSliderView3.image(R.drawable.sony03);
        textSliderView3.setScaleType(BaseSliderView.ScaleType.FitCenterCrop);

        mDemoSlider.addSlider(textSliderView1);
        mDemoSlider.addSlider(textSliderView2);
        mDemoSlider.addSlider(textSliderView3);

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        Button button = (Button) findViewById(R.id.gift_pay_button);
        button.setOnClickListener(v -> new PayPasswordDiagFragment().show(getSupportFragmentManager(), "payPasswordDialog"));

        setActionbar(R.string.title_redeem);
    }
}
