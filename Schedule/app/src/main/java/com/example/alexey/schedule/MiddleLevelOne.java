package com.example.alexey.schedule;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class MiddleLevelOne extends AppCompatActivity {
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle_level_one);
        ListView listView = (ListView) findViewById(R.id.slCategoriesListView);
        try{
            SQLiteOpenHelper affairsDataBaseHelper = new AffairsDataBaseHelper(this);
            db = affairsDataBaseHelper.getReadableDatabase();
            cursor = db.query("CATEGORY",
                    new String[]{"_id", "NAME"},
                    null, null, null, null, null);
            CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1, cursor,
                    new String[]{"NAME"}, new int[]{android.R.id.text1}, 0);
            listView.setAdapter(listAdapter);
        } catch (SQLiteException ex){
            Toast toast = Toast.makeText(this, "SQLITE EXCEPTION", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void onAddCategoryClick(View view){
        Intent intent = new Intent(this, BottomLevelThree.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cursor != null){
            cursor.close();
        }
        if (db != null){
            db.close();
        }
    }
}
