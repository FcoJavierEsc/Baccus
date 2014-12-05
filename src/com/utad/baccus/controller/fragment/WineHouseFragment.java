package com.utad.baccus.controller.fragment;

import android.support.v4.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.utad.baccus.R;
import com.utad.baccus.controller.adapter.WineFragmentAdapter;
import com.utad.baccus.model.Constans;

public class WineHouseFragment extends Fragment {

	public final static String SELECT_WINE_INDEX = "com.utad.baccus.controller.activity.WineHouseFragment";
	private WineFragmentAdapter mAdapter = null;
	private ActionBar mActionBar = null;

	private ViewPager mViewPager = null;
	private MenuItem mFirstItem;
//	private MenuItem mBefItem;
//	private MenuItem mNextItem;
	private MenuItem mLastItem;

	protected void updateActionBarAndSaveLastWine(int index) {
		if (mAdapter != null) {
			mActionBar.setSubtitle(mAdapter.getPageTitle(index));
			mActionBar.setIcon(mAdapter.getImageResource(index));
			saveLastWine();
		}
	}

	private void saveLastWine() {
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(getActivity());

		pref.edit()
				.putInt(Constans.PREF_LAST_WINE, mViewPager.getCurrentItem())
				.commit();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);

		View root = inflater.inflate(R.layout.activity_winehouse, container,
				false);
		mViewPager = (ViewPager) root.findViewById(R.id.pager);

		// bloqueante

		AsyncTask<FragmentManager, Void, WineFragmentAdapter> asinc = new AsyncTask<FragmentManager, Void, WineFragmentAdapter>() {

			private ProgressDialog proDialog = null;

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				proDialog = new ProgressDialog(getActivity());
				proDialog.setTitle("Descargando");
				proDialog.setIndeterminate(true);
				proDialog.setCancelable(false);
				proDialog.show();
			}

			@Override
			protected WineFragmentAdapter doInBackground(
					FragmentManager... params) {
				// TODO Auto-generated method stub
				return new WineFragmentAdapter(params[0]);
			}

			@Override
			protected void onPostExecute(WineFragmentAdapter result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				mAdapter = result;
				mViewPager.setAdapter(mAdapter);

				int position = getArguments().getInt(SELECT_WINE_INDEX, 0);

				// Toast.makeText(getActivity(), "vino " + position,
				// Toast.LENGTH_SHORT)
				// .show();
				showWine(position);

				proDialog.dismiss();
			}

		};

		asinc.execute(getFragmentManager());

		// Bloqueante

		// sustituida mViewPager.setAdapter(mAdapter);

		mActionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int index) {
				showWine(index);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

		return root;

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

		super.onCreateOptionsMenu(menu, inflater);

		if (mAdapter != null) {
			inflater.inflate(R.menu.nextbefore, menu);
			mFirstItem = menu.findItem(R.id.action_first);
//			mBefItem = menu.findItem(R.id.action_before);
//			mNextItem = menu.findItem(R.id.action_next);
			mLastItem = menu.findItem(R.id.action_last);
			int index = mViewPager.getCurrentItem();

			mFirstItem.setEnabled(index != 0);
//			mBefItem.setEnabled(index > 0);
//			mNextItem.setEnabled(index < mAdapter.getCount() - 1);
			mLastItem.setEnabled(index != mAdapter.getCount() - 1);

			MenuItemCompat.setShowAsAction(mFirstItem,
					MenuItem.SHOW_AS_ACTION_ALWAYS);
//			MenuItemCompat.setShowAsAction(mBefItem,
//					MenuItem.SHOW_AS_ACTION_ALWAYS);
//			MenuItemCompat.setShowAsAction(mNextItem,
//					MenuItem.SHOW_AS_ACTION_ALWAYS);
			MenuItemCompat.setShowAsAction(mLastItem,
					MenuItem.SHOW_AS_ACTION_ALWAYS);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int actIndex = mViewPager.getCurrentItem();

		switch (item.getItemId()) {
		case R.id.action_first:
					showWine(0);
			return true;
		case R.id.action_before:
			if (actIndex > 0)
				showWine(actIndex - 1);
			return true;
		case R.id.action_next:
			if (actIndex + 1 < mAdapter.getCount())
				showWine(actIndex + 1);
			return true;
		case R.id.action_last:
			showWine(mAdapter.getCount()-1);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void showWine(int index) {
		updateActionBarAndSaveLastWine(index);
		mViewPager.setCurrentItem(index);
	}
}
