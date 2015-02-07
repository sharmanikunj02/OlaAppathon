package com.olaappathon.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class StringHelper
 */
public class StringHelper {
	public static Pattern EMAIL_PATTERN = Pattern.compile("(?:[a-zA-Z0-9!#$%\\&'*+/=?\\^_`{|}~-]+(?:\\.[a-z0-9!#$%\\&'*+/=?\\^_`{|}"
			+ "~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\"
			+ "x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-zA-Z0-9](?:[a-"
			+ "zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?|\\[(?:(?:25[0-5"
			+ "]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-"
			+ "9][0-9]?|[a-zA-Z0-9-]*[a-zA-Z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21"
			+ "-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

	/**
	 * Method isValidEmail.
	 * 
	 * @param a_value
	 *            String
	 * @return boolean
	 */
	public static boolean isValidEmail(String a_value) {
		if (a_value != null) {
			Matcher matcher = EMAIL_PATTERN.matcher(a_value);
			return matcher.matches();
		}
		return false;
	}

	/**
	 * Method validateNameLength.
	 * 
	 * @param value
	 *            String
	 * @return boolean
	 */
	public static boolean validateNameLength(String a_value) {
		if (a_value.length() < 2 || a_value.length() > 20)
			return false;
		return true;
	}

	/**
	 * Method validatePasswordLength.
	 * 
	 * @param value
	 *            String
	 * @return boolean
	 */
	public static boolean validatePasswordLength(String a_value) {
		if (a_value.length() < 4 || a_value.length() > 20)
			return false;
		return true;
	}

	/**
	 * Method isEmpty.
	 * 
	 * @param value
	 *            String
	 * @return boolean
	 */
	public static boolean isEmpty(String value) {
		return (value == null) || (value.length() == 0) || (value.trim().length() == 0) || "null".equalsIgnoreCase(value);
	}

	/**
	 * Method isNotEmpty.
	 * 
	 * @param value
	 *            String
	 * @return boolean
	 */
	public static boolean isNotEmpty(String value) {
		return (value != null) && (value.trim().length() > 0);
	}

	/**
	 * Method getNameValue.
	 * 
	 * @param property
	 *            String
	 * @return String[]
	 */
	public static String[] getNameValue(String property) {
		String name = "";
		String value = "";
		int index = property.indexOf("=");
		if (index > -1) {
			name = property.substring(0, index);
			if (property.length() > index + 1) {
				value = property.substring(index + 1);
			}
		}

		return new String[] { name, value };
	}

	/**
	 * Method getHTMLBody.
	 * 
	 * @param content
	 *            String
	 * @return String
	 */
	public static String getHTMLBody(String content) {
		try {
			int index1 = content.indexOf("<body>");
			int index2 = content.indexOf("</body>");
			return content.substring(index1 + 6, index2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Method concatArray.
	 * 
	 * @param array
	 *            String[]
	 * @param separator
	 *            String
	 * @return String
	 */
	public static String concatArray(String array[], String separator) {
		try {
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				if (StringHelper.isEmpty(array[i]))
					continue;
				if (buffer.length() > 0)
					buffer.append(separator);
				buffer.append(array[i]);
			}
			return buffer.toString();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return null;
	}
}
