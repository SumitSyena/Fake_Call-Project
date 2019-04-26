package com.fakegirlfraind.grlyview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.racinggamelabs.fakecallgirlfriendboyfriend.R;


public class SelectPhoneImageAdapter extends BaseAdapter {
    private Context mContext;

    // Keep all Images in array
    public static  Integer[] mThumbIds = {
            R.drawable.s1, R.drawable.s2,
            R.drawable.s3, R.drawable.s4,
            R.drawable.s5, R.drawable.s6,
            R.drawable.s7, R.drawable.s8,
            R.drawable.s9, R.drawable.same10
    };

    // Constructor
    public SelectPhoneImageAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new GridView.LayoutParams(300, 500));
        return imageView;
    }

}