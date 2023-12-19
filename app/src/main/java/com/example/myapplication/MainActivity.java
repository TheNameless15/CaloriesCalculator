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

public class MainActivity extends AppCompatActivity {

    private EditText editTextAge, editTextWeight, editTextHeight;
    private RadioGroup radioGroupGender, radioGroupActivityLevel;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        editTextAge = findViewById(R.id.editTextAge);
        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioGroupActivityLevel = findViewById(R.id.radioGroupActivityLevel);
        calculateButton =(Button) findViewById(R.id.calculateButton);

        // Set onClickListener for the calculateButton
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateCalories();

            }
        });
    }

    private void calculateCalories() {
        // Retrieve user input from UI elements
        int age = Integer.parseInt(editTextAge.getText().toString());
        char gender = getSelectedGender();
        double weight = Double.parseDouble(editTextWeight.getText().toString());
        double height = Double.parseDouble(editTextHeight.getText().toString());
        String activityLevel = getSelectedActivityLevel();

        // Calculate BMR (Basal Metabolic Rate) using Harris-Benedict equation
        double bmr;
        if (gender == 'M') {
            bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else if (gender == 'F') {
            bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        } else {
            showToast("Invalid gender input.");
            return;
        }

        // Adjust BMR based on activity level
        double tdee; // Total Daily Energy Expenditure
        switch (activityLevel.toLowerCase()) {
            case "sedentary":
                tdee = bmr * 1.2;
                break;
            case "lightly active":
                tdee = bmr * 1.375;
                break;
            case "moderately active":
                tdee = bmr * 1.55;
                break;
            case "very active":
                tdee = bmr * 1.725;
                break;
            default:
                showToast("Invalid activity level input.");
                return;
        }

        // Calculate final caloric intake for all goals
        double maintainCalories = tdee * 1.0;
        double bulkCalories = tdee * 1.1;
        double cutCalories = tdee * 0.9;

        // Display results using Toast (you can update this part based on your UI requirements)
        String resultMessage = "Maintenance Calories: " + maintainCalories + " calories per day\n"
                + "Bulking Calories: " + bulkCalories + " calories per day\n"
                + "Cutting Calories: " + cutCalories + " calories per day";


        showToast(resultMessage);

        Intent intent = new Intent(MainActivity.this,ResultActivity.class);
        intent.putExtra("maintainCalories", maintainCalories);
        intent.putExtra("bulkCalories", bulkCalories);
        intent.putExtra("cutCalories", cutCalories);
        startActivity(intent);
    }

    private char getSelectedGender() {
        int selectedId = radioGroupGender.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);

        return radioButton.getText().charAt(0);
    }

    private String getSelectedActivityLevel() {
        int selectedId = radioGroupActivityLevel.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);

        return radioButton.getText().toString();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
