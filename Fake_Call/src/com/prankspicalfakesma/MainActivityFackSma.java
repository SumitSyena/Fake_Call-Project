package com.prankspicalfakesma;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.xml.sax.DTDHandler;

import com.racinggamelabs.fakecallgirlfriendboyfriend.R;
import com.prankspicalfakecall.FackCallServies;
import com.prankspicalfakecall.MainActivity;
import com.prankspicalfakecall.MainActivityFackCall;
import com.prankspicalfakecall.Menuactivity;
import com.prankspicalfakecall.Startmenuscreen;

import com.prankspicalfakecall.Savedata;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivityFackSma extends Activity {
	Savedata savedata;

	private static final int IMAGE_CAPTURE = 0;
	// public static boolean vibrationon = false;
	public static boolean recoredvoic = false;
	public MediaRecorder myAudioRecorder;
	public String outputFile;

	final private static int DIALOG_LOGIN = 1;
	private boolean clickcontctbtn = false, clickcontvoicbtn = false,
			clickuserimagevoicbtn, clickcringbtn = false;;
	private static final int PICK_FROM_GALLERY = 1;
	private static final int PICK_FROM_CAMERA = 2;
	public static boolean calltimetsms = false;
	public static long NOTIFY_INTERVAL = 0;
	public int PICK_CONTACT;
	int REQ_CODE_PICK_SONG;
	TextView setcallername, setcallernumber, notiftitext, writesms;
	public String CallerName, CallerNumber, SmaText;
	ImageView setcontectnumber, reordicon;
	public static MainActivityFackSma mainActivityFackSma;
	public Bitmap bitmap;
	private Calendar calendar;
	private String format = "";
	private TimePicker timePicker1;
	int Currenthour, Currentmin;
	private int mHour, mMinute;
	Button Custamtimesate, setringtone, vibratesmson, vibratesmsoff;
	long diffmillisec;
	MediaPlayer mPlayer = new MediaPlayer();
	public Uri ringtone;
	public static String SmsPlace = "";

	// public static Vibrator v;

	// Button FiveBox, TenBox, FiftenBox, ThrtysecBox, OneMinBox;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.facksma);
		mainActivityFackSma = this;
		savedata = Savedata.getInstance();
		addListenerRadioGroup1();
		// ======================All data save and get ===============
		savedata.savesmstext("", mainActivityFackSma);
		savedata.saveRingTone("", mainActivityFackSma);
		savedata.saveVibration(savedata.controalvibration, mainActivityFackSma);
		savedata.saveSmspalace(savedata.smspalace, mainActivityFackSma);
		// /====================

		calendar = Calendar.getInstance();
		Currenthour = calendar.get(Calendar.HOUR_OF_DAY);
		Currentmin = calendar.get(Calendar.MINUTE);

		if (Build.VERSION.SDK_INT >= 11) {
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
			ActionBar actionBar = getActionBar();
			actionBar.hide();
		}

		setcallername = (TextView) findViewById(R.id.setcallername);
		setcallernumber = (TextView) findViewById(R.id.setcallernumber);

		writesms = (TextView) findViewById(R.id.writesms);
		Custamtimesate = (Button) findViewById(R.id.setcustamtime21);

		setringtone = (Button) findViewById(R.id.setringtone);
		// setsmsplace = (Button) findViewById(R.id.setplacesms);
		vibratesmson = (Button) findViewById(R.id.vibratesms);

		vibratesmsoff = (Button) findViewById(R.id.vibratesms2);
		vibratesmsoff.setVisibility(View.INVISIBLE);
		setcontectnumber = (ImageView) findViewById(R.id.setcontectpic);

		final Button start = (Button) findViewById(R.id.start);

		// Button Tenvesec = (Button) findViewById(R.id.fiftensec);

		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				com.prankspicalfakecall.Menuactivity.manageNavigation("Menu");
				CallerName = setcallername.getText().toString();
				CallerNumber = setcallernumber.getText().toString();
				if (CallerName.equalsIgnoreCase("")) {
					Toast.makeText(getApplicationContext(),
							"please enter name", Toast.LENGTH_SHORT).show();
				} else if (CallerNumber.equalsIgnoreCase("")) {
					Toast.makeText(getApplicationContext(),
							"please enter number", Toast.LENGTH_SHORT).show();
				} else if (NOTIFY_INTERVAL == 0) {
					Toast.makeText(getApplicationContext(),
							"please enter time", Toast.LENGTH_SHORT).show();
				} else if (CallerName != null && NOTIFY_INTERVAL != 0
						&& CallerNumber != null) {
					CallerName = setcallername.getText().toString();
					CallerNumber = setcallernumber.getText().toString();
					SmaText = writesms.getText().toString();
					calltimetsms = true;
					savedata.saveusername(CallerName, mainActivityFackSma);
					savedata.saveuserNumber(CallerNumber, mainActivityFackSma);
					savedata.savesmstext(SmaText, mainActivityFackSma);
					startService(new Intent(MainActivityFackSma.this,
							FacksmaServies.class));
					Toast.makeText(getApplicationContext(), "Setting Saved",
							Toast.LENGTH_SHORT).show();
					/*if (Startmenuscreen.ISConnectedWifi) {
						Startmenuscreen.startAppAd.showAd();
						Startmenuscreen.startAppAd.loadAd();
					}*/
					finish();
				}
			}

		});

		vibratesmson.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				vibratesmsoff.setVisibility(View.VISIBLE);
				vibratesmson.setVisibility(View.INVISIBLE);
				savedata.controalvibration = true;
				savedata.saveVibration(savedata.controalvibration,
						mainActivityFackSma);
			}
		});
		vibratesmsoff.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				vibratesmson.setVisibility(View.VISIBLE);
				vibratesmsoff.setVisibility(View.INVISIBLE);
				savedata.controalvibration = false;
				savedata.saveVibration(savedata.controalvibration,
						mainActivityFackSma);

			}
		});

		setringtone.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(
						RingtoneManager.ACTION_RINGTONE_PICKER);
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE,
						RingtoneManager.TYPE_NOTIFICATION
								| RingtoneManager.TYPE_RINGTONE);
				intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT,
						true);
				intent.putExtra(
						RingtoneManager.EXTRA_RINGTONE_DEFAULT_URI,
						RingtoneManager
								.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
				startActivityForResult(intent, 0);
				clickcringbtn = true;

			}
		});

		setcontectnumber.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent pickContactIntent = new Intent(Intent.ACTION_PICK,
						ContactsContract.Contacts.CONTENT_URI);
				pickContactIntent
						.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
				startActivityForResult(pickContactIntent, 1);
				clickcontctbtn = true;
			}
		});
		Custamtimesate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar mcurrentTime = Calendar.getInstance();
				final int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
				final int minute = mcurrentTime.get(Calendar.MINUTE);
				TimePickerDialog mTimePicker;
				mTimePicker = new TimePickerDialog(mainActivityFackSma,
						new TimePickerDialog.OnTimeSetListener() {
							@Override
							public void onTimeSet(TimePicker timePicker,
									int selectedHour, int selectedMinute) {
								cusatmdate(Currenthour, Currentmin,
										selectedHour, selectedMinute);
								String s = selectedHour + ":" + selectedMinute;
								Custamtimesate.setText("" + s);

							};
						}, hour, minute, true);
				mTimePicker.setTitle("Select Time");
				mTimePicker.show();

			}

		});

	}

	public void callGallery() {
		Intent intent = new Intent();

		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);

		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 0);
		intent.putExtra("aspectY", 0);
		intent.putExtra("outputX", 200);
		intent.putExtra("outputY", 150);

		try {

			intent.putExtra("return-data", true);
			startActivityForResult(
					Intent.createChooser(intent, "Complete action using"),
					PICK_FROM_GALLERY);

		} catch (ActivityNotFoundException e) {
			// Do nothing for now
		}
	}

	/**
	 * open camera method
	 */
	public void callCamera() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		intent.putExtra(MediaStore.EXTRA_OUTPUT,
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());
		// ******** code for crop image
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 0);
		intent.putExtra("aspectY", 0);
		intent.putExtra("outputX", 200);
		intent.putExtra("outputY", 150);

		try {

			intent.putExtra("return-data", true);
			startActivityForResult(intent, PICK_FROM_CAMERA);

		} catch (ActivityNotFoundException e) {
			// Do nothing for now
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && clickuserimagevoicbtn) {
			Bundle extras2 = data.getExtras();
			if (extras2 != null) {
				bitmap = extras2.getParcelable("data");
				// setcallerimage.setImageBitmap(bitmap);
				clickuserimagevoicbtn = false;
			}
		}// http://stackoverflow.com/questions/9890757/android-camera-data-intent-returns-null
			// if (requestCode == PICK_FROM_CAMERA) {
		// Bundle extras = data.getExtras();
		// if (extras != null) {
		// bitmap = extras.getParcelable("data");
		// setcallerimage.setImageBitmap(bitmap);
		//
		// }
		// }

		if (resultCode == RESULT_OK && clickcringbtn) {
			ringtone = data
					.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
			Ringtone r = RingtoneManager.getRingtone(this, ringtone);
			String ringToneName = r.getTitle((this));
			savedata.saveRingTone(ringtone.toString(), mainActivityFackSma);

			if (ringToneName.length() > 8) {
				setringtone.setText(ringToneName.substring(0, 7));
			} else {
				setringtone.setText("" + ringToneName);
			}

			clickcringbtn = false;
		}
		if (resultCode == Activity.RESULT_OK && clickcontctbtn == true) {
			Uri contactData = data.getData();
			Cursor c = managedQuery(contactData, null, null, null, null);
			if (c.moveToFirst()) {
				CallerName = c
						.getString(c
								.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
				CallerNumber = c
						.getString(c
								.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
				System.out.println("");
				clickcontctbtn = false;
				setcallername.setText("" + CallerNumber);
				setcallernumber.setText("" + CallerName);
			}
		}

	}

	private void cusatmdate(int Currenthour, int Currentmin, int usersettimeHR,
			int usersettimeMIN) {
		String currinttime = Currenthour + ":" + Currentmin;
		String usertimetime = usersettimeHR + ":" + usersettimeMIN;

		String dateStart = currinttime;
		String dateStop = usertimetime;

		// HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");

		java.util.Date d1 = null;
		java.util.Date d2 = null;

		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			// in milliseconds
			diffmillisec = d2.getTime() - d1.getTime();
			NOTIFY_INTERVAL = diffmillisec;
			long diffSeconds = diffmillisec / 1000 % 60;
			long diffMinutes = diffmillisec / (60 * 1000) % 60;
			long diffHours = diffmillisec / (60 * 60 * 1000) % 24;
			// long diffDays = diffmillisec / (24 * 60 * 60 * 1000);

			// System.out.print(diffDays + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// private void AlertDialogView() {
	// final CharSequence[] items = { "Inbox", "Send", "Failed", "Darft",
	// "otbox", "Queued" };
	// // AlertDialog.Builder builder = new
	// AlertDialog.Builder(ShowDialog.this);
	// AlertDialog.Builder builder = new AlertDialog.Builder(
	// MainActivityFackSma.this);// ERROR ShowDialog cannot be resolved
	// // to a type
	// builder.setTitle("Fack sms");
	// builder.setItems(items, new DialogInterface.OnClickListener() {
	// @Override
	// public void onClick(DialogInterface dialog, int which) {
	// switch (which) {
	// case 0:
	// SmsPlace="inbox";
	// break;
	// case 1:
	// SmsPlace="send";
	// break;
	// case 2:
	// SmsPlace="faild";
	// break;
	// }
	// }
	// });
	//
	// AlertDialog alert = builder.create();
	// alert.show();
	// }
	private void AlertDialogView() {
		final CharSequence[] items = { "Inbox", "Send", "Failed", "Darft",
				"Outbox", "Queued" };

		AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivityFackSma.this);// ERROR ShowDialog cannot be resolved
											// to a type
		builder.setTitle("Alert Dialog with ListView and Radio button");
		builder.setSingleChoiceItems(items, -1,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						switch (item) {
						case 0:
							SmsPlace = "inbox";
							// setsmsplace.setText("Inbox");
							break;
						case 1:
							SmsPlace = "send";
							// setsmsplace.setText("Send");
							break;
						case 2:
							SmsPlace = "faild";
							// setsmsplace.setText("Failed");
							break;
						case 3:
							SmsPlace = "draft";
							// setsmsplace.setText("Darft");
							break;
						case 4:
							SmsPlace = "outbox";
							// setsmsplace.setText("Outbox");
							break;
						case 5:
							SmsPlace = "queued";
							// setsmsplace.setText("Queued");
							break;
						}

						// }
					}
				});

		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// Toast.makeText(MainActivityFackSma.this, "Success",
				// Toast.LENGTH_SHORT).show();
			}
		});

		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// Toast.makeText(MainActivityFackSma.this, "Fail",
				// Toast.LENGTH_SHORT).show();
			}
		});

		AlertDialog alert = builder.create();
		alert.show();
	}

	private void addListenerRadioGroup1() {

		RadioGroup radioGroupCricket = (RadioGroup) findViewById(R.id.radio_groupsmsplace);
		Button Inbox = (Button) findViewById(R.id.Inbox);
		Inbox.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SmsPlace = "inbox";
				savedata.saveSmspalace(SmsPlace, mainActivityFackSma);
			}
		});
		Button Outbox = (Button) findViewById(R.id.Outbox);
		Outbox.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SmsPlace = "outbox";
				savedata.saveSmspalace(SmsPlace, mainActivityFackSma);

			}
		});
		Button Draftbox = (Button) findViewById(R.id.Draftbox);
		Draftbox.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SmsPlace = "draft";
				savedata.saveSmspalace(SmsPlace, mainActivityFackSma);

			}
		});
		// ==================SetTime=============
		RadioGroup grouptime = (RadioGroup) findViewById(R.id.grouptime);
		Button fivesec = (Button) findViewById(R.id.fivesec);
		fivesec.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NOTIFY_INTERVAL = 5 * 1000;

			}
		});
		Button Tensec = (Button) findViewById(R.id.tensec);
		Tensec.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NOTIFY_INTERVAL = 15 * 1000;

			}
		});
		Button fiftysec = (Button) findViewById(R.id.fiftysec);
		fiftysec.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NOTIFY_INTERVAL = 30 * 1000;

			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		/*if (Startmenuscreen.ISConnectedWifi) {
			Startmenuscreen.startAppAd.showAd();
			Startmenuscreen.startAppAd.loadAd();
		}*/
	}
}
