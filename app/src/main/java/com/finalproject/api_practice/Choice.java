package com.finalproject.api_practice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Choice extends AppCompatActivity {
    Button btnLoadAll,btnLoadOne,btnAddNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        btnLoadAll = findViewById(R.id.btnLoadAll);
        btnLoadOne = findViewById(R.id.btnLoadOne);
        btnAddNew = findViewById(R.id.btnAddNew);

        btnLoadAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Choice.this,MainActivity.class));
            }
        });

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Choice.this,AddNewData.class));
            }
        });

        btnLoadOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Choice.this,SearchById.class));
            }
        });
    }
}
