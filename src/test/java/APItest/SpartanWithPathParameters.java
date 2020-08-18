package APItest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SpartanWithPathParameters {

    @BeforeMethod
    public void setUpClass(){
        RestAssured.baseURI="http://54.152.243.99:8000";
    }

    @Test
    public void pathParameter(){
     Response response= RestAssured.given().
                accept(ContentType.JSON).and().pathParam("id",11).when().get("api/spartans/{id}");
        Assert.assertTrue(response.body().asString().contains("Nona"));

    }
}
