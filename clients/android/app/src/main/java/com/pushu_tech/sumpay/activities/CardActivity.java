package com.pushu_tech.sumpay.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.models.BankCardItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by virgil on 11/01/2018.
 */

public class CardActivity extends BaseActivity {

    private ListView mBankCardListView;
    private List<BankCardItem> mBacnkCardItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        mBankCardListView = (ListView) findViewById(R.id.bankCardListView);

        setActionbar(R.string.title_card);

        mBacnkCardItems = new ArrayList<>();
        mBacnkCardItems.add(new BankCardItem("OCBC", "Debit Card", "**** **** **** 4321"));

        BankCardItem[] items = new BankCardItem[mBacnkCardItems.size()];
        BankCardListAdapter adapter = new BankCardListAdapter(getApplicationContext(), R.layout.card_list_view_item, mBacnkCardItems.toArray(items));

        mBankCardListView.setAdapter(adapter);
    }

    public class BankCardListAdapter extends ArrayAdapter<BankCardItem> {

        private int mItemResourceId;

        public BankCardListAdapter(@NonNull Context context, int resource, @NonNull BankCardItem[] objects) {
            super(context, resource, objects);
            mItemResourceId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            BankCardItem item= mBacnkCardItems.get(position);
            View view;
            ViewHolder viewHolder;
            if(convertView == null){
                view = LayoutInflater.from(getContext()).inflate(R.layout.card_list_view_item, null);
                viewHolder = new ViewHolder();
                viewHolder.IconBankImageView = (ImageView)view.findViewById(R.id.iconBankImageView);
                viewHolder.BankNameTextView = (TextView)view.findViewById(R.id.bankNameTextView);
                viewHolder.CardTypeTextView = (TextView)view.findViewById(R.id.cardTypeTextView);
                viewHolder.CardNumberTextView = (TextView)view.findViewById(R.id.cardNumberTextView);
                view.setTag(viewHolder);
            } else {
                view = convertView;
                viewHolder = (ViewHolder)view.getTag();
            }
            viewHolder.BankNameTextView.setText(item.getBankName());
            viewHolder.CardTypeTextView.setText(item.getCardType());
            viewHolder.CardNumberTextView.setText(item.getCardNumber());
            return view;
        }

        public class ViewHolder {
            ImageView IconBankImageView;
            TextView BankNameTextView;
            TextView CardTypeTextView;
            TextView CardNumberTextView;
        }
    }
}
