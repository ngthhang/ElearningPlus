package com.example.elearningplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;
import 	android.content.res.Resources;
import java.util.List;
import java.util.Random;

public class HomeCourseAdapter extends PagerAdapter {

    private List<HomeCourse> homeCourseList;
    private Context context;
    private LayoutInflater layoutInflater;
    int[] array_colors = null;


    public HomeCourseAdapter(List<HomeCourse> homeCourseList, Context context) {
        this.homeCourseList = homeCourseList;
        this.context = context;
        this.array_colors =context.getResources().getIntArray(R.array.color_array);
    }

    @Override
    public int getCount() {
        return homeCourseList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals( object );
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from( context );
        View convertView = layoutInflater.inflate( R.layout.home_course_item, container, false );

        TextView courseName, courseId;
        RelativeLayout relativeLayout;

        courseName = convertView.findViewById( R.id.course_name );
        courseId = convertView.findViewById( R.id.course_id );
        relativeLayout = convertView.findViewById( R.id.layout_card );

        courseName.setText( homeCourseList.get( position ).getName() );
        courseId.setText( homeCourseList.get( position ).getCourseId() );

        //random color
        int random;
        do{
            random = new Random().nextInt(array_colors.length);
        }while (random == 14 || random == 15);
        relativeLayout.setBackgroundColor( array_colors[random]);

        container.addView( convertView,0 );
        return convertView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView( (View)object );
    }
}
