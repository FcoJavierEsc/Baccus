package com.utad.baccus.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

public class Wine implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5304665550382167825L;

	private String mName;
	private String mType;
	private String mURL;
	private String mWinehouse;
	private List<String> mGrapes;
	private int mImage;
	private int mRating;
	private String mNotes;
	private String mImageURL;
	private String mId;

	public Wine(String id, String name, String type, String uRL,
			String winehouse, int image, int rating, String notes,
			String ImageURL) {
		super();
		mName = name;
		mType = type;
		mURL = uRL;
		mWinehouse = winehouse;
		mImage = image;
		mRating = rating;
		mNotes = notes;
		mGrapes = new LinkedList<String>();
		mImageURL = ImageURL;
		mId = id;
	}

	public String getId() {
		return mId;
	}

	public void setId(String id) {
		mId = id;
	}

	public void addGrape(String grape) {
		mGrapes.add(grape);
	}

	public Bitmap getBitmap(Context context) {
		return getBitmapFromURL(context, mImageURL);
	}

	public Bitmap getBitmapFromURL(Context context, String url) {

		File imageFile = new File(context.getCacheDir(), getId());

		if (imageFile.exists()) {
			return BitmapFactory.decodeFile(imageFile.getAbsolutePath());

		}

		InputStream in = null;

		try {
			in = new java.net.URL(url).openStream();
			Bitmap bmp = BitmapFactory.decodeStream(in);
			FileOutputStream fos = new FileOutputStream(imageFile);
			bmp.compress(Bitmap.CompressFormat.PNG, 90, fos);
			fos.close();
			Log.v("AQUI", "Hemos salvado ");
			return bmp;
		} catch (Exception ex) {
			Log.e("Baccus", "ERROR downloading image", ex);
			return null;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
			}
		}
	}

	@Override
	public String toString() {

		return getName();
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public String getType() {
		return mType;
	}

	public void setType(String type) {
		mType = type;
	}

	public String getURL() {
		return mURL;
	}

	public void setURL(String uRL) {
		mURL = uRL;
	}

	public String getWinehouse() {
		return mWinehouse;
	}

	public void setWinehouse(String winehouse) {
		mWinehouse = winehouse;
	}

	public List<String> getGrapes() {
		return mGrapes;
	}

	public int getImage() {
		return mImage;
	}

	public void setImage(int image) {
		mImage = image;
	}

	public int getRating() {
		return mRating;
	}

	public void setRating(int rating) {
		mRating = rating;
	}

	public String getNotes() {
		return mNotes;
	}

	public void setNotes(String notes) {
		mNotes = notes;
	}

}
