package com.utad.baccus.controller.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.utad.baccus.R;
import com.utad.baccus.model.Constans;

public class SettingsFragment extends DialogFragment implements
		android.content.DialogInterface.OnClickListener {
	// private View root = null;
	public static final int REQUEST_SELECT_SCALETYPE = 0;
	public static final String OPTION_SELECTED = "com.utad.baccus.OPTION_SELECTED";
	public static final int OPTION_NORMAL = 0;
	public static final int OPTION_FIT = 1;
	// private RadioGroup mRadios = null;
	private View mRoot = null;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		super.onCreateDialog(savedInstanceState);

		AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
		mRoot = getActivity().getLayoutInflater().inflate(
				R.layout.fragment_settings, null);
		dialog.setPositiveButton(android.R.string.ok, this);
		dialog.setNegativeButton(android.R.string.cancel, this);
		dialog.setTitle(R.string.action_settings);
		dialog.setView(mRoot);
		
		
		SharedPreferences pref =PreferenceManager.getDefaultSharedPreferences(getActivity());
		ImageView.ScaleType scaleType = ImageView.ScaleType.valueOf(pref.getString(Constans.PREF_SCALE_TYPE, ImageView.ScaleType.FIT_CENTER.toString()));
	
		
		RadioGroup radios = (RadioGroup) mRoot.findViewById(R.id.radio_options);
		if (scaleType.equals(ImageView.ScaleType.FIT_CENTER)){
			radios.check(R.id.radio_normal);
		}
		else{
			radios.check(R.id.radio_fit);
		}
		return dialog.create();
	}

	// @Override
	// public void onClick(View v) {
	//
	// }

	public void cancel() {
		Fragment tgFr = getTargetFragment();

		if (tgFr == null) {
			Activity act = getActivity();
			act.setResult(Activity.RESULT_CANCELED);
			act.finish();
		} else {
			tgFr.onActivityResult(getTargetRequestCode(),
					Activity.RESULT_CANCELED, null);
		}
	}

	public void save() {

		Activity act = getActivity();
		Intent intent = new Intent();
		RadioGroup mRadios = (RadioGroup) mRoot
				.findViewById(R.id.radio_options);
		
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
		if (mRadios.getCheckedRadioButtonId() == R.id.radio_normal) {
			intent.putExtra(OPTION_SELECTED, OPTION_NORMAL);
			pref.edit().putString(Constans.PREF_SCALE_TYPE, ImageView.ScaleType.FIT_CENTER.toString()).commit();
						
		} else {
			pref.edit().putString(Constans.PREF_SCALE_TYPE, ImageView.ScaleType.FIT_XY.toString()).commit();

			intent.putExtra(OPTION_SELECTED, OPTION_FIT);
		}

		Fragment tgFr = getTargetFragment();

		if (tgFr == null) {

			act.setResult(Activity.RESULT_OK, intent);
			act.finish();
		} else {
			tgFr.onActivityResult(getTargetRequestCode(), Activity.RESULT_OK,
					intent);
		}

	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		switch (which) {
		case DialogInterface.BUTTON_POSITIVE:
			save();
			break;
		case DialogInterface.BUTTON_NEGATIVE:
			cancel();
			break;
		default:
			cancel();
			break;

		}
	}
}
