package com.hotsource.hotbucket;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.hotsource.hotbucket.facebook.android.AsyncFacebookRunner;
import com.hotsource.hotbucket.facebook.android.AsyncFacebookRunner.RequestListener;
import com.hotsource.hotbucket.facebook.android.DialogError;
import com.hotsource.hotbucket.facebook.android.Facebook;
import com.hotsource.hotbucket.facebook.android.Facebook.DialogListener;
import com.hotsource.hotbucket.facebook.android.FacebookError;

/**
 * Setting page on the third tab
 * 
 */

public class SubPageThreeActivity extends Activity implements OnClickListener {

	public static Facebook mFacebook = new Facebook(C.FACEBOOK_APP_ID);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subpagethree);

		// Connect buttons

		Button btn1 = (Button) findViewById(R.id.btnFir);
		btn1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(SubPageThreeActivity.this,
						InforActivity.class);
				startActivity(myIntent);
			}
		});

		Button btn2 = (Button) findViewById(R.id.btnLogin);
		btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				login();
			}
		});

		Button btn3 = (Button) findViewById(R.id.btnLogout);
		btn3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				logout();
				Toast.makeText(getApplicationContext(), "Log out.", 1000)
						.show();
			}
		});

		Button btn4 = (Button) findViewById(R.id.btnGui);
		btn4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Intent myIntent = new Intent(SubPageThreeActivity.this,
						GuiActivity.class);
				startActivity(myIntent);
			}
		});

		Button btn5 = (Button) findViewById(R.id.btnList);
		btn5.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Intent myIntent = new Intent(SubPageThreeActivity.this,
						ListActivity.class);
				startActivity(myIntent);
			}
		});

		Button btn6 = (Button) findViewById(R.id.btnDev);
		btn6.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Intent myIntent = new Intent(SubPageThreeActivity.this,
						DevActivity.class);
				startActivity(myIntent);
			}
		});

	}


	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			if (requestCode == C.FACEBOOK_AUTH_CODE) {
				mFacebook.authorizeCallback(requestCode, resultCode, data);
			}
		} else {
			if (requestCode == C.FACEBOOK_AUTH_CODE) {
				mFacebook.authorizeCallback(requestCode, resultCode, data);
			}
		}
	}

	// login to Facebook
	
		private void login() {
			mFacebook.authorize2(this,
					new String[] { "publish_stream, user_photos, email" },
					new AuthorizeListener());
		}

		// Sharing
	
	public static void feed(String text) {
		try {
			RequestListener requestListener = new RequestListener() {

				public void onMalformedURLException(MalformedURLException e,
						Object state) {
					// TODO Auto-generated method stub

				}

				public void onIOException(IOException e, Object state) {
					// TODO Auto-generated method stub

				}

				public void onFileNotFoundException(FileNotFoundException e,
						Object state) {
					// TODO Auto-generated method stub

				}

				public void onFacebookError(FacebookError e, Object state) {
					// TODO Auto-generated method stub

				}

				public void onComplete(String response, Object state) {
					// TODO Auto-generated method stub

				}
			};

			// get the date
						final Calendar c = Calendar.getInstance();
						int Year = c.get(Calendar.YEAR);
						int Month = c.get(Calendar.MONTH);
						int Day = c.get(Calendar.DAY_OF_MONTH);
						int DayOfWeek = c.get(Calendar.DAY_OF_WEEK);

						Month = Month + 1;

						String stringDayOfWeek[] = { "", "Sun", "Mon", "Tues", "Wednes", "Thurs", "Fri", "Satur" };

						String stringDay = String.format("%4dY %dM %dD "
								+ stringDayOfWeek[DayOfWeek] + "day", Year, Month, Day);

						Bundle params = new Bundle();
						params.putString("message", stringDay + "\n" + text.toString()
								+ " has(have) completed. -from Hot Bucket");
						params.putString("name", "");
						params.putString("link", "https://www.facebook.com/hotbucket12");
						params.putString("description", "Hot bucket");
						params.putString("picture", "");
			AsyncFacebookRunner mAsyncRunner = new AsyncFacebookRunner(
					mFacebook);
			mAsyncRunner.request("me/feed", params, "POST", requestListener,
					null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	private void logout() {
		try {
			mFacebook.logout(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public class AuthorizeListener implements DialogListener {
		public void onCancel() {
			// TODO Auto-generated method stub
		}

		public void onComplete(Bundle values) {
			// TODO Auto-generated method stub
			if (C.D)
				Log.v(C.LOG_TAG, "::: onComplete :::");
		}

		public void onError(DialogError e) {
			// TODO Auto-generated method stub
		}

		public void onFacebookError(FacebookError e) {
			// TODO Auto-generated method stub
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			String alertTitle = "End";
			new AlertDialog.Builder(SubPageThreeActivity.this)
					.setTitle(alertTitle)
					.setMessage("Do you want to exit?")
					.setIcon(R.drawable.ic_launcher)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									moveTaskToBack(true);
									finish();
									ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
									am.restartPackage(getPackageName());
								}
							}).setNegativeButton("No", null).show();
		}
		return true;
	}

}
