package br.com.jeiferson.calculadorapdm20202;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    private TextView tvCalcContent;

    private int CALC_MODE = 0;

    private Button bttPot;
    private Button bttSqr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCalcContent = findViewById(R.id.tvCalcContent);
        bttPot = findViewById(R.id.bttPot);
        bttSqr = findViewById(R.id.bttSqr);
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

        // Adicionar replaces das operações avançadas para a engine do JavaScript entender.

        ScriptEngine sem = new ScriptEngineManager().getEngineByName("js");
        try {
            tvCalcContent.setText(sem.eval(tvCalcContent.getText().toString()).toString());
        } catch (ScriptException se) {
            se.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        if (CALC_MODE == 1) {
            inflater.inflate(R.menu.normal_calc_mode, menu);
        } else {
            inflater.inflate(R.menu.advanced_calc_mode, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.miCalcMode:
                changeCalcMode();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void changeCalcMode() {
        if (CALC_MODE == 0) {
            CALC_MODE = 1;

            bttPot.setEnabled(true);
            bttSqr.setEnabled(true);

            Toast.makeText(this, "Modo Avançado Selecionado!", Toast.LENGTH_SHORT).show();
        } else {
            CALC_MODE = 0;

            bttPot.setEnabled(false);
            bttSqr.setEnabled(false);

            Toast.makeText(this, "Modo Normal Selecionado!", Toast.LENGTH_SHORT).show();
        }

        invalidateOptionsMenu();
    }

}