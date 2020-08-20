package APItest;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
public class SpartanDeleteRequest {
    @BeforeClass
    public void setUp() {

        baseURI = "http://54.152.243.99:8000";
    }

    @Test
    public void test1() {
       given().pathParam("id",153)
          .when().delete("/api/spartans/{id}")
          .then().assertThat().statusCode(204);

    }
}
