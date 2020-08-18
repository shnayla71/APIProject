package APItest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class SpartanJsontoCollections {
    @BeforeClass
    public void setUp(){

        baseURI="http://54.152.243.99:8000";
    }
    @Test
    public void test1(){
        Response response= RestAssured.given().
                accept(ContentType.JSON).and().pathParam("id",11)
                .when().get("api/spartans/{id}");

       Map<String,Object> spartansMap= response.body().as(Map.class);

        System.out.println(spartansMap.get("id"));
        System.out.println(spartansMap.get("name"));

        Assert.assertEquals(spartansMap.get("name"),"Nona");
    }

    @Test
    public void test2(){
        Response response=given().accept(ContentType.JSON).when().get("/api/spartans");
        response.prettyPrint();

       List<Map<String,Object>> list=response.body().as(List.class);
        System.out.println(list.get(0));

       int count=1;
       for(Map<String,Object> map:list){
           System.out.println(count + " spartan "+map);
           count++;
       }


    }
}
