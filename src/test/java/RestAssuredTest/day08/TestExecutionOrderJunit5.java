package RestAssuredTest.day08;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// when we wrote it it oerdered how i can
public class TestExecutionOrderJunit5 {
//default order alphabetical

    @Order(1)
    @Test
    public void testB(){

    }
    @Order(4)
   @Test
    public void testA(){

   }
   @Order(3)
    @Test
    public void testZ(){

    }
    @Order(2)
    @Test
    public void testK(){

    }
}
