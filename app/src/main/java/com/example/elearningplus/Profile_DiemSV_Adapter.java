package com.example.elearningplus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;

public class Profile_DiemSV_Adapter extends ArrayAdapter<Profile_DiemSV> {
    List<Profile_DiemSV> item_list;
    Context context;

    public Profile_DiemSV_Adapter(Context context, List<Profile_DiemSV> item_list){
        super(context,R.layout.profile_diem_sv, item_list);
        this.context = context;
        this.item_list = item_list;
    }

    static class ViewHoler{
        public TextView textView;
        public TextView textView2;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHoler holder;
        holder = new ViewHoler();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView= inflater.inflate(R.layout.profile_diem_sv, parent, false);

        holder.textView= convertView.findViewById(R.id.tvTenMH);
        holder.textView2= convertView.findViewById(R.id.tvDiemMH);

        holder.textView.setText(item_list.get(position).getTenMH());
        holder.textView2.setText(Float.toString(item_list.get(position).getDiemMH()));

        return convertView;
    }


}
