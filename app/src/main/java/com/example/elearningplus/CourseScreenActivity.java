package com.example.elearningplus;

import android.content.Intent;
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
    private DatabaseReference mData,currentCourseName,currentCourse,currentAssignment,currentGrade;
    private List<CourseScreen_Course> listCourse;
    CourseScreen_ViewPager_Adapter adapter;
    private String courseKey, mssv, finalTest,midtermTest,progress1,progress2,name,due,isOpen;
    private List<CourseScreen_Assignment> listAssignment;
    private List<CourseScreen_Grade> listGrade;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_screen);

        // GET COURSE KEY FROM INTENT
        Intent i = getIntent();
        Bundle b = i.getExtras();
        courseKey = b.getSerializable( "COURSE_KEY" ).toString();
        mData = FirebaseDatabase.getInstance().getReference();

        initData();
        setTitleForAppBar();
        getData();
    }

    protected void setTitleForAppBar(){
        currentCourseName = mData.child( "course" ).child( courseKey );
        currentCourseName.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setTitle(  dataSnapshot.child( "name" ).getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }

    protected void initData(){
        tabLayout = (TabLayout) findViewById(R.id.tabLayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewPager_id);

        listCourse = new ArrayList<>(  );
        listAssignment = new ArrayList<>();
        listGrade = new ArrayList<>();
        adapter = new CourseScreen_ViewPager_Adapter(getSupportFragmentManager());
        adapter.AddFragment(new CourseScreen_Course_Fragment(listCourse,courseKey), "Lesson");
        adapter.AddFragment(new CourseScreen_Assignment_Fragment(listAssignment,courseKey), "Assignment");
        adapter.AddFragment(new CourseScreen_Grade_Fragment(listGrade,courseKey), "Grade");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    protected void getData(){
        getAssignment();
        getLesson();
        getGrade();
    }

    protected void getAssignment(){
        // GET DATA FOR FRAGMENT ASSIGNMENT
        currentAssignment = mData.child( "course" ).child( courseKey ).child( "assignment" );
        currentAssignment.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    name =snapshot.child( "name" ).getValue().toString();
                    due =snapshot.child( "due" ).getValue().toString();
                    isOpen = snapshot.child( "isOpen" ).getValue().toString();
                    listAssignment.add( new CourseScreen_Assignment( name,due,isOpen ));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );


    }

    protected void getLesson(){
        // GET DATA FOR FRAGMENT LESSON
        currentCourse = mData.child( "course" ).child( courseKey ).child( "lesson" );
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

    }

    protected void getGrade(){
        //GET DATA FOR FRAGMEMT GRADE
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        mssv = mUser.getEmail();
        mssv = mssv.replace("@gmail.com","").trim();
        currentGrade = mData.child("user").child( mssv ).child( "course_result" ).child( courseKey );
        currentGrade.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                finalTest = snapshot.child("final_test").getValue().toString();
                midtermTest = snapshot.child("mid_test").getValue().toString();
                progress1 = snapshot.child("progress_1").getValue().toString();
                progress2 = snapshot.child("progress_2").getValue().toString();
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
