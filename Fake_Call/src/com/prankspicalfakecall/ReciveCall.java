package com.prankspicalfakecall;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.fackgirlfraind.database.Contact;
import com.fackgirlfraind.database.DataBaseHandler;
import com.fakegirlfraind.grlyview.GalleryViewActivity;
import com.racinggamelabs.fakecallgirlfriendboyfriend.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ReciveCall extends Activity {
	MediaPlayer mPlayer = new MediaPlayer();
	int milliseconds = 0;
	TextView setcallertine;
	private long startTime = 0L;
	private Handler myHandler = new Handler();
	long timeInMillies = 0L;
	long timeSwap = 0L;
	long finalTime = 0L;
	TextView callername, callernumber;
	ImageView recivecallerimage;
	Savedata savedata;
	ReciveCall reciveCall;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recivecall);
		reciveCall = this;
		savedata = Savedata.getInstance();
		Timer();
		if (!savedata.getRecordVoice(reciveCall).equalsIgnoreCase("")) {
			try {
				play();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		callername = (TextView) findViewById(R.id.recivename);
		callernumber = (TextView) findViewById(R.id.revivenumber);
		recivecallerimage = (ImageView) findViewById(R.id.reciveccallerimage);

		String CallerName = savedata.getusername(reciveCall);
		String CallerNumber = savedata.getuserNumber(reciveCall);

		callername.setText("" + CallerName);
		callernumber.setText("" + CallerNumber);
		if (!savedata.getImage(reciveCall).equalsIgnoreCase("")) {
			Incommingall.incommingall.StringToBitMap(savedata
					.getImage(reciveCall));
			recivecallerimage.setImageBitmap(Incommingall.incommingall.bitmap);
		} else if (savedata.getIsgrlly(reciveCall)) {
			Contact singleContact = Menuactivity.menu.imageArry.get(savedata.getgalleryposition(reciveCall));

			ByteArrayInputStream imageStream = new ByteArrayInputStream(
					singleContact.getImage());
			Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
			recivecallerimage.setImageBitmap(bitmap);
			System.out.println("");

		} else {
			recivecallerimage.setBackground(getResources().getDrawable(
					R.drawable.user_image_icon));
		}
		if (!savedata.getBrowsevoice(reciveCall).equalsIgnoreCase("")) {
			String Browsevoice = savedata.getBrowsevoice(reciveCall);
			Uri BrowsevoiceUri = Uri.parse(Browsevoice);
			setvoice(BrowsevoiceUri);
			mPlayer.start();
		}
		// ============

		// ================

		startTime = SystemClock.uptimeMillis();
		myHandler.postDelayed(updateTimerMethod, 0);
		setcallertine = (TextView) findViewById(R.id.calltimer);
		if (Build.VERSION.SDK_INT >= 11) {
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
			ActionBar actionBar = getActionBar();
			actionBar.hide();
		}
		ImageView endcallbtn = (ImageView) findViewById(R.id.endbtn);
		endcallbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Menuactivity.menu.manageNavigation("Menu");
//				android.os.Process.killProcess(android.os.Process.myPid());
//				System.exit(0);


				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				finish();
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
			setcallertine.setText("" + milliseconds + ":"
					+ String.format("%02d", minutes) + ":"
					+ String.format("%02d", seconds));
			myHandler.postDelayed(this, 0);
		}

	};

	private void Timer() {
		new CountDownTimer(savedata.getCalldutraton(reciveCall), 1000) {

			public void onTick(long millisUntilFinished) {

			}

			public void onFinish() {

				finish();
				if (!savedata.getBrowsevoice(reciveCall).equalsIgnoreCase("")) {
					MainActivityFackCall.mainActivityFackCall.mPlayer.stop();
				}
				if (!savedata.getRecordVoice(reciveCall).equalsIgnoreCase("")) {
					try {
						MainActivityFackCall.mainActivityFackCall.Stopplayer();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}.start();
	}

	public void play() throws IllegalArgumentException, SecurityException,
			IllegalStateException, IOException {
		savedata.outputFile = savedata.getRecordVoice(reciveCall);
		MediaPlayer m = new MediaPlayer();
		m.setDataSource(savedata.outputFile);
		m.prepare();
		m.start();
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

}
