package com.prankspicalfakecall;

import android.app.Activity;
import android.app.Service;
import android.content.SharedPreferences;
import android.net.Uri;

public class Savedata {
	public String Callername = "";
	public String CallerNumber = "";
	public String RingTone = "";
	public int lNumber;
	public String Image = "";
	public boolean controalvibration = false;
	public boolean recoredvoic = false;
	public String browsevoice = "";
	public int CallDutratiov = 1200000;
	public String outputFile = "";
	public String smsText = "";
	public String smspalace = "";
	public static Savedata savedata;
	public static boolean isclickGallery = false;
	public static int positionclick;
	public Savedata() {
		savedata = this;
	}

	public static Savedata getInstance() {
		if (savedata == null) {
			savedata = new Savedata();
		}
		return savedata;
	}

	public void saveusername(String Claaername, Activity cActivity) {

		SharedPreferences preferences = cActivity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		System.out.println("game coin here save" + Claaername);
		editor.putString("callername", Claaername);
		editor.commit();
	}

	public String getusername(Activity cActivity) {
		SharedPreferences preferences = cActivity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		String Callername2 = preferences.getString("callername", ""
				+ Callername);
		return Callername2;
	}

	// =====================Save User Number===================
	public void saveuserNumber(String ClaaerNumber, Activity cActivity) {

		SharedPreferences preferences = cActivity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		System.out.println("game coin here save" + ClaaerNumber);
		editor.putString("callerNumber", ClaaerNumber);
		editor.commit();
	}

	public String getuserNumber(Activity cActivity) {
		SharedPreferences preferences = cActivity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		String callerNumber = preferences.getString("callerNumber", ""
				+ CallerNumber);
		return callerNumber;
	}
	//=====================
	// =====================Save User Number===================
	public void saveLayoutNumber(int lNumber, Activity cActivity) {

		SharedPreferences preferences = cActivity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		System.out.println("game coin here save" + lNumber);
		editor.putInt("lNumber", lNumber);
		editor.commit();
	}

	public int getLayoutNumber(Activity cActivity) {
		SharedPreferences preferences = cActivity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		int lNumbern = preferences.getInt("lNumber",lNumber);
		return lNumbern;
	}


	// =====================Save RingTone===================
	public void saveRingTone(String Ringtone, Activity cActivity) {

		SharedPreferences preferences = cActivity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		System.out.println("game coin here save" + Ringtone);
		editor.putString("RingTone", Ringtone);
		editor.commit();
	}

	public String getRingTone(Activity cActivity) {
		SharedPreferences preferences = cActivity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		String ringtone = preferences.getString("RingTone", "" + RingTone);
		return ringtone;
	}

	// =====================Save User Vibration===================
	public void saveVibration(Boolean Vib, Activity cActivity) {

		SharedPreferences preferences = cActivity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		System.out.println("game coin here save" + Vib);
		editor.putBoolean("Vibration", Vib);
		editor.commit();
	}

	public boolean getVibration(Activity cActivity) {
		SharedPreferences preferences = cActivity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		boolean sound = preferences.getBoolean("Vibration", false);
		return sound;
	}

	// //=====================Save Image Calller User===================
	public void saveImage(String Image, Activity cActivity) {

		SharedPreferences preferences = cActivity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		System.out.println("game coin here save" + Image);
		editor.putString("Image", Image);
		editor.commit();
	}

	public String getImage(Activity cActivity) {
		SharedPreferences preferences = cActivity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		String Imagertn = preferences.getString("Image", "" + Image);
		return Imagertn;
	}

	// =====================Save User Voice===================
	public void saveRecordVoice(String record, Activity cActivity) {

		SharedPreferences preferences = cActivity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		System.out.println("game coin here save" + record);
		editor.putString("Recordvoice", record);
		editor.commit();
	}

	public String getRecordVoice(Activity cActivity) {
		SharedPreferences preferences = cActivity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		String sound = preferences.getString("Recordvoice", "" + outputFile);
		return sound;
	}

