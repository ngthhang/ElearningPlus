package com.example.elearningplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AssignmentViewScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_view);




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

    }
}
