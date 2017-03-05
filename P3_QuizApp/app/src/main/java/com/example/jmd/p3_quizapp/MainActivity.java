package com.example.jmd.p3_quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup yearInception, headQuarter, industryPartner;
    CheckBox cb_q3_cs101, cb_q3_cs50, cb_q3_cs371, cb_q3_cs231,
            cb_q6_bmw, cb_q6_benz, cb_q6_bosch, cb_q6_nvidia, cb_q6_uber;
    EditText editText_YouTuber;
    TextView summaryTextView;
    int mScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //links the class variables to widgets.
        vGetReference();

        //click listener set on the Submit button. Displays the score and provides a summary of the answers.
        Button submitQuiz = (Button) findViewById(R.id.submitQuiz);
        submitQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vComputeScore();
            }
        });

        //resets the widgets and score to their default values.
        Button resetQuiz = (Button) findViewById(R.id.resetQuiz);
        resetQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vResetQuiz();
            }
        });

    }

    private void vComputeScore() {

        int l_yearInceptionID = yearInception.getCheckedRadioButtonId();
        if (l_yearInceptionID == R.id.correctAnswerOne) {
            mScore += 1;
        }

        int l_headQuarter = headQuarter.getCheckedRadioButtonId();
        if (l_headQuarter == R.id.correctAnswerTwo) {
            mScore += 1;
        }

        if (cb_q3_cs101.isChecked() && !cb_q3_cs50.isChecked() && cb_q3_cs371.isChecked() && !cb_q3_cs231.isChecked()) {
            mScore += 1;
        }

        int l_industryPartner = industryPartner.getCheckedRadioButtonId();
        if (l_industryPartner == R.id.correctAnswerFour) {
            mScore += 1;
        }

        String l_editText_YouTuber = editText_YouTuber.getText().toString();
        if (l_editText_YouTuber.equalsIgnoreCase("Siraj Raval")) {
            mScore += 1;
        }

        if (cb_q6_benz.isChecked() && cb_q6_bmw.isChecked() && cb_q6_bosch.isChecked() && cb_q6_nvidia.isChecked()
                && cb_q6_uber.isChecked()) {
            mScore += 1;
        }

        String userMessage = getString(R.string.scoreToast1) + mScore + getString(R.string.scoreToast2);
        Toast.makeText(getApplicationContext(), userMessage, Toast.LENGTH_LONG).show();

        //Shows a summary of the answers. Shown only after the quiz is submitted.
        summaryTextView.setText(getString(R.string.answerSummary));
        summaryTextView.setVisibility(View.VISIBLE);
    }

    private void vResetQuiz() {
        yearInception.clearCheck();
        headQuarter.clearCheck();
        industryPartner.clearCheck();
        cb_q3_cs101.setChecked(false);
        cb_q3_cs50.setChecked(false);
        cb_q3_cs371.setChecked(false);
        cb_q3_cs231.setChecked(false);
        cb_q6_bmw.setChecked(false);
        cb_q6_benz.setChecked(false);
        cb_q6_bosch.setChecked(false);
        cb_q6_nvidia.setChecked(false);
        cb_q6_uber.setChecked(false);
        editText_YouTuber.setText("");
        summaryTextView.setVisibility(View.GONE);
        mScore = 0;
    }

    private void vGetReference() {
        yearInception = (RadioGroup) findViewById(R.id.firstQuestion);
        headQuarter = (RadioGroup) findViewById(R.id.secondQuestion);
        industryPartner = (RadioGroup) findViewById(R.id.fourthQuestion);
        cb_q3_cs101 = (CheckBox) findViewById(R.id.q3_option1);
        cb_q3_cs50 = (CheckBox) findViewById(R.id.q3_option2);
        cb_q3_cs371 = (CheckBox) findViewById(R.id.q3_option3);
        cb_q3_cs231 = (CheckBox) findViewById(R.id.q3_option4);
        cb_q6_bmw = (CheckBox) findViewById(R.id.q6_option1);
        cb_q6_benz = (CheckBox) findViewById(R.id.q6_option2);
        cb_q6_bosch = (CheckBox) findViewById(R.id.q6_option3);
        cb_q6_nvidia = (CheckBox) findViewById(R.id.q6_option4);
        cb_q6_uber = (CheckBox) findViewById(R.id.q6_option5);
        editText_YouTuber = (EditText) findViewById(R.id.a5_editText);
        summaryTextView = (TextView) findViewById(R.id.summaryText);
        summaryTextView.setVisibility(View.GONE);
    }
}
