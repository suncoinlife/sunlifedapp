package com.pushu_tech.sumpay.utils;

import android.util.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CoinUtils {
    static CoinUtils _instance = new CoinUtils();

    private CoinUtils() {}

    Random random = new Random();

    class _CountWrapper {
        int count;

        public _CountWrapper(int count) {
            this.count = count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getCount() {
            return this.count;
        }
    }

    public Map<Integer, Pair<Integer, Integer>> splitCoin(int startCoin, int coin, int part) {
        if (coin < part) {
            // shit
        } else {
            final _CountWrapper coinLeft = new _CountWrapper(coin);
            final _CountWrapper sum = new _CountWrapper(startCoin);
            return new HashMap<Integer, Pair<Integer, Integer>>() {{
                for (int i = 0; i < part; i++) {
                    int randomCoin = random.nextInt(coinLeft.getCount() / (part - i));
                    int additionalCoin = i < part - 1 ? randomCoin : coinLeft.getCount();
                    sum.setCount(sum.getCount() + additionalCoin);
                    put(i, new Pair<>(sum.getCount(), additionalCoin));
                    coinLeft.setCount(coinLeft.getCount() - randomCoin);
                }
            }};
        }

        return new HashMap<Integer, Pair<Integer, Integer>>() {{ put(0, new Pair<>(coin, 0)); }};
    }

    public static CoinUtils getInstance() {
        return _instance;
    }
}
