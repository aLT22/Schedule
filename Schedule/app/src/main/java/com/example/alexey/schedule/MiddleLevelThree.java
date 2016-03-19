package com.example.alexey.schedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MiddleLevelThree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle_level_three);
    }

    public void onAddAffairClick(View view){
        Intent intent = new Intent(this, BottomLevelTwo.class);
        startActivity(intent);
    }
}
