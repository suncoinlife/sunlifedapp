package com.pushu_tech.sumpay.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.dialogs.PayPasswordDiagFragment;
import com.pushu_tech.sumpay.utils.SageHelper;

public class GiftGoodsOrderConfirmActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_deliver_pay_confirm);

        setActionbar(R.string.title_goods_delever);

        Button payButton = (Button)findViewById(R.id.order_pay_button);
        payButton.setOnClickListener(view ->{
            PayPasswordDiagFragment fragment = new PayPasswordDiagFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("payTotalPrice", 1010);
            bundle.putInt("payRewardSage", 0);
            bundle.putString("payType", "SAGE");
            bundle.putString("payItem", "Gift");
            fragment.setArguments(bundle);
            fragment.show(getSupportFragmentManager(), "payPasswordDialog");
            fragment.setDoneHandler(new Handler(msg -> {
                SageHelper.SetRecord(this, "sonyGift");
                setResult(1);
                finish();
                return true;
            }));
        });
    }
}
