package com.utad.baccus.controller.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import com.utad.baccus.R;
import com.utad.baccus.controller.fragment.WebFragment;

public class WebActivity extends ActionBarActivity {
	public static final String EXTRA_URL = "com.utad.baccus.controller.EXTRA_URL";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_fragment_container);
		// setContentView(R.layout.fragment_web);

		FragmentManager manager = getSupportFragmentManager();

		if (manager.findFragmentById(R.id.fragment_placeholder) == null) {
			WebFragment fragment = new WebFragment();
			Bundle arguments = new Bundle();
			arguments.putSerializable(WebFragment.ARGS_URL, getIntent()
					.getSerializableExtra(EXTRA_URL));

			fragment.setArguments(arguments);

			getSupportFragmentManager().beginTransaction()
					.add(R.id.fragment_placeholder, fragment).commit();

		}
	}
}