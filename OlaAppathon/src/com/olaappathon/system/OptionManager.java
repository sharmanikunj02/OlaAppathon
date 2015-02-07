package com.olaappathon.system;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

import com.olaappathon.helper.StringHelper;
import com.olaappathon.main.OlaAppathon;

/**
 * The Class OptionManager.
 */
public class OptionManager {

	public static final String TAG = OptionManager.class.getSimpleName();
	public static final String NAME = "com.olaappathon.system.OptionManager";

	/**
	 * Gets the prefs.
	 * 
	 * @return the prefs
	 */
	private static SharedPreferences getPrefs() {
		return OlaAppathon.getContext().getSharedPreferences(NAME, Context.MODE_PRIVATE);
	}

	/**
	 * Gets the editor.
	 * 
	 * @return the editor
	 */
	private static Editor getEditor() {
		return getPrefs().edit();
	}

	//
	// Get
	//
	/**
	 * Gets the string.
	 * 
	 * @param key
	 *            the key
	 * @return the string
	 */
	public static String getString(final String key) {
		return getString(key, null);
	}

	/**
	 * Gets the int.
	 * 
	 * @param key
	 *            the key
	 * @return the int
	 */
	public static int getInt(final String key) {
		return getInt(key, 0);
	}

	/**
	 * Gets the long.
	 * 
	 * @param key
	 *            the key
	 * @return the long
	 */
	public static long getLong(final String key) {
		return getLong(key, 0L);
	}

	/**
	 * Gets the boolean.
	 * 
	 * @param key
	 *            the key
	 * @return the boolean
	 */
	public static boolean getBoolean(final String key) {
		return getBoolean(key, false);
	}

	/**
	 * Gets the long.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the long
	 */
	public static long getLong(final String key, final long defaultValue) {
		return getPrefs().getLong(key, defaultValue);
	}

	/**
	 * Gets the int.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the int
	 */
	public static int getInt(final String key, final int defaultValue) {
		return getPrefs().getInt(key, defaultValue);
	}

	/**
	 * Gets the boolean.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the boolean
	 */
	public static boolean getBoolean(final String key, final boolean defaultValue) {
		return getPrefs().getBoolean(key, defaultValue);
	}

	/**
	 * Gets the string.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the string
	 */
	public static String getString(final String key, final String defaultValue) {
		return getPrefs().getString(key, defaultValue);
	}

	/**
	 * Gets the object.
	 * 
	 * @param key
	 *            the key
	 * @param defaultValue
	 *            the default value
	 * @return the object
	 */
	public static Object getObject(final String key, final Object defaultValue) {
		try {
			String data = getString(key);
			if (data == null) {
				return defaultValue;
			}

			final ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(data, Base64.DEFAULT)));
			return in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultValue;
	}

	//
	// Set
	//
	/**
	 * Sets the long.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public static void setLong(final String key, final long value) {
		if (StringHelper.isEmpty(key)) {
			return;
		}
		getEditor().putLong(key, value).commit();
	}

	/**
	 * Sets the int.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public static void setInt(final String key, final int value) {
		if (StringHelper.isEmpty(key)) {
			return;
		}
		getEditor().putInt(key, value).commit();
	}

	/**
	 * Sets the boolean.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public static void setBoolean(final String key, final boolean value) {
		if (StringHelper.isEmpty(key)) {
			return;
		}
		getEditor().putBoolean(key, value).commit();
	}

	/**
	 * Sets the string.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public static void setString(final String key, final String value) {
		if (StringHelper.isEmpty(key)) {
			return;
		}
		getEditor().putString(key, value).commit();
	}

	/**
	 * Sets the object.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public static void setObject(final String key, final Object value) {
		if (StringHelper.isEmpty(key)) {
			return;
		}

		try {
			final ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
			final ObjectOutputStream out = new ObjectOutputStream(byteStream);
			out.writeObject(value);
			out.flush();
			out.close();

			final byte[] byteArray = byteStream.toByteArray();
			final String data = Base64.encodeToString(byteArray, Base64.DEFAULT);
			getEditor().putString(key, data).commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Clear key.
	 * 
	 * @param key
	 *            the key
	 */
	public static void clearKey(final String key) {
		getEditor().remove(key).commit();
	}

}
