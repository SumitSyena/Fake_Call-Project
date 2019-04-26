package com.prankspicalfakecall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.fakegirlfraind.grlyview.SelectPhoneImageAdapter;
import com.racinggamelabs.fakecallgirlfriendboyfriend.R;


public class SelectPhoneActivity extends Activity {

    public static int selectedTheme_resID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_phone);

        GridView gridView = (GridView) findViewById(R.id.gridview);

        // Instance of ImageAdapter Class
        gridView.setAdapter(new SelectPhoneImageAdapter(this));
        gridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                selectedTheme_resID = SelectPhoneImageAdapter.mThumbIds[position];
                // Sending image id to FullScreenActivity
                Intent cin = new Intent(getApplicationContext(), MainActivityFackCall.class);
                // passing array index
                cin.putExtra("caller", "SelectPhoneActivity");
                startActivity(cin);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(SelectPhoneActivity.this,MainActivityFackCall.class);
        startActivity(i);
    }
}

