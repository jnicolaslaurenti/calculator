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
    public void onEqualsButtonPressed() {
        Double result= model.getResult();
        if (result != null) {
            view.showValues(result.toString());
        } else {
            view.showError();
        }

    }

    @Override
    public boolean deleteAll() {
        model.reset();
        view.showValues(model.getLastModified());
        view.showDeleteAllMessage();
        return true;
    }

    @Override
    public boolean delete() {
       model.delete();
       view.showValues(model.getLastModified());
       view.showDeleteMessage();
       return true;
    }

}
