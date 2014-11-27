package com.utad.baccus.controller.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import com.utad.baccus.R;
import com.utad.baccus.controller.fragment.WineHouseFragment;
import com.utad.baccus.controller.fragment.WineListFragment;

public class WineListActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_wine_list);

		FragmentManager manager = getSupportFragmentManager();

		if (findViewById(R.id.list_fragment) != null) {
			if (manager.findFragmentById(R.id.list_fragment) == null) {
				WineListFragment fragment = new WineListFragment();
				getSupportFragmentManager().beginTransaction()
						.add(R.id.list_fragment, fragment).commit();

			}
		}
		if (findViewById(R.id.winehouse_fragment) != null) {
			if (manager.findFragmentById(R.id.winehouse_fragment) == null) {
				WineHouseFragment fragment = new WineHouseFragment();
				Bundle args = new Bundle();
				args.putInt(WineHouseFragment.SELECT_WINE_INDEX, 0);
				fragment.setArguments(args);
				
				manager.beginTransaction().add(R.id.winehouse_fragment, fragment)
						.commit();

			}

		}

	}
}
