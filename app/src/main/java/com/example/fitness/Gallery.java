package com.example.fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Gallery extends AppCompatActivity {
    private Button breakfast, lunch, supper, home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        variables();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Gallery.this, homePage.class));
            }
        });
        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Gallery.this, "Breakfast gallery", Toast.LENGTH_SHORT).show();
            }
        });
        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Gallery.this, "Lunch gallery", Toast.LENGTH_SHORT).show();
            }
        });
        supper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Gallery.this, "Supper gallery", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void variables(){

        breakfast=findViewById(R.id.btnBreak);
        lunch=findViewById(R.id.btnLunch);
        supper=findViewById(R.id.btnSupper);
        home=findViewById(R.id.btnHome);
    }
}