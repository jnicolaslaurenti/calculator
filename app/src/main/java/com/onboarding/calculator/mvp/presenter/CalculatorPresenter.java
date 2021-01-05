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
        view.OperationViewUpdate(model.getOperation());
    }

    @Override
    public void onOperatorButtonPressed(String buttonText) {
        model.setOperator(buttonText);
        view.showOperator(model.getLastModified());
        view.OperationViewUpdate(model.getOperation());
    }

    @Override
    public void onSubtractionButtonPressed() {
        model.manageMinusOperator();
        view.showValues(model.getLastModified());
        view.OperationViewUpdate(model.getOperation());
    }

    @Override
    public void onEqualsButtonPressed() {
        Double result = model.getResult();
        switch (model.getError()) {
            case NONE: {
                view.showValues(result.toString());
                view.OperationViewUpdate(model.getOperation());
                break;
            }
            case ERROR_DIVISION_BY_ZERO: {
                view.showDivisionByZeroError();
                view.resetOperationView();
                break;
            }
            case ERROR_INCOMPLETE_OPERATION: {
                view.showIncompleteOperation();
                view.resetOperationView();
                break;
            }
        }
    }

    @Override
    public void deleteAll() {
        model.reset();
        view.resetResultView();
        view.showDeleteAllMessage();
        view.resetOperationView();
    }

    @Override
    public void delete() {
        model.delete();
        view.resetResultView();
        view.showDeleteMessage();
        view.resetOperationView();
    }

}
