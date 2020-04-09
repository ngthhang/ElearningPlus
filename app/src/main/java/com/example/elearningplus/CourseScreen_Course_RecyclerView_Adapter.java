package com.example.elearningplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseScreen_Course_RecyclerView_Adapter extends RecyclerView.Adapter<CourseScreen_Course_RecyclerView_Adapter.MyViewHolder> {

    Context mContext;
    List<CourseScreen_Course> mData;

    public CourseScreen_Course_RecyclerView_Adapter(Context mContext, List<CourseScreen_Course> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.course_layout, parent, false);
        MyViewHolder vHolder = new MyViewHolder(view);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_Chapter.setText(mData.get(position).getnChapter());
        holder.tv_Intro.setText(mData.get(position).getnIntro());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_Chapter;
        private TextView tv_Intro;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_Chapter = (TextView) itemView.findViewById(R.id.txt1);
            tv_Intro = (TextView) itemView.findViewById(R.id.txt2);
        }
    }
}
