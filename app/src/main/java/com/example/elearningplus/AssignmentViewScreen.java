package com.example.elearningplus;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AssignmentViewScreen extends AppCompatActivity {
    DatabaseReference mCourse,courseDtb,UserSubmit;
    String courseKey;
    TextView tvLab, tvDue, tvContent;
    EditText edtUrl;
    Button btnSubmit;
    String m, mssv,dateFormat, assignmentId,isLate, due;
    CardView cardView;
    FirebaseUser mUser;
    Date curentDate;
    SimpleDateFormat dinhDangNgay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_view);
        getDataFromIntent();
        initView();
        getData();
        backButton();
    }

    public void backButton(){
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp(){
        Intent i = new Intent( this,HomeScreenActivity.class );
        startActivity( i );
        finish();
        return true;
    }

    protected void initView(){
        courseDtb = FirebaseDatabase.getInstance().getReference();
        mCourse = courseDtb.child("course").child(courseKey).child("assignment").child(assignmentId);
        tvLab = findViewById(R.id.tvLab);
        tvContent = findViewById(R.id.tvContent);
        tvDue = findViewById(R.id.tvDue);
        edtUrl = findViewById(R.id.edtUrl);
        btnSubmit = findViewById(R.id.btnSubmit);
        cardView = findViewById( R.id.late_tag );

        if(isLate.equals( "false" )){
            cardView.setVisibility( View.GONE );
        }else{
            cardView.setVisibility( View.VISIBLE );
        }

        edtUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                m = String.valueOf(s);
            }
        });

    }

    protected void getDataFromIntent(){
        // GET DATA FROM INTENT
        Intent i = getIntent();
        Bundle b = i.getExtras();
        courseKey = b.getSerializable( "COURSE_KEY" ).toString();
        assignmentId = b.getSerializable( "ASSIGNMENT_ID" ).toString();
        Toast.makeText( AssignmentViewScreen.this,assignmentId,Toast.LENGTH_SHORT ).show();
        isLate = b.getSerializable( "IS_LATE" ).toString();
        setTitle( courseKey );
    }
    protected void getData(){
       getDue();
       getName();
       getContent();
       getPreviousSubmit();
       submitLink();
    }

    protected void getPreviousSubmit(){
        // CHECK IF FILE HAS SUBMITTED BEFORE
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mssv = mUser.getEmail();
        mssv = mssv.replace("@gmail.com","").trim();
        UserSubmit = courseDtb.child("user").child( mssv ).child( "assignment" ).child(courseKey);
        UserSubmit.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild( assignmentId )){
                    String lastEnclosed = dataSnapshot.child( assignmentId ).child( "enclosed" ).getValue().toString();
                    edtUrl.setText( lastEnclosed );
                    btnSubmit.setText( "RE-SUBMIT" );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }

    protected void submitLink(){
        // HANDLE SUBMIT LINK
        btnSubmit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curentDate = new Date();
                dinhDangNgay = new SimpleDateFormat("dd/MM/yyyy");
                dateFormat = dinhDangNgay.format( curentDate );
                if(m.equals( "" ) || m == null){
                    Toast.makeText( AssignmentViewScreen.this, "Chưa nộp link bài tập ", Toast.LENGTH_SHORT ).show();
                }else {
                    UserSubmit.child( assignmentId ).child( "sent_time" ).setValue( dateFormat );
                    UserSubmit.child( assignmentId ).child( "enclosed" ).setValue( m );
                    UserSubmit.child( assignmentId ).child( "late" ).setValue( isLate );
                    UserSubmit.child( assignmentId ).child( "name" ).setValue( tvLab.getText() );
                    Toast.makeText( AssignmentViewScreen.this,"Đã nộp",Toast.LENGTH_SHORT).show();
                    btnSubmit.setText( "RE-SUBMIT" );
                }
            }
        } );

    }

    protected void getDue(){
        mCourse.child("due").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                due = (String) dataSnapshot.getValue();
                tvDue.setText(" Due: "+ due +"\n Point: 0\n Submitting: a link upload");
                if(due== null){
                    Toast.makeText( AssignmentViewScreen.this,"Null me roi",Toast.LENGTH_SHORT );
                }
                Toast.makeText( AssignmentViewScreen.this,due,Toast.LENGTH_SHORT );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    protected void getName(){
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

    }

    protected void getContent(){
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
    }


}
