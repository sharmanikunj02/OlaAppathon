package com.olaappathon.adapters;


import java.util.Vector;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;


/**
 * The Class AdapterAdapter
 */
public class AdapterAdapter extends BaseAdapter 
{
	public final Vector<BaseAdapter> adapters = new Vector<BaseAdapter>();
	
    private DataSetObserver dataSetObserver = new DataSetObserver() 
    {
        @Override
        public void onChanged() 
        {
            notifyDataSetChanged();  
        }
    };
    
	/**
	 * Constructor for AdapterAdapter.
	 * @param context Context
	 */
	public AdapterAdapter(Context context) 
	{
		super();
	}

	/**
	 * Method add.
	 * @param adapter BaseAdapter
	 */
	public void add(BaseAdapter adapter)
	{
		this.adapters.add(adapter);
        adapter.registerDataSetObserver(dataSetObserver);
        this.notifyDataSetChanged();
	}
	 
	public void clear() 
    {
		for (Adapter adapter : adapters)
		{
			adapter.unregisterDataSetObserver(dataSetObserver);
		}
		adapters.clear();
		notifyDataSetInvalidated();
    }
   
    /**
     * Method getCount.
     * @return int
     * @see android.widget.Adapter#getCount()
     */
    @Override
	public int getCount() 
	{
		int count = 0;
    	for (BaseAdapter adapter : adapters)
    	{
    		count += adapter.getCount();
    	}
    	return count;
	}
	
    /**
     * Method getItem.
     * @param position int
     * @return Object
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public Object getItem(int position) 
	{
		for (BaseAdapter adapter : this.adapters) 
		{
			int size = adapter.getCount(); 
			if (position < size)
			{
				return adapter.getItem(position);
			}
			
			// Skip over adapter.
			position -= size;
		}
		return null;
	}
	
    /**
     * Method getItemId.
     * @param position int
     * @return long
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
	public long getItemId(int position) 
	{
		for (BaseAdapter adapter : adapters) 
		{
			int size = adapter.getCount();
			if (position < size) 
			{
				return adapter.getItemId(position);
			}
			
			// Skip over adapter.
			position -= size;
		}
		return 0L;	
	}
	
	/**
	 * Method getView.
	 * @param position int
	 * @param view View
	 * @param parent ViewGroup
	 * @return View
	 * @see android.widget.Adapter#getView(int, View, ViewGroup)
	 */
	@Override
	public View getView(int position, View view, ViewGroup parent) 
	{
		for (BaseAdapter adapter : adapters) 
		{
			int size = adapter.getCount();
			if (position < size) 
			{
				return adapter.getView(position, view, parent);
			}
			
			// Skip over adapter.
			position -= size;
		}
		return null;
	}

	/**
	 * Method getItemViewType.
	 * @param position int
	 * @return int
	 * @see android.widget.Adapter#getItemViewType(int)
	 */
	@Override
	public int getItemViewType(int position)
	{
		for (BaseAdapter adapter : adapters)
		{
			int size = adapter.getCount();
			if (position < size)
			{
				return adapter.getItemViewType(position);
			}

			// Skip over adapter.
			position -= size;
		}
		return 0;
	}
	
	/**
	 * Method isEnabled.
	 * @param position int
	 * @return boolean
	 * @see android.widget.ListAdapter#isEnabled(int)
	 */
	@Override
	public boolean isEnabled(int position)
	{
		for (BaseAdapter adapter : this.adapters) 
		{
			int size = adapter.getCount();
			if (position < size)
			{
				return adapter.isEnabled(position);
			}
			
			// Skip over adapter.
			position -= size;
		}
		return true;
	}
	
	/**
	 * Method areAllItemsEnabled.
	 * @return boolean
	 * @see android.widget.ListAdapter#areAllItemsEnabled()
	 */
	@Override
	public boolean areAllItemsEnabled()
	{
		for (BaseAdapter adapter : this.adapters) 
		{
			if (!adapter.areAllItemsEnabled()) return false;
		}
		return true;
	}
	
	/**
	 * Method isEmpty.
	 * @return boolean
	 * @see android.widget.Adapter#isEmpty()
	 */
	@Override
	public boolean isEmpty()
	{
		for (BaseAdapter adapter : this.adapters) 
		{
			if (!adapter.isEmpty()) return false;
		}
		return true;
	}
}
