package AutomationDemo.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.RestAssured;

public class Patch_UpdateUser {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://reqres.in";
		
		String userData = "{\r\n"
				+ "    \"name\": \"morpheus\"\r\n"
				+ "}";
		
		given().header("content-type", "application/json").
			   body(userData)
	    .when().
	    		patch("/api/users/2")
	    .then().log().all().
	    		assertThat().statusCode(200)
	    		.body("updatedAt", is(notNullValue()));

	}

}
