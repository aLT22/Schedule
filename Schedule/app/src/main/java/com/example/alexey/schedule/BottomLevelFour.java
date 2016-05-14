package com.example.alexey.schedule;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BottomLevelFour extends AppCompatActivity {
    private EditText rewriteCategoryName;
    private TextView changeName;
    private SQLiteOpenHelper affairsDataBaseHelper;
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_level_four);
        rewriteCategoryName = (EditText) findViewById(R.id.blEditNameCurCat);
        changeName = (TextView) findViewById(R.id.changeName);
        try{
            affairsDataBaseHelper = new AffairsDataBaseHelper(this);
            db = affairsDataBaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("NAME", rewriteCategoryName.getText().toString());
            db.update("CATEGORY",
                    contentValues,
                    "NAME = ?",
                    new String[]{""});
        } catch (SQLiteException ex){
            Toast.makeText(this, "SQLite Exception", Toast.LENGTH_SHORT).show();
        }

    }
}
