package com.fakegirlfraind.grlyview;

import java.util.ArrayList;
import java.util.List;

import com.fackgirlfraind.database.Contact;

import com.fackgirlfraind.database.DataBaseHandler;
import com.prankspicalfakecall.MainActivity;
import com.prankspicalfakecall.MainActivityFackCall;
import com.prankspicalfakecall.Menuactivity;
import com.prankspicalfakecall.Startmenuscreen;
import com.racinggamelabs.fakecallgirlfriendboyfriend.R;

import android.content.Context;

import android.util.AttributeSet;
import android.util.Log;

import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class CircleGallery extends Gallery {
	GalleryAdapter imageAdapter;

	DataBaseHandler db;

	// 3 default constructors
	public CircleGallery(Context context) {
		super(context);
		initiateAdapter(context);

	}

	public CircleGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
		initiateAdapter(context);
	}

	public CircleGallery(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initiateAdapter(context);
	}

	View lastSelectedView = null;

	private void initiateAdapter(Context context) {

		this.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if (((Startmenuscreen.display.getWidth() >= 1024) && (Startmenuscreen.display
						.getWidth() <= 1280))
						&& ((Startmenuscreen.display.getHeight() >= 600) && (Startmenuscreen.display
								.getHeight() <= 2000))) {
					if (lastSelectedView != null) {
						lastSelectedView
								.setLayoutParams(new Gallery.LayoutParams(550,
										550));
					}
				} else {
					if (lastSelectedView != null) {
						lastSelectedView
								.setLayoutParams(new Gallery.LayoutParams(200,
										200));
					}

				}

				if (((Startmenuscreen.display.getWidth() >= 1024) && (Startmenuscreen.display
						.getWidth() <= 1280))
						&& ((Startmenuscreen.display.getHeight() >= 600) && (Startmenuscreen.display
								.getHeight() <= 2000))) {
					if (arg1 != null) {
						arg1.setLayoutParams(new Gallery.LayoutParams(650, 650));
					}
				}

				else {
					if (arg1 != null) {
						arg1.setLayoutParams(new Gallery.LayoutParams(250, 250));
					}
				}
				// lastSelectedView=arg1;

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		// imageAdapter = new ContactImageAdapter(this,
		// R.layout.screen_list,imageArry);
		// dataList.setAdapter(imageAdapter);
		db = new DataBaseHandler(getContext());

		List<Contact> contacts = db.getAllContacts();
		Menuactivity.menu.imageArry = new ArrayList<Contact>();
		for (Contact cn : contacts) {
			String log = "ID:" + cn.getID() + " Name: " + cn.getName()
					+ " ,Image: " + cn.getImage();

			// Writing Contacts to log
			Log.d("Result: ", log);
			// add contacts data in arrayList
			/*if (cn.getUserGender().equalsIgnoreCase(
					Menuactivity.clickonboyandgirl)) {
				Menuactivity.menu.imageArry.add(cn);
			} else if (cn.getUserGender().equalsIgnoreCase(
					Menuactivity.clickonboyandgirl)) {
				Menuactivity.menu.imageArry.add(cn);
			}
*/
		}

		setAdapter(new GalleryAdapter(getContext(), R.layout.display,
				Menuactivity.menu.imageArry));

		// To select the xSelected one -> 0 is the first.
		int xSelected = 0;
		// To make the view go to the middle of our 'endless' array
		if (Menuactivity.menu.imageArry.size() > 0) {
			setSelection(Menuactivity.menu.imageArry.size() / 2
					+ (Menuactivity.menu.imageArry.size() / 2)
					% Menuactivity.menu.imageArry.size() - 1 + xSelected);
		}
	}

}
