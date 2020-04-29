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
        this.array_colors = context.getResources().getIntArray(R.array.color_array);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from( mContext ).inflate( R.layout.logup_chooselesson_items, parent, false );
        }
        TextView title = convertView.findViewById(R.id.txtName);
        CheckBox enable = convertView.findViewById(R.id.chBoxSelect);
        LinearLayout linearLayout = convertView.findViewById(R.id.choose_lesson_item);

        final LogUp_ChooseLesson_Data data = mData.get(position);
        title.setText(data.getName());
        enable.setChecked(data.isChecked());

        enable.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean previousState = data.isChecked();
                data.setChecked( !previousState );
            }
        } );

        //random color
        int random;
        do{
            random = new Random().nextInt(array_colors.length);
        } //handle select background white
        while (random==14 || random== 15);
        linearLayout.setBackgroundColor( array_colors[random] );

        return convertView;
    }
}
