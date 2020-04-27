package com.example.elearningplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class LogUp_ChooseLesson_Activity extends AppCompatActivity{

   private List<LogUp_ChooseLesson_Data> mData;
   private ListView mListView;
   private LogUp_ChooseLesson_Adapter mAdapter;
   private Button mButton;
   private CheckBox chBoxSelectAll;

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

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogUp_ChooseLesson_Activity.this, LoginScreenActivity.class);
                startActivity(i);
            }
        });

    }

    private void initData(){
        mData = new ArrayList<>();
        mData.add(new LogUp_ChooseLesson_Data("Cấu trúc rời rạc"));
        mData.add(new LogUp_ChooseLesson_Data("Hệ cơ sở dữ liệu"));
        mData.add(new LogUp_ChooseLesson_Data("Toán tổ hợp và đồ thị"));
        mData.add(new LogUp_ChooseLesson_Data("Phương pháp tính"));
        mData.add(new LogUp_ChooseLesson_Data("Ứng dụng di động"));

        mAdapter = new LogUp_ChooseLesson_Adapter(this, R.layout.logup_chooselesson_items, mData);
        mListView.setAdapter(mAdapter);


    }
}
