package com.example.healthrecorder;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class MeasurementActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageButton backButton, addButton;
    private CardView bloodPressureCard, pulseCard, sugarLevelCard, heightCard, weightCard;
    private TextView bloodPressureText, pulseText, sugarLevelText, heightText, weightText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement);

        // Initialize views
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        backButton = findViewById(R.id.backButton);
        addButton = findViewById(R.id.addButton);
        bloodPressureCard = findViewById(R.id.bloodPressureCard);
        pulseCard = findViewById(R.id.pulseCard);
        sugarLevelCard = findViewById(R.id.sugarLevelCard);
        heightCard = findViewById(R.id.heightCard);
        weightCard = findViewById(R.id.weightCard);

        bloodPressureText = findViewById(R.id.bloodPressureText);
        pulseText = findViewById(R.id.pulseText);
        sugarLevelText = findViewById(R.id.sugarLevelText);
        heightText = findViewById(R.id.heightText);
        weightText = findViewById(R.id.weightText);

        // Set toolbar title
        getSupportActionBar().setTitle("Measurements");

        // Back button listener
        backButton.setOnClickListener(v -> {
            finish(); // Close the activity when back button is pressed
        });

        // Add button listener
        addButton.setOnClickListener(v -> {
            // Handle the add button click (you can add your logic here)
            Toast.makeText(MeasurementActivity.this, "Add button clicked", Toast.LENGTH_SHORT).show();
        });

        // Blood Pressure Card click listener
        bloodPressureCard.setOnClickListener(v -> {
            // Navigate to Blood Pressure details or perform any action
            Toast.makeText(MeasurementActivity.this, "Blood Pressure Card clicked", Toast.LENGTH_SHORT).show();
        });

        // Pulse Card click listener
        pulseCard.setOnClickListener(v -> {
            // Navigate to Pulse details or perform any action
            Toast.makeText(MeasurementActivity.this, "Pulse Card clicked", Toast.LENGTH_SHORT).show();
        });

        // Sugar Level Card click listener
        sugarLevelCard.setOnClickListener(v -> {
            // Navigate to Sugar Level details or perform any action
            Toast.makeText(MeasurementActivity.this, "Sugar Level Card clicked", Toast.LENGTH_SHORT).show();
        });

        // Height Card click listener
        heightCard.setOnClickListener(v -> {
            // Navigate to Height details or perform any action
            Toast.makeText(MeasurementActivity.this, "Height Card clicked", Toast.LENGTH_SHORT).show();
        });

        // Weight Card click listener
        weightCard.setOnClickListener(v -> {
            // Navigate to Weight details or perform any action
            Toast.makeText(MeasurementActivity.this, "Weight Card clicked", Toast.LENGTH_SHORT).show();
        });
    }
}
