package com.utad.baccus.controller.fragment;

import com.utad.baccus.R;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebFragment extends Fragment {
	private WebView mBrowser = null;
	public static final String ARGS_URL = "com.utad.baccus.controller.ARGS_URL";

	private static final String CURRENT_URL = "CURRENT_URL";
	private static final int MENU_RELOAD = 0;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);

		View root = inflater.inflate(R.layout.fragment_web, container, false);

		// 1) Accedo a mis vistas
		mBrowser = (WebView) root.findViewById(R.id.browser);
		final ProgressBar loading = (ProgressBar) root
				.findViewById(R.id.loading);

		// 2) Recojo el modelo
		String url = (String) getArguments().getSerializable(ARGS_URL);

		// 3.1) Decirle al WebView su WebClient
		mBrowser.setWebViewClient(new WebViewClient() {

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				loading.setVisibility(View.VISIBLE);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				// loading.setVisibility(View.INVISIBLE);
				loading.setVisibility(View.GONE);
			}
		});

		// 3.2) Cargo la página web del modelo
		if (savedInstanceState != null
				&& savedInstanceState.containsKey(CURRENT_URL)) {
			mBrowser.loadUrl(savedInstanceState.getString(CURRENT_URL));
		} else {
			mBrowser.loadUrl(url);
		}

		return root;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		String currentURL = mBrowser.getUrl();
		outState.putString(CURRENT_URL, currentURL);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		menu.add(Menu.NONE, MENU_RELOAD, 0, R.string.reload);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case MENU_RELOAD:
			mBrowser.reload();
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}

}
