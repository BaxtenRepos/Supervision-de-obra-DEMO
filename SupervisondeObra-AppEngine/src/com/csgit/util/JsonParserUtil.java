package com.csgit.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonParserUtil {

	private static JsonParserUtil INSTANCE = null;
	private Gson gson;
	private JsonParser parser;
	private JsonObject objectJson;
	
	private JsonParserUtil() {
		this.gson = new Gson();
		this.parser = new JsonParser();
		this.objectJson = null;
	}
	
	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new JsonParserUtil();			
		}
	}
	
	public static JsonParserUtil getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	public Object getJsonParserObject(String objectJsonString, Object classSrc) {
		setObjectJson((JsonObject) getParser().parse(objectJsonString));
		return getGson().fromJson(getObjectJson(), classSrc.getClass());
	}
	
	public Gson getGson() {
		return gson;
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}

	public JsonParser getParser() {
		return parser;
	}

	public void setParser(JsonParser parser) {
		this.parser = parser;
	}

	public JsonObject getObjectJson() {
		return objectJson;
	}

	public void setObjectJson(JsonObject objectJson) {
		this.objectJson = objectJson;
	}
	
}
