package APItest;
import static io.restassured.RestAssured.*;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpartanTestPojo {

    @BeforeClass
    public void setUp(){
      baseURI="http://54.152.243.99:8000";
    }

    @Test
    public void test1(){
        Response response=given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when().get("/api/spartans/{id}");
     //   response.prettyPrint();
        Spartan spartan1=response.body().as(Spartan.class);
        System.out.println(spartan1.toString());
        assertEquals(spartan1.getId(),15);
        assertEquals(spartan1.getGender(),"Female");
        assertEquals(spartan1.getName("AysePojo"),"Meta");
    }

    @Test
    public void gsonExample(){
        Gson gson=new Gson();

        String myJsonbody="{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n"+"}" ;

        Spartan spartanMeta=gson.fromJson(myJsonbody,Spartan.class);
        System.out.println(spartanMeta.toString());

        Spartan spartan2=new Spartan(20,"Elif","Female",8765342341L);
        String jsonObject=gson.toJson(spartan2);
        System.out.println(jsonObject);

    }
}
