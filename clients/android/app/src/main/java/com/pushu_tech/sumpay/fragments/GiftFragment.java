package com.pushu_tech.sumpay.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.activities.GiftActivity;

/**
 * Created by virgil on 01/03/2018.
 */

public class GiftFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gift, null);

        Intent intent = new Intent(getContext(), GiftActivity.class);
        LinearLayout headset = (LinearLayout)view.findViewById(R.id.gift_headset);
        headset.setOnClickListener(v -> startActivity(intent));

        return view;
    }
}
