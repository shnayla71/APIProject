package RestAssuredTest.day05;


import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class POJOExample {
    @BeforeAll
    public  static void setUp(){
        baseURI="http://54.152.243.99:8000";
    }
    @Test
    public void test1(){
        SpartanPojo spartan1=new SpartanPojo("Aygun","Female",1233214566L);

        given().log().all()
                .contentType(ContentType.JSON)
                .body(spartan1).
        when().post("/api/spartans").
        then().log().all()
               .statusCode(is(201));
    }
    @Test
    public void test2(){
      String faker=new Faker().name().firstName();

        Map<String,Object> updateBody=new LinkedHashMap<>();
        updateBody.put("name",faker);
        updateBody.put("gender","Male");
        updateBody.put("phone",9876543216L);

        given().log().all()
                .contentType(ContentType.JSON)
                .body(updateBody).
                when().put("/api/spartans/{id}", 55).
                then().log().all()
                .statusCode(is(204));

        when().get("/api/spartans/{id}",55).
        then().statusCode(200).
               body("name",is(faker));
    }
    @Test
    public void thePathPartical(){
        String randomName=new Faker().name().firstName();

      //  String patchbody=" \"name\": \" "+ randomName+"\" ";
        Map<String,Object> patchbody=new HashMap<>();
        patchbody.put("name",randomName);

        given().log().all()
                .contentType(ContentType.JSON).body(patchbody).
         when().patch("/api/spartans/{id}",55).
         then().log().all().
                statusCode(204);
    }
}
