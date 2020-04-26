package com.example.elearningplus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class CourseScreen_Course_Fragment extends Fragment implements CourseScreen_Course_Adapter.OnNoteListener {

    View view;
    private RecyclerView myrecycleview;
    private List<CourseScreen_Course> listCourse;
    private DatabaseReference mData;
    private String courseKey;
    SharedPreferences sharedPreferences;


    public CourseScreen_Course_Fragment(List<CourseScreen_Course> listCourse, String courseKey){
        this.listCourse = listCourse;
        this.courseKey = courseKey;
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
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(getActivity(), ChapterViewScreenActivity.class);
        intent.putExtra( "CURRENT_LESSON", listCourse.get( position ).getnChapter() );
        intent.putExtra( "COURSE_KEY", courseKey );
        startActivity(intent);

    }

}
