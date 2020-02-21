package com.dan.navigationapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dan.navigationapp.R;


public class TextFragment extends Fragment {
    private TextView text_view;
    private String text = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         text = this.getArguments().getString("text");
        return inflater.inflate(R.layout.text_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        text_view = getActivity().findViewById(R.id.text_view);
        text_view.setText(text);
    }
}