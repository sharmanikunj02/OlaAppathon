package com.olaappathon.inputNameDialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.olaappathon.app.R;
import com.olaappathon.helper.StringHelper;
import com.olaappathon.main.OlaAppathon;
import com.olaappathon.main.Platform;

public class CustomNameNumberDialogFragment extends DialogFragment {
	EditText txtName;
	EditText txtNumber;
	Button btnYes;
	Button btnNo;

	static String DialogboxTitle;
	/** The fragment. */
	private static CustomNameNumberDialogFragment dialogFragment;

	public interface NameNumberDialogListener {
		void onPositiveButtonClick(String name, String number);
	}

	public CustomNameNumberDialogFragment() {

	}

	public static CustomNameNumberDialogFragment newInstance(int dialogId, String title, String message, String positiveButton, String negativeButton) {
		dialogFragment = new CustomNameNumberDialogFragment();
		Bundle args = new Bundle();
		args.putInt("dialogId", dialogId);
		args.putString("title", title);
		args.putString("message", message);
		args.putString("positiveButton", positiveButton);
		args.putString("negativeButton", negativeButton);
		dialogFragment.setArguments(args);
		return dialogFragment;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		String title = getArguments().getString("title");
		String message = getArguments().getString("message");
		String positiveButton = getArguments().getString("positiveButton");
		String negativeButton = getArguments().getString("negativeButton");

		AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

		View view = View.inflate(OlaAppathon.getContext(), R.layout.input_name_dialogfragment, null);
		TextView mTitle = (TextView) view.findViewById(R.id.alertTitle);

		mTitle.setText(title);
		mTitle.setTextColor(getResources().getColor((R.color.black)));

		txtName = (EditText) view.findViewById(R.id.txtName);
		txtNumber = (EditText) view.findViewById(R.id.txtNumber);
		TextView mMessage = (TextView) view.findViewById(R.id.message);
		if (StringHelper.isNotEmpty(message)) {
			mMessage.setVisibility(View.VISIBLE);
			mMessage.setText(message);
			if (Platform.getDeviceApiVersion() >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
				mMessage.setTextColor(getResources().getColor(R.color.text_color));
			}
		}
		alertDialog.setCancelable(false);
		dialogFragment.setCancelable(false);
		alertDialog.setView(view);
		alertDialog.setIcon(R.drawable.icon_alert);

		alertDialog.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialoginterface, int i) {
				String name = txtName.getText().toString();
				String number = txtNumber.getText().toString();
				if (StringHelper.isNotEmpty(name) && StringHelper.isNotEmpty(number)) {
					// ---gets the calling activity
					NameNumberDialogListener activity = (NameNumberDialogListener) getActivity();
					activity.onPositiveButtonClick(name, number);
				} else {
					Toast.makeText(OlaAppathon.getContext(), "Invalid values", Toast.LENGTH_SHORT).show();
				}
			}
		});
		alertDialog.setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialoginterface, int i) {
				dismiss();
			}
		});
		
		final AlertDialog dialog = alertDialog.create();
//		dialog.show();
		dialog.setOnShowListener(new DialogInterface.OnShowListener() {
			@Override
			public void onShow(final DialogInterface dialog) {
				Button negativeButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
				Button positiveButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);

				positiveButton.setBackgroundColor(getResources().getColor(R.color.white));
				negativeButton.setBackgroundColor(getResources().getColor(R.color.white));

				negativeButton.invalidate();
				positiveButton.invalidate();
			}
		});
		return alertDialog.create();
	}
}
