package com.olaappathon.helper;

import java.util.HashMap;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

// TODO: Auto-generated Javadoc
/**
 *  Customizing AutoCompleteTextView to return Place Description   
 *  corresponding to the selected item.
 */
public class CustomAutoCompleteTextView extends AutoCompleteTextView {
	
	/**
	 * Instantiates a new custom auto complete text view.
	 *
	 * @param context the context
	 * @param attrs the attrs
	 */
	public CustomAutoCompleteTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 *  Returns the place description corresponding to the selected item.
	 *
	 * @param selectedItem the selected item
	 * @return the char sequence
	 */
	@Override
	protected CharSequence convertSelectionToString(Object selectedItem) {
		/** Each item in the autocompetetextview suggestion list is a hashmap object */
		HashMap<String, String> hm = (HashMap<String, String>) selectedItem;
		return hm.get("description");
	}
	
}
