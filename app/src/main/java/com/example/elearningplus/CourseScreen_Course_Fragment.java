package com.example.elearningplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CourseScreen_Course_Fragment extends Fragment implements CourseScreen_Course_Adapter.OnNoteListener {

    View view;
    private RecyclerView myrecycleview;
    private List<CourseScreen_Course> listCourse;
    private DatabaseReference data;
    TextView txName, txContent;


    public CourseScreen_Course_Fragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.coursescreen_course_fragment, container, false);
        myrecycleview = (RecyclerView) view.findViewById(R.id.course_recyclerview);
        CourseScreen_Course_Adapter recyclerViewAdapter = new CourseScreen_Course_Adapter(getContext(), listCourse,this);
        myrecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecycleview.setAdapter(recyclerViewAdapter);


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listCourse = new ArrayList<>();
        listCourse.add(new CourseScreen_Course("Chapter 1", "Introduction to Android"));
        listCourse.add(new CourseScreen_Course("Chapter 2", "Introduction to Android"));
        listCourse.add(new CourseScreen_Course("Chapter 3", "Introduction to Android"));
        listCourse.add(new CourseScreen_Course("Chapter 4", "Introduction to Android"));
        listCourse.add(new CourseScreen_Course("Chapter 5", "Introduction to Android"));
        listCourse.add(new CourseScreen_Course("Chapter 6", "Introduction to Android"));
        listCourse.add(new CourseScreen_Course("Chapter 7", "Introduction to Android"));
        listCourse.add(new CourseScreen_Course("Chapter 8", "Introduction to Android"));

        /*txName.findViewById(R.id.txt1);
        txContent.findViewById(R.id.txt2);

        data = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mData = data.child("course").child("CSDL").child("lesson").child("0");
        mData.child("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name = (String) dataSnapshot.getValue();
                txName.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        mData.child("content").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String content = (String) dataSnapshot.getValue();
                txContent.setText(content);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        String mssv = mUser.getEmail();
        mssv = mssv.replace("@gmail.com","").trim();*/
    }

    @Override
    public void onNoteClick(int position) {
        listCourse.get(position);
        Intent intent = new Intent(getActivity(), AssignmentViewScreen.class);
        startActivity(intent);
    }

}
