package com.olaappathon.screens;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.olaappathon.activityfactory.ActivityFactory;
import com.olaappathon.app.R;
import com.olaappathon.helper.OlaConstant;
import com.olaappathon.main.OlaAppathon;

public class SplashScreen extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_login_signup);
		if (OlaAppathon.isActivated()) {
			startActivity(ActivityFactory.getInstance().getActivityIntent(OlaConstant.ACTIVITY_DASHBOARD));
			finish();
		}

	}

	public void onSignUpClick(View v) {
		startActivity(ActivityFactory.getInstance().getActivityIntent(OlaConstant.ACTIVITY_SIGNUP));
		finish();
	}

	public void onSignInClick(View v) {
		startActivity(ActivityFactory.getInstance().getActivityIntent(OlaConstant.ACTIVITY_SIGNIN));
		finish();
	}

}
