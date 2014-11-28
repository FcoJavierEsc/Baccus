package com.utad.baccus.controller.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.utad.baccus.R;
import com.utad.baccus.controller.fragment.WebFragment;

public class WebActivity extends ActionBarActivity {
	public static final String EXTRA_URL = "com.utad.baccus.controller.EXTRA_URL";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_fragment_container);
	
		FragmentManager manager = getSupportFragmentManager();

		if (manager.findFragmentById(R.id.fragment_placeholder) == null) {
			WebFragment fragment = new WebFragment();
	
			Bundle args = new Bundle();
			args.putSerializable(WebFragment.ARGS_URL, getIntent().getStringExtra(EXTRA_URL));
				
			fragment.setArguments(args);

			getSupportFragmentManager().beginTransaction()
					.add(R.id.fragment_placeholder, fragment).commit();

			ActionBar actionBar = getSupportActionBar();
			actionBar.setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}