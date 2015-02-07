package com.olaappathon.helper;

import android.os.Parcel;
import android.os.Parcelable;

// TODO: Auto-generated Javadoc
/**
 * The Class Attribution.
 */
public class Attribution implements Parcelable{
	
	// Attribution of the photo
	/** The m html attribution. */
	String mHtmlAttribution="";

	/* (non-Javadoc)
	 * @see android.os.Parcelable#describeContents()
	 */
	@Override
	public int describeContents() {		
		return 0;
	}

	/**
	 *  Writing Attribution object data to Parcel.
	 *
	 * @param dest the dest
	 * @param flags the flags
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mHtmlAttribution);		
	}
	
	/**
	 * Instantiates a new attribution.
	 */
	public Attribution(){
		
	}
	
	/**
	 *   Initializing Attribution object from Parcel object.
	 *
	 * @param in the in
	 */
	private Attribution(Parcel in){
		this.mHtmlAttribution = in.readString();
	}
	
	/**  Generates an instance of Attribution class from Parcel. */
	public static final Parcelable.Creator<Attribution> CREATOR = new Parcelable.Creator<Attribution>() {

		@Override
		public Attribution createFromParcel(Parcel source) {			
			return new Attribution(source);
		}

		@Override
		public Attribution[] newArray(int size) {
			// TODO Auto-generated method stub
			return null;
		}
	};	
}
