package com.judymax.materialdrawerlayout.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.judymax.materialdrawerlayout.R;

/**
 * Created by Maxiee on 2015/3/9.
 */
public class NavigationDrawerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_drawer,container,false);
        return v;
    }
}
