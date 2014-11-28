package com.utad.baccus.controller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.utad.baccus.R;
import com.utad.baccus.controller.adapter.WineFragmentAdapter;

public class WineHouseFragment extends Fragment {

	public final static String SELECT_WINE_INDEX = "com.utad.baccus.controller.activity.WineHouseFragment";
	private WineFragmentAdapter mAdapter = null;
	private ActionBar mActionBar = null;

	private ViewPager mViewPager = null;
	private MenuItem befItem;
	private MenuItem nextItem;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


		super.onCreateView(inflater, container, savedInstanceState);

		View root = inflater.inflate(R.layout.activity_winehouse, container, false);
		mViewPager = (ViewPager) root.findViewById(R.id.pager);
		mAdapter = new WineFragmentAdapter(getFragmentManager());
		mViewPager.setAdapter(mAdapter);
		mActionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int index) {
				showWine(index);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}

			@Override
			public void onPageScrollStateChanged(int arg0) {}
		});
		
		int position =getActivity().getIntent().getIntExtra(SELECT_WINE_INDEX, 0);
		Toast.makeText(getActivity(), "vino "+position,Toast.LENGTH_SHORT).show();
        showWine(position);
		return root;

	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		super.onCreateOptionsMenu(menu, inflater);

		inflater.inflate(R.menu.nextbefore, menu);
		befItem = menu.findItem(R.id.action_before);
		nextItem = menu.findItem(R.id.action_next);
		int index = mViewPager.getCurrentItem();

		befItem.setEnabled(index > 0);
		nextItem.setEnabled(index < mAdapter.getCount() - 1);

		MenuItemCompat.setShowAsAction(nextItem, MenuItem.SHOW_AS_ACTION_ALWAYS);
		MenuItemCompat.setShowAsAction(befItem, MenuItem.SHOW_AS_ACTION_ALWAYS);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int actIndex = mViewPager.getCurrentItem();

		switch (item.getItemId()) {
		case R.id.action_next:
			if (actIndex + 1 < mAdapter.getCount()) 
				showWine(actIndex + 1);			
			return true;
			
		case R.id.action_before:
			if (actIndex>0)
				showWine(actIndex-1);
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void showWine(int index) {
		mActionBar.setSubtitle(mAdapter.getPageTitle(index));
		mActionBar.setIcon(mAdapter.getImageResource(index));
		mViewPager.setCurrentItem(index);
	}
}
