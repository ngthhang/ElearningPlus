package com.example.elearningplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChapterViewScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_view_screen);


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
}
