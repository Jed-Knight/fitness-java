package com.example.fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    private TextView Height, Weight, Result;
    private Button bmi, BTN, reset, home;
    private double b;
    private String userHeight, userWeight, A;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        firebaseAuth=FirebaseAuth.getInstance();

        variables();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Height.setText("");
                Weight.setText("");
                Result.setText("");
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity2.this, homePage.class));
            }
        });

        BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    sendUser();
                    Toast.makeText(MainActivity2.this, "Save Successful",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity2.this, "Save unsuccessful",Toast.LENGTH_SHORT).show();
                }
            }
        });

        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }

    public void calculate(){
        b =(Double.parseDouble(Weight.getText().toString()))/((Double.parseDouble(Height.getText().toString()))*(Double.parseDouble(Height.getText().toString())));
        if(b<=18.5){
            Result.setText("Body Mass Index is: "+b+"\n (Underweight)");
        }
        if((b>18.5) && (b<=24.9))
            Result.setText("Body Mass Index is: "+b+"\n (Normal weight)");
        if((b>=25) &&(b<=29.9)){
            Result.setText("Body Mass Index is: "+b+"\n (Overweight)");
        }
        if(b>=30){
            Result.setText("Body Mass Index is: "+b+"\n (Obese)");
        }
    }
    public void variables(){
        Height= (TextView)findViewById(R.id.etHeight);
        Weight=(TextView)findViewById(R.id.etWeight);
        bmi=(Button)findViewById(R.id.btnBMI);
        Result=(TextView)findViewById(R.id.txtResult);
        BTN=findViewById(R.id.btnSave);
        home=findViewById(R.id.btnHome);
        reset=findViewById(R.id.btnReset);

    }
    public void sendUser(){


        firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference myRef= firebaseDatabase.getReference(firebaseAuth.getUid());
        myRef.child("Body Mass Index").setValue(b);
    }
    private Boolean validate(){
        Boolean result=false;
        userHeight=Height.getText().toString();
        userWeight= Weight.getText().toString();
        if(userHeight.isEmpty()|| userWeight.isEmpty()){
            Toast.makeText(this, "Please enter all details",Toast.LENGTH_SHORT).show();
        }else{
            result=true;
        }
        return result;
    }
}