package RestAssuredTest.day07;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class NewsApi {

    @Test
    public void test1(){
      String apiToken="87eb9bf287554b19af52dd56cfdc64a8";

       Response response= given().baseUri("http://newsapi.org")
                .basePath("/v2").
                header("Authorization","Bearer " + apiToken)
                .queryParam("country","us")
                .log().all().
        when().get("/top-headlines") ;

        JsonPath js=response.jsonPath();
         List<String> allAuthor=js.getList("articles.author");
         allAuthor.forEach(eachAuthor-> System.out.println(eachAuthor));

       List<String> allFiltredAuthor=js.getList("articles.findAll {it.source.id != null }.author");

       for (String author:allFiltredAuthor){
           System.out.println("author "+author);
       }

        System.out.println(allFiltredAuthor.size());
    }

}
