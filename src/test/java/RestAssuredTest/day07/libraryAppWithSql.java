package RestAssuredTest.day07;

import Utility.DB_Utility;
import Utility.libraryUtil;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class libraryAppWithSql {
private static String libraryToken;
    @BeforeAll
    public static void init(){

     libraryToken=   libraryUtil.setUprestAssuredAndDB("library1");

    }
    @Test
    public void test1(){
      System.out.println("library token= "+libraryToken);

        DB_Utility.runQuery("SELECT count(*) from books");
        // it return the book count as single row and column
      String bookCount=  DB_Utility.getColumnDataAtRow(1,1);
        System.out.println(bookCount);

      DB_Utility.runQuery("SELECT count(*) from users");
        String userCount=  DB_Utility.getColumnDataAtRow(1,1);
        System.out.println(userCount);

        DB_Utility.runQuery("SELECT count(*) from book_borrow where is_returned=false");
        String borrowCount=  DB_Utility.getColumnDataAtRow(1,1);
        System.out.println(borrowCount);


        given().log().all()
                .header("x-library-token",libraryToken)
        .when().get("/dashboard_stats")
                //.prettyPeek()
        .then()
               .body("book_count",is(bookCount))
                .body("borrowed_books",is(borrowCount))
                .body("users",is(userCount));
    }


    @AfterAll
    public static void destroy(){
        DB_Utility.destroy();
        RestAssured.reset();//reseting all values for restassured itself

    }
}
