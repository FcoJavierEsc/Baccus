package com.utad.baccus.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Wine implements Serializable {
	private String mName;
	private String mType;
	private String mURL;
	private String mWinehouse;
	private List<String> mGrapes;
	private int mImage;
	private int mRating;
	private String mNotes;
	
	
	public Wine(String name, String type, String uRL, String winehouse,
			int image, int rating, String notes) {
		super();
		mName = name;
		mType = type;
		mURL = uRL;
		mWinehouse = winehouse;
		mImage = image;
		mRating = rating;
		mNotes = notes;
		mGrapes = new LinkedList<String>();
	}
	
	public void addGrape(String grape) {
		mGrapes.add(grape);
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
