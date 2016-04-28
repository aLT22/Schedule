package com.example.alexey.schedule;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.Settings.Global.getString;

/**
 * Created by Alexey on 27.03.2016.
 */
public class AffairsDataBaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "affairs";
    public static final int DB_VERSION = 1;

    public AffairsDataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE CATEGORY ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT NOT NULL UNIQUE);");
        fillingDB("CATEGORY", "NAME", "Бизнес", db);
        fillingDB("CATEGORY", "NAME", "Спорт", db);
        fillingDB("CATEGORY", "NAME", "Встреча", db);
        fillingDB("CATEGORY", "NAME", "Учеба", db);
        fillingDB("CATEGORY", "NAME", "Работа", db);
        fillingDB("CATEGORY", "NAME", "Свободное время", db);
        db.execSQL("CREATE TABLE AFFAIR ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT NOT NULL, "
                + "DESCRIPTION TEXT, "
                + "DAY INTEGER, "
                + "MONTH INTEGER, "
                + "YEAR INTEGER, "
                + "CATGRY INTEGER NOT NULL, "
                + "FOREIGN KEY (CATGRY) REFERENCES CATEGORY (_id));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    private void fillingDB(String dbName, String coloumnName, String sValue, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(coloumnName, sValue);
        db.insert(dbName, null, contentValues);
    }
}
