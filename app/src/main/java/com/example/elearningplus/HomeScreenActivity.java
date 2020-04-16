package com.example.elearningplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenActivity extends AppCompatActivity {

    List<HomeCourse> homeCourseList;
    List<HomeAssignment> homeAssignmentList;
    public HomeCourseAdapter homeCourseAdapter;
    public HomeAssignmentAdapter homeAssignmentAdapter;
    public ViewPager courseViewPager, assignmentViewPager;
    private DatabaseReference mData;
    private FirebaseUser mUser;
    String studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // GET CURRENT USER
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        studentId = mUser.getEmail();
        studentId= studentId.replace("@gmail.com","").trim();

        /* START - VIEW PAGER ADAPTER */

        //create the list course fake data
        homeCourseList = new ArrayList<>(  );
        homeAssignmentList = new ArrayList<>(  );

        //create adapter for list course and assignment
        homeAssignmentAdapter = new HomeAssignmentAdapter( homeAssignmentList,this );
        homeCourseAdapter = new HomeCourseAdapter( homeCourseList,this );

        // set adapter for couse and assignment
        courseViewPager = (ViewPager)findViewById( R.id.home_viewpager );
        courseViewPager.setAdapter( homeCourseAdapter );
        courseViewPager.setPadding( 16,10, 300,20 );

        assignmentViewPager = (ViewPager)findViewById( R.id.home_course_viewpager );
        assignmentViewPager.setAdapter( homeAssignmentAdapter );
        assignmentViewPager.setPadding( 16,10, 300,20 );

//      /* FINISH - VIEW PAGER ADAPTER */



        /* START - REALTIME DATABASE WITH FIREBASE */
        mData = FirebaseDatabase.getInstance().getReference();

        final DatabaseReference studentCourse = mData.child( "user" ).child(studentId).child( "course" );

        studentCourse.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String courseKey = snapshot.getValue(String.class);
                    findCourse(courseKey);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

        /* FINISH - REALTIME DATABASE WITH FIREBASE */

        /* START - HANDLE BOTTOM NAVIGATION */
        //Initial and assign variable
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        //set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // handle click item in bottom_navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileScreenActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        return true;
                    case R.id.notification:
                        startActivity(new Intent(getApplicationContext(), NotificationScreenActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        /*FINISH - HANDLE BOTTOM NAVIGATION*/
    }

    protected void findCourse(final String courseKey){
        final DatabaseReference course =  mData.child( "course" );
        course.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild( courseKey )){
                    final DatabaseReference mCourse = course.child( courseKey );
                    final DatabaseReference mAssignment = mCourse.child( "assignment" );
                    mCourse.addListenerForSingleValueEvent( new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String name = dataSnapshot.child( "name" ).getValue().toString();
                            String id = dataSnapshot.child( "id" ).getValue().toString();
                            homeCourseList.add( new HomeCourse( name,id,courseKey ) );
                            homeCourseAdapter.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    } );
                    mAssignment.addListenerForSingleValueEvent( new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds: dataSnapshot.getChildren()){
                                String courseAssign = ds.child( "name" ).getValue(String.class);
                                String dueDate = ds.child( "due" ).getValue(String.class);
                                String courseName = ds.child( "course" ).getValue(String.class);
                                homeAssignmentList.add( new HomeAssignment( courseName,courseAssign,dueDate));
                                homeAssignmentAdapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    } );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }

}
