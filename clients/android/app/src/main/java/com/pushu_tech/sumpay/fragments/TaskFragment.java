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
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.pushu_tech.sumpay.R;
import com.pushu_tech.sumpay.activities.AdSurveyActivity;
import com.pushu_tech.sumpay.activities.AdVideoActivity;
import com.pushu_tech.sumpay.activities.ShopActivity;
import com.pushu_tech.sumpay.activities.TransferActivity;

/**
 * Created by virgil on 01/03/2018.
 */

public class TaskFragment extends Fragment {

    LinearLayout teselaLayout;
    LinearLayout hbcLayout;

    static int reqCode_AdVideo = 100;
    static int reqCode_AdSurvey = 101;
    public static int RESULT_DONE = 1;

    boolean isAdVideoDone = false;
    boolean isAdSurveyDone = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, null);

        teselaLayout = (LinearLayout) view.findViewById(R.id.telsela_layout);
        teselaLayout.setOnClickListener(v -> {
            Intent videoIntent = new Intent(getContext(), AdVideoActivity.class);
            videoIntent.putExtra("points", 45);
            videoIntent.putExtra("isDone", isAdVideoDone);
            startActivityForResult(videoIntent, reqCode_AdVideo);
        });

        hbcLayout = (LinearLayout) view.findViewById(R.id.hbc_layout);
        hbcLayout.setOnClickListener(v -> {
            Intent surveyIntent = new Intent(getContext(), AdSurveyActivity.class);
            surveyIntent.putExtra("points", 68);
            surveyIntent.putExtra("isDone", isAdSurveyDone);
            startActivityForResult(surveyIntent, reqCode_AdSurvey);
        });

        View tesla_not_interested1 = view.findViewById(R.id.tesla_not_interesting1);
        View tesla_not_interested2 = view.findViewById(R.id.tesla_not_interesting2);
        View tesla_not_interested3 = view.findViewById(R.id.tesla_not_interesting3);
        View tesla_not_interested4 = view.findViewById(R.id.tesla_not_interesting4);
        View.OnClickListener listener = v -> new AlertDialog.Builder(getContext()).setCancelable(false)
                .setTitle("Confirmation")
                .setMessage("Select 'Yes' if you are not interested.")
                .setPositiveButton("Yes", (v_y, i) -> { Log.d("TaskFragment", "YES"); collapse((View)v.getParent().getParent()); })
                .setNegativeButton("No", (v_n, i) -> Log.d("TaskFragment", "NO"))
                .create()
                .show();
        tesla_not_interested1.setOnClickListener(listener);
        tesla_not_interested2.setOnClickListener(listener);
        tesla_not_interested3.setOnClickListener(listener);
        tesla_not_interested4.setOnClickListener(listener);


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == reqCode_AdSurvey) {
            if (resultCode == RESULT_DONE) {
                isAdSurveyDone = true;
                getView().findViewById(R.id.surveyDone).setVisibility(View.VISIBLE);
                //collapse(getView().findViewById(R.id.hbc_layout));
            }
        }

        if (requestCode == reqCode_AdVideo) {
            if (resultCode == RESULT_DONE) {
                isAdVideoDone = true;
                getView().findViewById(R.id.videoDone).setVisibility(View.VISIBLE);
                //collapse(getView().findViewById(R.id.telsela_layout));
            }
        }
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }


}
