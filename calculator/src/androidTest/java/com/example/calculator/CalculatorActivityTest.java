package com.example.calculator;


import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class CalculatorActivityTest {

    private ViewInteraction calculateButton;
    private ViewInteraction inputEditText;
    private ViewInteraction editText;

    @Rule
    public ActivityTestRule<CalculatorActivity> calculatorActivity =
            new ActivityTestRule<>(CalculatorActivity.class);

    @Before
    public void setUp() {
        calculateButton = onView(withId(R.id.calculate_button));
        inputEditText = onView(withId(R.id.input_editText));
        editText = onView(withId(R.id.result_textView));
    }

    @Test
    public void CalculatorActivityTest() {
        calculateButton.check(matches(isDisplayed()));
        inputEditText.check(matches(isDisplayed()));
        editText.check(matches(isDisplayed()));
        calculateButton.check(new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException noViewFoundException) {
                if (view.isEnabled()) {
                    throw new IllegalStateException("button is enabled!!!");
                }
            }
        });

        inputEditText.perform(typeText("2+2"));
        calculateButton.check(matches(isEnabled()));
        calculateButton.perform(click());

        editText.check(new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException noViewFoundException) {
                if (!((TextView) view).getText().toString().equals("4")) {
                    throw new IllegalStateException("Result is wrong. Expected result 4");
                }
            }
        });
    }
}
