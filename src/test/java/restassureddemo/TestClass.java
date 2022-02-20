package restassureddemo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class TestClass {

	RequestSpecification reqSpec = null;
	Response response = null;
	String baseURI = "";
	FileInputStream fis = null;
	Properties prop  = null;
	String  environment = "";

	@BeforeMethod
	public void beforeMethod() throws Exception {
		
		environment = System.getProperty("Env");
		System.out.println("Scripts will run in "+ environment +" environment");
		
		setupEnv();
		
		String baseDir  = System.getProperty("user.dir");
		String propPath = baseDir + "\\resources\\api.properties";
		
		fis  = new FileInputStream(propPath);
		prop  = new Properties();
		prop.load(fis);
		baseURI = prop.getProperty(environment);
		
		reqSpec = RestAssured.given();
	}

	private void setupEnv() {
		if(environment == null) {
			environment = "QA";
		}
	}

	@Test(description = "List all the users with GET")
	public void getReq() {
		RestAssured.baseURI = baseURI;
		String endPont = "/api/users";
		reqSpec = RestAssured.given();
		response = reqSpec.request(Method.GET, endPont); //https://reqres.in/api/users
		Assert.assertEquals(response.getStatusCode() == 200, true);
	}

	@Test(description = "Create a user with POST")
	public void postReq() {
		RestAssured.baseURI = baseURI;
		String endPont = "/api/users";
		String empFullName = "John " + RandomStringUtils.randomAlphabetic(5);
		String myData = "{\"name\":\"" + empFullName + "\",\"job\":\"leader\"}";
		reqSpec = RestAssured.given();
		response = reqSpec.body(myData).request(Method.POST, endPont);
		Assert.assertEquals(response.getStatusCode() == 201, true);
		System.out.println(response.andReturn().asString());
	}

	@AfterMethod
	public void afterMethod() {
	}

}
