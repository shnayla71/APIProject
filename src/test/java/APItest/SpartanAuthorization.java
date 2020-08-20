package APItest;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpartanAuthorization {
    String accessToken="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoiMTQ0IiwiZnVsbF9uYW1lIjoiVGVzdCBTdHVkZW50IDE0IiwiZW1haWwiOiJzdHVkZW50MTRAbGlicmFy...";
    @BeforeClass
    public void setUp(){
      baseURI="http://library2.cybertekschool.com/rest/v1";
    }
    @Test
    public void test1(){
       Response response= given().header("Authorization",accessToken)
           .when().get();

        Assert.assertEquals(response.statusCode(),200);
        response.prettyPrint();
    }

}
