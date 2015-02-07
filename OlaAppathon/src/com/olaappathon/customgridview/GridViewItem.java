package com.olaappathon.customgridview;

import android.graphics.Bitmap;

public class GridViewItem {
	Bitmap image;
	String title;

	public GridViewItem(Bitmap image, String title) {
		super();
		this.image = image;
		this.title = title;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}