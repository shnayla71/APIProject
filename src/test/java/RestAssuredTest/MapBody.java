package RestAssuredTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class MapBody {
    @BeforeClass
    public void setUp() {

        baseURI = "http://54.152.243.99:8000";
    }

    @Test
    public void PostMap() {
        Map<String, Object> postmap = new HashMap<>();
        postmap.put("name", "AyseMap");
        postmap.put("gender", "Female");
        postmap.put("phone", 3452617891L);

        given().log().all()
                .contentType(ContentType.JSON)
                .body(postmap)
        .when().post("/api/spartans").
        then().statusCode( is(201));


    }
}