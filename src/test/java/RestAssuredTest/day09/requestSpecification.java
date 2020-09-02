package RestAssuredTest.day09;

import APItest.Spartan;
import RestAssuredTest.day05.SpartanPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class requestSpecification {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.160.106.84";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
    }

    @DisplayName("User should not be able to delete data")
    @Test
    public void testUserCanNotDeleteData(){

        // building reusable request specification
        // using RequestSpecBuilder class

        RequestSpecification requestSpec = given()
                .auth().basic("user", "user")
                .accept(ContentType.JSON) // we are getting 403 with json body so accept header is json
                .log().all();

        given()
                .spec(requestSpec).
                when()
                .delete("/spartans/{id}" , 10).
                then()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .header("Date", is( notNullValue() ) ) // checking Date header is not null
                .log().all();

    }

    @DisplayName("User should not be able to update data")
    @Test
    public void testUserCanNotUpdateData(){

        SpartanPojo spartanObj = new SpartanPojo("some name", "Male", 8888888888L) ;

        given()
                .auth().basic("user","user")
                .accept(ContentType.JSON)
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartanObj).
                when()
                .put("/spartans/{id}", 10).
                then()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .header("Date", is( notNullValue() ) ) // checking Date header is not null
                .log().all();


    }

    @DisplayName("User should not be able to post data")
    @Test
    public void testUserCanNotPostData(){

        Spartan spartanObj = new Spartan(10,"some name", "Male", 8888888888L) ;

        given()
                .auth().basic("user","user")
                .accept(ContentType.JSON)
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartanObj).
                when()
                .post("/spartans").
                then()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .header("Date", is( notNullValue() ) ) // checking Date header is not null
                .log().all();

    }
}
