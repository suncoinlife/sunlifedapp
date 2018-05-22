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

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("1", "http://jonvilma.com/images/digital-background-1.jpg");
        url_maps.put("2", "https://www.wirtschaft-digital-bw.de/fileadmin/_processed_/8/b/csm_head-iw40_88d75b90e5.jpg");
        url_maps.put("3", "http://ak6.picdn.net/shutterstock/videos/9134366/thumb/1.jpg");
        url_maps.put("4", "http://eskipaper.com/images/digital-wallpaper-4.jpg");

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView.image(url_maps.get(name));
            textSliderView.setScaleType(BaseSliderView.ScaleType.CenterCrop);
            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        return view;
    }
}
