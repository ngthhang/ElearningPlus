package com.example.elearningplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginScreenActivity extends AppCompatActivity {
    Button btnClick;
    TextView txtNd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
    btnClick=(Button) findViewById(R.id.loginBut);

    btnClick.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(LoginScreenActivity.this,HomeScreenActivity.class);
            startActivity(intent);
        }
    });
    txtNd=(TextView) findViewById(R.id.usernameText);
    txtNd.setText("");
    txtNd=(TextView) findViewById(R.id.passwordText);
    txtNd.setText("");
    }

    // test loginscreen
}
