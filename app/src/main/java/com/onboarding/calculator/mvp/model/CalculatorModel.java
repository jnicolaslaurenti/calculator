package com.onboarding.calculator.mvp.model;

import static com.onboarding.calculator.util.Util.ADD;
import static com.onboarding.calculator.util.Util.CLEAN;
import static com.onboarding.calculator.util.Util.DIV;
import static com.onboarding.calculator.util.Util.EMPTY_STRING;
import static com.onboarding.calculator.util.Util.EQUAL;
import static com.onboarding.calculator.util.Util.MUL;
import static com.onboarding.calculator.util.Util.OPERAND1;
import static com.onboarding.calculator.util.Util.OPERAND2;
import static com.onboarding.calculator.util.Util.SUB;

public class CalculatorModel {

    private String operator;
    private String operand1;
    private String operand2;
    private int switchOp;

    public CalculatorModel() {
        reset();
    }

    private void switchOperand() {
        if (switchOp == OPERAND2)
            switchOp = OPERAND1;
        else
            switchOp = OPERAND2;
    }

    private void addOperand(String value) {
        switch (switchOp) {
            case OPERAND1: {
                operand1 += value;
                break;
            }
            case OPERAND2: {
                operand2 += value;
                break;
            }
        }
    }

    private void reset() {
        switchOp = OPERAND1;
        operator = EMPTY_STRING;
        operand1 = EMPTY_STRING;
        operand2 = EMPTY_STRING;
    }

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
            default:
                addOperand(value);
                break;
        }
    }

    public String getResult() {
        if (!operator.isEmpty() && (operand2.isEmpty())) {
            return operator;
        } else {
            if (switchOp == OPERAND1) {
                return operand1;
            } else {
                return operand2;
            }
        }
    }
}


