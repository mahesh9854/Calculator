package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, inputTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.black));
        Objects.requireNonNull(getSupportActionBar()).hide();

        resultTv = findViewById(R.id.result);
        inputTv = findViewById(R.id.finalresult);
        assign(R.id.button_c);
        assign(R.id.button_open);
        assign(R.id.button_close);
        assign(R.id.button_divide);
        assign(R.id.button_7);
        assign(R.id.button_8);
        assign(R.id.button_9);
        assign(R.id.button_multiply);
        assign(R.id.button_4);
        assign(R.id.button_5);
        assign(R.id.button_6);
        assign(R.id.button_minus);
        assign(R.id.button_1);
        assign(R.id.button_2);
        assign(R.id.button_3);
        assign(R.id.button_plus);
        assign(R.id.button_0);
        assign(R.id.button_decimal);
        assign(R.id.button_ac);
        assign(R.id.button_equal);
    }

    void assign(int id) {
        MaterialButton button = findViewById(id);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonTxt = button.getText().toString();
        String data = inputTv.getText().toString();

        if (buttonTxt.equals("AC")) {
            resultTv.setText("0");
            inputTv.setText("");
            return;
        }
        if (buttonTxt.equals("=")) {
            inputTv.setText(resultTv.getText());

            return;
        }
        if (buttonTxt.equals("÷")) {
            buttonTxt = "/";
        }
        if (buttonTxt.equals("×")) {
            buttonTxt = "*";
        }
        if (buttonTxt.equals("C")) {
            data = data.substring(0, data.length() - 1);
        } else {
            data = data + buttonTxt;
        }

        inputTv.setText(data);
        String finalResult = Result(data);
        if (!finalResult.equals("E")) {
            resultTv.setText(finalResult);
        }
    }

    String Result(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String res = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (res.endsWith(".0")) {
                res = res.replace(".0", "");
            }
            return res;
        } catch (Exception e) {
            return "E";
        }
    }
}
