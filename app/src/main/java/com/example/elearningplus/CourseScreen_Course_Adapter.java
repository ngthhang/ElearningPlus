package com.example.elearningplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseScreen_Course_Adapter extends RecyclerView.Adapter<CourseScreen_Course_Adapter.MyViewHolder> {

    Context mContext;
    List<CourseScreen_Course> mData;
    private OnNoteListener mOnNoteListener;

    public CourseScreen_Course_Adapter(Context mContext, List<CourseScreen_Course> mData, OnNoteListener onNoteListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.coursescreen_course_layout, parent, false);
        MyViewHolder vHolder = new MyViewHolder(view, mOnNoteListener);

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

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tv_Chapter, tv_Intro;
        OnNoteListener onNoteListener;


        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            tv_Chapter = (TextView) itemView.findViewById(R.id.txt1);
            tv_Intro = (TextView) itemView.findViewById(R.id.txt2);

            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }

}
