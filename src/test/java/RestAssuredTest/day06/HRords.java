package RestAssuredTest.day06;

import POJO.Locations;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.stringContainsInOrder;

public class HRords {
    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://54.174.216.245" ;
        RestAssured.port = 1000 ;
        RestAssured.basePath = "ords/hr";

    }

    @DisplayName("Testing the /location/{location_id} endpoint")
    @Test
    public void testLocation(){

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("location_id",1700)
                .log().all().
                        when()
                .get("/locations/{location_id}")
                .prettyPeek();

        Locations l1=new Locations();
        System.out.println("l1 "+l1);

    }
    @Test
    public void testLocations(){
        //save all street address to List<String>

        //save all Locations into List<Locations>

       Response response=get("/locations");//prettyPeek();
        //save all street address to List<String>
        List<String> address=response.jsonPath().getList("items.street_address");

            System.out.println("addressList = "+address);

    List<Locations> list=response.jsonPath().getList("items",Locations.class);

     /*   for(Locations eachLocation:list){
            System.out.println("eachLocation = "+ eachLocation);
        }*/
    //    list.forEach(eachLocation-> System.out.println("each location= "+eachLocation));

       assertThat(list,hasSize(23));

       // we want to get list of pojo but we only want to get
        //those data with country_id as US

    List<Locations> usLocation=response.jsonPath().getList("items.findAll { it.country_id=='US'}",Locations.class);
      //  System.out.println("Locations are : "+usLocation);
        usLocation.forEach(eachLocation-> System.out.println(eachLocation));




    }






}
