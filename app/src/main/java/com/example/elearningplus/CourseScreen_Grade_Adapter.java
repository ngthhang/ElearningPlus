package com.example.elearningplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseScreen_Grade_Adapter extends RecyclerView.Adapter<CourseScreen_Grade_Adapter.MyViewHolder> {

    Context mContext;
    List<CourseScreen_Grade> mData;

    public CourseScreen_Grade_Adapter(Context mContext, List<CourseScreen_Grade> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.coursescreen_grade_layout, parent, false);
        MyViewHolder vHolder = new MyViewHolder(view);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_NameGrade.setText(mData.get(position).getNameGrade());
        holder.tv_Grade.setText(Float.toString(mData.get(position).getGrade()));
        float i = mData.get(position).getGrade()*10;
        holder.tv_ProgressBar.setProgress((int)i);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_NameGrade, tv_Grade;
        private ProgressBar tv_ProgressBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_NameGrade = (TextView) itemView.findViewById(R.id.txt7);
            tv_Grade = (TextView) itemView.findViewById(R.id.txt9);
            tv_ProgressBar = (ProgressBar) itemView.findViewById(R.id.txt8);

        }
    }

}
