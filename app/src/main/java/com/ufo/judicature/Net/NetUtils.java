package com.ufo.judicature.Net;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.ufo.judicature.Entity.ServiceResult;
import com.ufo.judicature.JudiApplication;
import com.ufo.judicature.R;
import com.ufo.judicature.Utils.Utils;
import com.ufo.judicature.Widget.CProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class NetUtils {
	public interface NetCallBack<T> {
		public void success(T rspData);
		public void failed(String msg);
	}

	public static final String BASE_URL = "http://www.ufocapital.com:8000/index.php";
//	public static final String BASE_URL = "http://112.124.98.9/justice/index.php";

	private static RequestQueue mQueue = Volley.newRequestQueue(JudiApplication.getContext());

	// GET
	public static void getValue(Context context, String method, final String msg, final NetCallBack<ServiceResult> callBack, final Class<?> rspCls) {

		if (!Utils.isNetworkConnected(context)) {
			if (callBack != null)
				callBack.failed("网络异常!");
			return;
		}

		final CProgressDialog progressDialog = new CProgressDialog(context, R.style.CustomDialog);

		if (msg != null) {
			progressDialog.setMessage(msg);
			progressDialog.setCancelable(false);
			try {
				if (!progressDialog.isShowing()) {
					progressDialog.show();
				}
			} catch (Exception e) {
			}
		}

		String url = BASE_URL + method;
		Log.i("NetUtils", url);
		JsonObjectRequest request = new JsonObjectRequest(Method.GET, url, null, new Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject jsonObject) {
				progressDialog.dismiss();
				//Log.i("NetUtils", "get rsp : " + jsonObject.toString());
				boolean errorCode = getErrorCode(jsonObject.toString());
				if (errorCode) {
					if (callBack != null)
						callBack.failed(getErrorMsg(jsonObject.toString()));
					return;
				}
				ServiceResult rsp = null;
				try {
					Gson gson = new Gson();
					rsp = (ServiceResult) gson.fromJson(jsonObject.toString(), rspCls);
				} catch (Exception e) {
					Log.e("NetUtils", "get RspMsgError !");
					e.printStackTrace();
				}
				if (callBack != null)
					if (rsp != null) {
						callBack.success(rsp);
					} else {
						callBack.failed("数据解析出错!");
					}
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				progressDialog.dismiss();
				if (callBack != null)
					callBack.failed(error.getMessage());
			}
		});

		request.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		mQueue.add(request);
	}

	// POST
	public static void post(Context context, String method, final Map<String, String> params, final String msg, final NetCallBack<ServiceResult> callBack, final Class<?> rspCls) {
		if (!Utils.isNetworkConnected(context)) {
			if (callBack != null)
				callBack.failed("网络异常!");
			return;
		}
		final CProgressDialog progressDialog = new CProgressDialog(context, R.style.CustomDialog);
		if (msg != null) {
			progressDialog.setMessage(msg);
			progressDialog.setCancelable(true);
			try {
				if (!progressDialog.isShowing()) {
					progressDialog.show();
				}
			} catch (Exception e) {

			}
		}
		String url = BASE_URL + method;
		//Log.i("NetUtils", "post req : " + url  +"  pama : "+ Api.getReqPama(params));
		StringRequest request = new StringRequest(Method.POST, url, new Listener<String>() {
			@Override
			public void onResponse(String jsonObject) {
				progressDialog.dismiss();
				//Log.i("NetUtils", "post rsp : " + new String(jsonObject.getBytes()));
				boolean errorCode = getErrorCode(jsonObject);
				if (errorCode) {
					if (callBack != null)
						callBack.failed(getErrorMsg(jsonObject));
					return;
				}
				ServiceResult rsp = null;
				try {
					Gson gson = new Gson();
					rsp = (ServiceResult) gson.fromJson(jsonObject.toString(), rspCls);
				} catch (Exception e) {
					Log.e("NetUtils", "post RspMsgError !");
					e.printStackTrace();
				}
				if (callBack != null)
					if (rsp != null) {
						callBack.success(rsp);
					} else {
						callBack.failed("数据解析出错!");
					}
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				if (callBack != null)
					callBack.failed(error.getMessage());
			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				return params;
			}
		};
		request.setRetryPolicy(new DefaultRetryPolicy(10 * 1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		mQueue.add(request);
	}

	public static boolean getErrorCode(String str) {
		try {
			JSONObject object = new JSONObject(str);
			return object.optBoolean("error");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static String getErrorMsg(String str) {
		try {
			JSONObject object = new JSONObject(str);
			return object.optString("msg");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "";
	}
}
