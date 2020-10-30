package com.example.egypttourguide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;


public class AlexandriaFragment extends Fragment {
    AlexandriaDAO alexandriaDAO;
    View view;
    WebView wv;

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
        wv.getSettings ().setAppCacheEnabled (true);
        wv.getSettings ().setBuiltInZoomControls (true);
        wv.setWebViewClient (new MyWebViewClient ());
        wv.loadUrl ("https://www.facebook.com/video/embed?video_id=1706940892719996");

        alexandriaDAO = PlacesDatabase.getInstance (getActivity ()).alexandriaDAO ();
        List<Alexandria> places = alexandriaDAO.selectAlexandria ();

        AlexandriaAdapter adapter = new AlexandriaAdapter ((getActivity ()), places);
        ListView lv = view.findViewById (R.id.lv);
        lv.setAdapter (adapter);
//        onClick ();

        return view;
    }
//I will modify it.
//    private void onClick() {
//        lv.setOnItemClickListener ((parent, view, position, id) ->
//                Toast.makeText (getActivity (), "id is " + lv.getSelectedItemPosition () + "\n" + position + "\n" + id, Toast.LENGTH_LONG).show ());
//    }

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

    @Override
    public void onDestroy() {
        wv.destroy ();
        wv = null;
        super.onDestroy ();
    }

    private static class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }
}