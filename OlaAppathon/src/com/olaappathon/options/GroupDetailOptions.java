package com.olaappathon.options;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences.Editor;

public class GroupDetailOptions extends Options {

	public static void resetDefaults() {
		Editor editor = getEditor();
		editor.remove(KEY_GROUP_DETAIL_OBJECT);
		editor.commit();
	}

	/**
	 * Email Options
	 */
	private static final String KEY_GROUP_DETAIL_OBJECT = "KEY_GROUP_DETAIL_OBJECT";

	public static String getGroupDetailObject() {
		return getPrefs().getString(KEY_GROUP_DETAIL_OBJECT, null);
	}

	public static void setGroupDetailObject(String value) {
		
		if(GroupDetailOptions.getGroupDetailObject()!=null){
			String actualValue= mergeroupDetailObject( value);
		}

		getEditor().putString(KEY_GROUP_DETAIL_OBJECT, value).commit();
	}

	private static  String mergeroupDetailObject(String value) {
		
		try {
			JSONObject existingGroupObject= new JSONObject(getGroupDetailObject());
			JSONObject newGroupObject = new JSONObject(value);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return value;
		
		
	}

}
