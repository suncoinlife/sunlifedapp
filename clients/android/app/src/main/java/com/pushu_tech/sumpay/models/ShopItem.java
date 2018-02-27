package com.pushu_tech.sumpay.models;

/**
 * Created by virgil on 18/01/2018.
 */

public class ShopItem {
    private String mShopName;
    private float mShopRating;
    private String mShopType;
    private String mShopArea;
    private String mShopDistance;

    public ShopItem(String shopName, float shopRating, String shopType, String shopArea, String shopDistance){
        mShopName = shopName;
        mShopRating = shopRating;
        mShopType = shopType;
        mShopArea = shopArea;
        mShopDistance = shopDistance;
    }

    public float getShopRating() {
        return mShopRating;
    }

    public String getShopName() {
        return mShopName;
    }

    public String getShopArea() {
        return mShopArea;
    }

    public String getShopType() {
        return mShopType;
    }

    public String getShopDistance() {
        return mShopDistance;
    }
}
