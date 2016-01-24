package org.dsu.test;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.dsu.builder.PageJsonBuilder;
import org.dsu.json.PageJson;
import org.junit.Test;

public class UserGetMethodsTest {

	@Test
	public void get_allUsers_ShouldReturnOk() {
		// create user
		
		// get users, check if one of them
		PageJson page = new PageJsonBuilder().start(0).size(10).build();
		TestUtil.requestWithJsonBody(page).get("/VotingSystem4Lunch/api/v1/user");
	}
}
