package APItest;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpartanWithHamcrest {
    @BeforeClass
    public void setUp(){
        baseURI="http://54.152.243.99:8000";
    }
    // "id": 27,
    //    "name": "Jeanelle",
    //    "gender": "Female",
    //    "phone": 6662999903
    @Test
    public void test1(){
        //request
        given().accept(ContentType.JSON).pathParam("id",27).
        when().get("/api/spartans/{id}").
                //response
        then().statusCode(200).
        and().assertThat().contentType("application/json;charset=UTF-8");

    }
    @Test
    public void test2(){
        given().accept(ContentType.JSON).pathParam("id",27)
        .when().get("/api/spartans/{id}")
        .then().assertThat().statusCode(200)
        .and().contentType("application/json;charset=UTF-8")
        .and().body("id", Matchers.equalTo(27),"name",Matchers.equalTo("Jeanelle"),
                "gender",Matchers.equalTo("Female"),"phone",Matchers.equalTo(6662999903L));

    }
}
