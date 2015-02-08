package com.olaappathon.listner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.olaappathon.screens.PanicTriggerDialog;

/**
 * This receiver is listens to screen on/off events in order to activate the Personal Guardian. It does so if it sees a certain number of events within a few
 * seconds of each other.
 * */
public class PanicTriggerListner extends BroadcastReceiver {
	private static int countPowerOff = 0;

	public PanicTriggerListner() {

	}

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
			countPowerOff++;
		} else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
		} else if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
			if (countPowerOff > 4) {
				countPowerOff = 0;
				Toast.makeText(context, "Panic Triggered ", Toast.LENGTH_LONG).show();
				Intent i = new Intent(context, PanicTriggerDialog.class);
				i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
				context.startActivity(i);
			}
		}

	}
}