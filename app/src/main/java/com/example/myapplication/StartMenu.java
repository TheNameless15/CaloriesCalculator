package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartMenu extends AppCompatActivity {


    private Button calculateButton;

    private static final int SPLASH_DELAY = 2000; // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_menu);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        // Initialize UI elements
/*
        calculateButton =(Button) findViewById(R.id.startButton);
*/

        // Set onClickListener for the calculateButton
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the MainActivity after the splash delay
                Intent intent = new Intent(StartMenu.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close the splash screen to prevent going back to it
            }
        }, SPLASH_DELAY);
    }
}
