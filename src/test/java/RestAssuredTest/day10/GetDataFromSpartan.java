package RestAssuredTest.day10;

import Utility.DB_Utility;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class GetDataFromSpartan {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI="http://54.152.243.99";
        RestAssured.port=8000;
        RestAssured.basePath="/api";

        DB_Utility.createConnection("spartan1");
    }


    @Test
    public void testDataFromDB(){

        //I want to write a query
        String myQuery="SELECT * FROM SPARTANS ORDER BY SPARTAN_ID DESC";
        DB_Utility.runQuery(myQuery);
        Map<String,String> list= DB_Utility.getRowMap(1);
        System.out.println("list = " + list);
        
        
        int idFromDB=Integer.parseInt(list.get("SPARTAN_ID"));
        System.out.println("idFromDB = " + idFromDB);
        
        String nameFromDB=list.get("NAME");
        System.out.println("nameFromDB = " + nameFromDB);
        
        String genderFromDB=list.get("GENDER");
        System.out.println("genderFromDB = " + genderFromDB);
        
        long phoneFromDB=Long.parseLong(list.get("PHONE"));
        System.out.println("phoneFromDB = " + phoneFromDB);

        when().
                get("/spartans/{id}",idFromDB).
        then().log().all()
                .statusCode(200)
              .body("id", is(idFromDB))
              .body("name",is(nameFromDB))
              .body("gender",is(genderFromDB))
        // the test is failing if the range of int
              .body("phone.toLong()",is(phoneFromDB))
             ;
        

    }
}
