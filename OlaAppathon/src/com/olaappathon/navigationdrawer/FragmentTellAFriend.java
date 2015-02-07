package com.olaappathon.navigationdrawer;

import android.os.Bundle;

import com.olaappathon.app.R;
import com.olaappathon.screens.CommonActivity;

public class FragmentTellAFriend extends CommonActivity {
	// this Fragment will be called from DashBoardActivity
	public FragmentTellAFriend(){}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tell_a_friend_fragment);
	}
	
}