package RestAssuredTest;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MovieTesting {

    @BeforeAll
    public static void setUp(){
        baseURI="http://www.omdbapi.com";
    }
    @Test
    public void test1(){
        given()
        .log().all()
                .queryParam("apikey","26aa5b74")
                .queryParam("t","Boss Baby")
                .queryParam("plot","full").
                        when()
                .get().  // what if my URL is already complete , DO NOTHING
                then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                // checking title contains Boss Baby
                // if you are searching for exact match use is equalTo
                .body("Title" , containsString("Boss Baby") )
                .body("Ratings[0].Value" , is("6.3/10") )
                .body("Ratings[-1].Value" , is("50/100"));


    }
}
