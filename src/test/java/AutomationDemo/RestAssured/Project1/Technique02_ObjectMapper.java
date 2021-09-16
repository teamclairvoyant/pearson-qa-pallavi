package AutomationDemo.RestAssured.Project1;

import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import restassured.POJO.JsonPlaceholder;

public class Technique02_ObjectMapper {
	
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
	
	public JsonPlaceholder getJsonPlaceholder(Response response)
	{
		JsonPlaceholder jsonPlaceholder = response.as(JsonPlaceholder.class, ObjectMapperType.GSON);
		return jsonPlaceholder;
	}

	
	@Test
	public void test01ParsingJsonIndividualElementString()
	{
		URI = "https://jsonplaceholder.typicode.com/users/1";
	
		response = getResponse(URI);
		
		System.out.println(response.getBody().asString());
		
		JsonPlaceholder jsonPlaceholder = getJsonPlaceholder(response);
		
		String website = jsonPlaceholder.getWebsite();
		
		System.out.println(website);	
	}
	
	@Test
	public void test02ParsingJsonIndividualElementInteger()
	{
		URI = "https://jsonplaceholder.typicode.com/users/1";
	
		response = getResponse(URI);
		
		System.out.println(response.getBody().asString());
		
		JsonPlaceholder jsonPlaceholder = response.as(JsonPlaceholder.class, ObjectMapperType.GSON);
		
		int id = jsonPlaceholder.getId();
		
		System.out.println(id);	
	}
	
	@Test
	public void test03ParsingJsonListSize()
	{
		try {
			URI = "https://jsonplaceholder.typicode.com/users";
			
			int users = 10;

			response = getResponse(URI);
			
			System.out.println(response.getBody().asString());
			
			JsonPlaceholder jsonPlaceholderArray[] = response.as(JsonPlaceholder[].class, ObjectMapperType.GSON);
			
			//Assert.assertEquals(users, jsonPlaceholderArray.length);
			
			
			//Alternative: Convert array to list
			List<JsonPlaceholder> jsonPlaceholderList = Arrays.asList(jsonPlaceholderArray);
			Assert.assertEquals(users, jsonPlaceholderList.size());
			
			System.out.println("Test passed");
		} catch(AssertionError e)
		{
			System.out.println("Test Failed");
			Assert.fail();
		}	
	}
	
	@Test
	public void test04ParsingJsonVerifyCatchPhraseIsNotEmpty()
	{
		try {
			URI = "https://jsonplaceholder.typicode.com/users";
			
			String catchPhrase;

			response = getResponse(URI);
			
			System.out.println(response.getBody().asString());
			
			JsonPlaceholder jsonPlaceholderArray[] = response.as(JsonPlaceholder[].class, ObjectMapperType.GSON);
			
			for (JsonPlaceholder jsonPlaceholder : jsonPlaceholderArray) {
				catchPhrase = jsonPlaceholder.getCompany().getCatchPhrase();
				System.out.println("Catchphrase: " + catchPhrase);
				
				Assert.assertTrue(!catchPhrase.isEmpty());
			}
			
			System.out.println("Test passed");
		} catch(AssertionError e)
		{
			System.out.println("Test Failed");
			Assert.fail();
		}	
	}
}
