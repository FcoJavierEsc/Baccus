package com.utad.baccus.controller;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBarActivity;
import android.widget.TabHost.TabSpec;

import com.utad.baccus.R;
import com.utad.baccus.model.Wine;
import com.utad.baccus.model.Winehouse;

public class WinehouseActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_winehouse);

		FragmentTabHost tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		tabHost.setup(this, getSupportFragmentManager(),
				android.R.id.tabcontent);
		Winehouse winehouse = Winehouse.getInstance();
		for (int i = 0; i < winehouse.getWineCount(); i++) {

			Bundle argumentos = new Bundle();

			Wine currentWine = winehouse.getWine(i);
			argumentos.putSerializable(WineFragment.ARGS_WINE, currentWine);

			TabSpec tabWine = tabHost.newTabSpec(currentWine.getName());
			tabWine.setIndicator(currentWine.getName());

			tabHost.addTab(tabWine, WineFragment.class, argumentos);

		}
	}

}
