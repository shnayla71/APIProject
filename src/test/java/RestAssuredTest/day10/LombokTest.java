package RestAssuredTest.day10;

import POJO.Category;
import POJO.Countries;
import org.junit.jupiter.api.Test;


public class LombokTest {

 @Test
  public void test() {
  Category c1 = new Category("abc", 12);
  System.out.println("c1 = " + c1);

  Countries city=new Countries("AR","ARGENITINA",2);
  System.out.println("city = " + city);





 }
}
