package com.example.elearningplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;
import java.util.Random;

public class HomeAssignmentAdapter extends PagerAdapter {

    private List<HomeAssignment> homeAssignmentList;
    public LayoutInflater layoutInflater;
    public Context context;
    int[] array_colors;

    public HomeAssignmentAdapter(List<HomeAssignment> homeAssignmentList, Context context) {
        this.homeAssignmentList = homeAssignmentList;
        this.context = context;
        this.array_colors = context.getResources().getIntArray( R.array.color_array );
    }

    @Override
    public int getCount() {
        return homeAssignmentList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals( object );
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from( context );
        View convertView  = layoutInflater.inflate( R.layout.home_course_item,container,false );

        TextView courseName,courseAssignment;
        RelativeLayout relativeLayout;


        courseName = convertView.findViewById( R.id.course_name );
        courseAssignment = convertView.findViewById( R.id.course_id );
        relativeLayout = convertView.findViewById( R.id.layout_card );

        courseName.setText( homeAssignmentList.get( position ).getCourseName() );
        courseAssignment.setText( homeAssignmentList.get( position ).getCourseAssignment() );


        //random color
        int random;
        do{
            random = new Random().nextInt(array_colors.length);
        } //handle select background white
        while (random==14 || random== 15);
        relativeLayout.setBackgroundColor( array_colors[random] );

        container.addView( convertView,0 );
        return convertView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView( (View)object );
    }


}
