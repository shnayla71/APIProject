package APItest;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class SpartanPOSTRequest {
    @BeforeClass
    public void setUp(){

        baseURI="http://54.152.243.99:8000";
    }

    @Test
    public void PostTest(){
       Response response= given().accept(ContentType.JSON)
          .and().contentType(ContentType.JSON)
          .body("{\n" +
                  "    \"name\":\"Ayse\",\n" +
                  "    \"gender\":\"Female\",\n" +
                  "    \"phone\":1239876542\n" +
                  "}").when().post("/api/spartans");
      // response.prettyPrint();
         assertEquals(response.statusCode(),201);
         assertEquals(response.contentType(),"application/json");
         assertEquals(response.path("success"),"A Spartan is Born!");


        JsonPath json=response.jsonPath();
        assertEquals(json.getString("data.name"),"Ayse");
        assertEquals(json.getString("data.gender"),"Female");
       // assertEquals(json.getString("data.phone"),1239876542);

        System.out.println("New object created successful!");
    }

    @Test
    public void PostMap(){
        Map<String,Object> postmap=new HashMap<>();
        postmap.put("name","AyseMap");
        postmap.put("gender","Female");
        postmap.put("phone",3452617891L);

        Response response= given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(postmap).when().post("/api/spartans");
        assertEquals(response.statusCode(),201);
        response.prettyPrint();

    }
    @Test
    public void PostWithPojo(){
        Spartan spartan=new Spartan();
        spartan.setName("AysePojo");
        spartan.setGender("Female");
        spartan.setPhone(2341568653L);
        Response response= given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(spartan).when().post("/api/spartans");
        assertEquals(response.statusCode(),201);
        response.prettyPrint();

    //  GET REQUEST
        Response response2= given().accept(ContentType.JSON)
                .pathParam("id",160)
                .and() .when().get("/api/spartans/{id}");
        Spartan spartanResponse=response2.body().as(Spartan.class);
        System.out.println(spartanResponse.toString());

    }
}
