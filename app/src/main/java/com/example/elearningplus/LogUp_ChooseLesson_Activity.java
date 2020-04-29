package com.example.elearningplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LogUp_ChooseLesson_Activity extends AppCompatActivity {

    private List<LogUp_ChooseLesson_Data> mData;
    private ListView mListView;
    private LogUp_ChooseLesson_Adapter mAdapter;
    private Button mButton;
    private CheckBox chBoxSelectAll;
    private FirebaseUser mUser;
    private DatabaseReference db, totalCourse, currentUserStorage;
    private ArrayList selectCourse;
    private String UserId,UserName, UserMail;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.logup_chooselesson_layout );
        setTitle( "Select course" );
        initView();
        initData();
        initUserProfile();
        getData();
        onClickSelectAll();
        onClickSelect();
    }

    private void initView() {
        mListView = findViewById( R.id.choose_lesson_ListView );
        mButton = findViewById( R.id.btnSelect );
        chBoxSelectAll = findViewById( R.id.chBoxSelectAll );

    }

    private void initData() {
        // CALL DATABASE IN FIREBASE
        db = FirebaseDatabase.getInstance().getReference();
        totalCourse = db.child( "course" );
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        mData = new ArrayList<>();
        mAdapter = new LogUp_ChooseLesson_Adapter( this, R.layout.logup_chooselesson_items, mData );
        mListView.setAdapter( mAdapter );
    }

    protected void initUserProfile(){
        Intent intent =  getIntent();
        Bundle b = intent.getExtras();
        UserName = b.getSerializable( "USER_NAME" ).toString();
        Toast.makeText( this,UserName,Toast.LENGTH_SHORT ).show();
        UserMail = mUser.getEmail();
        UserId = UserMail.replace("@gmail.com","").trim();
        user = new User(UserId,UserMail,UserName);
        // INIT USER IN FIRE BASE
        currentUserStorage = db.child( "user" ).child( UserId );
        currentUserStorage.setValue( user );
    }

    private void getData(){
        totalCourse.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name,id;
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                     name = snapshot.child( "name" ).getValue().toString();
                     id = snapshot.getKey();
                     mData.add( new LogUp_ChooseLesson_Data( id,name ));
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }

    protected void onClickSelectAll() {
        chBoxSelectAll.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                for(int i = 0 ; i< mData.size(); i++){
                    mData.get( i ).setChecked( isChecked );
                }
                mAdapter.notifyDataSetChanged();
            }
        } );
    }


    protected void onClickSelect() {
        mButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCourse = new ArrayList();
                String course;
                for (int i = 0; i < mData.size(); i++) {
                    if (mData.get( i ).isChecked()) {
                        course = mData.get( i ).getId();
                        selectCourse.add( course );
                    }
                }
                initDataFirebaseForUser();
                Intent intent = new Intent( LogUp_ChooseLesson_Activity.this,LoginScreenActivity.class );
                intent.putExtra( "PREVIOUS_MAIL", user.getEmail());
                startActivity( intent );
            }
        } );
    }

    protected void initDataFirebaseForUser(){
       final DatabaseReference finalListCourse = currentUserStorage.child( "course" );
       final DatabaseReference result = currentUserStorage.child( "result" );
       final DatabaseReference course_result = currentUserStorage.child( "course_result" );

       finalListCourse.setValue( selectCourse );
       for(int i = 0; i< selectCourse.size();i++){
           result.child( selectCourse.get( i ).toString()).setValue( 0 );
           course_result.child( selectCourse.get( i ).toString()).setValue( new CourseResultDetail() );
       }

    }
}