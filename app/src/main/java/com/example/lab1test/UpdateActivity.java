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

    EditText name_input, surname_input, phone_input, uin_input, address_input;
    Button update_button, delete_button;

    String id, name, surname, phone, uin, address;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.name_input2);
        surname_input = findViewById(R.id.surname_input2);
        phone_input = findViewById(R.id.phone_input2);
        uin_input = findViewById(R.id.uin_input2);
        address_input = findViewById(R.id.address_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                name = name_input.getText().toString().trim();
                surname = surname_input.getText().toString().trim();
                phone = phone_input.getText().toString().trim();
                uin = uin_input.getText().toString().trim(); // Измененное поле
                address = address_input.getText().toString().trim(); // Измененное поле
                myDB.updateData(id, name, surname, phone, uin, address);
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
                getIntent().hasExtra("uin") && getIntent().hasExtra("address")) {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            surname = getIntent().getStringExtra("surname");
            phone = getIntent().getStringExtra("phone");
            uin = getIntent().getStringExtra("uin");
            address = getIntent().getStringExtra("address");

            Log.d("UpdateActivity", "id: " + id);
            Log.d("UpdateActivity", "name: " + name);
            Log.d("UpdateActivity", "surname: " + surname);
            Log.d("UpdateActivity", "phone: " + phone);
            Log.d("UpdateActivity", "uin: " + uin);
            Log.d("UpdateActivity", "address: " + address);

            name_input.setText(name);
            surname_input.setText(surname);
            phone_input.setText(phone);
            uin_input.setText(uin);
            address_input.setText(address);
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
