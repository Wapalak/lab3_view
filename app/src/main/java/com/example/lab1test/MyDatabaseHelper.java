package com.example.lab1test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Group.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_SURNAME = "surname";
    private static final String NEW_COLUMN_PHONE = "phone";
    private static final String NEW_COLUMN_BLOOD_GROUP = "blood";
    private static final String NEW_COLUMN_BIRTH_DATE = "bDate";

    MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_SURNAME + " TEXT, " +
                NEW_COLUMN_PHONE + " TEXT, " +
                NEW_COLUMN_BLOOD_GROUP + " TEXT, " +
                NEW_COLUMN_BIRTH_DATE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addStudent(String name, String surname, String phone, String bloodGroup, String birthDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_SURNAME, surname);
        cv.put(NEW_COLUMN_PHONE, phone);
        cv.put(NEW_COLUMN_BLOOD_GROUP, bloodGroup);
        cv.put(NEW_COLUMN_BIRTH_DATE, birthDate);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(query, null);
    }

    void updateData(String row_id, String name, String surname, String phone, String bloodGroup, String birthDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_SURNAME, surname);
        cv.put(NEW_COLUMN_PHONE, phone);
        cv.put(NEW_COLUMN_BLOOD_GROUP, bloodGroup);
        cv.put(NEW_COLUMN_BIRTH_DATE, birthDate);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Log.e("MyDatabaseHelper", "Failed to update row with id: " + row_id);
            Toast.makeText(context, "Failed to update!", Toast.LENGTH_SHORT).show();
        } else {
            Log.d("MyDatabaseHelper", "Successfully updated row with id: " + row_id);
            Toast.makeText(context, "Successfully updated!", Toast.LENGTH_SHORT).show();
        }
    }


    void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to delete!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully deleted!", Toast.LENGTH_SHORT).show();
        }
    }
}
