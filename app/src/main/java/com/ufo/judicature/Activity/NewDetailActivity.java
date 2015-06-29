package com.ufo.judicature.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ufo.judicature.Base.BaseActivity;
import com.ufo.judicature.Entity.NewsEntity;
import com.ufo.judicature.R;

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
		NewsEntity.NewEntity newEntity = (NewsEntity.NewEntity) intent.getSerializableExtra(EXTRA_NEWDETAIL);

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
		tv_new_content.setText(newEntity.getContent());
	}
}
