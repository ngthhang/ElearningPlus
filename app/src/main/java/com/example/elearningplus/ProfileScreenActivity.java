package com.example.elearningplus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileScreenActivity extends AppCompatActivity {
    private static final int REQUEST_ID_IMAGE_CAPTURE = 100;
    private static final int REQUEST_ID_IMAGE_CHOOSE = 161;

    CircleImageView imgvAvatar;
    ListView listView;
    List<Profile_DiemSV> mlist;
    ImageButton imageButton;
    Button button;

    @SuppressLint("CutPasteId")
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
        imgvAvatar = findViewById(R.id.imgAvatar);
        imageButton = findViewById(R.id.imgbCamera);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu();
            }
        });
    }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_ID_IMAGE_CAPTURE && resultCode== RESULT_OK && data!=null) {
            Bitmap bitmap1 = (Bitmap) data.getExtras().get("data");
            imgvAvatar.setImageBitmap(bitmap1);
        }
        else if(requestCode == REQUEST_ID_IMAGE_CHOOSE && resultCode == RESULT_OK) {
            try {
                //xử lý lấy ảnh chọn từ điện thoại:
                Uri imageUri = data.getData();
                Bitmap bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imgvAvatar.setImageBitmap(bitmap2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
