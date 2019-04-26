package com.fakegirlfraind.grlyview;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import com.fackgirlfraind.database.Contact;
import com.racinggamelabs.fakecallgirlfriendboyfriend.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

public class GalleryAdapter extends ArrayAdapter<Contact> {
	int layoutResourceId;
	private Context mContext;

	ArrayList<Contact> data = new ArrayList<Contact>();

	public GalleryAdapter(Context context, int layoutResourceId,
			ArrayList<Contact> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.mContext = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;

		if (row == null) {
			ImageView i = new ImageView(mContext);
			position = getPosition(position);
			if (position < data.size()) {
				Contact picture = data.get(position);

				byte[] outImage = picture.getImage();
				ByteArrayInputStream imageStream = new ByteArrayInputStream(
						outImage);
				Bitmap theImage = BitmapFactory.decodeStream(imageStream);
				i.setImageBitmap(theImage);
				WindowManager wm = (WindowManager) mContext
						.getSystemService(Context.WINDOW_SERVICE);
				Display display = wm.getDefaultDisplay();
				if (((display.getWidth() >= 1024) && (display.getWidth() <= 1280))
						&& ((display.getHeight() >= 600) && (display
								.getHeight() <= 2000))) {
					i.setLayoutParams(new Gallery.LayoutParams(400, 400));
				} else {
					i.setLayoutParams(new Gallery.LayoutParams(200, 200));
				}

				i.setScaleType(ImageView.ScaleType.FIT_XY);
			}
			return i;

		}

		return row;

	}

	int getPosition(int position) {
		if (position >= data.size()) {
			position = position % data.size();
		}
		return position;
	}
}