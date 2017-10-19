package com.example.padlabear.hw.calculator;

import android.support.compat.BuildConfig;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.padlabear.hw.R;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class CalculatorActivityRobolectricTest {

    private static String EXPRESSION = "2+2";

    private Calculator calculator;
    private ActivityController<CalculatorActivity> activityController;
    private CalculatorActivity calculatorActivity;


    @Before
    public void setUp() {
        activityController = Robolectric.buildActivity(CalculatorActivity.class);
        calculatorActivity = Robolectric.setupActivity(CalculatorActivity.class);
        calculator = mock(DefaultCalculator.class);
    }

    @Ignore
    @Test
    public void calculatorActivityLifeCycleTest() {
        final CalculatorActivity activity = activityController
                .create()
                .start()
                .resume()
                .visible()
                .get();
        final Button calculateButton = (Button)
                activity.findViewById(R.id.calculate_button);
        final EditText inputFieldEditText = (EditText)
                activity.findViewById(R.id.input_editText);
        final TextView resultTextView = (TextView)
                activity.findViewById(R.id.result_textView);

        assertNotNull(calculateButton);
        assertEquals(View.VISIBLE, calculateButton.getVisibility());
        assertNotNull(inputFieldEditText);
        assertEquals(View.VISIBLE, inputFieldEditText.getVisibility());
        assertNotNull(resultTextView);
        assertEquals(View.VISIBLE, resultTextView.getVisibility());

        final Boolean isEnabledButton = calculateButton.isEnabled();
        assertFalse(isEnabledButton);
    }

    @Ignore
    @Test
    public void shouldNotBeNullTest() {
        assertNotNull(calculatorActivity);
    }

    @Ignore
    @Test
    public void shouldEnabledCalculateButtonTest() {
        final Button calculateButton = (Button)
                calculatorActivity.findViewById(R.id.calculate_button);
        final EditText inputFieldEditText = (EditText)
                calculatorActivity.findViewById(R.id.input_editText);

        assertFalse(calculateButton.isEnabled());
        inputFieldEditText.setText(EXPRESSION);
        assertTrue(calculateButton.isEnabled());
    }

    @Ignore
    @Test
    public void shouldAppearResultAfterCalculateButtonClick() {
        final String expectedResult = "4";
        final Button calculateButton = (Button)
                calculatorActivity.findViewById(R.id.calculate_button);
        final EditText inputFieldEditText = (EditText)
                calculatorActivity.findViewById(R.id.input_editText);
        final TextView resultTextView = (TextView)
                calculatorActivity.findViewById(R.id.result_textView);

        when(calculator.evaluate(anyString())).thenReturn(expectedResult);

        inputFieldEditText.setText(EXPRESSION);
        assertTrue(calculateButton.isEnabled());
        calculateButton.performClick();

        final String actualResult = resultTextView.getText().toString();
        assertEquals(expectedResult, actualResult);
    }


//    @After
//    public void tearDown() {
//        activityController
//                .pause()
//                .stop()
//                .destroy();
//    }
}
