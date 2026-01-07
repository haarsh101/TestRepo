package com.ibm.climate;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CityClimateTest {		
  @Test
  public void GetCityClimateDetails() {	  
	  RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
	  
	  RequestSpecification httpReq = RestAssured.given();
	  
	  //Response resp = httpReq.request(Method.GET, "/Kolkata");
	  
	  Response resp = httpReq.request("/Kolkata");
	  
	  int statusCode = resp.getStatusCode();
	  
	  System.out.println("The status code is: " + statusCode);
	  
	  //assertEquals(statusCode, 200);
	  
	  System.out.println(resp.getStatusLine() + " is the status line from the server...");
	  
	  String response = resp.body().asString();
	  
	  System.out.println("The response rendered from the server:" + response);	  	  
	  
	  Headers headers = resp.headers();
	  
	  for(Header h : headers) {
		  System.out.println("Headser name: " + h.getName() + " and header value is: "+ h.getValue());
	  }
	  
  }
}