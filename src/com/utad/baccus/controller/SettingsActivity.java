package com.utad.baccus.controller;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.utad.baccus.R;

public class SettingsActivity extends FragmentActivity {

	/**
	 * Singleton
	 */
	private static SettingsActivity sInstance = null;
	
	public static final int REQUEST_SELECT_SCALETYPE = 0;
	private int mValue = SettingsFragment.OPTION_NORMAL;

	public static SettingsActivity getInstance() {
		if (sInstance == null) {
			sInstance = new SettingsActivity();
		}

		return sInstance;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_fragment_container);

		SettingsFragment fragment = new SettingsFragment();

	
		Bundle arguments = new Bundle();
		
		arguments.putInt(SettingsFragment.OPTION_SELECTED, mValue);
		fragment.setArguments(arguments);
		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_placeholder, fragment).commit();

	}

}
