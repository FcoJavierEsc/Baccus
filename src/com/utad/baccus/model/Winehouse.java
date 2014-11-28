package com.utad.baccus.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import android.widget.GridLayout.Spec;

import com.utad.baccus.R;

public class Winehouse {
	/**
	 * Singleton
	 */
	private static Winehouse sInstance = null;
	private static final String WINES_URL = "http://baccusapp.herokuapp.com/wines";
	/**
	 * Lista de vinos
	 */
	private List<Wine> mWines = null;

	/**
	 * Método para obtener la instancia del singleton
	 */
	public static Winehouse getInstance() {
		if (sInstance == null) {
			sInstance = new Winehouse();
		}

		return sInstance;
	}

	/**
	 * Constructor de la bodega
	 */
	@SuppressLint("NewApi")
	private Winehouse() {
		// Creamos los modelos

		mWines = new LinkedList<Wine>();

		try {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}

			URLConnection conn = new URL(WINES_URL).openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			String line = null;

			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();

			JSONArray jsonRoot = new JSONArray(sb.toString());

			for (int i = 0; i < jsonRoot.length(); i++) {
				JSONObject jsonWine = jsonRoot.getJSONObject(i);

				if (jsonWine.has("name")) {
					Wine wine = new Wine(jsonWine.getString("name"),
							jsonWine.getString("type"),
							jsonWine.getString("wine_web"),
							jsonWine.getString("company"), R.drawable.vegaval,
							jsonWine.getInt("rating"),
							jsonWine.getString("notes"),
							jsonWine.getString("picture"));

					JSONArray nuevo = jsonWine.getJSONArray("grapes");
					for (int j = 0; j < nuevo.length(); j++) {
						JSONObject jsonGrape = nuevo.getJSONObject(j);
						wine.addGrape(jsonGrape.getString("grape"));
					}

					mWines.add(wine);
			
					Log.v("JSON", jsonWine.getString("name"));
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Métodos para obtener un vino a partir de un índice
	public Wine getWine(int index) {
		return mWines.get(index);
	}

	public int getWineCount() {
		return mWines.size();
	}

	public List<Wine> cloneWineList() {

		List<Wine> copy = new LinkedList<Wine>();

		for (Wine wine : mWines) {
			copy.add(wine);
		}

		return copy;
	}
}
