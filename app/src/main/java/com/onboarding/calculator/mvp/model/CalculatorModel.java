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
        error = Error.NONE;
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
        boolean operationEnabled = true;
        if (getError() == Error.ERROR_DIVISION_BY_ZERO || getError() == Error.ERROR_INCOMPLETE_OPERATION) {
            operationEnabled = false;
        }
        return operationEnabled;
    }


    private Double makeOperation() {
        switch (operator) {
            case ADD: {
                return Double.parseDouble(firstOperand) + Double.parseDouble(secondOperand);
            }
            case SUB: {
                return Double.parseDouble(firstOperand) - Double.parseDouble(secondOperand);

            }
            case MUL: {
                return Double.parseDouble(firstOperand) * Double.parseDouble(secondOperand);
            }
            case DIV: {
                return Double.parseDouble(firstOperand) / Double.parseDouble(secondOperand);
            }
        }
        return null;
    }

    @Override
    public Double getResult() {
        Double result = DEFAULT_RESULT;
        if (!firstOperand.equals(EMPTY_STRING)) {
            result = Double.parseDouble(firstOperand);
        }
        if (operationEnabled()) {
            result = makeOperation();
        }
        return result;
    }

    public Error getError() {
        if (!operandEnabled(firstOperand) || !operandEnabled(secondOperand)) {
            error = Error.ERROR_INCOMPLETE_OPERATION;
        }
        if (operandEnabled(firstOperand) && (operator.equals(DIV)) && (secondOperand.equals(ZERO_STRING))) {
            error = Error.ERROR_DIVISION_BY_ZERO;
        }
        return error;
    }

    public String getOperation() {
        return firstOperand + operator + secondOperand;
    }

    @Override
    public String getLastModified() {
        switch (switchOp) {
            case (OPERATOR): {
                return operator;
            }
            case (SECOND_OPERAND): {
                return secondOperand;
            }
            default: {
                return firstOperand;
            }
        }
    }
}
