package RestAssuredTest.day08;

import Utility.ConfigurationReader;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class CSVWithLibraryApp {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = ConfigurationReader.getProperty("library1.base_url");
        RestAssured.basePath = "/rest/v1";
    }

    //iteration 1 \ username:<firstcoldata> , password:secondcoldata
    @ParameterizedTest(name = "iteration {index} | username: {0} , password: {1}")
    @CsvFileSource(resources = "/credentials.csv", numLinesToSkip = 1)
    public void test1(String user, String pass) {
      //  System.out.println(user);
     //   System.out.println(pass);
        given().log().all()
                .contentType(ContentType.URLENC)
                .formParam("email",user)
                .formParam("password",pass)
        .when().post("/login")
        .then().statusCode(200)
               .body("token", notNullValue());




    }

    @AfterAll
    public static void cleanup(){
        RestAssured.reset();
    }
}

