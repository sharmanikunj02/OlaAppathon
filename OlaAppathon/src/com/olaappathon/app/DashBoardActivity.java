package com.olaappathon.app;

import java.util.ArrayList;
import java.util.List;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.olaappathon.activityfactory.ActivityFactory;
import com.olaappathon.helper.OlaConstant;
import com.olaappathon.listner.PanicTriggerListner;
import com.olaappathon.main.OlaAppathon;
import com.olaappathon.main.Platform;
import com.olaappathon.navigationdrawer.FragmentAboutApp;
import com.olaappathon.navigationdrawer.FragmentHome;
import com.olaappathon.navigationdrawer.adapter.NavDrawerListAdapter;
import com.olaappathon.navigationdrawer.model.NavDrawerItem;

// TODO: Auto-generated Javadoc
/**
 * The Class DashBoardActivity.
 */
public class DashBoardActivity extends ActionBarActivity {

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

	/** The my receiver. */
	private PanicTriggerListner myReceiver;

	/*
	 * (non-Javadoc)
	 * 
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
		if (myReceiver == null) {
			registerPanicTrigger();
		}
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
	 * Register panic trigger.
	 */
	private void registerPanicTrigger() {
		IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		filter.addAction(Intent.ACTION_USER_PRESENT);
		myReceiver = new PanicTriggerListner();
		registerReceiver(myReceiver, filter);
	}

	/**
	 * Slider menu item click listener.
	 * 
	 * @see SlideMenuClickEvent
	 */
	private class SlideMenuClickListener implements ListView.OnItemClickListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
		 */
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// display view for selected item
			displayView(position);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
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
	 * @param position
	 *            the position
	 */
	private void displayView(int position) {
		// update the main content with called Fragment
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new FragmentHome();
			break;
		case 1:
			ShowShareApplicationOption();
			// startActivity(ActivityFactory.getInstance().getActivityIntent(OlaConstant.ACTIVITY_TELL_A_FRIEND));
			break;
		case 2:
			startActivity(ActivityFactory.getInstance().getActivityIntent(OlaConstant.ACTIVITY_EMERGENCY_MANAGER));
			break;
		case 3:
			fragment = new FragmentAboutApp();
			break;
		case 4:
			OlaAppathon.clearActivatedKey();
			startActivity(ActivityFactory.getInstance().getActivityIntent(OlaConstant.ACTIVITY_SPLASH_SCREEN));
			finish();
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

	/**
	 * Show share application option.
	 */
	private void ShowShareApplicationOption() {

		Resources resources = getResources();
		Intent emailIntent = new Intent();
		emailIntent.setAction(Intent.ACTION_SEND);
		String body = resources.getString(R.string.email_manager_share_email_body1) + "\n\n" + resources.getString(R.string.email_manager_share_email_body2);
		emailIntent.putExtra(Intent.EXTRA_TEXT, body);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT,
				resources.getString(R.string.email_manager_share_email_subject, OlaAppathon.getContext().getString(R.string.product_name)));
		emailIntent.setType("message/rfc822");
		PackageManager pm = OlaAppathon.getContext().getPackageManager();
		Intent sendIntent = new Intent(Intent.ACTION_SEND);
		sendIntent.setType("text/plain");

		String text = getString(R.string.email_manager_share);
		Intent openInChooser;
		if (Platform.getDeviceApiVersion() == Build.VERSION_CODES.JELLY_BEAN_MR2) {
			openInChooser = Intent.createChooser(emailIntent, text);
		} else {
			SpannableString spannable = new SpannableString(text);
			// here we set the color
			spannable.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.yellow)), 0, spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			openInChooser = Intent.createChooser(emailIntent, spannable);
		}
		List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
		List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
		for (int i = 0; i < resInfo.size(); i++) {
			// Extract the label, append it, and repackage it in a
			// LabeledIntent
			ResolveInfo ri = resInfo.get(i);
			String packageName = ri.activityInfo.packageName;
			if (packageName.contains("android.email")) {
				emailIntent.setPackage(packageName);
			} else if (packageName.contains("twitter") || packageName.contains("mms") || packageName.contains("sms") || packageName.contains("android.gm")
					|| packageName.contains("sonyericsson.conversations") || packageName.contains("facebook")) {
				Intent intent = new Intent();
				intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
				intent.setAction(Intent.ACTION_SEND);
				intent.setType("text/plain");
				if (packageName.contains("twitter")) {
					intent.putExtra(
							Intent.EXTRA_TEXT,
							resources.getString(R.string.email_manager_share_text_body, OlaAppathon.getContext().getString(R.string.product_name))
									+ resources.getString(R.string.email_manager_fb_twitter_link_staysafe,
											OlaAppathon.getContext().getString(R.string.product_name)));
				} else if (packageName.contains("mms") || packageName.contains("sms") || packageName.contains("sonyericsson.conversations")) {
					intent.putExtra(Intent.EXTRA_TEXT,
							resources.getString(R.string.email_manager_share_text_body, OlaAppathon.getContext().getString(R.string.product_name)));
				} else if (packageName.contains("android.gm")) {
					intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(body));
					intent.putExtra(Intent.EXTRA_SUBJECT,
							resources.getString(R.string.email_manager_share_email_subject, OlaAppathon.getContext().getString(R.string.product_name)));
					intent.setType("message/rfc822");
				} else if (packageName.contains("facebook")) {
					intent.putExtra(
							Intent.EXTRA_TEXT,
							resources.getString(R.string.email_manager_facebook_share_text_body, OlaAppathon.getContext().getString(R.string.product_name))
									+ " "
									+ resources.getString(R.string.email_manager_fb_twitter_link_staysafe,
											OlaAppathon.getContext().getString(R.string.product_name))
									+ " "
									+ resources.getString(R.string.email_manager_fb_twitter_link_lovenewapps,
											OlaAppathon.getContext().getString(R.string.product_name)));
				}

				intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
			}
		}
		// convert intentList to array
		LabeledIntent[] extraIntents = intentList.toArray(new LabeledIntent[intentList.size()]);
		openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
		startActivity(openInChooser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#setTitle(java.lang.CharSequence)
	 */
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPostCreate(android.os.Bundle)
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v7.app.ActionBarActivity#onConfigurationChanged(android.content.res.Configuration)
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

}
