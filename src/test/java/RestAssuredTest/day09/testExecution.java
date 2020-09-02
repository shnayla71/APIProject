package RestAssuredTest.day09;

import RestAssuredTest.day05.SpartanPojo;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

public class testExecution {
    static RequestSpecification validPostRequestSpec;
    static ResponseSpecification validPostResponseSpec;
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.160.106.84";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
        SpartanPojo sp=createRandomSpartanObject();
        validPostRequestSpec=  given().auth().basic("admin","admin")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(sp).log().all();

        ResponseSpecBuilder resSpecBuilder=new ResponseSpecBuilder();
          validPostResponseSpec=  resSpecBuilder.expectStatusCode(201)
                    .expectHeader("Date",notNullValue(String.class))
                    .log(LogDetail.ALL)
                    .expectBody("success",is("A Spartan is Born!"))
                     .expectBody("data.name" , is(  sp.getName()  )   )
                     .expectBody("data.gender" , is(  sp.getGender()  )   )
                      .expectBody("data.phone" , is(  sp.getPhone()  )   )
                      .build();


    }

    @DisplayName("Extracting the requestSpec and responseSpec practice")
    @Test
    public void test(){
       // SpartanPojo sp=createRandomSpartanObject();
        // make a post request and assert the status code header and body
        // eventually extract out the spec for reuse
      /*  RequestSpecification requestSpec = given()
                .auth().basic("user", "user")
                .accept(ContentType.JSON) // we are getting 403 with json body so accept header is json
                .log().all();

        // Extracting ResponseSpecification for all assertions so we can reuse
        // We will be using a class called ResponseSpecBuilder
        ResponseSpecBuilder resSpecBuilder = new ResponseSpecBuilder() ;
        // Getting the reusable ResponseSpecification object using the builder methods chaining
        ResponseSpecification responseSpec = resSpecBuilder
                .expectStatusCode(403)
                .expectContentType(ContentType.JSON)
                .expectHeader("Date", notNullValue(String.class)  )
                .log(LogDetail.ALL)
                .build();
        given()
                .spec(requestSpec).
                when()
                .post("/spartans/{id}" , 10).
                then()
                .spec(responseSpec);*/
       given().spec(validPostRequestSpec)

        .when().post("/spartans")
        .then().spec(validPostResponseSpec);
               /*log().all().
                statusCode(201).
                header("Date" , notNullValue() )
                .body("success", is("A Spartan is Born!") )
                .body("data.name" , is(  sp.getName()  )   )
                .body("data.gender" , is(  sp.getGender()  )   )
                .body("data.phone" , is(  sp.getPhone()  )   )
                .body("data.id", notNullValue() );*/



    }



    public static SpartanPojo createRandomSpartanObject() {
        Faker faker = new Faker();
        String name   = faker.name().firstName();
        String gender = faker.demographic().sex();
        // always getting long number outside range of int to avoid errors
        long phone    = faker.number().numberBetween(5000000000L,9999999999L);
        SpartanPojo randomSpartanObject = new SpartanPojo(name,gender,phone);
        System.out.println("Created Random Spartan Object : " + randomSpartanObject);

        return randomSpartanObject ;
    }


}

