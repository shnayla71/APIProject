package RestAssuredTest.day11;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GettingRandomNumber {
   @Test
    public void testRandom(){
       Random random=new Random();
       int number= random.nextInt(5);

       List<String> names= Arrays.asList("Anna","vincent","emrah","zuura","zuleyha");
       System.out.println("number = " + number);

       System.out.println("Lucky Name= "+names.get(number));

   }



}
