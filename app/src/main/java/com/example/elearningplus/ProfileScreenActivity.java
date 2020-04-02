package com.example.elearningplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class ProfileScreenActivity extends AppCompatActivity {
    ListView listView;
    List<Profile_DiemSV> mlist;
    ImageButton imageButton;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        //Circling Avatar
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.giang);
        Bitmap circularBitmap = Profile_ImageConverter.getRoundedCornerBitmap(bitmap, 100);

        ImageView circularImageView = (ImageView) findViewById(R.id.imgAvatar);
        circularImageView.setImageBitmap(circularBitmap);

        /*START - HANDLE BOTTOM NAVIGATION */
        //Initial and assign variable
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        //set Home selected
        bottomNavigationView.setSelectedItemId(R.id.profile);

        // handle click item in bottom_navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.profile:
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

        //Add database DiemSV
        mlist = new ArrayList<>();

        mlist.add(new Profile_DiemSV("CTRR",(float) 7.5));
        mlist.add(new Profile_DiemSV("MMT",(float) 8));
        mlist.add(new Profile_DiemSV("NMHĐH",(float) 6));
        mlist.add(new Profile_DiemSV("SXTK",(float) 8.2));
        mlist.add(new Profile_DiemSV("PTƯDDĐ",(float) 9.3));

        //Truyền dữ liệu vào adapter
        listView=findViewById(R.id.listDiemSV);
        Profile_DiemSV_Adapter adapter=new Profile_DiemSV_Adapter(this,mlist);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //Thiết lập sự kiện button log out
        button = findViewById(R.id.btnLogOut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileScreenActivity.this,LoginScreenActivity.class);
                startActivity(intent);
            }
        });

        //Thiết lập sự kiện imagebutton Camera
        imageButton = findViewById(R.id.imgbCamera);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile,menu);
        return super.onCreateOptionsMenu(menu);
    }

}
