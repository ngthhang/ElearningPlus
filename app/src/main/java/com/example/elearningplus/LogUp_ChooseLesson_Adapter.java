package com.example.elearningplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Random;

public class LogUp_ChooseLesson_Adapter extends ArrayAdapter<LogUp_ChooseLesson_Data> {

    public Context mContext;
    public int layout;
    private List<LogUp_ChooseLesson_Data> mData;
    int[] array_colors;

    public LogUp_ChooseLesson_Adapter(Context context, int resource, List<LogUp_ChooseLesson_Data> objects){
        super(context, resource, objects);

        this.mContext = context;
        this.layout = resource;
        this.mData = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(layout, parent, false);

        TextView title = view.findViewById(R.id.txtName);
        CheckBox enable = view.findViewById(R.id.chBoxSelect);
        LinearLayout linearLayout = view.findViewById(R.id.choose_lesson_item);

        LogUp_ChooseLesson_Data data = mData.get(position);
        title.setText(data.getName());
        enable.setChecked(data.isChecked());

        //random color
        int random;
        do{
            random = new Random().nextInt(array_colors.length);
        } //handle select background white
        while (random==14 || random== 15);
        linearLayout.setBackgroundColor( array_colors[random] );

        return view;
    }
}
