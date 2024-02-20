package com.example.lab1test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddActivity extends AppCompatActivity {

    EditText name_input, surname_input, phone_input, blood_group_input, birth_date_input;
    Button add_button;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Проверка аутентификации пользователя
                if (isUserAuthenticated()) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Authentication failed. Please log in.", Toast.LENGTH_SHORT).show();

                    Intent loginIntent = new Intent(this, LoginActivity.class);
                    startActivity(loginIntent);
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean isUserAuthenticated() {

        return false;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        name_input = findViewById(R.id.name_input);
        surname_input = findViewById(R.id.surname_input);
        phone_input = findViewById(R.id.phone_input);
        blood_group_input = findViewById(R.id.blood_group_input);
        birth_date_input = findViewById(R.id.birth_date_input);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
                builder.setTitle("Confirmation");
                builder.setMessage("Are you sure you want to add this user data with the " +
                        "entered information?\n\n" +
                        "Name: " + name_input.getText().toString().trim() + "\n" +
                        "Surname: " + surname_input.getText().toString().trim() + "\n" +
                        "Phone: " + phone_input.getText().toString().trim() + "\n" +
                        "Blood Group: " + blood_group_input.getText().toString().trim() + "\n" +
                        "Birth Date: " + birth_date_input.getText().toString().trim());

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                        myDB.addStudent(
                                name_input.getText().toString().trim(),
                                surname_input.getText().toString().trim(),
                                phone_input.getText().toString().trim(),
                                blood_group_input.getText().toString().trim(),
                                birth_date_input.getText().toString().trim()
                        );

                        // Очистка полей ввода
                        name_input.setText("");
                        surname_input.setText("");
                        phone_input.setText("");
                        blood_group_input.setText("");
                        birth_date_input.setText("");


                        Toast.makeText(AddActivity.this, "Information added successfully!", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });
    }
}

