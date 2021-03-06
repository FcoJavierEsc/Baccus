package com.utad.baccus.controller.adapter;

import com.utad.baccus.controller.fragment.WineFragment;
import com.utad.baccus.model.Winehouse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class WineFragmentAdapter extends FragmentPagerAdapter {

	private Winehouse mWines = null;

	public WineFragmentAdapter(FragmentManager fm) {
		super(fm);
		mWines = Winehouse.getInstance();
	}

	@Override
	public Fragment getItem(int index) {

		WineFragment frg = new WineFragment();

		Bundle args = new Bundle();
		args.putSerializable(WineFragment.ARGS_WINE, mWines.getWine(index));
		frg.setArguments(args);
		return frg;
	}

	@Override
	public int getCount() {
		return mWines.getWineCount();
	}

	@Override
	public CharSequence getPageTitle(int position) {

		super.getPageTitle(position);
		return mWines.getWine(position).getName();
	}

	public int getImageResource(int index) {
		return mWines.getWine(index).getImage();
	}

}
