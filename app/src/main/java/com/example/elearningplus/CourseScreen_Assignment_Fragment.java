package com.example.elearningplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CourseScreen_Assignment_Fragment extends Fragment implements CourseScreen_Assignment_Adapter.OnNoteListener {

    View view;
    private RecyclerView myrecyclerView;
    private List<CourseScreen_Assignment> listAssignment;

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
    }

    @Override
    public void onNoteClick(int position) {
        listAssignment.get(position);
        Intent intent = new Intent(getActivity(), AssignmentViewScreen.class);
        startActivity(intent);
    }
}
