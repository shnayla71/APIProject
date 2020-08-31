package RestAssuredTest.day07;

import Utility.DB_Utility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class mySQLtest {
    @BeforeAll
    public static void initDB(){
        DB_Utility.createConnection("library1");
    }
    @Test
    public void test1(){
        DB_Utility.runQuery("SELECT * FROM books");
        DB_Utility.displayAllData();
    }

    @AfterAll
    public static void closeTest(){
        DB_Utility.destroy();
    }
}
