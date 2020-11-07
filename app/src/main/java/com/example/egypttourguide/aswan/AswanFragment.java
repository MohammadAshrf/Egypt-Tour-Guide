package com.example.egypttourguide.aswan;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.egypttourguide.PlacesDatabase;
import com.example.egypttourguide.R;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;

import java.util.List;
import java.util.Objects;

public class AswanFragment extends Fragment {
    AswanDAO aswanDao;
    View rootView;
    WebView wv;
    ListView lv;
    ProgressBar spinKit;

    public AswanFragment() {
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
        wv.setWebViewClient (new AswanFragment.MyWebViewClient ());
        wv.loadUrl ("https://www.youtube.com/embed/dMm57bBgeg8");

        /**
         * progress bar
         */
        spinKit = rootView.findViewById (R.id.spinKit);
        Sprite doubleBounce = new DoubleBounce ();
        spinKit.setIndeterminateDrawable (doubleBounce);

        aswanDao = PlacesDatabase.getInstance (getActivity ()).aswanDAO ();
        List<Aswan> places = aswanDao.selectAswan ();

        AswanAdapter adapter = new AswanAdapter ((Objects.requireNonNull (getActivity ())), places);
        lv = rootView.findViewById (R.id.lv);

        lv.setAdapter (adapter);
        setListener ();
        return rootView;
    }

    private void setListener() {
        lv.setOnItemClickListener ((AdapterView<?> adapterView, View view, int i, long l) -> {

            Intent in = new Intent (getActivity (), AswanDetailActivity.class);
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
