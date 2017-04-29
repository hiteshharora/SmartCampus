package com.example.hitesharora.smartcampus.Streaming;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hitesharora.smartcampus.BaseActivity;
import com.example.hitesharora.smartcampus.BaseFragment;
import com.example.hitesharora.smartcampus.NetworkApi.ApiClient;
import com.example.hitesharora.smartcampus.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;


@SuppressLint("InflateParams")
public class StreamingFragment extends BaseFragment implements View.OnClickListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener {


    String stationName = null;
    public MediaPlayer mPlayer;
    Boolean isPlayerReady = false;
    ImageView logo;
    TextView status;
    Button control;
    private TextView hangoutTvOne;
    private TextView hangoutTvTwo;
    private TextView hangoutTvThree;

    private ObjectAnimator waveOneAnimator;
    private ObjectAnimator waveTwoAnimator;
    private ObjectAnimator waveThreeAnimator;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_streaming, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_kasu:
                //refreshItems();
                break;
            case R.id.action_wolf:
                //refreshItems();
                break;

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_streaming, null);
        return view;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logo = (ImageView) getView().findViewById(R.id.station_logo);
        control = (Button) getView().findViewById(R.id.play_pause_button);
        status = (TextView) getView().findViewById(R.id.buffer_status);
        hangoutTvOne = (TextView) getView().findViewById(R.id.hangoutTvOne);
        hangoutTvTwo = (TextView) getView().findViewById(R.id.hangoutTvTwo);
        hangoutTvThree = (TextView) getView().findViewById(R.id.hangoutTvThree);
        hangoutTvOne.setVisibility(View.GONE);
        hangoutTvTwo.setVisibility(View.GONE);
        hangoutTvThree.setVisibility(View.GONE);
        waveAnimation();
        status.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));

        control.setOnClickListener(this);
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
            mPlayer.setOnCompletionListener(this);
            mPlayer.setOnErrorListener(this);
            mPlayer.setOnInfoListener(this);
            mPlayer.setOnPreparedListener(this);
            mPlayer.setScreenOnWhilePlaying(true);
        }

        refreshItems();
    }


    void refreshItems() {
        StreamingInterface apiService = ApiClient.getClient().create(StreamingInterface.class);
        Call<StreamingItem> call = apiService.getStreaming();
        call.enqueue(new Callback<StreamingItem>() {
            @Override
            public void onResponse(Call<StreamingItem> call, Response<StreamingItem> response) {
                if (getActivity() != null && response != null && response.body() != null) {
                    new RetrieveStreamingUrl().execute(response.body().url);
                    Glide.with(getActivity())
                            .load(response.body().backgroundUrl)
                            .placeholder(R.drawable.asu_logo);
                    status.setText(response.body().name);
                }
            }

            @Override
            public void onFailure(Call<StreamingItem> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

    }

    public void waveAnimation() {
        PropertyValuesHolder tvOne_Y = PropertyValuesHolder.ofFloat(hangoutTvOne.TRANSLATION_Y, -40.0f);
        PropertyValuesHolder tvOne_X = PropertyValuesHolder.ofFloat(hangoutTvOne.TRANSLATION_X, 0);
        waveOneAnimator = ObjectAnimator.ofPropertyValuesHolder(hangoutTvOne, tvOne_X, tvOne_Y);
        waveOneAnimator.setRepeatCount(-1);
        waveOneAnimator.setRepeatMode(ValueAnimator.REVERSE);
        waveOneAnimator.setDuration(400);

        PropertyValuesHolder tvTwo_Y = PropertyValuesHolder.ofFloat(hangoutTvTwo.TRANSLATION_Y, -40.0f);
        PropertyValuesHolder tvTwo_X = PropertyValuesHolder.ofFloat(hangoutTvTwo.TRANSLATION_X, 0);
        waveTwoAnimator = ObjectAnimator.ofPropertyValuesHolder(hangoutTvTwo, tvTwo_X, tvTwo_Y);
        waveTwoAnimator.setRepeatCount(-1);
        waveTwoAnimator.setRepeatMode(ValueAnimator.REVERSE);
        waveTwoAnimator.setDuration(400);
        waveTwoAnimator.setStartDelay(100);

        PropertyValuesHolder tvThree_Y = PropertyValuesHolder.ofFloat(hangoutTvThree.TRANSLATION_Y, -40.0f);
        PropertyValuesHolder tvThree_X = PropertyValuesHolder.ofFloat(hangoutTvThree.TRANSLATION_X, 0);
        waveThreeAnimator = ObjectAnimator.ofPropertyValuesHolder(hangoutTvThree, tvThree_X, tvThree_Y);
        waveThreeAnimator.setRepeatCount(-1);
        waveThreeAnimator.setRepeatMode(ValueAnimator.REVERSE);
        waveThreeAnimator.setDuration(400);
        waveThreeAnimator.setStartDelay(200);
    }

    public void startAnimation() {
        hangoutTvOne.setVisibility(View.VISIBLE);
        hangoutTvTwo.setVisibility(View.VISIBLE);
        hangoutTvThree.setVisibility(View.VISIBLE);

        waveOneAnimator.start();
        waveTwoAnimator.start();
        waveThreeAnimator.start();
    }

    public void stopAnimation() {
        waveOneAnimator.end();
        waveTwoAnimator.end();
        waveThreeAnimator.end();
        hangoutTvOne.setVisibility(View.GONE);
        hangoutTvTwo.setVisibility(View.GONE);
        hangoutTvThree.setVisibility(View.GONE);

    }

    class RetrieveStreamingUrl extends AsyncTask<String, Void, ArrayList<String>> {
        protected ArrayList<String> doInBackground(String... url) {
            if (url[0] == null) return null;
            ArrayList<String> allURls = new ArrayList<String>();
            try {
                URL urls = new URL(url[0]);
                BufferedReader in = new BufferedReader(new InputStreamReader(urls
                        .openStream()));
                String str;
                while ((str = in.readLine()) != null) {
                    allURls.add(str);
                }
                in.close();
                return allURls;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(ArrayList<String> urls) {
            if (urls != null) {
                String URL = "audio";
                String item = "";
                for (int i = 0; i < urls.size(); i++) {
                    item = urls.get(i);
                    if (item.contains("File")) {
                        URL = item.substring(item.indexOf("=") + 1, item.length());
                        break;
                    }
                }
                System.out.print("URL" + URL);
                showPlayIcon();
                try {
                    isPlayerReady = false;
                    mPlayer.setDataSource(URL);
                    mPlayer.prepareAsync();
                    status.setText("Buffering");
                    startAnimation();
                } catch (IllegalArgumentException e) {
                    onError(mPlayer, -1, -1);
                } catch (IllegalStateException e) {
                    onError(mPlayer, -1, -1);
                } catch (IOException e) {
                    onError(mPlayer, -1, -1);
                }
            }

        }
    }

    @Override
    public void onClick(View v) {
        if (mPlayer != null && isPlayerReady) {
            if (mPlayer.isPlaying()) {
                mPlayer.pause();
                showPlayIcon();
            } else {
                mPlayer.start();
                showPauseIcon();
            }
        }

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (mp == null)
            return;
        mp.release();
        mp = null;

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        if (mp != null) {
            mp.release();
            mp = null;
        }
        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        try {
            mPlayer.start();
            showPauseIcon();
            stopAnimation();
            isPlayerReady = true;
            status.setText("Playing");
        } catch (IllegalStateException e) {
            onError(mp, -1, -1);
        }
    }

    public void showPlayIcon() {
        control.setText("PLAY");
    }

    public void showPauseIcon() {
        control.setText("PAUSE");
    }

}
