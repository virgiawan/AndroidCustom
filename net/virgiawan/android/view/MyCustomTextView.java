package net.virgiawan.android.view;

import java.util.HashMap;

import com.onebit.beep.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class MyCustomTextView extends TextView {
	private static final String TAG = "TextView";
	private static HashMap<String, Typeface> typefaceCache = new HashMap<String, Typeface>();

	public MyCustomTextView(Context context) {
		super(context);
	}

	public MyCustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setCustomFont(context, attrs);
	}

	public MyCustomTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setCustomFont(context, attrs);
	}

	private void setCustomFont(Context ctx, AttributeSet attrs) {
		TypedArray a = ctx.obtainStyledAttributes(attrs,
				R.styleable.MyCustomTextView);
		String customFont = a.getString(R.styleable.MyCustomTextView_customFont);
		setCustomFont(ctx, customFont);
		a.recycle();
	}

	public boolean setCustomFont(Context ctx, String customFont) {
		Typeface tf = null;
		if(typefaceCache.containsKey(customFont)){
			tf = typefaceCache.get(customFont);
		}
		else{
			try {
				tf = Typeface.createFromAsset(ctx.getAssets(), customFont);
				typefaceCache.put(customFont, tf);
			} catch (Exception e) {
				Log.e(TAG, "Could not get typeface: " + e.getMessage());
				return false;
			}
		}

		setTypeface(tf);
		return true;
	}

}
