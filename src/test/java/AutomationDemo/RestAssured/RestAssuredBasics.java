package AutomationDemo.RestAssured;

import io.restassured.RestAssured;


import static io.restassured.RestAssured.given;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import org.apache.http.HttpStatus;


public class RestAssuredBasics {
	
	String URI = null;
	String path = null;
	
	public Response getResponse()
	{
		Response response = 
				given().
					contentType(ContentType.JSON).
					accept(ContentType.JSON).
				when().
					get().
				then().extract().response();
		
		return response;
	}
	
	@Before
	public void setUp()
	{
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RestAssured.basePath = "/users/1";
	}
	
	@Test
	public void RestAssuredGetBody()
	{
		String body = getResponse().getBody().asString();
		
		//Get Body
		System.out.println("Response Body: " + body);
	}
	
	@Test
	public void RestAssuredGetHeaders()
	{
		String headerName = "Content-Type";
		Headers headers = getResponse().getHeaders();
		
		//Get Headers
		System.out.println("Response Headers: " + headers);
		
		//Get Content-Type from Header
		System.out.println("Response Headers: " + headers.getValue(headerName));	
	}
	
	@Test
	public void RestAssuredHamcrestAssertion()
	{
		try {
			String expectedWebsite = "hildegard.org";
			String bodyStringValue = getResponse().getBody().toString();
			
			assertThat(bodyStringValue, containsString(expectedWebsite));
			
		}
		catch(AssertionError e)
		{
			System.out.println("Test Fail");
		}
	}
	
	@Test
	public void TestStatusCodeJUnit()
	{
		try {
			
			int statusCode = getResponse().getStatusCode();
			
			Assert.assertEquals(200, statusCode);
			System.out.println("Test Passed");
		}
		catch(AssertionError e)
		{
			System.out.println("Test Fail");
		}
	}
	
	@Test
	public void TestStatusCodeHamcrest()
	{
		try {
			
			int statusCode = getResponse().getStatusCode();
			
			assertThat(statusCode, equalTo(HttpStatus.SC_OK));
			System.out.println("Test Passed");
		}
		catch(AssertionError e)
		{
			System.out.println("Test Fail");
		}
	}

}
