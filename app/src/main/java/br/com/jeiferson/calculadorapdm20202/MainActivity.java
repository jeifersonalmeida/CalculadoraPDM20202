package br.com.jeiferson.calculadorapdm20202;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    private TextView tvCalcContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCalcContent = findViewById(R.id.tvCalcContent);
    }

    public void addNumber(View view) {
        String concatenated = tvCalcContent.getText().toString() + ((Button) view).getText();
        tvCalcContent.setText(concatenated);
    }

    public void addOperation(View view) {
        String content = tvCalcContent.getText().toString();

        System.out.println(content.matches("^.*[0-9]+$"));
        if (content.length() == 0 || content.matches("^.*[0-9]+$") == false) {
            return;
        }

        String operation = ((Button) view).getText().toString();
        tvCalcContent.setText(tvCalcContent.getText() + operation);
    }

    public void addDot(View view) {
        String content = tvCalcContent.getText().toString();

        System.out.println(content.matches("^.*\\.[0-9]*$"));
        if (content.matches("^.*\\.[0-9]*$")) {
            return;
        }

        String operation = ((Button) view).getText().toString();
        tvCalcContent.setText(tvCalcContent.getText() + operation);
    }

    public void clear(View view) {
        tvCalcContent.setText("");
    }

    public void calc(View view) {
        if (tvCalcContent.getText().toString().isEmpty()) {
            return;
        }

        ScriptEngine sem = new ScriptEngineManager().getEngineByName("js");
        try {
            tvCalcContent.setText(sem.eval(tvCalcContent.getText().toString()).toString());
        } catch (ScriptException se) {
            se.printStackTrace();
        }
    }

}