package com.prankspicalfakecall;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.fackgirlfraind.database.Contact;
import com.fackgirlfraind.database.DataBaseHandler;
import com.fakegirlfraind.grlyview.GalleryViewActivity;
import com.racinggamelabs.fakecallgirlfriendboyfriend.R;
import com.prankspicalfakesma.MainActivityFackSma;
import com.sm.ads.AdsLoadingListner;
import com.sm.ads.SMAdsConfig;
import com.sm.fullAds.FullAddScreen;
import com.sm.fullAds.FullAdsBridge;
import com.sm.fullAds.FullScreenAdvertisment;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Menuactivity extends Activity  {
	public static ArrayList<Contact> imageArry = new ArrayList<Contact>();
	public static int navigationCounter = 0;
	public static Menuactivity menu;
	private final int SPLASH_DISPLAY_LENGHT = 3000;
	RelativeLayout raLayout;
	public static int addHeight = 75;
	// public static BannerAdvertisment bannerAdvertisment;
	//MoreAppsBanner moBanner;
	// public static StartAppAd startAppAd;
	Bundle bundleref;
	// Connectivity connectivity = new Connectivity();
	// public static boolean ISconnectivity, ISConnectedWifi;SM ADS primiry ID
	// public static boolean ISConnectedMobile;
	DataBaseHandler db;
	/*int[] girlimage = new int[] { R.drawable.boy1, R.drawable.boy2,
			R.drawable.boy3, R.drawable.boy4, R.drawable.boy5,
			R.drawable.girl1, R.drawable.girl2, R.drawable.girl3,
			R.drawable.girl4, R.drawable.girl5 };*/
	/*public static String clickonboyandgirl;
	String Name[] = { "Evan", "Aaron", "Carrick", "Brooke", "Dalton",
			"Adamaris", "Alma", "Galia", "Tilly", "Sable" };

	String Numberboy[] = { "456712056", "991267321", "007873231", "917897564",
			"975649098", "111984365", "675432134", "947017354", "987873245",
			"329075345" };
	String Time[] = { "5000", "60000", "30000", "5000", "5000", "5000",
			"15000", "30000", "5000", "5000" };*/
	public static boolean isLibReady = false;
	public static boolean splaseadds = true;
	//	private static FullAdsBridge fullAdsBridge;
	public Bundle bundle;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		bundleref = savedInstanceState;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

		db = new DataBaseHandler(getApplicationContext());
	//	fulladds(this);

		List<Contact> contacts = db.getAllContacts();
		/*int con = contacts.size();
		if (contacts.size() == 0) {
			for (int i = 0; i < girlimage.length; i++) {
				if (i < 5) {
					Bitmap largeIcon = BitmapFactory.decodeResource(
							getResources(), girlimage[i]);

					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					largeIcon.compress(Bitmap.CompressFormat.PNG, 100, stream);
					byte[] bitMapData = stream.toByteArray();

					db.addContact(new Contact("Android", bitMapData, ""
							+ Name[i], "" + Numberboy[i], "" + Time[i],
							"false", "1200000", "", "Set Ringtone", "boy"));
				} else {
					Bitmap largeIcon = BitmapFactory.decodeResource(
							getResources(), girlimage[i]);
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					largeIcon.compress(Bitmap.CompressFormat.PNG, 100, stream);
					byte[] bitMapData = stream.toByteArray();

					db.addContact(new Contact("Android", bitMapData, ""
							+ Name[i], "" + Numberboy[i], "" + Time[i],
							"false", "1200000", "", "Set Ringtone", "girl"));
				}

			}
		}*/

		// if (ISConnectedWifi) {
		// startAppAd = new StartAppAd(this);
		// }

		raLayout = (RelativeLayout) findViewById(R.id.menuscreen);
		/*if (moBanner == null) {
			moBanner = MoreAppsBanner.getInstance(getApplicationContext(),
					(float)1.8, 0xff321414, Color.WHITE, Color.WHITE, 1, 1,
					MoreAppsBanner.portrait);
			moBanner.setURL("423046eb-982d-4929-8483-4caf2db8f5ad",
					"Entertainment", 50);

		}*/
		// set values
		/*try {
			raLayout.addView(moBanner);
		} catch (Exception e) {
			// TODO: handle exception
		}*/

		menu = this;

		RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.menuscreen);
		//Banner startAppBanner = new Banner(this);
		RelativeLayout.LayoutParams bannerParameters = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
		bannerParameters.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		//	mainLayout.addView(startAppBanner, bannerParameters);

		manageNavigation("Menu");
		if (Build.VERSION.SDK_INT >= 11) {
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
			ActionBar actionBar = getActionBar();
			actionBar.hide();
		}
		Thread thread = new Thread( new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				Savedata savedata = Savedata.getInstance();
				savedata.saveIsgrlly(false, menu);
				Intent i = new Intent(Menuactivity.this,MainActivityFackCall.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(i);
				finish();

			}


		});

		thread.start();

		manageNavigation("Menu");
				/*if (Startmenuscreen.ISConnectedWifi) {
					Startmenuscreen.startAppAd.showAd();
					Startmenuscreen.startAppAd.loadAd();
				}*/
	}

	public static void manageNavigation(String CallName) {
		navigationCounter++;

		if (navigationCounter == 1 && Startmenuscreen.ISConnectedMobile) {
			if (FullScreenAdvertisment.fullAdsBitmap != null) {

				navigationCounter = 0;
				if (isNetworkAvaliable(Menuactivity.menu)) {
					Intent i = new Intent(Menuactivity.menu,
							FullAddScreen.class);
					i.putExtra("caller", CallName);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					Menuactivity.menu.startActivity(i);
				}
				// SpalashActivity.spalashActivity.overridePendingTransition(R.anim.slide_in_from_bottom,
				// R.anim.nothing);
			} else {
				FullScreenAdvertisment.getFullAddController(menu)
						.getFullScreenAdd();
				navigationCounter = 0;
			}

		}
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

	/*@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (moBanner != null) {
			moBanner.onPause();
		}
		try {
			// raLayout.removeView(moBanner);
		} catch (Exception e) {

		}

	}
*/
	/*@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (moBanner != null) {
			moBanner.onResume();
		}
	}
*/
/*	public static void callMoreAps(Activity activity) {
		try {
			if (isNetworkAvaliable(activity)) {
				Intent i = new Intent(activity, MoreAppsListView.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				*//*i.putExtra("cat", com.sensiblemobiles.ads.Config.MoreCategory);
				i.putExtra("id", com.sensiblemobiles.ads.Config.MoreappsID);*//*
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

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		 manageNavigation("Menu");
		// if (Startmenuscreen.ISConnectedWifi) {
		// Startmenuscreen.startAppAd.showAd();
		// Startmenuscreen.startAppAd.loadAd();
		// }
	}
/*
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (moBanner != null) {
			ViewGroup parentViewGroup = (ViewGroup) moBanner.getParent();
			if (parentViewGroup != null) {
				parentViewGroup.removeAllViews();
			}
		}
	}*/

}
