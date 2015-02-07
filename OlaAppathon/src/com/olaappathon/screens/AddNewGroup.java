package com.olaappathon.screens;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import InputNameDialogFragment.CustomNameNumberDialogFragment;
import InputNameDialogFragment.CustomNameNumberDialogFragment.NameNumberDialogListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.olaappathon.app.R;
import com.olaappathon.customgridview.CustomGridViewAdapter;
import com.olaappathon.customgridview.GridViewItem;
import com.olaappathon.interfaces.SummaryInterface;
import com.olaappathon.main.OlaAppathon;
import com.olaappathon.navigationdrawer.adapter.SummaryAdapter;
import com.olaappathon.navigationdrawer.adapter.SummaryItem;
import com.olaappathon.options.GroupDetailOptions;

public class AddNewGroup extends FragmentActivity implements NameNumberDialogListener, android.widget.AdapterView.OnItemClickListener {

	/** The cancelDeleteHeader layout. */
	private LinearLayout cancelDeleteHeader;
	/** The dialog alert fragment. */
	private DialogFragment dialogAlertFragment;
	private ListView listView;

	ArrayList<GridViewItem> gridArray = new ArrayList<GridViewItem>();
	CustomGridViewAdapter customGridAdapter;
	SummaryAdapter addMemberAdapter;
	public static Gson GSON = new Gson();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_new_group);

		cancelDeleteHeader = (LinearLayout) findViewById(R.id.cancel_delete_header);
		cancelDeleteHeader.setVisibility(View.VISIBLE);
		listView = (ListView) findViewById(android.R.id.list);
		addMemberAdapter = new SummaryAdapter(this);

	}

	public void onManualAddMemberClick(View v) {

		FragmentManager fragmentManager = getSupportFragmentManager();

		dialogAlertFragment = CustomNameNumberDialogFragment.newInstance(1, "Enter Details", null, getString(R.string.common_ok),
				getString(R.string.common_cancel));
		dialogAlertFragment.show(fragmentManager, null);
		// CustomNameNumberDialogFragment inputNameDialog = new CustomNameNumberDialogFragment();
		// inputNameDialog.setCancelable(true);
		// inputNameDialog.show(fragmentManager, "Input Dialog");
		// MyCustomDialog dialog = new MyCustomDialog(v);
		// dialog.show();

	}

	public void onCancelClick(View v) {
		OlaAppathon.getInstance().showToast("Call Create Group APi");
		finish();
	}

	/**
	 * Method onSaveClick.: Call back for Save button of registered IMSI.
	 * 
	 * @param v
	 *            the v
	 */
	public void onSaveClick(View v) {
		Toast.makeText(this, "Call Create Group APi", Toast.LENGTH_SHORT).show();
		JSONObject resultObject = new JSONObject();
		JSONArray groupArray = new JSONArray();
		JSONObject groupIndividual = new JSONObject();
		try {
			// groupIndividual.put("groupId", "");
			groupIndividual.put("groupName", "Group Name");
			groupIndividual.put("ownerNo", "");

			JSONArray memberDetailsArray = new JSONArray();
			for (int i = 0; i < addMemberAdapter.getCount(); i++) {
				JSONObject memberDetailsObject = new JSONObject();
				memberDetailsObject.put("personName", addMemberAdapter.getItem(i).getSummaryName());
				memberDetailsObject.put("phoneNo", addMemberAdapter.getItem(i).getSummarySummary());
				memberDetailsObject.put("emailId", "Nik@nik.com");
				memberDetailsObject.put("role", "Owner");
				memberDetailsArray.put(i, memberDetailsObject);
			}

			groupIndividual.accumulate("memberDetails", memberDetailsArray);
			groupArray.put(0, groupIndividual);
			resultObject.accumulate("groupDetails", groupArray);
			Log.d(OlaAppathon.TAG, resultObject.toString());
//			GroupDetails GroupDetails;
//			GroupDetails= GSON.fromJson(resultObject.toString(), GroupDetails.class);
			if(OlaAppathon.checkNetworkConnection()){
				Log.d(OlaAppathon.TAG,"Network is available");				
			}							
			GroupDetailOptions.setGroupDetailObject(resultObject.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onPositiveButtonClick(String name, String number) {
		OlaAppathon.getInstance().showToast("Add member: " + name + "Number: " + number);
		SummaryItem item = new SummaryItem(name, number);
		addMemberAdapter.add(item);
		listView.setAdapter(addMemberAdapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
		SummaryInterface item = (SummaryInterface) listView.getItemAtPosition(position);
		addMemberAdapter.remove(item);
		addMemberAdapter.notifyDataSetChanged();
		OlaAppathon.getInstance().showToast("Deleted " + item.getSummaryName());
	}

}
