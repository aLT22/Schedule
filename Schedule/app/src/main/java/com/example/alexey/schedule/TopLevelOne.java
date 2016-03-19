package com.example.alexey.schedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class TopLevelOne extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level_one);
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        };
        ListView todaysAffairs = (ListView) findViewById(R.id.affairListView);
        todaysAffairs.setOnItemClickListener(itemClickListener);
    }

    //Вызов активити со списком категорий
    public void onCategoriesClick(View view){
        Intent intent = new Intent(this, MiddleLevelOne.class);
        startActivity(intent);
    }

    //Вызов активити со списком дел
    public void onAffairsClick(View view){
        Intent intentTwo = new Intent(this, MiddleLevelThree.class);
        startActivity(intentTwo);
    }

    //Вызов активити со справочным материалом
    public void onRefClick(View view){
        Intent intent = new Intent(this, References.class);
        startActivity(intent);
    }
}
