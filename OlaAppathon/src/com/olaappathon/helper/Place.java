package com.olaappathon.helper;

import android.os.Parcel;
import android.os.Parcelable;

// TODO: Auto-generated Javadoc
/**
 * The Class Place.
 */
public class Place implements Parcelable{
	
	/** The m lat. */
	public String mLat="";
	
	/** The m lng. */
	public String mLng="";	
	
	/** The m place name. */
	public String mPlaceName="";	
	// Vicinity of the place
	/** The m vicinity. */
	public String mVicinity="";	
	// Photos of the place
	/** The m photos. */
	public Photo[] mPhotos={};
	
	/* (non-Javadoc)
	 * @see android.os.Parcelable#describeContents()
	 */
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}		
	
	/**
	 *  Writing Place object data to Parcel.
	 *
	 * @param dest the dest
	 * @param flags the flags
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mLat);
		dest.writeString(mLng);
		dest.writeString(mPlaceName);
		dest.writeString(mVicinity);
		dest.writeParcelableArray(mPhotos, 0);	
	}
	
	/**
	 * Instantiates a new place.
	 */
	public Place(){		
	}
	
	/**
	 *  Initializing Place object from Parcel object.
	 *
	 * @param in the in
	 */
	private Place(Parcel in){
		this.mLat = in.readString();
		this.mLng = in.readString();
		this.mPlaceName = in.readString();
		this.mVicinity = in.readString();
		this.mPhotos = (Photo[])in.readParcelableArray(Photo.class.getClassLoader());				
	}
	
	
	/**  Generates an instance of Place class from Parcel. */
	public static final Parcelable.Creator<Place> CREATOR = new Parcelable.Creator<Place>(){
		@Override
		public Place createFromParcel(Parcel source) {			
			return new Place(source);
		}

		@Override
		public Place[] newArray(int size) {
			// TODO Auto-generated method stub
			return null;
		}		
	};
}