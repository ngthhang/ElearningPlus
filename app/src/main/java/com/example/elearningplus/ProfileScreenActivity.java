package com.example.elearningplus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileScreenActivity extends AppCompatActivity {

    private DatabaseReference mData, studentId, studentName, studentGrade;
    private static final int REQUEST_ID_IMAGE_CAPTURE = 100;
    CircleImageView imgvAvatar;
    ListView listView;
    List<Profile_DiemSV> mlist;
    ImageButton imageButton;
    Button button;
    Profile_DiemSV_Adapter adapter;
    TextView tvTenSV,tvMSSV,tvDTBs;
    FirebaseUser mUser;
    String mssv;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);


        initView();
        bottomTab();
        getData();
    }

    protected void getData(){
        studentGrade.addListenerForSingleValueEvent( new ValueEventListener() {
            float k=0;
            int i=0;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    String name = snapshot.getKey();
                    Float grade = snapshot.getValue(Float.class);
                    mlist.add(new Profile_DiemSV( name,grade ));
                    k+=grade;
                    i+=1;
                }
                tvDTBs.setText(String.valueOf(k/i));
                display();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_ID_IMAGE_CAPTURE && resultCode== RESULT_OK && data!=null) {
            Bitmap bitmap2 = (Bitmap) data.getExtras().get("data");
            imgvAvatar.setImageBitmap(bitmap2);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile,menu);
        return super.onCreateOptionsMenu(menu);
    }

    protected void initView(){
        /* START - LIST GRADE REALTIME DATABASE */
        mlist = new ArrayList<>();
        tvTenSV = findViewById( R.id.tvTenSV );
        tvMSSV = findViewById( R.id.tvMSSV );
        tvDTBs = findViewById(R.id.tvDTBs);

        mData = FirebaseDatabase.getInstance().getReference();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        mssv = mUser.getEmail();
        mssv = mssv.replace("@gmail.com","").trim();

        studentId = mData.child( "user" ).child(mssv);
        studentName = studentId.child( "name" );
        studentGrade = studentId.child( "result" );

        tvMSSV.setText( mssv);
        studentName.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tvTenSV.setText( dataSnapshot.getValue().toString() );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }

    protected void display(){
        /* START - ADAPTER FOR GRADE */
        adapterForGrade();
        logOut();
        handleChooseImage();
        /* FINISH - ADAPTER FOR GRADE */
    }

    protected void adapterForGrade(){
        //Add database DiemSV
        //Truyền dữ liệu vào adapter
        listView=findViewById(R.id.listDiemSV);
        adapter=new Profile_DiemSV_Adapter(this,mlist);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    protected void logOut(){
        //Thiết lập sự kiện button log out
        button = findViewById(R.id.btnLogOut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileScreenActivity.this,ChooseLoginSignup.class);
                startActivity(intent);
            }
        });
    }

    protected void handleChooseImage(){
        //Thiết lập sự kiện imagebutton Camera
        imgvAvatar = findViewById(R.id.imgAvatar);
        imageButton = findViewById(R.id.imgbCamera);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_ID_IMAGE_CAPTURE);
            }
        });
    }

    protected void bottomTab(){
        /*START - HANDLE BOTTOM NAVIGATION */
        //Initial and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

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
    }
}
