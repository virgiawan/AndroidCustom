package net.virgiawan.android.util;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

public class JsonParserUtil {

	public static String loadJSONFromAsset(Context context, String filename) {
	    String json = null;
	    try {
	        InputStream is = context.getAssets().open(filename);

	        int size = is.available();
	        byte[] buffer = new byte[size];
	        is.read(buffer);
	        is.close();
	        json = new String(buffer, "UTF-8");
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return null;
	    }
	    return json;
	}
	
}
