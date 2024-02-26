package com.msaggik.fourthlessonsystemofequations;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String WRONG_SOLUTION = "Текущее решение неправильное";
    private static final String WRONG_SOLUTION_X1 = "Введено неправильное значение X1";
    private static final String WRONG_SOLUTION_X2 = "Введено неправильное значение X2";
    private static final String WRONG_X1_X2 = "Введено неправильное значение X1/X2";

    private float a;
    private float b;
    private float c;
    private int x1;
    private int x2;
    private TextView equation;
    private EditText inputX1;
    private EditText inputX2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        equation = findViewById(R.id.equation);
        inputX1 = findViewById(R.id.inputX1);
        inputX2 = findViewById(R.id.inputX2);
        Button button = findViewById(R.id.button);

        randomCoefficient(100);

        equationSetText(a, b, c);

        x1 = (int) Math.round((Math.sqrt(Math.abs(b * b - 4 * a * c)) - b) / (2 * a));
        x2 = (int) Math.round((-Math.sqrt(Math.abs(b * b - 4 * a * c)) - b) / (2 * a));

        inputX1.setOnFocusChangeListener(focusListener);
        inputX2.setOnFocusChangeListener(focusListener);
        button.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            try {
                int inX2 = Integer.parseInt(inputX2.getText().toString());
                int inX1 = Integer.parseInt(inputX1.getText().toString());
                if (x1 == inX1 && x2 == inX2) {
                    randomCoefficient(100);
                    equationSetText(a, b, c);
                    x1 = (int) Math.round((Math.sqrt(Math.abs(b * b - 4 * a * c)) - b) / (2 * a));
                    x2 = (int) Math.round((-Math.sqrt(Math.abs(b * b - 4 * a * c)) - b) / (2 * a));
                } else {
                    Toast.makeText(MainActivity.this, WRONG_SOLUTION, Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, WRONG_X1_X2, Toast.LENGTH_SHORT).show();
            }
        }
    };

    private final View.OnFocusChangeListener focusListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            switch (view.getId()) {
                case R.id.inputX1:
                    if (!b) {
                        int inX1 = Integer.parseInt(inputX1.getText().toString());
                        if (x1 != inX1 | x2 != inX1) {
                            inputX1.setTextColor(Color.RED);
                            Toast.makeText(MainActivity.this, WRONG_SOLUTION_X1, Toast.LENGTH_SHORT).show();
                        } else {
                            inputX1.setTextColor(0xFF177C3A);
                        }
                    }
                    break;
                case R.id.inputX2:
                    if (!b) {
                        int inX2 = Integer.parseInt(inputX2.getText().toString());
                        if (x1 != inX2 | x2 != inX2) {
                            inputX2.setTextColor(Color.RED);
                            Toast.makeText(MainActivity.this, WRONG_SOLUTION_X2, Toast.LENGTH_SHORT).show();
                        } else {
                            inputX2.setTextColor(0xFF177C3A);
                        }
                    }
                    break;
            }
        }
    };

    private void randomCoefficient(int limit) {
        Random random = new Random();
        a = random.nextInt(limit);
        b = random.nextInt(limit);
        c = random.nextInt(limit);
    }

    private void equationSetText(float a, float b, float c) {
        equation.setText(String.valueOf(a));
        equation.append("*X^2 + ");
        equation.append(String.valueOf(b));
        equation.append("*X + ");
        equation.append(String.valueOf(c));
        equation.append(" = 0");
    }
}