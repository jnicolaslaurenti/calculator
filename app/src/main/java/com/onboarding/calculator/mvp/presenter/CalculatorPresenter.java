package com.onboarding.calculator.mvp.presenter;

import com.onboarding.calculator.mvp.model.CalculatorModel;
import com.onboarding.calculator.mvp.view.CalculatorView;

public class CalculatorPresenter {

    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorPresenter(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }

    public void onCalculatorButtonPressed(String buttonText) {
        model.setValues(buttonText);
        view.showValues(model.getResult());
    }

}
