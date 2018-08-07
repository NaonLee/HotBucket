package com.hotsource.hotbucket;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Widget
 * 
 */

public class Widget extends AppWidgetProvider {
	
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
		 for ( int i = 0; i < appWidgetIds.length; i++ ){

				int widgetId = appWidgetIds[i];

				Intent inputIntent = new Intent( context, Input_Activity.class );
				Intent listIntent = new Intent( context, SubPageOneActivity.class );
				Intent albumIntent = new Intent( context, SubPageTwoActivity.class );

				
				PendingIntent inputPIntent = PendingIntent.getActivity( context, 0, inputIntent, 0 );
				PendingIntent listPIntent = PendingIntent.getActivity( context, 0, listIntent, 0 );
				PendingIntent albumPIntent = PendingIntent.getActivity( context, 0, albumIntent, 0 );

				RemoteViews remoteView = new RemoteViews( context.getPackageName(),  R.layout.widgetlayout );

				remoteView.setOnClickPendingIntent(R.id.btnWi1, inputPIntent);
				remoteView.setOnClickPendingIntent(R.id.btnWi2, listPIntent);
				remoteView.setOnClickPendingIntent(R.id.btnWi3, albumPIntent);

				appWidgetManager.updateAppWidget( widgetId, remoteView );

			}
	}
}
