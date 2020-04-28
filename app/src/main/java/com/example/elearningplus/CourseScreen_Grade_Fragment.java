package com.example.elearningplus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseScreen_Grade_Fragment extends Fragment {

    View view;
    private RecyclerView myrecyclerView;
    private List<CourseScreen_Grade> listGrade;
    private String courseKey;

    public CourseScreen_Grade_Fragment(List<CourseScreen_Grade> listGrade, String courseKey) {
        this.listGrade = listGrade;
        this.courseKey = courseKey;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.coursescreen_grade_fragment, container, false);
        myrecyclerView = (RecyclerView) view.findViewById(R.id.grade_recyclerview);
        CourseScreen_Grade_Adapter gradeAdapter = new CourseScreen_Grade_Adapter(getContext(), listGrade);
        myrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerView.setAdapter(gradeAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
