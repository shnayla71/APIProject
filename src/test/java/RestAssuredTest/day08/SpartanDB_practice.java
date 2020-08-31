package RestAssuredTest.day08;

import Utility.ConfigurationReader;
import Utility.DB_Utility;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;

public class SpartanDB_practice {
    /*
    the dev just implemented the search endpoint
    want it to be tested to make sure it works
   GET/ spartans/search?gender=Female&nameContains=a
  select * from spartans
   where LOWER(gender)='female' and LOWER(name) like '%a%';
   we will implement with our test
     */
  @BeforeAll
    public static void init(){
      baseURI="http://54.152.243.99";
      port=8000;
      basePath="/api";

    DB_Utility.createConnection(ConfigurationReader.getProperty("spartan1.database.url"),
            ConfigurationReader.getProperty("spartan1.database.username"),
            ConfigurationReader.getProperty("spartan1.database.password"));
  }
  @Test
  public void dbConnection(){
  //  DB_Utility.runQuery("Select * from spartans");
   // DB_Utility.displayAllData();
    // run this query
    String query="select * from spartans\n" +
            "where LOWER(gender)='female' and LOWER(name) like '%a%'\n";
     DB_Utility.runQuery(query);
     DB_Utility.displayAllData();

     //get row count
    int row=DB_Utility.getRowCount();
    System.out.println(row);
  }
  @Test
  public void testSearch(){
    Response response= given().
              queryParam("gender","Female")
              .queryParam("nameContains","a")
     .when().get("/spartans/search");//prettyPeek();

    //int resultCount=response.path("numberOfElements");
      int resultCount=response.jsonPath().getInt("numberOfElements");
      System.out.println(resultCount);

  }








  @AfterAll
    public static void destroy(){
      reset();
      DB_Utility.destroy();
  }

}
