package com.hotsource.hotbucket;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Clear_Activity extends ImageSelectHelperActivity {

	private TextView itemtitle;
	private ImageView selectphoto;
	private Uri photouri;
	private Button okBtn;
	private Button resetBtn;

	private TextView mDateDisplay;
	private int mYear;
	private int mMonth;
	private int mDay;
	static final int DATE_DIALOG_ID = 0; // Date

	private TextView mMemo;

	public BucketData bucketdb = MainActivity.getDB();

	private String findate;
	private String memo;
	private String pic;
	public String item;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clear);
		item = BucketListAdapter.getItem();

		mDateDisplay = (TextView) findViewById(R.id.editWhen);
		mDateDisplay.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});

		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		updateDisplay();

		mMemo = (EditText) findViewById(R.id.editMemo);
		itemtitle = (TextView) findViewById(R.id.titletext);
		itemtitle.setText(item);

		selectphoto = (ImageView) findViewById(R.id.ivImageSelected); // photo
		selectphoto.setOnClickListener(new OnClickListener() { // When you press a photo
																// move to gallery
					public void onClick(View v) {
						// TODO Auto-generated method stub
						startSelectImage();
						setImageSizeBoundary(200);// Size of photo
						setCropOption(100,100);// crop
					}
				});

		photouri = Uri.fromFile(getSelectedImageFile()); // convert photo to Uri and save

		okBtn = (Button) findViewById(R.id.okbtn);
		okBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub

				memo = mMemo.getText().toString();
				findate = mDateDisplay.getText().toString();
				pic = photouri.toString();
				bucketdb.updateMember(item, findate, memo, pic);
				Toast.makeText(getApplicationContext(), "Complete", 1000).show();

				Intent myIntent = new Intent(Clear_Activity.this,
						ThreeTabActivity.class);
				startActivity(myIntent);

				finish();

			}
		});

		resetBtn = (Button) findViewById(R.id.resetbtn);

		resetBtn.setOnClickListener(new OnClickListener() { // When press cancel button on complete window
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Cancel", 1000).show();
				onBackPressed();
			}
		});

	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();
		}
	};

	private void updateDisplay() {
		mDateDisplay.setText(new StringBuilder().append(mYear).append("-")
				.append(mMonth + 1).append("-").append(mDay).append(" "));
	}

}