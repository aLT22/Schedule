package com.example.alexey.schedule;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class BottomLevelTwo extends AppCompatActivity {
    private Spinner categorySelector;
    private EditText writeAffairName, writeAffairDesc, writeAffairDate;
    private String affairName, affairDesc, affairDate, affairCategory;
    private Cursor cursor;
    private SQLiteDatabase db;
    /*private TextView addDate;
    private Calendar calendar = Calendar.getInstance();
    private final int DIALOG_DATE = 1;
    private int myYear = calendar.get(calendar.YEAR);
    private int myMonth = calendar.get(calendar.MONTH) + 1;
    private int myDay = calendar.get(calendar.DAY_OF_MONTH);
    private DatePicker datePicker;*/
    private DatePickerFragment datePickerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_level_two);
        categorySelector = (Spinner) findViewById(R.id.blCategorySelector);
        writeAffairName = (EditText) findViewById(R.id.blWriteAffairName);
        writeAffairDesc = (EditText) findViewById(R.id.blWriteAffairDesc);
        datePickerFragment = new DatePickerFragment();
        try{
            SQLiteOpenHelper affairsDataBaseHelper = new AffairsDataBaseHelper(this);
            db = affairsDataBaseHelper.getWritableDatabase();
            cursor = db.query("CATEGORY",
                    new String[]{"_id", "NAME"},
                    null, null, null, null, null);
            CursorAdapter spinnerAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_spinner_item, cursor,
                    new String[]{"NAME"}, new int[]{android.R.id.text1}, 0);
            categorySelector.setAdapter(spinnerAdapter);
        } catch (SQLiteException e){
            System.out.print(e.getMessage());
        }
    }

    public void testClick(View view){
        affairCategory = categorySelector.getSelectedItem().toString();
        Toast.makeText(this, affairCategory, Toast.LENGTH_LONG).show();
    }

    public void onAddConcreteAffair(View view) throws IllegalStateException{
        affairName = writeAffairName.getText().toString();
        affairDesc = writeAffairDesc.getText().toString();
        affairDate = writeAffairDate.getText().toString();
        affairCategory = categorySelector.getSelectedItem().toString();
        if (affairName.equals("")) {
            Toast.makeText(this, "Введите непустое название", Toast.LENGTH_LONG).show();
        } else {
            try {
                cursor = db.query("CATEGORY",
                        new String[]{"_id"},
                        "NAME = ?", new String[]{affairCategory}, null, null, null, null);
                long id = cursor.getLong(0);
                ContentValues contentValues = new ContentValues();
                contentValues.put("NAME", affairName);
                contentValues.put("DESCRIPTION", affairDesc);
                contentValues.put("YEAR", datePickerFragment.getYear());
                contentValues.put("MONTH", datePickerFragment.getMonth());
                contentValues.put("DAY", datePickerFragment.getDay());
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

    public void onCreateDatePicker(View view){
        Intent intent = new Intent(this, BottomLevelSetAffairDate.class);
        startActivity(intent);
    }
}
