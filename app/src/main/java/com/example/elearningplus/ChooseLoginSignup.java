package com.example.elearningplus;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ChooseLoginSignup extends AppCompatActivity {
    Button btnChooseLogin;
    Button btnChooseSignup;
    TextView tvForgotPassWord;
    TextView tvResetPassWord;
    EditText etResetPassWord;
    Button btnResetPassWord;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_login_signup);

        mAuth = FirebaseAuth.getInstance();

        btnChooseLogin = findViewById(R.id.btnChooseLogin);
        btnChooseSignup = findViewById(R.id.btnChooseSignup);
        tvForgotPassWord = findViewById(R.id.tvForgotPassword);

        btnChooseLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLoginSignup.this, LoginScreenActivity.class);
                startActivity(intent);
            }
        });

        btnChooseSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ChooseLoginSignup.this, SignupScreenActivity.class);
                startActivity(intent2);
            }
        });

        tvForgotPassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetPassWord();
            }
        });
    }

    public void ResetPassWord() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_resetpassword);
        dialog.show();

        tvResetPassWord = findViewById(R.id.tvResetPassWord);
        etResetPassWord = dialog.findViewById(R.id.etResetPassWord);
        btnResetPassWord = dialog.findViewById(R.id.btnResetPassWord);

        btnResetPassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.sendPasswordResetEmail(etResetPassWord.getText().toString().trim());
                Toast.makeText(ChooseLoginSignup.this, "Kiểm tra email để reset password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}