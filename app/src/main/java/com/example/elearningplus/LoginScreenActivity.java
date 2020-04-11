package com.example.elearningplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreenActivity extends AppCompatActivity {
    Button btnLogin;
    TextView txtUser,txtPass,btnForgot;
    private FirebaseAuth mAuth;

    TextView tvResetPassWord;
    EditText etResetPassWord;
    Button btnResetPassWord;

    public static String mssv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        mAuth = FirebaseAuth.getInstance();
        btnLogin=(Button) findViewById(R.id.loginBut);
        btnForgot=(TextView) findViewById(R.id.forgotBut);
        txtUser=(TextView) findViewById(R.id.usernameText);
        txtPass=(TextView) findViewById(R.id.passwordText);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            };
        });
        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetPassWord();
            }
        });

    }
    private void Login(){
        String email = txtUser.getText().toString().trim();
        String password = txtPass.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginScreenActivity.this,"Đăng Nhập thành công",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginScreenActivity.this,HomeScreenActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginScreenActivity.this,"Email hoặc mật khẩu sai",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void ResetPassWord(){
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
                Toast.makeText(LoginScreenActivity.this,"Kiểm tra email để reset password",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
