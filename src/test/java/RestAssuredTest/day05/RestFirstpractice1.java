package RestAssuredTest.day05;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RestFirstpractice1 {
    @Test
    public void test1(){
        Response response= RestAssured.get("http://54.152.243.99:8000/api/hello");
        Assertions.assertEquals(response.statusCode(),200);
    }
}
