package com.olaappathon.navigationdrawer.adapter;

import android.graphics.drawable.Drawable;

import com.olaappathon.interfaces.SummaryInterface;

/**
 * The Class SummaryItem
 */
public class SummaryItem implements SummaryInterface {
	private int layoutId;
	private long id;
	protected String text1;
	private String text2;
	private boolean isCheckable;
	private Drawable accessory;
	private boolean isChecked = false;

	public SummaryItem() {
	}

	/**
	 * Constructor for SummaryItem.
	 * 
	 * @param text1
	 *            String
	 * @param text2
	 *            String
	 */
	public SummaryItem(String text1, String text2) {
		this(-1, text1, text2, null);
	}

	/**
	 * Constructor for SummaryItem.
	 * 
	 * @param id
	 *            long
	 * @param text1
	 *            String
	 * @param text2
	 *            String
	 */
	public SummaryItem(long id, String text1, String text2) {
		this(id, text1, text2, null);
	}

	/**
	 * Constructor for SummaryItem.
	 * 
	 * @param text1
	 *            String
	 * @param text2
	 *            String
	 * @param accessory
	 *            Drawable
	 */
	public SummaryItem(String text1, String text2, Drawable accessory) {
		this(-1, text1, text2, accessory);
	}

	/**
	 * Constructor for SummaryItem.
	 * 
	 * @param id
	 *            long
	 * @param text1
	 *            String
	 * @param text2
	 *            String
	 * @param accessory
	 *            Drawable
	 */
	public SummaryItem(long id, String text1, String text2, Drawable accessory) {
		this.id = id;
		this.text1 = text1;
		this.text2 = text2;
		this.accessory = accessory;
	}

	/**
	 * Method getSummaryId.
	 * 
	 * @return long
	 * @see com.exclaim.snapsecure.lib.interfaces.SummaryInterface#getSummaryId()
	 */
	@Override
	public long getSummaryId() {
		return this.id;
	}

	/**
	 * Method getSummaryName.
	 * 
	 * @return String
	 * @see com.exclaim.snapsecure.lib.interfaces.SummaryInterface#getSummaryName()
	 */
	@Override
	public String getSummaryName() {
		return this.text1;
	}

	/**
	 * Method getSummarySummary.
	 * 
	 * @return String
	 * @see com.exclaim.snapsecure.lib.interfaces.SummaryInterface#getSummarySummary()
	 */
	@Override
	public String getSummarySummary() {
		return this.text2;
	}

	/**
	 * Method setSummaryName.
	 * 
	 * @param value
	 *            String
	 */
	public void setSummaryName(String value) {
		this.text1 = value;
	}

	/**
	 * Method setSummarySummary.
	 * 
	 * @param value
	 *            String
	 */
	public void setSummarySummary(String value) {
		this.text2 = value;
	}

	/**
	 * Method setCheckable.
	 * 
	 * @param value
	 *            boolean
	 */
	public void setCheckable(boolean value) {
		this.isCheckable = value;
	}

	/**
	 * Method isSummaryCheckable.
	 * 
	 * @return boolean
	 * @see com.exclaim.snapsecure.lib.interfaces.SummaryInterface#isSummaryCheckable()
	 */
	public boolean isSummaryCheckable() {
		return this.isCheckable;
	}

	/**
	 * Method isSummaryChecked.
	 * 
	 * @return boolean
	 * @see com.exclaim.snapsecure.lib.interfaces.SummaryInterface#isSummaryChecked()
	 */
	public boolean isSummaryChecked() {
		return this.isChecked;
	}

	/**
	 * Method getSummaryIcon.
	 * 
	 * @return Drawable
	 * @see com.exclaim.snapsecure.lib.interfaces.SummaryInterface#getSummaryIcon()
	 */
	public Drawable getSummaryIcon() {
		return null;
	}

	/**
	 * Method getSummaryAccessory.
	 * 
	 * @return Drawable
	 * @see com.exclaim.snapsecure.lib.interfaces.SummaryInterface#getSummaryAccessory()
	 */
	public Drawable getSummaryAccessory() {
		return this.accessory;
	}

	/**
	 * Method setSummaryChecked.
	 * 
	 * @param value
	 *            boolean
	 * @see com.exclaim.snapsecure.lib.interfaces.SummaryInterface#setSummaryChecked(boolean)
	 */
	@Override
	public void setSummaryChecked(boolean value) {
		this.isChecked = value;
	}

	/**
	 * Method getSummaryLayoutId.
	 * 
	 * @return int
	 * @see com.exclaim.snapsecure.lib.interfaces.SummaryInterface#getSummaryLayoutId()
	 */
	public int getSummaryLayoutId() {
		return layoutId;
	}

	@Override
	public Drawable getSummaryAlertIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAlertCount() {
		// TODO Auto-generated method stub
		return null;
	}

}
