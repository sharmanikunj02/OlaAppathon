package com.olaappathon.app;

import java.util.ArrayList;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.olaappathon.main.OlaAppathon;
import com.olaappathon.navigationdrawer.FragmentAboutApp;
import com.olaappathon.navigationdrawer.FragmentHelp;
import com.olaappathon.navigationdrawer.FragmentHome;
import com.olaappathon.navigationdrawer.FragmentSignOut;
import com.olaappathon.navigationdrawer.FragmentTellAFriend;
import com.olaappathon.navigationdrawer.adapter.NavDrawerListAdapter;
import com.olaappathon.navigationdrawer.model.NavDrawerItem;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends ActionBarActivity {
	
	/** The m drawer layout. */
	private DrawerLayout mDrawerLayout;
	
	/** The m drawer list. */
	private ListView mDrawerList;
	
	/** The m drawer toggle. */
	private ActionBarDrawerToggle mDrawerToggle;
	
	/** The m drawer title. */
	private CharSequence mDrawerTitle;
	
	/** The m title. */
	private CharSequence mTitle;
	
	/** The slider menu titles. */
	private String[] sliderMenuTitles;
	
	/** The slider menu icons. */
	private TypedArray sliderMenuIcons;

	/** The nav drawer items. */
	private ArrayList<NavDrawerItem> navDrawerItems;
	
	/** The adapter. */
	private NavDrawerListAdapter adapter;

	/* (non-Javadoc)
	 * @see android.support.v7.app.ActionBarActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitle = mDrawerTitle = getTitle();

		// getting items of slider from array
		sliderMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// getting Navigation drawer icons from res
		sliderMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		// OLA DashBoard
		navDrawerItems.add(new NavDrawerItem(sliderMenuTitles[0], sliderMenuIcons.getResourceId(0, -1)));
		// Tell a Friend
		navDrawerItems.add(new NavDrawerItem(sliderMenuTitles[1], sliderMenuIcons.getResourceId(1, -1)));
		// Help
		navDrawerItems.add(new NavDrawerItem(sliderMenuTitles[2], sliderMenuIcons.getResourceId(2, -1)));
		// About Application
		navDrawerItems.add(new NavDrawerItem(sliderMenuTitles[3], sliderMenuIcons.getResourceId(3, -1)));
		// Sign Out
		navDrawerItems.add(new NavDrawerItem(sliderMenuTitles[4], sliderMenuIcons.getResourceId(4, -1)));

		// Recycle array
		sliderMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting list adapter for Navigation Drawer
		adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
		mDrawerList.setAdapter(adapter);

		// Enable action bar icon_luncher as toggle Home Button
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.app_name) {

			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(mTitle);
				supportInvalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(mDrawerTitle);
				supportInvalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			displayView(0);
		}
	}

	/**
	 * Slider menu item click listener.
	 *
	 * @see SlideMenuClickEvent
	 */
	private class SlideMenuClickListener implements ListView.OnItemClickListener {
		
		/* (non-Javadoc)
		 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
		 */
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// display view for selected item
			displayView(position);
		}
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// title/icon
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// called when invalidateOptionsMenu() invoke

	/* (non-Javadoc)
	 * @see android.app.Activity#onPrepareOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if Navigation drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Display view.
	 *
	 * @param position the position
	 */
	private void displayView(int position) {
		// update the main content with called Fragment
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new FragmentHome();
			break;
		case 1:
			Intent tellAFriend = new Intent(OlaAppathon.getContext(), FragmentTellAFriend.class);
			startActivity(tellAFriend);
			break;
		case 2:
			fragment = new FragmentHelp();
			break;
		case 3:
			fragment = new FragmentAboutApp();
			break;
		case 4:
			fragment = new FragmentSignOut();
			break;

		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction.replace(R.id.frame_container, fragment).commit();
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(sliderMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			Log.e("this is mainActivity", "Error in else case");
		}
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#setTitle(java.lang.CharSequence)
	 */
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onPostCreate(android.os.Bundle)
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	/* (non-Javadoc)
	 * @see android.support.v7.app.ActionBarActivity#onConfigurationChanged(android.content.res.Configuration)
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

}
