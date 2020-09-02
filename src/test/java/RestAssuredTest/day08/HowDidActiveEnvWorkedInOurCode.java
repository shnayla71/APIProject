package RestAssuredTest.day08;

import Utility.ConfigurationReader;
import org.junit.jupiter.api.Test;

public class HowDidActiveEnvWorkedInOurCode {

    @Test
    public void test1(){
        //print out all library1 info if active env=library1
        String current_url="library1"; //ConfigurationReader.getProperty("active_env");
        System.out.println("current env= "+current_url);
        //print library1 database url
        String dbUrl1=ConfigurationReader.getProperty("library1.database.url");
        System.out.println("dbUrl1= "+dbUrl1);
    }
}
