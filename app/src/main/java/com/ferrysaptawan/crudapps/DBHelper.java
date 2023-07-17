package com.ferrysaptawan.crudapps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "project.db";
    private static final int DB_VERSION = 2;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE users (username TEXT PRIMARY KEY, password TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE data (kode TEXT PRIMARY KEY, nama TEXT, jumlah TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS data");
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("password", password);

        long result = db.insert("users", null, values);
        db.close();

        return result != -1;
    }

    public boolean insertBiodata(String kode, String nama, String jumlah) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("kode", kode);
        values.put("nama", nama);
        values.put("jumlah", jumlah);

        long result = db.insert("data", null, values);
        db.close();

        return result != -1;
    }


    public boolean checkUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                "users",
                null,
                "username = ?",
                new String[]{username},
                null,
                null,
                null
        );

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();

        return exists;
    }

    public boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("users", null, "username = ? AND password = ?",
                new String[]{username, password}, null, null, null);

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();

        return exists;
    }

    public boolean checkkode(String kode) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM data WHERE kode=?", new String[]{kode});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }


    public Cursor tampildata() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM data", null);
    }

    public boolean deleteData(String kode) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("data", "kode=?", new String[]{kode});
        db.close();
        return result > 0;
    }


    public boolean updateData(String kode, String nama, String jumlah) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nama", nama);
        values.put("jumlah", jumlah);


        int result = db.update("data", values, "kode=?", new String[]{kode});
        db.close();
        return result > 0;
    }


}
