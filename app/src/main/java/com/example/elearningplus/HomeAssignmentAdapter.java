package com.example.elearningplus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
    public Object instantiateItem(@NonNull ViewGroup container,final int position) {
        layoutInflater = LayoutInflater.from( context );
        View convertView  = layoutInflater.inflate( R.layout.home_assignment_item,container,false );

        TextView dueDate,courseAssignment,courseName;
        RelativeLayout relativeLayout;
        CardView lateTag;

        courseName = convertView.findViewById( R.id.course_name );
        dueDate = convertView.findViewById( R.id.course_due );
        courseAssignment = convertView.findViewById( R.id.course_assignment );
        relativeLayout = convertView.findViewById( R.id.layout_card );
        lateTag = convertView.findViewById( R.id.late_tag );

        dueDate.setText( "Due:\t"+homeAssignmentList.get( position ).getDueDate() );
        courseAssignment.setText( homeAssignmentList.get( position ).getCourseAssignment() );
        courseName.setText( homeAssignmentList.get( position ).getCourseName() );
        String isLate = homeAssignmentList.get( position ).getLate();
        if(isLate.equals( "true" )){
            lateTag.setVisibility( View.VISIBLE );
        }else{
            lateTag.setVisibility( View.GONE );
        }
        //random color
        int random;
        do{
            random = new Random().nextInt(array_colors.length);
        } //handle select background white
        while (random==14 || random== 15);
        relativeLayout.setBackgroundColor( array_colors[random] );

        convertView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeAssignment homeAssignment = homeAssignmentList.get( position );
                if(homeAssignment.getIsOpen().equals( "false" )){
                    Toast.makeText( context, "Assignment này đã bị khoá không thể nộp", Toast.LENGTH_SHORT ).show();
                }else {
                    Intent i = new Intent( context, AssignmentViewScreen.class );
                    i.putExtra( "COURSE_KEY", homeAssignment.getCourseKey() );
                    i.putExtra( "IS_LATE", homeAssignment.getLate() );
                    i.putExtra( "ASSIGNMENT_ID", position );
                    context.startActivity(i);
                }

            }
        } );

        container.addView( convertView,0 );
        return convertView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView( (View)object );
    }


}
