package com.pushu_tech.sumpay.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.activities.AdSurveyActivity;
import com.pushu_tech.sumpay.activities.AdVideoActivity;
import com.pushu_tech.sumpay.activities.ShopActivity;

/**
 * Created by virgil on 01/03/2018.
 */

public class TaskFragment extends Fragment {

    LinearLayout teselaLayout;
    LinearLayout hbcLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, null);

        teselaLayout = (LinearLayout) view.findViewById(R.id.telsela_layout);
        Intent videoIntent = new Intent(getContext(), AdVideoActivity.class);
        videoIntent.putExtra("points", 45);
        teselaLayout.setOnClickListener(v -> startActivity(videoIntent));

        hbcLayout = (LinearLayout) view.findViewById(R.id.hbc_layout);
        Intent surveyIntent = new Intent(getContext(), AdSurveyActivity.class);
        surveyIntent.putExtra("points", 68);
        hbcLayout.setOnClickListener(v -> startActivity(surveyIntent));

        View tesla_not_interested1 = view.findViewById(R.id.tesla_not_interesting1);
        View tesla_not_interested2 = view.findViewById(R.id.tesla_not_interesting2);
        View tesla_not_interested3 = view.findViewById(R.id.tesla_not_interesting3);
        View tesla_not_interested4 = view.findViewById(R.id.tesla_not_interesting4);
        View.OnClickListener listener = v -> new AlertDialog.Builder(getContext()).setCancelable(false)
                .setTitle("Confirmation")
                .setMessage("Select 'Yes' if you are not interested.")
                .setPositiveButton("Yes", (v_y, i) -> Log.d("TaskFragment", "YES"))
                .setNegativeButton("No", (v_n, i) -> Log.d("TaskFragment", "NO"))
                .create()
                .show();
        tesla_not_interested1.setOnClickListener(listener);
        tesla_not_interested2.setOnClickListener(listener);
        tesla_not_interested3.setOnClickListener(listener);
        tesla_not_interested4.setOnClickListener(listener);


        return view;
    }
}
