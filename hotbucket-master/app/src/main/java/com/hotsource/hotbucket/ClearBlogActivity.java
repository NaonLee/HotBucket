package com.hotsource.hotbucket;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class ClearBlogActivity extends Activity{
	String blogtodo;
	String blogdate;
	Uri blogpic;
	String blogstrpic;
	int blogstar;
	String blogmemo;
	private Button resetbtn;
	private Button okbtn;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clearblog);
		//get disply size
				Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
		        int displayWidth = display.getWidth();
		        int displayHeight = display.getHeight();
		
		
		
		Intent i = getIntent(); // get a value
		blogtodo = i.getStringExtra("todo");
		blogdate = i.getStringExtra("date");
		blogstrpic = i.getStringExtra("pic");
		blogstar = i.getIntExtra("star", 1);
		blogmemo = i.getStringExtra("memo");
		 blogpic = Uri.parse(blogstrpic);
		
		 ImageView CBlogImg = (ImageView) findViewById(R.id.CBlogImg);
		 CBlogImg.setImageURI(blogpic);
		 LayoutParams params = (LayoutParams) CBlogImg.getLayoutParams();
		 params.height = displayWidth/5*3;
		 params.width =  displayWidth/5*3;
		 CBlogImg.setLayoutParams(params);
		
		 
		TextView CBlogtv = (TextView) findViewById(R.id.Blogtodo);
		CBlogtv.setText(blogtodo);
		TextView CBlogdate = (TextView) findViewById(R.id.CBlogDatatext);
		CBlogdate.setText(blogdate);
		RatingBar CBlogstar = (RatingBar) findViewById(R.id.ratingBar1);
		CBlogstar.setRating(blogstar);
		TextView CBlogmemo = (TextView) findViewById(R.id.CBlogMemotext);
		CBlogmemo.setText(blogmemo);
		
		
		 resetbtn = (Button) findViewById(R.id.resetbtn);
		 okbtn = (Button) findViewById(R.id.okbtn);
		 
		 resetbtn = (Button) findViewById(R.id.resetbtn);
		 okbtn = (Button) findViewById(R.id.okbtn);
		 
		 resetbtn.setOnClickListener(new OnClickListener() { // When you press cancel button on add window
					public void onClick(View v) {
						Toast.makeText(getApplicationContext(), "Cancel",
								1000).show();
				
						finish();
					}
				});
		 
		 okbtn.setOnClickListener(new OnClickListener() { // When you press share button on add window
							1000).show();

					SubPageThreeActivity.feed(blogtodo);
				}
			});
		 
		 
		 
	}
}