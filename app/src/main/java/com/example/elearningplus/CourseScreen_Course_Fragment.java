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

import java.util.ArrayList;
import java.util.List;

public class CourseScreen_Course_Fragment extends Fragment {

    View view;
    private RecyclerView myrecycleview;
    private List<CourseScreen_Course> listCourse;

    public CourseScreen_Course_Fragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.course_fragment, container, false);
        myrecycleview = (RecyclerView) view.findViewById(R.id.course_recyclerview);
        CourseScreen_Course_RecyclerView_Adapter recyclerViewAdapter = new CourseScreen_Course_RecyclerView_Adapter(getContext(), listCourse);
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

    }
}
