package com.example.elearningplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreenActivity extends AppCompatActivity {
    Button btnLogin;
    TextView txtUser,txtPass,btnForgot;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_screen);
        btnLogin=(Button) findViewById(R.id.loginBut);
        btnForgot=(TextView) findViewById(R.id.forgotBut);
        txtUser=(TextView) findViewById(R.id.usernameText);
        txtPass=(TextView) findViewById(R.id.passwordText);

        btnLogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                Login();
            }
        });
        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginScreenActivity.this, "Béo ú", Toast.LENGTH_SHORT).show();
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
}
