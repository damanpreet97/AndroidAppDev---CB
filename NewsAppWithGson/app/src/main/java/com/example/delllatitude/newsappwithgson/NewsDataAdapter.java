package com.example.delllatitude.newsappwithgson;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsDataAdapter extends RecyclerView.Adapter<NewsDataAdapter.ViewHolder> {

    ArrayList<NewsData> newsDataArrayList;
    Context ctx;

    public NewsDataAdapter(ArrayList<NewsData> newsDataArrayList, Context ctx) {
        this.newsDataArrayList = newsDataArrayList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.list_row_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsData currNewsData = newsDataArrayList.get(position);
        holder.tvTitle.setText(currNewsData.getTitle());
        holder.tvDetail.setText(currNewsData.getUrl());
        Picasso.get().load(currNewsData.getUrlToImage()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return newsDataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView tvTitle, tvDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.ivList);
            tvTitle = itemView.findViewById(R.id.titleTvList);
            tvDetail = itemView.findViewById(R.id.despLinkTvList);
        }
    }
}
