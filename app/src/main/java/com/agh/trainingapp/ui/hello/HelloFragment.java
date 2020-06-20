package com.agh.trainingapp.ui.hello;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.agh.trainingapp.R;

public class HelloFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_hello, container, false);

//        ImageView imageLogo = view.findViewById(R.id.imageLogo);
//        int imageResource = getResources().getIdentifier("@drawable/logo",null, getActivity().getPackageName());
//        imageLogo.setImageResource(imageResource);
        return view;
    }
}