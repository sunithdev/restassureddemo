package restassureddemo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APITestsDemo {
	
  @Test
  public void firstTestCase() {
	 
	  RestAssured.baseURI = "https://reqres.in/api/users";
	  RequestSpecification reqSpec  = RestAssured.given();
	  Response response  = reqSpec.request(Method.GET);
	  Assert.assertEquals(response.getStatusCode() ==  200, true);
	  System.out.println(response.andReturn().asString());
  }
  
}
