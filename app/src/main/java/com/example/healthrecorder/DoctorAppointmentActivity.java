package com.example.healthrecorder;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
        ImageButton backButton = findViewById(R.id.backButton);

        // Initialize appointment list
        appointmentList = new ArrayList<>();

        // Back button click listener
        backButton.setOnClickListener(v -> finish());  // Close the activity (go back)

        // Set click listener for the Check Appointment button
        btnCheckAppointment.setOnClickListener(v -> checkAndDisplayAppointments());
    }

    private void checkAndDisplayAppointments() {
        // Get mock appointments
        appointmentList = getMockAppointments();

        // Check if we have appointments
        if (appointmentList != null && !appointmentList.isEmpty()) {
            tvAppointmentStatus.setText("You have " + appointmentList.size() + " scheduled appointments.");
            layoutAppointmentDetails.removeAllViews();
            layoutAppointmentDetails.setVisibility(View.VISIBLE);

            for (int i = 0; i < appointmentList.size(); i++) {
                Appointment appointment = appointmentList.get(i);
                LinearLayout appointmentBlock = createAppointmentBlock(appointment, i);

                // Set content descriptions for accessibility
                TextView tvSummary = (TextView) appointmentBlock.getChildAt(0); // Get the tvSummary created dynamically
                tvSummary.setContentDescription("Appointment " + (i + 1) + " with " + appointment.getDoctorName());

                // Add block to the layout
                layoutAppointmentDetails.addView(appointmentBlock);
            }
        } else {
            tvAppointmentStatus.setText("No scheduled appointments.");
            layoutAppointmentDetails.setVisibility(View.GONE);
        }
    }

    private LinearLayout createAppointmentBlock(Appointment appointment, int index) {
        // Create appointment block view
        LinearLayout appointmentBlock = new LinearLayout(this);
        appointmentBlock.setOrientation(LinearLayout.VERTICAL);
        appointmentBlock.setPadding(20, 20, 20, 20);
        appointmentBlock.setBackgroundResource(R.drawable.appointment_block_background);

        // Create summary view
        TextView tvSummary = new TextView(this);
        tvSummary.setText("Appointment " + (index + 1) + " - " + appointment.getDoctorName());
        tvSummary.setTextSize(16);
        tvSummary.setTextColor(ContextCompat.getColor(this, android.R.color.black));
        tvSummary.setPadding(20, 20, 20, 20);

        // Create details container (hidden by default)
        LinearLayout detailsContainer = new LinearLayout(this);
        detailsContainer.setOrientation(LinearLayout.VERTICAL);
        detailsContainer.setVisibility(View.GONE);

        // Create and add Doctor's Name TextView
        TextView tvDoctorName = new TextView(this);
        tvDoctorName.setText("Doctor: " + appointment.getDoctorName());
        tvDoctorName.setTextSize(16);
        tvDoctorName.setTextColor(ContextCompat.getColor(this, android.R.color.black));
        detailsContainer.addView(tvDoctorName);

        // Create and add Appointment Time TextView
        TextView tvAppointmentTime = new TextView(this);
        tvAppointmentTime.setText("Time: " + appointment.getAppointmentTime());
        tvAppointmentTime.setTextSize(16);
        tvAppointmentTime.setTextColor(ContextCompat.getColor(this, android.R.color.black));
        detailsContainer.addView(tvAppointmentTime);

        // Create and add Appointment Date TextView
        TextView tvAppointmentDate = new TextView(this);
        tvAppointmentDate.setText("Date: " + appointment.getAppointmentDate());
        tvAppointmentDate.setTextSize(16);
        tvAppointmentDate.setTextColor(ContextCompat.getColor(this, android.R.color.black));
        detailsContainer.addView(tvAppointmentDate);

        // Create and add Clinic Address TextView
        TextView tvClinicAddress = new TextView(this);
        tvClinicAddress.setText("Address: " + appointment.getClinicAddress());
        tvClinicAddress.setTextSize(16);
        tvClinicAddress.setTextColor(ContextCompat.getColor(this, android.R.color.black));
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

        return appointmentBlock;
    }

    private List<Appointment> getMockAppointments() {
        // Creating mock appointments
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment("Dr. Priya Sharma", "10:30 AM", "15th May, 2023", "Apollo Hospital, Greams Road, Chennai", "Scheduled"));
        appointments.add(new Appointment("Dr. Rajesh Kumar", "2:00 PM", "16th May, 2023", "Fortis Healthcare, Sector 62, Noida", "Scheduled"));
        appointments.add(new Appointment("Dr. Anita Rao", "9:00 AM", "17th May, 2023", "Max Super Speciality Hospital, Saket, New Delhi", "Scheduled"));
        return appointments;
    }
}
