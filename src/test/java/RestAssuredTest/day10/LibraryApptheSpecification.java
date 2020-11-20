package RestAssuredTest.day10;

import Utility.ConfigurationReader;
import  static io.restassured.RestAssured.*;

import Utility.libraryUtil;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LibraryApptheSpecification {
   static RequestSpecification reqSpec;
    static ResponseSpecification resSpec;
    @BeforeAll
    public static void init(){
        baseURI= ConfigurationReader.getProperty("library1.base_url");
        basePath="/rest/v1";

        String theToken= libraryUtil.loginAndGetToken("librarian69@library","KNPXrm3S");

      reqSpec=given().accept(ContentType.JSON)//all request I ams ending json request
                .log().all().header("x-library-token",theToken);

      resSpec=expect().statusCode(200)
                      .contentType(ContentType.JSON)
                      .logDetail(LogDetail.ALL);

    }

    @DisplayName("testing get_book_categories")
    @Test
    public void library1(){
        given()
                .spec(reqSpec).

    when().get("get_book_categories").
        then()
                .spec(resSpec) ;
    }

    @DisplayName("Testing GET /get_all_users Endpoint with spec")
    @Test
    public void testGetAllUsers(){

        given()
                .spec(reqSpec).
                when()
                .get(" /get_all_users").then()
                .spec(resSpec)
        ;

    }

    @DisplayName("Testing GET /dashboard_stats Endpoint with spec")
    @Test
    public void testGet_Dashboard_stats(){

        given()
                .spec(reqSpec).
                when()
                .get(" /dashboard_stats").
        then()
                .spec(resSpec)
        ;

    }
}
