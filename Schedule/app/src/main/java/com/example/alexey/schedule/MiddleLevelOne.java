package com.example.alexey.schedule;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import static android.widget.Toast.*;


public class MiddleLevelOne extends AppCompatActivity {
    private SQLiteOpenHelper affairsDataBaseHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ListView listView;

    @Override
    protected void onResume() {
        super.onResume();
        listView = (ListView) findViewById(R.id.slCategoriesListView);
        registerForContextMenu(listView);
        try{
            affairsDataBaseHelper = new AffairsDataBaseHelper(this);
            db = affairsDataBaseHelper.getReadableDatabase();
            cursor = db.query("CATEGORY",
                    new String[]{"_id", "NAME"},
                    null, null, null, null, null);
            CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1, cursor,
                    new String[]{"NAME"}, new int[]{android.R.id.text1}, 0);
            listAdapter.changeCursor(cursor);
            listView.setAdapter(listAdapter);
        } catch (SQLiteException ex){
            Toast toast = makeText(this, "SQLITE EXCEPTION", LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle_level_one);
        listView = (ListView) findViewById(R.id.slCategoriesListView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.middle_level_one_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        try {
            listView = (ListView) findViewById(R.id.slCategoriesListView);
            switch (item.getItemId()) {
                case R.id.deleteCategory:
                    AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    db.delete("CATEGORY", "_id = ?", new String[]{String.valueOf(adapterContextMenuInfo.id)});
                    cursor = db.query("CATEGORY",
                            new String[]{"_id", "NAME"},
                            null, null, null, null, null);
                    CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                            android.R.layout.simple_list_item_1, cursor,
                            new String[]{"NAME"}, new int[]{android.R.id.text1}, 0);
                    listView.setAdapter(listAdapter);
                    makeText(this, "Вы удалили категорию", LENGTH_SHORT).show();
                    break;
                case R.id.rewriteCategory:
                    Intent intent = new Intent(this, BottomLevelFour.class);
                    startActivity(intent);
                    break;
                default:
                    return super.onContextItemSelected(item);
            }
        }
        catch (SQLiteException e) {
            makeText(this, "Ошибка работы базы данных", LENGTH_LONG).show();
        }
        return true;
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
