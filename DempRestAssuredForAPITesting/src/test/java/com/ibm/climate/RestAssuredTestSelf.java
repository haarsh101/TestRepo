package com.ibm.climate;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Method;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;

public class RestAssuredTestSelf {
  @Test
  public void GetData() {
	  
	  RestAssured.baseURI = "http://localhost:3000/stocks";
	  RequestSpecification httpReq = RestAssured.given();
	  Response res = httpReq.request(Method.GET);
	  String r = res.body().asPrettyString();
	  System.out.println(r);
	  	  
  }
  
  
  @Test(dependsOnMethods = {"GetData"})
  public void PostData() {
	  
	  Map<String, Object> newData = new HashMap<>();
	  newData.put("id", "17");
	  newData.put("name", "IRFC");
	  newData.put("price", 23);
	  
	  RestAssured.given().baseUri("http://localhost:3000/stocks").contentType(ContentType.JSON).body(newData).post("").then().statusCode(201);
	  	  
  }
  
  @Test(dependsOnMethods = {"PostData"})
  public void PutData() {
	  
	  Map<String, Object> PutData = new HashMap<String, Object>();
	  PutData.put("id", "10");
	  PutData.put("name", "MUFG");
	  PutData.put("price", "111");
	  
	  RestAssured.given().baseUri("http://localhost:3000/stocks").contentType(ContentType.JSON).body(PutData).put("/111");
	  
  }
  
  @Test(dependsOnMethods = {"PutData"})
  public void smallUpdate() {
	  
	  Map<String, Object> minorUpdate = new HashMap<String, Object>();
      //minorUpdate.put("id", "2");
	  minorUpdate.put("name", "MUFG");
      minorUpdate.put("price", "100");
	  
	  RestAssured.given().baseUri("http://localhost:3000/stocks").contentType(ContentType.JSON).body(minorUpdate).patch("/2");
	  	  
  }
  
  @Test(dependsOnMethods = {"smallUpdate"})
  public void DeleteData() {
	  
	  RestAssured.baseURI = "http://localhost:3000/stocks";
	  RequestSpecification hr = RestAssured.given();
	  Response response = hr.delete("/555");
	  Response response1 = hr.delete("/15");
	  Response response2 = hr.delete("/17");
	  
  }

}