package com.hotsource.hotbucket;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

/**
 * Attach the tab to the MainActivity
 * 
 */

public class MainActivity extends Activity {

	static BucketData bucketdb; // define data

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bucketdb = new BucketData(this);

		startActivity(new Intent(this, ThreeTabActivity.class));
		startActivity(new Intent(this, SplashActivity.class));
		finish();
	}

	static BucketData getDB() {

		return bucketdb;
	}
}
