package com.example.egypttourguide.cairo;

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

public class CairoAdapter extends ArrayAdapter<Cairo> {

    public CairoAdapter(@NonNull Context context, List<Cairo> places) {
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




