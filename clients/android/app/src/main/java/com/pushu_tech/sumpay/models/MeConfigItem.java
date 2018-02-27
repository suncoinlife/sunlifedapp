package com.pushu_tech.sumpay.models;

/**
 * Created by virgil on 11/01/2018.
 */

public class MeConfigItem {

    private String mConfigId;
    private String mIconText;
    private String mText;

    public MeConfigItem(String configId, String iconText, String text) {
        mConfigId = configId;
        mIconText = iconText;
        mText = text;
    }

    public String getConfigId() {
        return mConfigId;
    }

    public String getIconText() {
        return mIconText;
    }

    public String getText() {
        return mText;
    }
}
