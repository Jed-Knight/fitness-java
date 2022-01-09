package com.example.fitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {

    private EditText userName,email, pass1;
    private Button reg;
    private TextView log;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        variables();
        firebaseAuth= FirebaseAuth.getInstance();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()){
                    String user_email= email.getText().toString().trim();
                    String user_password= pass1.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SecondActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(SecondActivity.this, MainActivity.class));
                            }else{
                                Toast.makeText(SecondActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });


        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent= new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    public void variables(){
        userName=(EditText)findViewById(R.id.etUsername);
        email=(EditText)findViewById(R.id.etEmail);
        pass1=(EditText)findViewById(R.id.etPass1);
        reg=(Button)findViewById(R.id.btnReg);
        log=(TextView)findViewById(R.id.txtLogin);

    }
    private boolean validate(){
        Boolean result=false;

        String name= userName.getText().toString();
        String password= pass1.getText().toString();
        String mail= email.getText().toString();

        if(name.isEmpty()|| password.isEmpty()||mail.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result=true;
        }
        return result;
    }

}