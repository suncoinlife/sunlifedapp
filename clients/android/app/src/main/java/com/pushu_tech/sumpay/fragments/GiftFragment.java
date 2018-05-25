package com.pushu_tech.sumpay.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.activities.GiftActivity;
import com.pushu_tech.sumpay.activities.ScanActivity;
import com.pushu_tech.sumpay.views.IconView;

import java.util.HashMap;

/**
 * Created by virgil on 01/03/2018.
 */

public class GiftFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gift, null);

//        Intent intent = new Intent(getContext(), GiftActivity.class);
//        View headset = view.findViewById(R.id.gift_headset);
//        headset.setOnClickListener(v -> startActivity(intent));



        View headset = view.findViewById(R.id.shop_food1);
        headset.setOnClickListener(v -> startActivity(new Intent(getContext(), GiftActivity.class)));


        SliderLayout mDemoSlider = (SliderLayout)view.findViewById(R.id.slider);

        TextSliderView textSliderView1 = new TextSliderView(getContext());
        textSliderView1.image(R.drawable.gifttop01);
        textSliderView1.setScaleType(BaseSliderView.ScaleType.CenterCrop);

        TextSliderView textSliderView2 = new TextSliderView(getContext());
        textSliderView2.image(R.drawable.gifttop02);
        textSliderView2.setScaleType(BaseSliderView.ScaleType.CenterCrop);

        TextSliderView textSliderView3 = new TextSliderView(getContext());
        textSliderView3.image(R.drawable.gifttop03);
        textSliderView3.setScaleType(BaseSliderView.ScaleType.CenterCrop);

        mDemoSlider.addSlider(textSliderView1);
        mDemoSlider.addSlider(textSliderView2);
        mDemoSlider.addSlider(textSliderView3);


        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        return view;
    }
}
