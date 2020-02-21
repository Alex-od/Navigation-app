package com.dan.navigationapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.dan.navigationapp.R;


public class WebFragment extends Fragment {
private  WebView web_view;
private String url = null;

@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        url = this.getArguments().getString("url");
        return inflater.inflate(R.layout.web_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        web_view = getActivity().findViewById(R.id.web_view);
        web_view.loadUrl(url);
    }
}