package com.khos.tanyadokter.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.khos.tanyadokter.MainActivity;
import com.khos.tanyadokter.R;
import com.khos.tanyadokter.Welcome;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_welcome extends Fragment implements View.OnClickListener {

    Button button;
    View view;
    public Fragment_welcome() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_fragment_welcome, container, false);
        view = inflater.inflate(R.layout.fragment_fragment_welcome, container, false);
        button = view.findViewById(R.id.button);
        button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

}
