package com.example.elearningplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreenActivity extends AppCompatActivity {
    Button btnLogin,btnForgot;
    TextView txtUser,txtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        
        btnLogin=(Button) findViewById(R.id.loginBut);
        btnForgot=(Button) findViewById(R.id.forgotBut);
        txtUser=(TextView) findViewById(R.id.usernameText);
        txtPass=(TextView) findViewById(R.id.passwordText);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtUser.getText().length()!=0 && txtPass.getText().length()!=0){
                    Toast.makeText(LoginScreenActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginScreenActivity.this,HomeScreenActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginScreenActivity.this,"Mời bạn nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
        }
    });

    }

    // test loginscreen
}
