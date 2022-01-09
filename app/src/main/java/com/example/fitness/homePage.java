package com.example.fitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class homePage extends AppCompatActivity {

    private ImageView settings, profile, basal, body, gallery, progress;
    private Button logout;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        variables();
        firebaseAuth= FirebaseAuth.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
                startActivity(new Intent(homePage.this, MainActivity.class));
                Toast.makeText(homePage.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
            }
        });
        basal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homePage.this, Gender.class));
            }
        });
        body.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homePage.this, MainActivity2.class));
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homePage.this, com.example.fitness.profile.class));
            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homePage.this, Gallery.class));
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(homePage.this, "Settings", Toast.LENGTH_SHORT).show();
            }
        });
        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homePage.this, Progress.class));
            }
        });
    }

    public void variables(){

        settings=findViewById(R.id.imgSettings);
        profile=findViewById(R.id.imgProfile);
        progress=findViewById(R.id.imgProgress);
        basal=findViewById(R.id.imgBMR);
        body=findViewById(R.id.imgBMI);
        gallery=findViewById(R.id.imgGallery);
        logout=findViewById(R.id.btnLogout);
    }
}