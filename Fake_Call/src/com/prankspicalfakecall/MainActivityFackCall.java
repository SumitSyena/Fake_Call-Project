package com.prankspicalfakecall;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.xml.sax.Attributes;
import org.xml.sax.DTDHandler;

import com.fackgirlfraind.database.Contact;
import com.fackgirlfraind.database.DataBaseHandler;
import com.fakegirlfraind.grlyview.GalleryViewActivity;
import com.racinggamelabs.fakecallgirlfriendboyfriend.R;
import com.prankspicalfakesma.MainActivityFackSma;
import com.sm.ads.AdsLoadingListner;
import com.sm.ads.SMAdsConfig;
import com.sm.fullAds.FullAdsBridge;

import android.content.SharedPreferences;
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
import android.os.Handler;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.provider.Settings;
import android.text.format.Time;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import static com.prankspicalfakecall.Startmenuscreen.fulladds;

public class MainActivityFackCall extends Activity {
	MainActivityFackCall mainActivityFackCalls;

	DataBaseHandler db;
	File finalFile;
	private long startTime = 0L;
	long timeInMillies = 0L;
	long timeSwap = 0L;
	long finalTime = 0L;
	private Handler myHandler = new Handler();
	TextView voivetimer;
	Button startrecord, stoprecrd, playvoice, pausevoice, Exit;
	Bitmap databasebitmap;
	// public static int Calldutration;
	// public static boolean controalvibration;
	public static boolean contrecording = false;
	// public static boolean recoredvoic = false;
	public MediaRecorder myAudioRecorder;
	public  static int m=0,n=0,o=0;
	// public String outputFile;

