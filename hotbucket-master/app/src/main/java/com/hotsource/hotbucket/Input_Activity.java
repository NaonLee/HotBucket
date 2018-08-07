package com.hotsource.hotbucket;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class Input_Activity extends Activity {

	public String todostr;
	public float star;
	public RatingBar rating;
	private Button makelistbtn;
	private Button cancleBtn;
	EditText edit1;
	BucketData bucketdb; // DB

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.input);

		bucketdb = MainActivity.getDB(); // get DB

		edit1 = (EditText) findViewById(R.id.todoEditText); 

		makelistbtn = (Button) findViewById(R.id.inputBtn);
		rating = (RatingBar) findViewById(R.id.ratingBar1); // star

		rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() { 
			// Occurs whenever the user changes the rating

			public void onRatingChanged(RatingBar ratingBar, float rating,
					boolean fromUser) {
				star = rating; // save changed rating
			}
		});

		makelistbtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				todostr = edit1.getText().toString();

				if (todostr.length() == 0) {
					// When it's blank
					Toast.makeText(getApplicationContext(), "Enter the contents", 1000)
							.show();
				} else { // When it isn't balnk
					bucketdb.insertMember(todostr, star); // add DB
					Toast.makeText(getApplicationContext(), "Add", 1000).show();

					finish();
				}
			}
		});

		cancleBtn = (Button) findViewById(R.id.cancleBtn);

		cancleBtn.setOnClickListener(new OnClickListener() { // When press cancel button on add window
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Cancel", 1000)
								.show();

						finish();
					}
				});

	}

}