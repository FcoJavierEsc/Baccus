package com.utad.baccus.controller.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.RadioGroup;

import com.utad.baccus.R;

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

		int defecto;

		super.onCreateDialog(savedInstanceState);

		AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
		mRoot = getActivity().getLayoutInflater().inflate(
				R.layout.fragment_settings, null);
		dialog.setPositiveButton(android.R.string.ok, this);
		dialog.setNegativeButton(android.R.string.cancel, this);
		dialog.setTitle(R.string.action_settings);
		dialog.setView(mRoot);
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
		if (mRadios.getCheckedRadioButtonId() == R.id.radio_normal) {
			intent.putExtra(OPTION_SELECTED, OPTION_NORMAL);
		} else {
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
