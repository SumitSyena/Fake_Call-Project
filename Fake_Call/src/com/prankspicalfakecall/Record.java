/*package com.prankspicalfakecall;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Record extends Activity {
	
	long timeInMillies = 0L;
	long timeSwap = 0L;
	long finalTime = 0L;
	private Handler myHandler = new Handler();
	TextView voivetimer;
	Button startrecord, stoprecrd, playvoice, pausevoice;
	private long startTime = 0L;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recordvoice);
		voivetimer = (TextView) findViewById(R.id.timerrecordV);
		startrecord = (Button) findViewById(R.id.recordvoice);
		stoprecrd = (Button) findViewById(R.id.stoprecvoice);
		stoprecrd.setVisibility(View.INVISIBLE);
		
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
				// TODO Auto-generated method stub
				stoprecrd.setVisibility(View.INVISIBLE);
				startrecord.setVisibility(View.VISIBLE);
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());

				// set title
				alertDialogBuilder.setTitle("Your Title");

				// set dialog message
				alertDialogBuilder
						.setMessage("Do ypu to save this file")
						.setCancelable(false)
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										MainActivityFackCall.mainActivityFackCall
												.stop();
										myHandler
												.removeCallbacks(updateTimerMethod);
									
									}
								})
						.setNegativeButton("No",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// if this button is clicked, just close
										// the dialog box and do nothing
										dialog.cancel();
									}
								});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
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
	}

*/