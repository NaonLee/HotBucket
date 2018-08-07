package com.hotsource.hotbucket;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * List page on the first tab
 * 
 */

public class SubPageOneActivity extends Activity {

	public static Context mContext = null;
	public RatingBar rating;
	public ArrayList<BucketItem> bkitem;
	public BucketItem bk;
	public BucketData bucketdb;
	public static BucketListAdapter bkadapter;
	public int spinnerposition = 0;

	int number = 100;
	String[] ss = new String[number];
	String[] bktodo = new String[number];
	Integer[] star = new Integer[number];
	String[] finpic = new String[number];
	String[] findate = new String[number];
	String[] memo = new String[number];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		mContext = this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.subpageone);

		rating = (RatingBar) findViewById(R.id.setRating);

		bucketdb = MainActivity.getDB();

		bkitem = new ArrayList<BucketItem>();

		ArrayAdapter<CharSequence> adapter = ArrayAdapter
				.createFromResource(this, R.array.array_array,
						android.R.layout.simple_spinner_item);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		Spinner spinner = (Spinner) findViewById(R.id.spinner_array);

		spinner.setAdapter(adapter);

		ArrayAdapter arrayadapter = ArrayAdapter.createFromResource(this,
				R.array.array_array, android.R.layout.simple_spinner_item);
		arrayadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(arrayadapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			// Event on spinner selection
			public void onItemSelected(AdapterView<?> parent, View v,
					int position, long id) {
				switch (position) {
				case 0:
					calldata();
					spinnerposition = 0;
					break;
				case 1: // sort by rating
					starsort();
					spinnerposition = 1;
					break;
				case 2: // sort by completion
					finsort();
					spinnerposition = 2;
					break;

				}
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	
		// add button
		Button btn1 = (Button) findViewById(R.id.btnAdd);
		btn1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Intent myIntent = new Intent(SubPageOneActivity.this,
						Input_Activity.class);
				startActivityForResult(myIntent, 0);
				listadapterconnect();
			}
		});
	}

	public void listadapterconnect() {
		bkadapter = new BucketListAdapter(SubPageOneActivity.this, // recieve an adapter
				R.layout.listitem, bkitem);
		final ListView bklist = (ListView) findViewById(R.id.listview); // Listview

																		
		bklist.setAdapter(bkadapter);

		bklist.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {

				final String string = (String) parent.getItemAtPosition(
						position).toString();

				AlertDialog diaBox = new AlertDialog.Builder(
						SubPageOneActivity.this)
						.setTitle("Delete")
						.setMessage("Are you sure you want to delete it?")
						.setIcon(R.drawable.ic_launcher)
						.setPositiveButton("Delete",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										Toast.makeText(getApplicationContext(),
												string + "will be deleted.", 1000)
												.show();
										bucketdb.deleteMember(string);
										if (spinnerposition == 0)
											calldata();
										if (spinnerposition == 1)
											starsort();
										if (spinnerposition == 2)
											finsort();
									}
								}).setNegativeButton("Cancel", null).create();
				diaBox.show();
				return false;
			}
		});

		bklist.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				bktodo[position] = searchnotfin(ss[position]);

				if (searchfin(bktodo[position]) == 1) {
					star[position] = searchstar(position + 1, bktodo[position]);
					findate[position] = searchdate(position + 1, bktodo[position]);
					memo[position] = searchmemo(position + 1, bktodo[position]);
					finpic[position] = searchpic(position + 1, bktodo[position]);
					
					  Intent i = new Intent(SubPageOneActivity.this, ClearBlogActivity.class);
					  
					  i.putExtra("todo", bktodo[position]);
					  i.putExtra("date", findate[position]);
					  i.putExtra("pic", finpic[position]);
					  i.putExtra("star", star[position]);
					  i.putExtra("memo", memo[position]);
					  
					  
					  startActivity(i);

				} else {
					Toast.makeText(getApplicationContext(), "Incomplete", 1000)
					.show();
		}
	}

});
}

