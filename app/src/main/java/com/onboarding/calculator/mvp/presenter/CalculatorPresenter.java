package com.onboarding.calculator.mvp.presenter;

import com.onboarding.calculator.mvp.contract.CalculatorContract;

public class CalculatorPresenter implements CalculatorContract.Presenter {

    private final CalculatorContract.Model model;
    private final CalculatorContract.View view;

    public CalculatorPresenter(CalculatorContract.Model model, CalculatorContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onCalculatorButtonPressed(String buttonText) {
        model.setValues(buttonText);
        view.showValues(model.getLastModified());
    }

    @Override
    public void onOperatorButtonPressed(String buttonText) {
        model.setOperator(buttonText);
        view.showOperator(model.getOperator());
    }

    @Override
    public void onEqualsButtonPressed() {
        Double result = model.getResult();
        if (result != null) {
            view.showValues(result.toString());
        } else {
            view.showError();
        }

    }

    @Override
    public void deleteAll() {
        model.reset();
        view.reset();
        view.showDeleteAllMessage();
    }

    @Override
    public void delete() {
        model.delete();
        view.reset();
        view.showDeleteMessage();
    }

}
