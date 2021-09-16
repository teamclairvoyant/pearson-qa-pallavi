package AutomationDemo.RestAssured.Project1;

import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import restassured.POJO.JsonPlaceholder;

public class Technique03_Gson {
	
	String URI = null;
	Response response;
	
	public Response getResponse(String URI)
	{
		Response response = 
				given().
					contentType(ContentType.JSON).
					accept(ContentType.JSON).
				when().
					get(URI).
				then().extract().response();
		
		return response;
	}
	
	@Test
	public void test01ParsingIndividualString() {
		URI = "https://jsonplaceholder.typicode.com/users/1";
		
		response = getResponse(URI);
		Gson gson = new Gson();
		
		JsonPlaceholder jsonPlaceholder = gson.fromJson(response.asString(), JsonPlaceholder.class);
		
		String email = jsonPlaceholder.getEmail();
		
		System.out.println("Email: " + email);
	}
	
	@Test
	public void test02ParsingJsonListSize()
	{
		try {
			URI = "https://jsonplaceholder.typicode.com/users";
			
			int users = 10;

			response = getResponse(URI);
			
			System.out.println(response.getBody().asString());
			
			Gson gson = new Gson();
			
			JsonPlaceholder jsonPlaceholderArray[] = gson.fromJson(response.asString(), JsonPlaceholder[].class);
			
			Assert.assertEquals(users, jsonPlaceholderArray.length);
			
			System.out.println("Test passed");
		} catch(AssertionError e)
		{
			System.out.println("Test Failed");
			Assert.fail();
		}	
	}

}
