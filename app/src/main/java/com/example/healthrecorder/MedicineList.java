package com.example.healthrecorder;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MedicineList extends AppCompatActivity {
    private static final String TAG = "MedicineList";

    private ArrayList<String> medicines;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_medicine_list);
            Log.d(TAG, "Layout set successfully");

            ImageButton backButton = findViewById(R.id.backButton);
            if (backButton != null) {
                backButton.setOnClickListener(v -> {
                    Log.d(TAG, "Back button clicked");
                    finish();
                });
            } else {
                Log.e(TAG, "Back button not found in layout");
            }

            medicines = new ArrayList<>();

            // Initialize ListView
            ListView listView = findViewById(R.id.listViewMedicines);
            if (listView == null) {
                Log.e(TAG, "ListView not found in layout");
                throw new IllegalStateException("ListView not found in layout");
            }
            Log.d(TAG, "ListView initialized successfully");

            // Initialize Add Medicine Button
            Button btnAddMedicine = findViewById(R.id.btnAddMedicine);
            if (btnAddMedicine == null) {
                Log.e(TAG, "Add Medicine button not found in layout");
                throw new IllegalStateException("Add Medicine button not found in layout");
            }
            Log.d(TAG, "Add Medicine button initialized successfully");

            // Initialize adapter
            try {
                adapter = new ArrayAdapter<String>(this, R.layout.list_item_medicine, R.id.medicine_name, medicines) {
                    @Override
                    public View getView(final int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        if (view != null) {
                            Button deleteButton = view.findViewById(R.id.btnDelete);
                            if (deleteButton != null) {
                                // Remove any existing click listeners to prevent memory leaks
                                deleteButton.setOnClickListener(null);
                                // Set new click listener
                                deleteButton.setOnClickListener(v -> {
                                    Log.d(TAG, "Delete button clicked for position: " + position);
                                    showDeleteConfirmation(position);
                                });
                            } else {
                                Log.e(TAG, "Delete button not found in list item view");
                            }
                        } else {
                            Log.e(TAG, "List item view is null");
                        }
                        return view;
                    }
                };
                Log.d(TAG, "Adapter initialized successfully");
            } catch (Exception e) {
                Log.e(TAG, "Error initializing adapter: " + e.getMessage(), e);
                throw e;
            }

            listView.setAdapter(adapter);
            Log.d(TAG, "Adapter set to ListView");

            // Add medicine button click
            btnAddMedicine.setOnClickListener(v -> {
                Log.d(TAG, "Add Medicine button clicked");
                showAddMedicineDialog();
            });

            // Long click to delete a medicine
            listView.setOnItemLongClickListener((parent, view, position, id) -> {
                Log.d(TAG, "Long click on item at position: " + position);
                showDeleteConfirmation(position);
                return true;
            });

            Log.d(TAG, "All initialization completed successfully");
        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate: " + e.getMessage(), e);
            Toast.makeText(this, "Error initializing activity: " + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void showAddMedicineDialog() {
        Log.d(TAG, "Showing add medicine dialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add New Medicine");

        final EditText input = new EditText(this);
        input.setHint("Enter medicine name");
        builder.setView(input);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String medicine = input.getText().toString().trim();
            if (!medicine.isEmpty()) {
                medicines.add(medicine);
                adapter.notifyDataSetChanged();
                Log.d(TAG, "Medicine added: " + medicine);
                Toast.makeText(MedicineList.this, "Medicine Added", Toast.LENGTH_SHORT).show();
            } else {
                Log.w(TAG, "Attempted to add empty medicine name");
                Toast.makeText(MedicineList.this, "Medicine name cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void showDeleteConfirmation(final int position) {
        Log.d(TAG, "Showing delete confirmation for position: " + position);
        if (position >= 0 && position < medicines.size()) {
            new AlertDialog.Builder(this)
                    .setTitle("Delete Medicine")
                    .setMessage("Are you sure you want to delete this medicine?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        if (position >= 0 && position < medicines.size()) {
                            String medicine = medicines.get(position);
                            medicines.remove(position);
                            adapter.notifyDataSetChanged();
                            Log.d(TAG, "Medicine deleted: " + medicine);
                            Toast.makeText(MedicineList.this, "Medicine Deleted", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "Invalid position for deletion: " + position);
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        } else {
            Log.e(TAG, "Invalid position for deletion: " + position);
        }
    }
}
