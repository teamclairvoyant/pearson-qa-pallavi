package AutomationDemo.RestAssured.Project1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import restassured.POJO.JsonPlaceholder;

import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class Technique01_JsonPath {
	
	String URI = null;
	String path = null;
	
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
	public void test01ParsingJsonIndividualElementString()
	{
		URI = "https://jsonplaceholder.typicode.com/users/1";
		path="website";
		
		Response response = getResponse(URI);
		
		System.out.println(response.getBody().asString());
		
		JsonPath jsonPath = response.body().jsonPath();
		
		String website = jsonPath.getString(path);
	}

	@Test
	public void test02ParsingJsonIndividualElementId()
	{
		URI = "https://jsonplaceholder.typicode.com/users/1";
		path = "id";
		
		try {
		Response response = getResponse(URI);
		
		System.out.println(response.getBody().asString());
		
		JsonPath jsonPath = response.body().jsonPath();
		
		int website = jsonPath.getInt(path);
		System.out.println("Test Passed");
		}
		catch(AssertionError e)
		{
			System.out.println("Test Failed");
			Assert.fail();
		}		
	}
	
	
	@Test
	public void test03ParsingJsonIndividualElementList()
	{
		URI = "https://jsonplaceholder.typicode.com/users";
		path = "$";
		
		int expected = 10;
		
		try {
		
			Response response = getResponse(URI);
		
			System.out.println(response.getBody().asString());
		
			JsonPath jsonPath = response.body().jsonPath();
		
			List<Object> allUsers = jsonPath.getList(path);
		
			Assert.assertEquals(expected, allUsers.size());
			System.out.println("Test Passed");
		}
		catch(AssertionError e)
		{
			System.out.println("Test Failed");
			Assert.fail();
		}
	}
	
	@Test
	public void test04ParsingJsonIndividualElementListOfAllObjects()
	{
		URI = "https://jsonplaceholder.typicode.com/users";
		path = "$";
		
		int expected = 10;
		
		try {
		
			Response response = getResponse(URI);
		
			System.out.println(response.getBody().asString());
		
			JsonPath jsonPath = response.body().jsonPath();
		
			List<Object> allUsers = jsonPath.getJsonObject(path);
			
			for (Object user : allUsers) {
				System.out.println(user + "\n");
			}
		
			Assert.assertEquals(expected, allUsers.size());
			System.out.println("Test Passed");
		}
		catch(AssertionError e)
		{
			System.out.println("Test Failed");
			Assert.fail();
		}
	}
	
	@Test
	public void test05ParsingJsonIndividualElementMapCompanyElements()
	{
		URI = "https://jsonplaceholder.typicode.com/users/1";
		path = "company";
		
		int expectedSize = 3;
		
		try {
		
			Response response = getResponse(URI);
		
			System.out.println(response.getBody().asString());
		
			JsonPath jsonPath = response.body().jsonPath();
		
			Map<String, String> companymap = jsonPath.getMap(path);
			
		
			Assert.assertEquals(expectedSize, companymap.size());
			System.out.println("Test Passed");
		}
		catch(AssertionError e)
		{
			System.out.println("Test Failed");
			Assert.fail();
		}
	}
	
	//Verify catchPhrase is not empty
	@Test
	public void test06ParsingJsonIndividualElementMapVerifyCatchPhraseIsNotEmpty()
	{
		URI = "https://jsonplaceholder.typicode.com/users";
		path = "company";
		String key = "catchPhrase";
		
		int expectedSize = 3;
		
		try {
		
			Response response = getResponse(URI);
		
			System.out.println(response.getBody().asString());
		
			JsonPath jsonPath = response.body().jsonPath();
		
			List<Map<String, String>> companymap = jsonPath.getList(path);
			
			for (Map<String, String> map : companymap) {
				Assert.assertTrue(!map.get(key).isEmpty());
			}
			
			System.out.println("Test Passed");	
		}
		catch(AssertionError e)
		{
			System.out.println("Test Failed");
			Assert.fail();
		}
	}
	
	
	
}
