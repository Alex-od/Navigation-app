package com.dan.navigationapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dan.navigationapp.R;
import com.squareup.picasso.Picasso;

public class ImageFragment extends Fragment {
    private ImageView imageView;
    String img_path = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        img_path = this.getArguments().getString("img_path");
        return inflater.inflate(R.layout.image_fragment, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        imageView = getActivity().findViewById(R.id.image_view);
        Picasso.get().load(img_path).error(android.R.drawable.stat_sys_warning).into(imageView);
    }
}