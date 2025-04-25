package com.example.healthrecorder;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Calendar;

public class GetMoreInfoActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView profileImage;
    private EditText nameInput, genderInput, bloodTypeInput, addressInput, phoneInput, emailInput, birthDateInput;
    private Button saveButton, uploadButton, editButton;
    private boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_more_info);

        profileImage = findViewById(R.id.profile_image);
        nameInput = findViewById(R.id.name_input);
        genderInput = findViewById(R.id.gender_input);
        bloodTypeInput = findViewById(R.id.blood_type_input);
        addressInput = findViewById(R.id.address_input);
        phoneInput = findViewById(R.id.phone_input);
        emailInput = findViewById(R.id.email_input);
        birthDateInput = findViewById(R.id.birth_date_input);
        saveButton = findViewById(R.id.save_button);
        uploadButton = findViewById(R.id.upload_button);
        editButton = findViewById(R.id.edit_button);

        // Pre-fill default input values
        setDefaultPatientData();

        // Disable editing initially (View Mode)
        setInputsEnabled(false);

        uploadButton.setOnClickListener(v -> openImageChooser());
        birthDateInput.setOnClickListener(v -> showDatePicker());
        saveButton.setOnClickListener(v -> savePatientInfo());

        editButton.setOnClickListener(v -> {
            isEditMode = !isEditMode;
            setInputsEnabled(isEditMode);
            editButton.setText(isEditMode ? "Lock Fields" : "Edit Fields");
        });
    }

    private void setDefaultPatientData() {
        nameInput.setText("Prayanjali Sharma");
        genderInput.setText("Female");
        bloodTypeInput.setText("B+");
        addressInput.setText("Alwar, Rajasthan");
        phoneInput.setText("1234567890");
        emailInput.setText("prayanjali@example.com");
        birthDateInput.setText("01/01/2000");
    }

    private void setInputsEnabled(boolean enabled) {
        nameInput.setEnabled(enabled);
        genderInput.setEnabled(enabled);
        bloodTypeInput.setEnabled(enabled);
        addressInput.setEnabled(enabled);
        phoneInput.setEnabled(enabled);
        emailInput.setEnabled(enabled);
        birthDateInput.setEnabled(enabled);
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Profile Image"), PICK_IMAGE_REQUEST);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            Uri imageUri = data.getData();
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
//                profileImage.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profileImage.setImageBitmap(bitmap);

                // Save image URI to SharedPreferences
                getSharedPreferences("UserProfile", MODE_PRIVATE)
                        .edit()
                        .putString("profile_image_uri", imageUri.toString())
                        .apply();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
                    String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    birthDateInput.setText(date);
                }, year, month, day);
        datePickerDialog.show();
    }

    private void savePatientInfo() {
        String name = nameInput.getText().toString().trim();
        String gender = genderInput.getText().toString().trim();
        String bloodType = bloodTypeInput.getText().toString().trim();
        String address = addressInput.getText().toString().trim();
        String phone = phoneInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String birthDate = birthDateInput.getText().toString().trim();

        String info = "Saved Patient Info:\n\n" +
                "Name: " + name + "\n" +
                "Gender: " + gender + "\n" +
                "Blood Type: " + bloodType + "\n" +
                "Address: " + address + "\n" +
                "Phone: " + phone + "\n" +
                "Email: " + email + "\n" +
                "Birth Date: " + birthDate;

        Toast.makeText(this, info, Toast.LENGTH_LONG).show();
    }
}
