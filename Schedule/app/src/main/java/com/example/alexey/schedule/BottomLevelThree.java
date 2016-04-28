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
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class BottomLevelThree extends AppCompatActivity {
    EditText writeCategoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_level_three);
        writeCategoryName = (EditText) findViewById(R.id.writeCategoryName);
    }

    public void onAddConcreteCategoryClick(View view){
        writeCategoryName = (EditText) findViewById(R.id.writeCategoryName);
        String categoryName = writeCategoryName.getText().toString();
        if (categoryName.equals("")){
            Toast.makeText(this, "Пустое название категории не допускается", Toast.LENGTH_LONG).show();
        } else {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("NAME", categoryName);
                SQLiteOpenHelper affairsDataBaseHelper = new AffairsDataBaseHelper(this);
                SQLiteDatabase db = affairsDataBaseHelper.getWritableDatabase();
                Cursor cursor = db.query("CATEGORY",
                        new String[]{"_id", "NAME"}, null, null, null, null, null);
                boolean cheq = false;
                for (int i = 0; i < cursor.getColumnCount(); i++){
                    if (cursor.moveToNext()){
                        if (categoryName.equals(cursor.getString(i))){
                            cheq = true;
                            break;
                        }
                    } else
                        break;
                }
                if (cheq){
                    Toast.makeText(this, "Такая категория уже существует", Toast.LENGTH_LONG).show();
                } else {
                    db.insert("CATEGORY", null, contentValues);
                    Toast.makeText(this, "Добавлена новая категория " + categoryName, Toast.LENGTH_LONG).show();
                }
            }
            catch (SQLiteException e){
                Toast.makeText(this, "Ошибка записи в базу данных", Toast.LENGTH_LONG).show();
            }
        }
    }
}