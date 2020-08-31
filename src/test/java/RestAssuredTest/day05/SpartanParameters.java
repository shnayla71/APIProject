package RestAssuredTest.day05;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SpartanParameters {

    @BeforeAll
    public static void setUp(){
     baseURI="http://54.152.243.99:8000";
    }

    @Test
    public void Paramtr(){
        given().log().all().pathParam("id",100)
        .when().get("/api/spartans/{id}")
         .then().statusCode( is (200));
    }

    @Test
    public void test1(){
        given().log().all().pathParam("id",100)
                .when().get("/api/spartans/{id}")
                .then().log().all()
                .statusCode( is (200))
                /*
                "id": 100,
              "name": "Terence",
              "gender": "Male",
              "phone": 1311814806
                 */
              .body("id", is(100))
              .body("name", is("Terence"))
              .body("gender", is("Male"))
              .body("phone", is(1311814806));
    }
}
