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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationPage extends AppCompatActivity {
    public EditText emailId,password;
    Button signUp,login;
    FirebaseAuth mFirebaseAuth;
    DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        mFirebaseAuth=FirebaseAuth.getInstance();
        emailId=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        signUp=(Button)findViewById(R.id.register);
        login=(Button)findViewById(R.id.login);
        signUp.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(RegistrationPage.this,"Fields are Empty",Toast.LENGTH_SHORT).show();
                }
                else {
                           mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(RegistrationPage.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if(!task.isSuccessful()){
                               Toast.makeText(RegistrationPage.this,"Register error",Toast.LENGTH_SHORT).show();
                           }
                           else{
                               FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                               startActivity(new Intent(RegistrationPage.this,ViewUploadsActivity.class));

                           }
                        }
                    });
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationPage.this, LoginActivity.class));
            }
        });

    }

}