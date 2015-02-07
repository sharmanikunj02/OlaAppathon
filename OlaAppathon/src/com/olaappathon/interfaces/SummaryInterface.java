package com.olaappathon.interfaces;

import android.graphics.drawable.Drawable;

/**
 * The Interface SummaryInterface
 */
public interface SummaryInterface {
	/**
	 * Method getSummaryLayoutId.
	 * 
	 * @return int
	 */
	public int getSummaryLayoutId();

	/**
	 * Method getSummaryId.
	 * 
	 * @return long
	 */
	public long getSummaryId();

	/**
	 * Method getSummaryName.
	 * 
	 * @return String
	 */
	public String getSummaryName();

	/**
	 * Method getSummarySummary.
	 * 
	 * @return String
	 */
	public String getSummarySummary();

	/**
	 * Method isSummaryCheckable.
	 * 
	 * @return boolean
	 */
	public boolean isSummaryCheckable();

	/**
	 * Method isSummaryChecked.
	 * 
	 * @return boolean
	 */
	public boolean isSummaryChecked();

	/**
	 * Method getSummaryIcon.
	 * 
	 * @return Drawable
	 */
	public Drawable getSummaryIcon();

	/**
	 * Method getSummaryAccessory.
	 * 
	 * @return Drawable
	 */
	public Drawable getSummaryAccessory();

	/**
	 * Method getSummaryAlertIcon.
	 * 
	 * @return Drawable
	 */
	public Drawable getSummaryAlertIcon();

	/**
	 * Method setSummaryChecked.
	 * 
	 * @param value
	 *            boolean
	 */
	public void setSummaryChecked(boolean value);

	/**
	 * Method getAlertcount.
	 */
	public String getAlertCount();

}
