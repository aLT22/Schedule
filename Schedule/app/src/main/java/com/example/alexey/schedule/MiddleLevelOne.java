package com.example.alexey.schedule;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MiddleLevelOne extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle_level_one);
    }

    public void onAddCategoryClick(View view){
        Intent intent = new Intent(this, MiddleLevelTwo.class);
        startActivity(intent);
    }
}
