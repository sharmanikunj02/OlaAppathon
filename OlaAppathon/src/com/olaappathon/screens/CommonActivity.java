package com.olaappathon.screens;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.olaappathon.app.R;

/**
 * class CommonActivity.
 */
public class CommonActivity extends FragmentActivity {

	/** The busy dialog. */
	private ProgressDialog busyDialog;
	
	/** The finish dialog. */
	private AlertDialog finishDialog;

	/** The no repeat toast. */
	private Toast noRepeatToast;

	/** The from notification. */
	private boolean fromNotification = false;

	/** The count. */
	private int count = 0;

	private DialogFragment networkConnectionAlert = null;
	private DialogFragment networkTimeoutAlert = null;
	private DialogFragment unauthorizedAlert = null;

	public final static String START_TIME = "START_TIME";
	public final static String END_TIME = "END_TIME";
	public final static String TYPE = "TYPE";

	/**
	 * Method onCreate.
	 * 
	 * @param savedInstanceState
	 *            Bundle
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		noRepeatToast = Toast.makeText(this, "", Toast.LENGTH_LONG);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onStart()
	 */
	@Override
	protected void onStart() {
		super.onStart();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.actionbarsherlock.app.SherlockFragmentActivity#onStop()
	 */
	@Override
	protected void onStop() {
		super.onStop();
	}

	/**
	 * Method setTitle.
	 * 
	 * @param value
	 *            String
	 */
	public void setTitle(String value) {
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.actionbarsherlock.app.SherlockFragmentActivity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
	}

	/**
	 * Method showBusy.
	 * 
	 * @param busy
	 *            boolean
	 */
	public void showBusyGetString(boolean busy, int getString) {
		if (!isFinishing()) {
			if (busyDialog == null && getString > 0) {
				busyDialog = new ProgressDialog(this);
				busyDialog.setMessage(getString(getString));
				busyDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				busyDialog.setIndeterminate(true);
				busyDialog.setCancelable(false);
			}

			if (busy) {
				busyDialog.show();
			} else {
				busyDialog.hide();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.actionbarsherlock.app.SherlockFragmentActivity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		if ((busyDialog != null) && busyDialog.isShowing()) {
			busyDialog.dismiss();
			busyDialog = null;
		}
		if ((finishDialog != null) && finishDialog.isShowing()) {
			finishDialog.dismiss();
			finishDialog = null;
		}
		super.onDestroy();
	}

	/**
	 * Method informThenFinish.
	 * 
	 * @param message
	 *            String
	 */
	protected void informThenFinish(String message) {
		if (finishDialog != null && finishDialog.isShowing())
			return;

		final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
		dialogBuilder
				.setMessage(message)
				.setPositiveButton(getString(R.string.common_ok),
						new Dialog.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								finish();
							}
						}).create();
		finishDialog = dialogBuilder.show();
	}

	/**
	 * Method inform.
	 * 
	 * @param message
	 *            String
	 */
	protected void inform(String message) {
		new AlertDialog.Builder(this).setMessage(message)
				.setPositiveButton(getString(R.string.common_ok), null)
				.create().show();
	}

	/**
	 * The Interface IButtonAction.
	 */
	interface IButtonAction {

		/**
		 * Do action.
		 */
		void doAction();
	}

	/**
	 * Method showMessageAndAction.
	 * 
	 * @param msg
	 *            String
	 * @param okAction
	 *            IButtonAction
	 * @param positiveBtnName
	 *            String
	 * @param cancelAction
	 *            IButtonAction
	 * @param nagtiveBtnName
	 *            String
	 */
	void showMessageAndAction(String msg, final IButtonAction okAction,
			String positiveBtnName, final IButtonAction cancelAction,
			String nagtiveBtnName) {
		try {
			AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
			alertbox.setCancelable(false);
			alertbox.setMessage(msg);
			if (okAction != null) {
				alertbox.setPositiveButton(positiveBtnName,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								okAction.doAction();
							}
						});
			}
			if (cancelAction != null) {
				alertbox.setNegativeButton(nagtiveBtnName,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								cancelAction.doAction();
							}
						});
			}
			// show it
			alertbox.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method showNoRepeatToast.
	 * 
	 * @param msgId
	 *            int
	 */
	protected void showNoRepeatToast(final int msgId) {
		noRepeatToast.setText(msgId);
		noRepeatToast.show();
	}

}
