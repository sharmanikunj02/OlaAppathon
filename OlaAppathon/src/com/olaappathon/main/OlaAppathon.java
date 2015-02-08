package com.olaappathon.main;

import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;

import com.olaappathon.app.R;
import com.olaappathon.system.OptionManager;

// TODO: Auto-generated Javadoc
/**
 * The Class OlaAppathon.
 */
public class OlaAppathon extends Application {

	/** The context. */
	private static Context context;

	/** The Constant TAG. */
	public static final String TAG = "OlaAppathonTag";

	/** The Constant KEY_REMOTE_KEY. */
	public static final String KEY_ACTIVATED = "KEY_ACTIVATED";

	/** The Constant KEY_SIGN_UP_OBJECT. */
	public static final String KEY_SIGN_UP_OBJECT = "KEY_SIGN_UP_OBJECT";

	/** The Constant KEY_PANIC_OBJECT. */
	public static final String KEY_PANIC_OBJECT = "KEY_PANIC_OBJECT";

	/** The handler. */
	private Handler handler;

	/* (non-Javadoc)
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		OlaAppathon.context = getApplicationContext();
		this.handler = new Handler();

	}

	/**
	 * Method getPrefs.
	 * 
	 * @return SharedPreferences
	 */
	private static SharedPreferences getPrefs() {
		return OlaAppathon.getContext().getSharedPreferences(OptionManager.NAME, Context.MODE_PRIVATE);
	}

	/**
	 * Method getEditor.
	 * 
	 * @return Editor
	 */
	private static Editor getEditor() {
		return getPrefs().edit();
	}

	/**
	 * Method getContext.
	 * 
	 * @return Context
	 */
	public static Context getContext() {
		return OlaAppathon.context;
	}

	/**
	 * Method getInstance.
	 * 
	 * @return SnapSecure
	 */
	public static OlaAppathon getInstance() {
		return (OlaAppathon) OlaAppathon.context;
	}

	/**
	 * Method showToast.
	 * 
	 * @param message
	 *            String
	 */
	public void showToast(final String message) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(OlaAppathon.context, message, Toast.LENGTH_SHORT).show();
			}
		});
	}

	/**
	 * Method getLanguage.
	 * 
	 * @return String
	 */
	public static String getLanguage() {
		String lang = Locale.getDefault().getLanguage();
		if ("fr".equalsIgnoreCase(lang))
			return "french";
		if ("pt".equalsIgnoreCase(lang))
			return "portuguese";
		if ("es".equalsIgnoreCase(lang))
			return "spanish";
		if ("it".equalsIgnoreCase(lang))
			return "italian";
		if ("ru".equalsIgnoreCase(lang))
			return "russian";
		if ("de".equalsIgnoreCase(lang))
			return "german";
		if ("ar".equalsIgnoreCase(lang))
			return "arabic";
		if ("ja".equalsIgnoreCase(lang))
			return "japanese";
		if ("ko".equalsIgnoreCase(lang))
			return "korean";
		if ("zh".equalsIgnoreCase(lang))
			return "mandarin";
		if ("tr".equalsIgnoreCase(lang))
			return "turkish";
		return "english";
	}

	/**
	 * Checks if is activated.
	 * 
	 * @return true, if is activated
	 */
	public static boolean isActivated() {
		return !TextUtils.isEmpty(getPrefs().getString(KEY_ACTIVATED, null));
	}

	/**
	 * Method setRemoteKey.
	 * 
	 * @param value
	 *            String
	 */
	public static void setActivated(String value) {
		getEditor().putString(KEY_ACTIVATED, value).commit();
	}

	/**
	 * Sets the sign up object.
	 *
	 * @param value the new sign up object
	 */
	public static void setSignUpObject(String value) {
		getEditor().putString(KEY_SIGN_UP_OBJECT, value).commit();
	}

	/**
	 * Gets the sign up object.
	 *
	 * @return the sign up object
	 */
	public static String getSignUpObject() {
		return getPrefs().getString(KEY_SIGN_UP_OBJECT, null);
	}
	
	public static String getLoggedInName() {
		return getSignUPObjectvalue("name");		
	}

	public static String getLoggedInEmail() {
		return getSignUPObjectvalue("email");
	}
	
	public static String getLoggedInPassword() {
		return getSignUPObjectvalue("password");
	}
	
	public static String getSignUPObjectvalue(String key){
		try {
			if(getSignUpObject() !=null){
				JSONObject object = new JSONObject(getSignUpObject());				
				return object.getString(key);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * Sets the panic object.
	 *
	 * @param value the new panic object
	 */
	public static void setPanicObject(String value) {
		getEditor().putString(KEY_PANIC_OBJECT, value).commit();
	}

	/**
	 * Gets the panic object.
	 *
	 * @return the panic object
	 */
	public static String getPanicObject() {
		return getPrefs().getString(KEY_PANIC_OBJECT, null);
	}

	/**
	 * Do reset keys.
	 */
	public void doResetKeys() {
		clearActivatedKey();
	}

	/**
	 * Method checkNetworkConnection - To verify network availability:.
	 * 
	 * @return boolean
	 */
	public static boolean checkNetworkConnection() {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
	}

	/**
	 * method showAlertAndNavigateToGooglePlayServices will pop up alert box and take user to google play service app on click of OK.
	 * 
	 * @param ctx
	 *            the ctx
	 * @param message
	 *            the message
	 */
	public void showAlertAndNavigateToGooglePlayServices(final Context ctx, String message) {
		final AlertDialog dialog = new AlertDialog.Builder(ctx).setMessage(message)
				.setPositiveButton(getString(R.string.common_ok), new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialoginterface, int i) {
						Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getContext().getString(R.string.GOOGLE_PLAY_SERVICE_STORE_URL)));
						intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(intent);
					}
				}).create();
		dialog.show();
	}

	/**
	 * Clear remote key.
	 */
	public static void clearActivatedKey() {
		OptionManager.clearKey(KEY_ACTIVATED);
	}

	/**
	 * Gets the display density pixel.
	 * 
	 * @return the display density pixel
	 */
	public float getDisplayDensityPixel() {
		return OlaAppathon.context.getResources().getDisplayMetrics().density;
	}

}
