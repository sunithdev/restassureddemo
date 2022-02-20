package restassureddemo;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APITestsDemo {
	
  @Test
  public void firstTestCase() {
	  
	  //GET request
	 
	 /* RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts";
	  RequestSpecification reqSpec  = RestAssured.given();
	  Response response  = reqSpec.request(Method.GET);
	  Assert.assertEquals(response.getStatusCode() ==  200, true);
	  System.out.println(response.andReturn().asString());
	  System.out.println("checkout");*/
	  
	  //POST request
	  
	  
	  
	  RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books"; 
	  RequestSpecification request = RestAssured.given();
	  JSONObject requestParams = new JSONObject(); 
	  requestParams.put("userId", "TQ123"); 
	  requestParams.put("isbn", "9781449325862"); 
	  request.header("Content-Type", "application/json"); // Add the Json to the body of the request 
	  request.body(requestParams.toJSONString()); // Post the request and check the response
	  Response response = request.post("/BookStoreV1BooksPost"); 
	  System.out.println("The status received: " + response.statusLine());
	  System.out.println(response.andReturn().asString());
  }
  
}
