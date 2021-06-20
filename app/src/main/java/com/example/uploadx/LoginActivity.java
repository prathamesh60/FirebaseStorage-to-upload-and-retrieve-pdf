package com.example.uploadx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText emailId,password;
    Button bt1,bt2;
    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailId=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        mFirebaseAuth=FirebaseAuth.getInstance();
        bt1=(Button) findViewById(R.id.login);
        bt2=(Button) findViewById(R.id.register);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailId.getText().toString();
                String pwd=password.getText().toString();
                if(email.isEmpty()){
                    emailId.setError("Please enter email");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty()){
                    password.setError("Enter Password");
                    emailId.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this,"Fields are Empty",Toast.LENGTH_SHORT).show();
                }
                else{
                    mFirebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if(!task.isSuccessful()){
                               Toast.makeText(LoginActivity.this,"Error pls try again",Toast.LENGTH_SHORT).show();
                           }
                           else{
                               startActivity(new Intent(LoginActivity.this,ViewUploadsActivity.class));
                           }
                        }
                    });
                }
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this, RegistrationPage.class));
            }
        });
    }
}