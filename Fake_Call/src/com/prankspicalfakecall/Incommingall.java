package com.prankspicalfakecall;

import java.io.ByteArrayInputStream;

import com.fackgirlfraind.database.Contact;
import com.fackgirlfraind.database.DataBaseHandler;
import com.fakegirlfraind.grlyview.GalleryViewActivity;
import com.racinggamelabs.fakecallgirlfriendboyfriend.R;

import android.R.bool;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Base64;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Incommingall extends Activity {
	TextView callername, callernumber;
	ImageView incomingcallerimage;
	MediaPlayer mp;
	public static Incommingall incommingall;
	Vibrator v;
	boolean vibrate = false;
	Savedata savedata;
	Bitmap bitmap;
	int layoutNumber;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		savedata=new Savedata();
		layoutNumber = savedata.getLayoutNumber(this);
		if(layoutNumber == 1 ) {
			setContentView(R.layout.custome_incoming1);

		}
		else if(layoutNumber == 2 ) {
			setContentView(R.layout.custome_incoming2);

		}
		else if(layoutNumber == 3 ) {
			setContentView(R.layout.custome_incoming3);
		}
		else if(layoutNumber == 4 ) {
			setContentView(R.layout.custome_incoming4);
		}
		else if(layoutNumber == 5 ) {
			setContentView(R.layout.custome_incoming5);
		}
		else if(layoutNumber == 6 ) {
			setContentView(R.layout.custom_incoming6);
		}
		else if(layoutNumber == 7 ) {
			setContentView(R.layout.custome_incoming7);
		}
		else if(layoutNumber == 8 ) {
			setContentView(R.layout.custom_incoming8);
		}
		else if(layoutNumber == 9) {
			setContentView(R.layout.custom_incoming9);
		}
		else if(layoutNumber == 10 ) {
			setContentView(R.layout.custome_incoming10);
		}
		savedata = Savedata.getInstance();
		Vibrate();
		incommingall = this;
		callername = (TextView) findViewById(R.id.callername);
		callernumber = (TextView) findViewById(R.id.textView3);
		incomingcallerimage = (ImageView) findViewById(R.id.callerimageincoming);
	/*	incomingcallerimage = (ImageView) findViewById(R.id.callerimageincoming);
		Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.sali_fs8);hklhlkjgfzdhjkhflkjkljflkjlkgxhgnk,bgxckj

		incomingcallerimage.setImageBitmap(icon);
*/
		boolean s = savedata.getVibration(incommingall);

		v = (Vibrator) this.incommingall
				.getSystemService(incommingall.VIBRATOR_SERVICE);

		if (savedata.getVibration(incommingall)) {
			v.vibrate(500);
		}

		if (Build.VERSION.SDK_INT >= 11) {
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
			ActionBar actionBar = getActionBar();
			actionBar.hide();
		}
		//String rin = savedata.getRingTone(incommingall);
		if (!savedata.getRingTone(incommingall).equalsIgnoreCase("")) {
			String ringtone = savedata.getRingTone(incommingall);
			Uri ringtoneUri = Uri.parse(ringtone);
			mp = MediaPlayer.create(getApplicationContext(), ringtoneUri);
			mp.start();
		} else {
            try {
                Uri notification = RingtoneManager
                        .getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                mp = MediaPlayer.create(getApplicationContext(), notification);
                mp.start();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
		}
		String CallerName = savedata.getusername(incommingall);
		String CallerNumber = savedata.getuserNumber(incommingall);

		callername.setText(" " + CallerName);
		callername.setTextColor(Color.WHITE);
		callernumber.setText("" + CallerNumber);
		callernumber.setTextColor(Color.WHITE);
		incomingcallerimage.setImageDrawable(MainActivityFackCall.setcallerimage.getDrawable());
		//incomingcallerimage.setImageDrawable(MainActivityFackCall.imageView.getDrawable());
		//incomingcallerimage.setImageDrawable(MainActivityFackCall.imageView.getBackground());


		if (!savedata.getImage(incommingall).equalsIgnoreCase("")) {
			StringToBitMap(savedata.getImage(incommingall));
			incomingcallerimage.setImageDrawable(MainActivityFackCall.setcallerimage.getDrawable());
		/*	incomingcallerimage.setBackground(get().getDrawable(MainActivityFackCall.imageView.getDrawable()));
			incomingcallerimage.setImageBitmap(bitmap);
*/
		} /*else {
			incomingcallerimage.setBackground(getResources().getDrawable(
					R.drawable.girls5));
		}*/
		ImageView answer = (ImageView) findViewById(R.id.answer);
		answer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				stopService((new Intent(Incommingall.this,
						FackCallServies.class)));
				mp.stop();
				vibrate = true;
				Vibrate();
				Intent i = new Intent(Incommingall.this, ReciveCall.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(i);
				v.cancel();
				finish();
			}
		});
		ImageView decline = (ImageView) findViewById(R.id.decline);
		decline.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mp.stop();
				vibrate = true;
				Vibrate();
				v.cancel();
				stopService((new Intent(Incommingall.this,
						FackCallServies.class)));

				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				finish();
			}
		});

	}

	private void Vibrate() {
		new CountDownTimer(1200000, 1000) {

			public void onTick(long millisUntilFinished) {
				// Toast.makeText(getApplicationContext(), "start",
				// Toast.LENGTH_SHORT).show();
				if (!vibrate && savedata.getVibration(incommingall)) {
					v.vibrate(500);
				} else {

					v.cancel();
				}
			}

			public void onFinish() {

				finish();
			}
		}.start();
	}

	public Bitmap StringToBitMap(String encodedString) {
		try {
			byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
			bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
					encodeByte.length);
			return bitmap;
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

}
