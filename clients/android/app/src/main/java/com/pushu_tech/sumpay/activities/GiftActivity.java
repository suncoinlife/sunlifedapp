package com.pushu_tech.sumpay.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("1", "http://img10.360buyimg.com/n1/s450x450_jfs/t9838/277/2552921741/98342/3ec03233/59f97e6eN50b56a1d.jpg");
        url_maps.put("2", "http://img10.360buyimg.com/n1/s450x450_jfs/t9838/277/2552921741/98342/3ec03233/59f97e6eN50b56a1d.jpg");
        url_maps.put("3", "http://img10.360buyimg.com/n1/s450x450_jfs/t10696/251/2507836097/109570/f7425c88/59f97e74Nd80b807e.jpg");
        url_maps.put("4", "http://img10.360buyimg.com/n1/s450x450_jfs/t7537/174/4364173899/82283/ad5abf36/59f97e70Nc14ca576.jpg");

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView.image(url_maps.get(name));
            textSliderView.setScaleType(BaseSliderView.ScaleType.CenterInside);
            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        Button button = (Button) findViewById(R.id.gift_pay_button);
        button.setOnClickListener(v -> new PayPasswordDiagFragment().show(getSupportFragmentManager(), "payPasswordDialog"));

        setActionbar(R.string.empty_str);
    }
}
