package com.fackgirlfraind.database;

public class Contact {

	// private variables
	int id;
	String name;
	String username;
	String Usernumber;
	String UserTime;
	String UserVibration;
	String UserCallduration;
	String UserRingtone;
	String UserRingtone_Name;
	String UserGender;
	byte[] image;

	// Empty constructor
	public Contact() {

	}

	// constructor
	public Contact(int keyId, String name, byte[] image, String username,
			String usernumber, String time, String vibration,
			String callduration, String ringtone, String ringtone_name,
			String usergender) {
		this.id = keyId;
		this.name = name;
		this.image = image;
		this.username = username;
		this.Usernumber = usernumber;

		this.UserTime = time;
		this.UserVibration = vibration;
		this.UserCallduration = callduration;
		this.UserRingtone = ringtone;
		this.UserRingtone_Name = ringtone_name;
		this.UserGender = usergender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Contact(String name, byte[] image, String username,
			String usernumber, String time, String vibration,
			String callduration, String ringtone, String ringtone_name,
			String usergender) {
		this.name = name;
		this.image = image;
		this.username = username;
		this.Usernumber = usernumber;

		this.UserTime = time;
		this.UserVibration = vibration;
		this.UserCallduration = callduration;
		this.UserRingtone = ringtone;
		this.UserRingtone_Name = ringtone_name;
		this.UserGender = usergender;
	}

	public Contact(int keyId) {
		this.id = keyId;

	}

	// getting ID
	public int getID() {
		return this.id;
	}

	// setting id
	public void setID(int keyId) {
		this.id = keyId;
	}

	// getting name
	public String getName() {
		return this.name;
	}

	// setting name
	public void setName(String name) {
		this.name = name;
	}

	// getting phone number
	public byte[] getImage() {
		return this.image;
	}

	// setting phone number
	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getUsernumber() {
		return Usernumber;
	}

	public void setUsernumber(String usernumber) {
		Usernumber = usernumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserTime() {
		return UserTime;
	}

	public void setUserTime(String userTime) {
		UserTime = userTime;
	}

	public String getUserVibration() {
		return UserVibration;
	}

	public void setUserVibration(String userVibration) {
		UserVibration = userVibration;
	}

	public String getUserCallduration() {
		return UserCallduration;
	}

	public void setUserCallduration(String userCallduration) {
		UserCallduration = userCallduration;
	}

	public String getUserRingtone() {
		return UserRingtone;
	}

	public void setUserRingtone(String userRingtone) {
		UserRingtone = userRingtone;
	}

	public String getUserRingtone_Name() {
		return UserRingtone_Name;
	}

	public void setUserRingtone_Name(String userRingtone_Name) {
		UserRingtone_Name = userRingtone_Name;
	}

	public String getUserGender() {
		return UserGender;
	}

	public void setUserGender(String userGender) {
		UserGender = userGender;
	}

}
