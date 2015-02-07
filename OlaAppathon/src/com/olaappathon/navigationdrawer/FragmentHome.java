package com.olaappathon.navigationdrawer;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.olaappathon.app.R;
import com.olaappathon.customgridview.CustomGridViewAdapter;
import com.olaappathon.customgridview.GridViewItem;
import com.olaappathon.screens.OlaMapActivity;
import com.olaappathon.screens.RideMapActivity;

public class FragmentHome extends Fragment {
	// this Fragment will be called from DashBoardActivity
	GridView gridView;
	ArrayList<GridViewItem> gridArray = new ArrayList<GridViewItem>();
	CustomGridViewAdapter customGridAdapter;

	public FragmentHome() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.home_fragment, container, false);

		return rootView;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Bitmap homeIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher);
		Bitmap userIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher);
		gridArray.add(new GridViewItem(homeIcon, "Vicinity"));
		gridArray.add(new GridViewItem(userIcon, "Ride Now"));
		gridView = (GridView) getView().findViewById(R.id.gridView1);
		customGridAdapter = new CustomGridViewAdapter(getActivity(), R.layout.row_grid, gridArray);
		gridView.setAdapter(customGridAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
				switch (position) {
				case 0:
					Intent startScenario = new Intent(getActivity(), OlaMapActivity.class);
					startActivity(startScenario);
					break;
				case 1:
					Intent startMap = new Intent(getActivity(), RideMapActivity.class);
					startActivity(startMap);
					break;

				default:
					break;
				}

			}
		});

	}

}