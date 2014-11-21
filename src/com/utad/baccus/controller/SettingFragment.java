package com.utad.baccus.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.utad.baccus.R;

public class SettingFragment extends Fragment implements OnClickListener {
	private View root=null;
	
	public static final String OPTION_SELECTED = "OPTION_SELECTED";
	public static final int OPTION_NORMAL = 0;
	public static final int OPTION_FIT = 1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);

		 root = inflater.inflate(R.layout.fragment_settings, container,
				false);

		Button cancelButton = (Button) root.findViewById(R.id.cancel_button);
		Button saveButton = (Button) root.findViewById(R.id.save_button);

		return inflater.inflate(R.layout.fragment_settings, container, false);

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
		RadioGroup radios = (RadioGroup) root.findViewById(R.id.radio_options);
		if (radios.getCheckedRadioButtonId() == R.id.radio_normal) {
			intent.putExtra(OPTION_SELECTED, OPTION_NORMAL);
		} else {
			intent.putExtra(OPTION_SELECTED, OPTION_FIT);
		}
		act.setResult(act.RESULT_OK, intent);

		act.finish();
	}
}
