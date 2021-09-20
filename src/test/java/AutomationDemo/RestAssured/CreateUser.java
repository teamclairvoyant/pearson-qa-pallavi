package AutomationDemo.RestAssured;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreateUser {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://reqres.in";
		
		String userData = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		
		given().
			   body(userData)
	    .when().
	    		post("/api/users")
	    .then().log().all().
	    		assertThat().statusCode(201)
	    		.body("id", is(notNullValue()));

	}

}
