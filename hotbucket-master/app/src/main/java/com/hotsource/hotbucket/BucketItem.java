package com.hotsource.hotbucket;

import java.util.ArrayList;
import java.util.Date;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.RatingBar;

public class BucketItem {

	public String title;		//The things you want to do
	private float star;			//Importance
	private Boolean complete;	//Whether it's done
	private Date completdate;	//The date it has completed
	private Uri potouri;		//photo
	

	public BucketItem(String title, float star) {
		this.title = title;
		this.star = star;
		
		complete = false;
		completdate = null;
		potouri = null;
	}

	public void setcomplete(int i) { //method for changing status(complete or incomplete)
		if (i == 1) {
			this.complete = true;
		} else if (i == 0) {
			this.complete = false;
		}
	}

	public void setcompletedate(Date date) {
		this.completdate = date;
	}

	public void setUri(Uri uri) {
		this.potouri = uri;
	}
	
	public String getTitle(){
		return title;
	}
	
	public float getStar(){
		return star;
	}
	
	
	public Boolean getComplete(){
		return complete;
	}
	
	public Uri getUri(){
		return potouri;
	}
}
