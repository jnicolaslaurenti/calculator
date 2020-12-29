package com.onboarding.calculator.mvp.contract;

public interface CalculatorContract {

    interface Model {
        void setValues(String value);

        String getLastModified();

        Double getResult();

        void reset();

        void delete();
    }

    interface View {
        void showValues(String value);

        void showError();

        void showDeleteAllMessage();

        void showDeleteMessage();
    }

    interface Presenter {
        void onCalculatorButtonPressed(String buttonText);

        void onEqualsButtonPressed();

        void deleteAll();

        void delete();
    }

}
