package com.example.lab1test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText name_input, surname_input, phone_input, blood_group_input, birth_date_input;
    Button update_button, delete_button;

    String id, name, surname, phone, bloodGroup, birthDate;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.name_input2);
        surname_input = findViewById(R.id.surname_input2);
        phone_input = findViewById(R.id.phone_input2);
        blood_group_input = findViewById(R.id.blood_group_input2);
        birth_date_input = findViewById(R.id.birth_date_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                name = name_input.getText().toString().trim();
                surname = surname_input.getText().toString().trim();
                phone = phone_input.getText().toString().trim();
                bloodGroup = blood_group_input.getText().toString().trim();
                birthDate = birth_date_input.getText().toString().trim();
                myDB.updateData(id, name, surname, phone, bloodGroup, birthDate);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("surname") && getIntent().hasExtra("phone") &&
                getIntent().hasExtra("blood") && getIntent().hasExtra("bDate")) {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            surname = getIntent().getStringExtra("surname");
            phone = getIntent().getStringExtra("phone");
            bloodGroup = getIntent().getStringExtra("blood");
            birthDate = getIntent().getStringExtra("bDate");

            Log.d("UpdateActivity", "id: " + id);
            Log.d("UpdateActivity", "name: " + name);
            Log.d("UpdateActivity", "surname: " + surname);
            Log.d("UpdateActivity", "phone: " + phone);
            Log.d("UpdateActivity", "bloodGroup: " + bloodGroup);
            Log.d("UpdateActivity", "birthDate: " + birthDate);

            name_input.setText(name);
            surname_input.setText(surname);
            phone_input.setText(phone);
            blood_group_input.setText(bloodGroup);
            birth_date_input.setText(birthDate);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + "?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}
