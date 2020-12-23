package com.onboarding.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.onboarding.calculator.databinding.ActivityMainBinding;
import com.onboarding.calculator.mvp.model.CalculatorModel;
import com.onboarding.calculator.mvp.presenter.CalculatorPresenter;
import com.onboarding.calculator.mvp.view.CalculatorView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new CalculatorPresenter(new CalculatorModel(), new CalculatorView(this, binding));
        setListeners();
    }

    private void setListeners(){
        binding.buttonAdd.setOnClickListener(view -> presenter.onCalculatorButtonPressed(binding.buttonAdd.getText().toString()));
        binding.buttonSubtraction.setOnClickListener(view -> presenter.onCalculatorButtonPressed(binding.buttonSubtraction.getText().toString()));
        binding.buttonMultiply.setOnClickListener(view -> presenter.onCalculatorButtonPressed(binding.buttonMultiply.getText().toString()));
        binding.buttonDivision.setOnClickListener(view -> presenter.onCalculatorButtonPressed(binding.buttonDivision.getText().toString()));
        binding.buttonClean.setOnClickListener(view -> presenter.onCalculatorButtonPressed(binding.buttonClean.getText().toString()));
        binding.buttonZero.setOnClickListener(view -> presenter.onCalculatorButtonPressed(binding.buttonZero.getText().toString()));
        binding.buttonOne.setOnClickListener(view -> presenter.onCalculatorButtonPressed(binding.buttonOne.getText().toString()));
        binding.buttonTwo.setOnClickListener(view -> presenter.onCalculatorButtonPressed(binding.buttonTwo.getText().toString()));
        binding.buttonThree.setOnClickListener(view -> presenter.onCalculatorButtonPressed(binding.buttonThree.getText().toString()));
        binding.buttonFour.setOnClickListener(view -> presenter.onCalculatorButtonPressed(binding.buttonFour.getText().toString()));
        binding.buttonFive.setOnClickListener(view -> presenter.onCalculatorButtonPressed(binding.buttonFive.getText().toString()));
        binding.buttonSix.setOnClickListener(view -> presenter.onCalculatorButtonPressed(binding.buttonSix.getText().toString()));
        binding.buttonSeven.setOnClickListener(view -> presenter.onCalculatorButtonPressed(binding.buttonSeven.getText().toString()));
        binding.buttonEight.setOnClickListener(view -> presenter.onCalculatorButtonPressed(binding.buttonEight.getText().toString()));
        binding.buttonNine.setOnClickListener(view -> presenter.onCalculatorButtonPressed(binding.buttonNine.getText().toString()));
        binding.buttonEquals.setOnClickListener(view -> presenter.onCalculatorButtonPressed(binding.buttonEquals.getText().toString()));
    }


}