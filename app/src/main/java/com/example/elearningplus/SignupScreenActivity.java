package com.example.elearningplus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupScreenActivity extends AppCompatActivity {
    EditText edtYourName, edtUserName, edtPassWord;
    Button btnSignup;
    FirebaseAuth auth;
    Toast error;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        auth=FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("my_data", Context.MODE_PRIVATE);

        edtYourName = findViewById(R.id.edtYourName);
        edtUserName = findViewById(R.id.usernameText);
        edtPassWord = findViewById(R.id.passwordText);
        btnSignup = findViewById(R.id.btnSignup);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String yourname = edtYourName.getText().toString().trim();
                String email = edtUserName.getText().toString().trim();
                String password = edtPassWord.getText().toString().trim();

                if(email.isEmpty() || email == null || password.isEmpty() || password == null || yourname.isEmpty() || yourname==null){
                    error = Toast.makeText( SignupScreenActivity.this,"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT );
                    error.show();}
                else {
                    //create user
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignupScreenActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(SignupScreenActivity.this, "Authentication failed." + task.getException(),
                                                Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(SignupScreenActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("yourname",yourname);
                                        editor.commit();
                                        String k = sharedPreferences.getString("yourname","");
                                        Toast.makeText(SignupScreenActivity.this, k, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SignupScreenActivity.this, HomeScreenActivity.class);
                                        startActivity(intent);

                                        finish();
                                    }
                                }
                            });
                }
            }
        });
    }
}
