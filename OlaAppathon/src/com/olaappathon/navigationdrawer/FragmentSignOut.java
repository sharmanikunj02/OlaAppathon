package com.olaappathon.navigationdrawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olaappathon.app.R;

public class FragmentSignOut extends Fragment {
	
	public FragmentSignOut(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.sign_out_fragment, container, false);
         
        return rootView;
    }
}
