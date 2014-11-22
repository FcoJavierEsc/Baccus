package com.utad.baccus.controller.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;

import com.utad.baccus.R;
import com.utad.baccus.controller.adapter.WineFragmentAdapter;

public class WinehouseActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_winehouse);

		final ViewPager pager = (ViewPager) findViewById(R.id.pager);
		
		

		pager.setAdapter(new WineFragmentAdapter(getSupportFragmentManager()));

		getSupportActionBar().setSubtitle(pager.getAdapter().getPageTitle(0));
		
		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int index) {
				getSupportActionBar().setSubtitle(
						pager.getAdapter().getPageTitle(index));
				getSupportActionBar().setIcon(R.drawable.vegaval);
		
			
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}
}
