package com.judymax.materialdrawerlayout.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.judymax.materialdrawerlayout.R;

/**
 * Created by Maxiee on 2015/3/11.
 */
public class Fragment1 extends Fragment {

    public Fragment1() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_1, container, false);


        return rootView;
    }
}
