package com.example.myapplication;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Retrieve calculated values from Intent
        double maintainCalories = getIntent().getDoubleExtra("maintainCalories", 0.0);
        double bulkCalories = getIntent().getDoubleExtra("bulkCalories", 0.0);
        double cutCalories = getIntent().getDoubleExtra("cutCalories", 0.0);

        // Display results in the table
        displayResults(maintainCalories, bulkCalories, cutCalories);
    }

    private void displayResults(double maintainCalories, double bulkCalories, double cutCalories) {
        TableLayout tableLayout = findViewById(R.id.tableLayout);

        // Create header row
        TableRow headerRow = new TableRow(this);
        headerRow.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        String[] headers = {"Goal", "Calories per Day"};
        for (String header : headers) {
            TextView textView = createTextView(header, true);
            headerRow.addView(textView);
        }

        tableLayout.addView(headerRow);

        // Create data rows
        addRow(tableLayout, "Maintenance", String.valueOf(maintainCalories));
        addRow(tableLayout, "Bulking", String.valueOf(bulkCalories));
        addRow(tableLayout, "Cutting", String.valueOf(cutCalories));
    }

    private void addRow(TableLayout tableLayout, String goal, String calories) {
        TableRow row = new TableRow(this);
        row.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        TextView goalTextView = createTextView(goal, false);
        TextView caloriesTextView = createTextView(calories, false);

        row.addView(goalTextView);
        row.addView(caloriesTextView);

        tableLayout.addView(row);
    }

    private TextView createTextView(String text, boolean isHeader) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setGravity(Gravity.CENTER);

        if (isHeader) {
            textView.setBackgroundResource(R.drawable.cell_header_bg); // Customize header background
            textView.setTextColor(getResources().getColor(android.R.color.white));
        } else {
            textView.setBackgroundResource(R.drawable.cell_bg); // Customize cell background
        }

        return textView;
    }
}
