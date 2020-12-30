package com.onboarding.calculator.mvp.view;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.onboarding.calculator.R;
import com.onboarding.calculator.databinding.ActivityMainBinding;
import com.onboarding.calculator.mvp.contract.CalculatorContract;
import com.onboarding.calculator.mvp.view.base.ActivityView;

import static com.onboarding.calculator.util.ConstantsUtils.RESULT;

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
    public void showOperator(String value) {
        binding.textViewResult.setText(value);
    }

    @Override
    public void showDeleteAllMessage() {
        Context context = getContext();
        if (context != null) {
            Toast.makeText(context, context.getString(R.string.operation_deleted), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showDeleteMessage() {
        Context context = getContext();
        if (context != null) {
            Toast.makeText(context, context.getString(R.string.value_deleted), Toast.LENGTH_SHORT).show();
        }
    }

    public void reset() {
        binding.textViewResult.setText(RESULT);
    }
}
