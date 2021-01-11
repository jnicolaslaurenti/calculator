package com.onboarding.calculator;

import com.onboarding.calculator.mvp.contract.CalculatorContract;
import com.onboarding.calculator.mvp.model.CalculatorModel;
import com.onboarding.calculator.mvp.presenter.CalculatorPresenter;

import org.junit.Before;
import org.junit.Test;

import static com.onboarding.calculator.util.ConstantsUtils.Error;
import static com.onboarding.calculator.util.ConstantsUtilsTest.ADD;
import static com.onboarding.calculator.util.ConstantsUtilsTest.DIV;
import static com.onboarding.calculator.util.ConstantsUtilsTest.ELEVEN_STRING;
import static com.onboarding.calculator.util.ConstantsUtilsTest.EMPTY_STRING;
import static com.onboarding.calculator.util.ConstantsUtilsTest.FOUR_STRING;
import static com.onboarding.calculator.util.ConstantsUtilsTest.MUL;
import static com.onboarding.calculator.util.ConstantsUtilsTest.ONE_HUNDRED_ELEVEN_STRING;
import static com.onboarding.calculator.util.ConstantsUtilsTest.ONE_RESULT_DOUBLE;
import static com.onboarding.calculator.util.ConstantsUtilsTest.ONE_STRING;
import static com.onboarding.calculator.util.ConstantsUtilsTest.OPERATION_CLEAN_A_VALUE_IN_SECOND_OPERAND;
import static com.onboarding.calculator.util.ConstantsUtilsTest.OPERATION_MINUS_IN_OPERATOR;
import static com.onboarding.calculator.util.ConstantsUtilsTest.OPERATION_MINUS_IN_SECOND_OPERAND;
import static com.onboarding.calculator.util.ConstantsUtilsTest.OPERATION_REPLACE_OPERATOR;
import static com.onboarding.calculator.util.ConstantsUtilsTest.SUB;
import static com.onboarding.calculator.util.ConstantsUtilsTest.THREE_RESULT_DOUBLE;
import static com.onboarding.calculator.util.ConstantsUtilsTest.THREE_STRING;
import static com.onboarding.calculator.util.ConstantsUtilsTest.TWO_RESULT_DOUBLE;
import static com.onboarding.calculator.util.ConstantsUtilsTest.TWO_STRING;
import static com.onboarding.calculator.util.ConstantsUtilsTest.ZERO_STRING;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class CalculatorTest {

    private CalculatorContract.Model model;
    private CalculatorContract.Presenter presenter;
    private CalculatorContract.View view;

    @Before
    public void setup() {
        model = new CalculatorModel();
        view = mock(CalculatorContract.View.class);
        presenter = new CalculatorPresenter(model, view);
    }

    @Test
    public void setACorrectOperator() {
        presenter.onOperatorButtonPressed(ADD);
        assertEquals(ADD, model.getLastModified());
        verify(view).showOperator(model.getLastModified());
        verify(view).operationViewUpdate(model.getLastModified());
        verifyNoMoreInteractions(view);
    }

    @Test
    public void setValuesInFirstOperand() {
        presenter.onCalculatorButtonPressed(ONE_STRING);
        assertEquals(ONE_STRING, model.getLastModified());
        verify(view).showValues(model.getLastModified());
        verify(view).operationViewUpdate(model.getLastModified());
        verifyNoMoreInteractions(view);
    }

    @Test
    public void setValuesInSecondOperand() {
        model.setValues(ONE_STRING);
        model.setOperator(ADD);
        presenter.onCalculatorButtonPressed(TWO_STRING);
        assertEquals(TWO_STRING, model.getLastModified());
        verify(view).showValues(model.getLastModified());
        verify(view).operationViewUpdate(model.getOperation());
        verifyNoMoreInteractions(view);
    }

    @Test
    public void CleanAllTheOperation() {
        presenter.deleteAll();
        assertEquals(EMPTY_STRING, model.getOperation());
        verify(view).resetResultView();
        verify(view).showDeleteAllMessage();
        verify(view).resetOperationView();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void CleanAValueInSecondOperand() {
        model.setValues(ONE_STRING);
        model.setOperator(MUL);
        model.setValues(TWO_STRING);
        model.setValues(THREE_STRING);
        presenter.delete();
        verify(view).resetResultView();
        verify(view).showDeleteMessage();
        verify(view).operationViewUpdate(model.getOperation());
        assertEquals(OPERATION_CLEAN_A_VALUE_IN_SECOND_OPERAND, model.getOperation());
        verifyNoMoreInteractions(view);
    }

    @Test
    public void CleanAValueInFirstOperand() {
        model.setValues(ONE_STRING);
        model.setValues(TWO_STRING);
        presenter.delete();
        verify(view).resetResultView();
        verify(view).showDeleteMessage();
        verify(view).operationViewUpdate(model.getOperation());
        assertEquals(ONE_STRING, model.getOperation());
        verifyNoMoreInteractions(view);
    }

    @Test
    public void CleanOperator() {
        model.setValues(ONE_STRING);
        model.setOperator(MUL);
        presenter.delete();
        verify(view).resetResultView();
        verify(view).showDeleteMessage();
        verify(view).operationViewUpdate(model.getOperation());
        assertEquals(ONE_STRING, model.getOperation());
        verifyNoMoreInteractions(view);
    }

    @Test
    public void minusInFirstOperand() {
        presenter.onSubtractionButtonPressed();
        assertEquals(SUB, model.getOperation());
        verify(view).showValues(model.getLastModified());
        verify(view).operationViewUpdate(model.getOperation());
        verifyNoMoreInteractions(view);
    }

    @Test
    public void minusInOperator() {
        model.setValues(ONE_STRING);
        presenter.onSubtractionButtonPressed();
        assertEquals(OPERATION_MINUS_IN_OPERATOR, model.getOperation());
        verify(view).showValues(model.getLastModified());
        verify(view).operationViewUpdate(model.getOperation());
        verifyNoMoreInteractions(view);
    }

    @Test
    public void minusInSecondOperand() {
        model.setValues(ONE_STRING);
        model.setOperator(MUL);
        presenter.onSubtractionButtonPressed();
        assertEquals(OPERATION_MINUS_IN_SECOND_OPERAND, model.getOperation());
        verify(view).showValues(model.getLastModified());
        verify(view).operationViewUpdate(model.getOperation());
        verifyNoMoreInteractions(view);
    }

    @Test
    public void operationWithoutErrors() {
        model.setValues(ONE_STRING);
        model.setOperator(MUL);
        model.setValues(THREE_STRING);
        Double result = model.getResult();
        presenter.onEqualsButtonPressed();
        assertEquals(THREE_RESULT_DOUBLE, result);
        verify(view).showValues(result.toString());
        verify(view).operationViewUpdate(result.toString());
        verifyNoMoreInteractions(view);
    }

    @Test
    public void ErrorByDivisionByZero() {
        Error error;
        model.setValues(ONE_STRING);
        model.setOperator(DIV);
        model.setValues(ZERO_STRING);
        error = model.getError();
        presenter.onEqualsButtonPressed();
        assertEquals(Error.ERROR_DIVISION_BY_ZERO, error);
        verify(view).showDivisionByZeroError();
        verify(view).resetOperationView();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void ErrorIncompleteOperation() {
        Error error;
        model.setValues(ONE_STRING);
        model.setOperator(DIV);
        error = model.getError();
        presenter.onEqualsButtonPressed();
        assertEquals(Error.ERROR_INCOMPLETE_OPERATION, error);
        verify(view).showIncompleteOperation();
        verify(view).resetOperationView();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void emptyOperator() {
        model.setValues(ONE_STRING);
        assertEquals(ONE_RESULT_DOUBLE, model.getResult());
    }

    @Test
    public void addOperator() {
        model.setValues(ONE_STRING);
        model.setOperator(ADD);
        model.setValues(TWO_STRING);
        assertEquals(THREE_RESULT_DOUBLE, model.getResult());
    }

    @Test
    public void subtractionOperator() {
        model.setValues(THREE_STRING);
        model.setOperator(SUB);
        model.setValues(ONE_STRING);
        assertEquals(TWO_RESULT_DOUBLE, model.getResult());
    }

    @Test
    public void divisionOperator() {
        model.setValues(FOUR_STRING);
        model.setOperator(DIV);
        model.setValues(TWO_STRING);
        assertEquals(TWO_RESULT_DOUBLE, model.getResult());
    }

    @Test
    public void concatenateValuesInFirstOperand() {
        model.setValues(ONE_STRING);
        assertEquals(ONE_STRING, model.getLastModified());
        model.setValues(ONE_STRING);
        assertEquals(ELEVEN_STRING, model.getLastModified());
        model.setValues(ONE_STRING);
        assertEquals(ONE_HUNDRED_ELEVEN_STRING, model.getLastModified());
    }

    @Test
    public void concatenateValuesInSecondOperand() {
        model.setValues(ONE_STRING);
        model.setOperator(MUL);
        model.setValues(ONE_STRING);
        assertEquals(ONE_STRING, model.getLastModified());
        model.setValues(ONE_STRING);
        assertEquals(ELEVEN_STRING, model.getLastModified());
        model.setValues(ONE_STRING);
        assertEquals(ONE_HUNDRED_ELEVEN_STRING, model.getLastModified());
    }

    @Test
    public void replaceOperator() {
        model.setValues(ONE_STRING);
        model.setOperator(MUL);
        model.setValues(TWO_STRING);
        model.setOperator(ADD);
        assertEquals(OPERATION_REPLACE_OPERATOR, model.getOperation());
    }

    @Test
    public void correctlyReturnsTheLastModified() {
        model.setValues(ONE_STRING);
        assertEquals(ONE_STRING, model.getLastModified());
        model.setOperator(MUL);
        assertEquals(MUL, model.getLastModified());
        model.setValues(TWO_STRING);
        assertEquals(TWO_STRING, model.getLastModified());
    }

}
