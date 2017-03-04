package com.example.hitesharora.smartcampus.News;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.hitesharora.smartcampus.R;
import com.example.hitesharora.smartcampus.utilities.NetworkUtils;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.Menu;
import android.content.Intent;
import android.widget.ProgressBar;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class NewsList extends AppCompatActivity {

    private ListView newsListView;
    private ArrayList arrayList;
    private ProgressBar mLoadingIndicator;

    private String result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        setTitle("NEWS");

        newsListView = (ListView) findViewById(R.id.news_list);

        List<String> newsArrayList = new ArrayList<String>();
        newsArrayList.add("Foo");
        newsArrayList.add("bar");



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, newsArrayList);
        newsListView.setAdapter(arrayAdapter);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Intent intent = new Intent(getApplicationContext(),NewsDetailView.class);
                String message = "abc";
                intent.putExtra("Value",message);
                startActivity(intent);
                */
                onRequest();
            }
        });

    }

    public void onRequest(){
        String location = "94043,USA";
        new FetchNewsList().execute(location);
    };

    public class FetchNewsList extends AsyncTask<String, Void, String[]> {

        // COMPLETED (6) Override the doInBackground method to perform your network requests
        @Override
        protected String[] doInBackground(String... params) {

            /* If there's no zip code, there's nothing to look up. */
            if (params.length == 0) {
                return null;
            }

            String location = params[0];
            URL weatherRequestUrl = NetworkUtils.buildUrl(location);

            try {
                String jsonWeatherResponse = NetworkUtils
                        .getResponseFromHttpUrl(weatherRequestUrl);

                System.out.print("Response" + jsonWeatherResponse);
                //String[] simpleJsonWeatherData = OpenWeatherJsonUtils
                //      .getSimpleWeatherStringsFromJson(MainActivity.this, jsonWeatherResponse);
                String[] simpleJsonWeatherData = new String[0];

                return simpleJsonWeatherData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] weatherData) {
            // COMPLETED (19) As soon as the data is finished loading, hide the loading indicator
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (weatherData != null) {
                // COMPLETED (11) If the weather data was not null, make sure the data view is visible
                //showWeatherDataView();
                /*
                 * Iterate through the array and append the Strings to the TextView. The reason why we add
                 * the "\n\n\n" after the String is to give visual separation between each String in the
                 * TextView. Later, we'll learn about a better way to display lists of data.
                 */
                //for (String weatherString : weatherData) {
                  //  result.append((weatherString) + "\n\n\n");
                //}
            } else {
                // COMPLETED (10) If the weather data was null, show the error message
                showErrorMessage();
            }
        }

    }

    private void showErrorMessage() {
        System.out.print("Error");
    }
}
