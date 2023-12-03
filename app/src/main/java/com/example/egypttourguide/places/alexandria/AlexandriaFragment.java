package com.example.egypttourguide.places.alexandria;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.egypttourguide.places.PlacesDatabase;
import com.example.egypttourguide.R;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;

import java.util.List;


public class AlexandriaFragment extends Fragment {
    AlexandriaDAO alexandriaDAO;
    View view;
    WebView wv;
    ListView lv;
    ProgressBar spinKit;

    public AlexandriaFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //web view
        view = inflater.inflate (R.layout.place_list, container, false);

        wv = view.findViewById (R.id.webView);

        wv.getSettings ().setJavaScriptEnabled (true);
        wv.getSettings ().setDomStorageEnabled (true);
        wv.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        wv.getSettings ().setBuiltInZoomControls (true);
        wv.setWebViewClient (new MyWebViewClient ());
        wv.loadUrl ("https://www.youtube.com/embed/XPYxYp4UYdg?si=awFjkb0Jc5H6QKnI&amp;start=10s");
/**
 * progress bar
 */
        spinKit = view.findViewById (R.id.spinKit);
        Sprite doubleBounce = new DoubleBounce ();
        spinKit.setIndeterminateDrawable (doubleBounce);


        alexandriaDAO = PlacesDatabase.getInstance (getActivity ()).alexandriaDAO ();
        List<Alexandria> places = alexandriaDAO.selectAlexandria ();

        AlexandriaAdapter adapter = new AlexandriaAdapter ((requireActivity()), places);
        lv = view.findViewById (R.id.lv);
        lv.setAdapter (adapter);

        setListener ();
        return view;
    }

    private void setListener() {
        lv.setOnItemClickListener ((AdapterView<?> adapterView, View view, int i, long l) -> {

            Intent in = new Intent (getActivity (), AlexandriaDetailActivity.class);
            in.putExtra ("titles", i);
            startActivity (in);
        });
    }


    @Override
    public void onPause() {
        super.onPause ();
        wv.onPause ();
        wv.pauseTimers ();
    }

    @Override
    public void onResume() {
        super.onResume ();
        wv.resumeTimers ();
        wv.onResume ();
    }


    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            spinKit.setVisibility (View.GONE);
        }
    }

}
