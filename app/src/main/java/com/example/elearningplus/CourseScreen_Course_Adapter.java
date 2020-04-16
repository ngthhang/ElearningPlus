package com.example.elearningplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

public class CourseScreen_Course_Adapter extends RecyclerView.Adapter<CourseScreen_Course_Adapter.MyViewHolder> {

    Context mContext;
    List<CourseScreen_Course> mData;

    private OnNoteListener mOnNoteListener;
    int[] array_colors = null;

    public CourseScreen_Course_Adapter(Context mContext, List<CourseScreen_Course> mData, OnNoteListener onNoteListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.mOnNoteListener = onNoteListener;
        this.array_colors =mContext.getResources().getIntArray(R.array.color_array);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.coursescreen_course_layout, parent, false);
        MyViewHolder vHolder = new MyViewHolder(view, mOnNoteListener, array_colors);
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


        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener, int[] array_colors) {
            super(itemView);

            tv_Chapter = (TextView) itemView.findViewById(R.id.txt1);
            tv_Intro = (TextView) itemView.findViewById(R.id.txt2);
            LinearLayout linearLayout = itemView.findViewById( R.id.course_lesson_item );

            //random color
            int random;
            do{
                random = new Random().nextInt(array_colors.length);
            } //handle select background white
            while (random==14 || random== 15);
            linearLayout.setBackgroundColor( array_colors[random] );

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
