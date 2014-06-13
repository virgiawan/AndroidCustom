package net.virgiawan.android.util;

import java.util.HashMap;

import android.app.Activity;
import android.util.DisplayMetrics;

public class ScreenMeasurement {

	public static final int MDPI = 1;
	public static final int HDPI = 2;
	public static final int XHDPI = 3;
	private Activity activity;
	private HashMap<String, Integer> result;
	private DisplayMetrics display;

	public ScreenMeasurement(Activity activity) {
		this.activity = activity;
		this.getScreenSize();
	}

	private HashMap<String, Integer> getScreenSize() {
		result = new HashMap<String, Integer>();

		display = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(display);

		int width = display.widthPixels;
		int height = display.heightPixels;

		int pxWidth = (int) Math.ceil(width * (display.densityDpi / 160.0));
		int pxHeight = (int) Math.ceil(height * (display.densityDpi / 160.0));

		result.put("width", width);
		result.put("height", height);
		result.put("pxWidth", pxWidth);
		result.put("pxHeight", pxHeight);

		return result;
	}
	
	public int getWidth(){
		return result.get("width");
	}
	
	public int getHeight(){
		return result.get("height");
	}
	
	public int getWidthInPx(){
		return result.get("pxWidth");
	}
	
	public int getHeightInPx(){
		return result.get("pxHeight");
	}
	
	public double getInches(){
		double density = display.density * 160;
		double x = Math.pow(display.widthPixels / density, 2);
		double y = Math.pow(display.heightPixels / density, 2);
		double screenInches = Math.sqrt(x + y);
		
		return screenInches;
	}

	public int dpToPx(int dp) {
		DisplayMetrics displayMetrics = activity.getResources()
				.getDisplayMetrics();
		int px = Math.round(dp
				* (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
		return px;
	}

	public int pxToDp(int px) {
		DisplayMetrics displayMetrics = activity.getResources()
				.getDisplayMetrics();
		int dp = Math.round(px
				/ (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
		return dp;
	}

	public int getTypeDensity() {
		int densityType = 0;
		float type = activity.getResources().getDisplayMetrics().density;
		if (type == 2.0) {
			// xhdpi
			densityType = ScreenMeasurement.XHDPI;
		} else if (type == 1.5) {
			// hdpi
			densityType = ScreenMeasurement.HDPI;
		} else {
			// mdpi
			densityType = ScreenMeasurement.MDPI;
		}
		return densityType;
	}

}
