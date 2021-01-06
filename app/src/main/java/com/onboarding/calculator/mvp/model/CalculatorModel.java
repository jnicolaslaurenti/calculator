package com.onboarding.calculator.mvp.model;

import com.onboarding.calculator.mvp.contract.CalculatorContract;

import static com.onboarding.calculator.util.ConstantsUtils.ADD;
import static com.onboarding.calculator.util.ConstantsUtils.CORRECTION_FACTOR;
import static com.onboarding.calculator.util.ConstantsUtils.DEFAULT_RESULT;
import static com.onboarding.calculator.util.ConstantsUtils.DIV;
import static com.onboarding.calculator.util.ConstantsUtils.EMPTY_OPERAND;
import static com.onboarding.calculator.util.ConstantsUtils.EMPTY_STRING;
import static com.onboarding.calculator.util.ConstantsUtils.Error;
import static com.onboarding.calculator.util.ConstantsUtils.FIRST_OPERAND;
import static com.onboarding.calculator.util.ConstantsUtils.MUL;
import static com.onboarding.calculator.util.ConstantsUtils.OPERATOR;
import static com.onboarding.calculator.util.ConstantsUtils.SECOND_OPERAND;
import static com.onboarding.calculator.util.ConstantsUtils.SUB;
import static com.onboarding.calculator.util.ConstantsUtils.ZERO_STRING;

public class CalculatorModel implements CalculatorContract.Model {

    private String operator = EMPTY_STRING;
    private String firstOperand = EMPTY_STRING;
    private String secondOperand = EMPTY_STRING;
    private int switchOp = FIRST_OPERAND;
    private Error error = Error.NONE;

    @Override
    public void reset() {
        switchOp = FIRST_OPERAND;
        operator = EMPTY_STRING;
        firstOperand = EMPTY_STRING;
        secondOperand = EMPTY_STRING;
    }

    @Override
    public void setValues(String value) {
        if (switchOp == FIRST_OPERAND) {
            firstOperand += value;
        } else {
            secondOperand += value;
            switchOp = SECOND_OPERAND;
        }
    }

    public void setOperator(String operator) {
        switchOp = OPERATOR;
        this.operator = operator;
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

    private boolean operandEnabled(String operand) {
        return (!operand.equals(EMPTY_STRING) && !operand.equals(SUB));
    }

    private boolean operationEnabled() {
        return (operandEnabled(firstOperand) && operandEnabled(secondOperand) && !operator.isEmpty());
    }

    @Override
    public Double getResult() {
        Double result = DEFAULT_RESULT;
        if (!firstOperand.equals(EMPTY_STRING) && !firstOperand.equals(SUB)) {
            result = Double.parseDouble(firstOperand);
        }
        if (operationEnabled()) {
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
            result = null;
        }
        reset();
        if (result != null) {
            firstOperand = result.toString();
            operator = EMPTY_STRING;
            switchOp = OPERATOR;
        }
        return result;
    }

    public Error getError() {
        Error aux = error;
        error = Error.NONE;
        return aux;
    }

    public String getOperation() {
        return firstOperand + operator + secondOperand;
    }

    @Override
    public String getLastModified() {
        String lastModified = EMPTY_STRING;
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
