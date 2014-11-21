package com.utad.baccus.controller;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.utad.baccus.R;

public class WineActivity extends ActionBarActivity {
	public static final String EXTRA_WINE = "com.utad.baccus.controller.WineActivity.extra_wine";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_container);

		WineFragment fragment = new WineFragment();

		Bundle arguments = new Bundle();
		arguments.putSerializable(WineFragment.ARGS_WINE, getIntent()
				.getSerializableExtra(EXTRA_WINE));

		fragment.setArguments(arguments);

		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_placeholder, fragment).commit();
	}
}
