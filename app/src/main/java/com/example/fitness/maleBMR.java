package com.example.fitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class maleBMR extends AppCompatActivity {
    private EditText height, weight, age;
    private TextView BMR;
    private RadioButton no, little, mid, more, most;
    private Button calc, reset, setTarget, home;
    private double man, none;
    private RadioGroup radioGroup;
    private String userHeight, userWeight, userAge;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_b_m_r);
        variables();
        FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();

        setTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    userData();
                    Toast.makeText(maleBMR.this, "Target Set Successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(maleBMR.this, "Target Setting Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(maleBMR.this, homePage.class));
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                height.setText("");
                weight.setText("");
                age.setText("");
                BMR.setText("");
                radioGroup.clearCheck();
            }
        });

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manBMR();
            }
        });

    }

    public void variables() {
        height = findViewById(R.id.etBheight2);
        weight = findViewById(R.id.etBweight2);
        age = findViewById(R.id.etBage2);
        no = findViewById(R.id.rdNo);
        little = findViewById(R.id.rdLittle);
        mid = findViewById(R.id.rdThree);
        more = findViewById(R.id.rdSix);
        most = findViewById(R.id.rdActive);
        calc = findViewById(R.id.btnCalculate);
        reset = findViewById(R.id.btnReset);
        setTarget = findViewById(R.id.btnSetTarget);
        BMR = findViewById(R.id.txtBMR);
        home=findViewById(R.id.button9);
        radioGroup=findViewById(R.id.radioGroup);
    }

    public void manBMR() {
        man = 66.47 + (13.75 * Double.parseDouble(weight.getText().toString())) + (5.003 * ((Double.parseDouble(height.getText().toString()) * 100)) -
                (6.755 * Double.parseDouble(age.getText().toString())));


        if(no.isChecked()){
            none=man*1.2;
            BMR.setText("BMR is: " + man + " Kcal\nSuggested daily calorie intake: "+(none)+" Kcal");
        }
        if (little.isChecked()) {
            none=man*1.375;
            BMR.setText("BMR is: " + man + " Kcal\nSuggested daily calorie intake: "+(none)+" Kcal");
        }
        if (mid.isChecked()) {
            none=man*1.55;
            BMR.setText("BMR is: " + man + " Kcal\nSuggested daily calorie intake: "+(none)+" Kcal");
        }
        if (more.isChecked()) {
            none=man*1.725;
            BMR.setText("BMR is: " + man + " Kcal\nSuggested daily calorie intake: "+(none)+" Kcal");
        }
        if (most.isChecked()) {
            none=man*1.9;
            BMR.setText("BMR is: " + man + " Kcal\nSuggested daily calorie intake: "+(none)+" Kcal");
        }
        else{
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

        firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference myRef= firebaseDatabase.getReference(firebaseAuth.getUid());
        myRef.child("BMR").setValue(man);
        myRef.child("Recommended Intake").setValue(none);
    }
}