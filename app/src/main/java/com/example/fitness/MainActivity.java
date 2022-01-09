package com.example.fitness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText Name, Password;
    private Button Login;
    private TextView Register;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        variables();

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });


            Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(validated()) {
                        validate(Name.getText().toString(), Password.getText().toString());
                    }
                }
            });
        }


    private void validate(String userName, String userPassword) {
        progressDialog.setMessage("Validating user credentials");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(MainActivity.this, homePage.class));
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Username/password incorrect", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
    public void variables(){
        Name=findViewById(R.id.etFullName);
        Password=findViewById((R.id.etPass));
        Login=findViewById(R.id.btnLogin);
        Register=findViewById(R.id.txtRegister);
    }
    private boolean validated(){
        Boolean result=false;

        String name= Name.getText().toString();
        String password= Password.getText().toString();
        if(name.isEmpty()|| password.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result=true;
        }
        return result;
    }
}


