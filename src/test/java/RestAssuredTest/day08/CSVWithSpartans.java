package RestAssuredTest.day08;

import Utility.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class CSVWithSpartans {
    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://54.152.243.99:8000";
        RestAssured.basePath = "/api/spartans";
    }

    //iteration 1 \ username:<firstcoldata> , password:secondcoldata
    @ParameterizedTest(name = "iteration {index} | name: {0} , gender: {1} , phone: {2}")
    @CsvFileSource(resources = "/allSpartans.csv", numLinesToSkip = 1)
    public void test1(String user, String pass) {
        //  System.out.println(user);
        //   System.out.println(pass);
        given().log().all()
                .contentType(ContentType.JSON)

                .when().get()
                .then().statusCode(200);





    }

    @AfterAll
    public static void cleanup(){
        RestAssured.reset();
    }
}
