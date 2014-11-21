package com.utad.baccus.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.utad.baccus.R;

public class SettingsFragment extends Fragment implements OnClickListener {
	private View root = null;

	public static final String OPTION_SELECTED = "com.utad.baccus.OPTION_SELECTED";
	public static final int OPTION_NORMAL = 0;
	public static final int OPTION_FIT = 1;
	private RadioGroup mRadios = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		int defecto;

		super.onCreateView(inflater, container, savedInstanceState);

		if (savedInstanceState != null) {
			defecto = savedInstanceState
					.getInt(SettingsFragment.OPTION_SELECTED);
		} else
			defecto = SettingsFragment.OPTION_NORMAL;

		root = inflater.inflate(R.layout.fragment_settings, container, false);
		mRadios = (RadioGroup) root.findViewById(R.id.radio_options);
		int cual;

		switch (defecto) {
		case SettingsFragment.OPTION_FIT:
			cual = R.id.radio_fit;
			break;
		case SettingsFragment.OPTION_NORMAL:
			cual = R.id.radio_normal;
			break;
		default:
			cual = R.id.radio_normal;
		}

		RadioButton rButton = (RadioButton) root.findViewById(cual);
		rButton.setChecked(true);

		return root;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.cancel_button:
			cancel();
			break;
		case R.id.save_button:
			save();
			break;
		}
	}

	public void cancel() {
		Activity act = getActivity();
		act.setResult(Activity.RESULT_CANCELED);
		act.finish();
	}

	public void save() {
		Activity act = getActivity();
		Intent intent = act.getIntent();

		if (mRadios.getCheckedRadioButtonId() == R.id.radio_normal) {
			intent.putExtra(OPTION_SELECTED, OPTION_NORMAL);
		} else {
			intent.putExtra(OPTION_SELECTED, OPTION_FIT);
		}
		act.setResult(Activity.RESULT_OK, intent);

		act.finish();
	}
}
