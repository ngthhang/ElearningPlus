package com.example.elearningplus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChapterViewScreenActivity extends AppCompatActivity {
    Button btnBackward, btnForward;
    TextView tvChapternumber, tvChaptercontent, tvChapterdetail1;
    private DatabaseReference mData, contentLesson;
    String courseKey,currentChapter,chapter;
    SharedPreferences sharedPreferences;
    Integer finalK,chapterIdPosition, id,k,m;
    Character chapterId;
    SharedPreferences.Editor editor, editor1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_view_screen);

        initView();
        getDataFromIntent();
        getData();
        bottomTab();

    }

    private void getDataFromIntent(){
        //GET KEY FROM INTENT PUTEXTRA
        Intent i = getIntent();
        Bundle b = i.getExtras();
        courseKey = b.getSerializable( "COURSE_KEY" ).toString();
        currentChapter = b.getSerializable( "CURRENT_LESSON" ).toString();
        chapterIdPosition = currentChapter.length()-1;
        chapterId = currentChapter.charAt(chapterIdPosition);
        id = Integer.parseInt(chapterId.toString());
        //SET TITLE
        setTitle(courseKey);
    }

    private void getData(){

        k = sharedPreferences.getInt("numberOflesson",-1);
        if (k==-1){
            k=id;
        }

        //GET DATA FOR FILE
        mData = FirebaseDatabase.getInstance().getReference();
        contentLesson = mData.child( "course" ).child(courseKey).child( "lesson" );
        finalK = k;
        contentLesson.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Long count = dataSnapshot.getChildrenCount();
                    if (snapshot.child("chapter").getValue().toString().equals( String.valueOf(finalK) )){
                        chapter = "Chapter " + String.valueOf( finalK );
                        tvChapternumber.setText( chapter);
                        tvChaptercontent.setText( snapshot.child( "name" ).getValue().toString());
                        tvChapterdetail1.setText( snapshot.child( "content" ).getValue().toString() );

                        if (String.valueOf(finalK).equals( "1" )){
                            btnBackward.setOnClickListener( null );
                            btnForward.setOnClickListener( new View.OnClickListener() {
                                @Override
                                public void onClick(View v) { onClickForward();
                                }
                            } );
                        }
                        else if (count.toString().equals(String.valueOf(finalK))){
                            btnForward.setOnClickListener( null );
                            btnBackward.setOnClickListener( new View.OnClickListener() {
                                @Override
                                public void onClick(View v) { onClickBackward();
                                }
                            } );
                        }
                        else {
                            btnForward.setOnClickListener( new View.OnClickListener() {
                                @Override
                                public void onClick(View v) { onClickForward();
                                }
                            } );
                            btnBackward.setOnClickListener( new View.OnClickListener() {
                                @Override
                                public void onClick(View v) { onClickBackward();
                                }
                            } );
                        }
                        break;
                    };

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        editor1 = sharedPreferences.edit();
        editor1.putInt("numberOflesson",-1);
        editor1.commit();
    }

    private void initView(){
        btnBackward = findViewById(R.id.btnBackward);
        btnForward = findViewById(R.id.btnForward);
        tvChapternumber = findViewById(R.id.tvChapternumber);
        tvChaptercontent = findViewById(R.id.tvChaptercontent);
        tvChapterdetail1 = findViewById(R.id.tvChapterdetail1);

        sharedPreferences = getSharedPreferences("my_data", Context.MODE_PRIVATE);

        //SUPPORT ACTION BAR
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    private void onClickForward(){
        m = finalK +1;
        editor = sharedPreferences.edit();
        editor.putInt("numberOflesson",m);
        editor.commit();
        finish();
        startActivity(getIntent());
    }

    private void onClickBackward(){
        m = finalK -1;
        editor = sharedPreferences.edit();
        editor.putInt("numberOflesson",m);
        editor.commit();
        finish();
        startActivity(getIntent());
    }

    @Override
    public boolean onSupportNavigateUp(){
        Intent i = new Intent( this,HomeScreenActivity.class );
        startActivity(i);
        finish();
        return true;
    }

    protected void bottomTab(){
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
