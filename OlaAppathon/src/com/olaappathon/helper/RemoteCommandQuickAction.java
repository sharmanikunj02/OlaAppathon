package com.olaappathon.helper;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.ScrollView;
import android.widget.TextView;

import com.olaappathon.app.R;

public class RemoteCommandQuickAction extends RemoteCommandPopupWindows implements OnDismissListener {
	private View mRootView;
	private ImageView mArrowDown;
	private LayoutInflater mInflater;
	private ViewGroup mTrack;
	private ScrollView mScroller;
	private OnActionItemClickListener mItemClickListener;
	private OnDismissListener mDismissListener;

	private List<RemoteCommandActionItem> actionItems = new ArrayList<RemoteCommandActionItem>();

	private boolean mDidAction;

	private int mChildPos;
	private int mInsertPos;
	private int rootWidth = 0;

	public RemoteCommandQuickAction(final Context context) {
		super(context);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		setRootViewId(R.layout.popup_vertical);
		mChildPos = 0;
	}

	/**
	 * Get action item at an index
	 * 
	 * @param index
	 *            Index of item (position from callback)
	 * 
	 * @return Action Item at the position
	 */
	public final RemoteCommandActionItem getActionItem(final int index) {
		return actionItems.get(index);
	}

	/**
	 * Set root view.
	 * 
	 * @param id
	 *            Layout resource id
	 */
	public final void setRootViewId(final int id) {
		mRootView = mInflater.inflate(id, null);
		mTrack = (ViewGroup) mRootView.findViewById(R.id.tracks);
		mScroller = (ScrollView) mRootView.findViewById(R.id.scroller);
		//mArrowDown = (ImageView) mRootView.findViewById(R.id.arrow_down);
		mRootView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		setContentView(mRootView);
	}

	/**
	 * Set listener for action item clicked.
	 * 
	 * @param listener
	 *            Listener
	 */
	public final void setOnActionItemClickListener(final OnActionItemClickListener listener) {
		mItemClickListener = listener;
	}

	/**
	 * Add action item
	 * 
	 * @param action
	 *            {@link RemoteCommandActionItem}
	 */
	public final void addActionItem(final RemoteCommandActionItem action) {
		actionItems.add(action);
		String title = action.getTitle();
		Drawable icon = action.getIcon();
		View container;
		container = mInflater.inflate(R.layout.action_item_vertical, null);
		ImageView img = (ImageView) container.findViewById(R.id.iv_icon);
		TextView text = (TextView) container.findViewById(R.id.tv_title);
		if (icon != null) {
			img.setImageDrawable(icon);
		} else {
			img.setVisibility(View.GONE);
		}

		if (title != null) {
			text.setText(title);
		} else {
			text.setVisibility(View.GONE);
		}
		final int pos = mChildPos;
		final int actionId = action.getActionId();
		container.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(final View v) {
				if (mItemClickListener != null) {
					mItemClickListener.onItemClick(RemoteCommandQuickAction.this, pos, actionId);
				}
				if (!getActionItem(pos).isSticky()) {
					mDidAction = true;
					dismiss();
				}
			}
		});
		container.setFocusable(true);
		container.setClickable(true);
		mTrack.addView(container, mInsertPos);
		mChildPos++;
		mInsertPos++;
	}

	/**
	 * Show quickaction popup. Popup is automatically positioned, on top or bottom of anchor view.
	 * 
	 */
	public final void show(final View anchor) {
		preShow();
		int xPos, yPos, arrowPos;
		mDidAction = false;
		int[] location = new int[2];
		anchor.getLocationOnScreen(location);
		Rect anchorRect = new Rect(location[0], location[1], location[0] + anchor.getWidth(), location[1] + anchor.getHeight());
		mRootView.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		int rootHeight = mRootView.getMeasuredHeight();
		if (rootWidth == 0) {
			rootWidth = mRootView.getMeasuredWidth();
		}
		int screenWidth = mWindowManager.getDefaultDisplay().getWidth();
		int screenHeight = mWindowManager.getDefaultDisplay().getHeight();
		// automatically get X coord of popup (top left)
		if ((anchorRect.left + rootWidth) > screenWidth) {
			xPos = anchorRect.left - (rootWidth - anchor.getWidth());
			xPos = (xPos < 0) ? 0 : xPos;
			arrowPos = anchorRect.centerX() - xPos;
		} else {
			if (anchor.getWidth() > rootWidth) {
				xPos = anchorRect.centerX() - (rootWidth / 2);
			} else {
				xPos = anchorRect.left;
			}
			arrowPos = anchorRect.centerX() - xPos;
		}
		int dyTop = anchorRect.top;
		int dyBottom = screenHeight - anchorRect.bottom;
		boolean onTop = (dyTop > dyBottom) ? true : false;
		if (onTop) {
			if (rootHeight > dyTop) {
				yPos = 15;
				LayoutParams l = mScroller.getLayoutParams();
				l.height = dyTop - anchor.getHeight();
			} else {
				yPos = anchorRect.top - rootHeight;
			}
		} else {
			yPos = anchorRect.bottom;
			if (rootHeight > dyBottom) {
				LayoutParams l = mScroller.getLayoutParams();
				l.height = dyBottom;
			}
		}
		// Here we are setting Animation style
		//showArrow(R.id.arrow_down, arrowPos);
		mWindow.setAnimationStyle(R.style.Animations_PopUpMenu_Reflect);
		mWindow.showAtLocation(anchor, Gravity.NO_GRAVITY, xPos, yPos);
	}

	/**
	 * Set listener for window dismissed. This listener will only be fired if the quicakction dialog is dismissed by clicking outside the dialog or
	 * clicking on sticky item.
	 */
	public final void setOnDismissListener(final RemoteCommandQuickAction.OnDismissListener listener) {
		setOnDismissListener(this);
		mDismissListener = listener;
	}

	@Override
	public final void onDismiss() {
		if (!mDidAction && mDismissListener != null) {
			mDismissListener.onDismiss();
		}
	}

	/**
	 * Listener for item click
	 */
	public interface OnActionItemClickListener {
		void onItemClick(RemoteCommandQuickAction source, int pos, int actionId);
	}

	/**
	 * Listener for window dismiss
	 */
	public interface OnDismissListener {
		void onDismiss();
	}

	/**
	 * Show arrow
	 * 
	 * @param whichArrow
	 *            arrow type resource id
	 * @param requestedX
	 *            distance from left screen
	 */
	private void showArrow(final int whichArrow, final int requestedX) {
		final View showArrow = mArrowDown;
		final int arrowWidth = mArrowDown.getMeasuredWidth();
		showArrow.setVisibility(View.VISIBLE);
		ViewGroup.MarginLayoutParams param = (ViewGroup.MarginLayoutParams) showArrow.getLayoutParams();
		param.leftMargin = requestedX - arrowWidth / 2;
	}
}