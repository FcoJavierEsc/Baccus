package com.utad.baccus.controller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.utad.baccus.R;
import com.utad.baccus.controller.activity.SettingsActivity;
import com.utad.baccus.controller.activity.WebActivity;
import com.utad.baccus.model.Wine;

public class WineFragment extends Fragment {
	public static final String ARGS_WINE = "com.utad.baccus.controller.WineFragment.arg_wine";
	public static final String CURRENT_STYLE_TYPE = "com.utad.baccus.controller.WineFragment.arg_currentsytletype";
	private Wine mWine = null;
	private ImageView mWineImage = null;

	private int mTypeScale = -1;

	// private View root = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);

		if (savedInstanceState == null) {
			mTypeScale = SettingsFragment.OPTION_NORMAL;
		}

		View root = inflater.inflate(R.layout.fragment_wine, container, false);

		mWine = (Wine) getArguments().getSerializable(ARGS_WINE);

		TextView wineName = (TextView) root.findViewById(R.id.wine_name);
		wineName.setText(mWine.getName());

		TextView wineType = (TextView) root.findViewById(R.id.wine_type);
		wineType.setText(mWine.getType());

		TextView winehouse = (TextView) root.findViewById(R.id.winehouse);
		winehouse.setText(mWine.getWinehouse());

		TextView notes = (TextView) root.findViewById(R.id.wine_notes);
		notes.setText(mWine.getNotes());

		mWineImage = (ImageView) root.findViewById(R.id.wine_image);
		mWineImage.setImageResource(mWine.getImage());
		if (savedInstanceState != null) {
			if (savedInstanceState.containsKey(CURRENT_STYLE_TYPE)) {
				mWineImage.setScaleType((ScaleType) savedInstanceState
						.getSerializable(CURRENT_STYLE_TYPE));
			}
		}
		// Vamos a crear los textos de las uvas
		LinearLayout grapesContainer = (LinearLayout) root
				.findViewById(R.id.grapes);
		for (String grape : mWine.getGrapes()) {
			TextView text = new TextView(getActivity());
			text.setText(grape);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);
			text.setLayoutParams(params);

			grapesContainer.addView(text);
		}

		RatingBar ratingBar = (RatingBar) root.findViewById(R.id.rating);
		ratingBar.setProgress(mWine.getRating());

		Button webButton = (Button) root.findViewById(R.id.web_button);

		webButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent webIntent = new Intent(getActivity(), WebActivity.class);
				webIntent.putExtra(WebActivity.EXTRA_URL, mWine.getURL());
				startActivity(webIntent);
			}
		});

		return root;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.main, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean defaultValue = super.onOptionsItemSelected(item);

		switch (item.getItemId()) {
		case R.id.action_settings:
			Intent settingsIntent = new Intent(getActivity(),
					SettingsActivity.class);
			settingsIntent.putExtra(SettingsFragment.OPTION_SELECTED,
					mTypeScale);
			startActivityForResult(settingsIntent,
					SettingsActivity.REQUEST_SELECT_SCALETYPE);
			defaultValue = true;
			break;
		}

		return defaultValue;
	}

	@Override
	public void onActivityResult(int requestCode, int result, Intent intent) {
		super.onActivityResult(requestCode, result, intent);

		if (requestCode == SettingsActivity.REQUEST_SELECT_SCALETYPE) {
			if (result == Activity.RESULT_OK) {
				mTypeScale = intent.getIntExtra(
						SettingsFragment.OPTION_SELECTED, mTypeScale);
				switch (mTypeScale) {
				case SettingsFragment.OPTION_NORMAL:
					mWineImage.setScaleType(ScaleType.FIT_CENTER);
					break;
				case SettingsFragment.OPTION_FIT:
					mWineImage.setScaleType(ScaleType.FIT_XY);
					break;
				}
			}
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mWineImage != null) {
			outState.putSerializable(CURRENT_STYLE_TYPE,
					mWineImage.getScaleType());
		}
	}

}
