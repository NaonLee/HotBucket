package com.hotsource.hotbucket;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BucketData extends SQLiteOpenHelper {
	private static String dbname = "bucket.db";
	private static String createTable = "CREATE TABLE bucketlist(item TEXT,date DATE, pic text, star integer, memo text, fin integer)";
	// fin 0 - Uncompleted 1- completed
	SQLiteDatabase mdatabase;
	private static final String TAG = "BucketData";

	public BucketData(Context context) {
		super(context, dbname, null, 1);
		mdatabase = this.getWritableDatabase();
		createTable();

	}

	private void createTable() {
		String sql = "select count(*) from sqlite_master where name='bucketlist'";
		Cursor c = mdatabase.rawQuery(sql, null); 
		c.moveToFirst();
		int count = c.getInt(0);

		if (count == 0) {
			mdatabase.execSQL(createTable);
		}

	}
	
	public void insertMember(String item, float star) {	//add item
		String sql = "insert into bucketlist values('" + item
				+ "','null','null','" + star + "','null','0')";
		mdatabase.execSQL(sql);

	}

	public void updateMember(String item, String date, String memo, String pic) {
		String sql = "update bucketlist set date='" + date + "',memo='" + memo
				+ "',pic='" + pic + "',fin= '1' where item='" + item + "'";
		mdatabase.execSQL(sql);
	} // save

	public int isfinish(String item) {
		int isfin = 0;
		Cursor _cursor = rawQuery("select fin from bucketlist where item ='"
				+ item + "'", null);

		_cursor.moveToFirst();
		isfin = _cursor.getInt(0);
		_cursor.close();
		return isfin;
	}

	public void deleteMember(String item) {
		String sql = "delete from bucketlist where item='" + item + "'";
		mdatabase.execSQL(sql);
	}

	
	/*
	 * bring completed items only
	 */
	public void finDataPlz(){
		String sql = "select item,pic,date,memo from bucketlist where fin='1'";
		mdatabase.execSQL(sql);
	}

	public Cursor rawQuery(String string, Object object) {
		Cursor cursor = mdatabase.rawQuery(string, null);
		return cursor;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS bucketlist");
		onCreate(db);
	}
}
