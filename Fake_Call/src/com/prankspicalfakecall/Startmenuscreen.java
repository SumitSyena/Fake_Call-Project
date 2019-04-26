package com.prankspicalfakecall;

import com.fakegirlfraind.grlyview.GalleryViewActivity;
import com.racinggamelabs.fakecallgirlfriendboyfriend.R;
import com.sensiblemobiles.moreapps.MoreAppsListView;
import com.sm.ads.AdsLoadingListner;
import com.sm.ads.SMAdsConfig;
import com.sm.fullAds.FullAdsBridge;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;
import com.startapp.android.publish.banner.Banner;
import com.startapp.android.publish.splash.SplashConfig;
import com.startapp.android.publish.splash.SplashConfig.Theme;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Startmenuscreen extends Activity implements AdsLoadingListner {
	public static Animation animBlink;
	public static boolean ISconnectivity, ISConnectedWifi;
	public static boolean ISConnectedMobile;
	Connectivity connectivity = new Connectivity();
	public static StartAppAd startAppAd;
	public static Startmenuscreen Startmenuscreen;
	public static Display display;
	public static FullAdsBridge fullAdsBridge;
	public static boolean isLibReady = false;
	public static boolean splaseadds = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		SMAdsConfig.setEventDebug(true);
		SMAdsConfig.intilizeAdsConfig(Startmenuscreen.this, Config.SMID, this, SMAdsConfig.ADONBOTTOM);
		Startmenuscreen = this;
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
	//	display = wm.getDefaultDisplay();
		ISconnectivity = connectivity.isConnected(getApplicationContext());
		ISConnectedWifi = connectivity.isConnectedWifi(getApplicationContext());
		ISConnectedMobile = connectivity
				.isConnectedMobile(getApplicationContext());
		if (ISConnectedWifi) {
			//fulladds(this);
		}

		if (ISConnectedWifi) {
			StartAppSDK.init(this, "102132082", "203001719", true);

			StartAppAd.showSplash(this, savedInstanceState, new SplashConfig()
					.setTheme(Theme.GLOOMY).setLogo(R.drawable.splash)
					.setAppName("Sali Prank Call"));
			// StartAppAd.showSlider(this);
		}

		// StartAppAd.showSlider(this);
		RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.menuscreen);
		Banner startAppBanner = new Banner(this);
		RelativeLayout.LayoutParams bannerParameters = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
		bannerParameters.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//		mainLayout.addView(startAppBanner, bannerParameters);

		if (Build.VERSION.SDK_INT >= 11) {
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
			ActionBar actionBar = getActionBar();
			actionBar.hide();
		}
		//Button freegift = (Button) findViewById(R.id.freegift);
		animBlink = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.heart_animation);
	//	freegift.startAnimation(animBlink);
	//	Button startButton = (Button) findViewById(R.id.startmenubtn);
		Thread thread = new Thread( new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}

				Intent i = new Intent(Startmenuscreen.this, Menuactivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(i);
				finish();

			}
		});
		thread.start();

		/*freegift.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (isNetworkAvaliable(getApplicationContext())) {

					callMoreAps(Startmenuscreen);
				} else {
					Toast.makeText(getApplicationContext(),
							"Please check internet connection",
							Toast.LENGTH_SHORT).show();
				}

			}
		});*/
	}

	public void onBackPressed() {
		if (Startmenuscreen.ISConnectedWifi) {
	//	fulladds(this);
		}
		new AlertDialog.Builder(this).setIcon(R.drawable.ic_launcher)

				.setTitle("Exit").setMessage("Do you want to exit?")

				.setPositiveButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				}).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		}).show();

	}

	public static boolean isNetworkAvaliable(Context ctx) {
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) ctx
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if ((connectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED)
					|| (connectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connectivityManager
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
					.getState() == NetworkInfo.State.CONNECTED)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return true;
			// TODO: handle exception
		}

	}

	/*public static void callMoreAps(Activity activity) {
		try {
			if (isNetworkAvaliable(activity)) {
				Intent i = new Intent(activity, MoreAppsListView.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				i.putExtra("cat", com.sensiblemobiles.ads.Config.MoreCategory);
				i.putExtra("id", com.sensiblemobiles.ads.Config.MoreappsID);
				i.putExtra("count", "100");
				activity.startActivity(i);
			} else {
				Toast.makeText(activity, "Please check internet connection",
						Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}*/
	public void libLoaded(boolean flag) {
		if (flag) {
			isLibReady= true;
			if (isLibReady && splaseadds) {
				fullAdsBridge = new FullAdsBridge();
				//fullAdsBridge.showSpalshAds(this, bundle);
				fullAdsBridge.addSuperSonicTracking(this);
				System.out.println("sumit");
			}
		}


	}
	public static void fulladds(Activity activity) {
		try {
			if (fullAdsBridge != null) {
				System.out.println("fulladd");

				fullAdsBridge.showFullScreenAds(activity);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}