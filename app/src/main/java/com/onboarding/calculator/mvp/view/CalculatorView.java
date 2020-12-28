package com.onboarding.calculator.mvp.view;

import android.app.Activity;
import android.widget.Toast;

import com.onboarding.calculator.R;
import com.onboarding.calculator.databinding.ActivityMainBinding;
import com.onboarding.calculator.mvp.contract.CalculatorContract;
import com.onboarding.calculator.mvp.view.base.ActivityView;
import static com.onboarding.calculator.util.ConstantsUtils.MESSAGE_DELETE_ALL;
import static com.onboarding.calculator.util.ConstantsUtils.MESSAGE_DELETE_VALUE;

public class CalculatorView extends ActivityView implements CalculatorContract.View {

    private final ActivityMainBinding binding;

    public CalculatorView(Activity activity, ActivityMainBinding binding) {
        super(activity);
        this.binding = binding;
    }

    @Override
    public void showValues(String value) {
        binding.textViewResult.setText(value);
    }

    @Override
    public void showError() {
        binding.textViewResult.setText(R.string.calculator_error);
    }

    @Override
    public void showDeleteAllMessage(){
        Toast.makeText(getContext(),MESSAGE_DELETE_ALL,Toast.LENGTH_LONG).show();
    }
    @Override
    public void showDeleteMessage(){
        Toast.makeText(getContext(),MESSAGE_DELETE_VALUE,Toast.LENGTH_LONG).show();
    }
}
