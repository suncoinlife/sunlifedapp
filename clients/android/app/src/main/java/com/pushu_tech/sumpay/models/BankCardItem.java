package com.pushu_tech.sumpay.models;

/**
 * Created by virgil on 11/01/2018.
 */

public class BankCardItem {
    private String mBankName;
    private String mCardType;
    private String mCardNumber;

    public BankCardItem(String bankName, String cardType, String cardNumber){
        mBankName = bankName;
        mCardType = cardType;
        mCardNumber = cardNumber;
    }

    public String getBankName() {
        return mBankName;
    }

    public String getCardNumber() {
        return mCardNumber;
    }

    public String getCardType() {
        return mCardType;
    }
}
