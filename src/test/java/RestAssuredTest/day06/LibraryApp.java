package RestAssuredTest.day06;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static RestAssuredTest.LibraryStarter.loginAndGetToken;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class LibraryApp {
    private static String libraryToken;

    @BeforeAll
    public static void init(){
        RestAssured.baseURI="http://library1.cybertekschool.com";
        RestAssured.basePath="/rest/v1";
        libraryToken = loginAndGetToken("librarian69@library", "KNPXrm3S");
    }

    @Test
    public void test1DashboardToken(){

     given().log().all().
             header("x-library-token",libraryToken).
     when().get("/dashboard_stats").
     then().log().all()
             .statusCode(200)
             .body("book_count",is("985"))
             .body("borrowed_books",is("600"))
             .body("users",is("5042"));

    }
    // add a test for the /decode
    @Test
    public void decodeTest(){
        given().log().all().
              contentType(ContentType.URLENC).
               formParam("token",libraryToken)
        .when().post("/decode")
        . then().log().all()
                .statusCode(200);
    }

    @Test
    public void testUserId(){
        given().log().all()
              .header("x-library-token",libraryToken)
               .pathParam("id",2090)
        .when().get("/get_user_by_id/{id}")
        .then().log().all().statusCode(200)
        //verify full_name
        .body("id",is("2090"))
        .body("full_name",is("Test Librarian 69"))
        .body("user_group_id",is("2"))
        //check image is null
        .body("image",is(nullValue()));// how to use matcher for null value



    }


}