	final private static int DIALOG_LOGIN = 1;
	private boolean clickcontctbtn = false, clickcontvoicbtn = false,
			clickuserimagevoicbtn, clickcringbtn = false;;
	private static final int PICK_FROM_GALLERY = 1;
	private static final int PICK_FROM_CAMERA = 2;
	public static boolean calltimet = false;
	public static long NOTIFY_INTERVAL = 0;
	public int PICK_CONTACT;
	int REQ_CODE_PICK_SONG;
	TextView setcallername, setcallernumber, notiftitext;
	public String CallerName, CallerNumber,callerimage;
	public  static  ImageView setcallerimage, setcontectnumber, reordicon;
	public static MainActivityFackCall mainActivityFackCall;
	public Bitmap bitmap;
	private Calendar calendar;
	private String format = "";
	private TimePicker timePicker1;
	int Currenthour, Currentmin;
	private int mHour, mMinute;
	Button Custamtimesate, setvoice, setringtone, setVon, setVoff, calltime,selectphone,settimer,forward,backward,upload,camera;
	String ringToneName;
	long diffmillisec;
	MediaPlayer mPlayer = new MediaPlayer();
	public Uri ringtone;
	MainActivity mainActivity;
	private PopupWindow pwindo;
	Savedata savedata;
	Button fivesec, fiften, thrtysec, onemin,thitymint;
	RadioGroup radioGroupCricket;
	AlertDialog dialog;
	String gender;
	private String selectedImagePath;
	static int i=0;
	private static final int SELECT_PICTURE = 1;
	private String caller = "";
	SharedPreferences preferences;
	public static FullAdsBridge fullAdsBridge;
	public static boolean isLibReady = false;
	public static boolean splaseadds = true;
    Context context;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fackcallma);
		//mainActivityFackCalls=this;
        context=getApplicationContext();
		preferences = getSharedPreferences("PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		//SharedPreferences.Editor editor = preferences.edit();
		//editor.putString("USERNAME","");
		//editor.putString("USERPHONE","");
		//editor.commit();

		db = new DataBaseHandler(this);
		if (Startmenuscreen.ISConnectedWifi) {
		//	fulladds(mainActivityFackCalls);
		}

		if (Build.VERSION.SDK_INT >= 11) {
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
			ActionBar actionBar = getActionBar();
			actionBar.hide();
		}
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			caller = extras.getString("caller");
			if (caller == null) caller = "";
		}
		mainActivityFackCall = this;
		savedata = Savedata.getInstance();
		//addListenerRadioGroup1();
		calendar = Calendar.getInstance();
		Currenthour = calendar.get(Calendar.HOUR_OF_DAY);
		Currentmin = calendar.get(Calendar.MINUTE);

		// ==================Data Get======================
		savedata.saveVibration(savedata.controalvibration, mainActivityFackCall);
		savedata.Callername = savedata.getusername(mainActivityFackCall);
		savedata.CallerNumber = savedata.getuserNumber(mainActivityFackCall);
		boolean s = savedata.getVibration(mainActivityFackCall);
		savedata.saveRingTone("", mainActivityFackCall);
		savedata.RingTone = savedata.getRingTone(mainActivityFackCall);
		savedata.saveImage("", mainActivityFackCall);
		savedata.Image = savedata.getImage(mainActivityFackCall);
		savedata.saveBrowsevoice("", mainActivityFackCall);
		savedata.browsevoice = savedata.getBrowsevoice(mainActivityFackCall);
		savedata.saveCalldutraton(1200000, mainActivityFackCall);
		savedata.CallDutratiov = savedata.getCalldutraton(mainActivityFackCall);
		savedata.saveRecordVoice("", mainActivityFackCall);
		savedata.outputFile = savedata.getRecordVoice(mainActivityFackCall);
		// ==================End Data Get======================

		setcallername = (TextView) findViewById(R.id.setcallername);
		setcallernumber = (TextView) findViewById(R.id.setcallernumber);

		setcallerimage = (ImageView) findViewById(R.id.setcallerimage);

		settimer = (Button) findViewById(R.id.settimer);

		setvoice = (Button) findViewById(R.id.setvoice);
		setringtone = (Button) findViewById(R.id.setringtone);
		setcontectnumber = (ImageView) findViewById(R.id.setcontectpic2);

		setVoff = (Button) findViewById(R.id.setvibrationoff);
		calltime = (Button) findViewById(R.id.calltime);
		setVon = (Button) findViewById(R.id.setvibration);
		setVon.setVisibility(View.GONE);
		setirlfraincall();
		stopService((new Intent(MainActivityFackCall.this,
				FackCallServies.class)));
		settimer = (Button) findViewById(R.id.settimer);

		//if(preferences)
		setcallername.setText(preferences.getString("USERNAME", ""));
		setcallernumber.setText(preferences.getString("USERPHONE", ""));
		String s3 = preferences.getString("USERIMAGECAMERA", "GOOD");
		System.out.println("hi" + s3);
		String s2 = preferences.getString("USERIMAGEGALLERY", "good");
		System.out.println("hi" + s2);
		if (o == 1){
			if (selectedImagePath != "good") {
				//s*//*etcallerimage.setImageBitmap(preferences.getString("USERIMAGEGALLERY",""));
				try {
					System.out.println("hihello" + s2);
					//byte[] encodeByte = Base64.decode(s2, Base64.DEFAULT);
					Bitmap bitmap2 = BitmapFactory.decodeFile(s2);
					setcallerimage.setImageBitmap(bitmap2);
				} catch (Exception e) {
					e.getMessage();
				}
			}
	}
		if (n == 1) {
			if (s3 != "GOOD") {
				//s*//**//*etcallerimage.setImageBitmap(preferences.getString("USERIMAGEGALLERY",""));
				try {
					System.out.println("hihello" + s3);
					//byte[] encodeByte = Base64.decode(s2, Base64.DEFAULT);
					Bitmap bitmap3 = BitmapFactory.decodeFile(s3);
					setcallerimage.setImageBitmap(bitmap3);
				} catch (Exception e) {
					e.getMessage();
				}
			}
		}


		//String position=preferences.getString("USERIMAGE","");

		//setcallerimage.setImageBitmap(bitmap);
		String position=preferences.getString("USERIMAGE","");
		/*if(position.equalsIgnoreCase("1")) {
			setcallerimage.setImageResource(R.drawable.girls9);
		}*/if(m==1) {

			if (i == 1) {

				setcallerimage.setImageResource(R.drawable.girls1);

			} else if (i == 2) {
				setcallerimage.setImageResource(R.drawable.girls2);
			} else if (i == 3) {
				setcallerimage.setImageResource(R.drawable.girls3);
			}
			else if (i== 4) {

				setcallerimage.setImageResource(R.drawable.girls4);

			} else if (i == 5) {
				setcallerimage.setImageResource(R.drawable.girls5);

			}
			else if (i== 6) {

				setcallerimage.setImageResource(R.drawable.girls6);
			} else if (i== 7) {
				setcallerimage.setImageResource(R.drawable.girls7);

			}
			else if (i== 8) {

				setcallerimage.setImageResource(R.drawable.girls8);

			} else if (i== 9) {
				setcallerimage.setImageResource(R.drawable.girls9);

			}
		}

		settimer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Startmenuscreen.ISConnectedWifi) {
				Startmenuscreen.fulladds(MainActivityFackCall.this);
				}
				displayAlertDialog();


			/*
				// Create custom dialog object
				final Dialog dialog = new Dialog(MainActivityFackCall.this);
				// Include dialog.xml file
				dialog.setContentView(R.layout.set_timer_custom_alert);
				// Set dialog title
				// set values for custom dialog components - text, image and button


				dialog.show();
				Button declineButton = (Button) dialog.findViewById(R.id.no);
				declineButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}

				});
				Custamtimesate = (Button) dialog.findViewById(R.id.customtime);
				Custamtimesate.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						Calendar mcurrentTime = Calendar.getInstance();
						final int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
						final int minute = mcurrentTime.get(Calendar.MINUTE);
						TimePickerDialog mTimePicker;
						mTimePicker = new TimePickerDialog(mainActivityFackCall,
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

			}*/
			}
		});
		/*if(caller.equalsIgnoreCase("SelectPhoneActivity")){
			imageView.setImageResource(SelectPhoneActivity.selectedTheme_resID);
		}*/
		//imageView= (ImageView) findViewById(R.id.setcallerimage);
		selectphone=(Button)findViewById(R.id.selectphone);
		selectphone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (Startmenuscreen.ISConnectedWifi) {
					//fulladds(mainActivityFackCalls);
				}
				SharedPreferences.Editor editor = preferences.edit();
				editor.putString("USERNAME",setcallername.getText().toString());
				editor.putString("USERPHONE",setcallernumber.getText().toString());
				editor.putString("USERIMAGE",""+i);
				editor.putString("USERIMAGEGALLERY",selectedImagePath);
				editor.putString("USERIMAGECAMERA", String.valueOf(finalFile));
				//System.out.println("hello"+selectedImagePath);

				editor.commit();
				Intent intent =new Intent(MainActivityFackCall.this,SelectPhoneActivity.class);
				startActivity(intent);
				finish();

			}
		});
		forward=(Button)findViewById(R.id.forward);
		forward.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			//fulladds(MainActivityFackCall.this);


				m = 1;
				n = 0;
				o = 0;
				if (i < 10) {
					i++;

					if (i == 1) {
						System.out.println("Ram1: " + i);
						setcallerimage.setImageResource(R.drawable.girls1);

					} else if (i == 2) {
						System.out.println("Ram2: " + i);
						setcallerimage.setImageResource(R.drawable.girls2);
					} else if (i == 3) {
						System.out.println("Ram3: " + i);
						setcallerimage.setImageResource(R.drawable.girls3);

					}
					else if (i == 4) {
						System.out.println("Ram4: " + i);
						setcallerimage.setImageResource(R.drawable.girls4);
					} else if (i == 5) {
						System.out.println("Ram5: " + i);
						setcallerimage.setImageResource(R.drawable.girls5);
					}
					else if (i == 6) {
						System.out.println("Ram6: " + i);
						setcallerimage.setImageResource(R.drawable.girls6);
					} else if (i == 7) {
						System.out.println("Ram7: " + i);
						setcallerimage.setImageResource(R.drawable.girls7);
					}
					else if (i == 8) {
						System.out.println("Ram8: " + i);
						setcallerimage.setImageResource(R.drawable.girls8);
					} else if (i == 9) {
						System.out.println("Ram9: " + i);
						setcallerimage.setImageResource(R.drawable.girls9);

					}
				}
				else {
					Toast.makeText(getApplicationContext(),"Please press back Button ",Toast.LENGTH_SHORT).show();
					i=9;
				}
			}


		});
		backward=(Button)findViewById(R.id.backward);
		backward.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
              m=1;
				n=0;
				o=0;
				i--;
				if (i == 8) {
					setcallerimage.setImageResource(R.drawable.girls8);
				} else if (i == 7) {
					setcallerimage.setImageResource(R.drawable.girls7);
				}
				else if (i == 6) {

					setcallerimage.setImageResource(R.drawable.girls6);
				} else if (i == 5) {
					setcallerimage.setImageResource(R.drawable.girls5);
				}
				else if (i == 4) {

					setcallerimage.setImageResource(R.drawable.girls4);
				} else if (i == 3) {
					setcallerimage.setImageResource(R.drawable.girls3);
				}
				else if (i == 2) {

					setcallerimage.setImageResource(R.drawable.girls2);
				} else if (i == 1) {
					setcallerimage.setImageResource(R.drawable.girls1);

				}

			}

		});
		upload=(Button)findViewById(R.id.upload);
		upload.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				o=1;
				m=0;
				n=0;
				/*Intent intent = new Intent();
				intent.setType("image*//*");
				intent.setAction(Intent.ACTION_GET_CONTENT);
				startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
*/              callGallery();
			}
		});
		camera= (Button)findViewById(R.id.camera);
		camera.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
              m=0;
				n=1;
				o=0;
				/*Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(cameraIntent, PICK_FROM_CAMERA);*/
				callCamera();
			}
		});
		final Button start = (Button) findViewById(R.id.start);

		start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (!Savedata.savedata.getIsgrlly(mainActivityFackCall)
						&& bitmap != null) {
					dialog.show();
				} else {
					CallerName = setcallername.getText().toString();
					CallerNumber = setcallernumber.getText().toString();
					com.prankspicalfakecall.Menuactivity
							.manageNavigation("Menu");
					if (CallerName.equalsIgnoreCase("")) {
						Toast.makeText(getApplicationContext(),
								"please enter name", Toast.LENGTH_SHORT).show();
					} else if (CallerNumber.equalsIgnoreCase("")) {
						Toast.makeText(getApplicationContext(),
								"please enter number", Toast.LENGTH_SHORT)
								.show();
					} else if (NOTIFY_INTERVAL == 0) {
						Toast.makeText(getApplicationContext(),
								"please enter time", Toast.LENGTH_SHORT).show();
					} else if (CallerName != null && NOTIFY_INTERVAL != 0
							&& CallerNumber != null) {

						CallerName = setcallername.getText().toString();
						CallerNumber = setcallernumber.getText().toString();
						if(SelectPhoneActivity.selectedTheme_resID == R.drawable.s1){
							savedata.saveLayoutNumber(1,mainActivityFackCall);
						}
						else if(SelectPhoneActivity.selectedTheme_resID == R.drawable.s2){
							savedata.saveLayoutNumber(2,mainActivityFackCall);
						}
						else if(SelectPhoneActivity.selectedTheme_resID == R.drawable.s3){
							savedata.saveLayoutNumber(3,mainActivityFackCall);
						}
						else if(SelectPhoneActivity.selectedTheme_resID == R.drawable.s4){
							savedata.saveLayoutNumber(4,mainActivityFackCall);
						}
						else if(SelectPhoneActivity.selectedTheme_resID == R.drawable.s5){
							savedata.saveLayoutNumber(5,mainActivityFackCall);
						}
						else if(SelectPhoneActivity.selectedTheme_resID == R.drawable.s6){
							savedata.saveLayoutNumber(6,mainActivityFackCall);
						}
						else if(SelectPhoneActivity.selectedTheme_resID == R.drawable.s7){
							savedata.saveLayoutNumber(7,mainActivityFackCall);
						}
						else if(SelectPhoneActivity.selectedTheme_resID == R.drawable.s8){
							savedata.saveLayoutNumber(8,mainActivityFackCall);
						}
						else if(SelectPhoneActivity.selectedTheme_resID == R.drawable.s9){
							savedata.saveLayoutNumber(9,mainActivityFackCall);
						}
						else if(SelectPhoneActivity.selectedTheme_resID == R.drawable.same10){
							savedata.saveLayoutNumber(10,mainActivityFackCall);
						}
						calltimet = true;
						startService(new Intent(MainActivityFackCall.this,FackCallServies.class));
						savedata.saveusername(CallerName, mainActivityFackCall);
						savedata.saveuserNumber(CallerNumber,mainActivityFackCall);
						//  savedata.saveImage(setcallerimage,mainActivityFackCall);


					/*	if (Startmenuscreen.ISConnectedWifi) {
							Startmenuscreen.startAppAd.showAd();
							Startmenuscreen.startAppAd.loadAd();
						}*/

						if (Savedata.savedata.getIsgrlly(mainActivityFackCall)) {
							Contact singleContact = Menuactivity.imageArry.get(savedata
									.getgalleryposition(mainActivityFackCall));
							singleContact.setUsername(CallerName);
							singleContact.setUsernumber(CallerNumber);
							singleContact.setUserTime("" + NOTIFY_INTERVAL);
							singleContact.setUserCallduration(""
									+ savedata
									.getCalldutraton(mainActivityFackCall));
							singleContact.setUserRingtone(""
									+ savedata
									.getRingTone(mainActivityFackCall));
							singleContact.setUserRingtone_Name(ringToneName);
							singleContact.setUserVibration(""
									+ savedata.controalvibration);
							db.updateContact(singleContact);
						}
						Toast.makeText(getApplicationContext(),
								"Setting Saved", Toast.LENGTH_SHORT).show();

						Intent intent = new Intent(Intent.ACTION_MAIN);
						intent.addCategory(Intent.CATEGORY_HOME);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(intent);
						finish();

					}
				}
			}

		});


		setVoff.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				setVoff.setVisibility(View.GONE);
				setVon.setVisibility(View.VISIBLE);
				savedata.controalvibration = true;
				savedata.saveVibration(savedata.controalvibration,
						mainActivityFackCall);
			}
		});
		setVon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				setVoff.setVisibility(View.VISIBLE);
				setVon.setVisibility(View.GONE);
				savedata.controalvibration = false;
				savedata.saveVibration(savedata.controalvibration,
						mainActivityFackCall);
			}
		});
		setringtone.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (Startmenuscreen.ISConnectedWifi) {
					//fulladds(mainActivityFackCalls);
				}
				{

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

			}
		});
		calltime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (Startmenuscreen.ISConnectedWifi) {
				//	fulladds(mainActivityFackCalls);
				}


					AlertDialogView();

			}
		});
		/*setcallerimage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (!savedata.getIsgrlly(mainActivityFackCall)) {
					if (Startmenuscreen.ISConnectedWifi) {
						Startmenuscreen.startAppAd.showAd();
						Startmenuscreen.startAppAd.loadAd();
					} else {
						com.prankspicalfakecall.Menuactivity
								.manageNavigation("GalleryViewActivity");
					}

					picphotofrom();
				}
				// callCamera();
			}
		});*/
		setcontectnumber.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				if (Startmenuscreen.ISConnectedWifi) {
					//fulladds(mainActivityFackCalls);
				}
					Intent pickContactIntent = new Intent(Intent.ACTION_PICK,
							ContactsContract.Contacts.CONTENT_URI);
					pickContactIntent
							.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
					startActivityForResult(pickContactIntent, 3);
					clickcontctbtn = true;
			}
		});

		setvoice.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Startmenuscreen.ISConnectedWifi) {
					//fulladds(mainActivityFackCalls);
				}
					com.prankspicalfakecall.Menuactivity
							.manageNavigation("Menu");

					pupepMenu();

			}
		});

		final String[] option = new String[] { "Fake Boyfriend",
				"Fake Girlfriend", "Call without save" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.select_dialog_item, option);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle("Want to save this in profile");
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.e("Selected Item", String.valueOf(which));
				if (which == 0) {
					gender = "boy";
					saverecordindatabase();
					Toast.makeText(getApplicationContext(), "Setting Saved",
							Toast.LENGTH_SHORT).show();
					CallerName = setcallername.getText().toString();
					CallerNumber = setcallernumber.getText().toString();
					com.prankspicalfakecall.Menuactivity
							.manageNavigation("Menu");
					if (CallerName.equalsIgnoreCase("")) {
						Toast.makeText(getApplicationContext(),
								"please enter name", Toast.LENGTH_SHORT).show();
					} else if (CallerNumber.equalsIgnoreCase("")) {
						Toast.makeText(getApplicationContext(),
								"please enter number", Toast.LENGTH_SHORT)
								.show();
					} else if (NOTIFY_INTERVAL == 0) {
						Toast.makeText(getApplicationContext(),
								"please enter time", Toast.LENGTH_SHORT).show();
					} else if (CallerName != null && NOTIFY_INTERVAL != 0
							&& CallerNumber != null) {
						CallerName = setcallername.getText().toString();
						CallerNumber = setcallernumber.getText().toString();
						calltimet = true;
						startService(new Intent(MainActivityFackCall.this,
								FackCallServies.class));
						savedata.saveusername(CallerName, mainActivityFackCall);
						savedata.saveuserNumber(CallerNumber,
								mainActivityFackCall);
						savedata.saveImage(selectedImagePath,mainActivityFackCall);

						/*f (Startmenuscreen.ISConnectedWifi) {
							Startmenuscreen.startAppAd.showAd();
							Startmenuscreen.startAppAd.loadAd();
						}*/

					}

					finish();
				} else if (which == 1) {
					gender = "girl";
					saverecordindatabase();
					Toast.makeText(getApplicationContext(), "Setting Saved",
							Toast.LENGTH_SHORT).show();
					CallerName = setcallername.getText().toString();
					CallerNumber = setcallernumber.getText().toString();
					com.prankspicalfakecall.Menuactivity
							.manageNavigation("Menu");
					if (CallerName.equalsIgnoreCase("")) {
						Toast.makeText(getApplicationContext(),
								"please enter name", Toast.LENGTH_SHORT).show();
					} else if (CallerNumber.equalsIgnoreCase("")) {
						Toast.makeText(getApplicationContext(),
								"please enter number", Toast.LENGTH_SHORT)
								.show();
					} else if (NOTIFY_INTERVAL == 0) {
						Toast.makeText(getApplicationContext(),
								"please enter time", Toast.LENGTH_SHORT).show();
					} else if (CallerName != null && NOTIFY_INTERVAL != 0
							&& CallerNumber != null) {
						CallerName = setcallername.getText().toString();
						CallerNumber = setcallernumber.getText().toString();
						calltimet = true;
						startService(new Intent(MainActivityFackCall.this,
								FackCallServies.class));
						savedata.saveusername(CallerName, mainActivityFackCall);
						savedata.saveuserNumber(CallerNumber,
								mainActivityFackCall);
/*

						if (Startmenuscreen.ISConnectedWifi) {
							Startmenuscreen.startAppAd.showAd();
							Startmenuscreen.startAppAd.loadAd();
						}
*/

					}

					Intent intent = new Intent(Intent.ACTION_MAIN);
					intent.addCategory(Intent.CATEGORY_HOME);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
					finish();
				} else if (which == 2) {
					CallerName = setcallername.getText().toString();
					CallerNumber = setcallernumber.getText().toString();
					com.prankspicalfakecall.Menuactivity
							.manageNavigation("Menu");
					if (CallerName.equalsIgnoreCase("")) {
						Toast.makeText(getApplicationContext(),
								"please enter name", Toast.LENGTH_SHORT).show();
					} else if (CallerNumber.equalsIgnoreCase("")) {
						Toast.makeText(getApplicationContext(),
								"please enter number", Toast.LENGTH_SHORT)
								.show();
					} else if (NOTIFY_INTERVAL == 0) {
						Toast.makeText(getApplicationContext(),
								"please enter time", Toast.LENGTH_SHORT).show();
					} else if (CallerName != null && NOTIFY_INTERVAL != 0
							&& CallerNumber != null) {
						CallerName = setcallername.getText().toString();
						CallerNumber = setcallernumber.getText().toString();

						calltimet = true;
						startService(new Intent(MainActivityFackCall.this,
								FackCallServies.class));
						savedata.saveusername(CallerName, mainActivityFackCall);
						savedata.saveuserNumber(CallerNumber,
								mainActivityFackCall);

					/*	if (Startmenuscreen.ISConnectedWifi) {
							Startmenuscreen.startAppAd.showAd();
							Startmenuscreen.startAppAd.loadAd();
						}*/

					}
					Toast.makeText(getApplicationContext(), "Setting Saved",
							Toast.LENGTH_SHORT).show();

					Intent intent = new Intent(Intent.ACTION_MAIN);
					intent.addCategory(Intent.CATEGORY_HOME);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intent);
					finish();
					dialog.cancel();
				}
			}
		});
		dialog = builder.create();
	}

	private void displayAlertDialog() {
		LayoutInflater inflater = getLayoutInflater();
		View alertLayout = inflater.inflate(R.layout.set_timer_custom_alert, null);

		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("hi");
		alert.setIcon(R.drawable.set);
		alert.setView(alertLayout);
		alert.setCancelable(false);

		alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getBaseContext(), "No clicked", Toast.LENGTH_SHORT).show();
			}
		});
		alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getBaseContext(), "Time saved", Toast.LENGTH_SHORT).show();
			}
		});

		Custamtimesate = (Button) alertLayout.findViewById(R.id.setcustamtime22);
		Custamtimesate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Calendar mcurrentTime = Calendar.getInstance();
				final int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
				final int minute = mcurrentTime.get(Calendar.MINUTE);
				TimePickerDialog mTimePicker;
				mTimePicker = new TimePickerDialog(mainActivityFackCall,
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
		radioGroupCricket = (RadioGroup) alertLayout.findViewById(R.id.radioGroup1);
		// radioGroupCricket.setClickable(clickable)
		fivesec = (Button) alertLayout.findViewById(R.id.radioGroupButton0);
		fivesec.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NOTIFY_INTERVAL = 10* 1000;
				radioGroupCricket.setVisibility(View.VISIBLE);

			}
		});
		fiften = (Button) alertLayout.findViewById(R.id.radioGroupButton1);
		fiften.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NOTIFY_INTERVAL = 30 * 1000;
				radioGroupCricket.setVisibility(View.VISIBLE);
				radioGroupCricket.setVisibility(View.VISIBLE);

			}
		});
		thrtysec = (Button) alertLayout.findViewById(R.id.radioGroupButton3);
		thrtysec.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NOTIFY_INTERVAL = 60 * 1000;
				radioGroupCricket.setVisibility(View.VISIBLE);

			}
		});
		onemin = (Button) alertLayout.findViewById(R.id.radioGroupButton4);
		onemin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NOTIFY_INTERVAL = 900 * 1000;
				radioGroupCricket.setVisibility(View.VISIBLE);

			}
		});
		thitymint = (Button) alertLayout.findViewById(R.id.radioGroupButton5);
		thitymint.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NOTIFY_INTERVAL = 900 * 1000;
				radioGroupCricket.setVisibility(View.VISIBLE);

			}
		});

		AlertDialog dialog = alert.create();
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();

		wmlp.gravity = Gravity.BOTTOM | Gravity.LEFT;
		wmlp.x = 650;   //x position
		wmlp.y = 135;   //y positio
		dialog.show();
		dialog.getWindow().setLayout(500, 700);


	}

	/*private void addListenerRadioGroup1() {

		radioGroupCricket = (RadioGroup) findViewById(R.id.radioGroup1);
		// radioGroupCricket.setClickable(clickable)
		fivesec = (Button) findViewById(R.id.radioGroupButton0);
		fivesec.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NOTIFY_INTERVAL = 5 * 1000;
				radioGroupCricket.setVisibility(View.VISIBLE);

			}
		});
		fiften = (Button) findViewById(R.id.radioGroupButton1);
		fiften.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NOTIFY_INTERVAL = 15 * 1000;
				radioGroupCricket.setVisibility(View.VISIBLE);
				radioGroupCricket.setVisibility(View.VISIBLE);

			}
		});
		thrtysec = (Button) findViewById(R.id.radioGroupButton3);
		thrtysec.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NOTIFY_INTERVAL = 30 * 1000;
				radioGroupCricket.setVisibility(View.VISIBLE);

			}
		});
		onemin = (Button) findViewById(R.id.radioGroupButton4);
		onemin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				NOTIFY_INTERVAL = 60 * 1000;
				radioGroupCricket.setVisibility(View.VISIBLE);

			}
		});
	}*/

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
		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(cameraIntent, PICK_FROM_CAMERA);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && clickuserimagevoicbtn) {
			Bundle extras2 = data.getExtras();
			if (extras2 != null) {
				bitmap = extras2.getParcelable("data");
				setcallerimage.setImageBitmap(bitmap);
				clickuserimagevoicbtn = false;

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
				byte[] b = baos.toByteArray();
				savedata.saveImage(Base64.encodeToString(b, Base64.DEFAULT),
						mainActivityFackCall);

			}
		}

		if (resultCode == RESULT_OK) {
			if (requestCode == SELECT_PICTURE) {
				Uri selectedImageUri = data.getData();
				selectedImagePath = getPathOfImage(selectedImageUri);
				System.out.println("Image Path : " + selectedImagePath);
				setcallerimage.setImageURI(selectedImageUri);

			}
		}

	/*	if (requestCode == PICK_FROM_CAMERA && resultCode == RESULT_OK) {
			Bitmap photo = (Bitmap) data.getExtras().get("data");
			setcallerimage.setImageBitmap(photo);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			//bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
			byte[] b = baos.toByteArray();
			savedata.saveImage(Base64.encodeToString(b,Base64.DEFAULT),mainActivityFackCall);
		}*/
		if (requestCode == PICK_FROM_CAMERA && resultCode == RESULT_OK) {
			Bitmap photo = (Bitmap) data.getExtras().get("data");
			setcallerimage.setImageBitmap(photo);
			//knop.setVisibility(Button.VISIBLE);


			// CALL THIS METHOD TO GET THE URI FROM THE BITMAP
			Uri tempUri = getImageUri2(getApplicationContext(), photo);

			// CALL THIS METHOD TO GET THE ACTUAL PATH
			finalFile = new File(getRealPathFromURI2(tempUri));

			System.out.println("togood"+finalFile);
		}


		if (resultCode == RESULT_OK && clickuserimagevoicbtn) {
			Bundle extras = data.getExtras();
			if (extras != null) {
				bitmap = extras.getParcelable("data");
				setcallerimage.setImageBitmap(bitmap);
				clickuserimagevoicbtn = false;

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
				byte[] b = baos.toByteArray();

				savedata.saveImage(Base64.encodeToString(b, Base64.DEFAULT),
						mainActivityFackCall);

			}
		}
		if (resultCode == RESULT_OK && clickcontvoicbtn) {
			Log.d("result picked song: ", data.toString());
			Uri contactData = data.getData();

			try {
				Cursor c = managedQuery(contactData, null, null, null, null);
				// setvoice(contactData);
				savedata.saveBrowsevoice(contactData.toString(),
						mainActivityFackCall);
				clickcontvoicbtn = false;
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		if (resultCode == RESULT_OK && clickcringbtn) {
			if (Startmenuscreen.ISConnectedWifi) {
			//	fulladds(this);
			}
				com.prankspicalfakecall.Menuactivity.manageNavigation("Menu");


			ringtone = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            if(ringtone==null)
            {
                Toast.makeText(getApplicationContext(),"Please select an t",Toast.LENGTH_LONG).show();
                return;
            }
			Ringtone r = RingtoneManager.getRingtone(this, ringtone);
			ringToneName = r.getTitle((this));

			savedata.saveRingTone(ringtone.toString(), mainActivityFackCall);

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
				setcallername.setText("" + CallerName);
				setcallernumber.setText("" + CallerNumber);
			}
		}

	}

	public Uri getImageUri2(Context inContext, Bitmap inImage) {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
		String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
		return Uri.parse(path);
	}
	public String getRealPathFromURI2(Uri uri) {
		Cursor cursor = getContentResolver().query(uri, null, null, null, null);
		cursor.moveToFirst();
		int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
		return cursor.getString(idx);
	}

	private String getPathOfImage(Uri selectedImageUri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(selectedImageUri, projection, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	public void setvoice(Uri contactData) {
		Uri myUri1 = Uri.parse("" + contactData);
		mPlayer = new MediaPlayer();
		// String title = mPlayer.get
		mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		try {
			mPlayer.setDataSource(getApplicationContext(), myUri1);
		} catch (IllegalArgumentException e) {
			Toast.makeText(getApplicationContext(),
					"You might not set the URI correctly!", Toast.LENGTH_LONG)
					.show();
		} catch (SecurityException e) {
			Toast.makeText(getApplicationContext(),
					"You might not set the URI correctly!", Toast.LENGTH_LONG)
					.show();
		} catch (IllegalStateException e) {
			Toast.makeText(getApplicationContext(),
					"You might not set the URI correctly!", Toast.LENGTH_LONG)
					.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			mPlayer.prepare();
		} catch (IllegalStateException e) {
			Toast.makeText(getApplicationContext(),
					"You might not set the URI correctly!", Toast.LENGTH_LONG)
					.show();
		} catch (IOException e) {
			Toast.makeText(getApplicationContext(),
					"You might not set the URI correctly!", Toast.LENGTH_LONG)
					.show();
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

			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// =====dilog
	@Override
	protected Dialog onCreateDialog(int id) {

		AlertDialog dialogDetails = null;

		switch (id) {
			case DIALOG_LOGIN:
				LayoutInflater inflater = LayoutInflater.from(this);
				View dialogview = inflater.inflate(R.layout.comment_layout, null);

				AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
				dialogbuilder.setTitle("Record voice");
				dialogbuilder.setView(dialogview);
				dialogDetails = dialogbuilder.create();

				break;
		}

		return dialogDetails;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {

		switch (id) {
			case DIALOG_LOGIN:
				final AlertDialog alertDialog = (AlertDialog) dialog;
				Button loginbutton = (Button) alertDialog.findViewById(R.id.btn_ok);
				Button cancelbutton = (Button) alertDialog
						.findViewById(R.id.btn_cancel);
				notiftitext = (TextView) alertDialog.findViewById(R.id.textnoti);
				reordicon = (ImageView) alertDialog.findViewById(R.id.recordvoice);
				reordicon.setVisibility(View.INVISIBLE);
				loginbutton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						notiftitext.setVisibility(View.INVISIBLE);
						reordicon.setVisibility(View.VISIBLE);
						start(v);

					}
				});

				cancelbutton.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						try {
							if (contrecording) {
								stop();
								notiftitext.setVisibility(View.VISIBLE);
								notiftitext.setText("Audio recorded successfully");
								setvoice.setText("Faxkcall.3gp");
							}

							alertDialog.dismiss();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (IllegalStateException e) {
							e.printStackTrace();
						}

					}
				});
				break;
		}
	}

	public void pupepMenu() {
		CharSequence colors[] = new CharSequence[] { "Record voice", "Browse", };

		AlertDialog.Builder builder = new AlertDialog.Builder(
				mainActivityFackCall);
		builder.setTitle("Fake Call SMS");
		builder.setItems(colors, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
					case 0:
						initiatePopupWindow();
						break;
					case 1:
						Intent i = new Intent();
						i.setAction(android.content.Intent.ACTION_PICK);
						i.setData(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
						startActivityForResult(i, REQ_CODE_PICK_SONG);
						clickcontvoicbtn = true;
						System.out.println("onClick DisLike");
						break;
					case 2:

						System.out.println("onClick Profile");
						break;
				}
			}
		});
		builder.show();
	}

	public void start(View view) {
		savedata.outputFile = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/Faxkcall.3gp";
		myAudioRecorder = new MediaRecorder();
		myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
		myAudioRecorder.setOutputFile(savedata.outputFile);

		try {
			myAudioRecorder.prepare();
			myAudioRecorder.start();

		} catch (IllegalStateException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		Toast.makeText(getApplicationContext(), "Recording started",
				Toast.LENGTH_LONG).show();
		contrecording = true;

	}

	public void pause() throws IllegalArgumentException, SecurityException,
			IllegalStateException, IOException {

		MediaPlayer m = new MediaPlayer();
		m.setDataSource(savedata.outputFile);
		m.prepare();
		m.pause();

	}

	public void Stopplayer() throws IllegalArgumentException,
			SecurityException, IllegalStateException, IOException {

		MediaPlayer m = new MediaPlayer();
		m.setDataSource(savedata.outputFile);
		m.prepare();
		m.stop();
	}

	public void stop() {
		myAudioRecorder.stop();
		myAudioRecorder.release();
		myAudioRecorder = null;

		// stop.setEnabled(false);
		// play.setEnabled(true);
		setvoice.setText("Faxkcall.3gp");
		savedata.saveRecordVoice(savedata.outputFile, mainActivityFackCall);
		Toast.makeText(getApplicationContext(), "Audio recorded successfully",
				Toast.LENGTH_LONG).show();
	}

	private void AlertDialogView() {
		final CharSequence[] items = { "5 Sec", "10 Sec", "15 Sec", "45 Sec",
				"1 Min", "2 Min" };

		AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivityFackCall.this);// ERROR ShowDialog cannot be
		// resolved
		// to a type
		builder.setTitle("Fake Call SMS");
		builder.setSingleChoiceItems(items, -1,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						switch (item) {
							case 0:
								calltime.setText("5 Sec");
								// Calldutration = 5 * 1000;
								savedata.saveCalldutraton(5 * 1000,
										mainActivityFackCall);
								break;
							case 1:
								// Calldutration = 10 * 1000;
								savedata.saveCalldutraton(10 * 1000,
										mainActivityFackCall);
								calltime.setText("10 Sec");
								break;
							case 2:
								// Calldutration = 15 * 1000;
								savedata.saveCalldutraton(15 * 1000,
										mainActivityFackCall);
								calltime.setText("15 Sec");
								break;
							case 3:
								// Calldutration = 45 * 1000;
								savedata.saveCalldutraton(45 * 1000,
										mainActivityFackCall);
								calltime.setText("45 Sec");
								break;
							case 4:
								// Calldutration = 60 * 1000;
								savedata.saveCalldutraton(45 * 1000,
										mainActivityFackCall);
								calltime.setText("1 Min");
								break;
							case 5:
								// Calldutration = 120 * 1000;
								savedata.saveCalldutraton(120 * 1000,
										mainActivityFackCall);
								calltime.setText("2 Min");
								break;
						}

						// }
					}
				});

		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				/*if (Startmenuscreen.ISConnectedWifi) {
					Startmenuscreen.startAppAd.showAd();
					Startmenuscreen.startAppAd.loadAd();
				} else {*/
					com.prankspicalfakecall.Menuactivity
							.manageNavigation("Menu");

			}
		});

		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
