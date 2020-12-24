package com.onboarding.calculator.mvp.model;

import com.onboarding.calculator.mvp.contract.CalculatorContract;

import static com.onboarding.calculator.util.ConstantsUtils.ADD;
import static com.onboarding.calculator.util.ConstantsUtils.CLEAN;
import static com.onboarding.calculator.util.ConstantsUtils.DIV;
import static com.onboarding.calculator.util.ConstantsUtils.EMPTY_STRING;
import static com.onboarding.calculator.util.ConstantsUtils.EQUAL;
import static com.onboarding.calculator.util.ConstantsUtils.MUL;
import static com.onboarding.calculator.util.ConstantsUtils.FIRST_OPERAND;
import static com.onboarding.calculator.util.ConstantsUtils.SECOND_OPERAND;
import static com.onboarding.calculator.util.ConstantsUtils.SUB;

public class CalculatorModel implements CalculatorContract.Model {

    private String operator = EMPTY_STRING;
    private String firstOperand = EMPTY_STRING;
    private String secondOperand = EMPTY_STRING;
    private int switchOp = FIRST_OPERAND;

    private void switchOperand() {
        if (switchOp == SECOND_OPERAND) {
            switchOp = FIRST_OPERAND;
        } else {
            switchOp = SECOND_OPERAND;
        }
    }

    private void addOperand(String value) {
        switch (switchOp) {
            case FIRST_OPERAND: {
                firstOperand += value;
                break;
            }
            case SECOND_OPERAND: {
                secondOperand += value;
                break;
            }
        }
    }

    private void reset() {
        switchOp = FIRST_OPERAND;
        operator = EMPTY_STRING;
        firstOperand = EMPTY_STRING;
        secondOperand = EMPTY_STRING;
    }

    @Override
    public void setValues(String value) {
        switch (value) {
            case EQUAL:
            case CLEAN: {
                reset();
                break;
            }
            case ADD:
            case SUB:
            case DIV:
            case MUL: {
                operator = value;
                switchOperand();
                break;
            }
            default: {
                addOperand(value);
                break;
            }
        }
    }

    @Override
    public String getLastModified() {
        if (!operator.isEmpty() && secondOperand.isEmpty()) {
            return operator;
        } else {
            if (switchOp == FIRST_OPERAND) {
                return firstOperand;
            } else {
                return secondOperand;
            }
        }
    }
}


