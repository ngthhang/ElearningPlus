package com.example.elearningplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

public class CourseScreen_Assignment_Adapter extends RecyclerView.Adapter<CourseScreen_Assignment_Adapter.MyViewHodler> {
    
    Context mContext;
    List<CourseScreen_Assignment> mData;
    private OnNoteListener onNoteListener;
    int[] array_colors = null;

    
    public CourseScreen_Assignment_Adapter(Context mContext, List<CourseScreen_Assignment> mData, OnNoteListener onNoteListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.onNoteListener = onNoteListener;
        this.array_colors =mContext.getResources().getIntArray(R.array.color_array);
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.coursescreen_assignment_layout, parent, false);
        MyViewHodler vHolder = new MyViewHodler(view, onNoteListener,array_colors);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        String asssignmentDueText = mData.get(position).getDue();
        String isLate = mData.get( position ).getLate();
        if (isLate.equals( "true" )) {
            holder.lateTag.setVisibility( View.VISIBLE );
        }
        else{
            holder.lateTag.setVisibility( View.GONE );
        }
        holder.tv_nLab.setText(mData.get(position).getName());
        holder.tv_nText.setText(asssignmentDueText);
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }
    
    public static class MyViewHodler extends RecyclerView.ViewHolder implements View.OnClickListener{
        
        private TextView tv_nLab, tv_nText;
        OnNoteListener onNoteListener;
        LinearLayout linearLayout;
        CardView lateTag;

        public MyViewHodler(@NonNull View itemView, OnNoteListener onNoteListener,int[] array_colors) {
            super(itemView);

            tv_nLab = (TextView) itemView.findViewById(R.id.txt3);
            tv_nText = (TextView) itemView.findViewById(R.id.txt4);
            lateTag = (CardView) itemView.findViewById( R.id.late_tag );
            linearLayout = (LinearLayout) itemView.findViewById( R.id.course_assignment_item );
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
