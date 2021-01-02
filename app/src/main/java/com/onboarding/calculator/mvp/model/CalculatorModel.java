package com.onboarding.calculator.mvp.model;

import com.onboarding.calculator.mvp.contract.CalculatorContract;
import com.onboarding.calculator.util.ConstantsUtils;

import static com.onboarding.calculator.util.ConstantsUtils.ADD;
import static com.onboarding.calculator.util.ConstantsUtils.CLEAN;
import static com.onboarding.calculator.util.ConstantsUtils.CORRECTION_FACTOR;
import static com.onboarding.calculator.util.ConstantsUtils.DEFAULT_RESULT;
import static com.onboarding.calculator.util.ConstantsUtils.DIV;
import static com.onboarding.calculator.util.ConstantsUtils.EMPTY_OPERAND;
import static com.onboarding.calculator.util.ConstantsUtils.EMPTY_STRING;
import static com.onboarding.calculator.util.ConstantsUtils.FIRST_OPERAND;
import static com.onboarding.calculator.util.ConstantsUtils.MUL;
import static com.onboarding.calculator.util.ConstantsUtils.OPERATOR;
import static com.onboarding.calculator.util.ConstantsUtils.SECOND_OPERAND;
import static com.onboarding.calculator.util.ConstantsUtils.SUB;
import static com.onboarding.calculator.util.ConstantsUtils.ZERO_RESULT_STRING;
import static com.onboarding.calculator.util.ConstantsUtils.ZERO_STRING;
import static com.onboarding.calculator.util.ConstantsUtils.Error;

public class CalculatorModel implements CalculatorContract.Model {

    private String operator = EMPTY_STRING;
    private String firstOperand = EMPTY_STRING;
    private String secondOperand = EMPTY_STRING;
    private int switchOp = FIRST_OPERAND;
    private Error error = Error.NONE;

    private void addOperand(String value) {
        if (switchOp == FIRST_OPERAND) {
            if (firstOperand.isEmpty()) {
                firstOperand += value;
            } else {
                firstOperand = value;
            }
        } else {
            switchOp = SECOND_OPERAND;
            if (!secondOperand.equals(ZERO_RESULT_STRING)) {
                secondOperand += value;
            } else {
                secondOperand = value;
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
        if (value.equals(CLEAN)) {
            delete();
        } else {
            addOperand(value);
        }
    }

    public void setOperator(String operator) {
        switchOp = OPERATOR;
        if (!firstOperand.equals(EMPTY_STRING)) {
            this.operator = operator;
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

    public void manageMinusOperator() {
        if (firstOperand.isEmpty()) {
            firstOperand = SUB;
            switchOp = FIRST_OPERAND;
        } else {
            if (operator.isEmpty()) {
                setOperator(SUB);
                switchOp = OPERATOR;
            } else {
                if (secondOperand.isEmpty()) {
                    secondOperand = SUB;
                    switchOp = SECOND_OPERAND;
                }
            }
        }
    }

    @Override
    public Double getResult() {
        Double result = DEFAULT_RESULT;
        if (!firstOperand.equals(EMPTY_STRING)) {
            result = Double.parseDouble(firstOperand);
        }
        if (!firstOperand.isEmpty() || !secondOperand.isEmpty()) {
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
                        error = Error.ERROR_DIVISION_BY_ZERO;
                    }
                    break;
                }
                case EMPTY_STRING: {
                    result = Double.parseDouble(firstOperand);
                    break;
                }
            }
        } else {
            error = Error.ERROR_INCOMPLETE_OPERATION;
        }
        reset();
        if (result != null) {
            firstOperand = result.toString();
        }
        return result;
    }

    public Error getError() {
        Error aux = error;
        error = Error.NONE;
        return aux;
    }

    @Override
    public String getLastModified() {
        String lastModified = "";
        switch (switchOp) {
            case (FIRST_OPERAND): {
                lastModified = firstOperand;
                break;
            }
            case (OPERATOR): {
                lastModified = operator;
                break;
            }
            case (SECOND_OPERAND): {
                lastModified = secondOperand;
                break;
            }
        }
        return lastModified;
    }
}
