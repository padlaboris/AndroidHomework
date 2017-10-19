package com.example.padlabear.hw.calculator;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.padlabear.hw.R;

public class CalculatorActivity extends AppCompatActivity {
    private EditText inputFieldEditText;
    private TextView resultTextView;
    private Button calculateButton;
    private Calculator calculator = new DefaultCalculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        initView();
    }

    private void initView() {
        inputFieldEditText = (EditText) findViewById(R.id.input_editText);
        resultTextView = (TextView) findViewById(R.id.result_textView);
        calculateButton = (Button) findViewById(R.id.calculate_button);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String result = calculator.evaluate(inputFieldEditText.getText().toString());
                showResult(result);
            }
        });

        inputFieldEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int length = editable.length();
                if (length > 0) {
                    calculateButton.setEnabled(true);
                } else {
                    calculateButton.setEnabled(false);
                }
            }
        });


    }

    private void showResult(String result) {
        resultTextView.setText(result);
    }
}