	// =====================Save Song===================
	public void saveBrowsevoice(String Browsevoice, Activity cActivity) {

		SharedPreferences preferences = cActivity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		System.out.println("game coin here save" + Browsevoice);
		editor.putString("Browsevoice", Browsevoice);
		editor.commit();
	}

	public String getBrowsevoice(Activity cActivity) {
		SharedPreferences preferences = cActivity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		String Browsevoice = preferences.getString("Browsevoice", ""
				+ browsevoice);
		return Browsevoice;
	}

	// =====================Save calldutraton===================
	public void saveCalldutraton(int calltime, Activity cActivity) {

		SharedPreferences preferences = cActivity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		System.out.println("game coin here save" + calltime);
		editor.putInt("Calltime", calltime);
		editor.commit();
	}

	public int getCalldutraton(Activity cActivity) {
		SharedPreferences preferences = cActivity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		int Browsevoice = preferences.getInt("Calltime", CallDutratiov);
		return Browsevoice;
	}

	// ===========
	public String getusernameService(Service cActivity) {
		SharedPreferences preferences = cActivity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		String Callername2 = preferences.getString("callername", ""
				+ Callername);
		return Callername2;
	}

	// =====================Save User Number===================
	public void saveuserNumberService(String ClaaerNumber, Service cActivity) {

		SharedPreferences preferences = cActivity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		System.out.println("game coin here save" + ClaaerNumber);
		editor.putString("callerNumber", ClaaerNumber);
		editor.commit();
	}

	
	
	// =================Save Sms Text====================

	public void savesmstext(String smstext, Activity activity) {

		SharedPreferences preferences = activity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		System.out.println("game coin here save" + smstext);
		editor.putString("SMS", smstext);
		editor.commit();
	}

	public String getsmstext(Service service) {
		SharedPreferences preferences = service.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		String smstext2 = preferences.getString("SMS", "" + smsText);
		return smstext2;
	}
	//======Get RingTone Servics==================
	public String getRingToneServics(Service service) {
		SharedPreferences preferences = service.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		String ringtone = preferences.getString("RingTone", "" + RingTone);
		return ringtone;
	}
	public boolean getVibrationServics(Service service) {
		SharedPreferences preferences = service.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		boolean sound = preferences.getBoolean("Vibration", false);
		return sound;
	}
	// =====================Save User RLLLLLLYYYYYYYYY===================
		public void saveIsgrlly(Boolean click, Activity cActivity) {

			SharedPreferences preferences = cActivity.getSharedPreferences(
					"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = preferences.edit();	
			editor.putBoolean("ISCLICK", click);
			editor.commit();
		}

		public boolean getIsgrlly(Activity cActivity) {
			SharedPreferences preferences = cActivity.getSharedPreferences(
					"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
			boolean sound = preferences.getBoolean("ISCLICK", false);
			return sound;
		}
		// =====================Save gallery position===================
		public void savegalleryposition(int pos, Activity cActivity) {

			SharedPreferences preferences = cActivity.getSharedPreferences(
					"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = preferences.edit();
			System.out.println("game coin here save" + pos);
			editor.putInt("POSITION", pos);
			editor.commit();
		}

		public int getgalleryposition(Activity cActivity) {
			SharedPreferences preferences = cActivity.getSharedPreferences(
					"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
			int Browsevoice = preferences.getInt("POSITION", positionclick);
			return Browsevoice;
		}

	///======================Save Sms Palace =============
	public void saveSmspalace(String smspalace, Activity activity) {

		SharedPreferences preferences = activity.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		System.out.println("game coin here save" + smspalace);
		editor.putString("SmsPalce", smspalace);
		editor.commit();
	}

	public String getSmapalce(Service service) {
		SharedPreferences preferences = service.getSharedPreferences(
				"PROJECT_NAME", android.content.Context.MODE_PRIVATE);
		String ringtone = preferences.getString("SmsPalce", ""+smspalace);
		return ringtone;
	}
}
