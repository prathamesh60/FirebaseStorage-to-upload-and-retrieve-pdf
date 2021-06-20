package com.example.uploadx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {
    public EditText emailId,password;
    public Button bt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        emailId=findViewById(R.id.email);
        password=findViewById(R.id.password);
        bt1=findViewById(R.id.login);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=emailId.getText().toString();
                String pwd= password.getText().toString() ;

                if(email.equals("kanhaiya.singh1974@gmail.com") && pwd.equals("12345678"))
                {
                    startActivity(new Intent(AdminLogin.this,MainAdminPage.class));
                }
                else
                {
                    Toast.makeText(AdminLogin.this,"Invalid credential or network problem",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}