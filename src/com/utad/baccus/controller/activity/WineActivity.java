package com.utad.baccus.controller.activity;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.utad.baccus.R;
import com.utad.baccus.controller.fragment.WineFragment;

public class WineActivity extends ActionBarActivity {
	public static final String EXTRA_WINE = "com.utad.baccus.controller.WineActivity.extra_wine";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_container);

		FragmentManager manager = getSupportFragmentManager();

		if (manager.findFragmentById(R.id.fragment_placeholder) == null) {
			WineFragment fragment = new WineFragment();
			Bundle arguments = new Bundle();
			arguments.putSerializable(WineFragment.ARGS_WINE, getIntent()
					.getSerializableExtra(EXTRA_WINE));

			fragment.setArguments(arguments);

			manager.beginTransaction().add(R.id.fragment_placeholder, fragment)
					.commit();
		}
	}
}
