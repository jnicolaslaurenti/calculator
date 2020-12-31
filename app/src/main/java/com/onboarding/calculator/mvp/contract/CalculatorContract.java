package com.onboarding.calculator.mvp.contract;

public interface CalculatorContract {

    interface Model {
        void setValues(String value);

        void setOperator(String value);

        String getLastModified();

        Double getResult();

        void negativeNumbers();

        String getError();

        void reset();

        void delete();
    }

    interface View {
        void showValues(String value);

        void showError(String error);

        void showOperator(String value);

        void showDeleteAllMessage();

        void showDeleteMessage();

        void resetResultView();
    }

    interface Presenter {
        void onCalculatorButtonPressed(String buttonText);

        void onOperatorButtonPressed(String operator);

        void onSubtractionButtonPressed();

        void onEqualsButtonPressed();

        void deleteAll();

        void delete();
    }

}
