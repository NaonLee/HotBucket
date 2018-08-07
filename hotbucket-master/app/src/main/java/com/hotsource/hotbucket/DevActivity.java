package com.hotsource.hotbucket;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;

/**
 * Enter developer information
 * button is on the third tab
 * 
 */

public class DevActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.devpage);
				
	}



}