//				if (Startmenuscreen.ISConnectedWifi) {
//					Startmenuscreen.startAppAd.showAd();
//					Startmenuscreen.startAppAd.loadAd();
//				} else {
					com.prankspicalfakecall.Menuactivity
							.manageNavigation("Menu");

			}
		});

		AlertDialog alert = builder.create();
		alert.show();
	}

	private void initiatePopupWindow() {

		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View layout = inflater.inflate(R.layout.recordvoice,

				(ViewGroup) findViewById(R.id.fragment_container));
		pwindo = new PopupWindow(layout, 720, 1300, true);
		voivetimer = (TextView) layout.findViewById(R.id.timerrecordV);
		startrecord = (Button) layout.findViewById(R.id.recordvoice);
		stoprecrd = (Button) layout.findViewById(R.id.stoprecvoice);
		Exit = (Button) layout.findViewById(R.id.exit);
		stoprecrd.setVisibility(View.INVISIBLE);
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
			}
		});
		startrecord.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View V) {
				// TODO Auto-generated method stub
				MainActivityFackCall.mainActivityFackCall.start(V);
				startTime = SystemClock.uptimeMillis();
				myHandler.postDelayed(updateTimerMethod, 0);
				stoprecrd.setVisibility(View.VISIBLE);
				startrecord.setVisibility(View.INVISIBLE);
			}
		});
		stoprecrd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View V) {
				myHandler.removeCallbacks(updateTimerMethod);
				conformpoup();

			}
		});

		Exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View V) {
				/*if (Startmenuscreen.ISConnectedWifi) {
					Startmenuscreen.startAppAd.showAd();
					Startmenuscreen.startAppAd.loadAd();
				} else {*/
					com.prankspicalfakecall.Menuactivity
							.manageNavigation("Menu");

				pwindo.dismiss();

			}
		});

	}

	private Runnable updateTimerMethod = new Runnable() {

		public void run() {
			timeInMillies = SystemClock.uptimeMillis() - startTime;
			finalTime = timeSwap + timeInMillies;

			int seconds = (int) (finalTime / 1000);
			int minutes = seconds / 60;
			seconds = seconds % 60;
			int milliseconds = minutes / 60;
			voivetimer.setText("" + milliseconds + ":"
					+ String.format("%02d", minutes) + ":"
					+ String.format("%02d", seconds));
			myHandler.postDelayed(this, 0);

		}

	};

	private void conformpoup() {
		stoprecrd.setVisibility(View.INVISIBLE);
		startrecord.setVisibility(View.VISIBLE);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				mainActivityFackCall);

		// set title
		alertDialogBuilder.setTitle("Fake Call");

		// set dialog message
		alertDialogBuilder
				.setMessage("DO you want to save?")
				.setCancelable(false)
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								MainActivityFackCall.mainActivityFackCall
										.stop();
								myHandler.removeCallbacks(updateTimerMethod);
								pwindo.dismiss();
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						myAudioRecorder.reset();
						pwindo.dismiss();
						dialog.cancel();
					}
				});

		// create alert dialog

		// show it
		final AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		/*super.onBackPressed();
		if (Startmenuscreen.ISConnectedWifi) {
			Startmenuscreen.startAppAd.showAd();
			Startmenuscreen.startAppAd.loadAd();
		}*/
	}

	// ===============================SET ALL DATA FROM
	// DATABASE===============================
	private void setirlfraincall() {

	/*	if (Startmenuscreen.ISConnectedWifi) {
			Startmenuscreen.startAppAd.showAd();
			Startmenuscreen.startAppAd.loadAd();
		} else {*/
			com.prankspicalfakecall.Menuactivity
					.manageNavigation("GalleryViewActivity");


		if (Savedata.savedata.getIsgrlly(mainActivityFackCall)) {
			// Contact singleContact =
			// db.getContact(savedata.getgalleryposition(mainActivityFackCall));
			Contact singleContact = Menuactivity.menu.imageArry.get(savedata
					.getgalleryposition(mainActivityFackCall));
			setcallername.setText("" + singleContact.getUsername());
			setcallernumber.setText("" + singleContact.getUsernumber());
			String vibrbtn = singleContact.getUserVibration();
			if (vibrbtn.equalsIgnoreCase("true")) {
				setVon.setVisibility(View.VISIBLE);
				setVoff.setVisibility(View.GONE);
				savedata.controalvibration = true;
				savedata.saveVibration(savedata.controalvibration,
						mainActivityFackCall);
			} else {
				setVoff.setVisibility(View.VISIBLE);
				setVon.setVisibility(View.GONE);
				savedata.controalvibration = false;
				savedata.saveVibration(savedata.controalvibration,
						mainActivityFackCall);
			}
			int timecall = Integer
					.parseInt(singleContact.getUserCallduration()) / 1000;
			int maintime = Integer.parseInt(singleContact.getUserTime()) / 1000;
			if (timecall < 60) {
				calltime.setText(timecall + " Sec");
			}
			if (timecall == 1200) {
				calltime.setText("Call Duration");
			} else if (timecall == 60) {
				calltime.setText("1 Min");
			} else if (timecall == 120) {
				calltime.setText("2 Min");
			} else {
				calltime.setText(timecall + "Min");
			}
			if (maintime == 10) {
				radioGroupCricket.setClickable(true);
				fivesec.setClickable(true);
			} else if (maintime == 30) {
				radioGroupCricket.setClickable(true);
				fiften.setClickable(true);
			} else if (maintime == 60) {
				radioGroupCricket.setClickable(true);
				thrtysec.setClickable(true);
			} else if (maintime == 900) {
				radioGroupCricket.setClickable(true);
				onemin.setClickable(true);
			}
			else if (maintime == 1800) {
				radioGroupCricket.setClickable(true);
				onemin.setClickable(true);
			}
			savedata.saveCalldutraton(
					Integer.parseInt(singleContact.getUserCallduration()),
					mainActivityFackCall);
			if (singleContact.getUserRingtone().equalsIgnoreCase("")) {
				savedata.saveRingTone(singleContact.getUserRingtone(),
						mainActivityFackCall);
			}
			ringToneName = singleContact.getUserRingtone_Name();
			if (ringToneName.length() > 8
					&& !ringToneName.equalsIgnoreCase("Set Ringtone")) {
				setringtone.setText(ringToneName.substring(0, 7));
			} else {
				if (ringToneName.equalsIgnoreCase("null")) {
					setringtone.setText("Set Ringtone");
				} else {
					setringtone.setText("" + ringToneName);
				}
			}

			ByteArrayInputStream imageStream = new ByteArrayInputStream(
					singleContact.getImage());
			databasebitmap = BitmapFactory.decodeStream(imageStream);
			setcallerimage.setImageBitmap(databasebitmap);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			databasebitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
			byte[] b = baos.toByteArray();
			savedata.saveImage(Base64.encodeToString(b, Base64.DEFAULT),
					mainActivityFackCall);

		}
	}

	// ============================SAVE DATA IN
	// DATABASE=======================================
	private void saverecordindatabase() {

		if (!Savedata.savedata.getIsgrlly(mainActivityFackCall)
				&& bitmap != null) {

			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
			byte imageInByte[] = stream.toByteArray();
			db.addContact(new Contact("Android", imageInByte, CallerName,
					CallerNumber, "" + NOTIFY_INTERVAL, ""
					+ savedata.controalvibration, ""
					+ savedata.getCalldutraton(mainActivityFackCall),
					"" + savedata.getRingTone(mainActivityFackCall), ""
					+ ringToneName, gender));
			// } else {
			// Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),
			// R.drawable.demyg);
			// ByteArrayOutputStream stream = new ByteArrayOutputStream();
			// largeIcon.compress(Bitmap.CompressFormat.PNG, 100, stream);
			// byte[] imageInByte = stream.toByteArray();
			// db.addContact(new Contact("Android", imageInByte, CallerName,
			// CallerNumber, "" + NOTIFY_INTERVAL, ""
			// + savedata.controalvibration, ""
			// + savedata
			// .getCalldutraton(mainActivityFackCall),
			// "" + savedata.getRingTone(mainActivityFackCall), ""
			// + ringToneName, gender));
			// }

		}
	}

	private void picphotofrom() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

		// Setting Dialog Title

		// Setting Dialog Message
		alertDialog.setMessage("Choose a photo option...");

		// Setting Icon to Dialog

		// Setting Positive "Yes" Button
		alertDialog.setPositiveButton("Camera",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						clickuserimagevoicbtn = true;
						callCamera();
					}
				});

		// Setting Negative "NO" Button
		alertDialog.setNegativeButton("Gallery",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						clickuserimagevoicbtn = true;
						callGallery();
					}
				});

		alertDialog.show();
	}

}