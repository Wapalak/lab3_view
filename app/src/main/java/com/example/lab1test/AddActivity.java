package com.example.lab1test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText name_input, surname_input, phone_input, uin_input, address_input;
    Button add_button, see_students_button, manage_panel_button;  // Добавлено новое поле

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
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
        // Добавьте свою логику аутентификации здесь
        return false;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        surname_input = findViewById(R.id.surname_input);
        phone_input = findViewById(R.id.phone_input);
        uin_input = findViewById(R.id.uin_input);  // Добавлено новое поле
        address_input = findViewById(R.id.address_input);  // Добавлено новое поле
        add_button = findViewById(R.id.add_button);
        see_students_button = findViewById(R.id.see_students_button);
        manage_panel_button = findViewById(R.id.manage_panel_button2);

        manage_panel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Переход на активити для управления панелью (LoginActivity)
                Intent intent = new Intent(AddActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Прямой вызов метода добавления пользователя
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addStudent(
                        name_input.getText().toString().trim(),
                        surname_input.getText().toString().trim(),
                        phone_input.getText().toString().trim(),
                        uin_input.getText().toString().trim(),
                        address_input.getText().toString().trim()
                );

                // Очистка полей ввода
                name_input.setText("");
                surname_input.setText("");
                phone_input.setText("");
                uin_input.setText("");
                address_input.setText("");

                Toast.makeText(AddActivity.this, "Information added successfully!", Toast.LENGTH_SHORT).show();
            }
        });



        see_students_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Переход на активити для просмотра студентов
                Intent intent = new Intent(AddActivity.this, MainActivityNew.class);
                startActivity(intent);

            }
        });
    }

}
