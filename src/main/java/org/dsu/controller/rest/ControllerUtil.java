package org.dsu.controller.rest;

import java.io.IOException;

import org.dsu.common.ExceptionType;
import org.dsu.common.VotingSystemException;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class ControllerUtil {

	private ControllerUtil() {
	}

	public static <T> T deserialize(String json, Class<T> claz) {
		ObjectMapper mapper = new ObjectMapper();
		T ret = null;
		try {
			ret = mapper.readValue(json, claz);
		} catch (IOException e) {
			throw new VotingSystemException(ExceptionType.CONVERT_FROM_JSON_FAILED);
		}
		return ret;
	}
	
//	public static PageJson deserializePage(String json) {
//		PageJson page = deserialize(json, PageJson.class);
//		return page;
//	}

}
