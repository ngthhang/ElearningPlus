package com.example.elearningplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView tvChapternumber, tvChaptercontent, tvChapterdetail1, tvChapterdetail2;
    ImageView imgvAnh;
    private DatabaseReference mData;
    String courseKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_view_screen);

        btnBackward = findViewById(R.id.btnBackward);
        btnForward = findViewById(R.id.btnForward);
        tvChapternumber = findViewById(R.id.tvChapternumber);
        tvChaptercontent = findViewById(R.id.tvChaptercontent);
        tvChapterdetail1 = findViewById(R.id.tvChapterdetail1);

        //SUPPORT ACTION BAR
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //GET KEY FROM INTENT PUTEXTRA
        Intent i = getIntent();
        Bundle b = i.getExtras();
        courseKey = b.getSerializable( "COURSE_KEY" ).toString();
        String currentChapter = b.getSerializable( "CURRENT_LESSON" ).toString();
        Integer chapterIdPosition = currentChapter.length()-1;
        final Character chapterId = currentChapter.charAt(chapterIdPosition);
        final String id = chapterId.toString();

        //GET DATA FOR FILE
        mData = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference contentLesson = mData.child( "course" ).child(courseKey).child( "lesson" );
        contentLesson.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    if (snapshot.child("chapter").getValue().toString().equals( id )){
                        tvChapternumber.setText( "Chapter " + id );
                        tvChaptercontent.setText( snapshot.child( "name" ).getValue().toString());
                        tvChapterdetail1.setText( snapshot.child( "content" ).getValue().toString() );
                        Long count = dataSnapshot.getChildrenCount();
                        if (id.equals( "1" )){
                            btnBackward.setOnClickListener( null );
                            btnForward.setOnClickListener( new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText( ChapterViewScreenActivity.this,"Forward",Toast.LENGTH_SHORT ).show();
                                }
                            } );
                        }
                        else if (count.toString().equals(id)){
                            btnForward.setOnClickListener( null );
                            btnBackward.setOnClickListener( new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(ChapterViewScreenActivity.this, "Clicked Back", Toast.LENGTH_SHORT).show();
                                }
                            } );
                        }
                        else {
                            btnForward.setOnClickListener( new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(ChapterViewScreenActivity.this, "Clicked F", Toast.LENGTH_SHORT).show();
                                }
                            } );
                            btnBackward.setOnClickListener( new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(ChapterViewScreenActivity.this, "Clicked B", Toast.LENGTH_SHORT).show();
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
