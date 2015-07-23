package com.ufo.judicature.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ufo.judicature.Base.BaseActivity;
import com.ufo.judicature.Entity.NewsEntity;
import com.ufo.judicature.Entity.PhotosEntity;
import com.ufo.judicature.R;

import java.util.ArrayList;

/**
 * new detail page
 * 
 */
public class NewDetailActivity extends BaseActivity {

	public static final String EXTRA_NEWDETAIL = "EXTRA_NEWDETAIL";
	private ImageView image_back;
	private TextView tv_new_title;
	private ImageView image_new;
	private TextView tv_new_content;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newdetail);

		Intent intent = getIntent();
		final NewsEntity.NewEntity newEntity = (NewsEntity.NewEntity) intent.getSerializableExtra(EXTRA_NEWDETAIL);

		image_back = (ImageView) findViewById(R.id.image_back);
		image_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		tv_new_title = (TextView) findViewById(R.id.tv_new_title);
		image_new = (ImageView) findViewById(R.id.image_new);
		tv_new_content = (TextView) findViewById(R.id.tv_new_content);

		tv_new_title.setText(newEntity.getTitle());
		ImageLoader.getInstance().displayImage(newEntity.getImage(), image_new);
		image_new.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(self, PhotoDetailActivity.class);
				ArrayList<PhotosEntity.Photo> photos = new ArrayList<>();
				PhotosEntity.Photo photo = new PhotosEntity.Photo();
				photo.setImage(newEntity.getImage());
				photos.add(photo);
				intent.putExtra(PhotoDetailActivity.EXTRA_PHOTODETAIL, photos);
				startActivity(intent);
			}
		});
		tv_new_content.setText(newEntity.getContent());
	}
}
