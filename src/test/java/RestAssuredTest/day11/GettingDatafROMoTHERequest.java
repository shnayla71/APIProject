package RestAssuredTest.day11;

import APItest.Spartan;
import Utility.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class GettingDatafROMoTHERequest {
    @BeforeAll
    public static void init() {
        RestAssured.baseURI= ConfigurationReader.getProperty( "spartan1.database_url");

        RestAssured.basePath="/api";
    }
    @Test
    public void testDynamicID(){

        Response response=get("/spartans");
        List<Spartan> spartanList=response.jsonPath().getList("",Spartan.class);
        System.out.println("spartanList = " + spartanList);

        int firstSpartanId=spartanList.get(0).getId();
        System.out.println("firstSpartanId = " + firstSpartanId);
        
        String firstSpartanName=spartanList.get(0).getName();
        System.out.println("firstSpartanName = " + firstSpartanName);

        given().pathParam("id",firstSpartanId)
       .when().get("/spartans/{id}").//prettyPeek();
        then().statusCode(200)
              .body("name",is(firstSpartanName));
    }
    
    @RepeatedTest(10)
    public void random(){
        Response response=get("/spartans");
        List<Spartan> spartanList=response.jsonPath().getList("",Spartan.class);

        Random random=new Random();
        int number= random.nextInt(spartanList.size());
        System.out.println("number = " + number);
        
      Spartan randomSpartanObject=spartanList.get(number);
        System.out.println("randomSpartanObject = " + randomSpartanObject);


        given().pathParam("id",randomSpartanObject.getId())
                .when().get("/spartans/{id}").//prettyPeek();
                then().log().body().
                statusCode(200)
                .body("name",is(randomSpartanObject.getName() ));
    
    
    }








}