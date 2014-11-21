package com.utad.baccus.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;


import com.utad.baccus.R;
import com.utad.baccus.model.Wine;

public class WineActivity extends ActionBarActivity {
	public static final String EXTRA_WINE = "com.utad.baccus.controller.WineActivity.extra_wine";
	


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        
        WineFragment fragment = new WineFragment();
    
	    Bundle arguments = new Bundle();
	    arguments.putSerializable(WineFragment.ARGS_WINE, getIntent().getSerializableExtra(EXTRA_WINE));
	    
	    fragment.setArguments(arguments);
	    
	    
	    FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
	    trx.add(R.id.fragment_placeholder ,fragment);
	    trx.commit();
    }
  


	public void showWeb(View v) {
    		Intent webIntent = new Intent(this, WebActivity.class);
    		Wine mWine = (Wine)getIntent().getSerializableExtra(EXTRA_WINE);
    		webIntent.putExtra(WebActivity.EXTRA_URL, mWine.getURL());
    		startActivity(webIntent);
    }
}









