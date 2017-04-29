package com.example.hitesharora.smartcampus.News;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hitesharora.smartcampus.BaseActivity;
import com.example.hitesharora.smartcampus.R;
import com.example.hitesharora.smartcampus.WebViewActivity;

public class NewsDetailActivity extends BaseActivity {

    public NewsDetailsFragment mFragment;
    NewsItem currentItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        currentItem = (NewsItem) getIntent().getSerializableExtra("item");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(currentItem.Title);
        loadFragment();
    }

    public void loadFragment() {
        mFragment = NewsDetailsFragment.newInstance(currentItem);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, mFragment)
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_open_url:
                Intent browserIntent = new Intent(NewsDetailActivity.this, WebViewActivity.class);
                browserIntent.putExtra("URL", currentItem.Link);
                browserIntent.putExtra("title", currentItem.Title);
                startActivity(browserIntent);
                break;
            case android.R.id.home:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


}
