package com.onboarding.calculator;

import com.onboarding.calculator.mvp.contract.CalculatorContract;
import com.onboarding.calculator.mvp.model.CalculatorModel;
import com.onboarding.calculator.mvp.presenter.CalculatorPresenter;

import org.junit.Before;
import org.junit.Test;

import static com.onboarding.calculator.util.ConstantsUtils.ADD;
import static com.onboarding.calculator.util.ConstantsUtils.DIV;
import static com.onboarding.calculator.util.ConstantsUtils.EMPTY_STRING;
import static com.onboarding.calculator.util.ConstantsUtils.Error;
import static com.onboarding.calculator.util.ConstantsUtils.MUL;
import static com.onboarding.calculator.util.ConstantsUtils.ONE_STRING_TEST;
import static com.onboarding.calculator.util.ConstantsUtils.OPERATION_CLEAN_A_VALUE_IN_SECOND_OPERAND_TEST;
import static com.onboarding.calculator.util.ConstantsUtils.OPERATION_CLEAN_OPERATOR_TEST;
import static com.onboarding.calculator.util.ConstantsUtils.OPERATION_MINUS_IN_OPERATOR_TEST;
import static com.onboarding.calculator.util.ConstantsUtils.OPERATION_MINUS_IN_SECOND_OPERAND_TEST;
import static com.onboarding.calculator.util.ConstantsUtils.SUB;
import static com.onboarding.calculator.util.ConstantsUtils.THREE_STRING_TEST;
import static com.onboarding.calculator.util.ConstantsUtils.TWO_STRING_TEST;
import static com.onboarding.calculator.util.ConstantsUtils.ZERO_STRING_TEST;
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
        //Test
        presenter.onOperatorButtonPressed(ADD);
        //Assert
        assertEquals(ADD, model.getLastModified());
        verify(view).showOperator(model.getLastModified());
        verify(view).operationViewUpdate(model.getLastModified());
        verifyNoMoreInteractions(view);
    }

    @Test
    public void setValuesInFirstOperand() {
        //Test
        presenter.onCalculatorButtonPressed(ONE_STRING_TEST);
        //Assert
        assertEquals(ONE_STRING_TEST, model.getLastModified());
        verify(view).showValues(model.getLastModified());
        verify(view).operationViewUpdate(model.getLastModified());
        verifyNoMoreInteractions(view);
    }

    @Test
    public void setValuesInSecondOperand() {
        //Set
        model.setValues(ONE_STRING_TEST);
        model.setOperator(ADD);
        //Test
        presenter.onCalculatorButtonPressed(TWO_STRING_TEST);
        //Assert
        assertEquals(TWO_STRING_TEST, model.getLastModified());
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
        model.setValues(ONE_STRING_TEST);
        model.setOperator(MUL);
        model.setValues(TWO_STRING_TEST);
        model.setValues(THREE_STRING_TEST);
        presenter.delete();
        verify(view).resetResultView();
        verify(view).showDeleteMessage();
        verify(view).operationViewUpdate(model.getOperation());
        assertEquals(OPERATION_CLEAN_A_VALUE_IN_SECOND_OPERAND_TEST, model.getOperation());
        verifyNoMoreInteractions(view);
    }

    @Test
    public void CleanAValueInFirstOperand() {
        model.setValues(ONE_STRING_TEST);
        model.setValues(TWO_STRING_TEST);
        presenter.delete();
        verify(view).resetResultView();
        verify(view).showDeleteMessage();
        verify(view).operationViewUpdate(model.getOperation());
        assertEquals(ONE_STRING_TEST, model.getOperation());
        verifyNoMoreInteractions(view);
    }

    @Test
    public void CleanOperator() {
        model.setValues(ONE_STRING_TEST);
        model.setOperator(MUL);
        presenter.delete();
        verify(view).resetResultView();
        verify(view).showDeleteMessage();
        verify(view).operationViewUpdate(model.getOperation());
        assertEquals(ONE_STRING_TEST, model.getOperation());
        verifyNoMoreInteractions(view);
    }

    @Test
    public void minusInFirstOperand() {
        //Set
        //Test
        presenter.onSubtractionButtonPressed();
        //Assert
        assertEquals(SUB, model.getOperation());
        verify(view).showValues(model.getLastModified());
        verify(view).operationViewUpdate(model.getOperation());
        verifyNoMoreInteractions(view);
    }

    @Test
    public void minusInOperator() {
        //Set
        model.setValues(ONE_STRING_TEST);
        //Test
        presenter.onSubtractionButtonPressed();
        //Assert
        assertEquals(OPERATION_MINUS_IN_OPERATOR_TEST, model.getOperation());
        verify(view).showValues(model.getLastModified());
        verify(view).operationViewUpdate(model.getOperation());
        verifyNoMoreInteractions(view);
    }

    @Test
    public void minusInSecondOperand() {
        //Set
        model.setValues(ONE_STRING_TEST);
        model.setOperator(MUL);
        //Test
        presenter.onSubtractionButtonPressed();
        //Assert
        assertEquals(OPERATION_MINUS_IN_SECOND_OPERAND_TEST, model.getOperation());
        verify(view).showValues(model.getLastModified());
        verify(view).operationViewUpdate(model.getOperation());
        verifyNoMoreInteractions(view);
    }

    @Test
    public void operationWithoutErrors() {
        //Set
        model.setValues(ONE_STRING_TEST);
        model.setOperator(MUL);
        model.setValues(TWO_STRING_TEST);
        Double result = model.getResult();
        //Test
        presenter.onEqualsButtonPressed();
        //Assert
        assertEquals(Double.valueOf(2), result);
        verify(view).showValues(result.toString());
        verify(view).operationViewUpdate(result.toString());
        verifyNoMoreInteractions(view);
    }

    @Test
    public void ErrorByDivisionByZero() {
        //Set
        Error error;
        model.setValues(ONE_STRING_TEST);
        model.setOperator(DIV);
        model.setValues(ZERO_STRING_TEST);
        error = model.getError();
        //Test
        presenter.onEqualsButtonPressed();
        //Assert
        assertEquals(Error.ERROR_DIVISION_BY_ZERO, error);
        verify(view).showDivisionByZeroError();
        verify(view).resetOperationView();
        verifyNoMoreInteractions(view);
    }

    @Test
    public void ErrorIncompleteOperation() {
        //Set
        Error error;
        model.setValues(ONE_STRING_TEST);
        model.setOperator(DIV);
        error = model.getError();
        //Test
        presenter.onEqualsButtonPressed();
        //Assert
        assertEquals(Error.ERROR_INCOMPLETE_OPERATION, error);
        verify(view).showIncompleteOperation();
        verify(view).resetOperationView();
        verifyNoMoreInteractions(view);
    }
}
