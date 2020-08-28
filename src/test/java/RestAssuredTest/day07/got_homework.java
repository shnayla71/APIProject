package RestAssuredTest.day07;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class got_homework {
    @Test
    public void test1(){

        Response response= given().baseUri("http://api.got.show")
                .basePath("/api/book").
                 when().get("/characters");


        List<String> houseStarkList=response.jsonPath().getList("findAll{it.house=='House Stark'}.name");
        System.out.println("houseStarkList= "+houseStarkList);

        assertThat(houseStarkList,hasSize(76));

        //check the list item Eddard Stark
        assertThat(houseStarkList,hasItem("Eddard Stark"));

        //check three or more than names
        assertThat(houseStarkList,hasItems("Robb Stark","Lyanna Stark","Arya Stark"));

    }
}
