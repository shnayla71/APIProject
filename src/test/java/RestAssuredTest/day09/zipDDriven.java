package RestAssuredTest.day09;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;

public class zipDDriven {

    @ParameterizedTest
    @CsvFileSource(resources = "/state.csv",numLinesToSkip = 1)
    public void testStateCity(String expectedstate,String expectedcity,int numberOfZipcodes){

      /*  System.out.println("expectedstate "+expectedstate);
        System.out.println("expectedcity "+expectedcity);
        System.out.println("numberOfZip "+numberOfZipcodes);*/

        Response response=given().pathParam("expectedstate",expectedstate)
                .pathParam("expectedcity",expectedcity)
                .baseUri("http://api.zippopotam.us/us")
         .when().get("/{expectedstate}/{expectedcity}");
                // .prettyPeek();

        JsonPath jp=response.jsonPath();
        System.out.println("expectedstate= "+ jp.getString("'state abbreviation'"));
        System.out.println("expectedcity= "+jp.getString("'place name'"));

      assertThat(jp.getString("'state abbreviation'"),is(expectedstate));
        assertThat(jp.getString("'place name'"),is(expectedcity));

        List<String> ziplist=jp.getList("places.'post code'");
        System.out.println("zipList= "+ziplist);

       // assertThat(ziplist,hasSize(numberOfZipcodes));

        System.out.println("jp.getInt() "+jp.getInt("places.size()"));


    }

    @Test
    public void testSingle(){

        Response response=given().pathParam("expectedstate","VA")
                .pathParam("expectedcity","Fairfax")
                .baseUri("http://api.zippopotam.us/us")
                .when().get("/{expectedstate}/{expectedcity}");
        response.then().statusCode(200);
    }

}
