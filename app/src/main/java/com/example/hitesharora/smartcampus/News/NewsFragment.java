package com.example.hitesharora.smartcampus.News;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
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
import com.example.hitesharora.smartcampus.database.SmartCampusDataHelper;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


@SuppressLint("InflateParams")
public class NewsFragment extends BaseFragment implements NewsAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private ArrayList<NewsItem> mNewsItemsList;
    private NewsAdapter mNewsAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ProgressBar mProgressBar;


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
        mProgressBar = (ProgressBar) getView().findViewById(R.id.progress_bar);

        final RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.list_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNewsAdapter = new NewsAdapter(mNewsItemsList, R.layout.item_news_feed, getActivity(),this);
        recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(getActivity()));
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mNewsAdapter);

        mNewsItemsList = SmartCampusDataHelper.getInstance(getActivity()).getNewsFeed().newsItems;
        if (mNewsItemsList != null) {
            mNewsAdapter.notifyDataChanged(mNewsItemsList);
            mProgressBar.setVisibility(View.GONE);
        }else{
            mProgressBar.setVisibility(View.VISIBLE);
            refreshItems();
        }

        mSwipeRefreshLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }
    @Override
    public void onItemClick(NewsItem newsItem) {
        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        intent.putExtra("item", newsItem);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        refreshItems();
    }

    void refreshItems() {
        NewsInterface apiService = ApiClient.getClient().create(NewsInterface.class);
        Call<NewsFeed> call = apiService.getNewsFeed();
        call.enqueue(new Callback<NewsFeed>() {
            @Override
            public void onResponse(Call<NewsFeed> call, Response<NewsFeed> response) {
                if (getActivity() != null) {
                    SmartCampusDataHelper.getInstance(getActivity()).insertNewsData(response.body());
                    mNewsItemsList = SmartCampusDataHelper.getInstance(getActivity()).getNewsFeed().newsItems;
                    if (mNewsItemsList != null)
                        mNewsAdapter.notifyDataChanged(mNewsItemsList);
                    mProgressBar.setVisibility(View.GONE);
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<NewsFeed> call, Throwable t) {
                Log.e(TAG, t.toString());
                if (getActivity() != null) {
                    mProgressBar.setVisibility(View.GONE);
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });

    }
}
