package com.utad.baccus.controller;

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
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

import com.utad.baccus.R;
import com.utad.baccus.model.Wine;

public class WineFragment extends Fragment {
	public static final String ARGS_WINE = "com.utad.baccus.controller.WineFragment.arg_wine";
	private Wine mWine = null;
	private ImageView mWineImage = null;

	private View root = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}




	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);

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
  
		Button webButton = (Button)root.findViewById(R.id.web_button);
		
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
	public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
		super.onCreateOptionsMenu(menu,inflater);
		inflater.inflate(R.menu.main, menu);		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean defaultValue = super.onOptionsItemSelected(item);
		
		if (item.getItemId() == R.id.action_settings) {
			Intent settingsIntent = new Intent(getActivity(), SettingsActivity.class);
			startActivityForResult(settingsIntent, SettingsActivity.REQUEST_SELECT_SCALETYPE);
			return true;
		}
		else {
			return defaultValue;
		}
	}


	@Override
	public void onActivityResult(int requestCode, int result, Intent intent) {
		super.onActivityResult(requestCode, result, intent);
		
		ImageView mWineImage = (ImageView)root.findViewById(R.id.wine_image);
		if (requestCode == SettingsActivity.REQUEST_SELECT_SCALETYPE
				&& result == Activity.RESULT_OK) {
			int optionSelected = intent.getIntExtra(SettingFragment.OPTION_SELECTED, -1); 
			if (optionSelected != -1 && optionSelected == SettingFragment.OPTION_NORMAL) {
				// A la imagen le doy un scale type normal
				mWineImage.setScaleType(ScaleType.FIT_CENTER);
			}
			else if (optionSelected != -1 && optionSelected == SettingFragment.OPTION_FIT) {
				// A la imagen le doy un scale type estirado
				mWineImage.setScaleType(ScaleType.FIT_XY);
			}
		}
	}
}



