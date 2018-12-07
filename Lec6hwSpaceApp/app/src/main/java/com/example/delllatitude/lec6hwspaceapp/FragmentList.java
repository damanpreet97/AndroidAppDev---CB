package com.example.delllatitude.lec6hwspaceapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FragmentList extends Fragment {
    ArrayList<Data> dataArrayList;
    public static FragmentList newInstance(ArrayList<Data> dataArrayList) {

        Bundle args = new Bundle();
        args.putParcelableArrayList("ArrayList", dataArrayList);
        FragmentList fragment = new FragmentList();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_list_celestial, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView rv = view.findViewById(R.id.rvPlanetsFrag);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        Communicator communicator = (Communicator)getActivity();
        rv.setAdapter(new DataAdapter(getContext(), getArguments().<Data>getParcelableArrayList("ArrayList"), communicator));
    }
}
