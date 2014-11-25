package com.utad.baccus.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.utad.baccus.R;
import com.utad.baccus.controller.adapter.WineFragmentAdapter;

public class WinehouseActivity extends ActionBarActivity {
	
	public static final String  SELECT_WINE_INDEX = "com.utad.baccus.SELECT_WINE_INDEX";
	private WineFragmentAdapter mAdapter = null;
	private ActionBar mActionBar = null;
	private MenuItem befItem;
	private MenuItem nextItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_winehouse);

		final ViewPager pager = (ViewPager) findViewById(R.id.pager);

		mAdapter = new WineFragmentAdapter(getSupportFragmentManager());

		pager.setAdapter(mAdapter);

		getSupportActionBar().setSubtitle(pager.getAdapter().getPageTitle(0));

		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int index) {

				updateActionBar(index);

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

		mActionBar = getSupportActionBar();
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

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
		
		updateActionBar(getIntent().getIntExtra(SELECT_WINE_INDEX, 0));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.nextbefore, menu);
		befItem = menu.findItem(R.id.action_before);
		nextItem = menu.findItem(R.id.action_next);
		int index = mActionBar.getSelectedNavigationIndex();
		
        befItem.setEnabled(index > 0);
        nextItem.setEnabled(index<mAdapter.getCount()-1);
		
        MenuItemCompat
				.setShowAsAction(nextItem, MenuItem.SHOW_AS_ACTION_ALWAYS);
		MenuItemCompat.setShowAsAction(befItem, MenuItem.SHOW_AS_ACTION_ALWAYS);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		boolean defaultValue = super.onOptionsItemSelected(item);
		int actIndex = mActionBar.getSelectedNavigationIndex();

		switch (item.getItemId()) {
		case R.id.action_next:
			if (actIndex + 1 < mAdapter.getCount())
				updateActionBar(actIndex + 1);
			
			break;

		case R.id.action_before:
			if (actIndex > 0)
				updateActionBar(actIndex - 1);
			
			break;

		}

		return defaultValue;
	}

	private void updateActionBar(int index) {
		// mActionBar = getSupportActionBar();
		mActionBar.setSubtitle(mAdapter.getPageTitle(index));
		mActionBar.setIcon(mAdapter.getImageResource(index));
		mActionBar.setSelectedNavigationItem(index);
	}
}
