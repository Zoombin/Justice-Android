package com.ufo.judicature.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.easemob.chatuidemo.activity.LoginActivity;
import com.ufo.judicature.Base.BaseActivity;
import com.ufo.judicature.JudiApplication;
import com.ufo.judicature.R;
import com.ufo.judicature.Utils.Config;

/**
 * 个人中心
 * 
 */
public class PersonalActivity extends BaseActivity {

	private TextView tv_user;
	private TextView tv_feedback;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal);

		((ImageView)findViewById(R.id.image_back)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		tv_user = (TextView)findViewById(R.id.tv_user);
		tv_user.setText("登录用户：" + JudiApplication.getInstance().getUserName());

		tv_feedback = (TextView)findViewById(R.id.tv_feedback);
		tv_feedback.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(self, FeedbackActivity.class));
			}
		});

		((Button)findViewById(R.id.btn_logout)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				logout();
			}
		});
	}

	void logout() {
		final ProgressDialog pd = new ProgressDialog(self);
		String st = getResources().getString(R.string.Are_logged_out);
		pd.setMessage(st);
		pd.setCanceledOnTouchOutside(false);
		pd.show();
		JudiApplication.getInstance().logout(new EMCallBack() {

			@Override
			public void onSuccess() {
				self.runOnUiThread(new Runnable() {
					public void run() {
						pd.dismiss();
						startActivity(new Intent(self, LoginActivity.class));
						finish();
					}
				});
			}

			@Override
			public void onProgress(int progress, String status) {

			}

			@Override
			public void onError(int code, String message) {
				Toast.makeText(self, message, Toast.LENGTH_SHORT).show();
			}
		});
	}
}