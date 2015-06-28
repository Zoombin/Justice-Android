package com.ufo.judicature.Activity;

import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.ufo.judicature.Base.BaseActivity;
import com.ufo.judicature.R;
import com.ufo.judicature.Utils.Config;

//import com.ufo.judicature.R;

/**
 * logo page
 * 
 */
public class LogoActivity extends BaseActivity {

//	private MyLocationListener mMyLocationListener;
	private ImageView image_logo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);

		Config.setScreenSize(this);
		initView();
	}

	@Override
	protected void onResume() {
		super.onResume();

		startLogoAnimation();

	}

	private void initView() {
		image_logo = (ImageView) findViewById(R.id.image_logo);
	}

	private void nextPage() {
		if (self.isFinishing()) {
			return;
		}

		if (Config.isFirst()) {
			createShortcut();
		}
		startActivity(new Intent(getApplication(), MainActivity.class));
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		finish();
	}

//	/**
//	 * 实现实位回调监听
//	 */
//	public class MyLocationListener implements BDLocationListener {
//
//		@Override
//		public void onReceiveLocation(BDLocation location) {
//
//			// 系统版本号
//			String deviceOSVersion = Utils.getDeviceOSVersion();
//
//			// 设备唯一序列号(IMEI)
//			String deviceid = Utils.getDeviceId(self);
//
//			// APP版本号
//			String appVer = String.valueOf(Utils.getClientVersionCode(self));
//
//			// IP地址
//			String ipAddress = Utils.getLocalIpAddress();
//
//			String nlatitude = "0.0";
//			String nlontitude = "0.0";
//			String cityName = "";
//			String cityId = "";
//			if (location != null) {
//				// 百度定位命令反了
//				nlatitude = Double.toString(location.getLongitude());
//				nlontitude = Double.toString(location.getLatitude());
//				cityName = location.getCity();
//				cityId = location.getCityCode();
//				DB_Location db_location = new DB_Location(self);
//				db_location.setLatitude(nlatitude);
//				db_location.setLontitude(nlontitude);
//				db_location.setCityId(cityId);
//				db_location.setCityName(cityName);
//			}
//
//			if (!TextUtils.isEmpty(Config.getAppID())) {
//				return;
//			}
//			Api.appRegister("Android", deviceOSVersion, "Android", deviceid, ipAddress, nlatitude, nlontitude, appVer, self,
//					new NetCallBack<ServiceResult>() {
//						@Override
//						public void success(ServiceResult rspData) {
//							RspAppRegist rsp = (RspAppRegist) rspData;
//							if (rsp.data == null) {
//								System.out.println("appregister fail");
//								finish();
//								return;
//							}
//							Config.setAppID(rsp.data.getAppid());
//							Config.setAppSecret(rsp.data.getAppsecret());
//
//							TicketUtil.instance().refrashTicket();
//							setPushEnable();
//
//							new Handler().postDelayed(new Runnable() {
//								@Override
//								public void run() {
//									// TODO Auto-generated method stub
//									nextPage2();
//								}
//							}, 1500);
//						}
//
//						@Override
//						public void failed(String msg) {
//							Toast.show(self, msg);
//							finish();
//						}
//					}, RspAppRegist.class);
//		}
//	}

	/**
	 * 创建桌面快捷方式
	 */
	private void createShortcut() {
		Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
		// 快捷方式的名称
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.app_name));
		// 不允许重复创建
		shortcut.putExtra("duplicate", false);
		// 指定快捷方式的启动对象
		Intent shortcutIntent = new Intent(Intent.ACTION_MAIN);
		shortcutIntent.setClassName(this.getPackageName(), this.getClass().getName());
		shortcutIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		// 快捷方式的图标
		ShortcutIconResource iconRes = ShortcutIconResource.fromContext(this, R.mipmap.ic_launcher);
		shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconRes);
		// 发出广播
		sendBroadcast(shortcut);
		// 将第一次启动的标识设置为false
		Config.setFirst(false);
	}

	// 闪屏动画
	private void startLogoAnimation() {

		TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.2f, Animation.RELATIVE_TO_SELF, 0.0f);
		animation.setDuration(800); // 设置动画持续时间
		animation.setFillAfter(true);
		animation.setInterpolator(new DecelerateInterpolator());

		animation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation arg0) {
//				if (!TextUtils.isEmpty(Config.getAppID())) {
//
//					TicketUtil.instance().refrashTicket();
//					setPushEnable();
//
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							nextPage();
						}
					}, 1500);
//				}
//
//				mMyLocationListener = new MyLocationListener();
//				MoLiApplication.getInstance().mLocationClient.registerLocationListener(mMyLocationListener);
//				MoLiApplication.getInstance().mGeofenceClient = new GeofenceClient(getApplicationContext());
//				MoLiApplication.getInstance().mLocationClient.start();
			}
		});

		image_logo.startAnimation(animation);
	}
}
