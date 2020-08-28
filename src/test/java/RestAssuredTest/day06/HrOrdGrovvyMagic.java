package RestAssuredTest.day06;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class HrOrdGrovvyMagic {
    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://54.174.216.245" ;
        RestAssured.port = 1000 ;
        RestAssured.basePath = "ords/hr";

    }
    @Test
    public void testEmployee(){
        Response response=get("/employees");//prettyPeek();

      /*  List<String> employees=response.jsonPath().getList("items.employee_id");
        System.out.println("Employees : "+employees);*/

        JsonPath jp=response.jsonPath();
        System.out.println("All IDs "+ jp.getList("items.employee_id"));
        System.out.println("first id "+ jp.getInt("items[0].employee_id"));
        System.out.println("last id "+ jp.getInt("items[-1].employee_id"));

        System.out.println("first until the fifth "+ jp.getList("items[0..4].employee_id"));

        System.out.println("last name from 10-15 "+ jp.getList("items[10..15].last_name"));

        //get the employee first name with employee id of 105
        // find method will return single value that fall into the criteria
       // find{it.employee_id=105}
        String result=jp.getString("items.find{it.employee_id==105}");
        System.out.println(result);
        System.out.println(jp.getString("items.find{it.employee_id==105}.last_name"));


        System.out.println(jp.getString("items.find{it.email=='LDEHAAN'}.salary"));

        //findAll will get all the result that match the criteria and return it as a list

      List<String> richPeople= jp.getList("items.findAll {it.salary > 15000}.first_name");
        System.out.println("rich people "+richPeople);

      List<String> phoneNumbers=jp.getList("items.findAll {it.department_id==90}.phone_number");
        System.out.println(phoneNumbers);
        //find max salary
      int maxSalary= jp.getInt("items.max {it.salary}.salary ");
        System.out.println("maxSalary: "+ maxSalary);

        //find out first name of maxsalary
        String name=jp.getString("items.max{it.salary}.first_name");
        System.out.println("Max salary guy: "+name);

        

    }




}
