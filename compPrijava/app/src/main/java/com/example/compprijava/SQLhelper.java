package com.example.compprijava;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CompPrijava";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_KORISNICI = "korisnici";
    public static final String TABLE_PRIJAVA = "prijava";
    public static final String TABLE_ODJAVA = "odjava";

    public static final String C_ID = "id";
    public static final String C_NAME = "ime";
    public static final String C_PREZIME = "prezime";
    public static final String C_TOKEN = "token";
    public static final String C_DATUM = "datum";
    public static final String C_VRIJEMEDOL = "vrijeme_dolaska";
    public static final String C_VRIJEMEODL = "vrijeme_odlaska";
    public static final String C_RAZLOG = "razlog";

    private static final String CREATE_TABLE_KORISNICI = "CREATE TABLE "
            + TABLE_KORISNICI + "("
            + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + C_NAME + " TEXT, "
            + C_PREZIME + " TEXT, "
            + C_TOKEN + " TEXT ); ";


    private static final String CREATE_TABLE_PRIJAVA = "CREATE TABLE "
            + TABLE_PRIJAVA + "("
            + C_ID + " INTEGER,"
            + C_NAME + " TEXT,"
            + C_PREZIME + " TEXT,"
            + C_DATUM + " TEXT,"
            + C_VRIJEMEDOL + " TEXT );";

    private static final String CREATE_TABLE_ODJAVA = "CREATE TABLE "
            + TABLE_ODJAVA + "("
            + C_ID + " INTEGER,"
            + C_NAME + " TEXT,"
            + C_PREZIME + " TEXT,"
            + C_DATUM + " TEXT,"
            + C_VRIJEMEODL + " TEXT,"
            + C_RAZLOG + " TEXT );";


    public SQLhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_KORISNICI);
        db.execSQL(CREATE_TABLE_PRIJAVA);
        db.execSQL(CREATE_TABLE_ODJAVA);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KORISNICI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRIJAVA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ODJAVA);

        onCreate(db);
    }


    public Cursor showData() {

        SQLiteDatabase sd = this.getWritableDatabase();
        Cursor res = sd.rawQuery(" select * from " + TABLE_ODJAVA, null);
        return res;
    }

    // uzimanje tokena iz tabele "korisnici"
    public Cursor takeDataToken(String broj) {

        SQLiteDatabase sd = this.getWritableDatabase();
        Cursor res = sd.rawQuery(" select * from " + TABLE_KORISNICI + " WHERE " + SQLhelper.C_TOKEN + " == " + broj, null);
        return res;
    }

    public Cursor compareToken() {

        SQLiteDatabase sd = this.getWritableDatabase();
        Cursor res = sd.rawQuery(" select " + C_TOKEN + " from " + TABLE_KORISNICI, null);
        return res;
    }

    //insterovanje podataka u tabelu "prijava"

    public void insertDataPrijava(String id, String ime, String prezime, String datum, String time) {

        SQLiteDatabase sd = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(C_ID, id);
        cv.put(C_NAME, ime);
        cv.put(C_PREZIME, prezime);
        cv.put(C_DATUM, datum);
        cv.put(C_VRIJEMEDOL, time);
        sd.insert(TABLE_PRIJAVA, null, cv);
    }

    //insterovanje podataka u tabelu "odjava"

    public void insertDataOdjava(String id, String ime, String prezime, String datum, String time, String razlog) {

        SQLiteDatabase sd = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(C_ID, id);
        cv.put(C_NAME, ime);
        cv.put(C_PREZIME, prezime);
        cv.put(C_DATUM, datum);
        cv.put(C_VRIJEMEODL, time);
        cv.put(C_RAZLOG, razlog);
        sd.insert(TABLE_ODJAVA, null, cv);

    }

    public void insertDataKorisnici(String ime, String prezime, String token) {

        SQLiteDatabase sd = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(C_NAME, ime);
        cv.put(C_PREZIME, prezime);
        cv.put(C_TOKEN, token);
        sd.insert(TABLE_KORISNICI, null, cv);
    }
}
