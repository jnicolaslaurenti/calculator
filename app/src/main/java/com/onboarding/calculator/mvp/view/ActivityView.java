package com.onboarding.calculator.mvp.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;

import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;

public class ActivityView {
    private WeakReference<Activity> activityRef;

    public ActivityView(Activity activity) {
        activityRef = new WeakReference<>(activity);
    }

}
