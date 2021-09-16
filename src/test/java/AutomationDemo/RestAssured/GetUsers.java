package AutomationDemo.RestAssured;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

public class GetUsers {

	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://reqres.in";
		
		given().
			    queryParam("page", "2")
	    .when().log().all().
	    		get("/api/users")
	    .then().
	    		assertThat().statusCode(200)
	    		.body("page", equalTo(2));
	}

}
