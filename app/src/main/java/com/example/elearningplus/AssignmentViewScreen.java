package com.example.elearningplus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AssignmentViewScreen extends AppCompatActivity {
    DatabaseReference courseDtb;
    DatabaseReference mCourse;
    TextView tvLab, tvDue, tvContent;
    EditText edtUrl;
    Button btnSubmit;
    String m;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_view);

        sharedPreferences = getSharedPreferences("my_data",Context.MODE_PRIVATE);


        courseDtb = FirebaseDatabase.getInstance().getReference();
        tvLab = findViewById(R.id.tvLab);
        tvContent = findViewById(R.id.tvContent);
        tvDue = findViewById(R.id.tvDue);
        edtUrl = findViewById(R.id.edtUrl);
        btnSubmit = findViewById(R.id.btnSubmit);


//        /*START - HANDLE BOTTOM NAVIGATION */
//        //Initial and assign variable
//        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
//
//        //set Home selected
//        bottomNavigationView.setSelectedItemId(R.id.home);
//
//        // handle click item in bottom_navigation
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()){
//                    case R.id.profile:
//                        startActivity(new Intent(getApplicationContext(), ProfileScreenActivity.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                    case R.id.home:
//                        return true;
//                    case R.id.notification:
//                        startActivity(new Intent(getApplicationContext(), NotificationScreenActivity.class));
//                        overridePendingTransition(0,0);
//                        return true;
//                }
//                return false;
//            }
//        });
//        /*FINISH - HANDLE BOTTOM NAVIGATION*/

        mCourse = courseDtb.child("course").child("CSDL").child("assignment").child("0");
        mCourse.child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = (String) dataSnapshot.getValue();
                tvLab.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        mCourse.child("due").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String due = (String) dataSnapshot.getValue();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("due",due);
                editor.commit();
                due ="Due: "+due + "\nPoint: 0\nSubmitting: a file upload";
                tvDue.setText(due);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        mCourse.child("content").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String content = (String) dataSnapshot.getValue();
                tvContent.setText(content);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        edtUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                m = String.valueOf(s);
            }
        });

        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        String mssv = mUser.getEmail();
        mssv = mssv.replace("@gmail.com","").trim();

        final DatabaseReference userCourse = courseDtb.child("user").child(mssv).child("assignment").child("CTRR").child("0");
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                String duedate = sharedPreferences.getString("due", "");

                SimpleDateFormat dinhDangNgay = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                try {
                    if (calendar.getTime().after(dinhDangNgay.parse(duedate))){
                        btnSubmit.setVisibility(View.INVISIBLE);
                    }
                    else {
                        Toast.makeText(AssignmentViewScreen.this,dinhDangNgay.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
                        userCourse.child("sent_time").setValue(calendar.getTime().toString());
                        userCourse.child("enclosed").setValue(m);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuBackk){
            Toast.makeText(this, "Finish", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp(){
        Intent i = new Intent( this,HomeScreenActivity.class );
        startActivity( i );
        finish();
        return true;
    }

}
