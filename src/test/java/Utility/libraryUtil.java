package Utility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class libraryUtil {
    // we want to keep the method getToken
    public static String loginAndGetToken(String username, String password){
        Response jsonResponse  = given().
                contentType(ContentType.URLENC).
                formParam("email", username ).
                formParam("password", password).
                when().
                post("/login");
        JsonPath jsonPath = jsonResponse.jsonPath();
        String token = jsonPath.getString("token");
        return  token;
    }

    public static String setUprestAssuredAndDB(String env){
        RestAssured.baseURI = ConfigurationReader.getProperty(env + ".base_url");
        RestAssured.basePath = "/rest/v1";
        //added a utility class that contains below method
        DB_Utility.createConnection(env);

        return  libraryUtil.loginAndGetToken(ConfigurationReader.getProperty(env+".library_username")
                , ConfigurationReader.getProperty(env+".library_password"));
    }
}
