package com.example.elearningplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_screen);


        // GET COURSE KEY FROM INTENT
        Intent i = getIntent();
        Bundle b = i.getExtras();
        String courseKey = b.getSerializable( "COURSE_KEY" ).toString();

        tabLayout = (TabLayout) findViewById(R.id.tabLayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewPager_id);

        listCourse = new ArrayList<>(  );
        final CourseScreen_ViewPager_Adapter adapter = new CourseScreen_ViewPager_Adapter(getSupportFragmentManager());
        adapter.AddFragment(new CourseScreen_Course_Fragment(listCourse,courseKey), "Lesson");
        adapter.AddFragment(new CourseScreen_Assignment_Fragment(), "Assignment");
        adapter.AddFragment(new CourseScreen_Grade_Fragment(), "Grade");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // GET DATA FOR FRAGMENT
        mData = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference currentCourse = mData.child( "course" ).child( courseKey ).child( "lesson" );
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




        /*START - HANDLE BOTTOM NAVIGATION */
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
                        startActivity(new Intent(getApplicationContext(), HomeScreenActivity.class));
                        overridePendingTransition(0,0);
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

    @Override
    public boolean onSupportNavigateUp(){
        Intent i = new Intent( this,HomeScreenActivity.class );
        startActivity(i);
        finish();
        return true;
    }
}
