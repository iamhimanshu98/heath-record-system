package com.example.healthrecorder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DoctorAppointmentActivity extends AppCompatActivity {

    private TextView tvAppointmentStatus;
    private LinearLayout layoutAppointmentDetails;
    private Button btnCheckAppointment;
    private List<Appointment> appointmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment);

        // Initialize views
        tvAppointmentStatus = findViewById(R.id.tvAppointmentStatus);
        layoutAppointmentDetails = findViewById(R.id.layoutAppointmentDetails);
        btnCheckAppointment = findViewById(R.id.btnCheckAppointment);

        // Initialize appointment list
        appointmentList = new ArrayList<>();

        // Set click listener
        btnCheckAppointment.setOnClickListener(v -> checkAndDisplayAppointments());
    }

    private void checkAndDisplayAppointments() {
        // Get mock appointments
        appointmentList = getMockAppointments();

        // Check if we have appointments
        if (appointmentList != null && !appointmentList.isEmpty()) {
            tvAppointmentStatus.setText("You have " + appointmentList.size() + " scheduled appointments.");

            // Clear previous details and show new ones
            layoutAppointmentDetails.removeAllViews();
            layoutAppointmentDetails.setVisibility(View.VISIBLE);

            // Iterate through the appointments and create a new block for each appointment
            for (int i = 0; i < appointmentList.size(); i++) {
                Appointment appointment = appointmentList.get(i);

                // Create main appointment block container
                LinearLayout appointmentBlock = new LinearLayout(this);
                appointmentBlock.setOrientation(LinearLayout.VERTICAL);
                appointmentBlock.setPadding(20, 20, 20, 20);
                appointmentBlock.setBackgroundResource(R.drawable.appointment_block_background);

                // Set layout parameters with margins
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(20, 20, 20, 20);
                appointmentBlock.setLayoutParams(params);

                // Create summary view (visible by default)
                TextView tvSummary = new TextView(this);
                tvSummary.setText("Appointment " + (i+1) + " - " + appointment.getDoctorName());
                tvSummary.setTextSize(16);
                tvSummary.setTextColor(getResources().getColor(android.R.color.black));
                tvSummary.setPadding(20, 20, 20, 20);

                // Create details container (hidden by default)
                LinearLayout detailsContainer = new LinearLayout(this);
                detailsContainer.setOrientation(LinearLayout.VERTICAL);
                detailsContainer.setVisibility(View.GONE);

                // Create and add Doctor's Name TextView
                TextView tvDoctorName = new TextView(this);
                tvDoctorName.setText("Doctor: " + appointment.getDoctorName());
                tvDoctorName.setTextSize(16);
                tvDoctorName.setTextColor(getResources().getColor(android.R.color.black));
                detailsContainer.addView(tvDoctorName);

                // Create and add Appointment Time TextView
                TextView tvAppointmentTime = new TextView(this);
                tvAppointmentTime.setText("Time: " + appointment.getAppointmentTime());
                tvAppointmentTime.setTextSize(16);
                tvAppointmentTime.setTextColor(getResources().getColor(android.R.color.black));
                detailsContainer.addView(tvAppointmentTime);

                // Create and add Appointment Date TextView
                TextView tvAppointmentDate = new TextView(this);
                tvAppointmentDate.setText("Date: " + appointment.getAppointmentDate());
                tvAppointmentDate.setTextSize(16);
                tvAppointmentDate.setTextColor(getResources().getColor(android.R.color.black));
                detailsContainer.addView(tvAppointmentDate);

                // Create and add Clinic Address TextView
                TextView tvClinicAddress = new TextView(this);
                tvClinicAddress.setText("Address: " + appointment.getClinicAddress());
                tvClinicAddress.setTextSize(16);
                tvClinicAddress.setTextColor(getResources().getColor(android.R.color.black));
                detailsContainer.addView(tvClinicAddress);

                // Add summary and details to the main block
                appointmentBlock.addView(tvSummary);
                appointmentBlock.addView(detailsContainer);

                // Set click listener to toggle details visibility
                appointmentBlock.setOnClickListener(v -> {
                    if (detailsContainer.getVisibility() == View.VISIBLE) {
                        detailsContainer.setVisibility(View.GONE);
                    } else {
                        detailsContainer.setVisibility(View.VISIBLE);
                    }
                });

                // Add the appointment block to the main layout
                layoutAppointmentDetails.addView(appointmentBlock);
            }
        } else {
            tvAppointmentStatus.setText("No scheduled appointments.");
            layoutAppointmentDetails.setVisibility(View.GONE);
        }
    }

    private List<Appointment> getMockAppointments() {
        // Creating mock appointments
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment("Dr. Sarah Johnson", "10:30 AM", "May 15, 2023", "123 Medical Center Blvd, Suite 456, Health City", "Scheduled"));
        appointments.add(new Appointment("Dr. James Smith", "2:00 PM", "May 16, 2023", "456 Health Plaza, Health City", "Scheduled"));
        appointments.add(new Appointment("Dr. Emily Brown", "9:00 AM", "May 17, 2023", "789 Wellness Street, Health City", "Scheduled"));
        return appointments;
    }
}