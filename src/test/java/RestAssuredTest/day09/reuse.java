package RestAssuredTest.day09;

import RestAssuredTest.day05.SpartanPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class reuse{

    public static class SpartanRoleBaseAccessNegativeTest {
        @BeforeAll
        public static void init(){
            RestAssured.baseURI = "http://54.160.106.84";
            RestAssured.port = 8000;
            RestAssured.basePath = "/api";
        }
        @DisplayName("User should not be able to delete data")
        @Test
        public void testUserCanNotDeleteData(){
                     given().auth().basic("user","user")
                                     .accept(ContentType.JSON)
                                     .log().all()
                    .when().delete("/spartans/{id}",47)
                    .then().statusCode(403).log().all();

        }
        @DisplayName("User should not be able to update data")
        @Test
        public void testUserCanNotUpdateData(){
            SpartanPojo sp=new SpartanPojo("some name","Male",3333333333L);
            given().
                    auth().basic("user","user")
                    .accept(ContentType.JSON)
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(sp)
            .when().post("/spartans")
            .then().statusCode(403);

        }
        @DisplayName("User should not be able to post data")
        @Test
        public void testUserCanNotPostData(){


        }

    }
}
