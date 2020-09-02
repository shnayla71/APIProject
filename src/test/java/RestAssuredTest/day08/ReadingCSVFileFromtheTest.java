package RestAssuredTest.day08;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ReadingCSVFileFromtheTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv",numLinesToSkip = 1)
    public void simpleRead(int num1,int num2){
        System.out.println("num1 "+ num1);
        System.out.println("num2 "+num2);

    }
    //Please add another csv file called numbers.csv
    //what if i want some custom name
    //you can refer the row number in my csv file
    //{index}
    //if I want to column data in my display name
    //{0} for first column
    //{1}
    //{2}
    //where do I add the display name to start with

    //Row1 : 5+4=9
    //Row2 : 4+7=11
   // @ParameterizedTest(name = "Row {index} | FirstCol {0} |SecondCol {1}| ThirdCol{2}")
                                             //{0}+{1}={2}
    // teste isim verir name, index testin num gosterir
    @ParameterizedTest(name = "iteration {index}")
    @CsvFileSource(resources = "/numbers.csv",numLinesToSkip = 1)
    public void numbersAddition(int num1,int num2,int result){

        assertEquals(result,num1+num2);


    }

}
