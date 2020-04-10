package com.example.elearningplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        /* START - REALTIME DATABASE WITH FIREBASE */
        final ArrayList<String> course = new ArrayList<>();

        mData = FirebaseDatabase.getInstance().getReference();

        final DatabaseReference studentCourse = mData.child( "user" ).child( "51800378").child( "course" );

        studentCourse.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    course.add( snapshot.getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );


        /* FINISH - REALTIME DATABASE WITH FIREBASE */


        /* START - VIEW PAGER ADAPTER */

        //create the list course fake data
        homeCourseList = new ArrayList<>(  );
        homeCourseList.add( new HomeCourse( "Cấu trúc rời rạc","CTRR0101" ) );
        homeCourseList.add( new HomeCourse( "Xác suất thống kê","XSTK1002" ) );
        homeCourseList.add( new HomeCourse( "Cơ sở dữ liệu","CSDL111" ) );
        homeCourseList.add( new HomeCourse( "Phương pháp tính", "PPT112" ) );

        homeAssignmentList = new ArrayList<>(  );
        homeAssignmentList.add( new HomeAssignment( "Cấu trúc rời rạc","LAB1" ) );
        homeAssignmentList.add( new HomeAssignment( "Cơ sở dữ liệu","LAB 7" ) );
        homeAssignmentList.add( new HomeAssignment( "Xác suất thống kê","LAB 3" ) );
        homeAssignmentList.add( new HomeAssignment( "Phương pháp tính", "LAB 9" ) );

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

        /* FINISH - VIEW PAGER ADAPTER */








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
}
