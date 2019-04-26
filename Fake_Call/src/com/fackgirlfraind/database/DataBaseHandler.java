package com.fackgirlfraind.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "imagedb";

	// Contacts table name
	private static final String TABLE_CONTACTS = "contacts";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_IMAGE = "image";
	private static final String KEY_USERNAME = "username";
	private static final String KEY_USERNUMBER = "usernumber";
	private static final String KEY_USRTIME = "time";
	private static final String KEY_USRVIBRATION = "vibration";
	private static final String KEY_USRCALLDURATION = "zero";
	private static final String KEY_USRRINGTONE = "ring";
	private static final String KEY_USRRINGTONE_NAME = "namringtone";
	private static final String KEY_USRGENDER= "girl";
	public DataBaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_IMAGE + " BLOB," + KEY_USERNAME + " TEXT,"
				+ KEY_USERNUMBER + " TEXT," + KEY_USRTIME + " TEXT,"
				+ KEY_USRVIBRATION + " TEXT," + KEY_USRCALLDURATION + " TEXT,"
				+ KEY_USRRINGTONE + " TEXT, " +KEY_USRRINGTONE_NAME+ " TEXT, " + KEY_USRGENDER+ "  TEXT " + ")";

		// String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
		// + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
		// + KEY_IMAGE + " BLOB" + ")";

		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	public// Adding new contact
	void addContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, contact.name); // Contact Name
		values.put(KEY_IMAGE, contact.image); // Contact Phone
		values.put(KEY_USERNAME, contact.username);
		values.put(KEY_USERNUMBER, contact.Usernumber);
		values.put(KEY_USRTIME, contact.UserTime);
		values.put(KEY_USRVIBRATION, contact.UserVibration);
		values.put(KEY_USRCALLDURATION, contact.UserCallduration);
		values.put(KEY_USRRINGTONE, contact.UserRingtone);
		values.put(KEY_USRRINGTONE_NAME, contact.UserRingtone_Name);
		values.put(KEY_USRGENDER, contact.UserGender);
		// Inserting Row
		db.insert(TABLE_CONTACTS, null, values);
		db.close(); // Closing database connection
	}

	// Getting All Contacts
	public List<Contact> getAllContacts() {
		List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM contacts ORDER BY name";

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setName(cursor.getString(1));
				contact.setImage(cursor.getBlob(2));
				contact.setUsername(cursor.getString(3));
				contact.setUsernumber(cursor.getString(4));
				contact.setUserTime(cursor.getString(5));
				contact.setUserVibration(cursor.getString(6));
				contact.setUserCallduration(cursor.getString(7));
				contact.setUserRingtone(cursor.getString(8));
				contact.setUserRingtone_Name(cursor.getString(9));
				contact.setUserGender(cursor.getString(10));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		// close inserting data from database
		db.close();
		// return contact list
		return contactList;

	}

	// Updating single contact
	public int updateContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, contact.name); // Contact Name
		values.put(KEY_IMAGE, contact.image); // Contact Phone
		values.put(KEY_USERNAME, contact.username);
		values.put(KEY_USERNUMBER, contact.Usernumber);
		values.put(KEY_USRTIME, contact.UserTime);
		values.put(KEY_USRVIBRATION, contact.UserVibration);
		values.put(KEY_USRCALLDURATION, contact.UserCallduration);
		values.put(KEY_USRRINGTONE, contact.UserRingtone);
		values.put(KEY_USRRINGTONE_NAME, contact.UserRingtone_Name);
		values.put(KEY_USRGENDER, contact.UserGender);
		

		// updating row
		return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });

	}

	// Deleting single contact
	public void deleteContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
		db.close();
	}

	// Getting contacts Count
	public int getContactsCount() {
		String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}

}
