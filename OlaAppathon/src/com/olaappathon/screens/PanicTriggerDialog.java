package com.olaappathon.screens;

import android.os.Bundle;
import android.view.View;

import com.olaappathon.app.R;

public class PanicTriggerDialog extends CommonActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.panic_dialog_screen);
	}
	
	public void cancelPanic(View v) {
		finish();		
	}
}

