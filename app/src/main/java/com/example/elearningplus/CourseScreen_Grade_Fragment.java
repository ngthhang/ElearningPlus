package com.example.elearningplus;

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

public class CourseScreen_Grade_Fragment extends Fragment {

    View view;
    private RecyclerView myrecyclerView;
    private List<CourseScreen_Grade> listGrade;
    private DatabaseReference data;
    TextView txName, proGrade, txGrade;

    public CourseScreen_Grade_Fragment(){
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

        listGrade = new ArrayList<>();
        listGrade.add(new CourseScreen_Grade("Progress Test 1",(float) 7.5 ));
        listGrade.add(new CourseScreen_Grade("Progress Test 2",(float) 8.0));
        listGrade.add(new CourseScreen_Grade("Mid-term test",(float) 9.0));
        listGrade.add(new CourseScreen_Grade("Final test",(float) 6.7));

        /*txName.findViewById(R.id.txt7);
        txGrade.findViewById(R.id.txt9);
        proGrade.findViewById(R.id.txt8);

        data = FirebaseDatabase.getInstance().getReference();
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
        String mssv = mUser.getEmail();
        mssv = mssv.replace("@gmail.com","").trim();

        final DatabaseReference studentId = data.child( "user" ).child(mssv);
        final DatabaseReference studentGrade = studentId.child( "result" );

        studentGrade.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }
}
