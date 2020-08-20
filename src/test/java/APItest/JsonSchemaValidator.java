package APItest;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import java.util.HashMap;
import java.util.Map;
public class JsonSchemaValidator{
    @BeforeClass
    public void setUp() {

        baseURI = "http://54.152.243.99:8000";
    }
    @Test
    public void test1(){
      given().accept(ContentType.JSON)
      .pathParam("id",12)
      .when().get("/api/spartans/{id}")
      .then().assertThat().statusCode(200)
       .and().assertThat().body(matchesJsonSchemaInClasspath("SingleSpartan.json"));


    }
    }