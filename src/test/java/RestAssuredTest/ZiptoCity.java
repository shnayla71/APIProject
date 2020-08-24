package RestAssuredTest;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class ZiptoCity {

    @BeforeAll
    public static void SetUp(){
     baseURI="http://api.zippopotam.us";
    }
    @Test
    public void test1(){
        given().log().all()
        .pathParam("zipcode",22030)
        .when().get("/us/{zipcode}")
        .then().statusCode( is(200))
        .contentType(ContentType.JSON)
                .body("'post code'", is("22030")    )
                .body("country",is("United States") )
                // get the state and check it's Virginia
                .body("places[0].state" , is("Virginia")   )
                // get the place name
                .body("places[0].'place name' " , is("Fairfax") )

        ;
    }

    @Test
    public void testCityToZip(){

        //api.zippopotam.us/us/:state/:city
        given()
                .pathParam("state","VA")
                .pathParam("city","Fairfax")
                .log().all().
                when()
                .get("/us/{state}/{city}").
//                .get("/{state}/{city}" , "VA","Fairfax" ). // second way we did with spartan
        then()
                .log().all()
                .statusCode( is(200) )
                .body("'country abbreviation'",is("US") )
                // test the latitude of first place is "38.8458"
                .body("places[0].latitude" ,is("38.8458") )
                // jsonPath hack for getting last item from jsonArray
                // -1 index indicate the last item , only works here not in postman
                .body("places[-1].latitude" , is("38.7602") )

        ;


    }


}
