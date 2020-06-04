package apitests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SimplePostTest 
{
	@Test
	public void RegistrationSuccessful()
	{ 
		 RestAssured.baseURI ="https://reqres.in";
		 RequestSpecification request = RestAssured.given();
		 
		 JSONObject requestParams = new JSONObject();
		 requestParams.put("name", "morpheus"); 
		 requestParams.put("job", "leader");
		 
		 request.header("Content-Type", "application/json");
		 request.body(requestParams.toJSONString());
		 
		 Response response = request.post("/api/users");
		 
		 // Validate the Response
		 
		 int statusCode = response.getStatusCode();
		 System.out.println("Response Status Code: " + statusCode);
		 Assert.assertEquals(statusCode, 201);
		 		 
		 String jsonString = response.asString();
		 System.out.println("Response JSON String is: " + jsonString); 
		 Assert.assertEquals(jsonString.contains("morpheus"), true);
	}
}
