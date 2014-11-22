package com.utad.baccus.controller.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;

import com.utad.baccus.R;
import com.utad.baccus.controller.adapter.WineFragmentAdapter;

public class WinehouseActivity extends ActionBarActivity {
	WineFragmentAdapter mAdapter = null;
	ActionBar mActionBar = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_winehouse);

		final ViewPager pager = (ViewPager) findViewById(R.id.pager);

		mAdapter = new WineFragmentAdapter(getSupportFragmentManager());

		pager.setAdapter(new WineFragmentAdapter(getSupportFragmentManager()));

		getSupportActionBar().setSubtitle(pager.getAdapter().getPageTitle(0));

		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int index) {

				updateActionBar(index);

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

		mActionBar = getSupportActionBar();
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		updateActionBar(0);

		for (int i = 0; i < mAdapter.getCount(); i++) {
			Tab tab = mActionBar.newTab();
			tab.setText(mAdapter.getPageTitle(i));
			tab.setTabListener(new TabListener() {

				@Override
				public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
				}

				@Override
				public void onTabSelected(Tab tab, FragmentTransaction trx) {
					pager.setCurrentItem(tab.getPosition());
				}

				@Override
				public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
				}
			});

			mActionBar.addTab(tab);

		}

	}

	private void updateActionBar(int index) {
		mActionBar = getSupportActionBar();
		mActionBar.setSubtitle(mAdapter.getPageTitle(index));
		mActionBar.setIcon(mAdapter.getImageResource(index));

	}
}
