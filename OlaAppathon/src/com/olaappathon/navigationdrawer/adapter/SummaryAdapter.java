package com.olaappathon.navigationdrawer.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.olaappathon.app.R;
import com.olaappathon.interfaces.SummaryInterface;

// TODO: Auto-generated Javadoc
/**
 * The Class SummaryAdapter.
 * 
 * @author snap
 * @version $Revision: 1.0 $
 */
public class SummaryAdapter extends ArrayAdapter<SummaryInterface> {


	/** The inflater. */
	private final LayoutInflater inflater;

	/** The layout id. */
	private final int layoutId;

	/** The context. */
	private final Context context;


	/**
	 * Constructor for SummaryAdapter.
	 * 
	 * @param context
	 *            Context
	 * @param layoutId
	 *            int
	 */
	public SummaryAdapter(Context context, int layoutId) {
		this(context, layoutId, true);
	}

	/**
	 * Constructor for SummaryAdapter.
	 * 
	 * @param context
	 *            Context
	 * @param layoutId
	 *            int
	 * @param isRoundCorner
	 *            boolean
	 */
	public SummaryAdapter(Context context, int layoutId, boolean isRoundCorner) {
		super(context, layoutId, android.R.id.text1);
		this.layoutId = layoutId;
		this.inflater = LayoutInflater.from(context);
		this.context = context;
	}

	/**
	 * Constructor for SummaryAdapter.
	 * 
	 * @param context
	 *            Context
	 */
	public SummaryAdapter(Context context) {
		this(context, R.layout.list_item_summary);
	}

	/**
	 * Method getItemId.
	 * 
	 * @param position
	 *            int
	 * 
	 * 
	 * @return long * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return getItem(position).getSummaryId();
	}

	/**
	 * Method getView.
	 * 
	 * @param position
	 *            int
	 * @param view
	 *            View
	 * @param parent
	 *            ViewGroup
	 * 
	 * 
	 * @return View * @see android.widget.Adapter#getView(int, View, ViewGroup)
	 */
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		SummaryInterface item = getItem(position);
		int itemLayoutId = (item.getSummaryLayoutId() > 0 ? item.getSummaryLayoutId() : layoutId);
		ViewHolder holder = (ViewHolder) (view == null ? null : view.getTag());

		StringBuffer screenName1 = new StringBuffer();
		screenName1.insert(0, inflater.getContext());

		// Create view.
		if ((view == null) || (holder == null) || (holder.layoutId != itemLayoutId)) {
			view = this.inflater.inflate(itemLayoutId, null);
			holder = null;
		}

		if (holder == null) {
			holder = new ViewHolder(itemLayoutId, view);
			view.setTag(holder);
		}

		// Populate view.
		if (holder.icon1 != null) {
			Drawable sumIcon = item.getSummaryIcon();

			if (sumIcon == null) {
				holder.icon1.setVisibility(View.GONE);
			} else {
				holder.icon1.setVisibility(View.VISIBLE);
				holder.icon1.setImageDrawable(item.getSummaryIcon());
			}
		}

		if (holder.text1 != null) {
			holder.text1.setText(item.getSummaryName());
			holder.text1.setVisibility(holder.text1.getText().length() == 0 ? View.GONE : View.VISIBLE);
		}

		if (holder.text2 != null) {
			holder.text2.setText(item.getSummarySummary());
			holder.text2.setVisibility(holder.text2.getText().length() == 0 ? View.GONE : View.VISIBLE);
		}

		
		if (holder.checkbox != null) {
			holder.checkbox.setVisibility(item.isSummaryCheckable() ? View.VISIBLE : View.GONE);
			holder.checkbox.setChecked(item.isSummaryChecked());
		}

		if (holder.icon2 != null) {
			holder.icon2.setVisibility(item.getSummaryAccessory() == null ? View.GONE : View.VISIBLE);
			holder.icon2.setImageDrawable(item.getSummaryAccessory());
		}

		return view;
	}

	/**
	 * The Class ViewHolder.
	 * 
	 * @author snap
	 * @version $Revision: 1.0 $
	 */
	private class ViewHolder {

		/** The layout id. */
		public int layoutId;

		/** The icon1. */
		public ImageView icon1;

		/** The icon2. */
		public ImageView icon2;

		/** The checkbox. */
		public CheckedTextView checkbox;

		/** The text1. */
		public TextView text1;

		/** The text2. */
		public TextView text2;


		/**
		 * Constructor for ViewHolder.
		 * 
		 * @param layoutId
		 *            int
		 * @param view
		 *            View
		 */
		public ViewHolder(int layoutId, View view) {
			this.layoutId = layoutId;
			this.icon1 = (ImageView) view.findViewById(android.R.id.icon1);
			this.icon2 = (ImageView) view.findViewById(android.R.id.icon2);
			this.text1 = (TextView) view.findViewById(android.R.id.text1);
			this.text2 = (TextView) view.findViewById(android.R.id.text2);
			this.checkbox = (CheckedTextView) view.findViewById(android.R.id.checkbox);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#finalize()
		 */
		@Override
		public void finalize() {
			try {
				super.finalize();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
