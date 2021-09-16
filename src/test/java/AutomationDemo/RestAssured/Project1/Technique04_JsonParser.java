package AutomationDemo.RestAssured.Project1;

import static io.restassured.RestAssured.given;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Technique04_JsonParser {
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
	public void test01ParsingJsonIndividualElementInteger()
	{
		URI = "https://jsonplaceholder.typicode.com/users/1";
		String path = "id";
		
		Response response = getResponse(URI);
		
		System.out.println(response.getBody().asString());
		
		JsonObject jsonObject = (JsonObject)JsonParser.parseString(response.asString());
		
		JsonPrimitive id = jsonObject.getAsJsonPrimitive(path);
		
		Assert.assertEquals(1, id.getAsInt());
		
		System.out.println("Test Passed");	
	}
	
	@Test
	public void test02ParsingJsonAllObjects()
	{
		try {
		URI = "https://jsonplaceholder.typicode.com/users";
		String path = "id";
		int users = 10;
		
		Response response = getResponse(URI);
		
		System.out.println(response.getBody().asString());
		
		JsonArray jsonArray = (JsonArray)JsonParser.parseString(response.asString());
		
		for (JsonElement jsonElement : jsonArray) {
			System.out.println("JsonElement: " + jsonElement);
		}
		
		Assert.assertEquals(jsonArray.size(), users);
		
		System.out.println("Test Passed");
		}
		catch(AssertionError e)
		{
			System.out.println("Test Failed");
			Assert.fail();
		}	
	}

}
