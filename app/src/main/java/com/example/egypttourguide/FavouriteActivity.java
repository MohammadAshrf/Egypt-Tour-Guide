package com.example.egypttourguide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FavouriteActivity extends AppCompatActivity {
    ListView lv;
    FavouriteDAO favouriteDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_favourite);
        lv = findViewById (R.id.lv);

        favouriteDAO = PlacesDatabase.getInstance (this).favouriteDAO ();
        List<Favourite> favourites = favouriteDAO.selectAll ();

        FavouriteAdapter adapter = new FavouriteAdapter (this, favourites);
        lv.setAdapter (adapter);
    }

    class FavouriteAdapter extends ArrayAdapter<Favourite> {


        public FavouriteAdapter(@NonNull Context context, List<Favourite> favourites) {
            super (context, 0, favourites);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            convertView = getLayoutInflater ().inflate (R.layout.custom_favourite, parent, false);

            ImageView picture = convertView.findViewById (R.id.ivPicture);
            TextView title = convertView.findViewById (R.id.tvTitle);
            /**
             * Open Image on FullScreen
             */
            picture.setOnClickListener (v -> {
                Intent in = new Intent (FavouriteActivity.this, ImageViewerActivity.class);
                in.putExtra ("url", (getItem (position).picture));
                startActivity (in);
            });

            Picasso.get ().load (getItem (position).picture).placeholder (R.drawable.placeholder).into (picture);

            title.setText (getItem (position).title);
            return convertView;
        }
    }
}