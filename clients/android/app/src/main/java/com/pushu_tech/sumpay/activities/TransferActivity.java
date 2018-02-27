package com.pushu_tech.sumpay.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.dialogs.PayConfirmDialogFragment;

/**
 * Created by virgil on 12/01/2018.
 */

public class TransferActivity extends BaseActivity {

    private EditText mAmountEditText;
    private Button mTransferButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        setActionbar(R.string.title_transfer);

        mAmountEditText = (EditText)findViewById(R.id.amountEditText);
        mAmountEditText.setInputType(EditorInfo.TYPE_NUMBER_FLAG_DECIMAL|EditorInfo.TYPE_CLASS_NUMBER);
        mAmountEditText.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if(source.equals(".") && dest.toString().length() == 0){
                    return "0.";
                }
                if(dest.toString().contains(".")){
                    int index = dest.toString().indexOf(".");
                    int length = dest.toString().substring(index).length();
                    if(length == 3){
                        return "";
                    }
                }
                return null;
            }
        }});

        mTransferButton = (Button)findViewById(R.id.confirmTransferButton);
        mTransferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAmountEditText.getText().length() <= 0){
                    return;
                }
                PayConfirmDialogFragment dialogFragment = new PayConfirmDialogFragment();
                dialogFragment.setPayAmount( Double.parseDouble(mAmountEditText.getText().toString()));
                dialogFragment.setTransferActivity(TransferActivity.this);
                dialogFragment.show(getSupportFragmentManager(), "payConfirmDialog");
            }
        });
    }
}
