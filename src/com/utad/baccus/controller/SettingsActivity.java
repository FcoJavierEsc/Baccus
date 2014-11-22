package com.utad.baccus.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.utad.baccus.R;

public class SettingsActivity extends FragmentActivity {

	public static final int REQUEST_SELECT_SCALETYPE = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_fragment_container);
		int defValue;

		Intent valIntent = new Intent(this, SettingsFragment.class);
		defValue = valIntent.getIntExtra(SettingsFragment.OPTION_SELECTED,
				SettingsFragment.OPTION_NORMAL);

		SettingsFragment fragment = new SettingsFragment();

		Bundle arguments = new Bundle();
		arguments.putInt(SettingsFragment.OPTION_SELECTED, defValue);
		fragment.setArguments(arguments);
		
		getSupportFragmentManager()
		.beginTransaction()
		.add(R.id.fragment_placeholder, fragment)
		.commit();

	}

}
