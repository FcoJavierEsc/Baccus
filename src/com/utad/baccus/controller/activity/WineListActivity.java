package com.utad.baccus.controller.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import com.utad.baccus.R;
import com.utad.baccus.controller.fragment.WineListFragment;

public class WineListActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		

		setContentView(R.layout.activity_fragment_container);

		FragmentManager manager = getSupportFragmentManager();

		if (manager.findFragmentById(R.id.fragment_placeholder) == null) {
			WineListFragment fragment = new WineListFragment();
				getSupportFragmentManager().beginTransaction()
					.add(R.id.fragment_placeholder, fragment).commit();
		

		}
	}

}
