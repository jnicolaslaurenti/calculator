package com.onboarding.calculator.mvp.model;

import com.onboarding.calculator.mvp.contract.CalculatorContract;

import static com.onboarding.calculator.util.ConstantsUtils.ADD;
import static com.onboarding.calculator.util.ConstantsUtils.CLEAN;
import static com.onboarding.calculator.util.ConstantsUtils.CORRECTION_FACTOR;
import static com.onboarding.calculator.util.ConstantsUtils.DEFAULT_RESULT;
import static com.onboarding.calculator.util.ConstantsUtils.DIV;
import static com.onboarding.calculator.util.ConstantsUtils.EMPTY_OPERAND;
import static com.onboarding.calculator.util.ConstantsUtils.EMPTY_STRING;
import static com.onboarding.calculator.util.ConstantsUtils.FIRST_OPERAND;
import static com.onboarding.calculator.util.ConstantsUtils.MUL;
import static com.onboarding.calculator.util.ConstantsUtils.SECOND_OPERAND;
import static com.onboarding.calculator.util.ConstantsUtils.SUB;
import static com.onboarding.calculator.util.ConstantsUtils.ZERO_STRING;

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

    @Override
    public void reset() {
        switchOp = FIRST_OPERAND;
        operator = EMPTY_STRING;
        firstOperand = EMPTY_STRING;
        secondOperand = EMPTY_STRING;
    }

    @Override
    public void setValues(String value) {
        switch (value) {
            case CLEAN: {
                delete();
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

    public void delete() {
        if (!operator.isEmpty() && secondOperand.isEmpty()) {
            operator = EMPTY_STRING;
        } else
            switch (switchOp) {
                case FIRST_OPERAND: {
                    if (firstOperand.length() > EMPTY_OPERAND) {
                        firstOperand = firstOperand.substring(EMPTY_OPERAND, firstOperand.length() - CORRECTION_FACTOR);
                    }
                    break;
                }
                case SECOND_OPERAND: {
                    if (secondOperand.length() > EMPTY_OPERAND) {
                        secondOperand = secondOperand.substring(EMPTY_OPERAND, secondOperand.length() - CORRECTION_FACTOR);
                    }
                    break;
                }
            }
    }

    @Override
    public Double getResult() {
        Double result = DEFAULT_RESULT;
        switch (operator) {
            case ADD: {
                result = Double.parseDouble(firstOperand) + Double.parseDouble(secondOperand);
                break;
            }
            case SUB: {
                result = Double.parseDouble(firstOperand) - Double.parseDouble(secondOperand);
                break;
            }
            case MUL: {
                result = Double.parseDouble(firstOperand) * Double.parseDouble(secondOperand);
                break;
            }
            case DIV: {
                if (!secondOperand.equals(ZERO_STRING)) {
                    result = Double.parseDouble(firstOperand) / Double.parseDouble(secondOperand);
                } else {
                    result = null;
                }
            }
            break;
        }
        reset();
        return result;
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
