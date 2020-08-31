package OfficeHours;

import io.restassured.response.Response;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.lang.String;
import static io.restassured.RestAssured.*;

public class Rewiev {
    public static void main(String[] args) {
        String Url="http://api.zippopotam.us/us/90210";

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your country code: ");
        String  country=scanner.next();
        System.out.println("\nEnter your postal code");
        int postalcode=scanner.nextInt();

      //  if (postalcode.matches("\\d+")){
       //     throw new RuntimeException("Wrong code");
     //   }
            baseURI = "http://api.zippopotam.us";

           Response response= given().pathParam("country", country)
                    .pathParam("zip", postalcode)
                    .when().get("/{country}/{zip}").prettyPeek();

        List<Map<String,Object>> places=response.jsonPath().getList("places");
        for(Map<String,Object> place:places){
            place.forEach((key,value)->{
                System.out.println(key+" : "+value);
            });
        }


    }
}
