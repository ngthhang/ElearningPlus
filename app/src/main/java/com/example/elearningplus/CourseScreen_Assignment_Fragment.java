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

public class CourseScreen_Assignment_Fragment extends Fragment implements CourseScreen_Assignment_Adapter.OnNoteListener {

    View view;
    private RecyclerView myrecyclerView;
    private List<CourseScreen_Assignment> listAssignment;
    private DatabaseReference data;
    TextView txName, txContent, txDay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =inflater.inflate(R.layout.coursescreen_assignment_fragment, container, false);
        myrecyclerView = (RecyclerView) view.findViewById(R.id.assignment_recyclerview);
        CourseScreen_Assignment_Adapter assignment_adapter = new CourseScreen_Assignment_Adapter(getContext(), listAssignment, this);
        myrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerView.setAdapter(assignment_adapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listAssignment = new ArrayList<>();
        listAssignment.add(new CourseScreen_Assignment("Lab 1", "Introduction", "11/12/2000"));
        listAssignment.add(new CourseScreen_Assignment("Lab 2", "Introduction", "9/7/2000"));
        listAssignment.add(new CourseScreen_Assignment("Lab 3", "Introduction", "7/8/2000"));
        listAssignment.add(new CourseScreen_Assignment("Lab 4", "Introduction", "21/12/2000"));
        listAssignment.add(new CourseScreen_Assignment("Lab 5", "Introduction", "30/1/2000"));
        listAssignment.add(new CourseScreen_Assignment("Lab 6", "Introduction", "15/6/2000"));
        listAssignment.add(new CourseScreen_Assignment("Lab 7", "Introduction", "8/4/2000"));
        listAssignment.add(new CourseScreen_Assignment("Lab 8", "Introduction", "19/22/2000"));
        listAssignment.add(new CourseScreen_Assignment("Lab 9", "Introduction", "13/3/2000"));

        /*txName.findViewById(R.id.txt3);
        txContent.findViewById(R.id.txt4);
        txDay.findViewById(R.id.txt5);

        data = FirebaseDatabase.getInstance().getReference();
        DatabaseReference mData = data.child("course").child("CSDL").child("assignment").child("0");
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

        mData.child("due").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String due = (String) dataSnapshot.getValue();
                txDay.setText(due);
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
        listAssignment.get(position);
        Intent intent = new Intent(getActivity(), AssignmentViewScreen.class);
        startActivity(intent);
    }
}
