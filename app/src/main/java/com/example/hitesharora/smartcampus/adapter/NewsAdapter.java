package com.example.hitesharora.smartcampus.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hitesharora.smartcampus.News.NewsItem;
import com.example.hitesharora.smartcampus.R;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
 
    private ArrayList<NewsItem> news;
    private int rowLayout;
    private Context context;
    private OnItemClickListener mOnItemClickListener;
 
 
    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle;
        TextView date;
        TextView newsDescription;
        ImageView  image;
 
 
        public NewsViewHolder(View v) {
            super(v);
            newsTitle = (TextView) v.findViewById(R.id.news_title);
            newsDescription = (TextView) v.findViewById(R.id.description);
            date = (TextView) v.findViewById(R.id.date);
            image = (ImageView) v.findViewById(R.id.news_image);
        }
    }
 
    public NewsAdapter(ArrayList<NewsItem> news, int rowLayout, Context context, OnItemClickListener onItemClickListener) {
        this.news = news;
        this.rowLayout = rowLayout;
        this.context = context;
        this.mOnItemClickListener = onItemClickListener;
    }

    public void notifyDataChanged(ArrayList<NewsItem> news){
        this.news = news;
        notifyDataSetChanged();
    }
 
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new NewsViewHolder(view);
    }
 
 
    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {
        holder.newsTitle.setText(news.get(position).Title);
        holder.date.setText(news.get(position).PublishedDate);
        holder.newsDescription.setText(news.get(position).Description);
        Glide.with(context)
                .load(news.get(position).Thumbnail)
                .override(450, 450)
                .placeholder(R.drawable.asu_logo)
                .crossFade().into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(news.get(position));
            }
        });
    }
 
    @Override
    public int getItemCount() {
        return news.size();
    }

    public interface OnItemClickListener{
        void onItemClick(NewsItem newsItem);
    }
}