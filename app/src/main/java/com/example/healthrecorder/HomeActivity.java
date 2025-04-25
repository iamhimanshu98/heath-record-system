package com.example.healthrecorder;
import android.util.Log;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public  class HomeActivity extends AppCompatActivity {

    TextView tvWelcome, tvHealthStatus, tvAppointments;
    Button btnSignOut,btnmeasurementdetails, btnMedicineList,btnviewdetils,btncheckapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize UI components
        tvWelcome = findViewById(R.id.tvWelcome);
        tvHealthStatus = findViewById(R.id.tvHealthStatus);
        tvAppointments = findViewById(R.id.tvAppointments);
        btnSignOut = findViewById(R.id.btnSignOut);
//        btnUploadDocument = findViewById(R.id.btnUploadDocument);
        btnMedicineList = findViewById(R.id.btnMedicineList);
        btnviewdetils=findViewById(R.id.btngetmoreinfo);
        btncheckapp=findViewById(R.id.btnCheckAppointment);
        btnmeasurementdetails=findViewById(R.id.btnmeasurementdetails);
        // Set static welcome message
        tvWelcome.setText("Welcome to Health Tracker!");



        // Dummy Health Status
        tvHealthStatus.setText("✅ Healthy\n💖 BP Normal\n🫁 Oxygen 98%");
        tvAppointments.setText("Prayanjali Sharma\n");
        btnviewdetils.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, GetMoreInfoActivity.class);
            startActivity(intent);
        });
        btncheckapp.setOnClickListener(view -> {
            Log.d("HomeActivity", "Check Appointment button clicked");

            Intent intent = new Intent(HomeActivity.this, DoctorAppointmentActivity.class);
            startActivity(intent);
        });



        // Medicine List Button Click Listener
        btnMedicineList.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, MedicineList.class);
            startActivity(intent);
        });
        btnmeasurementdetails.setOnClickListener(view -> {
            Log.d("NAVIGATION", "Attempting to open MeasurementsActivity");
            try {
                Intent intent = new Intent(HomeActivity.this, MeasurementActivity.class);
                startActivity(intent);
                Log.d("NAVIGATION", "Intent started successfully");
            } catch (Exception e) {
                Log.e("NAVIGATION", "Error starting activity", e);
            }
        });

        // Sign Out Button Click Listener
        btnSignOut.setOnClickListener(view -> {
            // Directly return to MainActivity (No Google Sign Out needed)
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
