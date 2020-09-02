package RestAssuredTest.day08;

import RestAssuredTest.day05.SpartanPojo;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpartanApi_E2E_happyPath {

    static int newID;
    @BeforeAll
    public static void init(){
        baseURI="http://54.152.243.99:8000";
    }

    @Order(2)
    @Test
    public void testAdd(){
 //create one data here using the pojo as body ,do your assertion
  // and grab the id so it can be used for all next 3 tests

        SpartanPojo spartan1=new SpartanPojo("Ayla","Female",1233214566L);

      Response response=  given().log().all()
                .contentType(ContentType.JSON)
                .body(spartan1).
                when().post("/api/spartans");
      String expected=response.prettyPrint();
        System.out.println(expected);
      String actual=" \"name\": \"Ayla\",\n" +
        "    \"gender\": \"Female\",\n" +
        "    \"phone\": 1233214566";
     // assertEquals(expected,actual);
    }
    @Order(1)
    @Test
    public void testRead(){//get
 //use the id that have been generated from previous request
   // asssert the response json according to expected data
   // you can make your post body from previous request at class level


    }
    @Order(2)
    @Test
    public void testUpdate(){//PUT
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
    @Order(4)
    @Test
    public void testDelete(){//DELETE

    }
}
