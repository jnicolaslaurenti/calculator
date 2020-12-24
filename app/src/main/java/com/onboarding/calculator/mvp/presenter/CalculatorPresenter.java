package com.onboarding.calculator.mvp.presenter;

import com.onboarding.calculator.mvp.contract.CalculatorContract;
import com.onboarding.calculator.mvp.model.CalculatorModel;
import com.onboarding.calculator.mvp.view.CalculatorView;

public class CalculatorPresenter implements CalculatorContract.Presenter {

    private CalculatorContract.Model model;
    private CalculatorContract.View view;

    public CalculatorPresenter(CalculatorContract.Model model, CalculatorContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onCalculatorButtonPressed(String buttonText) {
        model.setValues(buttonText);
        view.showValues(model.getLastModified());
    }

}
