package com.utad.baccus.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.RadioGroup;

import com.utad.baccus.R;

public class SettingsActivity extends FragmentActivity {

	public static final int REQUEST_SELECT_SCALETYPE = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_container);

		SettingFragment fragment = new SettingFragment();

		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_placeholder, fragment).commit();

	}

	
}
