package br.com.a2dm.spdmws.utils;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.GsonBuilder;

public class JsonUtils {

	private JsonUtils() {
	}
	
	public static String toJson(Object obj) {
		return new GsonBuilder().create().toJson(obj);
	}
	
	public static <T> T fromJson(String json, Class<T> classOfT) {
		return new GsonBuilder().create().fromJson(json, classOfT);
	}
	
	public static JSONObject parse(String str) throws JSONException {
		return new JSONObject(str);
	}
	

}
