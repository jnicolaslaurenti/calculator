package com.onboarding.calculator.util;

public class ConstantsUtils {
    public enum Error {
        NONE,
        ERROR_DIVISION_BY_ZERO,
        ERROR_INCOMPLETE_OPERATION
    }

    public static final String EMPTY_STRING = "";
    public static final String ADD = "+";
    public static final String SUB = "-";
    public static final String MUL = "*";
    public static final String DIV = "/";
    public static final String ZERO_STRING = "0";
    public static final int FIRST_OPERAND = 1;
    public static final int OPERATOR = 2;
    public static final int SECOND_OPERAND = 3;
    public static final int EMPTY_OPERAND = 0;
    public static final int CORRECTION_FACTOR = 1;
    public static final double DEFAULT_RESULT = 0.0;

    //TEST

    public static final String ZERO_STRING_TEST = "0";
    public static final String ONE_STRING_TEST = "1";
    public static final String TWO_STRING_TEST = "2";
    public static final String THREE_STRING_TEST = "3";
    public static final String OPERATION_MINUS_IN_SECOND_OPERAND_TEST = "1*-";
    public static final String OPERATION_MINUS_IN_OPERATOR_TEST = "1-";
    public static final String OPERATION_CLEAN_A_VALUE_TEST = "1*2";

}
