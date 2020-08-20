package APItest;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
public class SpartanPutRequest {
    @BeforeClass
    public void setUp() {

        baseURI = "http://54.152.243.99:8000";
    }

    @Test
    public void PutRequest() {

        Map<String, Object> putMap = new HashMap<>();
        putMap.put("name", "AyseUpdate");
        putMap.put("gender", "Female");
        putMap.put("phone", 3425167859L);

        given().contentType(ContentType.JSON)
                .and().pathParam("id", 159)
                .and().body(putMap).when()
                .put("/api/spartans/{id}")
                .then().assertThat().statusCode(204);
    }

    @Test
    public void PatchRequest() {

        Map<String, Object> patchMap = new HashMap<>();
        patchMap.put("name", "Aynur");

        given().contentType(ContentType.JSON)
                .and().pathParam("id", 159)
                .and().body(patchMap).when()
                .patch("/api/spartans/{id}")
                .then().assertThat().statusCode(204);
    }
}