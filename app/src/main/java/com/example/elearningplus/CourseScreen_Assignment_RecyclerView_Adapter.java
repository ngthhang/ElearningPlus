package com.example.elearningplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseScreen_Assignment_RecyclerView_Adapter extends RecyclerView.Adapter<CourseScreen_Assignment_RecyclerView_Adapter.MyViewHodler> {
    
    Context mContext;
    List<CourseScreen_Assignment> mData;
    
    public CourseScreen_Assignment_RecyclerView_Adapter(Context mContext, List<CourseScreen_Assignment> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.assignment_layout, parent, false);
        MyViewHodler vHolder = new MyViewHodler(view);
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
    
    public static class MyViewHodler extends RecyclerView.ViewHolder{
        
        private TextView tv_nLab;
        private TextView tv_nText;
        private TextView tv_nDay;
        private TextView tv_nTime;

        public MyViewHodler(@NonNull View itemView) {
            super(itemView);

            tv_nLab = (TextView) itemView.findViewById(R.id.txt3);
            tv_nText = (TextView) itemView.findViewById(R.id.txt4);
            tv_nDay = (TextView) itemView.findViewById(R.id.txt5);
            tv_nTime = (TextView) itemView.findViewById(R.id.txt6);
        }
    }
}
