package com.utad.baccus.controller.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.utad.baccus.R;
import com.utad.baccus.controller.fragment.WineHouseFragment;

public class WinehouseActivity extends ActionBarActivity {

	public static final String SELECT_WINE_INDEX = "com.utad.baccus.SELECT_WINE_INDEX";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int rId = R.id.list_fragment;

		setContentView(R.layout.activity_wine_list);

		FragmentManager manager = getSupportFragmentManager();

		if (manager.findFragmentById(rId) == null) {
			
			
			WineHouseFragment fragment = new WineHouseFragment();
			Bundle arguments = new Bundle();
			arguments.putSerializable(WineHouseFragment.SELECT_WINE_INDEX,
					getIntent().getIntExtra(SELECT_WINE_INDEX, 0));
			
			fragment.setArguments(arguments);

			manager.beginTransaction().add(rId, fragment).commit();
		}
	}
}
