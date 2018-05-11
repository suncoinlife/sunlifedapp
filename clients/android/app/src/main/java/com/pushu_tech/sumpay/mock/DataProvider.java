package com.pushu_tech.sumpay.mock;

import android.support.annotation.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DataProvider {

    private static DataProvider _instance;

    public static DataProvider getInstance() {
        if (_instance == null) {
            _instance = new DataProvider();
        }

        return _instance;
    }


    class BalanceChange implements Comparable<BalanceChange> {
        private String from;
        private double count;
        private Date date;
        private double total;

        public BalanceChange(String from, Date date, double count, double total) {
            this.from = from;
            this.date = date;
            this.count = count;
            this.total = total;
        }

        public String getFrom() {
            return from;
        }

        public double getCount() {
            return count;
        }

        public Date getDate() {
            return date;
        }

        public double getTotal() {
            return total;
        }

        @Override
        public int compareTo(@NonNull BalanceChange o) {
            return getDate().compareTo(o.getDate());
        }
    }

    private double userBalance = 8.0;
    private List<BalanceChange> balanceChanges = new ArrayList<BalanceChange>() {{
        add(new BalanceChange("NetsPay", new Date(2018, 1, 19, 0, 0, 0), 1, 8));
        add(new BalanceChange("NetsPay", new Date(2018, 1, 19, 0, 0, 0), 1, 7));
        add(new BalanceChange("NetsPay", new Date(2018, 1, 19, 0, 0, 0), 1, 6));
        add(new BalanceChange("NetsPay", new Date(2018, 1, 19, 0, 0, 0), 1, 5));
        add(new BalanceChange("NetsPay", new Date(2018, 1, 19, 0, 0, 0), 1, 4));
        add(new BalanceChange("NetsPay", new Date(2018, 1, 19, 0, 0, 0), 1, 3));
        add(new BalanceChange("NetsPay", new Date(2018, 1, 19, 0, 0, 0), 1, 2));
        add(new BalanceChange("NetsPay", new Date(2018, 1, 19, 0, 0, 0), 1, 1));
    }};

    public double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(double balance) {
        userBalance = balance;
    }

    public void addBalanceChange(String from, Date date, double count) {
        this.userBalance += count;
        balanceChanges.add(new BalanceChange(from, date, count, this.userBalance));
    }

    public List<BalanceChange> getBalanceChanges() {
        Collections.sort(balanceChanges);
        return balanceChanges;
    }

}
