package com.example.alexey.schedule;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class BottomLevelTwo extends AppCompatActivity {
    private Spinner categorySelector;
    private EditText writeAffairName, writeAffairDesc, writeAffairDate;
    private String affairName, affairDesc, affairDate, affairCategory;
    private Cursor cursor;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_level_two);
        try{
            SQLiteOpenHelper affairsDataBaseHelper = new AffairsDataBaseHelper(this);
            db = affairsDataBaseHelper.getReadableDatabase();
            cursor = db.query("CATEGORY",
                    new String[]{"_id", "NAME"},
                    null, null, null, null, null);
            CursorAdapter spinnerAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_spinner_item, cursor,
                    new String[]{"NAME"}, new int[]{android.R.id.text1}, 0);
            categorySelector = (Spinner) findViewById(R.id.blCategorySelector);
            writeAffairName = (EditText) findViewById(R.id.blWriteAffairName);
            writeAffairDesc = (EditText) findViewById(R.id.blWriteAffairDesc);
            writeAffairDate = (EditText) findViewById(R.id.blWriteAffairDate);
            categorySelector.setAdapter(spinnerAdapter);
        } catch (SQLiteException e){
            System.out.print(e.getMessage());
        }
    }

    public void onAddConcreteAffair(View view){
        affairName = writeAffairName.getText().toString();
        affairDesc = writeAffairDesc.getText().toString();
        affairDate = writeAffairDate.getText().toString();
        affairCategory = categorySelector.getSelectedItem().toString();
        if (affairName.equals("")) {
            Toast.makeText(this, "Введите непустое название", Toast.LENGTH_LONG).show();
        } else {
            try {
                SQLiteOpenHelper affairsDataBaseOpenHelper = new AffairsDataBaseHelper(this);
                db = affairsDataBaseOpenHelper.getWritableDatabase();
                cursor = db.query("CATEGORY",
                        new String[]{"_id"},
                        "NAME = ?", new String[]{affairCategory}, null, null, null, null);
                long id = cursor.getLong(0);
                ContentValues contentValues = new ContentValues();
                contentValues.put("NAME", affairName);
                contentValues.put("DESCRIPTION", affairDesc);
                contentValues.put("DATE", affairDate);
                contentValues.put("CATGRY", id);
                db.insert("AFFAIRS", null, contentValues);
            } catch (SQLiteException e){
                Toast.makeText(this, "Ошибка при работе с базой данных", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
