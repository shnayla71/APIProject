package APItest;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class SpartanMatchParams {

    @BeforeClass
    public void setUp(){

        baseURI="http://54.152.243.99:8000";
    }
    @Test
    public void test1(){
      Response response= given().accept(ContentType.JSON).pathParam("id",10)
               .when().get("/api/spartans/{id}");
        Assert.assertEquals(response.statusCode(),200);

        Assert.assertEquals(response.contentType(),"application/json;charset=UTF-8");

        System.out.println("id : "+ response.body().path("id").toString());
            int id=response.path("id");
        System.out.println("name : "+ response.body().path("name").toString());
            String name=response.body().path("name");
        System.out.println("gender : "+ response.body().path("gender").toString());
            String gender=response.body().path("gender");
        System.out.println("phone : "+ response.body().path("phone").toString());
            long phone=response.path("phone");

        Assert.assertEquals(id,10);
        Assert.assertEquals(name,"Lorenza");
        Assert.assertEquals(gender,"Female");
        Assert.assertEquals(phone, 3312820936L);
    }
    @Test
    public void test2(){
        Response response=get("/api/spartans");

        int firstId=response.path("id[10]");
        System.out.println("first id: "+firstId);

        String firstName=response.path("name[20]");
        System.out.println("first name: "+firstName);
        //sondan bir onceki isim
       // String lastfirstName=response.path("name[-1]");
      //  System.out.println(" last first name: "+firstName);

        List<String> names=response.path("name");
        System.out.println(names);
        String lastfirstName=response.path("name[-1]");
        System.out.println(lastfirstName);

        List<Object> phoneNumbers=response.path("phone");
        for(Object phone:phoneNumbers){
            System.out.println(phone);
        }
    }
}
