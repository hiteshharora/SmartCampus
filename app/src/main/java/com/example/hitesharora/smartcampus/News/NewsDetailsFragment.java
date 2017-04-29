package com.example.hitesharora.smartcampus.News;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.hitesharora.smartcampus.BaseActivity;
import com.example.hitesharora.smartcampus.BaseFragment;
import com.example.hitesharora.smartcampus.R;


@SuppressLint("InflateParams")
public class NewsDetailsFragment extends BaseFragment {
	private BaseActivity mActivity;
	NewsItem currentItem;
	private ImageView mNewsImageView;
	private TextView mNewsTitle;
	private TextView mPublishedDate;
	private TextView mNewsContent;
	
	public static NewsDetailsFragment newInstance(NewsItem item) {
		NewsDetailsFragment mNewsDetailsFragment = new NewsDetailsFragment();
		Bundle args = new Bundle();
		if (item != null) {
			args.putSerializable("item", item);
		}
		mNewsDetailsFragment.setArguments(args);
		return mNewsDetailsFragment;
	}
	
	@Override
	public void onStart() {
		super.onStart();
	}
	
	@Override
	public void onAttach(Activity activity) {
		this.mActivity = (BaseActivity) activity;
		super.onAttach(activity);
		currentItem = (NewsItem)getArguments().getSerializable("item");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.news_detail_fragment, null);
		mNewsImageView = (ImageView) view.findViewById(R.id.news_details_image);
	    mNewsTitle = (TextView) view.findViewById(R.id.news_details_title);
	    mPublishedDate = (TextView) view.findViewById(R.id.news_details_item_publishedDate);
	    mNewsContent = (TextView) view.findViewById(R.id.news_details_content);
	    loadData();
		return view;
	}
	
	public void loadData() {
		Glide.with(getActivity()).load(currentItem.Thumbnail)
				.asBitmap()
				.override(450, 450)
				.placeholder(R.drawable.asu_logo)
				.into(new SimpleTarget<Bitmap>() {
					@Override
					public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
						int w = resource.getWidth();
						int h = resource.getHeight();
						LayoutParams params = (LayoutParams) mNewsImageView.getLayoutParams();
						params.width = mNewsImageView.getWidth();
						params.height = params.width * h / w;
						mNewsImageView.setLayoutParams(params);
						mNewsImageView.setImageBitmap(resource);
					}
				});
	
		mNewsTitle.setText(currentItem.Title);
//		try {
//			if (currentItem.PublishedDate == null) {
//				mPublishedDate.setText(Utils.convertToDisplayDate(new Date()));
//			} else {
//				mPublishedDate.setText(Utils.convertToDisplayDate(currentItem.PublishedDate));
//			}
//		} catch (ParseException e) {
//
//		}
		mNewsContent.setText(Html.fromHtml(currentItem.Content));
		mNewsContent.setMovementMethod(LinkMovementMethod.getInstance());
	}	
	
	@Override
	public void onResume() {
		super.onResume();
	}
}
