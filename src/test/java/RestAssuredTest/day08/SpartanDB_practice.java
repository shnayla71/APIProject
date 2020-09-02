package RestAssuredTest.day08;

import RestAssuredTest.day05.LibraryStarter;
import Utility.ConfigurationReader;
import Utility.DB_Utility;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.baseURI;
import static org.testng.Assert.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
  @Order(2)
  @Test
  public void IdValidate(){
    Response response = given().
            queryParam("gender", "Female")
            .queryParam("nameContains", "a")
            .when().get("/spartans/search");//prettyPeek();

   List<Integer> responseIdList=response.jsonPath().getList("content.id");

   // we want to store the id list as List<String> rather than Integer

    String query ="select * from spartans\n" +
            "where LOWER(gender)='female' and LOWER(name) like '%a%'\n";
    DB_Utility.runQuery(query);
    List<String> idListDB=DB_Utility.getColumnDataAsList(1);
    //int expected=DB_Utility.getRowCount();
   assertEquals(responseIdList.size(),idListDB.size());


  }
  @Order(3)
  @Test
  public void IdValidate2(){
    Response response = given().
            queryParam("gender", "Female")
            .queryParam("nameContains", "a")
            .when().get("/spartans/search");//prettyPeek();
    List<String> responseIdList=response.jsonPath().getList("content.id",String.class);
    String query ="select * from spartans\n" +
            "where LOWER(gender)='female' and LOWER(name) like '%a%'\n";
    DB_Utility.runQuery(query);
    List<String> idListDB=DB_Utility.getColumnDataAsList(1);
    assertEquals(responseIdList,idListDB);
  }

  @Order(1)
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
   @Order(4)
  @Test
  public void testSearch() {
    Response response = given().
            queryParam("gender", "Female")
            .queryParam("nameContains", "a")
            .when().get("/spartans/search");//prettyPeek();

    //int resultCount=response.path("numberOfElements");
    int resultCount = response.jsonPath().getInt("numberOfElements");
    System.out.println(resultCount);
    // try at home parametiraize what you search for gender name
    // query param and db query
    String query ="select * from spartans\n" +
            "where LOWER(gender)='female' and LOWER(name) like '%a%'\n";
    DB_Utility.runQuery(query);
    int expected=DB_Utility.getRowCount();

    assertEquals(expected,resultCount);

  }





  @AfterAll
    public static void destroy(){
      reset();
      DB_Utility.destroy();
  }

}
