package com.example.elearningplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseLoginSignup extends AppCompatActivity {
    Button btnChooseLogin;
    Button btnChooseSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_login_signup);

        btnChooseLogin = findViewById(R.id.btnChooseLogin);
        btnChooseSignup = findViewById(R.id.btnChooseSignup);

        btnChooseLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLoginSignup.this,LoginScreenActivity.class);
                startActivity(intent);
            }
        });

        btnChooseSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ChooseLoginSignup.this,SignupScreenActivity.class);
                startActivity(intent2);
            }
        });
    }
}