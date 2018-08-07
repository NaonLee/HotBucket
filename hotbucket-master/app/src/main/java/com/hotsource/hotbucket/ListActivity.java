package com.hotsource.hotbucket;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;

/**
 * recommend Bucket List
 * The button is on the third tab
 * 
 */

public class ListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.listpage);
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
