package com.example.lab1test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivityNew extends AppCompatActivity {

    RecyclerView recyclerViewNew;
    MyDatabaseHelper myDB;
    ArrayList<String> student_id, student_name, student_surname;
    CustomAdapterNew customAdapterNew;
    FloatingActionButton add_button;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityNew.this, AddActivity.class);
                startActivity(intent);
            }
        });

        recyclerViewNew = findViewById(R.id.recyclerViewNew);
        myDB = new MyDatabaseHelper(MainActivityNew.this);

        student_id = new ArrayList<>();
        student_name = new ArrayList<>();
        student_surname = new ArrayList<>();

        storeDataInArrays();
        customAdapterNew = new CustomAdapterNew(MainActivityNew.this, this, student_id,
                student_name, student_surname);
        recyclerViewNew.setAdapter(customAdapterNew);
        recyclerViewNew.setLayoutManager(new LinearLayoutManager(MainActivityNew.this));
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
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Возвращение на предыдущую активити (AddActivity)
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}