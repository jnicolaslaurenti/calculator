package com.onboarding.calculator.mvp.view;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.onboarding.calculator.R;
import com.onboarding.calculator.databinding.ActivityMainBinding;
import com.onboarding.calculator.mvp.contract.CalculatorContract;
import com.onboarding.calculator.mvp.view.base.ActivityView;

public class CalculatorView extends ActivityView implements CalculatorContract.View {

    private final ActivityMainBinding binding;

    public CalculatorView(Activity activity, ActivityMainBinding binding) {
        super(activity);
        this.binding = binding;
    }

    @Override
    public void operationViewUpdate(String operation) {
        binding.textViewCompleteOperation.setText(operation);
    }

    @Override
    public void resetOperationView() {
        binding.textViewCompleteOperation.setText(R.string.activity_main_calculator_empty_operation);
    }

    @Override
    public void showValues(String value) {
        binding.textViewResult.setText(value);
    }

    @Override
    public void showDivisionByZeroError() {
        Context context = getContext();
        binding.textViewResult.setText(R.string.activity_main_calculator_result_text);
        binding.textViewCompleteOperation.setText(R.string.activity_main_calculator_empty_operation);
        if (context != null) {
            Toast.makeText(context, context.getString(R.string.calculator_error_division_by_zero), Toast.LENGTH_LONG).show();
        }
    }

    public void showIncompleteOperation() {
        Context context = getContext();
        binding.textViewResult.setText(R.string.activity_main_calculator_result_text);
        binding.textViewCompleteOperation.setText(R.string.activity_main_calculator_empty_operation);
        if (context != null) {
            Toast.makeText(context, context.getString(R.string.calculator_error_incomplete_operation), Toast.LENGTH_SHORT).show();
        }
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

    public void resetResultView() {
        binding.textViewResult.setText(R.string.activity_main_calculator_result_text);
    }
}
