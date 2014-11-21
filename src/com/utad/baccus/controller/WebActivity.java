package com.utad.baccus.controller;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.utad.baccus.R;

public class WebActivity extends ActionBarActivity {
	public static final String EXTRA_URL = "com.utad.baccus.controller.EXTRA_URL";

//	private static final String CURRENT_URL = "CURRENT_URL";
//	private static final int MENU_RELOAD = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WebFragment fragment = new WebFragment();

		setContentView(R.layout.activity_fragment_container);
		// setContentView(R.layout.fragment_web);

		Bundle arguments = new Bundle();
		arguments.putSerializable(WebFragment.ARGS_URL, getIntent()
				.getSerializableExtra(EXTRA_URL));

		fragment.setArguments(arguments);

		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_placeholder, fragment).commit();

	}


}
