package com.example.lab1test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> student_id, student_name, student_surname, student_phone, student_uin, student_address;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        student_id = new ArrayList<>();
        student_name = new ArrayList<>();
        student_surname = new ArrayList<>();
        student_phone = new ArrayList<>();
        student_uin = new ArrayList<>(); // Измененное поле
        student_address = new ArrayList<>(); // Измененное поле

        storeDataInArrays();
        customAdapter = new CustomAdapter(MainActivity.this, this, student_id,
                student_name, student_surname, student_phone, student_uin, student_address);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                student_id.add(cursor.getString(0));
                student_name.add(cursor.getString(1));
                student_surname.add(cursor.getString(2));
                // Использованы новые поля базы данных
                student_phone.add(cursor.getString(3));
                student_uin.add(cursor.getString(4));
                student_address.add(cursor.getString(5));
            }
        }
    }
}