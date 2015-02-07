package com.olaappathon.screens;

import org.apache.http.HttpResponse;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

import com.olaappathon.activityfactory.ActivityFactory;
import com.olaappathon.app.R;
import com.olaappathon.helper.OlaConstant;
import com.olaappathon.helper.StringHelper;
import com.olaappathon.interfaces.HttpInterface;
import com.olaappathon.main.OlaAppathon;
import com.olaappathon.tasks.HttpPostTask;

public class SignUpActivity extends FragmentActivity implements HttpInterface {

	/** The email. */
	private EditText email;

	/** The password. */
	private EditText password;
	private EditText name;
	private EditText confirmPassword;
	private EditText number;

	private static final int HTTP_SIGNUP_TASK = 1;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.sign_up);
		email = (EditText) findViewById(R.id.email);
		name = (EditText) findViewById(R.id.name);
		password = (EditText) findViewById(R.id.password);
		confirmPassword = (EditText) findViewById(R.id.confirmPassword);
		number = (EditText) findViewById(R.id.number);

	}

	public void onSignUpClick(View v) {

		if (!OlaAppathon.checkNetworkConnection()) {
			OlaAppathon.getInstance().showToast(OlaAppathon.getContext().getString(R.string.network_error));
		} 
		else if (StringHelper.isEmpty(email.getText().toString()) || !StringHelper.isValidEmail(email.getText().toString())) {
			OlaAppathon.getInstance().showToast(OlaAppathon.getContext().getString(R.string.incorrect_email));
		} else if (StringHelper.isEmpty(password.getText().toString())) {
			OlaAppathon.getInstance().showToast(OlaAppathon.getContext().getString(R.string.empty_password));
		} else {
			HttpPostTask task = new HttpPostTask(HTTP_SIGNUP_TASK, this);
			JSONObject jsonObject = new JSONObject();
			try {
				jsonObject.put("email", email.getText().toString());
				jsonObject.put("password", password.getText().toString());
				jsonObject.put("name", name.getText().toString());
				jsonObject.put("mobile", number.getText().toString());
				jsonObject.put("source", "and-app");
//				jsonObject.put("device_id", Platform.getDeviceId());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

			OlaAppathon.setActivated("true");
			startActivity(ActivityFactory.getInstance().getActivityIntent(OlaConstant.ACTIVITY_DASHBOARD));
			finish();
			
//			task.setJson(jsonObject);
//			task.execute(OlaAppathon.getContext().getString(R.string.OLA_SERVER_URL) + "/user/signup");
		}
	}

	@Override
	public void onHttpResponse(int id, HttpResponse response, String result) {
	}
}
