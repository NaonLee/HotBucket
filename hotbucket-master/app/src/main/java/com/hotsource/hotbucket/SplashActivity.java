package com.hotsource.hotbucket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

/**
 * Splash
 * 
 */

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
	
		Handler handler = new Handler () {
			@Override
			public void handleMessage(Message msg){
				finish();
			}
		};
		handler.sendEmptyMessageDelayed(0, 3000);
	}



}
