package com.fakegirlfraind.grlyview;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.fackgirlfraind.database.Contact;
import com.fackgirlfraind.database.DataBaseHandler;

import com.prankspicalfakecall.MainActivityFackCall;
import com.prankspicalfakecall.Menuactivity;
import com.prankspicalfakecall.Savedata;
import com.prankspicalfakecall.Startmenuscreen;
import com.racinggamelabs.fakecallgirlfriendboyfriend.R;
import android.R.string;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GalleryViewActivity extends Activity {
	/** Called when the activity is first created. */

	View lastSelectedView = null;
	int i;
	public static int mycallset;
	DataBaseHandler db;
	GalleryViewActivity galleryViewTestActivity;

	private static final int CAMERA_REQUEST = 1;
	private static final int PICK_FROM_GALLERY = 2;

	Contact pictureContact;
	ImageView toptmimage, bootmimage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		if (((display.getWidth() >= 1024) && (display.getWidth() <= 1280))
				&& ((display.getHeight() >= 600) && (display.getHeight() <= 2000))) {
			setContentView(R.layout.gerlly2);
		} else {
			setContentView(R.layout.gerlly);
		}

	/*	toptmimage = (ImageView) findViewById(R.id.chngimageselection);
		bootmimage = (ImageView) findViewById(R.id.selectionimage);
		if (Menuactivity.clickonboyandgirl.equalsIgnoreCase("boy")) {
			toptmimage.setImageResource(R.drawable.slctyrbf);
			bootmimage.setImageResource(R.drawable.boyfrnd);
		}*/
		if (Build.VERSION.SDK_INT >= 11) {
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
			ActionBar actionBar = getActionBar();
			actionBar.hide();
		}

		final Context context = this;
		db = new DataBaseHandler(this);
		galleryViewTestActivity = this;

		//StartAppAd.showSlider(this);
		RelativeLayout mainLayout = (RelativeLayout) findViewById(R.id.gerllylayout);
		//Banner startAppBanner = new Banner(this);
		RelativeLayout.LayoutParams bannerParameters = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		bannerParameters.addRule(RelativeLayout.CENTER_HORIZONTAL);
		bannerParameters.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		//mainLayout.addView(startAppBanner, bannerParameters);

		RelativeLayout mainLayout2 = (RelativeLayout) findViewById(R.id.gerllylayout);
		//Banner startAppBanner2 = new Banner(this);
		RelativeLayout.LayoutParams bannerParameters2 = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		bannerParameters2.addRule(RelativeLayout.CENTER_HORIZONTAL);
		bannerParameters2.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		//mainLayout2.addView(startAppBanner2, bannerParameters2);

		CircleGallery yappsGallery = (CircleGallery) findViewById(R.id.gallery);
		yappsGallery.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {
				Contact singleContact = Menuactivity.imageArry.get(position);
				if (singleContact.getId() <= 6 || singleContact.getId() <= 10) {
					
					DataBaseHandler db = new DataBaseHandler(
							GalleryViewActivity.this);
					Savedata savedata = Savedata.getInstance();
					Savedata.savedata
							.saveIsgrlly(true, galleryViewTestActivity);
					Savedata.savedata.savegalleryposition(position,
							galleryViewTestActivity);

					Intent intent = new Intent(GalleryViewActivity.this,
							MainActivityFackCall.class);
					startActivity(intent);
					finish();
				/*	if (Startmenuscreen.ISConnectedWifi) {
						Startmenuscreen.startAppAd.showAd();
						Startmenuscreen.startAppAd.loadAd();
					}*/
						com.prankspicalfakecall.Menuactivity
						.manageNavigation("GalleryViewActivity");

				} else {
					ratepoup(position);

				}
			}
		});
		/**
		 * open dialog for choose camera/gallery
		 */

		final String[] option = new String[] { "Take from Camera",
				"Select from Gallery" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.select_dialog_item, option);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Select Option");
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.e("Selected Item", String.valueOf(which));
				if (which == 0) {
					callCamera();
				}
				if (which == 1) {
					callGallery();
				}

			}
		});
		final AlertDialog dialog = builder.create();

		// addImage.setOnClickListener(new View.OnClickListener() {
		// public void onClick(View v) {
		// dialog.show();
		// }
		// });

	}

	/**
	 * On activity result
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK)
			return;

		switch (requestCode) {
		case CAMERA_REQUEST:

			Bundle extras = data.getExtras();

			if (extras != null) {
				Bitmap yourImage = extras.getParcelable("data");
				// convert bitmap to byte
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				yourImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
				byte imageInByte[] = stream.toByteArray();
				Log.e("outputbefore conversion",imageInByte.toString());
				// Inserting Contacts
				Log.d("Insert: ", "Inserting ..");
				// db.addContact(new Contact("Android", imageInByte, "afsar",
				// "9898", "5sec", "vibartion_of", "call:10sec"));
				Intent i = new Intent(GalleryViewActivity.this,
						GalleryViewActivity.class);
				startActivity(i);
				finish();

			}
			break;
		case PICK_FROM_GALLERY:
			Bundle extras2 = data.getExtras();

			if (extras2 != null) {
				Bitmap yourImage = extras2.getParcelable("data");
				// convert bitmap to byte
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				yourImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
				byte imageInByte[] = stream.toByteArray();
				Log.e("outputbeforeconversion", imageInByte.toString());
				// Inserting Contacts
				Log.d("Insert: ", "Inserting ..");
				// db.addContact(new Contact("Android", imageInByte, "afroz",
				// "000", "5sec", "vibartion_of", "call:10sec"));
				Intent i = new Intent(GalleryViewActivity.this,
						GalleryViewActivity.class);
				startActivity(i);
				finish();
			}

			break;
		}
	}

	/**
	 * open camera method
	 */
	public void callCamera() {

		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(cameraIntent, CAMERA_REQUEST);

		// Intent cameraIntent = new Intent(
		// android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		// cameraIntent.putExtra("crop", "true");
		// cameraIntent.putExtra("aspectX", 0);
		// cameraIntent.putExtra("aspectY", 0);
		// cameraIntent.putExtra("outputX", 200);
		// cameraIntent.putExtra("outputY", 150);
		// cameraIntent.putExtra("return-data", true);
		// startActivityForResult(cameraIntent, CAMERA_REQUEST);

	}

	/**
	 * open gallery method
	 */

	public void callGallery() {
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 0);
		intent.putExtra("aspectY", 0);
		intent.putExtra("outputX", 200);
		intent.putExtra("outputY", 150);
		intent.putExtra("return-data", true);
		startActivityForResult(
				Intent.createChooser(intent, "Complete action using"),
				PICK_FROM_GALLERY);

	}

	private void ratepoup(final int position) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

		// Setting Dialog Title

		// Setting Dialog Message
		alertDialog.setMessage("What you want to do?");

		// Setting Icon to Dialog

		// Setting Positive "Yes" Button
		alertDialog.setPositiveButton("Open",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						
////					
//							if (Startmenuscreen.ISConnectedWifi) {
//								Startmenuscreen.startAppAd.showAd();
//								Startmenuscreen.startAppAd.loadAd();
//							}else{
//								com.prankspicalfakecall.Menuactivity
//								.manageNavigation("GalleryViewActivity");
//							}
						
						
						DataBaseHandler db = new DataBaseHandler(
								GalleryViewActivity.this);
						Savedata savedata = Savedata.getInstance();
						Savedata.savedata.saveIsgrlly(true,
								galleryViewTestActivity);
						Savedata.savedata.savegalleryposition(position,
								galleryViewTestActivity);

						Intent intent = new Intent(GalleryViewActivity.this,
								MainActivityFackCall.class);
						startActivity(intent);
						finish();
					}
				});

		// Setting Negative "NO" Button
		alertDialog.setNegativeButton("Remove",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						DataBaseHandler db = new DataBaseHandler(
								GalleryViewActivity.this);
						Contact singleContact = Menuactivity.imageArry
								.get(position);
						db.deleteContact(singleContact);
						Intent intent = new Intent(GalleryViewActivity.this,
								GalleryViewActivity.class);
						startActivity(intent);
						finish();
						dialog.cancel();
						/*if (Startmenuscreen.ISConnectedWifi) {
							Startmenuscreen.startAppAd.showAd();
							Startmenuscreen.startAppAd.loadAd();
						}else{*/
							com.prankspicalfakecall.Menuactivity
							.manageNavigation("GalleryViewActivity");
						}
					

				});

		alertDialog.show();
	}
}