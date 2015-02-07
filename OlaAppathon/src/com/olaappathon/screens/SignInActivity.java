package com.olaappathon.screens;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.olaappathon.activityfactory.ActivityFactory;
import com.olaappathon.app.R;
import com.olaappathon.helper.OlaConstant;

public class SignInActivity extends CommonActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.sign_in);
	}

	public void onSignInClick(View v) {
		startActivity(ActivityFactory.getInstance().getActivityIntent(OlaConstant.ACTIVITY_DASHBOARD));
		finish();
	}

}
