package br.com.jeiferson.calculadorapdm20202;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvSelectedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSelectedButton = findViewById(R.id.tvSelectedButton);
    }

    public void onClick(View view) {
        if (view instanceof Button) {
            tvSelectedButton.setText(((Button) view).getText());
        }
    }
}