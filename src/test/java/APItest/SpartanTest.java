package APItest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SpartanTest {
   String baseUrl="http://54.152.243.99:8000";
  @Test
  public void firstTest(){
    Response response= RestAssured.get(baseUrl+"/api/spartans");
      System.out.println(response.statusCode());
  }

  @Test
  public void viewSpartans(){
    Response response= RestAssured.get(baseUrl+"/api/spartans");
    Assert.assertTrue(response.body().asString().contains("Allen"));

  }

  @Test
  public void ContentType(){
    Response response= RestAssured.given().accept(ContentType.JSON).when().get(baseUrl+"/api/spartans");

    Assert.assertEquals(response.contentType(),"application/json;charset=UTF-8");
  }
}
