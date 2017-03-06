package com.example.hitesharora.smartcampus.News;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.hitesharora.smartcampus.BaseFragment;
import com.example.hitesharora.smartcampus.NetworkApi.ApiClient;
import com.example.hitesharora.smartcampus.R;
import com.example.hitesharora.smartcampus.adapter.NewsAdapter;
import com.example.hitesharora.smartcampus.adapter.VerticalSpaceItemDecoration;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static android.support.v7.recyclerview.R.attr.layoutManager;


@SuppressLint("InflateParams")
public class NewsFragment extends BaseFragment {

    public ArrayList<NewsItem> mNewsItemsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mNewsItemsList = new ArrayList<>();

        final ProgressBar progressBar = (ProgressBar) getView().findViewById(R.id.progress_bar);


        final RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.list_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final NewsAdapter newsAdapter = new NewsAdapter(mNewsItemsList, R.layout.item_news_feed, getActivity());
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(getActivity()));
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(newsAdapter);



        NewsInterface apiService = ApiClient.getClient().create(NewsInterface.class);
        Call<NewsFeed> call = apiService.getNewsFeed();
        call.enqueue(new Callback<NewsFeed>() {
            @Override
            public void onResponse(Call<NewsFeed> call, Response<NewsFeed> response) {
                mNewsItemsList = response.body().newsItems;

                if (mNewsItemsList != null)
                    newsAdapter.notifyDataChanged(mNewsItemsList);

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<NewsFeed> call, Throwable t) {
                Log.e(TAG, t.toString());
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
