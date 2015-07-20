package com.ufo.judicature.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ufo.judicature.Base.BaseActivity;
import com.ufo.judicature.Entity.PhotosEntity;
import com.ufo.judicature.R;
import com.ufo.judicature.Widget.gallery.GalleryViewPager;
import com.ufo.judicature.Widget.gallery.UrlPagerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * photo detail page
 * 
 */
public class PhotoDetailActivity extends BaseActivity {

	public static final String EXTRA_PHOTODETAIL = "PHOTODETAIL";
	private ImageView image_back;
	private GalleryViewPager viewpager_photos;
	private TextView tv_photo_content;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photodetail);

		image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		Intent intent = getIntent();
		final ArrayList<PhotosEntity.Photo> photos = (ArrayList<PhotosEntity.Photo>) intent.getSerializableExtra(EXTRA_PHOTODETAIL);
		String[] urls = new String[photos.size()];
		for (int i=0; i<photos.size(); i++) {
			urls[i] = photos.get(i).getImage();
		}

		List<String> items = new ArrayList<>();
		Collections.addAll(items, urls);
		UrlPagerAdapter pagerAdapter = new UrlPagerAdapter(this, items);
		viewpager_photos = (GalleryViewPager) findViewById(R.id.viewpager_photos);
//		viewpager_photos.setOffscreenPageLimit(100);
		viewpager_photos.setAdapter(pagerAdapter);

		viewpager_photos.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				tv_photo_content.setText(photos.get(position).getContent());
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		tv_photo_content = (TextView) findViewById(R.id.tv_photo_content);
		tv_photo_content.setText(photos.get(0).getContent());
	}
}
