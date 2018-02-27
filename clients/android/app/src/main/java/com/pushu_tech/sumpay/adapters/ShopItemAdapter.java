package com.pushu_tech.sumpay.adapters;

import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.models.ShopItem;

import java.util.ArrayList;

/**
 * Created by virgil on 18/01/2018.
 */

public class ShopItemAdapter extends RecyclerView.Adapter<ShopItemAdapter.ViewHolder>{

    private ArrayList<ShopItem> mData;

    public ShopItemAdapter(ArrayList<ShopItem> data) {
        this.mData = data;
    }

    public void updateData(ArrayList<ShopItem> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_show_item, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 绑定数据
        ShopItem item = this.mData.get(position);
        holder.mShopNameTextView.setText(item.getShopName());
        holder.mShopRatingBar.setRating(item.getShopRating());
        holder.mShopTypeTextView.setText(item.getShopType());
        holder.mShopAreaTextView.setText(item.getShopArea());
        holder.mShopDistanceView.setText(item.getShopDistance());
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mShopImageView;
        AppCompatRatingBar mShopRatingBar;
        TextView mShopNameTextView;
        TextView mShopTypeTextView;
        TextView mShopAreaTextView;
        TextView mShopDistanceView;

        public ViewHolder(View itemView) {
            super(itemView);
            mShopImageView = (ImageView) itemView.findViewById(R.id.shopImageView);
            mShopRatingBar = (AppCompatRatingBar) itemView.findViewById(R.id.shopRatingBar);
            mShopNameTextView = (TextView) itemView.findViewById(R.id.shopNameTextView);
            mShopTypeTextView = (TextView) itemView.findViewById(R.id.shopTypeTextView);
            mShopAreaTextView = (TextView) itemView.findViewById(R.id.shopAreaTextView);
            mShopDistanceView = (TextView) itemView.findViewById(R.id.shopDistanceTextView);
        }
    }
}
