package net.virgiawan.android.util;

import java.util.Set;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class Storage {

	private static Context context = null;
	private static SharedPreferences sharedPreferences = null;
	// TAG
	public final static String TAG_PREF = "beepMobile";
		
	public static void setContext(Context context){
		Storage.context = context;
	}
	
	public static Context getContext(){
		return Storage.context;
	}
	
	public static SharedPreferences getPref(){
		if (Storage.sharedPreferences == null) {
			if (getContext() != null) {
				Storage.sharedPreferences = getContext()
						.getSharedPreferences(TAG_PREF,
								Activity.MODE_PRIVATE);
			}
			else{
				Log.d("STORAGE ERROR","Context is null");
			}
		}

		return Storage.sharedPreferences;
	}
	
	@SuppressLint("CommitPrefEdits")
	public static Editor prepare(){
		Editor editor = getPref().edit();

		return editor;
	}
	
	// set value to shared pref
	public static boolean setBoolean(String key, boolean value){
		return prepare().putBoolean(key, value).commit();
	}
	
	public static boolean setFloat(String key, float value){
		return prepare().putFloat(key, value).commit();
	}
	
	public static boolean setInt(String key, int value){
		return prepare().putInt(key, value).commit();
	}
	
	public static boolean setLong(String key, long value){
		return prepare().putLong(key, value).commit();
	}
	
	public static boolean setString(String key, String value){
		return prepare().putString(key, value).commit();
	}
	
	public static boolean setStringSet(String key, Set<String> value){
		return prepare().putStringSet(key, value).commit();
	}
	
	// get value from shared pref
	public static boolean geBoolean(String key){
		return getPref().getBoolean(key, false);
	}
	
	public static float geFloat(String key){
		return getPref().getFloat(key, 0);
	}
	
	public static int getInt(String key){
		return getPref().getInt(key, 0);
	}
	
	public static long getLong(String key){
		return getPref().getLong(key, 0);
	}
	
	public static String getString(String key){
		return getPref().getString(key, null);
	}
	
	public static Set<String> getSetString(String key){
		return getPref().getStringSet(key, null);
	}
	
	// remove value of pref
	public static boolean remove(String key){
		return prepare().remove(key).commit();
	}
	
	// remove all pref
	public static boolean removeAll(){
		return prepare().clear().commit();
	}
	
}
