package com.example.elearningplus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileScreenActivity extends AppCompatActivity {
    private static final int REQUEST_ID_IMAGE_CAPTURE = 100;
    private static final int REQUEST_ID_IMAGE_CHOOSE = 161;

    public float k=0;
    public int i=0;
    public float m=0;
    public String mssv;

    DatabaseReference mData;
    CircleImageView imgvAvatar;
    ListView listView;
    List<Profile_DiemSV> mlist;
    ImageButton imageButton;
    Button button;
    TextView tvMSSV;
    TextView tvTenSV;
    TextView tvDTBs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

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

        //Ánh xạ
        tvTenSV = findViewById(R.id.tvTenSV);
        tvMSSV =findViewById(R.id.tvMSSV);
        tvDTBs = findViewById(R.id.tvDTBs);
        listView=findViewById(R.id.listDiemSV);

        //Khởi tạo mData
        mData = FirebaseDatabase.getInstance().getReference();

        //Use database Ten
        FirebaseUser user;

        user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();
        mssv=email;
        mssv=mssv.replace("@gmail.com","").trim();

        mData.child("user").child(mssv).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tvTenSV.setText((String) dataSnapshot.getValue());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        //Use database MSSV
        mData.child("user").child(mssv).child("id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                tvMSSV.setText(dataSnapshot1.getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        //Add database
        //mData.child("user").child(mssv).child("result").push().setValue(new Profile_DiemSV("NMHĐH",(float) 6.5));

        //Use database DiemSV
        mlist = new ArrayList<>();
        final Profile_DiemSV_Adapter adapter=new Profile_DiemSV_Adapter(this,mlist);
        listView.setAdapter(adapter);

        mData.child("user").child(mssv).child("result").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Profile_DiemSV diemSV = dataSnapshot.getValue(Profile_DiemSV.class);
                mlist.add(new Profile_DiemSV(diemSV.tenMH,diemSV.diemMH));
                i+=1;
                k=k+diemSV.diemMH;
                m=k/i;
                tvDTBs.setText(String.format("%.3g%n",m));
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Thiết lập sự kiện button log out
        button = findViewById(R.id.btnLogOut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileScreenActivity.this,LoginScreenActivity.class);
                startActivity(intent);
            }
        });

        //button, imagebutton Camera
        imgvAvatar = findViewById(R.id.imgAvatar);
        imageButton = findViewById(R.id.imgbCamera);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu();
            }
        });
    }

    //Menu for Camera
    private void ShowMenu(){
        final PopupMenu popupMenu = new PopupMenu(this,imageButton);
        popupMenu.getMenuInflater().inflate(R.menu.menu_profile,popupMenu.getMenu());
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuChup:
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent,REQUEST_ID_IMAGE_CAPTURE);
                        break;
                    case R.id.menuChon:
                        Intent intent1 = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent1,REQUEST_ID_IMAGE_CHOOSE);
                        break;
                }
                return false;
            }
        });
    }

    //Make a photo
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Capture
        if (requestCode==REQUEST_ID_IMAGE_CAPTURE && resultCode== RESULT_OK && data!=null) {
            Bitmap bitmap1 = (Bitmap) data.getExtras().get("data");
            imgvAvatar.setImageBitmap(bitmap1);
        }
        else if(requestCode == REQUEST_ID_IMAGE_CHOOSE && resultCode == RESULT_OK) {
            try {
                //Choose
                Uri imageUri = data.getData();
                Bitmap bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imgvAvatar.setImageBitmap(bitmap2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
