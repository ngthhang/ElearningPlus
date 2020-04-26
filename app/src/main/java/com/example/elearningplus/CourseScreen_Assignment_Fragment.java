package com.example.elearningplus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseScreen_Assignment_Fragment extends Fragment implements CourseScreen_Assignment_Adapter.OnNoteListener {

    View view;
    private RecyclerView myrecyclerView;
    private List<CourseScreen_Assignment> listAssignment;
    private String courseKey;

    public CourseScreen_Assignment_Fragment(List<CourseScreen_Assignment> listAssignment, String courseKey){
        this.listAssignment = listAssignment;
        this.courseKey = courseKey;
    }

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
    }

    @Override
    public void onNoteClick(int position) {
        CourseScreen_Assignment  assignment =listAssignment.get(position);
        if(assignment.getIsOpen().equals( "false" )){
            Toast.makeText(getContext(),"Assignment đã bị khoá không thể nộp",Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(getActivity(), AssignmentViewScreen.class);
            intent.putExtra("COURSE_KEY", courseKey);
            intent.putExtra("ASSIGNMENT_ID", position);
            intent.putExtra( "IS_LATE", assignment.getLate() );
            startActivity(intent);
        }
    }
}
