package com.example.egypttourguide.luxor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.egypttourguide.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LuxorAdapter extends ArrayAdapter<Luxor> {


    public LuxorAdapter(@NonNull Context context, List<Luxor> places) {
        super (context, 0, places);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View lv, @NonNull ViewGroup parent) {

        lv = LayoutInflater.from (getContext ()).inflate (R.layout.custom_place, parent, false);

        ImageView cmPic = lv.findViewById (R.id.cmPic);
        TextView cmTitle = lv.findViewById (R.id.cmTitle);

        Picasso.get ().load (getItem (position).picture).placeholder (R.drawable.placeholder).into (cmPic);
        cmTitle.setText (getItem (position).title);

        return lv;
    }

}

