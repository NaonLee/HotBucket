package com.hotsource.hotbucket;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class BucketListAdapter extends BaseAdapter {
	static String passitem;
	public BucketData bucketdb = MainActivity.getDB();

	Context context;
	LayoutInflater Inflater;
	ArrayList<BucketItem> arSrc;
	int layout;
	RatingBar rating;
	static String intentitem;

	public BucketListAdapter(Context context, int layout,
			ArrayList<BucketItem> arSrc) {
		this.context = context;
		this.layout = layout;
		this.arSrc = arSrc;
		Inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return arSrc.size();
	}

	public Object getItem(int position) {
		return arSrc.get(position).title;
	}

	public long getItemId(int position) {
		return position;
	}

	public class ViewHolder {
		public TextView mText;
		public RatingBar mRating;
		public String item;

	}

	static String getItem() {
		return passitem;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final int pos = position;
		final ViewHolder holder;

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = Inflater.inflate(layout, parent, false);
			holder.mText = (TextView) convertView.findViewById(R.id.titleT);
			holder.mRating = (RatingBar) convertView
					.findViewById(R.id.setRating);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.mRating.setRating(arSrc.get(position).getStar());
		holder.mRating.setFocusable(false);
		holder.mText.setText(arSrc.get(position).title);
		holder.item = holder.mText.getText().toString();

		if (bucketdb.isfinish(holder.item) == 0) { // If the item hasn't completed
			Button clearBtn = (Button) convertView.findViewById(R.id.clearBtn);

			clearBtn.setOnClickListener(new Button.OnClickListener() {
				public void onClick(View v) {
					Intent intent;
					Toast.makeText(context, "complete", Toast.LENGTH_LONG).show();

					passitem = holder.item;
					
					intent = new Intent(SubPageOneActivity.mContext,
							Clear_Activity.class);
					context.startActivity(intent);
					((SubPageOneActivity)context).finish();
				
				}
			});

			clearBtn.setFocusable(false);
		}

		if (bucketdb.isfinish(holder.item) == 1) { // If the item has been completed
			Button facebookBtn = (Button) convertView
					.findViewById(R.id.clearBtn); // /button for share on Facebook
			facebookBtn.setText("Share");
			facebookBtn.setOnClickListener(new Button.OnClickListener() {
				public void onClick(View v) {
					// connect
					SubPageThreeActivity.feed(holder.item);
					Toast.makeText(context, "Share has been completed", Toast.LENGTH_LONG).show();
				}
			});
			facebookBtn.setFocusable(false);
		}
		return convertView;
	}
}
