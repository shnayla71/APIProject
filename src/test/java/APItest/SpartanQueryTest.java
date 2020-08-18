package APItest;

import  static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import  static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SpartanQueryTest {

    @BeforeMethod
    public void setUpClass(){
      baseURI="http://54.152.243.99:8000";
    }
    @Test
    public void queryParam1(){
      Response response= given().accept(ContentType.JSON).and().
               queryParam("gender","Female").
              and().queryParam("nameContains","N").
               when().get("/api/spartans/search");
        assertEquals(response.statusCode(),200);
        System.out.println("you found!");
        assertTrue(response.body().asString().contains("Female"));
        System.out.println("there is a female");
        assertTrue(response.body().asString().contains("Nona"));
        response.prettyPrint();
    }
}
