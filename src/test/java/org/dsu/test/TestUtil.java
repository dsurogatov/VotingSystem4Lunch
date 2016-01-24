/**
 * 
 */
package org.dsu.test;

import static com.jayway.restassured.RestAssured.given;

import java.nio.charset.Charset;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.specification.RequestSpecification;

/**
 * @author nescafe Some constants and utils methods for testing controllers
 */
public final class TestUtil {

	private TestUtil() {
	}

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
	        Charset.forName("utf8"));

	public static byte[] convertObjectToJsonBytes(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		try {
			return mapper.writeValueAsBytes(object);
		} catch (JsonProcessingException e) {
			return new byte[0];
		}
	}
	
	public static RequestSpecification requestWithJsonBody(Object body) {
		return given().request().contentType("application/json").body(convertObjectToJsonBytes(body));
	}
}