public void calldata() {
int i = 0;
Cursor _cursor = bucketdb.rawQuery("Select * from bucketlist", null);
_cursor.moveToFirst();
bkitem = new ArrayList<BucketItem>();
while (_cursor.isAfterLast() == false) {
	bk = new BucketItem(_cursor.getString(0).toString(),
			_cursor.getFloat(3));
	ss[i++] = bk.getTitle();
	bkitem.add(bk);
	_cursor.moveToNext();
}
_cursor.close();
listadapterconnect();
}

public void starsort() { // sort by rating
		int i = 0;
		Cursor _cursor = bucketdb.rawQuery(
				"Select * from bucketlist order by star desc", null);
		_cursor.moveToFirst();
		bkitem = new ArrayList<BucketItem>();
		while (_cursor.isAfterLast() == false) {
			bk = new BucketItem(_cursor.getString(0).toString(),
					_cursor.getFloat(3));

			ss[i++] = bk.getTitle();
			bkitem.add(bk);
			_cursor.moveToNext();
		}
		_cursor.close();
		listadapterconnect();
	}

	public void finsort() { // sort by completion
		int i = 0;
		Cursor _cursor = bucketdb.rawQuery(
				"Select * from bucketlist order by fin desc", null);
		_cursor.moveToFirst();
		bkitem = new ArrayList<BucketItem>();
		while (_cursor.isAfterLast() == false) {
			bk = new BucketItem(_cursor.getString(0).toString(),
					_cursor.getFloat(3));
			ss[i++] = bk.getTitle();
			bkitem.add(bk);
			_cursor.moveToNext();
		}
		_cursor.close();
		listadapterconnect();
	}



	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (spinnerposition == 0)
			calldata();
		if (spinnerposition == 1)
			starsort();
		if (spinnerposition == 2)
			finsort();
	}

	public int searchstar(int position, String sItem) {
		Cursor _cursor = bucketdb.rawQuery(
				"select * from bucketlist where fin = '1' and item='" + sItem
						+ "'", null);
		_cursor.moveToFirst();
		return _cursor.getInt(3);

	}

	public String searchdate(int position, String sItem) {
		Cursor _cursor = bucketdb.rawQuery(
				"select * from bucketlist where fin = '1' and item='" + sItem
						+ "'", null);
		_cursor.moveToFirst();
		return _cursor.getString(1);

	}

	public String searchmemo(int position, String sItem) {
		Cursor _cursor = bucketdb.rawQuery(
				"select * from bucketlist where fin = '1' and item='" + sItem
						+ "'", null);
		_cursor.moveToFirst();
		return _cursor.getString(4);

	}
	public String searchpic(int position, String sItem) {
		Cursor _cursor = bucketdb.rawQuery(
				"select * from bucketlist where fin = '1' and item='" + sItem
						+ "'", null);
		_cursor.moveToFirst();
		return _cursor.getString(2);

	}
	public String searchnotfin(String sItem) { // give a name
		Cursor _cursor = bucketdb.rawQuery(
				"select item from bucketlist where item = '" + sItem + "'",
				null);
		_cursor.moveToFirst();
		return _cursor.getString(0);
	}

	public int searchfin(String sItem) { // Check completion

		Cursor _cursor = bucketdb.rawQuery(
				"select fin from bucketlist where item ='" + sItem + "'", null);
		_cursor.moveToFirst();

		return _cursor.getInt(0);
	}

	// / When you press the back button
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			switch (keyCode) {
			case KeyEvent.KEYCODE_BACK:
				String alertTitle = "End";
				new AlertDialog.Builder(SubPageOneActivity.this)
						.setTitle(alertTitle)
						.setMessage("Do you want to exit")
						.setIcon(R.drawable.ic_launcher)
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										moveTaskToBack(true);
										finish();

										// Release processes remaining in memory
									ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
									am.restartPackage(getPackageName());
									am.killBackgroundProcesses(getPackageName());
								}
							}).setNegativeButton("No", null).show();
		}
		return true;
	}
}
