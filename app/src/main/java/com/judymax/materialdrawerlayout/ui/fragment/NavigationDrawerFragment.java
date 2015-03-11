package com.judymax.materialdrawerlayout.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.judymax.materialdrawerlayout.R;
import com.judymax.materialdrawerlayout.ui.adapter.DrawerListItemAdapter;

import java.util.ArrayList;

/**
 * Created by Maxiee on 2015/3/9.
 */
public class NavigationDrawerFragment extends Fragment {

    private NavigationDrawerCallbacks mCallbacks;
    private ListView mDrawerListView;
    private DrawerListItemAdapter mAdapter;
    private int mCurrentSelectedPosition = 0;

    public NavigationDrawerFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectItem(mCurrentSelectedPosition);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_drawer,container,false);

        mDrawerListView = (ListView) v.findViewById(R.id.drawer_list);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

        ArrayList<DrawerListItemAdapter.Item> data = new ArrayList<>();

        data.add(new DrawerListItemAdapter.Item(
                "Item1",
                R.drawable.ic_launcher
        ));

        data.add(new DrawerListItemAdapter.Item(
                "Item2",
                R.drawable.ic_launcher
        ));

        data.add(new DrawerListItemAdapter.Item(
                "Item3",
                R.drawable.ic_launcher
        ));

        data.add(new DrawerListItemAdapter.Item(
                "Item4",
                R.drawable.ic_launcher
        ));

        mAdapter = new DrawerListItemAdapter(getActivity().getApplicationContext(),
                data, R.color.drawer_list_item_normal, R.color.drawer_list_item_highlight);

        mDrawerListView.setAdapter(mAdapter);
        mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);

        return v;
    }

    public void selectItem(int position){
        mCurrentSelectedPosition = position;
        if (mDrawerListView != null) {
            mDrawerListView.setItemChecked(position, true);
        }
        if (mCallbacks != null) {
            if (mCallbacks.onNavigationDrawerItemSelected(position)) {
                try {
                    mAdapter.setSelectedPosition(position);
                } catch (Exception e) {}
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    private ActionBar getActionBar() {
        return ((ActionBarActivity) getActivity()).getSupportActionBar();
    }

    public static interface NavigationDrawerCallbacks {
        boolean onNavigationDrawerItemSelected(int position);
    }
}
