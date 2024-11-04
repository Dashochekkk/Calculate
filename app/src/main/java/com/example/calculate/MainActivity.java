package com.example.calculate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private double operand1 = Double.NaN;
    private double operand2;
    private char ACTION;
    private boolean isNewInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonDot, R.id.buttonAdd,
                R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide,
                R.id.buttonEquals, R.id.buttonClear
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(this::onButtonClick);
        }
    }

    private void onButtonClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (buttonText) {
            case "C":
                editText.setText("0");
                operand1 = Double.NaN;
                isNewInput = true;
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                handleAction(buttonText.charAt(0));
                break;
            case "=":
                handleEquals();
                break;
            case ".":
                handleDot();
                break;
            default:
                handleNumber(buttonText);
                break;
        }
    }

    private void handleNumber(String number) {
        String currentText = editText.getText().toString();
        if (isNewInput || currentText.equals("0")) {
            editText.setText(number);
        } else {
            editText.append(number);
        }
        isNewInput = false;
    }

    private void handleDot() {
        String currentText = editText.getText().toString();
        if (!currentText.contains(".")) {
            editText.append(".");
        }
    }

    private void handleAction(char action) {
        try {
            if (!Double.isNaN(operand1)) {
                operand2 = Double.parseDouble(editText.getText().toString());
                switch (ACTION) {
                    case '+':
                        operand1 += operand2;
                        break;
                    case '-':
                        operand1 -= operand2;
                        break;
                    case '*':
                        operand1 *= operand2;
                        break;
                    case '/':
                        if (operand2 == 0) {
                            throw new ArithmeticException("Деление на ноль");
                        }
                        operand1 /= operand2;
                        break;
                }
                editText.setText(String.valueOf(operand1));
            } else {
                operand1 = Double.parseDouble(editText.getText().toString());
            }
            ACTION = action;
            isNewInput = true;
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Неверный ввод", Toast.LENGTH_SHORT).show();
        } catch (ArithmeticException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            operand1 = Double.NaN;
            editText.setText("0");
        }
    }

    private void handleEquals() {
        try {
            if (!Double.isNaN(operand1)) {
                operand2 = Double.parseDouble(editText.getText().toString());
                switch (ACTION) {
                    case '+':
                        operand1 += operand2;
                        break;
                    case '-':
                        operand1 -= operand2;
                        break;
                    case '*':
                        operand1 *= operand2;
                        break;
                    case '/':
                        if (operand2 == 0) {
                            throw new ArithmeticException("Деление на ноль");
                        }
                        operand1 /= operand2;
                        break;
                }
                editText.setText(String.valueOf(operand1));
                operand1 = Double.NaN;
                isNewInput = true;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Неверный ввод", Toast.LENGTH_SHORT).show();
        } catch (ArithmeticException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            operand1 = Double.NaN;
            editText.setText("0");
        }
    }
}