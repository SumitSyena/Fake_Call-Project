package com.prankspicalfakesma;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import com.racinggamelabs.fakecallgirlfriendboyfriend.R;
import com.prankspicalfakecall.FackCallServies;
import com.prankspicalfakecall.Incommingall;
import com.prankspicalfakecall.MainActivityFackCall;

import com.prankspicalfakecall.Savedata;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Vibrator;
import android.provider.Contacts.People;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

public class FacksmaServies extends Service {
	Savedata savedata;
	String CallerName, CallerNumber, SmaText;
	private static final int IMAGE_CAPTURE = 0;
	private Uri imageUri;
	int a = 0;
	Notification noti;
	NotificationManager nmgr;
	// constant
	FackCallServies mainActivityFackSma;
	// 10 seconds
	private static final int NOTIFY_ME_ID = 1337;

	// run on another Thread to avoid crash
	private Handler mHandler = new Handler();
	// timer handling
	MediaPlayer mp;
	public static Timer mTimer = null;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	FacksmaServies facksmaServies;
	Vibrator v;

	@Override
	public void onCreate() {
		facksmaServies = this;
		savedata = Savedata.getInstance();
		// if (MainActivityFackSma.calltimetsms) {
		new CountDownTimer(MainActivityFackSma.NOTIFY_INTERVAL, 1000) {

			public void onTick(long millisUntilFinished) {
				//
				// Toast.makeText(getApplicationContext(),
				// "Setting Saved", Toast.LENGTH_SHORT).show();

			}

			public void onFinish() {

				// try {
				// MainActivityFackSma.mainActivityFackSma.startCamera();
				// } catch (IOException e1) {
				// // TODO Auto-generated catch block
				// e1.printStackTrace();
				// }
				boolean s = savedata.getVibrationServics(facksmaServies);
				System.out.println();
				v = (Vibrator) getApplicationContext().getSystemService(
						facksmaServies.VIBRATOR_SERVICE);
				if (savedata.getVibrationServics(facksmaServies)) {
					v.vibrate(500);
				}
				MainActivityFackSma.NOTIFY_INTERVAL = 0;
				ContentValues values = new ContentValues();

				CallerName = savedata.getusernameService(facksmaServies);
				CallerNumber = savedata.getusernameService(facksmaServies);
				SmaText = savedata.getsmstext(facksmaServies);

				values.put("address", CallerName); // name
				values.put("body", SmaText);

				if (savedata.getSmapalce(facksmaServies).equalsIgnoreCase(
						"inbox")) {
					getContentResolver().insert(
							Uri.parse("content://sms/inbox"), values);
				} else if (MainActivityFackSma.SmsPlace
						.equalsIgnoreCase("draft")) {
					getContentResolver().insert(
							Uri.parse("content://sms/draft"), values);
				} else if (savedata.getSmapalce(facksmaServies)
						.equalsIgnoreCase("outbox")) {
					getContentResolver().insert(
							Uri.parse("content://sms/outbox"), values);
				} else if (savedata.getSmapalce(facksmaServies)
						.equalsIgnoreCase("")) {
					getContentResolver().insert(
							Uri.parse("content://sms/inbox"), values);
				}
				nmgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				noti = new Notification(R.drawable.sms, "Sms recived",
						System.currentTimeMillis());
				Intent notificationIntent = new Intent(Intent.ACTION_MAIN);
				notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
						| Intent.FLAG_ACTIVITY_SINGLE_TOP
						| Intent.FLAG_ACTIVITY_CLEAR_TOP);
				notificationIntent.setType("vnd.android-dir/mms-sms");
				PendingIntent pIntent = PendingIntent.getActivity(
						getApplicationContext(), 0, notificationIntent, 0);
				noti.setLatestEventInfo(getApplicationContext(), ""
						+ CallerName, "" + SmaText, pIntent);
				noti.flags = Notification.FLAG_AUTO_CANCEL;

				String rin = savedata.getRingToneServics(facksmaServies);

				if (!savedata.getRingToneServics(facksmaServies)
						.equalsIgnoreCase("")) {
					String ringtone = savedata
							.getRingToneServics(facksmaServies);
					Uri ringtoneUri = Uri.parse(ringtone);

					mp = MediaPlayer.create(getApplicationContext(),
							ringtoneUri);
					mp.start();
				} else {
					try {
						Uri notification = RingtoneManager
								.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
						Ringtone r = RingtoneManager.getRingtone(
								getApplicationContext(), notification);
						r.play();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				nmgr.notify(0, noti);
				stopService(new Intent(FacksmaServies.this,
						FacksmaServies.class));

			}

		}.start();
		//

	}

}