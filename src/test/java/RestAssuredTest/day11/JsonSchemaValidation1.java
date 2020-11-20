package RestAssuredTest.day11;

import Utility.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchemaValidation1 {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI= ConfigurationReader.getProperty( "spartan1.database_url");

        RestAssured.basePath="/api";
    }

    @Test
    public void test1(){
        given().log()
                .uri()
                .when().get("/spartans/{id}",55).prettyPeek()
               .then().assertThat().statusCode(200)
                .and().assertThat().body(matchesJsonSchemaInClasspath("SingleSpartan.json"));

    }
}
