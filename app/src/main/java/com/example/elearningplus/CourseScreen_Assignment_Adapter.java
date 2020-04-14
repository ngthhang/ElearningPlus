package com.example.elearningplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseScreen_Assignment_Adapter extends RecyclerView.Adapter<CourseScreen_Assignment_Adapter.MyViewHodler> {
    
    Context mContext;
    List<CourseScreen_Assignment> mData;
    private OnNoteListener onNoteListener;
    
    public CourseScreen_Assignment_Adapter(Context mContext, List<CourseScreen_Assignment> mData, OnNoteListener onNoteListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.coursescreen_assignment_layout, parent, false);
        MyViewHodler vHolder = new MyViewHodler(view, onNoteListener);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {

        holder.tv_nLab.setText(mData.get(position).getnLab());
        holder.tv_nText.setText(mData.get(position).getnText());
        holder.tv_nDay.setText(mData.get(position).getnDay());
        holder.tv_nTime.setText(mData.get(position).getnTime());

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }
    
    public static class MyViewHodler extends RecyclerView.ViewHolder implements View.OnClickListener{
        
        private TextView tv_nLab, tv_nText, tv_nDay, tv_nTime;
        OnNoteListener onNoteListener;

        public MyViewHodler(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            tv_nLab = (TextView) itemView.findViewById(R.id.txt3);
            tv_nText = (TextView) itemView.findViewById(R.id.txt4);
            tv_nDay = (TextView) itemView.findViewById(R.id.txt5);
            tv_nTime = (TextView) itemView.findViewById(R.id.txt6);

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
