package com.example.alexey.schedule;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BottomLevelThree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_level_three);
    }

    public void onAddConcreteCategoryClick(View view){
        EditText writeCategoryName = (EditText) findViewById(R.id.writeCategoryName);
        String categoryName = writeCategoryName.getText().toString();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", categoryName);
        SQLiteOpenHelper affairsDataBaseHelper = new AffairsDataBaseHelper(this);
        SQLiteDatabase db = affairsDataBaseHelper.getWritableDatabase();
        db.insert("CATEGORY", null, contentValues);
    }
}
