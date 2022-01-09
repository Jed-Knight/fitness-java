package com.example.fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InfoActivity extends AppCompatActivity {

    private EditText height, weight, age;
    private TextView intake;
    private RadioButton no, little, mid, more, most;
    private Button calc, reset, set, home;
    private double woman, none;
    private RadioGroup radioGroup;
    private String userHeight, userWeight, userAge;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        variables();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(InfoActivity.this, homePage.class));
            }
        });
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InfoActivity.this, "Function not available yet", Toast.LENGTH_SHORT).show();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height.setText("");
                weight.setText("");
                age.setText("");
                intake.setText("");
                radioGroup.clearCheck();

            }
        });
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                womanBMR();
            }

        });


    }

    public void variables(){
        height=findViewById(R.id.etBheight);
        weight=findViewById(R.id.etBweight);
        age=findViewById(R.id.etBage);
        no=findViewById(R.id.rdNo);
        little=findViewById(R.id.rdLittle);
        mid=findViewById(R.id.rdFive);
        more=findViewById(R.id.rdSeven);
        most=findViewById(R.id.rdVery);
        calc=findViewById(R.id.btnCalculate2);
        reset=findViewById(R.id.btnReset2);
        set=findViewById(R.id.btnSetTarget2);
        intake=findViewById(R.id.txtBMR2);
        home=findViewById(R.id.button);
        radioGroup=findViewById(R.id.radioGroup3);

    }

    public void womanBMR() {
        woman = 655.1 + (9.563 * Double.parseDouble(weight.getText().toString())) + (1.85 * ((Double.parseDouble(height.getText().toString())) * 100)) -
                (4.676 * Double.parseDouble(age.getText().toString()));

        if (no.isChecked()) {
            none=woman * 1.2;
            intake.setText("BMR is: " + woman + " Kcal\nSuggested daily calorie intake: " + (none) + " Kcal");
        }
        if (little.isChecked()) {
            none=woman * 1.375;
            intake.setText("BMR is: " + woman + " Kcal\nSuggested daily calorie intake: " + (none) + " Kcal");
        }
        if (mid.isChecked()) {
            none=woman * 1.55;
            intake.setText("BMR is: " + woman + " Kcal\nSuggested daily calorie intake: " + (none) + " Kcal");
        }
        if (more.isChecked()) {
            none=woman * 1.725;
            intake.setText("BMR is: " + woman + " Kcal\nSuggested daily calorie intake: " + (none) + " Kcal");
        }
        if (most.isChecked()) {
            none=woman * 1.9;
            intake.setText("BMR is: " + woman + " Kcal\nSuggested daily calorie intake: " + (none) + " Kcal");

        } else {
            Toast.makeText(this, "Please select activity level", Toast.LENGTH_SHORT).show();
        }
    }

        private Boolean validate(){
            Boolean result= false;
            userHeight=height.getText().toString();
            userWeight=weight.getText().toString();
            userAge=age.getText().toString();

            if(userHeight.isEmpty()|| userWeight.isEmpty()||userAge.isEmpty()||radioGroup.isClickable()){
                Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();
            }else{
                result=true;
            }
            return result;
        }
        public void userData(){

            firebaseDatabase= FirebaseDatabase.getInstance();
            DatabaseReference myRef= firebaseDatabase.getReference(firebaseAuth.getUid());
            myRef.child("BMR").setValue(woman);
            myRef.child("Suggested Intake").setValue(none);
        }

}