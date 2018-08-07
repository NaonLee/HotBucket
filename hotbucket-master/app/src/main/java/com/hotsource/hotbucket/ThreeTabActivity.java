package com.hotsource.hotbucket;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class ThreeTabActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupTabs();
	}

	private void setupTabs() {
		final TabHost tabHost = getTabHost();
		TabHost.TabSpec spec = null;
		Intent intent = null;

		spec = tabHost.newTabSpec("tab01"); // The first tab
		intent = new Intent(this, SubPageOneActivity.class);
		intent.putExtra("mode", "new");
		intent.putExtra("initialize", true);
		intent.putExtra("request", true);
		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

		spec.setContent(intent);
		spec.setIndicator("", getResources().getDrawable(R.drawable.tab1));
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec("tab02"); // The second tab
		intent = new Intent(this, SubPageTwoActivity.class);
		intent.putExtra("mode", "new");
		intent.putExtra("initialize", true);
		intent.putExtra("request", true);
		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

		spec.setContent(intent);
		spec.setIndicator("", getResources().getDrawable(R.drawable.tab2));
		tabHost.addTab(spec);

		spec = tabHost.newTabSpec("tab03"); // The third tab
		intent = new Intent(this, SubPageThreeActivity.class);
		intent.putExtra("mode", "new");
		intent.putExtra("initialize", true);
		intent.putExtra("request", true);
		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

		spec.setContent(intent);
		spec.setIndicator("", getResources().getDrawable(R.drawable.tab3));
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);

		for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
			tabHost.getTabWidget().getChildAt(i)
					.setBackgroundColor(Color.parseColor("#dbfadb"));
		}

		tabHost.getTabWidget().setCurrentTab(0);
		tabHost.getTabWidget().getChildAt(0)
				.setBackgroundColor(Color.parseColor("#66cc99"));

		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			public void onTabChanged(String tabId) {

				for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
					tabHost.getTabWidget().getChildAt(i)
							.setBackgroundColor(Color.parseColor("#dbfadb"));
				}
				tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab())
						.setBackgroundColor(Color.parseColor("#66cc99"));
			}
		});

	}

}
