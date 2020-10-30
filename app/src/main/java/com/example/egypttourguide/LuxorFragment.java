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

public class LuxorFragment extends Fragment {
    LuxorDAO luxorDao;
    View rootView;
    WebView wv;

    public LuxorFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate (R.layout.place_list, container, false);
        //web view
        wv = rootView.findViewById (R.id.webView);
        wv.getSettings ().setJavaScriptEnabled (true);
        wv.getSettings ().setDomStorageEnabled (true);
        wv.getSettings ().setAppCacheEnabled (true);
        wv.getSettings ().setBuiltInZoomControls (true);
        wv.setWebViewClient (new LuxorFragment.MyWebViewClient ());
        wv.loadUrl ("https://www.youtube.com/embed/dMm57bBgeg8");

        luxorDao = PlacesDatabase.getInstance (getActivity ()).luxorDAO ();
        List<Luxor> places = luxorDao.selectLuxor ();

        LuxorAdapter adapter = new LuxorAdapter ((getActivity ()), places);
        ListView lv = rootView.findViewById (R.id.lv);

        lv.setAdapter (adapter);

        return rootView;
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
