package com.si61.sqlitemahasiswa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context ctx;
    private static final String DATABASE_NAME = "db_mahasiswa";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tbl_mahasiswa";
    private static final String FIELD_Id = "id";
    private static final String FIELD_Npm = "npm";
    private static final String FIELD_Nama = "nama";
    private static final String FIELD_Prodi ="prodi";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*CREATE TABLE tbl_destinasi(
                id INTEGER PPRIMARY KEY AUTOINCREMENT,
                nama VARCHAR(50),
                alamat TEXT,
                jam VARCHAR(30)
        )*/
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                FIELD_Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIELD_Npm + " CHAR(10), " +
                FIELD_Nama + " VARCHAR(50), " +
                FIELD_Prodi + " VARCHAR(75) " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public long tambahData(String npm, String nama, String prodi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIELD_Npm, npm);
        cv.put(FIELD_Nama, nama);
        cv.put(FIELD_Prodi, prodi);

        long eksekusi = db.insert(TABLE_NAME, null, cv);
        return eksekusi;
    }

    public Cursor bacaDataMahasiswa(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor varcursor = null;
        if(db != null) {
            varcursor = db.rawQuery(query, null);
        }
        return varcursor;
    }

}