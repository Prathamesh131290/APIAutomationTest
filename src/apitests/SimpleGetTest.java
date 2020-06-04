package apitests;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
 
public class SimpleGetTest {
 
 @Test
 public void GetWeatherDetails() {

	 RestAssured.baseURI = "https://reqres.in";
	 RequestSpecification httpRequest = RestAssured.given();
	 Response response = httpRequest.get("/api/users/2");
	 
	 // Response Status: (Status code, status line)
	 
	 int statusCode = response.getStatusCode();
	 System.out.println("Response status code: " + statusCode);
	 Assert.assertEquals(statusCode, 200);
	 
	 String statusLine = response.getStatusLine(); 
	 System.out.println("Response status line is: " + statusLine);
	 Assert.assertEquals(statusLine, "HTTP/1.1 200 OK"); 
	 
	 // Response Header: (Content-Type, Server, Content-Encoding)
	 
	 String contentType = response.header("Content-Type");
	 System.out.println("Content-Type: " + contentType);
	 
	 String serverType =  response.header("Server");
	 System.out.println("Server: " + serverType);
	 
	 String acceptLanguage = response.header("Content-Encoding");
	 System.out.println("Content-Encoding: " + acceptLanguage);
	 
	 Headers allHeaders = response.headers();
	 for(Header header : allHeaders)
	 {
		 System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
	 }
	 
	 // Response Body:
	 
	 ResponseBody body = response.getBody(); 
	 String bodyAsString = body.asString(); 
	 System.out.println("Response Body is: " + bodyAsString);
	 Assert.assertEquals(bodyAsString.contains("Janet"), true);

	 // Verify JSON response:
	 
	 JsonPath jsonPathEvaluator = response.jsonPath();
	 String company = jsonPathEvaluator.get("ad.company");
	 System.out.println("company received from Response: " + company);
	 Assert.assertEquals(company, "StatusCode Weekly");
 }
 
}