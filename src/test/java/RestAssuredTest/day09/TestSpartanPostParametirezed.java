package RestAssuredTest.day09;

import APItest.Spartan;
import RestAssuredTest.day05.SpartanPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class TestSpartanPostParametirezed {

        @BeforeAll
        public static void init(){
            RestAssured.baseURI = "http://54.160.106.84";
            RestAssured.port = 8000;
            RestAssured.basePath = "/api";
        }


    @ParameterizedTest
    @CsvFileSource(resources = "/allSpartans.csv",numLinesToSkip = 1)
    public void test(String name,String gender,long phone){
        System.out.println(name);
        System.out.println(gender);

       SpartanPojo spBody=new SpartanPojo(name,gender,phone);

        given().contentType(ContentType.JSON)
                .body(spBody)
        .when().post("/spartans")
        .then().statusCode(201)
              .body("success",is("A Spartan is Born!"));
    }
}
