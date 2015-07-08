package com.ufo.judicature.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.EMCallBack;
import com.easemob.chatuidemo.activity.LoginActivity;
import com.ufo.judicature.Base.BaseActivity;
import com.ufo.judicature.Entity.DoNortarizationEntity;
import com.ufo.judicature.Entity.ExamEntity;
import com.ufo.judicature.Entity.FeedbackEntity;
import com.ufo.judicature.Entity.ServiceResult;
import com.ufo.judicature.JudiApplication;
import com.ufo.judicature.Net.Api;
import com.ufo.judicature.Net.NetUtils;
import com.ufo.judicature.R;
import com.ufo.judicature.Utils.Config;

/**
 * 意见反馈
 * 
 */
public class FeedbackActivity extends BaseActivity {

	private EditText et_feedback;
	private Button btn_commit;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);

		((ImageView)findViewById(R.id.image_back)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		et_feedback = (EditText)findViewById(R.id.et_feedback);
		btn_commit = (Button)findViewById(R.id.btn_commit);
		btn_commit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				feedback();
			}
		});
	}

	private void feedback() {
		String content  = et_feedback.getText().toString().trim();
		Api.advice(self, content, Config.getUserId() , new NetUtils.NetCallBack<ServiceResult>() {
			@Override
			public void success(ServiceResult rspData) {
				FeedbackEntity entity = (FeedbackEntity) rspData;
				if (!entity.getError()) {
					com.ufo.judicature.Widget.Toast.show(self, "提交成功");
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(et_feedback.getWindowToken(), 0); //强制隐藏键盘
					finish();
				}
			}

			@Override
			public void failed(String msg) {
				com.ufo.judicature.Widget.Toast.show(self, msg);
			}
		}, FeedbackEntity.class);
	}
}