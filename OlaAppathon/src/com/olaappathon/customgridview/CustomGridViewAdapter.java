package com.olaappathon.customgridview;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.olaappathon.app.R;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomGridViewAdapter.
 */
public class CustomGridViewAdapter extends ArrayAdapter<GridViewItem> {
	
	/** The context. */
	Context context;
	
	/** The layout resource id. */
	int layoutResourceId;
	
	/** The data. */
	ArrayList<GridViewItem> data = new ArrayList<GridViewItem>();

	/**
	 * Instantiates a new custom grid view adapter.
	 *
	 * @param context the context
	 * @param layoutResourceId the layout resource id
	 * @param data the data
	 */
	public CustomGridViewAdapter(Context context, int layoutResourceId,
			ArrayList<GridViewItem> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		RecordHolder holder = null;
		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new RecordHolder();
			holder.txtTitle = (TextView) row.findViewById(R.id.item_text);
			holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}
		GridViewItem item = data.get(position);
		holder.txtTitle.setText(item.getTitle());
		holder.imageItem.setImageBitmap(item.getImage());
		return row;
	}

	/**
	 * The Class RecordHolder.
	 */
	static class RecordHolder {
		
		/** The txt title. */
		TextView txtTitle;
		
		/** The image item. */
		ImageView imageItem;
	}
}
