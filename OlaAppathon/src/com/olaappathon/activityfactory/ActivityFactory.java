package com.olaappathon.activityfactory;

import android.content.Intent;

import com.olaappathon.app.DashBoardActivity;
import com.olaappathon.helper.OlaConstant;
import com.olaappathon.main.OlaAppathon;
import com.olaappathon.navigationdrawer.FragmentHelp;
import com.olaappathon.navigationdrawer.FragmentTellAFriend;
import com.olaappathon.screens.OlaMapActivity;
import com.olaappathon.screens.SignInActivity;
import com.olaappathon.screens.SignUpActivity;
import com.olaappathon.screens.SplashScreen;

public class ActivityFactory {

	/* singleton instance*/
	/** The Constant factoryInstance. */
	private static final ActivityFactory factoryInstance = new ActivityFactory();
	
	/* reducing the scope of constructor for singleton implementation*/
	/**
	 * Instantiates a new rules factory.
	 */
	private ActivityFactory() {
		
	}
	 
	/**
	 * public static method to get the factory instance.
	 *
	 * @return single instance of RulesFactory
	 */
	public static final ActivityFactory getInstance() {
		return factoryInstance;
	}
	
	
    /**
     * Gets the rules.
     * 
     * @param ruleType
     *            the rule type
     * @return the rules
     */
    public final Intent getActivityIntent(final String ruleType) {
        Intent activity = null;
        if (OlaConstant.ACTIVITY_SIGNIN.equalsIgnoreCase(ruleType)) {
        	activity = new  Intent(OlaAppathon.getContext(), SignInActivity.class);
        } else if (OlaConstant.ACTIVITY_SIGNUP.equalsIgnoreCase(ruleType)) {
        	activity = new  Intent(OlaAppathon.getContext(), SignUpActivity.class);
        }
        else if(OlaConstant.ACTIVITY_DASHBOARD.equalsIgnoreCase(ruleType)){
          	activity = new  Intent(OlaAppathon.getContext(), DashBoardActivity.class);
        }  
        else if(OlaConstant.ACTIVITY_EMERGENCY_MANAGER.equalsIgnoreCase(ruleType)){
          	activity = new  Intent(OlaAppathon.getContext(), FragmentHelp.class);
        }  
        else if(OlaConstant.ACTIVITY_TELL_A_FRIEND.equalsIgnoreCase(ruleType)){
          	activity = new  Intent(OlaAppathon.getContext(), FragmentTellAFriend.class);
        }  
        else if(OlaConstant.ACTIVITY_SPLASH_SCREEN.equalsIgnoreCase(ruleType)){
          	activity = new  Intent(OlaAppathon.getContext(), SplashScreen.class);
        } 
        else if(OlaConstant.ACTIVITY_OLA_MAP.equalsIgnoreCase(ruleType)){
          	activity = new  Intent(OlaAppathon.getContext(), OlaMapActivity.class);
        } 

        return activity;
    }
	
	
	
}
