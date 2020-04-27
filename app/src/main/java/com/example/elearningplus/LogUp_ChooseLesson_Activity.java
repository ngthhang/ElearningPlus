package com.example.elearningplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class LogUp_ChooseLesson_Activity extends AppCompatActivity{

   private List<LogUp_ChooseLesson_Data> mData;
   private ListView mListView;
   private LogUp_ChooseLesson_Adapter mAdapter;
   private Button mButton;
   private CheckBox chBoxSelectAll;
   private DatabaseReference mdatabase;
   private TextView txtName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logup_chooselesson_layout);

        initView();
        initData();
    }

    private void initView(){
        mListView = findViewById(R.id.choose_lesson_ListView);
        mButton = findViewById(R.id.btnSelect);
        chBoxSelectAll = findViewById(R.id.chBoxSelectAll);
        txtName = findViewById(R.id.txtName);

        chBoxSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(LogUp_ChooseLesson_Activity.this, LoginScreenActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void initData(){
        mData = new ArrayList<>();
        mData.add(new LogUp_ChooseLesson_Data("Hệ cơ sở dữ liệu"));
        mData.add(new LogUp_ChooseLesson_Data("Cấu trúc rời rạc"));
        mData.add(new LogUp_ChooseLesson_Data("Hệ điều hành"));
        mData.add(new LogUp_ChooseLesson_Data("Ứng dụng di động"));
        mData.add(new LogUp_ChooseLesson_Data("Xác suất thống kê"));

        mAdapter = new LogUp_ChooseLesson_Adapter(this, R.layout.logup_chooselesson_items, mData);
        mListView.setAdapter(mAdapter);

        /*mdatabase = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference choose_courses = mdatabase.child("Course");

        choose_courses.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = (String) dataSnapshot.getValue();
                txtName.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }
}
