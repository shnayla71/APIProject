package RestAssuredTest.day07;

import org.junit.jupiter.api.Test;

public class WhatisVarArgs {
    //variable number of arguments
    //create a method that accept N numbers and add all of them

    public static void AllNumbers(int[] num){
        int sum=0;
        for(int eachNum:num){
            sum += eachNum;
        }
        System.out.println(sum);
    }
    @Test
    public void testAdd(){
        AllNumbers(new int[]{1,2,3,4,5,6});
        AllNumbersVarArgs(1,2,3,4,5,78,90,5,4,3,6);
    }

    public static void AllNumbersVarArgs(int...nums){
        int sum=0;
        for(int eachNum:nums){
            sum += eachNum;
        }
        System.out.println(sum);
    }
}
