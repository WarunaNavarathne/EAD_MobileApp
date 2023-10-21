package com.example.ticketingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyDatabase";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "NicTable";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NIC = "nic";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NIC + " TEXT" +
                ");";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades, if needed
    }

    // Method to insert a "nic" value into the table
    public long insertNic(String nic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NIC, nic);
        return db.insert(TABLE_NAME, null, values);
    }

    // Method to retrieve the most recent "nic" value
    public String getLatestNic() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_NIC}, null, null, null, null, COLUMN_ID + " DESC", "1");
        if (cursor != null && cursor.moveToFirst()) {
            String nic = cursor.getString(cursor.getColumnIndex(COLUMN_NIC));
            cursor.close();
            return nic;
        }
        return null;
    }
}

