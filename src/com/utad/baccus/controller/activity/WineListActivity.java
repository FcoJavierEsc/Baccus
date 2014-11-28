package com.utad.baccus.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.utad.baccus.R;
import com.utad.baccus.controller.fragment.WineHouseFragment;
import com.utad.baccus.controller.fragment.WineListFragment;
import com.utad.baccus.controller.fragment.WineListFragment.OnWineSelectedListener;

public class WineListActivity extends ActionBarActivity implements
		OnWineSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_wine_list);

		FragmentManager manager = getSupportFragmentManager();

		if (findViewById(R.id.list_fragment) != null) {

			WineListFragment wineListFragment = (WineListFragment) manager
					.findFragmentById(R.id.list_fragment);

			if (wineListFragment == null) {
				wineListFragment = new WineListFragment();
				getSupportFragmentManager().beginTransaction()
						.add(R.id.list_fragment, wineListFragment).commit();

			}
			wineListFragment.setOnWineSelectedListener(this);
		}
		
		if (findViewById(R.id.winehouse_fragment) != null) {
			if (manager.findFragmentById(R.id.winehouse_fragment) == null) {
				WineHouseFragment fragment = new WineHouseFragment();
				Bundle args = new Bundle();
				args.putInt(WineHouseFragment.SELECT_WINE_INDEX, 0);
				fragment.setArguments(args);
				manager.beginTransaction()
						.add(R.id.winehouse_fragment, fragment).commit();

			}

		}
	}

	@Override
	public void onWineSelected(int position) {
		if (findViewById(R.id.winehouse_fragment) != null) {
			FragmentManager mng = getSupportFragmentManager();
			WineHouseFragment frg = (WineHouseFragment) mng
					.findFragmentById(R.id.winehouse_fragment);
			frg.showWine(position);
		} else {
			Intent wineHouseActivityIntent = new Intent(this,
					WinehouseActivity.class);
			wineHouseActivityIntent.putExtra(
					WinehouseActivity.SELECT_WINE_INDEX, position);
			startActivity(wineHouseActivityIntent);
		}

	}

}
