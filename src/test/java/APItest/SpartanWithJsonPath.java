package APItest;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.json.Json;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpartanWithJsonPath {
    @BeforeClass
    public void setUp(){
       baseURI="http://54.152.243.99:8000";
    }
    //  "id": 12,
    //    "name": "Sol",
    //    "gender": "Male",
    //    "phone": 7006438852
    @Test
    public void test1(){
        Response response=given().accept(ContentType.JSON).pathParam("id",12)
                .when().get("/api/spartans/{id}");
        JsonPath jsonData=response.jsonPath();

        int id=jsonData.getInt("id");
        String name=jsonData.getString("name");
        String gender=jsonData.getString("gender");
        long phone=jsonData.getLong("phone");

        System.out.println("id: "+id+"\nname: "+name+"\ngender "+gender+"\nphone: "+phone);
    }
}
