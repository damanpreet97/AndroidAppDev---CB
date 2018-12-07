package com.example.delllatitude.lec6hwspaceapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FragmentDetail extends Fragment{
    public static FragmentDetail newInstance(Data data) {

        Bundle args = new Bundle();
        args.putParcelable("Data", data);
        FragmentDetail fragment = new FragmentDetail();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_detail_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Data currData = getArguments().getParcelable("Data");
        view.findViewById(R.id.ivFragDetail);
        TextView tvName = view.findViewById(R.id.tvNameFragDetail);
        tvName.setText(currData.getName());
        TextView tvDetail = view.findViewById(R.id.tvDetailFragDetail);
        tvDetail.setText(currData.getData());
        Picasso.get().load(currData.getImageUrl()).into((ImageView) view.findViewById(R.id.ivFragDetail));
    }
}
