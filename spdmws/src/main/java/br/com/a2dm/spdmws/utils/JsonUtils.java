package br.com.a2dm.spdmws.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils {

	private JsonUtils() {
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T serializeInstance(T target) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			String someJsonString = mapper.writeValueAsString(target);
			return (T)mapper.readValue(someJsonString, target.getClass());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
