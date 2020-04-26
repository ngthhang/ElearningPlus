package com.example.elearningplus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CourseScreenActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DatabaseReference mData;
    private List<CourseScreen_Course> listCourse;
    private List<CourseScreen_Assignment> listAssignment;
    private List<CourseScreen_Grade> listGrade;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_screen);
        sharedPreferences = getSharedPreferences("my_data", Context.MODE_PRIVATE);

        // GET COURSE KEY FROM INTENT
        Intent i = getIntent();
        Bundle b = i.getExtras();
        final String courseKey = b.getSerializable( "COURSE_KEY" ).toString();

        tabLayout = (TabLayout) findViewById(R.id.tabLayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewPager_id);

        listCourse = new ArrayList<>(  );
        listAssignment = new ArrayList<>();
        listGrade = new ArrayList<>();
        final CourseScreen_ViewPager_Adapter adapter = new CourseScreen_ViewPager_Adapter(getSupportFragmentManager());
        adapter.AddFragment(new CourseScreen_Course_Fragment(listCourse,courseKey), "Lesson");
        adapter.AddFragment(new CourseScreen_Assignment_Fragment(listAssignment,courseKey), "Assignment");
        adapter.AddFragment(new CourseScreen_Grade_Fragment(listGrade,courseKey), "Grade");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // SET TITLE FOR ACTION BAR
        mData = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference currentCourseName = mData.child( "course" ).child( courseKey );
        currentCourseName.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setTitle(  dataSnapshot.child( "name" ).getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

        // GET DATA FOR FRAGMENT LESSON
        final DatabaseReference currentCourse = mData.child( "course" ).child( courseKey ).child( "lesson" );
        currentCourse.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String Chapter ="Chapter " + snapshot.child( "chapter" ).getValue().toString();
                    String Intro =snapshot.child( "name" ).getValue().toString();
                    listCourse.add( new CourseScreen_Course( Chapter,Intro ) );
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );


        // GET DATA FOR FRAGMENT ASSIGNMENT
        final DatabaseReference currentAssignment = mData.child( "course" ).child( courseKey ).child( "assignment" );
        currentAssignment.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String name =snapshot.child( "name" ).getValue().toString();
                    String due =snapshot.child( "due" ).getValue().toString();
                    String isOpen = snapshot.child( "isOpen" ).getValue().toString();
                    listAssignment.add( new CourseScreen_Assignment( name,due,isOpen ));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

        //GET DATA FOR FRAGMEMT GRADE
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        String mssv = mUser.getEmail();
        mssv = mssv.replace("@gmail.com","").trim();
        final  DatabaseReference currentGrade = mData.child("user").child( mssv ).child( "course_result" ).child( courseKey );
        currentGrade.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String finalTest = snapshot.child("final test").getValue().toString();
                String midtermTest = snapshot.child("mid-term test").getValue().toString();
                String progress1 = snapshot.child("progress_1").getValue().toString();
                String progress2 = snapshot.child("progress_2").getValue().toString();
                listGrade.add( new CourseScreen_Grade( "Progress Test 1", Float.valueOf( progress1 )));
                listGrade.add( new CourseScreen_Grade( "Progress Test 2", Float.valueOf( progress2 )));
                listGrade.add( new CourseScreen_Grade( "Midterm Test", Float.valueOf( midtermTest )));
                listGrade.add( new CourseScreen_Grade( "Final Test", Float.valueOf( finalTest )));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

    }

    @Override
    public boolean onSupportNavigateUp(){
        Intent i = new Intent( this,HomeScreenActivity.class );
        startActivity(i);
        finish();
        return true;
    }
}
