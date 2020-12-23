package com.onboarding.calculator.mvp.view;

import android.app.Activity;

import com.onboarding.calculator.databinding.ActivityMainBinding;

public class CalculatorView extends ActivityView {

    private final ActivityMainBinding binding;

    public CalculatorView(Activity activity, ActivityMainBinding binding) {
        super(activity);
        this.binding = binding;
    }

    public void showValues(String value){
        binding.textViewResult.setText(value);
    }
}
