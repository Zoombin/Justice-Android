package com.ufo.judicature.Widget;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.TextView;

import com.ufo.judicature.R;

public class Toast {
	
	public static void show(Context context,String msg){
		if(TextUtils.isEmpty(msg)){
			return;
		}
		TextView txt = new TextView(context);
		txt.setText(msg);

		txt.setTextColor(Color.parseColor("#000000"));
		txt.setBackgroundResource(R.drawable.ic_toast_bg);
		txt.setTextSize(18);
		txt.setPadding(30, 10, 30, 10);
		android.widget.Toast toast = new android.widget.Toast(context);
		toast.setView(txt);
		toast.setDuration(android.widget.Toast.LENGTH_SHORT);
		//toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
}
