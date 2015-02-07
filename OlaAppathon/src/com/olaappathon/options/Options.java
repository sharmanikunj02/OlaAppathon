package com.olaappathon.options;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Base64;

import com.olaappathon.main.OlaAppathon;

public class Options {

	/**
	 * Gets the shared preferences object.
	 */
	protected static SharedPreferences getPrefs() {
		final String KEY = "com.olaappathon.system.OptionManager";
		return OlaAppathon.getContext().getSharedPreferences(KEY, Context.MODE_PRIVATE);
	}

	/**
	 * Convenience method to return a shared preferences editor.
	 */
	protected static Editor getEditor() {
		return getPrefs().edit();
	}

	/**
	 * TODO - There is no guarantee that this is binary safe.
	 */
	public static Object getObject(final String key, final Object defaultValue) {
		try {
			String data = getPrefs().getString(key, null);
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

	/**
	 * TODO - There is no guarantee that this is binary safe.
	 */
	public static void setObject(final String key, final Object value) {
		if (TextUtils.isEmpty(key)) {
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
}
