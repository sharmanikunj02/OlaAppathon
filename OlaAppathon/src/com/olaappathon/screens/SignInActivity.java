package com.olaappathon.screens;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.olaappathon.activityfactory.ActivityFactory;
import com.olaappathon.app.R;
import com.olaappathon.helper.OlaConstant;
import com.olaappathon.main.OlaAppathon;

public class SignInActivity extends CommonActivity {

	EditText email;
	EditText password;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.sign_in);
		email = (EditText) findViewById(R.id.email);
		password = (EditText) findViewById(R.id.password);

	}

	public void onSignInClick(View v) {
		if (OlaAppathon.getSignUpObject() != null) {
			if (email.getText().toString().equalsIgnoreCase(OlaAppathon.getLoggedInEmail())
					&& password.getText().toString().equalsIgnoreCase(OlaAppathon.getLoggedInPassword())) {
				OlaAppathon.setActivated("true");
				startActivity(ActivityFactory.getInstance().getActivityIntent(OlaConstant.ACTIVITY_DASHBOARD));
				finish();
			} else {
				OlaAppathon.getInstance().showToast("Please Enter valid email and password combination");
			}
		} else {
			OlaAppathon.getInstance().showToast("Please Sign up first");
			finish();
			startActivity(ActivityFactory.getInstance().getActivityIntent(OlaConstant.ACTIVITY_SPLASH_SCREEN));
		}
	}

}
