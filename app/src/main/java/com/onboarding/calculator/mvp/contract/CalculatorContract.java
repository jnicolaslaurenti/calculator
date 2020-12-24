package com.onboarding.calculator.mvp.contract;

public interface CalculatorContract {

    interface Model {
        void setValues(String value);
        String getLastModified();
    }

    interface View {
        void showValues(String value);
    }

    interface Presenter {
        void onCalculatorButtonPressed(String buttonText);
    }

}
