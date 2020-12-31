package com.onboarding.calculator.mvp.contract;

import com.onboarding.calculator.util.ConstantsUtils;

public interface CalculatorContract {

    interface Model {
        void setValues(String value);

        void setOperator(String value);

        String getLastModified();

        Double getResult();

        void manageMinusOperator();

        ConstantsUtils.Error getError();

        void reset();

        void delete();
    }

    interface View {
        void showValues(String value);


        void showOperator(String value);

        void showDeleteAllMessage();

        void showDeleteMessage();

        void resetResultView();

        void showDivisionByZeroError();

        void showIncompleteOperation();
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
