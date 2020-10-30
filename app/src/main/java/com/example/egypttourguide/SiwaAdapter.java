package com.example.egypttourguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SiwaAdapter extends ArrayAdapter<Siwa> {


    public SiwaAdapter(@NonNull Context context, List<Siwa> places) {
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

