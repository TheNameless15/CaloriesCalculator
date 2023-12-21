package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StartMenu extends AppCompatActivity {

    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_menu);

        // Initialize UI elements
        calculateButton =(Button) findViewById(R.id.startButton);

        // Set onClickListener for the calculateButton
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartMenu.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
