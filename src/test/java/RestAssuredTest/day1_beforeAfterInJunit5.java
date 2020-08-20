package RestAssuredTest;

import org.junit.jupiter.api.*;

public class day1_beforeAfterInJunit5 {
    // junit5 da before ve after static olmak zorunda
    // butun testlerden once bir kere run eder
   @BeforeAll
   public static void setUp(){
       System.out.println("This run before all");
   }
   //her testen once run eder
   @BeforeEach
   public void beforeEachTest(){
       System.out.println("running before the test");
   }
   @Disabled // same with @Ignore
    @Test
    public void test1(){
        System.out.println("test is running");
    }

    @Test
    public void test2(){
        System.out.println("test is running");
    }
    //her testen once run eder
    @AfterEach
    public void afterEachTest(){
        System.out.println("running after the test");
    }
    @AfterAll
    public static void tearDown(){
        System.out.println("This run all the way at the end");
    }
}
